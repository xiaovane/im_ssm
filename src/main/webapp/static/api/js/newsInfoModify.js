/**
 * @add:各个页面需要的个性化js
 * @author:whai
 * @version:1.0
 */

// ====================begin userManage============================


$(function(){	
	   // 信息初始化
	 /*
		 * var ue = UE.getEditor('editor'); ue.addListener('ready',function () {
		 * UE.ajax.request('../captureInfo/newsInfoContent.do',{ method: "post",
		 * async: false, data: {"infoGuid":getUrlParam("infoGuid")}, onsuccess:
		 * function(result) { result = JSON.parse(result.responseText);
		 * $("#c_title").val(result.title);
		 * $("#c_categoryNum").combobox('setValue',result.categoryNum);
		 * $("#c_infoDate").datebox("setValue",result.infoDate);
		 * $("#c_zhuanzai").val(result.zhuaiZai);
		 * $("#c_author").val(result.author);
		 * UE.getEditor('editor').setContent(result.content); } }) })
		 
		var ue = UE.getEditor('editor');
	    $.post("../captureInfo/newsInfoContent.do",{
		    "infoGuid":getUrlParam("infoGuid")
		   
	　　　　}, function (data) {　			　
	　　				result = JSON.parse(data.responseText);
	                $("#c_title").val(result.title);
	                $("#c_categoryNum").combobox('setValue',result.categoryNum);
	                $("#c_infoDate").datebox("setValue",result.infoDate);
	                $("#c_zhuanzai").val(result.zhuaiZai);
	                $("#c_author").val(result.author);
	                UE.getEditor('editor').setContent(result.content);				
				
	　　　　}, 'json');*/
		

		// 提交
		$("#btnSubmit").click(function(){
			
			// 标题验证
			if ($("#c_title").val()==""||$("#c_title").val()==null) {
				$.messager.alert("系统提示", "标题不能为空！");
				return;
			}

			$.post("../captureInfo/newsInfoModify.do",{
			    "infoGuid":getUrlParam("infoGuid"),
			    "title":$("#c_title").val(),
			    "categoryNum":$("#c_categoryNum").combobox('getValue'),
			    "infoDate":$("#c_infoDate").datebox("getValue"),
			    "zhuanzai":$("#c_zhuanzai").val(),
			    "author":$("#c_author").val(),
			    "content":UE.getEditor('editor').getContent()
		　　　　}, function (data) {
		　　			　　if (data.success) {
						$.messager.alert("系统提示", "修改信息成功！");				
					} else {
						$.messager.alert("系统提示", "修改信息失败！");
					}
		　　　　}, 'json');
			
		});
		
	
})


　　
