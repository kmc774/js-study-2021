<template>
  <div class="section">
    <h1>게시글 쓰기</h1>
    <b-card bg-variant="light" ref="board_form">
      <b-form-group label-cols-lg="3" label="" label-size="lg" label-class="font-weight-bold pt-0" class="mb-0">
      </b-form-group>
        <b-form-group label="제목 :"  label-for="title"><span ref="valid_title" style="display: none">제목을 입력하세요.</span>
          <b-form-input id="nested-title" name="title" ref="title" v-model="title"></b-form-input>
        </b-form-group>
      <b-form-group label="내용 :" label-for="content" label-cols-sm="3" label-align-sm="right"><span ref="valid_content" style="display: none">내용을 입력하세요.</span>
        <b-form-textarea no-resize id="content" ref="content" v-model="content"></b-form-textarea>
      </b-form-group>
        <b-form-group label="작성자 : " label-cols-sm="3" label-align-sm="right" label-for="userId"><span ref="valid_userId" style="display: none">작성자를 입력하세요.</span>
          <b-form-input id="nested-userId" name="userId" ref="userId" v-model="userId"></b-form-input>
        </b-form-group>

        <div class="fileBox">
          <label for="files">파일 첨부</label>
          <label @click="clearFile"> CLEAR </label>
          <input type="file" id="files" ref="fileUpload" multiple @change="fileUpload">
        </div>
        <div id="fileChange" ref="fileChange">
        </div>
    </b-card>
    <b-button pill id="addBtn"  variant="outline-danger" @click="insertBoard">작성완료</b-button>
  </div>
</template>

<script>

export default {
  name: "BoardWrite",
  data(){
    return {
      title : '',
      content : '',
      userId : '',
      files : null
    }
  },
  methods: {
    fileUpload(event){
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
          if( !fileExtArr.includes(extension) ){  // extension이 fileExtArr에 포함되어있는지 확인
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
          this.$refs.fileChange.append(' [ ' + filesArr[fileNum].name + ' ] ');
        }
    },
    clearFile(){
        this.$refs.fileUpload.value = null;
        this.$refs.fileChange.innerText = '';
    },
    insertBoard(){

        if(this.title == '') {
          this.$refs.valid_title.style.display ='inline';
          this.$refs.valid_title.style.color ='crimson';
          return;
        }
        if(this.content == ''){
          this.$refs.valid_content.style.display ='inline';
          this.$refs.valid_content.style.color ='crimson';
          return;
        }
        if(this.userId == ''){
          this.$refs.valid_userId.style.display ='inline';
          this.$refs.valid_userId.style.color ='crimson';
          return;
        }
        console.log(this.userId);
          if(confirm('게시글을 등록하시겠습니까 ?')){
            const formData = new FormData();
            formData.append('title' , this.title);
            formData.append('content' , this.content);
            formData.append('userId' , this.userId);
            if(this.files != null){
              for(let i = 0 ; i < this.files.length; i ++){
                formData.append('files' , this.files[i]);
              }
            }
            this.$axios.post('board/write' , formData)
                .then((response) => {
                      let result = response.data;
                      if (result == 'success') {
                        alert('게시글이 등록되었습니다.');
                        location.href = '/';
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
                        location.href = '/';
                      }
                })
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

#content{
  height: 30vh;
}

div #fileChange {
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