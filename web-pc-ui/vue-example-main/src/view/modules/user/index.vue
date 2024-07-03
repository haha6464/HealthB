<template>
  <div id="app">
     
    <div>
      <Top></Top>
    </div>
    <div  class="custom-col">
      <el-row :gutter="10">
        <el-col style="width:19%"><div class="grid-content bg-purple">
          <div>
            <Left></Left>
          </div>

          
        </div></el-col>
        <el-col   class="custom-col" style="height: 100vh;width:78%">
          <div>
              <div class="grid-content bg-purple">    
                <div style="font-size: 14px; margin-top:10px">
                    <span> <span style="color: #C0C0C0;;margin-left:10px">首页</span> / example</span>
                    
                </div>
                <div style="background-color:white;margin-top:20px;margin-left:10px;height:80vh">
                    1
                </div>
              </div>
          </div>
        </el-col>
   
      </el-row>
    </div>
  </div>
</template>

<script>
import { add, getUserInfoById } from '@/api/user.js';
import UserInfo from './component/UserInfo.vue';
import UserProfile from './component/UserProfile.vue';
import Top from '../../main-top.vue';
import Left from '../../main-left.vue';

export default {
  name: 'App',
  components: {
    UserInfo,
    UserProfile,
    Top,
    Left,
  },
  data() {
    return {
      openTemplate: 0,
      user: {
        username: 'root',
        password: 'admin',
      },
    };
  },
  methods: {
    handleChildEvent(dataFromChild) {
      // Log data received from the child component
      console.log(dataFromChild);
    },
    async AddHandle() {
      try {
        const res = await this.addUserInfoService();
        console.log(res);
      } catch (error) {
        console.error('Error adding user info:', error);
      }
    },
    async addUserInfoService() {
      try {
        return await add(this.user);
      } catch (error) {
        console.error('API call failed:', error);
        throw error;
      }
    },
    async FindByIdHandle() {
      const params = {
        id: 1,
      };
      try {
        const res = await getUserInfoById(params);
        console.log(res, 'findById');
      } catch (error) {
        console.error('Error finding user by ID:', error);
      }
    },
  },
};
</script>
 
<style scoped>
.full-screen {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100vh;
  background-color: #F0F0F0;
}

.custom-col {
  background-color: #F0F0F0;
}
</style>