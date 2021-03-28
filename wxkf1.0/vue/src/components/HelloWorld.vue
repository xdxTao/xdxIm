<template>
	<div class="main">	
		<div class="main_up" v-if="curKfId == ''">
			<div v-if="curKfName == ''">
				<el-button @click="openDialog('登录')">登录</el-button>
			</div>
			<div v-else>
				{{this.curKfName}}
				<el-button type="info" @click="loginOut">退出登录</el-button>
			</div>
		</div>
		<div class="main_down" v-else>
			<div class="left">
				<div class="left_up">
					<div :class="curStatus == 1 ? 'label_title label_title_active' : 'label_title'" @click="getSessionList(1)">
						当前会话
					</div>
					<div :class="curStatus == 0 ? 'label_title label_title_active' : 'label_title'" @click="getSessionList(0)">
						历史会话
					</div>
				</div>
				<div class="left_down">
					<div :class="curUserId == item.userId ? 'box_select' : 'box'" v-for="item in sessionList" :key="item.id">
						<div class="box_left"  @click="startSession(item.userId, item.unReadCount = 0)">
							{{item.listName}}
						</div>
						<div class="right_left">
							<div class="right_left_count">
								{{item.unReadCount > 0 ? item.unReadCount : ""}}
							</div>
							<div class="right_left_del">
								<i class="el-icon-close" @click="delSession(item.userId)"></i>
							</div>
						</div>
					</div>
				</div>

			</div>
			<div class="right">
				<div class="up" ref="element" id="msg_end">
					<div v-for="(item,i) in list" :key="i" :class="item.source === 1 ? 'msg_right' : 'msg_left'">
						<div class="msg_right_up">
							{{item.source === 1 ? item.kfName : item.userName}} {{item.createTime}}
						</div>
						<div :class="item.source === 1 ? 'msg_right_down' : 'msg_left_down'">
							{{item.content}}
						</div>
					</div>
					
				</div>
				<div class="down">
					<el-input
					type="textarea"
					:rows="9"
					placeholder="请输入内容，回车发送！"
					@keyup.enter.native = "sendMsg"
					v-model="textarea">
					</el-input>
				</div>
			</div>
		</div>

		<el-dialog
			:title="dialogTitle"
			:visible.sync="dialogVisible"
			width="30%"
			>
			<el-input v-model="loginName" placeholder="请输入用户名..."></el-input>
			<span slot="footer" class="dialog-footer">
				<el-button @click="dialogVisible = false">取 消</el-button>
				<el-button type="primary" @click="login">确 定</el-button>
			</span>
		</el-dialog>
  </div>
</template>

