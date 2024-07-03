<template>
  <div id="app">
    <div>
      <Top></Top>
    </div>
    <div class="custom-col">
      <el-row :gutter="10">
        <el-col style="width:19%"
          ><div class="grid-content bg-purple">
            <div>
              <Left :value="4"></Left>
            </div></div
        ></el-col>
        <el-col class="custom-col" style="height: 100%;width:78%">
          <div>
            <div class="grid-content bg-purple">
              <div style="font-size: 14px; margin-top:10px">
                <span>
                  <span style="color: #C0C0C0;;margin-left:10px">首页</span> /
                  医院管理</span
                >
              </div>
              <div
                style="background-color:white;margin-top:20px;margin-left:10px;height:80vh"
              >
                <div style="margin-top: 20px;">
                  <br />
                  <span
                    style="font-size:14px;float:left;margin-top:6px;margin-left:10px"
                    >医院昵称&nbsp;</span
                  >
                  <el-input
                    placeholder="请输入订单号/姓名"
                    style="float: left;width:150px;margin-top:4px"
                    class="custom-input"
                  ></el-input>

                  <span
                    style="font-size:16px;float:left;margin-top:2px;margin-left:15px;margin-top:3px"
                    >服务&nbsp;</span
                  >

                  <div style="float: left">
                    <el-select
                      placeholder="请选择"
                      class="custom-select"
                      style="margin-top:3px"
                    >
                      <el-option
                        v-for="item in serviceList"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value"
                      >
                      </el-option>
                    </el-select>
                  </div>

                  <div style="float:left;margin-left:20px;margin-top:1px">
                    <Button value="搜索"></Button>
                  </div>
                  <div
                    style="float:left;margin-left:90px;"
                    @click="showChildModal"
                  >
                    <Button value="增新"></Button>
                  </div>
                </div>

                <List></List>

                <!--编辑修改-->

                <Edtion ref="dictItemModal"></Edtion>
              </div>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import { add, getUserInfoById } from "@/api/user.js";

import Top from "../../main-top.vue";
import Left from "../../main-left.vue";
import List from "../hospital/component/List.vue";
import Button from "../../../components/button/Button.vue";
import Edtion from "../hospital/component/Edtion.vue";

export default {
  name: "App",
  components: {
    Top,
    Left,
    List,
    Button,
    Edtion,
  },
  data() {
    return {
      serviceList: [
        {
          value: "-1",
          label: "---全部---",
        },
        {
          value: "0",
          label: "全程服务",
        },
        {
          value: "1",
          label: "VIP服务",
        },
        {
          value: "2",
          label: "普通陪诊",
        },
      ],

      openTemplate: 0,
      user: {
        username: "root",
        password: "admin",
      },
    };
  },
  methods: {
    //模糊框的开关
    showChildModal() {
      this.$refs.dictItemModal.dialogFormVisible = !this.$refs.dictItemModal
        .dialogFormVisible;
    },

    handleChildEvent(dataFromChild) {
      // Log data received from the child component
      console.log(dataFromChild);
    },
    async AddHandle() {
      try {
        const res = await this.addUserInfoService();
        console.log(res);
      } catch (error) {
        console.error("Error adding user info:", error);
      }
    },
    async addUserInfoService() {
      try {
        return await add(this.user);
      } catch (error) {
        console.error("API call failed:", error);
        throw error;
      }
    },
    async FindByIdHandle() {
      const params = {
        id: 1,
      };
      try {
        const res = await getUserInfoById(params);
        console.log(res, "findById");
      } catch (error) {
        console.error("Error finding user by ID:", error);
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
  background-color: #f0f0f0;
}

.custom-col {
  background-color: #f0f0f0;
}
</style>

<style>
.custom-input .el-input__inner {
  height: 25px;
  line-height: 20px;
  padding: 0 8px;
}

.styled-input {
  position: absolute;
  left: 368px;
  top: 149px;
  width: 195px;
  height: 20px;
  border-radius: 3px;
  background-color: rgba(255, 255, 255, 1);
  color: rgba(0, 82, 217, 1);
  font-size: 10px;
  text-align: left;
  font-family: Roboto, sans-serif;
  border: 1px solid rgba(220, 220, 220, 1);
  padding: 5px;
}

.custom-option {
  height: 25px !important; /* 设置高度为25px，使用!important确保覆盖可能存在的默认样式 */
  margin-top: 4px; /* 保留你原来设置的上边距 */
}
</style>

<style>
.custom-select .el-input__inner {
  height: 25px !important;
  width: 120px;
  padding-right: 30px !important; /* 确保有足够的空间给箭头 */
}

.custom-select .el-input__suffix {
  display: flex;
  align-items: center;
}

.custom-select .el-input__suffix-inner .el-icon-arrow-down {
  margin-top: 3px; /* 调整这个值使箭头向下移动 */
}
</style>
