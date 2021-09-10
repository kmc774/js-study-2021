<template>
  <div>
    <h1>게시글 목록</h1>
    <div class="filter_section">
      <b-row>
        <b-col sm="30">
          게시글수
          <b-form-select v-model="searchParam.cntPerPage"
                         :options="optionPerPage"
                         size="sm"
                         @change="goSearch" />
        </b-col>
        <b-col sm="30">
          정렬
          <b-form-select v-model="searchParam.orderBy"
                         :options="optionOrderBy"
                         size="sm" />
          <b-form-select v-model="searchParam.orderDir"
                         :options="optionOrderDir"
                         @change="goSearch"
                         size="sm" />
        </b-col>
      </b-row>
    </div>
    <div class="search_section">
      <b-col sm="50">
        검색 키워드
        <b-form-select v-model="searchParam.type"
                       :options="optionType"
                       size="sm" />
        <input type="search" class="search"  v-model="searchParam.keyword">
        <b-button pill @click="goSearch">검색</b-button>
      </b-col>
    </div>
    <div>
      <b-button pill variant="outline-danger" @click="$router.push('/write');">게시글쓰기</b-button>
    </div>


    <table class="board_table" items="this.boardLit">
      <tr>
        <th>번호</th>
        <th style="width: 400px; height: 50px; text-align: center; cursor: pointer;">제목</th>
        <th>작성자</th>
        <th>등록일</th>
        <th>수정일</th>
      </tr>
      <tr v-for="(board , idx) in boardList" :key="idx">
        <td>{{board.bdIdx}}</td>
        <td @click="viewDetail(board.bdIdx , board.userPw)"> {{board.title}}<b-icon icon="lock-fill" v-if="board.userPw != null"></b-icon></td>
        <td>{{board.userId}}</td>
        <td>{{board.regDt}}</td>
        <td>{{board.crtnDt}}</td>
      </tr>
    </table>


    <!-- [s] section pagination  -->
    <b-pagination
        v-if="this.total > 0"
        v-model="searchParam.page"
        :total-rows="this.total"
        :per-page="searchParam.cntPerPage"
        @change="goToPage"
    ></b-pagination>
    <!-- [e] section pagination -->

  <!--  <infinite-loading @infinite="infiniteHandler" spinner="waveDots"></infinite-loading> -->

  </div>

</template>



<script>
// import InfiniteLoading from 'vue-infinite-loading';

export default {
  name: "boardList", // Component의 이름 설정
  props: {
    query: {
      type: Object,
      default: () => {
      }
    }
  },
  inject: ['boardService'] ,
  data: () => ({
    bdIdx: null,
    userPw: null,
    total: '',
    searchParam: {
      cntPerPage: 10,
      page: 0 ,
      type: '',
      keyword: '',
      orderBy: '',
      orderDir: ''
    },
    boardList: [],
    optionPerPage: [
                    { value : '10' , text : '10개씩 보기' },
                    { value : '20' , text : '20개씩 보기' },
                    { value : '30' , text : '30개씩 보기' }
                   ],
    optionOrderBy: [
                    { value : '' , text : '선택' },
                    { value : 'bdIdx' , text : '번호' },
                    { value : 'title' , text : '제목' },
                    { value : 'content' , text : '내용' } ,
                    { value : 'userId' , text : '작성자' },
                    { value : 'regDt' , text : '등록일' }
                   ],
    optionOrderDir: [
                    { value : '' , text : '선택' },
                    { value : 'asc' , text : '오름차순' },
                    { value : 'desc' , text : '내림차순' },
                   ],
    optionType:[
                    { value : '' , text : '선택' },
                    { value : 'all' , text : '전체' },
                    { value : 'bdIdx' , text : '번호' },
                    { value : 'title' , text : '제목' },
                    { value : 'content' , text : '내용' },
                    { value : 'userId' , text : '작성자' }
                ]
  }),
  computed: {
    searchQuery() {
      return this.searchParam;
    }
  },
  components : {
  //   InfiniteLoading ,
  },
  async mounted() {
    this.initSearchParam();
    await this.getList();
  },

  methods: {
    initSearchParam() {
      this.$setDataAttributes( this.searchParam , this.query )
    },
    async getList() {
      const {boardList, paging} = await this.boardService.getBoardList(this.searchParam);
      this.boardList = boardList;
      this.total = paging.total;
      this.searchParam.page = paging.page;
    },
    goSearch(){
      this.getList();
    },
    viewDetail(bdIdx , userPw) {  // ---  게시글 상세보기
      if(userPw != null){  // 비밀번호 인증하기
       const inputPw =  window.prompt('게시글 비밀번호를 입력하세요.');
        if(inputPw == null){
            return;
          }
        if(userPw == inputPw){
           this.$router.push({
             path: "/view/" + bdIdx,
             query: this.searchQuery,
           }); return;
       }
         alert('비밀번호를 다시 입력하세요.');
         return;
      }
      this.$router.push({
        path: "/view/" + bdIdx ,
        query: this.searchQuery
      });
    },
    goToPage(page){  // --- 페이징 처리
      this.searchParam.page = page;
      this.getList();
    },/*
   infiniteHandler($state){
        this.$axios.get('http://localhost:8080/board/list', { params : this.searchParam})
            .then((res) => {
             if(res.data.boardList.length <= res.data.paging.total){
              setTimeout(()=> {
                this.searchParam.cntPerPage += 3;
                this.boardList = res.data.boardList;
                if(res.data.boardList.length == res.data.paging.total){ // 모든 게시글이 보여진 경우
                  $state.complete();
                }
                $state.loaded();
              }, 1000)
             } else {
               $state.complete();
             }
            }).catch(error => {
              console.error(error);
        })
    }
*/
  },
  watch: {

  }
}




</script>

<style scoped>

th {
  background-color: cadetblue;
  color: white;
  width: 100px;
  height: 50px;
  text-align: center;
  border: 1px solid;
  font-size: 1vw;
  cursor: pointer;
}
td {
  text-align : center;
  height: 10px;
  border: 1px solid;
  font-size: 1vw;
  cursor: pointer;
}
tr {
  height: 5vh;
  border: 1px solid;
  font-size: 1vw;
  cursor: pointer;
}
.board_table{
  margin : auto;
  margin-top: 30px;
  margin-bottom: 30px;
}
button{
  cursor: pointer;
}
ul.pagination.b-pagination {
  display: flex;
  justify-content: center;
}
.search {
  border: none;
  border-bottom: 1px solid;
  margin-right: 20px;
  margin-left: 20px;
  width: 20vw;
  height: 2.5vw;
}
.filter_section {
  display: flex;
  justify-content: space-around;
  align-items: center;
  width: 500px;
  margin: auto;
}
.search_section {
  margin-bottom: 30px;
}

</style>