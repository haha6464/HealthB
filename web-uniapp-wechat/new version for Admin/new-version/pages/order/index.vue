<template>
	<view class="content">
		<div style="height: 100vh;width:100%;background-color: #F5F5F5;">
			<div style=" width:100%;height:30px;margin-top:2px;margin-top:30px">
				<div>
					<span :style="chooseId == 1 ? 'margin-left:8%;color:red' : 'margin-left:8%'"
					 :class="chooseId == 1 ? 'titleText' : ''" @click="choose(1)">全部</span>
					<span 
					:style="chooseId == 2 ? 'margin-left:8%;color:red' : 'margin-left:5%'"
					style="margin-left:5%" :class="chooseId == 2 ? 'titleText' : ''" @click="choose(2)">待服务</span>
					<span
					:style="chooseId == 3 ? 'margin-left:8%;color:red' : 'margin-left:5%'"
					style="margin-left:5%" :class="chooseId == 3 ? 'titleText' : ''" @click="choose(3)">进行中</span>
					<span 
					:style="chooseId == 4 ? 'margin-left:8%;color:red' : 'margin-left:5%'"
					style="margin-left:5%" :class="chooseId == 4 ? 'titleText' : ''" @click="choose(4)">已完成</span>
					<span 
					:style="chooseId == 5 ? 'margin-left:8%;color:red' : 'margin-left:5%'"
					style="margin-left:5%" :class="chooseId == 5 ? 'titleText' : ''" @click="choose(5)">已取消</span>
				</div>
			</div>


			
			
			<!-- List -->
			 <div style="margin-left:2%">
				<div style="margin-top: 5px;">
					<div style="float: left;margin-left:5%">
						<image src="../../static/index/title.jpg" style="height: 90px;width:90px"></image>
					</div>
					<div  style="float:left;margin-left:20px">
						<div >
							<h1 style="font-size: 19px;">全天陪诊</h1>
						</div>
						<div style="font-size:10px;color:#999999">
							<p style="margin-top: 7px;">就诊医院: 北京牛逼医院</p>
			 
							<p style="margin-top: 5px;">就诊时间: 2025-05-12 05:00</p>
			 
							<p style="margin-top: 5px;">杨先生 100861110086</p>
						</div>
					</div>
					<div style="width: 100%;clear:both;text-align: center;">
						<div style="width:75%; margin: 0 auto;">
							<hr style="border: 0;height: 1px;background-color: #999999;">
						</div>
					</div>
			
					<div style="width: 90%;margin-top:5px">
						<div style="margin-left:20px;float:left">
							<span style="font-size: 13px;">本单收益:</span> 
							<span style="color: rgba(255,46,109,1);font-size:14px"> ¥</span> 
							<span style="color: rgba(255,46,109,1);font-size:14px;margin-left:0.1px"> 10086.11 </span> 
						</div>
						<div style="float: right;">
							<button style="float: left;" class="buttonTrueokFalse" @click="toOrder(1)" >联系客服</button> 
							<button style="float: left;" class="buttonTrueok" @click="toOrder(1)" >修改订单</button> 
							<button style="float: left;" class="buttonTrueok" @click="toOrder(1)" >接单</button>
						</div>
					</div>
				</div>

 
				<div style="clear: both;height:2px"> </div>
				<div style="margin-top: 10px;">
					<div style="float: left;margin-left:5%">
						<image src="../../static/index/title.jpg" style="height: 90px;width:90px"></image>
					</div>
					<div  style="float:left;margin-left:20px">
						<div >
							<h1 style="font-size: 19px;">全天陪诊</h1>
						</div>
						<div style="font-size:10px;color:#999999">
							<p style="margin-top: 7px;">就诊医院: 北京牛逼医院</p>
			 
							<p style="margin-top: 5px;">就诊时间: 2025-05-12 05:00</p>
			 
							<p style="margin-top: 5px;">杨先生 100861110086</p>
						</div>
					</div>
					<div style="width: 100%;clear:both;text-align: center;">
						<div style="width:75%; margin: 0 auto;">
							<hr style="border: 0;height: 1px;background-color: #999999;">
						</div>
					</div>
			
					<div style="width: 90%;margin-top:5px">
						<div style="margin-left:20px;float:left">
							<span style="font-size: 13px;">本单收益:</span> 
							<span style="color: rgba(255,46,109,1);font-size:14px"> ¥</span> 
							<span style="color: rgba(255,46,109,1);font-size:14px;margin-left:0.1px"> 10086.11 </span> 
						</div>
						<div style="float: right;">
							<button style="float: left;" class="buttonTrueokFalse" @click="toOrder(1)" >联系客服</button> 
							<button style="float: left;" class="buttonTrueok" @click="toOrder(1)" >修改订单</button> 
							<button style="float: left;" class="buttonTrueok" @click="toOrder(1)" >接单</button>
						</div>
					</div>
				</div>

				<div style="clear: both;height:2px"> </div>
				<div style="margin-top: 10px;">
					<div style="float: left;margin-left:5%">
						<image src="../../static/index/title.jpg" style="height: 90px;width:90px"></image>
					</div>
					<div  style="float:left;margin-left:20px">
						<div >
							<h1 style="font-size: 19px;">全天陪诊</h1>
						</div>
						<div style="font-size:10px;color:#999999">
							<p style="margin-top: 7px;">就诊医院: 北京牛逼医院</p>
			 
							<p style="margin-top: 5px;">就诊时间: 2025-05-12 05:00</p>
			 
							<p style="margin-top: 5px;">杨先生 100861110086</p>
						</div>
					</div>
					<div style="width: 100%;clear:both;text-align: center;">
						<div style="width:75%; margin: 0 auto;">
							<hr style="border: 0;height: 1px;background-color: #999999;">
						</div>
					</div>
			
					<div style="width: 90%;margin-top:5px">
						<div style="margin-left:20px;float:left">
							<span style="font-size: 13px;">本单收益:</span> 
							<span style="color: rgba(255,46,109,1);font-size:14px"> ¥</span> 
							<span style="color: rgba(255,46,109,1);font-size:14px;margin-left:0.1px"> 10086.11 </span> 
						</div>
						<div style="float: right;">
							<button style="float: left;" class="buttonTrueokFalse" @click="toOrder(1)" >联系客服</button> 
							<button style="float: left;" class="buttonTrueok" @click="toOrder(1)" >修改订单</button> 
							<button style="float: left;" class="buttonTrueok" @click="toOrder(1)" >接单</button>
						</div>
					</div>
				</div>


				<div style="clear: both;height:2px"> </div>
				<div style="margin-top: 10px;">
					<div style="float: left;margin-left:5%">
						<image src="../../static/index/title.jpg" style="height: 90px;width:90px"></image>
					</div>
					<div  style="float:left;margin-left:20px">
						<div >
							<h1 style="font-size: 19px;">全天陪诊</h1>
						</div>
						<div style="font-size:10px;color:#999999">
							<p style="margin-top: 7px;">就诊医院: 北京牛逼医院</p>
			 
							<p style="margin-top: 5px;">就诊时间: 2025-05-12 05:00</p>
			 
							<p style="margin-top: 5px;">杨先生 100861110086</p>
						</div>
					</div>
					<div style="width: 100%;clear:both;text-align: center;">
						<div style="width:75%; margin: 0 auto;">
							<hr style="border: 0;height: 1px;background-color: #999999;">
						</div>
					</div>
			
					<div style="width: 90%;margin-top:5px">
						<div style="margin-left:20px;float:left">
							<span style="font-size: 13px;">本单收益:</span> 
							<span style="color: rgba(255,46,109,1);font-size:14px"> ¥</span> 
							<span style="color: rgba(255,46,109,1);font-size:14px;margin-left:0.1px"> 10086.11 </span> 
						</div>
						<div style="float: right;">
							<button style="float: left;" class="buttonTrueokFalse" @click="toOrder(1)" >联系客服</button> 
							<button style="float: left;" class="buttonTrueok" @click="toOrder(1)" >修改订单</button> 
							<button style="float: left;" class="buttonTrueok" @click="toOrder(1)" >接单</button>
						</div>
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
				title: 'Hello',
				chooseId: 1,
			}
		},
		onLoad() {

		},
		methods: {
			choose(id) {
				this.chooseId = id;
				console.log(this.chooseId);
			}
		}
	}
</script>

<style>
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

	.titleText {
		display: inline-block;
		position: relative;
	}
	
	.titleText::after {
		content: '';
		display: block;
		width: 100%;
		height: 2px;
		background-color: #FF2E6D;
		color: #FF2E6D;
		position: absolute;
		bottom: 0;
		left: 0;
	}

	.buttonTrueok{
		width: 60px;
		height: 21px;
		line-height: 20px;
		border-radius: 10px 0px 10px 0px;
		background: linear-gradient(180deg, rgba(184,134,248,0.8) 0%,rgba(255,46,109,1) 100%);
		color: rgba(255,255,255,1);
		font-size: 8px;
		text-align: center;
		font-family: Roboto;
	}

	.buttonTrueokFalse{
		width: 60px;
		height: 21px;
		line-height: 20px;
		color: rgb(0, 0, 0);
		font-size: 8px;
		text-align: center;
		font-family: Roboto;
		}
</style>

