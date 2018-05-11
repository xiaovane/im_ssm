/**
 * @add:各个页面需要的个性化js
 * @author:whai
 * @version:1.0
 */

// ====================begin feedBackManage============================

$(function(){
	// 初始化
	dgAreaInfoLoad("../userAdmin/userAdminList.do","");
	
})

function dgAreaInfoLoad(url,userName){	
	// 获取datagrid的实例
	$("#dg").datagrid({  
	    url:url,  
	    pageSize:10,  
	    queryParams: {  
	    	"userName":userName,
	    },  
	   
	});  
}
// 自定义选项列表操作
function formatOperation(val, row) {
	var content = "<option ></option>";
	content += "<option value=\"" + row.userName
			+ "\" >初始化密码</option>";
	content += "<option value=\"midifyAdmin.jsp?userName=" + row.userName
	+ "&param=1\" >修改账号信息</option>";
	content += "<option value=\"menuList.jsp?userName=" + row.userName
	+ "\" >模块授权</option>";
	content += "<option value=\"" + row.userName
	+ "\" >启用账号</option>";
	content += "<option value=\"" + row.userName
	+ "\" >禁用账号</option>";
	var html = "<select id=\"opr\" class=\"icon-item\"   onchange=\"oprFunc(this,'userAdmin')\">"
			+ content + "</select>";

	return html;

}

// 按照用户id、类型进行查询
function searchAreaInfo() {
	dgAreaInfoLoad("../userAdmin/userAdminList.do",$("#m_loginId").val());
}



