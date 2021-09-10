<template>
  <div class="section">
    <h1>게시글 쓰기</h1>
    <b-card bg-variant="light" ref="board_form">
      <b-form-group label-cols-lg="3" label="" label-size="lg" label-class="font-weight-bold pt-0" class="mb-0">
      </b-form-group>
        <b-form-group label="제목 :"  label-for="title"><span class="valid" >{{validTitle}}</span>
          <b-form-input name="title" ref="title" v-model="title"></b-form-input>
        </b-form-group>
      <b-form-group label="내용 :" label-for="content" label-cols-sm="3" label-align-sm="right"><span class="valid" >{{validContent}}</span>
        <b-form-textarea no-resize class="content" ref="content" v-model="content"></b-form-textarea>
      </b-form-group>
        <b-form-group label="작성자 : " label-cols-sm="3" label-align-sm="right" label-for="userId"><span class="valid">{{validUserId}}</span>
          <b-form-input  name="userId" ref="userId" v-model="userId"></b-form-input>
        </b-form-group>
        <b-form-group label="비밀번호 : " label-cols-sm="3" label-align-sm="right" label-for="userPw">
          <b-form-input  name="userPw" ref="userPw" v-model="userPw"></b-form-input>
        </b-form-group>

        <div class="fileBox">

          <label for="files">파일 첨부</label>
          <label @click="clearFile"> CLEAR </label>
          <input type="file" id="files" ref="fileUpload" multiple @change="fileUpload">
        </div>
        <div class="fileChange" ref="fileChange">
          <span v-for = "(files , idx) in filesArr" :key="idx" style="cursor: pointer">  [ {{files.name}} ]</span>
        </div>


    </b-card>
    <b-button pill id="addBtn"  variant="outline-danger" @click="insertBoard">작성완료</b-button>

  </div>

</template>

<script>
//import File from "@/views/util/File";

export default {
  name: "BoardWrite",
  components : {
    //File
  },
  inject: ['boardService'],
  data(){
    return {
      title : '',
      content : '',
      userId : '',
      userPw: '' ,
      validTitle : '',
      validContent : '',
      validUserId : '',
      files : null,
      filesArr: []
    }
  },
  methods: {
     fileUpload(event){ // 컴포넌트로 빼기 </file_upload>
        let fileNum;
        let fileSumSize = 0;
        const fileExtArr = ['hwp', 'doc', 'docx', 'ppt', 'pptx', 'xls', 'txt', 'csv', 'jpg', 'jpeg', 'gif', 'png', 'bmp', 'pdf'];
        const maxSize = 1024 * 1024 * 10; // 10MB 거르기
        const maxSumSize = 1024 * 1024 * 100; // 100MB 거르기

        fileSumSize = 0;
        let files = event.target.files;
        this.files = files;
        this.filesArr = Array.prototype.slice.call(files); // 얕은 복사 후 입력된 파일 나눠서 배열에 저장

        for( fileNum = 0 ; fileNum < this.filesArr.length; fileNum++ ){
          let extension = this.filesArr[fileNum].name.split('.').pop().toLowerCase();
          if( !fileExtArr.includes(extension) ){  // extension이 fileExtArr에 포함되어있는지 확인
            alert('등록할 수 없는 형태의 파일이 존재합니다.');
            this.$refs.fileUpload.value = ''; // 초기화
            return;
          }
          if ( this.filesArr[fileNum].size > maxSize ) {
            alert('파일은 개당 10MB 이하만 업로드 가능합니다.');
            this.$refs.fileUpload.value = ''; // 초기화
            return;
          }
          fileSumSize += this.filesArr[fileNum].size;  // 파일들의 총 사이즈 구하기
        }
        if(fileSumSize > maxSumSize){
          alert('총 파일의 크기는 100MB를 넘을 수 없습니다.');
          this.$refs.fileUpload.value = ''; // 초기화
          return;
        }
    },
    clearFile(){
        this.$refs.fileUpload.value = null;
    },
    async insertBoard(){

        if(this.title == '') { // 모델을 쳐다보게끔 설정 데이터를 조작
          this.validTitle = "제목을 입력하세요.";
          return ;
        }
        if(this.content == ''){
          this.validContent = "내용을 입력하세요.";
          return;
        }
        if(this.userId == ''){
          this.validUserId = "작성자를 입력하세요.";
          return;
        }

          if(confirm('게시글을 등록하시겠습니까 ?')){
            const formData = new FormData();
            formData.append('title' , this.title);
            formData.append('content' , this.content);
            formData.append('userId' , this.userId);
            formData.append('userPw' , this.userPw);
            if(this.files != null){
              for(let i = 0 ; i < this.files.length; i ++){
                formData.append('files' , this.files[i]);
              }
            }
            await this.boardService.insertBoard(formData);
          }
          return;
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

.content{
  height: 30vh;
}

.valid{
  color: crimson;
}

div .fileChange {
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


#addBtn {
  margin-top: 30px;
}

</style>