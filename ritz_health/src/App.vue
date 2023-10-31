<template>
<!--
  <router-view></router-view>
-->
  <!--hehe。。。-->
 <!--<router-link to="main.html"></router-link>-->
<button @click="goMain()"><img src="./logo/1.png"/>></button>

</template>

<script>


export default {
  //name: 'App',
  components: {
    //HelloWorld
  },
  data(){
    return{

    }
  },methods:{
    goMain(){
      window.open('main.html');
    }
  },
  created(){
   // alert("好");
    //window.open('main.html');
    //window.location.replace("main.html");

  }
}
</script>

<style>

</style>
