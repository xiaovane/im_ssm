/**
 * @add:各个页面需要的个性化js
 * @author:whai
 * @version:1.0
 */

// ====================begin feedBackManage============================
$(function() {
	// 初始化
	dgLoad("../userAdmin/memuList.do");

})

// 保存勾选行
function selectMenus() {
	var selRows = $('#dg').datagrid('getChecked');
	if (selRows.length==0) {
		$.messager.alert("系统提示", "请至少选择一个选项");
		return;
	}
	var msg="";
	for (var i = 0; i < selRows.length; i++) {
		msg+=selRows[i].menuNum+",";
	}
	
	$.post("../userAdmin/addMenu.do", {
		"userName":getUrlParam("userName"),
		"menu":msg
		
	}, function(result) {
		if (result.success) {
			$.messager.alert("系统提示", "授权成功");
			
		}
		else {
			$.messager.alert("系统提示", "授权失败");
		}
		

	}, "json")
	
	
}
