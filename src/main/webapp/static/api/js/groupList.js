/**
 * @add:各个页面需要的个性化js
 * @author:whai
 * @version:1.0
 */

// ====================begin userManage============================
$(function(){
	//初始化
	dgGroupInfoLoad("../group/groupInfo.do","","");
	
})

function dgGroupInfoLoad(url,groupId,groupNickName){	
	// 获取datagrid的实例	
	$("#dg").datagrid({  
	    url:url,  
	    pageSize:10,  
	    queryParams: {  
	        "groupId":groupId,
	 	    "groupNickName":groupNickName,
	    },  
	   
	});
}
// 自定义选项列表操作
function formatOperation(val, row) {
	var content = "<option >&nbsp;</option>";
	content += "<option value=\"userFriend.jsp?groupId=" + row.groupId
			+ "&status=0&isGroup=1\" >群组成员</option>";
	content += "<option value=\"userFriend.jsp?groupId=" + row.groupId
			+ "&status=1&isGroup=1\"> 群组黑名单</option>";
	content += "<option value=\"userSendMsg.jsp?userId=" + row.groupId
			+ "\">发消息</option>";
	var html = "<select id=\"opr\" class=\"icon-item\" onchange=\"oprFunc(this,'group')\">"
			+ content + "</select>";

	return html;

}

function searchGroupInfo() {
	
	dgGroupInfoLoad("../group/groupInfo.do",$("#m_groupId").val(),$("#m_nickName").val());
}


