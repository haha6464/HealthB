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
      <div style="margin-left: 10px">服务图片</div>
      <div style="margin-bottom: 20px; margin-left: 80px">
        <el-upload
            class="upload-icon"
            action="https://jsonplaceholder.typicode.com/posts/"
            list-type="picture-card"
            :on-preview="handlePictureCardPreview"
            :on-remove="handleRemove">
          <i class="el-icon-plus" style="margin-top: 50px">
            <p style="font-size: 13px">上传照片</p>
          </i>
        </el-upload>
      </div>
      <el-dialog :visible.sync="dialogVisible">
        <img width="100%" :src="dialogImageUrl" alt="">
      </el-dialog>
      <el-form ref="form" label-width="80px">
        <el-form-item label="服务名称">
          <el-input
            placeholder="请输入"
            v-model="serveBo.serve.serve_item_name"
          ></el-input>
        </el-form-item>
        <div style="margin-left: 12px; margin-bottom: -30px">服务类型</div>
        <el-select v-model="selectServeTypeValue" placeholder="选择服务类型"
                   style="margin-left: 80px; margin-bottom: 20px">
          <el-option
              v-for="item in ServeTypeOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value">
          </el-option>
        </el-select>
        <div style="margin-left: 12px; margin-bottom: -30px">服务医院</div>
        <el-select v-model="selectServeHospitalValue" placeholder="选择服务医院"
        style="margin-left: 80px; margin-bottom: 20px">
          <el-option
              v-for="item in ServeHospitalOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value">
          </el-option>
        </el-select>
        <el-form-item label="服务价格">
          <el-input
            placeholder="请输入"
            v-model="serveBo.serve.serve_price"
          ></el-input>
        </el-form-item>
        <template>
          <span style="margin-left: 10px">上架时间</span>
          <el-radio-group
          v-model="serveBo.serve.serve_on_sell_time"
          style="margin-left: 20px;"
          >
            <el-radio class="custom-radio" v-model="radio" :label="1">立即上架</el-radio>
            <el-radio class="custom-radio" v-model="radio" :label="2">定时上架
              <el-date-picker
                  v-model="value1"
                  type="datetime"
                  placeholder="选择日期时间">
              </el-date-picker>
            </el-radio>

          </el-radio-group>
        </template>
        <br>
        <br>
        <br>
        <template>
          <span style="margin-left: 10px">商品状态</span>
          <el-radio-group
            v-model="serveBo.serve.status"
            style="margin-left: 20px;"
          >
            <el-radio :label="0" class="custom-radio">上架</el-radio>
            <el-radio :label="1" class="custom-radio">下架</el-radio>
          </el-radio-group>
        </template>
        <div
          style="width: 91%; display: flex; justify-content: center;margin-top:40px"
        >
          <div>
            <div style="float:left" @click="dialogFormVisible = false">
              <CancelButton value="取消"></CancelButton>
            </div>
            <div style="float:left;margin-left:90px" @click="edtion">
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
import { add , editHospital} from "@/api/hospital.js";
import { labeladd } from "@/api/hospitalLabel.js";
import { getCityList } from "@/api/city.js";

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

      dialogImageUrl: '',
      dialogVisible: false,

      serveBo: {
        serve: {
          id: null,
          serve_item_icon: "",
          serve_item_name: null,
          serve_item_type: null,
          serve_item_hospital: null,
          serve_price: null,
          serve_on_sell_time: null,
          status: 0,
          delFlag: 0,
        },
      },

      hospital: {
        radio: 0,
      },

      cityList: [
        {
          value: "1",
          label: "南京",
        },
        {
          value: "2",
          label: "天津",
        },
        {
          value: "3",
          label: "上海",
        },
        {
          value: "4",
          label: "日本",
        },
        {
          value: "5",
          label: "宇宙",
        },
      ],

      value: [],

      formLabelWidth: "120px",

      ServeHospitalOptions: [{
        value: '选项1',
        label: '黄金糕'
      }, {
        value: '选项2',
        label: '双皮奶'
      }, {
        value: '选项3',
        label: '蚵仔煎'
      }, {
        value: '选项4',
        label: '龙须面'
      }, {
        value: '选项5',
        label: '北京烤鸭'
      }],
      selectServeHospitalValue: '',

      ServeTypeOptions: [{
        value: '选项1',
        label: '黄金糕'
      }, {
        value: '选项2',
        label: '双皮奶'
      }, {
        value: '选项3',
        label: '蚵仔煎'
      }, {
        value: '选项4',
        label: '龙须面'
      }, {
        value: '选项5',
        label: '北京烤鸭'
      }],
      selectServeTypeValue: '',
      radio: '1'
    };
  },

  mounted() {
    this.getCityList();
    console.log("UserInfo component mounted!");
    console.log("userinfo prop:", this.userinfo);
  },

  methods: {
    // 上传图片
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },

    //获取城市集合
    getCityList() {
      getCityList().then((res) => {
        this.cityList = res.data;
      });
    },

    //操作数据
    async edtion() {
      console.log("直接父组件:", this.$parent.$options.name);
      console.log("间接父组件:", this.$parent.$parent.$options.name);
      console.log("间接父组件:", this.$parent.$parent.$parent.$options.name);
 
      //添加
      if (this.serveBo.serve.id == null) {
        let id = 0;
        await add(this.serveBo.hospital).then((res) => {
          id = res.data.data.id;
        });

        //增加标签
        for (let i = 0; i < this.serveBo.labelList.length; i++) {
          let obj = {
            hospitalId: id,
            labelName: this.serveBo.labelList[i],
          };

          await labeladd(obj).then((res) => {});
        }

        this.dialogFormVisible = false;
        this.$parent.$parent.$parent.reset();
      }else{
        await editHospital(this.serveBo.hospital).then(res=>{
            
        })

        for (let i = 0; i < this.serveBo.labelList.length; i++) {
          let obj = {
            hospitalId: this.serveBo.hospital.id,
            labelName: this.serveBo.labelList[i],
          };

          await labeladd(obj).then((res) => {});
        }


        
        
        this.dialogFormVisible = false;
        this.$parent.$parent.$parent.reset();
      }

      let serveBo = {
        serve: {
          id: null,
          serve_item_icon: "",
          serve_item_name: null,
          serve_item_type: null,
          serve_item_hospital: null,
          serve_price: null,
          serve_on_sell_time: null,
          status: 0,
          delFlag: 0,
        },
      }
    },
  },
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

.el-upload--picture-card {
  background-color: #fbfdff;
  border: 1px dashed #c0ccda;
  border-radius: 6px;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  width: 350px;
  height: 150px;
  cursor: pointer;
  line-height: 146px;
  vertical-align: top;
}
</style>
