<template>
  <div class="section">
    <h1>게시글 수정하기</h1>
    <b-card bg-variant="light" ref="board_form">
      <b-form-group label-cols-lg="3" label="" label-size="lg" label-class="font-weight-bold pt-0" class="mb-0">
      </b-form-group>
      <b-form-group label="제목 :"  label-for="title">
        <span class="valid" >{{validTitle}}</span>
        <b-form-input name="title" ref="title" v-model="board.title" ></b-form-input>
      </b-form-group>
      <b-form-group label="내용 :" label-for="content" label-cols-sm="3" label-align-sm="right">
        <span class="valid" >{{validContent}}</span>
        <b-form-textarea class="content" ref="content" v-model="board.content" no-resize></b-form-textarea>
      </b-form-group>
      <b-form-group label="작성자 : " label-cols-sm="3" label-align-sm="right" label-for="userId">
        <b-form-input  name="userId" ref="userId" v-model="board.userId" readOnly></b-form-input>
      </b-form-group>
      <b-form-group label="등록날짜 : " label-cols-sm="3" label-align-sm="right" label-for="regDt">
        <b-form-input name="regDt" ref="regDt" v-model="board.regDt" readonly ></b-form-input>
      </b-form-group>
      <b-form-group v-if="board.crtnDt != null" label="수정날짜 : " label-cols-sm="3" label-align-sm="right" label-for="crtnDt">
        <b-form-input name="crtnDt" ref="crtnDt" v-model="board.crtnDt" readonly ></b-form-input>
       </b-form-group>

      <div class="fileBox" v-show="this.fileList.length > 0"> <!-- 게시글 수정하기 -->
        <span class="files"> 첨부파일 목록 : </span>
        <span v-for = "(files , idx) in fileList" :key="idx"  @click="deleteFile(files.fileIdx)" style="cursor: pointer">{{files.originFileName}}   <b-icon icon="x-square-fill"></b-icon></span>
      </div>
      <div class="fileBox">
        <label for="files">파일 추가</label>
        <label @click="clearFile()"> CLEAR </label>
        <input type="file" id="files" class="files" ref="fileUpload" multiple @change="fileUpload">
      </div>
      <div class="fileBox" ref="fileChange">
        <span v-show="updateFileList.length > 0 "> 추가된 파일 목록 : </span><br>
        <p v-for = "(files , idx) in updateFileList" :key="idx" >  [ {{ files.name }} ]</p>
      </div>
    </b-card>

    <b-button pill variant="outline-danger" @click="updateBoard">수정하기</b-button>
    <b-button pill variant="outline-danger" @click="cancelUpdate">취소하기</b-button>
  </div>
</template>

<script>

