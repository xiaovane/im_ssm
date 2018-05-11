/**
 * @后台共用js
 * @author：whai
 * @version 1.0
 */

// ========================================================================
// easyUI刷新
function reload() {
	$("#dg").datagrid("reload");
}

// 获取参数值
function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg); // 匹配目标参数
	if (r != null)
		return unescape(r[2]);
	return null; // 返回参数值
}

// 打开选项卡
function openTab(text, url, icon) {
	// 判断当前选项卡是否存在
	if ($('#tabs').tabs('exists', text)) {
		// 如果存在 显示
		$("#tabs").tabs("select", text);
	} else {
		// 如果不存在 则新建一个
		$("#tabs")
				.tabs(
						'add',
						{
							title : text,
							closable : true, // 是否允许选项卡摺叠。
							iconCls : icon, // 显示图标
							content : "<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='"
									+ url + "'></iframe>"
						// url 远程加载所打开的url
						})
	}
}



// 触发select操作
function oprFunc(item,fun) {
	var options = item.options[item.selectedIndex].value;
	var optionsTxt = item.options[item.selectedIndex].text;
	if (optionsTxt == "删除账户") {
		userOpr(options,2, optionsTxt, "../"+fun+"/userDelete.do");
	} else if (optionsTxt == "禁用账号") {
		userOpr(options,0, optionsTxt, "../"+fun+"/userDeac.do");
	} else if (optionsTxt == "启用账号") {
		userOpr(options,1, optionsTxt, "../"+fun+"/userDeac.do");
	} else if (optionsTxt == "初始化密码") {
		userOpr(options,"11111", optionsTxt, "../"+fun+"/userInitPwd.do");
	}  else {
		window.parent.openTab("" + optionsTxt + "", "" + options + "",
				"icon-writeWebUser");
	}
	
	// 重置选项为第一个
	item.options[0].selected=true;

}

// 删除、禁用、启用等操作
function userOpr(param,status, msg, api) {
	$.messager.confirm("系统提示", "<font>您确定要" + msg + "么？</font>",
			function(r) {
				// 确定操作
				if (r) {
					// 发送ajax请求
					$.post(api, {
						userId : param,
						status:status
					}, function(result) {
						if (result.success) {
							$.messager.alert("系统提示", "" + msg + "成功！");
							$("#dg").datagrid("reload");
						} else {
							$.messager.alert("系统提示", "" + msg + "失败！");
						}
					}, "json");
				}
			});
}

// 管理页面列表加载
function dgLoad(url){	
	$("#dg").datagrid({  
	    url:url,  
	    pageSize:10,  
	   
	});  
}

// 百度编辑器封装请求
function postUE(url,params,callback){
	var ue = UE.getEditor('content');
	ue.addListener('ready', function() {
		UE.ajax.request(url, {
			method : "post",
			async : false,
			data : {params},
			onsuccess : function(result) {
				result = JSON.parse(result.responseText);
				callback(result);
			}
		})
	})
	
}

/**
 * 批量删除通用代码
 */
function commonDelete(url){	
	var selRows = $('#dg').datagrid('getChecked');
	if (selRows.length==0) {
		$.messager.alert("系统提示", "请至少选择一个选项");
		return;
	}
	var msg="";
	for (var i = 0; i < selRows.length; i++) {
		msg+=selRows[i].id+",";
	}
	
	$.post(url, {
		"msg":msg
		
	}, function(result) {
		if (result.success) {
			$.messager.alert("系统提示", "信息删除成功");		
			
		}
		else {
			$.messager.alert("系统提示", "信息删除失败");
		}
		

	}, "json")
}


function loginOut() {
	$.messager.confirm("系统提示", "<font>您确定要退出系统么？</font>",
			function(r) {
				// 确定操作
				if (r) {
					window.open("/ssm/loginOut.jsp");
				}
			});
}