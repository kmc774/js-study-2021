<template>
  <div class="hello">
    <h1>게시글 목록</h1>

    <b-container fluid>
      <b-row>
        <b-col sm="3">
          <label>제목</label>
          <b-form-input type="text" v-model="searchParam.title"></b-form-input>
        </b-col>
        <b-col sm="3">
          <label>내용</label>
          <b-form-input type="text" v-model="searchParam.content"></b-form-input>
        </b-col>
        <b-col sm="3">
          <label>저자</label>
          <b-form-input type="text" v-model="searchParam.userId"></b-form-input>
        </b-col>
        <b-col sm="3">
          <b-button @click="doSearch" >
            검색
          </b-button>
        </b-col>
      </b-row>

      <b-row>
        <b-col sm="3"></b-col>
        <b-col sm="3"></b-col>
        <b-col sm="3">
          order by
          <b-form-select v-model="searchParam.orderBy"
                         :options="optionOrderBy"
                         @change="doSearch"
                         size="sm" />
          <b-form-select v-model="searchParam.orderDir"
                         :options="optionOrderDir"
                         @change="doSearch"
                         size="sm" />
        </b-col>
        <b-col sm="3">
          per-page
          <b-form-select v-model="searchParam.perPage"
                         :options="optionPerPage"
                         @change="doSearch"
                         size="sm" />
        </b-col>
      </b-row>

    </b-container>


    <b-table :items="boardList" @row-clicked="gotoView" />

    <b-pagination :value="searchParam.page"
                  :per-page="searchParam.perPage"
                  :total-rows="pagination.totalCount"
                  @change="gotoPage" />

    <p @click="gotoWrite">
      Write
    </p>

  </div>
</template>



<script>

export default {
  name: 'BoardList',

  props: {
    boardPath:{
      type: String,
      required: true
    },
    query: {
      type: Object,
      default: () => {
      }
    }
  },

  components: {},

  data() {
    return {
      pagination: {
        totalCount: 0,
      },
      searchParam: {
        title: '',
        content: '',
        author:'',
        orderBy:'regDt',
        orderDir:'desc',
        page: 1,
        perPage: 10,
      },
      boardList: [],

      optionPerPage:[2, 3, 4],
      //TODO: sql injection
      optionOrderBy:['regDt', 'title', 'content', 'userId'],
      optionOrderDir:['asc', 'desc']
    }
  },

  computed: {
    searchQuery() {
      return this.searchParam;
    }
  },

  created() {
  },

  async mounted() {
    this.initSearchParam();
    await this.fetchList();
  },

  methods: {

    initSearchParam(){
      //TODO: query 에서 값을 가져오는 경우 모두 String 타입임을 주의.
      this.$setDataAttributes( this.searchParam, this.query);
      // this.pagination.page = this.query.page;
    },

    gotoWrite() {
      this.$router.push({
        path: `${this.boardPath}/write`,
        query: this.searchQuery
      })
    },

    gotoView(row) {
      this.$router.push({
        path: `board/view/${row.seq}`,
        query: this.searchQuery
      })
    },

    gotoPage(page) {
      this.searchParam.page = page;
      this.doSearch();
    },

    doSearch(){
      // query-string 만 바뀐 경우에는 router 에서 페이지 로딩 하지 않음.
      // $route 경로 변경을 감시하여, 명시적으로 데이타 로딩을 호출.
      this.$router.push({
        path: `${this.boardPath}/list`,
        query: this.searchQuery
      })
    },

    async fetchList() {

      const {search, rows} = await this.boardService.getBoardList(this.boardCode, this.searchParam);

      //this.$setDataAttributes( this.pagination, search);
      this.pagination.totalCount = search.totalCount;
      this.searchParam.page = search.page;

      this.boardList.splice(0, this.boardList.length);
      this.boardList.push(...rows)
    }
  },

  watch: {
    $route(to, from) {

      // react to route changes...
      this.fetchList();
    }
  }


}

</script>

<style scoped>

</style>