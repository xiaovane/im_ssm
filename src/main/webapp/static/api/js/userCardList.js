/**
 * @add:各个页面需要的个性化js
 * @author:whai
 * @version:1.0
 */

// ====================begin feedBackManage============================
$(function(){
	//初始化
	dgLoad("../cardInfo/userCardInfoList.do");
	
})
// 自定义选项列表操作
function formatOperation(val, row) {
	var content = "<option ></option>";
	content += "<option value=\"userCardInfoModify.jsp?cardId=" + row.cardId
			+ "&param=1\" >修改卡券</option>";
	content += "<option value=\"userCardInfoModify.jsp?cardId=" + row.cardId
			+ "&param=1\" >查看卡券</option>";
	var html = "<select id=\"opr\" class=\"icon-item\"   onchange=\"oprFunc(this,'cardInfo')\">"
			+ content + "</select>";

	return html;
}

