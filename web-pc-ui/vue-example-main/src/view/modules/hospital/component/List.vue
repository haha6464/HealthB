<!--
   杨易达
   @email: y51288033@outlook.com
           y51288033@gmail.com
-->
<template>
  <div id="app">
    <div style="padding: 5px;">
      <div style="clear: both;">
        <br />
      </div>

      <div style="height: 500px;">
        <table style="width: 100%;">
          <thead>
            <tr style="font-weight: bold;">
              <th>序号</th>
              <th>城市</th>
              <th>医院</th>
              <th>医院地址</th>
              <th>医院简介</th>
              <th>标签</th>
              <th>状态</th>
              <th>操作</th>
            </tr>
          </thead>

          <!-- 这里需要tableData 获取数据 -->
          <tbody v-for="(obj, index) in tableData" :key="index">
            <tr>
              <td style="width: 30px;">
                {{ (seach.page - 1) * 16 + index + 1 }}
              </td>
              <td style="width: 70px;">
                {{ obj.city }}
              </td>
              <td>
                {{ obj.name }}
              </td>
              <td>
                {{ obj.address }}
              </td>
              <td>{{ obj.hospitalIntroduction }}</td>

              <td>
                <span v-for="o in obj.label" :key="o.labelName">
                  {{ o.labelName }}
                </span>
              </td>

              <td>
                <span v-if="obj.status == 0">
                  正常
                </span>
                <span v-else-if="obj.status == 1">
                  停用
                </span>
              </td>
              <td>
                <el-link type="primary" style="font-size:12px" @click="switchStatus(obj)"><span
                    v-if="obj.status == 0">停用</span> <span v-else>启动</span> </el-link>
                <el-link type="primary" style="font-size:16px">|</el-link>
                <el-link type="primary" style="font-size:12px" @click="edit(obj)">编辑</el-link>
              </td>
              
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <div style="margin-top:10px">
      <div style="color:rgb(152, 151, 151);font-size:13px;margin-top:15px;margin-left:14px;float:left;">
        共{{ count }}条数据
      </div>

      <div style="margin-top:5px;float:right">
        <div>
          <el-pagination background layout="prev, pager, next" :total="count" :page-size="seach.size"
            @current-change="getListDate" class="custom-pagination">
          </el-pagination>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getData } from "@/api/order.js";
import { getCityList } from "@/api/city.js";
import Button from "../../../../components/button/Button.vue";
import { getHospitalList, findByOne } from "@/api/hospital.js";

export default {
  props: ["userinfo"],

  name: "App",

  components: {
    Button,
  },

  data() {
    return {
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

      serviceList: [
        {
          value: "-1",
          label: "---全部---",
        },
        {
          value: "0",
          label: "正常",
        },
        {
          value: "1",
          label: "停用",
        },
      ],

      //查询
      seach: {
        status: null,
        name: "",
        page: 1,
        size: 16,
      },

      valueForVague: "", //模糊查询
      valueForAccurate: "", //精确查询

      //个数
      count: 0,

      tableData: [
        {
          id: 0,
          city: "",
          name: "",
          address: "",
          hospitalIntroduction: "",
          label: [],
          status: 0,
        },
      ],
    };
  },

  mounted() {
    console.log("UserInfo component mounted!");
    console.log("userinfo prop:", this.userinfo);
    this.getListDate(1);
  },

  methods: {
    async edit(ob1j) {
      let app = this.$parent.$parent.$parent;
      let obj = 0;
      await findByOne({ "id": ob1j.id }).then(res => {
        console.log(res.data.list[0])
        obj = res.data.list[0];
      })

      app.$refs.dictItemModal.hospitalBo.hospital = {
        id: obj.id,
        name: obj.name,
        cityId: obj.cityId,
        address: obj.address,
        hospitalIntroduction: obj.hospitalIntroduction,
        status: obj.status,
        delFlag: obj.delFlag,
      }
      app.$refs.dictItemModal.hospitalBo.labelList = [];
      for (let i = 0; i < obj.label.length; i++) {
        app.$refs.dictItemModal.hospitalBo.labelList.push(obj.label[i].labelName)
      }


      app.$refs.dictItemModal.dialogFormVisible = true;
    },

    switchStatus(obj) {
      console.log(obj, "obj")
      let app = this.$parent.$parent.$parent;
      app.$refs.dictItemModal.hospitalBo.hospital = {
        id: obj.id,
        name: null,
        cityId: null,
        address: null,
        hospitalIntroduction: null,
        status: obj.status == 1 ? 0 : 1,
        delFlag: null,
      }
      console.log(app.$refs.dictItemModal.hospitalBo)
      app.$refs.dictItemModal.hospitalBo.labelList = [];

      for (let i = 0; i < obj.label.length; i++) {
        app.$refs.dictItemModal.hospitalBo.labelList.push(obj.label[i].labelName)
      }

      app.$refs.dictItemModal.edtion();
    },

    //服务选择
    chooseService(val) {
      if (val == -1) {
        this.seach.serviceType = null;
        this.getListDate(1);
      }
    },
    reset() { },
    seachButton() {
      this.getListDate(1);
    },

    //获取数据
    getListDate(val) {
      getHospitalList(this.seach).then((res) => {
        this.tableData = res.data.list;
        console.log(this.tableData);
        console.log(this.city);
        this.count = res.data.count;
      });
    },
  },
};
</script>

<style>
table {
  width: 100%;
  border-collapse: collapse;
}

th,
td {
  border: 1px solid rgba(220, 220, 220, 1);
  /* 表头和边框颜色为深灰色 */
  padding: 0;
  /* 移除填充 */
  text-align: left;
  font-size: 10px;
  /* 设置表头和单元格字体大小一致为10px */
  font-weight: normal;
  /* 去掉表头的加粗 */
  height: 10px;
  /* 设置单元格高度为5px */
  line-height: 28px;
  /* 设置行高为5px */
  text-align: center;
}

th {
  color: rgb(152, 151, 151);
  /* 表头文字颜色 */
}

td {
  color: black;
}

td>div {
  display: block;
  height: 5px;
  overflow: hidden;
}

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
  height: 25px !important;
  /* 设置高度为25px，使用!important确保覆盖可能存在的默认样式 */
  margin-top: 4px;
  /* 保留你原来设置的上边距 */
}
</style>

<style>
.custom-select .el-input__inner {
  height: 25px !important;
  width: 120px;
  padding-right: 30px !important;
  /* 确保有足够的空间给箭头 */
}

.custom-select .el-input__suffix {
  display: flex;
  align-items: center;
}

.custom-select .el-input__suffix-inner .el-icon-arrow-down {
  margin-top: 3px;
  /* 调整这个值使箭头向下移动 */
}
</style>

<style>
.el-pagination.is-background .btn-next,
.el-pagination.is-background .btn-prev,
.el-pagination.is-background .el-pager li {
  background-color: #fff !important;
}

.el-pagination.is-background .el-pager li.active {
  color: #fff !important;
  /* 设置选中字体颜色为白色 */
  background-color: rgba(255, 46, 109, 1) !important;
  /* 设置选中背景色为粉色 */
}
</style>
