/**
 * @add:各个页面需要的个性化js
 * @author:whai
 * @version:1.0
 */

// ====================begin userManage============================


$(function(){	
	   // 信息初始化
		$.post("../voteInfo/voteInfoDetail.do",{
		    "voteId":getUrlParam("voteId"),
	　　　　}, function (data) {
				// 列表页
	　　			　$("#groupName").val(data.data.groupNickName);
	　　			　$("#createName").val(data.data.nickName);
	　　			　$("#title").val(data.data.title);
	　　			　$("#startTime").datebox("setValue", ""+data.data.startTime+"");	　　			
	　　			  $("#endTime").datebox("setValue", ""+data.data.endTime+"");
	　　　　}, 'json');
		
		
		// 提交
		$("#btnSubmit").click(function(){
			
			// 标题验证
			if ($("#title").val()==""||$("#title").val()==null) {
				$.messager.alert("系统提示", "标题不能为空！");
				return;
			}
			// 日期验证
			if( $("#endTime").datebox("getValue")< $("#startTime").datebox("getValue")){
				$.messager.alert("系统提示", "开始时间不能大于结束时间！");
				return;
			}
			
			$.post("../voteInfo/voteInfoModify.do",{
			    "voteId":getUrlParam("voteId"),
			    "title":$("#title").val(),
			    "startTime":$("#startTime").datebox("getValue"),
			    "endTime":$("#endTime").datebox("getValue")
		　　　　}, function (data) {
		　　			　　if (data.success) {
						$.messager.alert("系统提示", "修改投票成功！");				
					} else {
						$.messager.alert("系统提示", "修改投票失败！");
					}
		　　　　}, 'json');
			
		});
	
})


　　
