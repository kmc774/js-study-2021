<template>
  <div>
    <h1>게시글 목록</h1>
    <table class="board_table" id="board_table" items="this.boardLit">
      <tr>
        <th id="bdIdx">번호</th>
        <th id="title">제목</th>
        <th id="userId">작성자</th>
        <th id="regDt">등록일</th>
        <th id="crtnDt">수정일</th>
      </tr>
      <tr v-for="(board , idx) in boardList" :key="idx">
        <td id="resTitle" >{{board.bdIdx}}</td>
        <td id="tit" @click="viewDetail(board.bdIdx)"> {{board.title}}</td>
        <td id="user" >{{board.userId}}</td>
        <td id="reg" >{{board.regDt}}</td>
        <td id="crtn" >{{board.crtnDt}}</td>
      </tr>
    </table>

    <!-- [s] section pagination -->
    <b-pagination
        v-model="searchParam.page"
        :total-rows="this.total"
        :per-page="searchParam.cntPerPage"
        aria-controls="board_table"
        @change="goToPage"
    ></b-pagination>
    <!-- [e] section pagination -->

    <div class="filter_section">

      <b-row>
        <b-col sm="30">
          글자수 선택
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
    <div class="search_section fileBox">
      <select class="filter" v-model=searchParam.type >
        <option value="" selected disabled>키워드 선택</option>
        <option value="all" selected>모두</option>
        <option value="title">제목</option>
        <option value="content">내용</option>
        <option value="userId">저자</option>
      </select>
      <input type="search" class="search"  v-model="searchParam.keyword">
      <b-button pill @click="goSearch">검색</b-button>
    </div>

    <div>
      <b-button pill variant="outline-danger" @click="$router.push('/write');">게시글쓰기</b-button>
    </div>
  </div>

</template>



<script>

export default {
  name: "boardList", // Component의 이름 설정
  props: {
    query: {
      type: Object,
      default: () => {
      }
    }
  },
  data: () => ({
    bdIdx: null,
    total: '',
    searchParam: {
      cntPerPage: 10,
      page: 1,
      type: '',
      keyword: '',
      orderBy: null,
      orderDir: null
    },
    boardList: [],
    optionPerPage:[
                    { value : '10' , text : '10개씩 보기' },
                    { value : '20' , text : '20개씩 보기' },
                    { value : '30' , text : '30개씩 보기' }
                   ],
    optionOrderBy:[
                    { value : null , text : '선택' },
                    { value : 'bdIdx' , text : '번호' },
                    { value : 'title' , text : '제목' },
                    { value : 'content' , text : '내용' } ,
                    { value : 'userId' , text : '작성자' },
                    { value : 'regDt' , text : '등록일' }
                  ],
    optionOrderDir:[
                  { value : null , text : '선택' },
                  { value : 'asc' , text : '오름차순' },
                  { value : 'desc' , text : '내림차순' },
                ]


  }),
  computed: {
    searchQuery() {
      return this.searchParam;
    }
  },
  async mounted() {
    this.initSearchParam();
    await this.getList();
  },

  methods: {
    initSearchParam() {
      this.$setDataAttributes( this.searchParam , this.query )
    },
    getList() {
      this.$axios.get('board/list',
          {
            params: {
              keyword: this.searchParam.keyword
              , type: this.searchParam.type
              , cntPerPage: this.searchParam.cntPerPage
              , page: this.searchParam.page
              , orderBy: this.searchParam.orderBy
              , orderDir: this.searchParam.orderDir
            }
          }
      )
          .then((res) => {
            this.boardList = res.data.boardList;
            this.total = res.data.paging.total;
          })
    },
    goSearch(){
      this.getList();
      this.$router.push({
        name: "list",
        query: this.searchQuery  // 쿼리 스트링으로 데이터를 보내준다.
      }).catch(()=>{})
    },
    viewDetail(bdIdx) {  // ---  게시글 상세보기
      this.$router.push({
        name: "view",
        params : { bdIdx : bdIdx },
        query: this.searchQuery
      });
    },
    goToPage(page){  // --- 페이징 처리
      this.searchParam.page = page;
      this.getList();
    }
  },
  watch: {

  }
}




</script>

<style scoped>

#userId{
  width: 200px;
  text-align: center;
}

#title {
  width: 400px;
  height: 50px;
  text-align: center;
  cursor: pointer;
}


#bdIdx , #regDt , #crtnDt {
  width: 100px;
  height: 50px;
  text-align: center;
}



.board_table{
  margin : auto;
  margin-top: 30px;
  margin-bottom: 30px;
}
.board_table *{
  border: 1px solid;
  font-size: 1vw;
  cursor: pointer;

}

.board_table th{
  background-color: cadetblue;
  color: white;
}

td {
  text-align : center;
  height: 10px
}

tr {
  height: 5vh;
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
.search_btn{
  background-color: rosybrown;
  color : white;
  font-size: x-small;
  font-weight : bold;
  width: 5vw;
  height: 2.5vw;
  border-radius: 5px;
  border: none;
  margin-top: 10px;
  margin-right: 10px;
}

.search_type {
  font-size: small;
  font-weight: bold;
  width: 7vw;
  height: 2.5vw;
  margin-top: 10px;
}

.check_box_section {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-right: 50px;
}
.filter_section {
  display: flex;
  justify-content: space-around;
  align-items: center;
  width: 500px;
  margin: auto;
}

.filter{
  margin-bottom : 30px;
}

.search_section * {
  margin-right: 30px;
}
</style>