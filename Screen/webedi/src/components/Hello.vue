<template>
  <div class="hello">
    <h1>{{ username }}</h1>
    <h2>Welcome success!</h2>
    
  </div>
</template>

<script>
export default {
  name: 'hello',
  data () {
    return {
      username: this.$store.state.username
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>
