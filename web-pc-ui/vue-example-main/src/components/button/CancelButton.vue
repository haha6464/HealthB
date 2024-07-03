<template>
    <div class="hello">
        <button class="custom-button">{{value}}</button>
    </div>
  </template>
  

<script>
    export default {
 

        name: 'App',

        data() {
            return {
        
            }
        },  

        mounted() {
            console.log("用户的简历")
            //this.$emit('childEvent', "你好我给我的父组件值");
        },

        methods: {

        },

        props: {
            value: {
                type: String,
                required: true
            }
        }
    }
</script>

<style>
.custom-button {
 
 
    width: 80px;
    height: 27px;
    line-height: 20px;
    border-radius: 4px;
    background-color: rgb(255, 255, 255);
    color: rgba(153,153,153,1);
    font-size: 14px;
    text-align: center;
    font-family: 'Roboto', sans-serif;
    border: 1px solid rgba(187,187,187,1);
    cursor: pointer; /* 添加这行 */
}
</style>