export default {
  name: "boardUpdate",
  props: {
    query: {
      type: Object,
      default: () => {
      }
    }
  },
  inject: ['boardService'] ,
  data: () => ({
    validTitle: "",
    validContent: "",
    board: [] ,
    fileList : [],  // 기존 파일
    delFileList : [], // 삭제 할 파일
    updateFileList : [] // 새로 업데이트 할 파일

  }),
  async mounted() {
    await this.getDetail();
  },
  created() {
    this.idx = this.$route.params.bdIdx; // 파라미터 데이터 가져오기
  },
  methods:{
    async getDetail(){
      const {board , files} = await this.boardService.getBoardDetail(this.idx);
      this.board = board;
      this.fileList = files;
    }, /***********  기존 파일 삭제  **************/
    deleteFile(idx){ /* 기존 파일 삭제 */
      for( let i = 0 ; i < this.fileList.length; i++ ) {
           this.delFileList.push(idx);
           if( this.fileList[i].fileIdx == idx ) {
                 this.fileList.splice(i,1);
              }
      }
    }, /***********  파일 추가  **************/
    fileUpload(event){ /* 파일 추가 업로드*/
      let fileNum = 0;
      let fileSumSize = 0;
      const fileExtArr = ['hwp', 'doc', 'docx', 'ppt', 'pptx', 'xls', 'txt', 'csv', 'jpg', 'jpeg', 'gif', 'png', 'bmp', 'pdf'];
      const maxSize = 1024 * 1024 * 10; // 10MB 거르기
      const maxSumSize = 1024 * 1024 * 100; // 100MB 거르기

      fileSumSize = 0;
      let files = event.target.files;
      this.files = files;
      this.updateFileList = Array.prototype.slice.call(files); // 얕은 복사 후 입력된 파일 나눠서 배열에 저장

      for( fileNum = 0 ; fileNum < this.updateFileList.length; fileNum++ ){
        let extension = this.updateFileList[fileNum].name.split('.').pop().toLowerCase();
        if( !fileExtArr.includes(extension) ){  //  fileExtArr에 extension이 포함 안된 경우 확인
          alert('등록할 수 없는 형태의 파일이 존재합니다.');
          this.$refs.fileUpload.value = ''; // 초기화
          return;
        }
        if ( this.updateFileList[fileNum].size > maxSize ) {
          alert('파일은 개당 10MB 이하만 업로드 가능합니다.');
          this.$refs.fileUpload.value = ''; // 초기화
          return;
        }
        fileSumSize += this.updateFileList[fileNum].size;  // 파일들의 총 사이즈 구하기
      }
      if(fileSumSize > maxSumSize){
        alert('총 파일의 크기는 100MB를 넘을 수 없습니다.');
        this.$refs.fileUpload.value = ''; // 초기화
        return;
      }
    },
    clearFile() {
      this.$refs.fileUpload.value = null;
      this.updateFileList = [];
    },
   async updateBoard(){

      if(this.board.title == '') {
        this.validTitle = "제목을 입력하세요.";
        return;
      }
      if(this.board.content == ''){
        this.validContent = "내용을 입력하세요.";
        return;
      }
      if( confirm('게시글을 수정하시겠습니까 ?') ){
        const formData = new FormData();
        formData.append('bdIdx' , this.board.bdIdx);
        formData.append('title' , this.board.title);
        formData.append('content' , this.board.content);
        for(let i = 0 ; i < this.delFileList.length; i ++){
          formData.append('delFiles' , this.delFileList[i]);
        }
        for(let i = 0 ; i < this.updateFileList.length; i ++){
          formData.append('files' , this.updateFileList[i]);
        }
       const result = await this.boardService.updateBoard( this.idx , formData );
        if(result == 'success'){
          this.$router.push({
            path: "/view/" + this.idx,
            query: this.query
          });
        }
        return;
      }
      return;
    },
    cancelUpdate(){
      if(confirm('수정을 취소하시겠습니까 ?')) {
        this.$router.push({
          path: "/view/" + this.idx,
          query: this.query
        });
      }
      return;
    }
  }
}
</script>

<style scoped>
.section {
  height : 1000px;
}
.card {
  margin: auto;
  width: 60%;
  margin-top: 30px;
}

.content{
  height: 30vh;
}

.valid {
  color : crimson;
}

.fileBox span {
  margin-top: 15px;
  display: inline-block;
  color: #999;
  font-size: inherit;
  margin-right: 10px;
}

.fileBox p {
  font-size: 1.5vw;
  color: grey;
}
.fileBox label {
  margin-top: 20px;
  display: inline-block;
  padding: .5em .75em;
  color: #999;
  font-size: inherit;
  line-height: normal;
  vertical-align: middle;
  background-color: #fdfdfd;
  cursor: pointer;
  border: 1px solid #ebebeb;
  border-bottom-color: #e2e2e2;
  border-radius: .25em;
  margin-right: 30px;
}

.fileBox input[type="file"] { /* 파일 필드 숨기기 */
  position: absolute;
  width: 1px;
  height: 1px;
  padding: 0;
  margin: -1px;
  overflow: hidden;
  clip:rect(0,0,0,0);
  border: 0;
}

button{
  cursor: pointer;
  margin-top: 30px;
  margin-right: 10px;
}


</style>