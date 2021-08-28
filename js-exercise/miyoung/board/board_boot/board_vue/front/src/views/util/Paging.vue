<template>
  <ul class="paging">
    <li v-for="(page, idx) in totalPage" :key="idx" @click="movePage">{{ page }}</li>
  </ul>
</template>

<script>
export default {
  props: ["totalpage"],
  data(){
    return {};
  },
  methods:{
    movePage(page) {
      this.$emit("movePage" , page);
    }
  }
};
</script>

<style scoped>

</style>