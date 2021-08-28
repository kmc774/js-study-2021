<template>
  <div class="section">
    <h1>{{boardTitle}}</h1>
    <b-card bg-variant="light" ref="board_form">
      <b-form-group label-cols-lg="3" label="" label-size="lg" label-class="font-weight-bold pt-0" class="mb-0">
      </b-form-group>
      <b-form-group label="제목 :"  label-for="title">
        <span ref="valid_title" style="display: none">제목을 입력하세요.</span>
        <b-form-input id="nested-title" name="title" ref="title" v-model="board.title" :readonly="isRead"></b-form-input>
      </b-form-group>
      <b-form-group label="내용 :" label-for="content" label-cols-sm="3" label-align-sm="right">
        <span ref="valid_content" style="display: none">내용을 입력하세요.</span>
        <b-form-textarea no-resize id="content" ref="content" v-model="board.content" :readonly="isRead" ></b-form-textarea>
      </b-form-group>
      <b-form-group v-if= isView label="작성자 : " label-cols-sm="3" label-align-sm="right" label-for="userId">
        <b-form-input id="nested-userId" name="userId" ref="userId" v-model="board.userId" readOnly></b-form-input>
      </b-form-group>
      <b-form-group v-if= isView label="등록날짜 : " label-cols-sm="3" label-align-sm="right" label-for="regDt">
        <b-form-input id="nested-regDt" name="regDt" ref="regDt" v-model="board.regDt" readonly ></b-form-input>
      </b-form-group>
      <b-form-group v-if="board.crtnDt != null && isView" label="수정날짜 : " label-cols-sm="3" label-align-sm="right" label-for="crtnDt">
        <b-form-input id="nested-crtnDt" name="crtnDt" ref="crtnDt" v-model="board.crtnDt" readonly ></b-form-input>
       </b-form-group>

      <div class="fileBox" v-show="this.fileList.length > 0 && isView"> <!-- 게시글 상세보기 -->
        <span class="files"> 첨부파일 목록 : </span>
        <span v-for = "(files , idx) in fileList" :key="idx" v-show= isView @click="downloadFile(files.fileIdx)" style="cursor: pointer">  [ {{files.originFileName}} ]</span>
      </div>
      <div class="fileBox" v-show="this.fileList.length > 0 && !isView"> <!-- 게시글 수정하기 -->
        <span class="files"> 첨부파일 목록 : </span>
        <span v-for = "(files , idx) in fileList" :key="idx" v-show= !isView @click="deleteFile(files.fileIdx)" style="cursor: pointer">  [ {{files.originFileName}} ][X]</span>
      </div>
      <div class="fileBox" v-show = !isView>
        <label for="files">파일 추가</label>
        <label @click="clearFile"> CLEAR </label>
        <input type="file" id="files" ref="fileUpload" multiple @change="fileUpload">
      </div>
      <div class="fileBox" id="fileChange" ref="fileChange">
        <span v-show="updateFileList.length > 0 "> 추가된 파일 목록 : </span><br>
        <span v-for = "(files , idx) in updateFileList" :key="idx" >  [ {{files.name}} ]</span>
      </div>


    </b-card>
    <b-button pill v-show= isView id="delBtn"  @click="deleteBoard">삭제하기</b-button>
    <b-button pill v-show= isView id="updateBtn"  variant="outline-danger" @click="updateGo">수정하기</b-button>
    <b-button pill v-show= !isView id="updateBoard"  variant="outline-danger" @click="updateBoard">수정하기</b-button>
    <b-button pill v-show= isView id="listBtn"  variant="outline-danger" @click="goList">목록가기</b-button>
  </div>
</template>

<script>