<script>
import axios from 'axios';
export default {
	
	name: 'HelloWorld',
	data(){
		return{
			dialogVisible: false,
			dialogTitle: '',
			loginName: '',
			textarea: "",
			list: [],
			curKfId: '',
			curKfName: "",
			curUserId: '',
			curUserName:'',
			sessionList:[],
			curStatus: 1
		}
	},
	created() { // 页面创建生命周期函数              
		
	},     
	updated(){
		// 解决每次发消息到最后面
		var elmnt = document.getElementById("msg_end");
		if(elmnt != null)
			elmnt.scrollTop = elmnt.scrollHeight;
	},   
	destroyed: function () { // 离开页面生命周期函数              
		this.websocketclose();        
	},
	methods: {            
		initWebSocket: function (kfId,userId) {                
			// WebSocket与普通的请求所用协议有所不同，ws等同于http，wss等同于https                
			this.websock = new WebSocket("ws://127.0.0.1:1997/websocket/"+kfId+"/"+userId);                
			this.websock.onopen = this.websocketonopen;                
			this.websock.onerror = this.websocketonerror;                
			this.websock.onmessage = this.websocketonmessage;                
			this.websock.onclose = this.websocketclose;
		},              
		websocketonopen: function () {                
			console.log("WebSocket连接成功");    
		},              
		websocketonerror: function (e) { 
			console.log("WebSocket连接发生错误",e);              
		},              
		websocketonmessage: function (e) {  
			let data = JSON.parse(e.data);
			if(data.status == 1){
				// 消息数据
				this.list.push(data.data)
			}else{
				this.curStatus = 1
				// 列表数据
				this.sessionList = data.data
			}
		},              
		websocketclose: function (e) {
			if(this.curKfId != null && this.curKfId != ''){
				if(this.curUserId != null){
					this.initWebSocket(this.curKfId, this.curUserId)
				}else{
					this.initWebSocket(this.curKfId, 99999999)
				}
			}
			console.log("connection closed",e);     
			console.log(e);              
		},
		// 消息发送
		sendMsg(){
			if(this.curStatus == 0){
				return this.$message.error("历史会话不可以发消息!");
			}
			if(this.curUserId == '' || this,this.curUserId == '99999999'){
				return this.$message.error("请选择左边的对话框开始聊天!")
			}
			let data = {
				"kfId": this.curKfId,
				"kfName": this.curKfName,
				"content": this.textarea,
				"userName": this.curUserName,
				"userId": this.curUserId,
				"createTime": this.getCurDate(),
				"source": 1
			}
			this.list.push(data)
			this.textarea = ''
			// 1、可以使用webSocket发送消息
			// this.websock.send(this.textarea)
			// 2、可以使用接口发送消息
			let thus = this
			axios.post('http://127.0.0.1:1997/msg/send',data)
			.then(function (response) {
				console.log(response.data);
				if(response.data.code == -1){
					return thus.$message.error(response.data.errDesc);
				}
			})
			.catch(function (error) {
				console.log(error);
			});
			
		},
		openDialog(openName){
			this.dialogTitle = openName
			this.dialogVisible = true
		},
		// 登录
		login(){
			let thus = this
			if(this.dialogTitle == '登录'){
				axios.get('http://127.0.0.1:1997/user/login?kfName=' + this.loginName)
				.then(function (response) {
					console.log(response.data);
					if(response.data.code == -1){
						return thus.$message.error(response.data.errDesc);
					}
					thus.curKfId = response.data.data.id
					thus.curKfName = response.data.data.kfName
					thus.dialogVisible = false
					thus.getSessionList(1)
					thus.startSession(99999999)
				})
				.catch(function (error) {
					console.log(error);
				});
			}
		},
		// 获取会话列表
		getSessionList(status){
			this.curStatus = status
			let thus = this
			axios.get('http://127.0.0.1:1997/session/list?kfId=' + this.curKfId+"&status="+this.curStatus)
			.then(function (response) {
				if(response.data.code == -1){
					return thus.$message.error(response.data.errDesc);
				}
				thus.sessionList = response.data.data
				thus.startSession(99999999)
			})
			.catch(function (error) {
				console.log(error);
			});
		},
		// 创建会话
		createSession(toUserId, toUserName){
			let thus = this
			axios.get('http://127.0.0.1:1997/createSession?id=' + this.curKfId + "&toUserId=" + toUserId + "&toUserName=" + toUserName)
			.then(function (response) {
				if(response.data.code == -1){
					return thus.$message.error(response.data.errDesc);
				}
			})
			.catch(function (error) {
				console.log(error);
			});
		},
		// 开始会话
		startSession(userId){
			console.log(this.websock);
			if(this.websock != undefined){
				this.websock.close()
			}
			this.curUserId = userId
			this.initWebSocket(this.curKfId, userId)
			this.msgList()
		},
		// 删除会话
		delSession(userId){
			let thus = this
			axios.get('http://127.0.0.1:1997/session/close?userId=' + userId)
			.then(function (response) {
				if(response.data.code == -1){
					return thus.$message.error(response.data.errDesc);
				}
				thus.getSessionList(thus.curStatus)
			})
			.catch(function (error) {
				console.log(error);
			});
		},
		// 退出登录
		loginOut(){
			let thus = this
			axios.get('http:////127.0.0.1:1997/loginOut?name=' + this.curKfName)
			.then(function (response) {
				if(response.data.code == -1){
					return thus.$message.error(response.data.errDesc);
				}
				thus.curKfId = ''
				thus.curKfName = ''
				return thus.$message.success("退出登录成功");
			})
			.catch(function (error) {
				console.log(error);
			});
		},
		// 获取消息数据
		msgList(){
			let thus = this
			axios.get('http://127.0.0.1:1997/msg/list?openId=' + this.curUserId)
			.then(function (response) {
				if(response.data.code == -1){
					return thus.$message.error(response.data.errDesc);
				}
				thus.list = response.data.data
				// 从新获取列表
			})
			.catch(function (error) {
				console.log(error);
			});
		},
		getCurDate(){
			let date = new Date();
			let yy = date.getFullYear();
			let mm = date.getMonth() + 1 < 10 ? '0'+date.getMonth() : date.getMonth();
			let dd = date.getDate()
			let hh = date.getHours()
			let mf = date.getMinutes() < 10 ? '0'+date.getMinutes() : date.getMinutes()
			let ss = date.getSeconds() < 10 ? '0'+date.getSeconds() : date.getSeconds()
			
			console.log(yy + '-'+ mm + '-'+dd + ' ' + hh + ':' + mf + ':' + ss );
			return yy + '-'+ mm + '-'+dd + ' ' + hh + ':' + mf + ':' + ss 
		}
	}    
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
	.main{
		width: 980px;
		/* border: 1px #1890ff solid; */
		margin-top: 100px;
		height: 790px;
	}
	.main_up{
		width: 980px;
		/* height: 100px; */
		/* border:1px red solid; */
		text-align: center;
		margin-top: 50px;
	}
	.main_down{
		width: 981px;
		height: 750px;
		border: 1px #1890ff solid;
		display: flex;
		justify-self: space-between;
	}
	.left{
		width: 300px;
		height: 750px;
		border-right: 1px #1890ff solid;
	}
	.left_up{
		width: 300px;
		height: 42px;
		/* overflow-y: auto; */
		display: flex;
		justify-content: space-between;
		/* border: 1px red solid; */
	}
	.label_title{
		width: 135px;
		height: 25px;
		text-align: center;
		/* background-color: #f8f8f8; */
		font-weight: 600;
		padding: 8px;
	}
	.label_title:hover{
		background-color: gainsboro;
		cursor: pointer;
	}
	.label_title_active{
		background-color: gainsboro;
	}

	.left_down{
		width: 300px;
		height: 705px;
		overflow-y: auto;
		/* border: 1px green solid; */
	}
	.right{
		width: 680px;
		height: 750px;
		/* border-right: 1px #1890ff solid; */
	}
	.box{
		width: 250px;
		height: 22px;
		padding: 10px 25px 10px 25px;
		display: flex;
		justify-self: flex-end;
		/* border: 1px red solid; */
	}
	.box:hover{
		background-color: gainsboro;
		cursor: pointer;
	}
	.box_select{
		width: 250px;
		height: 22px;
		padding: 10px 25px 10px 25px;
		display: flex;
		justify-self: flex-end;
		background-color: gainsboro;
	}
	.box_left{
		width: 230px;
		height: 22px;
	}
	.right_left{
		width: 50px;
		height: 22px;
		display: flex;
		justify-content: space-between;
		/* border: 1px red solid; */
	}
	.right_left_count{
		/* border: 1px blue solid; */
		padding-left: 10px;
		width: 20px;
	}
	.right_left_del{
		width: 20px;
		padding-left: 10px;
	}
	.up{
		width: 680px;
		height: 550px;
		overflow-y: scroll;
		overflow-x: hidden;
		/* padding-bottom: 40px; */
		border-bottom: 1px #1890ff solid;
	}
	.msg_left{
		width: 675px;
		/* padding-left: 5px; */
		margin-top: 5px;
		/* border: 1px #1890ff solid; */
	}
	.msg_left_up{
		height: 25px;
		margin-top: 5px;
	}
	.msg_left_down{
		height: 25px;
		/* border: 1px #1890ff solid; */
		padding-left: 10px;
	}
	.msg_right{
		width: 660px;
		/* padding-right: 20px; */
		margin-top: 5px;
		/* border: 1px #1890ff solid; */
		text-align: right;
	}
	.msg_right_up{
		height: 25px;
		
	}
	.msg_right_down{
		height: 25px;
		/* border: 1px #1890ff solid; */
		padding-right: 10px;
	}
	.down{
		width: 680px;
		height: 200px;
		/* border: 1px red solid; */
	}
</style>
