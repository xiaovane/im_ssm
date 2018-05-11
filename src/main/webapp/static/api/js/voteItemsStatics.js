/**
 * @add:各个页面需要的个性化js
 * @author:whai
 * @version:1.0
 */

// ====================begin feedBackManage============================
$(function(){
	// 初始化
	getVoteTitle("../voteInfo/voteInfoDetail.do");
	dgVoteLoad("../voteInfo/voteItemStatics.do");
	

})

// 列表加载
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

//获取vote 标题
function getVoteTitle(url){	
	
	// 获取datagrid的实例
	var grid = $("#dg").datagrid();
　　　$.post(url,{	   
	    "voteId":getUrlParam("voteId"),
　　　}, function (data) {
　			 $("#voteTitle").html(data.data.title);
　　　}, 'json');
}

