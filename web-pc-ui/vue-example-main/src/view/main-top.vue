<!--
   杨易达
   @email: y51288033@outlook.com
           y51288033@gmail.com
-->
<template>
    <div id="app">
        <el-row :gutter="20">
            <el-col :span="4">
                <div class="grid-content bg-purple">
              <div class="my-pink-text" style="width: 233px">
                陪诊后台

              </div>
    
              
            </div></el-col>
            
            <el-col :span="16">
                <div class="grid-content bg-purple"></div>
            </el-col>

            <el-col :span="4" style="width: 80%;float:right">
                <div class="grid-content bg-purple" style="display: flex; justify-content: flex-end; width: 90%;">
                    <i class="el-icon-search" style="margin-top:15px"> </i>  
                    <i class="el-icon-bell" style="margin-left: 20px;margin-top:15px"></i>  
                    <div class="block" style="margin-left: 18px;margin-top:13px">
                        <el-avatar shape="square" :size="20" :src="squareUrl"></el-avatar>
                    </div>
                    <span style="margin-top:13px;margin-left:10px">用户名</span>
                    
                </div>
                
            </el-col>
          </el-row>
    </div>
  </template>
  
<script>
    export default {
 

        name: 'App',

        data() {
            return {
                squareUrl: "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png",
            }
        },  

        mounted() {
            console.log("用户的简历")
            this.$emit('childEvent', "你好我给我的父组件值");
        },

        methods: {

 
        }
    }
</script>
    

<style scoped>
    .my-pink-text {
        color: #FF2E6D ;
        text-align: center;
        display: flex;
        justify-content: center;
        align-items: center;
        font-size: 28px; /* 可根据需要调整字体大小 */
        height: 56px;
    }
</style>
  