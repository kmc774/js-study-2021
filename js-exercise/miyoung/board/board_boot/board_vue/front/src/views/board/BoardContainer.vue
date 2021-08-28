<template>
  <div>
    <h3>{{ title }} </h3>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'BoardContainer',
  props: {
    title: {
      type: String,
      required:true
    },

  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
</style>
