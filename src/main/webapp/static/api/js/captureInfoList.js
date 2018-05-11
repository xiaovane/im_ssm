/**
 * @add:各个页面需要的个性化js
 * @author:whai
 * @version:1.0
 */

// ====================begin feedBackManage============================
$(function() {
	// 初始化
	dgLoad("../captureInfo/captureInfoList.do");

})
// 自定义选项列表操作
function formatOperation(val, row) {
	var content = "<option ></option>";
	content += "<option value=\"captureInfoModify.jsp?infoId=" + row.infoId
			+ "&param=1\" >修改抓取配置</option>";
	var html = "<select id=\"opr\" class=\"icon-item\"   onchange=\"oprFunc(this,'captureInfo')\">"
			+ content + "</select>";

	return html;

}
