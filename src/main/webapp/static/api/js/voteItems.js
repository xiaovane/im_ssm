/**
 * @add:各个页面需要的个性化js
 * @author:whai
 * @version:1.0
 */

// ====================begin feedBackManage============================
$(function(){
	// 初始化
	dgVoteLoad("../voteInfo/voteItem.do");

})

// 管理页面列表加载
function dgVoteLoad(url){	
	// 获取datagrid的实例    
	$("#dg").datagrid({  
	    url:url,  
	    pageSize:10,  
	    queryParams: {  
	    	 "voteId":getUrlParam("voteId"),
	    },  
	   
	});
}

// 自定义选项列表操作
function formatOperation(val, row) {
	return "<a class=\"icon-item\" onclick=\"voteItemOpr('"+row.voteId+"','"+row.choiceId+"','"+row.id+"','保存','../voteInfo/ItemSave.do')\">&nbsp;&nbsp;&nbsp;&nbsp;</a>"
}

//行内编辑
function formatchoiceValue(val, row) {
	return "<input type=\"text\" value=\""+row.choiceValue+"\" id=\""+row.id+"\"></input>"
}

function voteItemOpr(item,item2,item3, msg, api) {
	$.messager.confirm("系统提示", "<font>您确定要" + msg + "么？</font>",
			
			function(r) {
				// 确定操作
				if (r) {
					// 发送ajax请求
					$.post(api, {
						"voteId" : item,
						"choiceId" : item2,
						"choiceValue" : $("#"+item3+"").val()
					}, function(result) {
						if (result.success) {
							$.messager.alert("系统提示", "" + msg + "成功！");
							$("#dg").datagrid("reload");
						} else {
							$.messager.alert("系统提示", "" + msg + "失败！");
						}
					}, "json");
				}
			});
}


//新vote选项
function voteItemAdd() {
	window.parent.openTab("投票选项", "voteItemAdd.jsp",
	"icon-vote");
}

