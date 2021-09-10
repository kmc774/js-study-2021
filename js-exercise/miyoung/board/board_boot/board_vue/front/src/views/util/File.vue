<template>
  <div>
    <div class="fileBox">
      <label for="files">파일 첨부</label>
      <label @click="clearFile"> CLEAR </label>
      <input type="file" id="files" ref="fileUpload" multiple @change="fileUpload">
    </div>
    <div ref="fileChange">
    </div>
  </div>

</template>

<script>
export default {
  name: "File",
  data : () =>({
    files : null
  }),
  methods: {
    fileUpload(event){ // 컴포넌트로 빼기 </file_upload>
      let fileNum;
      let fileSumSize = 0;
      let filesArr = new Array();
      const fileExtArr = ['hwp', 'doc', 'docx', 'ppt', 'pptx', 'xls', 'txt', 'csv', 'jpg', 'jpeg', 'gif', 'png', 'bmp', 'pdf'];
      const maxSize = 1024 * 1024 * 10; // 10MB 거르기
      const maxSumSize = 1024 * 1024 * 100; // 100MB 거르기

      fileSumSize = 0;
      let files = event.target.files;
      this.files = files;
      console.log(this.files);
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
      for( fileNum = 0 ; fileNum < filesArr.length; fileNum++) { // :items로 불러주는 형태로
        this.$refs.fileChange.append(' [ ' + filesArr[fileNum].name + ' ] ');
      }
    },
    clearFile(){
      this.$refs.fileUpload.value = null;
      this.$refs.fileChange.innerText = '';
    }
  }
}
</script>

<style scoped>

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


</style>