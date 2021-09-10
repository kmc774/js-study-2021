<template>
  <div>
    <b-navbar class="navbar" type="dark" variant="dark" >
      <b-navbar-nav>
        <b-nav-item href="/">List</b-nav-item>
        <b-nav-item href="/write">Write</b-nav-item>
      </b-navbar-nav>
    </b-navbar>
  </div>
</template>

<script>
export default {
  name: "Header", // Component의 이름 설정
}
</script>

<style scoped>
.navbar {
  margin-bottom :50px;
}
</style>