export default {
  name: "boardDetail",
  props: {
    query: {
      type: Object,
      default: () => {
      }
    }
  },
  data: () => ({
    isRead: true ,
    isView: true ,
    boardTitle : '게시글 보기',
    board: [] ,
    fileList : [],
    updateFileList : [],
    delFileList : []
  }),
  async mounted() {
    await this.getDetail();
  },
  created() {
    this.bdIdx = this.$route.params.bdIdx;
  },
  methods:{
    getDetail(){
      this.$axios.get('/board/view/'+ this.bdIdx )
           .then((res) => {
            this.board = res.data.board;
            this.fileList = res.data.files;
          })
    },  /***********  다운로드 파일 **************/
    downloadFile(fileIdx){
      location.href = '/board/download/' + fileIdx ;
    },  /***********  게시글 삭제  **************/
    deleteBoard(){
      if(confirm("정말 삭제하시겠습니까 ?")){
        this.$axios.delete('/board/delete/'+ this.bdIdx )
            .then((res) => {
              let result =  res.data;
              if( result == 'success' ){
                alert('게시글이 삭제되었습니다.');
                this.$router.push('/list');
              } else if ( result == 'fail' ){
                alert('게시글 게시글 삭제에 실패했습니다.');
                return;
              }
            })
      }
    }, /***********  게시글 수정  **************/
    updateGo(){
      this.isRead = false;
      this.isView = false;
      this.boardTitle = "게시글 수정";
    },
    deleteFile(idx){ /* 기존 파일 삭제 */
      for( let i = 0 ; i < this.fileList.length; i++ ) {
           this.delFileList.push(idx);
           if( this.fileList[i].fileIdx == idx ) {
                 this.fileList.splice(i,1);
              }
      }
    },
    fileUpload(event){ /* 파일 추가 업로드*/
      let fileNum;
      let fileSumSize = 0;
      let filesArr = new Array();
      const fileExtArr = ['hwp', 'doc', 'docx', 'ppt', 'pptx', 'xls', 'txt', 'csv', 'jpg', 'jpeg', 'gif', 'png', 'bmp', 'pdf'];
      const maxSize = 1024 * 1024 * 10; // 10MB 거르기
      const maxSumSize = 1024 * 1024 * 100; // 100MB 거르기

      fileSumSize = 0;
      let files = event.target.files;
      this.files = files;
      filesArr = Array.prototype.slice.call(files); // 얕은 복사 후 입력된 파일 나눠서 배열에 저장

      for( fileNum = 0 ; fileNum < filesArr.length; fileNum++ ){
        let extension = filesArr[fileNum].name.split('.').pop().toLowerCase();
        if( !fileExtArr.includes(extension) ){  //  fileExtArr에 extension이 포함 안된 경우 확인
          alert('등록할 수 없는 형태의 파일이 존재합니다.');
          this.$refs.fileUpload.value = ''; // 초기화
          return;
        }
        if ( filesArr[fileNum].size > maxSize ) {
          alert('파일은 개당 10MB 이하만 업로드 가능합니다.');
          this.$refs.fileUpload.value = ''; // 초기화
          return;
        }
        fileSumSize += filesArr[fileNum].size;  // 파일들의 총 사이즈 구하기
      }
      if(fileSumSize > maxSumSize){
        alert('총 파일의 크기는 100MB를 넘을 수 없습니다.');
        this.$refs.fileUpload.value = ''; // 초기화
        return;
      }
      for( fileNum = 0 ; fileNum < filesArr.length; fileNum++) {
        this.$refs.fileChange.html = "<span> [ " + filesArr[fileNum].name + " ] </span>";
        this.updateFileList.push(filesArr[fileNum]);
      }
    },
    clearFile(){
      this.$refs.fileUpload.value = null;
      this.$refs.fileChange.innerText = '';
      let endNum = this.updateFileList.length;
      this.updateFileList.splice(0, endNum);  // 파일 전체 삭제

    },
    updateBoard(){

      if(this.board.title == null) {
        this.$refs.valid_title.style.display ='inline';
        this.$refs.valid_title.style.color ='crimson';
        return;
      }
      if(this.board.content == null){
        this.$refs.valid_content.style.display ='inline';
        this.$refs.valid_content.style.color ='crimson';
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

        this.$axios.put('/board/update/' + this.board.bdIdx + '/', formData
                    ).then((response) => {
                        let result = response.data;
                        if (result == 'success') {
                          alert('게시글이 수정되었습니다.');
                          location.reload();
                        } else if (result == 'extension') {
                          alert('등록할 수 없는 형태의 파일이 존재합니다.');
                        } else if (result == 'maxSize') {
                          alert('파일은 개당 10MB 이하만 업로드 가능합니다.');
                        } else if (result == 'SumMaxSize') {
                          alert('총 파일의 크기는 100MB를 넘을 수 없습니다.');
                        } else if (result == 'title') {
                          alert('제목을 입력해주세요.');
                        } else if (result == 'content') {
                          alert('내용을 입력해주세요.');
                        } else if (result == 'userId') {
                          alert('작성자를 입력해주세요.');
                        } else if (result == 'fail') {
                          alert('게시글 등록에 실패했습니다.');
                        }
                      })
                    }
                    return;
    },
    goList(){
      console.log(this.query);
      this.$router.push({
        name: "list",
        query : this.query
      }).catch(()=>{});
    }
  }
}
</script>

<style scoped>
.section {
  height : 900px;
}
.card {
  margin: auto;
  width: 50%;
  margin-top: 30px;
}

#content{
  height: 30vh;
}

#updateBtn , #listBtn, #delBtn , #updateBoard {
  margin-top: 30px;
  margin-right: 10px;
}

.fileBox span , div #fileChange {
  margin-top: 20px;
  display: inline-block;
  color: #999;
  font-size: inherit;
  margin-right: 30px;
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
}



</style>