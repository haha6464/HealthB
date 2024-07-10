<template>
  <view class="content">
    <div class="mainbackgroup">
      <div style="float: left; width: 20%; margin-top: 9px">
        <div class="custom-uni-data-select">
          <DateSelect
            v-model="value"
            :clear="false"
            :localdata="range"
            @change="change"
            class="custom-uni-data-select"
          ></DateSelect>
        </div>
      </div>

      <div class="search-container" style="width: 80%; float: left">
        <div class="search-bar-container">
          <uni-search-bar
            class="uni-mt-10 search-input"
            radius="100"
            placeholder="一直不显示"
            clearButton="none"
            cancelButton="none"
            @confirm="search"
            @cancel="cancel"
          />
        </div>
        <div class="fav-container">
          <div class="custom-button">点击我</div>
        </div>
      </div>

	  <div>
		<image src="../../static/index/title.jpg" style="height: 150px;width:90%"></image>
	</div>
    </div>
 
	<uni-section class="mb-1" title="派单信息" titleFontSize="16px" type="line"></uni-section>

	<div>
		<div style="float: left;margin-left:5%">
			<image src="../../static/index/title.jpg" style="height: 90px;width:90px"></image>
		</div>
		<div  style="float:left;margin-left:20px">
			<div >
				<h1 style="font-size: 21px;">全天陪诊</h1>
			</div>
			<div style="font-size:10px;color:#999999">
				<p style="margin-top: 7px;">就诊医院: 北京牛逼医院</p>
 
				<p style="margin-top: 5px;">就诊时间: 2025-05-12 05:00</p>
 
				<p style="margin-top: 5px;">杨先生 100861110086</p>
			</div>
		</div>
		<div style="width: 100%;clear:both;text-align: center;">
			<div style="width: 80%; margin: 0 auto;">
				<hr style="border: 0;height: 1px;background-color: #999999;">
			</div>
		</div>

		<div style="width: 90%;margin-top:5px">
			<div style="margin-left:20px;float:left">
				<span style="font-size: 13px;">本单收益:</span> 
				<span style="color: rgba(255,46,109,1);font-size:16px"> ¥</span> 
				<span style="color: rgba(255,46,109,1);font-size:16px;margin-left:0.1px"> 10086.11</span> 
			</div>
			<div style="float: right;">
				<button class="buttonTrueok"  >接单</button> 
			</div>
		</div>
	</div>

	

	<div style="clear: both;">
		&nbsp;
	</div>

	<div>
		<div style="float: left;margin-left:5%">
			<image src="../../static/index/title.jpg" style="height: 90px;width:90px"></image>
		</div>
		<div  style="float:left;margin-left:20px">
			<div >
				<h1 style="font-size: 21px;">全天陪诊</h1>
			</div>
			<div style="font-size:10px;color:#999999">
				<p style="margin-top: 7px;">就诊医院: 北京牛逼医院</p>
 
				<p style="margin-top: 5px;">就诊时间: 2025-05-12 05:00</p>
 
				<p style="margin-top: 5px;">杨先生 100861110086</p>
			</div>
		</div>
		<div style="width: 100%;clear:both;text-align: center;">
			<div style="width: 80%; margin: 0 auto;">
				<hr style="border: 0;height: 1px;background-color: #999999;">
			</div>
		</div>

		<div style="width: 90%;margin-top:5px">
			<div style="margin-left:20px;float:left">
				<span style="font-size: 13px;">本单收益:</span> 
				<span style="color: rgba(255,46,109,1);font-size:16px"> ¥</span> 
				<span style="color: rgba(255,46,109,1);font-size:16px;margin-left:0.1px"> 10086.11</span> 
			</div>
			<div style="float: right;">
				<button class="buttonTrueok"  >接单</button> 
			</div>
		</div>
	</div>

	
	<div style="clear: both;">
		&nbsp;
	</div>

	<div>
		<div style="float: left;margin-left:5%">
			<image src="../../static/index/title.jpg" style="height: 90px;width:90px"></image>
		</div>
		<div  style="float:left;margin-left:20px">
			<div >
				<h1 style="font-size: 21px;">全天陪诊</h1>
			</div>
			<div style="font-size:10px;color:#999999">
				<p style="margin-top: 7px;">就诊医院: 北京牛逼医院</p>
 
				<p style="margin-top: 5px;">就诊时间: 2025-05-12 05:00</p>
 
				<p style="margin-top: 5px;">杨先生 100861110086</p>
			</div>
		</div>
		<div style="width: 100%;clear:both;text-align: center;">
			<div style="width: 80%; margin: 0 auto;">
				<hr style="border: 0;height: 1px;background-color: #999999;">
			</div>
		</div>

		<div style="width: 90%;margin-top:5px">
			<div style="margin-left:20px;float:left">
				<span style="font-size: 13px;">本单收益:</span> 
				<span style="color: rgba(255,46,109,1);font-size:16px"> ¥</span> 
				<span style="color: rgba(255,46,109,1);font-size:16px;margin-left:0.1px"> 10086.11</span> 
			</div>
			<div style="float: right;">
				<button class="buttonTrueok"  >接单</button> 
			</div>
		</div>
	</div>
	<div style="clear: both;">
		&nbsp;
	</div>

	<div>
		<div style="float: left;margin-left:5%">
			<image src="../../static/index/title.jpg" style="height: 90px;width:90px"></image>
		</div>
		<div  style="float:left;margin-left:20px">
			<div >
				<h1 style="font-size: 21px;">全天陪诊</h1>
			</div>
			<div style="font-size:10px;color:#999999">
				<p style="margin-top: 7px;">就诊医院: 北京牛逼医院</p>
 
				<p style="margin-top: 5px;">就诊时间: 2025-05-12 05:00</p>
 
				<p style="margin-top: 5px;">杨先生 100861110086</p>
			</div>
		</div>
		<div style="width: 100%;clear:both;text-align: center;">
			<div style="width: 80%; margin: 0 auto;">
				<hr style="border: 0;height: 1px;background-color: #999999;">
			</div>
		</div>

		<div style="width: 90%;margin-top:5px">
			<div style="margin-left:20px;float:left">
				<span style="font-size: 13px;">本单收益:</span> 
				<span style="color: rgba(255,46,109,1);font-size:16px"> ¥</span> 
				<span style="color: rgba(255,46,109,1);font-size:16px;margin-left:0.1px"> 10086.11</span> 
			</div>
			<div style="float: right;">
				<button class="buttonTrueok"  >接单</button> 
			</div>
		</div>
	</div>
		<div style="clear: both;">
		&nbsp;
	</div>

	<div>
		<div style="float: left;margin-left:5%">
			<image src="../../static/index/title.jpg" style="height: 90px;width:90px"></image>
		</div>
		<div  style="float:left;margin-left:20px">
			<div >
				<h1 style="font-size: 21px;">全天陪诊</h1>
			</div>
			<div style="font-size:10px;color:#999999">
				<p style="margin-top: 7px;">就诊医院: 北京牛逼医院</p>
 
				<p style="margin-top: 5px;">就诊时间: 2025-05-12 05:00</p>
 
				<p style="margin-top: 5px;">杨先生 100861110086</p>
			</div>
		</div>
		<div style="width: 100%;clear:both;text-align: center;">
			<div style="width: 80%; margin: 0 auto;">
				<hr style="border: 0;height: 1px;background-color: #999999;">
			</div>
		</div>

		<div style="width: 90%;margin-top:5px">
			<div style="margin-left:20px;float:left">
				<span style="font-size: 13px;">本单收益:</span> 
				<span style="color: rgba(255,46,109,1);font-size:16px"> ¥</span> 
				<span style="color: rgba(255,46,109,1);font-size:16px;margin-left:0.1px"> 10086.11</span> 
			</div>
			<div style="float: right;">
				<button class="buttonTrueok"  >接单</button> 
			</div>
		</div>
	</div>

 

 
 
  </view>
