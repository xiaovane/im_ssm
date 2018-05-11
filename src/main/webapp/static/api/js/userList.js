/**
 * @add:各个页面需要的个性化js
 * @author:whai
 * @version:1.0
 */

// ====================begin userManage============================

$(function(){
	// 初始化
	dgUserInfoLoad("../user/userInfo.do","","","",1);
	
})

function dgUserInfoLoad(url,userId,mobile,nickName,useType){	
	// 获取datagrid的实例
	$("#dg").datagrid({  
	    url:url,  
	    pageSize:10,  
	    queryParams: {  
	    	"userId":userId,
	 	    "mobile":mobile,
	 	    "nickName":nickName,
	 	    "useType":useType,
	    },  
	   
	});
}
// 自定义选项列表操作
function formatOperation(val, row) {
	var content = "<option></option>";
	content += "<option value=\"userFriend.jsp?userId=" + row.userId
			+ "&status=0&isGroup=0\" >用户好友</option>";
	content += "<option value=\"userFriend.jsp?userId=" + row.userId
			+ "&status=1&isGroup=0\"> 黑名单</option>";
	content += "<option value=\"userSendMsg.jsp?userId=" + row.userId
			+ "\">发消息</option>";
	content += "<option value=\"userResetPwd.jsp?userId=" + row.userId
			+ "\">重置密码</option>";
	content += "<option value=\"" + row.userId + "\">禁用账号</option>";
	content += "<option value=\"" + row.userId + "\">启用账号</option>";
	var html = "<select id=\"opr\" class=\"icon-item\"  onchange=\"oprFunc(this,'user')\">"
			+ content + "</select>";
	return html;

}


// 按照标题、手机号、昵称和用户类型进行查询
function searchUserInfo() {
	 var userType=$("#m_useType").combobox('getValue');
	
	dgUserInfoLoad("../user/userInfo.do",$("#m_userId").val(),$("#m_mobile").val(),$("#m_nickName").val(),userType);
}

