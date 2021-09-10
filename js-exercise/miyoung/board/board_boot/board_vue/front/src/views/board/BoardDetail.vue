<template>
  <div class="section" ref="section">
    <h1>게시글 보기 ({{board.bdIdx}})</h1>
    <span>조회수 : {{ board.viewCount }}</span>
    <b-card bg-variant="light" ref="board_form">
      <b-form-group label-cols-lg="3" label="" label-size="lg" label-class="font-weight-bold pt-0" class="mb-0">
      </b-form-group>
      <b-form-group label="제목 :"  label-for="title">
        <span ref="valid_title" style="display: none">제목을 입력하세요.</span>
        <b-form-input id="nested-title" name="title" ref="title" v-model="board.title" readonly></b-form-input>
      </b-form-group>
      <b-form-group label="내용 :" label-for="content" label-cols-sm="3" label-align-sm="right">
        <span ref="valid_content" style="display: none">내용을 입력하세요.</span>
        <b-form-textarea no-resize class="content" ref="content" v-model="board.content" readonly ></b-form-textarea>
      </b-form-group>
      <b-form-group label="작성자 : " label-cols-sm="3" label-align-sm="right" label-for="userId">
        <b-form-input  name="userId" ref="userId" v-model="board.userId" readOnly></b-form-input>
      </b-form-group>
      <b-form-group label="등록날짜 : " label-cols-sm="3" label-align-sm="right" label-for="regDt">
        <b-form-input  name="regDt" ref="regDt" v-model="board.regDt" readonly></b-form-input>
      </b-form-group>
      <b-form-group v-if="board.crtnDt != null" label="수정날짜 : " label-cols-sm="3" label-align-sm="right" label-for="crtnDt">
        <b-form-input  name="crtnDt" ref="crtnDt" v-model="board.crtnDt" readonly ></b-form-input>
      </b-form-group>
      <div class="fileBox" v-show="this.fileList.length > 0"> <!-- 게시글 상세보기 -->
        <span class="files"> 첨부파일 목록 : </span>
        <span v-for = "(files , idx) in fileList" :key="idx" @click="downloadFile(files.fileIdx)" style="cursor: pointer">  [ {{files.originFileName}} ]</span>
      </div>
    </b-card>

    <div class="etc_section">
      <div class="comment-ok">
        <h5>댓글 : {{ comments.length }}</h5>
        <b-icon icon="arrow-down-circle" v-if="comments.length > 0" font-scale="1.5" style="cursor:pointer" @click="openComment"></b-icon>
      </div>
      <div class="like">
        <h5 @click="countLike" style="cursor: pointer">추천<b-icon icon="hand-thumbs-up" animation="cylon-vertical" font-scale="1"></b-icon>: {{ board.likeCount }} </h5>
        </div>
    </div>
    <div class="comment-div">
      <div v-show="isRead" class="comment" v-for = "(comment , idx) in commentList" :key="idx">
        <div class="comment-info" >
          <span v-show="isRead" >{{comment.bdComment}}</span>
          <span v-show="isRead">{{comment.crtnDt}}</span>
        </div>
        <div>
          <div v-show="isRead">
            <b-dropdown  variant="secondary" size="sm" >
              <b-dropdown-item-button>
                <a style="cursor:pointer;" @click="goUpdateComment(comment.comIdx , comment.bdComment)">수정</a>
              </b-dropdown-item-button>
              <b-dropdown-item-button>
                <a style="cursor:pointer;" @click="deleteComment(comment.comIdx)">삭제</a>
              </b-dropdown-item-button>
            </b-dropdown>
          </div>
        </div>
      </div>
      <div v-show="!isRead" class="updateCm" style="display: flex; justify-content: flex-end">
        <b-form-input v-show="!isRead" class="bdComment" ref="bdComment" v-model="oldComment"></b-form-input>
        <div>
          <a style="cursor:pointer;" @click="updateComment"><b-icon icon="check-circle"></b-icon> 확인</a>
          <a style="cursor:pointer;" @click="cancelComment"><b-icon icon="x-circle"></b-icon> 취소</a>
        </div>
        </div>
      <div class="add_comment" v-if="commentList.length > 0 || comments.length == 0">
        <b-form-textarea no-resize v-model="bdComment" placeholder="댓글쓰기" style="width:80%"></b-form-textarea>
        <b-button variant="outline-secondary" @click="addComment">확인</b-button>
      </div>
    </div>

    <b-button pill class="delBtn"  @click="deleteBoard">삭제하기</b-button>
    <b-button pill class="updateBtn"  variant="outline-danger" @click="goUpdate">수정하기</b-button>
    <b-button pill class="listBtn"  variant="outline-danger" @click="goList">목록가기</b-button>
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
    },
  },
  inject: ['boardService'] ,
  data: () => ({
    comIdx: '',
    bdComment: '',
    oldComment:'',
    newComment:'',
    board: [] ,
    fileList : [],
    comments: [],
    commentList: [],
    isOpen: true,
    isRead: true
  }),
  async mounted() {
    await this.countView();
    await this.getDetail();
  },
  created() {
    this.bdIdx = this.$route.params.bdIdx; // 데이터 매핑
  },
  methods:{
    async getDetail(){
      const {board , files, comments} = await this.boardService.getBoardDetail(this.bdIdx);
      this.board = board;
      this.fileList = files;
      this.comments = comments;
    },
    async downloadFile(fileIdx){
      await this.boardService.downloadFile( fileIdx );
    },
    async deleteBoard() {
      if(confirm('삭제하시겠습니까 ?')){
        const result = await this.boardService.deleteBoard(this.bdIdx);
        if (result == 'success') {
          alert('게시글이 삭제되었습니다.');
          this.$router.push({
            path: "/list",
            query: this.query
          });
        } else if (result == 'fail') {
          alert('게시글 게시글 삭제에 실패했습니다.');
          return;
        }
      }
    },
    goUpdate(){
     this.$router.push({
       path: '/update/' + this.board.bdIdx ,
       query: this.query
     })
    },
    goList(){
      this.$router.push({
        path: "/list",
        query : this.query
      }).catch(()=>{});
    },
   openComment(){
      if(this.isOpen){
        this.commentList = this.comments;
        this.isOpen = false;
      } else if ( !this.isOpen ){
        this.commentList = [];
        this.isOpen = true;
      }
    },
    async addComment(){
      const commentList = await this.boardService.addComment(this.bdIdx , this.bdComment);
      this.commentList = commentList;
      this.comments = this.commentList;
      this.bdComment ='';

    },
    goUpdateComment(comIdx , bdComment ){
      this.isRead = false;
      this.comIdx = comIdx;
      this.newComment = bdComment;
      this.oldComment = bdComment;
    },
    async updateComment(){
      const commentList = await this.boardService .updateComment(this.bdIdx , this.comIdx , this.oldComment);
      this.comments = commentList;
      this.isRead = true;
      this.isOpen = true;
      this.openComment();
    },
    cancelComment(){
      this.oldComment  = this.newComment;
      this.isRead = true;
    },
    deleteComment(comIdx){
      if(confirm('댓글을 삭제하시겠습니까 ?')){
        this.boardService.deleteComment(comIdx);
        for( let i = 0 ; i < this.commentList.length; i++ ) {
          if (this.commentList[i].comIdx == comIdx) {
            this.commentList.splice(i, 1);
            this.comments = this.commentList;
          }
        }
        return;
      }
      return;
    },
    async countLike(){
      const num = await this.boardService.countLike(this.bdIdx);
      this.board.likeCount = this.board.likeCount + num;
    },
    async countView(){
      const num = await this.boardService.countView(this.bdIdx);
      this.board.viewCount = this.board.viewCount + num;
    }

  },
  watch: { // comment창 닫을 경우 isRead값 변경
    isOpen: function(){
      this.isRead = true;
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
  margin-bottom: 30px;
}
.content{
  height: 30vh;
}

.updateBtn , .listBtn, .delBtn {
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

.comment {
  padding: 10px;
  text-align: initial;
  border-bottom: solid 1px lightgray;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 10px;
}

.comment-div {
  margin: auto;
  width: 50%;
}

.comment-div span{
  color: gray;
}
.comment-div .add_comment {
  display: flex;
  flex-direction: row;
  margin-top: 20px;
  justify-content: space-around;
}
.bdComment {
  height: 30px;
  font-size: 1em;
  margin-bottom: 10px;
  word-break: break-word;
}
.updateCm {
  display: flex;
  flex-direction: column;
  margin-top: 20px;
}
.updateCm a{
  margin-right: 10px;
}
.etc_section{
  width: 50%;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  margin: auto;
}
.comment-ok {
  display: flex;
}
.comment-ok h5 {
  margin-right: 10px;
  margin-bottom: 0;
}
.comment-info {
  display: flex;
  flex-direction: column;
  width: 100%;
}
.comment-info a {
  margin-right:10px;
}
.like{
  display: flex;
  align-items: center;
}
.like h5 {
  margin-right: 10px;
  margin-bottom: 0;
}
</style>