/**
 * @add:各个页面需要的个性化js
 * @author:whai
 * @version:1.0
 */

// ====================begin feedBackManage============================
$(function(){
	// 初始化
	dgVoteLoad("../voteInfo/voteInfoList.do","");
	
})

// 管理页面列表加载
function dgVoteLoad(url,title){	
	// 获取datagrid的实例
	$("#dg").datagrid({  
	    url:url,  
	    pageSize:10,  
	    queryParams: {  
	    	 "voteType":getUrlParam("param"),
	 	    "title":title,
	    },  
	   
	});
}

// 自定义选项列表操作
function formatOperation(val, row) {
	var content = "<option ></option>";
	content += "<option value=\"voteInfoModify.jsp?voteId=" + row.voteId
			+ "&param=1\" >修改投票</option>";
	content += "<option value=\"voteInfoItems.jsp?voteId=" + row.voteId
			+ "&param=1\" >投票选项</option>";
	content += "<option value=\"voteInfoStatics.jsp?voteId=" + row.voteId
			+ "&param=1\" >投票统计</option>";
	var html = "<select id=\"opr\" class=\"icon-item\"   onchange=\"oprFunc(this,'voteInfo')\">"
			+ content + "</select>";

	return html;

}

// 按照投票标题进行查询
function searchVoteInfo() {
	dgVoteLoad("../voteInfo/voteInfoList.do",$("#v_title").val());
}

