/**
 * @add:各个页面需要的个性化js
 * @author:whai
 * @version:1.0
 */

// ====================begin feedBackManage============================
$(function(){
	//初始化
	dgFeedBackInfoLoad("../feedBack/feedBackList.do","",0);
	
})

function dgFeedBackInfoLoad(url,nickName,type){	
	// 获取datagrid的实例
	$("#dg").datagrid({  
	    url:url,  
	    pageSize:10,  
	    queryParams: {  
	    	 "nickName":nickName,
	 	    "type":type,
	    },  
	   
	});  
}

// 自定义选项列表操作
function formatOperation(val, row) {
	var content = "<option ></option>";
	content += "<option value=\"feedBackModify.jsp?infoId=" + row.infoId
			+ "&param=1\" >回复用户反馈</option>";
	
	var html = "<select id=\"opr\" class=\"icon-item\"   onchange=\"oprFunc(this,'feedBack')\">"
			+ content + "</select>";

	return html;

}



function searchFeedBack() {
	dgFeedBackInfoLoad("../feedBack/feedBackList.do",$("#m_nickName").val(),$("#m_type").combobox('getValue'));
}

