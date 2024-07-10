<template>
    <div class="hello">
        <button class="button-style">{{value}}</button>
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
    .button-style {
        position: absolute;
        width: 80px;
        height: 27px;
        line-height: 27px;
        border-radius: 10px 0px 10px 0px;
        background: linear-gradient(180deg, rgba(184,134,248,0.8) 0%, rgba(255,46,109,1) 100%);
        color: rgba(255,255,255,1);
        font-size: 14px;
        text-align: center;
        font-family: Roboto, sans-serif;
        cursor: pointer;
        border: none;
        outline: none;
    }

    .button-style:hover {
        opacity: 0.9;
    }
</style>