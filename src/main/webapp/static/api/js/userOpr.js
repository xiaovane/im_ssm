/**
 * @add:各个页面需要的个性化js
 * @author:whai
 * @version:1.0
 */

// ====================begin userManage============================


$(function(){	
	// 给用户发送消息
	$("#btnSendmsg").click(function(){
		$.post("../user/userSendMsg.do",{
		    "userId":getUrlParam("userId"),
		    "content":$("#msgContent").val(),
		   
	　　　　}, function (data) {
	　　			　if (data.success) {
					$.messager.alert("系统提示", "发送消息成功！");				
				} else {
					$.messager.alert("系统提示", "发送消息失败！");
				}
	　　　　}, 'json');
		
	});
	
	// 重置密码
	$("#btnResetPwd").click(function(){
		// 判断两次输入的密码是否一致
		var pwd=$("#txtPwd").val();
		var pwdAgin=$("#txtPwdAgin").val();
		if(pwd==null||pwd==''){
			$.messager.alert("系统提示", "密码不能为空！");	
			return;
		}
		if(pwd!=pwdAgin){
			$.messager.alert("系统提示", "两次密码输入不一致！");	
			return;
		}
		
		$.post("../user/userResetPwd.do",{
		    "userId":getUrlParam("userId"),
		    "pwd":pwd,
		   
	　　　　}, function (data) {
	　　			　if (data.success) {
					$.messager.alert("系统提示", "密码重置成功！");				
				} else {
					$.messager.alert("系统提示", "密码重置失败！");
				}
	　　　　}, 'json');
		
	});
	
	// 禁用
	$("#btnResetPwd").click(function(){
		// 判断两次输入的密码是否一致
		var pwd=$("#txtPwd").val();
		var pwdAgin=$("#txtPwdAgin").val();
		if(pwd==null||pwd==''){
			$.messager.alert("系统提示", "密码不能为空！");	
			return;
		}
		if(pwd!=pwdAgin){
			$.messager.alert("系统提示", "两次密码输入不一致！");	
			return;
		}
		
		$.post("../user/userResetPwd.do",{
		    "userId":getUrlParam("userId"),
		    "pwd":pwd,
		   
	　　　　}, function (data) {
	　　			　if (data.success) {
					$.messager.alert("系统提示", "密码重置成功！");				
				} else {
					$.messager.alert("系统提示", "密码重置失败！");
				}
	　　　　}, 'json');
		
	});
	
	
})
	

//============小区管理多级联动============

　　
