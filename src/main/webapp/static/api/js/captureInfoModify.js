/**
 * @add:各个页面需要的个性化js
 * @author:whai
 * @version:1.0
 */

// ====================begin userManage============================


$(function(){	
	  
	
	   // 判断是修改还是新增
	   if (getUrlParam("param")==1) {
		// 请求获取相关信息
			$.post("../captureInfo/captureInfoContent.do",{
			    "infoId":getUrlParam("infoId"),
		　　　　}, function (data) {
					// 列表页
		　　			　$("#listName").val(data.data.urlName);
		　　			　$("#listUrl").val(data.data.urlList);
		　　			　$("#listTitle").val(data.data.title);
		　　			　$("#listContent").val(data.data.content);	　　			
		　　			// 详细页面
		　　			　$("#detailUrl").val(data.data.fromUrl);
		　　			　$("#detailTitle").val(data.data.urlDetail);
		　　			　$("#categoryName").combobox('setValue',data.data.categoryNum);
		　　			　$("#urlCode").combobox('setValue',data.data.urlCode);
		　　			
		　　			
		　　　　}, 'json');
	   }
	   
		
		// 修改信息抓取配置
		$("#btnReply").click(function(){
			var msg="新增抓取配置";
			
			var url="../captureInfo/captureInfoAdd.do";
			if (getUrlParam("param") == 1) {
				msg="修改抓取配置";
				url="../captureInfo/captureInfoModify.do";
			}
			// 判断参数是否为空
			if (($("#listName").val()==null||$("#listName").val()=="")||($("#listUrl").val()==null||$("#listUrl").val()=="")||($("#listTitle").val()==null||$("#listTitle").val()=="")||($("#listContent").val()==null||$("#listContent").val()=="")||($("#detailUrl").val()==null||$("#detailUrl").val()=="")||($("#detailTitle").val()==null||$("#detailTitle").val()=="")) {				
				$.messager.alert("系统提示", "配置字段不能为空！");	
				return;
			}
			
			//新增或者修改
			$.post(url,{
			    "infoId":getUrlParam("infoId"),
			    "urlName":$("#listName").val(),
	　			"urlList":　$("#listUrl").val(),
	　			"title":$("#listTitle").val(),
	　			"content":　$("#listContent").val(),	　　			
	　			"fromUrl":　$("#detailUrl").val(),
	　			"urlDetail":　$("#detailTitle").val(),
	　			"categoryNum":$("#categoryName").combobox('getValue'),
	　			"urlCode":　$("#urlCode").combobox('getValue')
		　　　　}, function (data) {
		　　			　　if (data.success) {
						$.messager.alert("系统提示", ""+msg+"成功！");				
					}
		　　			else {
						$.messager.alert("系统提示", ""+msg+"失败!");
					}
		　　　　}, 'json');
			
		});
	
})


　　
