<template>
  <div id="app">
    <Header/>
    <div class="section">
      <!-- 라우터가 들어가야 함 : 해당 영역에 router 결과 값들이 바운딩 된다.-->
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import Header from './components/common/Header.vue'

export default {
  name: 'App',
  components: {
    Header, // 컴포넌트로 사용하려면 넣어줘야 함
  }
}

</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

.section {
  margin-bottom : 30px;
}
</style>
