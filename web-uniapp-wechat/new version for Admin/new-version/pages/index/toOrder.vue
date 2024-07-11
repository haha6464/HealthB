<template>
	<view class="content">
		 <div style="height: 100vh;background-color: #FAFAFA;width:100%">
			<div style="background-color: #F5F5F5;width:100%;height:80px;margin-top:2px;margin-top:30px">
		

				<div style="float: left;margin-left:5%;margin-top:2px">
					<image src="../../static/index/title.jpg" style="height: 58px;width:60px"></image>
				</div>
				<div  style="float:left;margin-left:20px">
					<div >
						<h1 style="font-size: 18px;">全天陪诊</h1>
					</div>
					<div style="font-size:10px;color:#999999">
						<p style="margin-top: 7px;">全程陪同，手续代办</p>
					</div>
				</div>
			</div>


			<div style="background-color: #F5F5F5;width:100%;height:200px;margin-top:2px;margin-top:35px">
				<h1 style="font-size: 20px;">全天陪诊</h1>
				<div style="margin-top: 9px;">
					<div style="float: left;font-size:16px;color:#999999;margin-left:5%">就诊医院</div>
					<div style="float: right;font-size:18px;margin-right:5%">北京牛逼医院</div>
				</div>
				<div style="clear: both;margin-top:9px">
					<div style="float: left;font-size:16px;color:#999999;margin-left:5%">就诊人</div>
					<div style="float: right;font-size:18px;margin-right:5%">
						<span>杨先生</span>
						<span>&nbsp;&nbsp;</span>
						<span>10086110000</span>
					</div>
				</div>

				<div style="clear: both;margin-top: 9px;">
					<div style="float: left;font-size:16px;color:#999999;margin-left:5%">科室</div>
					<div style="float: right;font-size:18px;margin-right:5%">呼吸外科</div>
				</div>

				<div style="clear: both;margin-top: 9px;">
					<div style="float: left;font-size:16px;color:#999999;margin-left:5%">陪诊时间</div>
					<div style="float: right;font-size:18px;margin-right:5%">2025-05-12 08:00-11:30</div>
				</div>


				<div style="clear: both;margin-top: 9px;">
					<div style="float: left;font-size:18px;margin-left:5%">服务要求</div>
				</div>
				<div style="clear: both;margin-top: 3px;">
					<div style="float: left;font-size:16px;color:#999999;margin-left:5%">给我好好的看好好的陪病人，给我好好的
						别给我搞事，别给我制造麻烦
					</div>
				</div>
			</div>

			<div style="background-color: #F5F5F5;width:100%;height:130px;margin-top:2px;margin-top:45px">
				<h1 style="font-size: 20px;">订单类型</h1>

				<div style="margin-top: 2px;">
					<div style="float: left;font-size:16px;color:#999999;margin-left:5%">订单编号</div>
					<div style="float: right;font-size:18px;margin-right:5%">NR6923161298387192</div>
				</div>

				<div style="clear:both;margin-top: 2px;">
					<div style="float: left;font-size:16px;color:#999999;margin-left:5%">下单时间</div>
					<div style="float: right;font-size:18px;margin-right:5%">2025-02-22</div>
				</div>
			</div>

			<div style="background-color: #F5F5F5; margin-top: 15px; position: fixed; bottom: 0; width: 100%;height:9%">
				<div style="width: 90%;margin-top:5px">
					<div style="margin-left:20px;float:left">
						<span style="font-size: 13px;">本单收益:</span> 
						<span style="color: rgba(255,46,109,1);font-size:18px"> ¥</span> 
						<span style="color: rgba(255,46,109,1);font-size:18px;margin-left:0.1px"> 10086.11</span> 
					</div>
					<div style="float: right;">
						<button class="buttonTrueok"  >接单</button> 
					</div>
				</div>
			</div>
			
		 </div>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				title: 'Hello'
			}
		},
		onLoad() {

		},
		methods: {

		}
	}
</script>

<style>
.buttonTrueok{
 
	width: 70px;
	height: 30px;
	line-height: 30px;
	border-radius: 10px 0px 10px 0px;
	background: linear-gradient(180deg, rgba(184,134,248,0.8) 0%,rgba(255,46,109,1) 100%);
	color: rgba(255,255,255,1);
	font-size: 12px;
	text-align: center;
	font-family: Roboto;
	}
	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}

	.logo {
		height: 200rpx;
		width: 200rpx;
		margin-top: 200rpx;
		margin-left: auto;
		margin-right: auto;
		margin-bottom: 50rpx;
	}

	.text-area {
		display: flex;
		justify-content: center;
	}

	.title {
		font-size: 36rpx;
		color: #8f8f94;
	}
</style>
