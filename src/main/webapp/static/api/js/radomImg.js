/**
 * @add:各个页面需要的个性化js
 * @author:whai
 * @version:1.0
 */

// ====================begin feedBackManage============================
$(function(){
	//初始化
	dgFeedBackInfoLoad("../infoList/radomImg.do","");
	
})

function dgFeedBackInfoLoad(url,nickName){	
	// 获取datagrid的实例   
	$("#dg").datagrid({  
	    url:url,  
	    pageSize:10,  
	    queryParams: {  
	    	 "nickName":nickName,
	    },  
	   
	});
}

// 自定义选项列表操作
function formatOperation(val, row) {
	var content = "<option ></option>";
	content += "<option value=\"radomImgModify.jsp?guid=" + row.guid
			+ "&param=1\" >修改信息</option>";
	
	var html = "<select id=\"opr\" class=\"icon-item\"   onchange=\"oprFunc(this,'infoList')\">"
			+ content + "</select>";

	return html;

}



function searchFeedBack() {
	dgFeedBackInfoLoad("../infoList/radomImg.do",$("#m_nickName").val());
}

