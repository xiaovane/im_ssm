/**
 * @add:各个页面需要的个性化js
 * @author:whai
 * @version:1.0
 */

// ====================begin userManage============================


$(function(){	
	   // 判断是查看还是修改
	   var param=getUrlParam("param");
	   // 查看,则不能修改，没有提交按钮
	   if(param==0){	  
		   $("#btnReply").hide();
		   $("#replycontent").attr("readonly","readonly");
	    }
	   
	   // 请求获取相关信息
		$.post("../feedBack/feedBackContent.do",{
		    "infoId":getUrlParam("infoId"),
	　　　　}, function (data) {
	　　			　$("#nickName").val(data.data.nickName);
	　　			　$("#type").val(data.data.feedBackType);
	　　			　$("#time").val(data.data.feedBackTime);
	　　			　$("#feedBackContent").val(data.data.content);
	　　			　$("#replycontent").val(data.data.replyContent);
	　　　　}, 'json');
		
		
		//对提交反馈的内容进行回复
		$("#btnReply").click(function(){
			$.post("../feedBack/feedBackReply.do",{
			    "infoId":getUrlParam("infoId"),
			    "replyContent":$("#replycontent").val()
		　　　　}, function (data) {
		　　			　　if (data.success) {
						$.messager.alert("系统提示", "回复成功！");				
					} else {
						$.messager.alert("系统提示", "回复失败！");
					}
		　　　　}, 'json');
			
		});
	
})


　　
