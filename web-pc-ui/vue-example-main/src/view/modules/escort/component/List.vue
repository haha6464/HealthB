<!--
   杨易达
   @email: y51288033@outlook.com
           y51288033@gmail.com
-->
<template>
    <div id="app">
        <div style="padding: 5px;">

            <!-- 前端信息查询(搜索) -->
            <div>
                <span style="font-size:16px;float:left;margin-top:4px;margin-left:10px">模糊搜索&nbsp;</span>
                <el-input v-model="valueForVague" placeholder="请输入订单号/姓名" style="float: left;width:150px;margin-top:4px"
                    class="custom-input"></el-input>

                <!-- 这里对入医院的数据 -->
                <span style="font-size:16px;float:left;margin-top:2px;margin-left:15px;margin-top:4px">性别&nbsp;</span>
                <div style="float: left;">
                    <el-select v-model="valueForAccurate" placeholder="请选择性别" class="custom-select"
                        style="float: left;width:200px;height: 20px;margin-top:4px">
                        <el-option v-for="item in sexList" :key="item.value" :label="item.label" :value="item.value">
                        </el-option>
                    </el-select>
                </div>

                <span style="font-size:16px;float:left;margin-top:2px;margin-left:15px;margin-top:3px">医院&nbsp;</span>
                <div style="float: left;">
                    <el-select v-model="seach.cityId" placeholder="请选择" class="custom-select" style="margin-top:3px"
                        @change="chooseCity">
                        <el-option v-for="item in cityList" :key="item.value" :label="item.label" :value="item.value">
                        </el-option>
                    </el-select>
                </div>

                <span style="font-size:16px;float:left;margin-top:2px;margin-left:15px;margin-top:3px">状态&nbsp;</span>
                <div style="float: left;">
                    <el-select v-model="seach.serviceType" placeholder="请选择" class="custom-select"
                        style="margin-top:3px" @change="chooseService">
                        <el-option v-for="item in serviceList" :key="item.value" :label="item.label"
                            :value="item.value">
                        </el-option>
                    </el-select>
                </div>

                <div style="float: left;margin-left:20px;" @click="seachButton()">
                    <Button value="搜索" style="margin-top:2px"></Button>
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
                            <th>医院</th>
                            <th>陪诊师</th>
                            <th>性别</th>
                            <th>年龄</th>
                            <th>评分</th>
                            <th>联系电话</th>
                            <th>注册时间</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody v-for="(obj, index) in tableData" :key="index">
                        <tr>
                            <td style="width: 30px;">
                                #
                            </td>
                            <td style="width: 70px;">
                                {{ obj.city }}
                            </td>

                            <td>
                                {{ obj.patientescortlist.name }}
                            </td>
                            
                            <td>
                                <span v-if="obj.patientescortlist.gender == 0">
                                    男
                                </span>
                                <span v-else-if="obj.patientescortlist.gender == 1">
                                    女
                                </span>
                            </td>

                            <td>{{ obj.patientEscortName }}</td>
                            <td>{{ obj.income }}</td>
                            <td>{{ obj.patientescortlist.phoneNumber }}</td>

                            <!-- luo -->
                            <td>{{ obj.patientescortlist.createTime }}</td>

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
        <!-- 页脚 -->
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
import { getData } from "@/api/escort.js";
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
            sexList: [
                {
                    value: "1",
                    label: "男",
                },
                {
                    value: "2",
                    label: "女",
                }
            ],

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
                page: 0,
                size: 16,
            },

            valueForVague: "", //模糊查询
            valueForAccurate: "", //精确查询

            //个数
            count: 0,

            tableData: [

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
            getData(this.seach).then(res => {
                console.log(res, "qweqewqw");
                this.tableData = res.data.data.list;
                this.count = res.data.data.count;
            })
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
