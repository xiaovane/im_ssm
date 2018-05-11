<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../common/head.jspf"%>
<script type="text/javascript" charset="utf-8"
	src="../../static/ueditor1_4_3_3/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="../../static/ueditor1_4_3_3/ueditor.all.min.js"></script>
<!-- 添加需要的js和css -->
<script type="text/javascript" src="../../static/api/js/common.js"></script>
<script type="text/javascript"
	src="../../static/api/js/newsInfoModify.js"></script>
<link rel="stylesheet" href="../../static/api/css/common.css">
<link rel="stylesheet" href="../../static/api/css/special.css">
</head>

<body>

	<div class="easyui-panel" title="新闻管理"
		data-options="selected:true,iconCls:'icon-news'">
		<div class="news-div">
			<span class="news-span">标题:</span> <input type="text"
				class="news-input-title" id="c_title"></input>
		</div>
		<div class="news-div-left">
			<span class="news-span">栏目:</span> <select class="easyui-combobox"
				style="width: 158px; height: 30px;" id="c_categoryNum">
				<option value='001'>法律法规</option>
				<option value='002'>新时代</option>
				<option value='003'>物业</option>
				<option value='004'>新闻</option>
				<option value='005'>财经</option>
				<option value='006'>科技</option>
				<option value='007'>汽车</option>
				<option value='008'>军事</option>
				<option value='009'>社会</option>
				<option value='010'>旅游</option>
				<option value='011'>女性</option>
				<option value='012'>教育</option>
				<option value='013'>健康</option>
				<option value='014'>育儿</option>
				<option value='015'>历史</option>
				<option value='016'>国际</option>
				<option value='017'>健身</option>
				<option value='018'>政务</option>
				<option value='019'>房产</option>
				<option value='020'>家居</option>
				<option value='021'>生活</option>
				<option value='022'>美食</option>
				<option value='023'>宠物</option>
				<option value='024'>亲子</option>
			</select>
		</div>

		<div class="news-div-left">
			<span class="news-span">日期:</span> <input id="c_infoDate" type="text"
				class="easyui-datebox" required="required">
		</div>
		<div class="clearDiv"></div>
		<div class="news-div-left">
			<span class="news-span">转载:</span> <input type="text"
				class="news-input" id="c_zhuanzai"></input>
		</div>
		<div class="news-div-left">
			<span class="news-span">作者:</span> <input type="text"
				class="news-input" id="c_author"></input>
		</div>
		<div class="news-div">
			<script id="content" name="content" type="text/plain"
				style="width:95%; height:200px;"></script>
			<input type="hidden" id="ed" name="content">
			<%-- UEditor不能作为表单的一部分提交，所以用这种隐藏域的方式 --%>
		</div>
		<div class="captureInfo-div">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-submit'"
				id="submitData">确定</a>
		</div>
		<script type="text/javascript">		
		$(function() {

			//页面数据初始化
			var ue = UE.getEditor('content');
			//判断是新增还是修改
			if (getUrlParam("param") == 1) {				
				ue.addListener('ready', function() {
					UE.ajax.request(
							'${WebUser}/admin/captureInfo/newsInfoContent.do',
							{
								method : "post",
								async : false,
								data : {
									"infoGuid" : getUrlParam("infoGuid")
								},
								onsuccess : function(result) {
									result = JSON.parse(result.responseText);
					                $("#c_title").val(result.data.title);
					                $("#c_categoryNum").combobox('setValue',result.data.categoryNum);
					                $("#c_infoDate").datebox("setValue",result.data.infoDate);
					                $("#c_zhuanzai").val(result.data.zhuanZai);
					                $("#c_author").val(result.data.author);
									UE.getEditor('content').setContent(
											result.data.content);
								}
							})
				})
			}

			//修改、新增提交
			$("#submitData").click(function(){
				var msg="信息新增";
				var url="../captureInfo/newsInfoAdd.do";
				if (getUrlParam("param") == 1) {
					msg="信息修改";
					url="../captureInfo/newsInfoModify.do";
				}
				// 判断参数是否为空
				if ($("#c_title").val()==null||$("#c_title").val()=="") {				
					$.messager.alert("系统提示", "标题不能为空！");	
					return;
				}
				
				if($("#c_infoDate").datebox("getValue")==null||$("#c_infoDate").datebox("getValue")=="")
					{				
					$.messager.alert("系统提示", "日期不能为空！");	
					return;
				}
				
				$.post(url,{
				    "infoGuid":getUrlParam("infoGuid"),				    
		　			"categoryNum":$("#c_categoryNum").combobox('getValue'),
		 			"infoDate":$("#c_infoDate").datebox("getValue"),
		 			"title":$("#c_title").val(),
		 			"zhuanZai": $("#c_zhuanzai").val(),
		 			"author": $("#c_author").val(),
		　			"content":　UE.getEditor("content").getContent()
			　　　　}, function (data) {
			　　		　if (data.success) {
							$.messager.alert("系统提示", ""+msg+"成功！");				
						} else {
							$.messager.alert("系统提示", ""+msg+"失败!");
						}
			　　　　}, 'json');
			})
		})
			
		</script>
</body>
</html>
