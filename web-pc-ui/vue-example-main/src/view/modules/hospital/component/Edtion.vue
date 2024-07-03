<!--
   杨易达
   @email: y51288033@outlook.com
           y51288033@gmail.com
-->
<template>
  <div id="app">
    <el-dialog
      title="收货地址"
      :visible.sync="dialogFormVisible"
      :show-close="false"
      custom-class="dialog-with-custom-header"
    >
      <template v-slot:title>
        <div class="custom-title">新增/编辑</div>
      </template>

      <el-form ref="form" label-width="80px">
        <el-form-item label="医院昵称">
          <el-input placeholder="请输入医院昵称"></el-input>
        </el-form-item>

        <el-form-item label="城市">
          <el-select placeholder="请选择" style="width:100%">
            <el-option label="区域一" value="shanghai"></el-option>
            <el-option label="区域二" value="beijing"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="医院地址">
          <el-input placeholder="请输入医院地址"></el-input>
        </el-form-item>

        <el-form-item label="医院标签">
          <el-select
            style="width: 100%;"
            v-model="value"
            multiple
            filterable
            allow-create
            default-first-option
            placeholder="请选择文章标签"
          >
            <el-option
              v-for="item in hospitalLabel"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="医院简介">
          <el-input placeholder="请输入医院简介"></el-input>
        </el-form-item>

        <template>
          <el-radio-group v-model="hospital.radio" style="margin-left: 80px;">
            <el-radio :label="0" class="custom-radio">正常</el-radio>
            <el-radio :label="1" class="custom-radio">停用</el-radio>
          </el-radio-group>
        </template>
        <div

          style="width: 91%; display: flex; justify-content: center;margin-top:40px"
        >
          <div>
            <div style="float:left" @click="dialogFormVisible = false">
              <CancelButton value="取消"></CancelButton>
            </div>
            <div style="float:left;margin-left:90px">
              <Button value="确定"></Button>
            </div>
          </div>
        </div>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import Button from "../../../../components/button/Button.vue";
import CancelButton from "../../../../components/button/CancelButton.vue";

export default {
  props: ["userinfo"],

  name: "App",

  components: {
    Button,
    CancelButton,
  },

  data() {
    return {
      asd: "",
      dialogFormVisible: false,

      hospital: {
        radio: 0,
      },

      value: [],

      hospitalLabel: [
        {
          value: "HTML",
          label: "HTML",
        },
        {
          value: "CSS",
          label: "CSS",
        },
        {
          value: "JavaScript",
          label: "JavaScript",
        },
      ],

      formLabelWidth: "120px",
    };
  },

  mounted() {
    console.log("UserInfo component mounted!");
    console.log("userinfo prop:", this.userinfo);
  },

  methods: {},
};
</script>

<style>
.dialog-with-custom-header .el-dialog__header {
  background-color: rgb(229, 228, 228) !important;
  border-color: rgba(245, 245, 245, 1) !important;
}

.custom-title {
  color: #333; /* Optional: Adjust text color */
  padding: 3px; /* Optional: Adjust padding */
}
</style>

<style>
.custom-radio .el-radio__input.is-checked .el-radio__inner {
  background-color: rgba(255, 46, 109, 1) !important; /* 粉色背景 */
  border-color: rgba(255, 46, 109, 1) !important; /* 粉色边框 */
  position: relative; /* 相对定位 */
}
</style>

<style>
.custom-el-button {
  height: 29px;
  line-height: 29px; /* Ensure text is vertically centered */
  color: grey; /* Set text color to grey */
  display: inline-flex;
  align-items: center; /* Vertically center the text */
  justify-content: center; /* Horizontally center the text */
  font-size: 12px;
}
</style>
