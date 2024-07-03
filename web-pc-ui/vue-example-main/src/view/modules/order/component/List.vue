<!--
   杨易达
   @email: y51288033@outlook.com
           y51288033@gmail.com
-->
<template>
  <div id="app">
    <div style="padding: 5px;">
      <div>
        <span style="font-size:16px;float:left;margin-top:4px;margin-left:10px"
          >模糊查询&nbsp;</span
        >
        <el-input
          v-model="valueForVague"
          placeholder="请输入订单号/姓名"
          style="float: left;width:150px;margin-top:4px"
          class="custom-input"
        ></el-input>

        <span
          style="font-size:16px;float:left;margin-top:2px;margin-left:15px;margin-top:4px"
          >陪诊师&nbsp;</span
        >
        <el-input
          v-model="valueForAccurate"
          placeholder="请输入陪诊师姓名/手机号码"
          style="float: left;width:200px;height: 20px;margin-top:4px"
          class="custom-input"
        ></el-input>

        <span
          style="font-size:16px;float:left;margin-top:2px;margin-left:15px;margin-top:3px"
          >城市&nbsp;</span
        >
        <div style="float: left;">
          <el-select
            v-model="seach.cityId"
            placeholder="请选择"
            class="custom-select"
            style="margin-top:3px"
            @change="chooseCity"
          >
            <el-option
              v-for="item in cityList"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </div>

        <span
          style="font-size:16px;float:left;margin-top:2px;margin-left:15px;margin-top:3px"
          >服务&nbsp;</span
        >
        <div style="float: left;">
          <el-select
            v-model="seach.serviceType"
            placeholder="请选择"
            class="custom-select"
            style="margin-top:3px"
            @change="chooseService"
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

        <div style="float: left;margin-left:20px;" @click="seachButton()">
          <Button value="搜索" style="margin-top:2px"></Button>
        </div>

        <div
          style="float: left;margin-left:20px;margin-left:90px"
          @click="reset()"
        >
          <Button value="重制" style="margin-top:1px"></Button>
        </div>
      </div>

      <div style="clear: both;">
        <br />
      </div>

      <div style="height: 500px;">
        <table style="width: 100%;">
          <thead>
            <tr style="font-weight: bold;">
              <th>序号</th>
              <th>城市</th>
              <th>订单号</th>
              <th>服务</th>
              <th>订单金额</th>
              <th>接单人</th>
              <th>接单收益(元)</th>
              <th>联系电话</th>
              <th>订单状态</th>
            </tr>
          </thead>
          <tbody v-for="(obj, index) in tableData" :key="index">
            <tr>
              <td style="width: 30px;">
                {{ (seach.page - 1) * 16 + index + 1 }}
              </td>
              <td style="width: 70px;">
                {{ obj.city }}
              </td>
              <td>
                {{ obj.oderId }}
              </td>
              <td>
                <span v-if="obj.serviceType == 0">
                  全程服务
                </span>
                <span v-else-if="obj.serviceType == 1">
                  VIP服务
                </span>
                <span v-else-if="obj.serviceType == 2">
                  普通服务
                </span>
              </td>
              <td>{{ obj.amount }}</td>
              <td>{{ obj.patientEscortName }}</td>
              <td>{{ obj.income }}</td>
              <td>{{ obj.phoneNumber }}</td>
              <td>
                <span v-if="obj.status == 0">
                  待服务
                </span>
                <span v-else-if="obj.status == 1">
                  进行中
                </span>
                <span v-else-if="obj.status == 2">
                  已完成
                </span>
                <span v-else-if="obj.status == 3">
                  已取消
                </span>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <div style="margin-top:10px">
      <div
        style="color:rgb(152, 151, 151);font-size:13px;margin-top:15px;margin-left:14px;float:left;"
      >
        共{{ count }}条数据
      </div>

      <div style="margin-top:5px;float:right">
        <div>
          <el-pagination
            background
            layout="prev, pager, next"
            :total="count"
            :page-size="seach.size"
            @current-change="getListDate"
            class="custom-pagination"
          >
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

      //查询
      seach: {
        orderIdForVague: "",
        serviceType: null, //服务类型
        cityId: null, //城市id
        patientEscortNameForVague: "", //接单人 陪诊师的姓名 模糊查询使用
        patientEscortName: "", //接单人  精确查询
        phoneNumber: "", //手机号
        page: 0,
        size: 16,
      },

      valueForVague: "", //模糊查询
      valueForAccurate: "", //精确查询

      //个数
      count: 0,

      tableData: [
        {
          id: "1",
          city: "Beijing",
          orderId: "1008611",
          serviceType: 1,
          amount: 10.1,
          patientEscortName: "刘德华", //接单人
          income: 10.1, //陪诊师收益
          phoneNumber: 123456789121, //phone_number
          status: 0, //订单状态
        },
      ],
    };
  },

  mounted() {
    console.log("UserInfo component mounted!");
    console.log("userinfo prop:", this.userinfo);
    this.getListDate(1);
    this.getCityList();
  },

  methods: {
    //城市选择
    chooseCity(val) {
      if (val == -1) {
        this.seach.cityId = null;
        this.getListDate(1);
      }
    },
    //服务选择
    chooseService(val) {
      if (val == -1) {
        this.seach.serviceType = null;
        this.getListDate(1);
      }
    },

    reset() {
      this.seach = {
        orderIdForVague: "",
        serviceType: null, //服务类型
        cityId: null, //城市id
        patientEscortNameForVague: "", //接单人 陪诊师的姓名 模糊查询使用
        patientEscortName: "", //接单人  精确查询
        phoneNumber: "", //手机号
        page: 0,
        size: 16,
      };

      this.valueForVague = "";
      this.valueForAccurate = "";
      this.getListDate(1);
    },

    //获取城市集合
    getCityList() {
      getCityList().then((res) => {
        this.cityList = res.data;
        this.cityList.unshift({ value: -1, label: "---全部---" });
      });
    },

    seachButton() {
      this.getListDate(1);
    },
    //纯字符串
    isNumeric(value) {
      return /^\d+$/.test(value);
    },

    //获取数据
    getListDate(val) {
      if (this.seach.cityId == -1) {
        this.seach.cityId = null;
      }

      if (this.seach.serviceType == -1) {
        this.seach.serviceType = null;
      }

      console.log(this.valueForVague + "??", "valueForVague");
      console.log(this.valueForAccurate + "[[", "valueForAccurate");
      console.log(this.valueForAccurate != "");
      //模糊查询
      if (this.valueForVague != null && this.valueForVague != "") {
        console.log(this.isNumeric(this.valueForVague), "zz");
        //如果都不是数字就是陪诊师姓名查询，否则就是订单号
        if (!this.isNumeric(this.valueForVague)) {
          this.seach.orderIdForVague = null;
          this.seach.patientEscortNameForVague = this.valueForVague;
        } else {
          console.log("??");
          this.seach.patientEscortNameForVague = null;
          this.seach.orderIdForVague = this.valueForVague;
        }
      } else {
        this.seach.orderIdForVague = null;
        this.seach.patientEscortNameForVague = null;
      }

      console.log(this.seach, "this.seach.");

      //精准查询
      if (this.valueForAccurate != null && this.valueForAccurate != "") {
        //如果长度小于5那么就是陪师姓名查询，否则手机号
        if (this.valueForAccurate.length < 5) {
          this.seach.patientEscortName = this.valueForAccurate;
          this.seach.phoneNumber = null;
        } else {
          this.seach.patientEscortName = null;
          this.seach.phoneNumber = this.valueForAccurate;
        }
      } else {
        this.seach.phoneNumber = null;
        this.seach.patientEscortName = null;
      }

      this.seach.page = val;

      getData(this.seach).then((res) => {
        this.tableData = res.data.list;
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
  border: 1px solid rgba(220, 220, 220, 1); /* 表头和边框颜色为深灰色 */
  padding: 0; /* 移除填充 */
  text-align: left;
  font-size: 10px; /* 设置表头和单元格字体大小一致为10px */
  font-weight: normal; /* 去掉表头的加粗 */
  height: 10px; /* 设置单元格高度为5px */
  line-height: 28px; /* 设置行高为5px */
  text-align: center;
}

th {
  color: rgb(152, 151, 151); /* 表头文字颜色 */
}

td {
  color: black;
}

td > div {
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

<style>
.el-pagination.is-background .btn-next,
.el-pagination.is-background .btn-prev,
.el-pagination.is-background .el-pager li {
  background-color: #fff !important;
}

.el-pagination.is-background .el-pager li.active {
  color: #fff !important; /* 设置选中字体颜色为白色 */
  background-color: rgba(255, 46, 109, 1) !important; /* 设置选中背景色为粉色 */
}
</style>