</template>

<script>
import DateSelect from "@/components/ui/DataSelect";
import ButtonTrue from "@/components/ui/ButtonTrue";

export default {
  components: {
    DateSelect,
  },
  data() {
    return {
      title: "Hello",
      seachButton: true,
      contentText: {
        contentFav: "搜索",
      },

      value: 1,

      range: [
        { value: 0, text: "北京" },
        { value: 1, text: "上海" },
        { value: 2, text: "南京" },
      ],
    };
  },

  onLoad() {},
  methods: {},
};
</script>

<style scoped>

.custom-uni-data-select .uni-select {
  font-size: 14px;
  box-sizing: border-box;
  border-radius: 4px;
  padding: 0 5px;
  padding-left: 10px;
  position: relative;
  display: flex;
  user-select: none;
  flex-direction: row;
  align-items: center;
  width: 100%;
  flex: 1;
  height: 35px;
}

.custom-uni-data-select .uni-select--disabled {
  background-color: #f5f7fa;
  cursor: not-allowed;
}
</style>

<style>
.buttonTrueok{
 
width: 70px;
height: 25px;
line-height: 20px;
border-radius: 10px 0px 10px 0px;
background: linear-gradient(180deg, rgba(184,134,248,0.8) 0%,rgba(255,46,109,1) 100%);
color: rgba(255,255,255,1);
font-size: 12px;
text-align: center;
font-family: Roboto;
}

.custom-button {
  width: 48px;
  height: 26px;
  line-height: 20px;
  border-radius: 10px;
  background: linear-gradient(
    180deg,
    rgba(184, 134, 248, 0.8) 0%,
    rgba(255, 46, 109, 1) 100%
  );
  color: rgba(255, 255, 255, 1);
  font-size: 14px;
  text-align: center;
  font-family: Roboto;
}

.search-container {
  position: relative;
  display: flex;
  align-items: center;
}

.search-bar-container {
  flex: 1;
}

.fav-container {
  position: absolute;
  right: 25px;
  top: 12px; /* 修正样式，确保按钮在顶部对齐 */
  margin-top: 2px;
}

.fav-icon {
  background-color: red; /* 设置红色背景 */
}

.fav-icon img {
  display: none; /* 隐藏星星图标 */
}

.mainbackgroup {
  left: 0px;
  top: 0px;
  width: 100%;
  height: 226px;
  line-height: 20px;
  background: linear-gradient(
    180deg,
    rgba(255, 46, 109, 1) 0%,
    rgba(184, 134, 248, 0.8) 100%
  );
  color: rgba(16, 16, 16, 1);
  font-size: 14px;
  text-align: center;
  font-family: Roboto;
}
</style>
