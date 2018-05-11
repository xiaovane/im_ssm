/**
 * @add:各个页面需要的个性化js
 * @author:whai
 * @version:1.0
 */

// ====================begin userManage============================



// 用户好友列表的初始化数据
$(function(){	
	
	 // 判断是群还是用户
	var isGroup=getUrlParam("isGroup");
	var param=getUrlParam("userId");
	if (isGroup=="1") {
		param=getUrlParam("groupId");
	}
	 // 判断是否加载黑名单还是好友列表，0是好友列表，1是黑名单
	  var friendStatus=0;
	  if(getUrlParam("status")==1){
		  friendStatus=1;
	  }
	  // 获取datagrid的实例
		$("#dg").datagrid({  
		    url:url,  
		    pageSize:10,  
		    queryParams: {  
		    	"userId":param,
		 	    "groupId":param,
		 	    "blackStatus":friendStatus,
		 	    "isGroup":isGroup,
		    },  
		   
		});  
})