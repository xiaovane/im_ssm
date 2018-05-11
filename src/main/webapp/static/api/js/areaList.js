/**
 * @add:各个页面需要的个性化js
 * @author:whai
 * @version:1.0
 */

// ====================begin feedBackManage============================

$(function(){
	//初始化
	dgAreaInfoLoad("../position/positionList.do","");
	
})

function dgAreaInfoLoad(url,areaName){	
	// 获取datagrid的实例
	$("#dg").datagrid({  
	    url:url,  
	    pageSize:10,  
	    queryParams: {  
	    	 "xiaoQuName":areaName,
	    },  
	   
	});  
}
// 自定义选项列表操作
function formatOperation(val, row) {
	var content = "<option ></option>";
	content += "<option value=\"areaModify.jsp?xiaoQuId=" + row.xiaoQuId
			+ "&param=1\" >修改小区信息</option>";
	
	var html = "<select id=\"opr\" class=\"icon-item\"   onchange=\"oprFunc(this,'position')\">"
			+ content + "</select>";

	return html;

}

// 按照用户id、类型进行查询
function searchAreaInfo() {
	dgAreaInfoLoad("../position/positionList.do",$("#m_xiaoqu").val());
}

