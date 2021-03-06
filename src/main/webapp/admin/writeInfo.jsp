<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>写页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="./common/head.jspf"%>

<script type="text/javascript" charset="utf-8"
	src="${WebUser}/static/ueditor1_4_3_3/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${WebUser}/static/ueditor1_4_3_3/ueditor.all.min.js">
	
</script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8"
	src="${WebUser}/static/ueditor1_4_3_3/lang/zh-cn/zh-cn.js"></script>

</head>

<body style="margin: 10px; font-family: microsoft yahei">

	<div id="p" class="easyui-panel" title="编写" style="padding: 10px;">
		
		<table cellspacing="20px">
			<tr>
				<td width="80px">标题：</td>
				<td><input type="text" id="title" name="title" style="width:400px" /></td>
			</tr>
			<tr>
				<td>所属类别：</td>
				<td><select id="InfoTypeId" class="easyui-combobox"
					name="InfoType.id" style="width:154px" editable="false"
					panelHeight="auto">
						<option value="">请选择类别...</option>
						<c:forEach items="${InfoTypeList }" var="InfoType">
							<option value="${InfoType.id}">${InfoType.typeName}</option>
						</c:forEach>
				</select></td>
				<td></td>
			</tr>
			<tr>
				<td valign="top">内容：</td>
				<td><script id="editor" name="content" type="text/plain"
						style="width:95%; height:200px;"></script></td>
			</tr>
			<tr>
				<td>关键字：</td>
				<td><input type="text" id="keyWord" name="keyWord"
					style="width:400px" />&nbsp;&nbsp;&nbsp;多个关键字的话请用空格隔开</td>
			</tr>
			<tr>
				<td></td>
				<td><a href="javascript:submitData()" class="easyui-linkbutton"
					data-options="iconCls:'icon-submit'">发布</a></td>
			</tr>
		</table>
	</div>




	<!-- 实例化编辑器 -->
	<script type="text/javascript">
		var ue = UE.getEditor('editor');
	</script>
	<script type="text/javascript">
        /**
		 * 发布
          */
		function submitData() {
		    //获取标题
			var title = $("#title").val();
            //获取类别id
			var InfoTypeId = $("#InfoTypeId").combobox("getValue");
            //获取内容 带标记
			var content = UE.getEditor('editor').getContent();
            //截取前155字符 作为简介
			var summary = UE.getEditor('editor').getContentTxt().substr(0, 155);
			//关键词
			var keyWord = $("#keyWord").val();
			//获取内容  不带标签 纯文本
			var contentNoTag = UE.getEditor('editor').getContentTxt();
			//校验
			if (title == null || title == '') {
				$.messager.alert("系统提示", "请输入标题！");
			} else if (InfoTypeId == null || InfoTypeId == '') {
				$.messager.alert("系统提示", "请选择类型！");
			} else if (content == null || content == '') {
				$.messager.alert("系统提示", "请编辑内容！");
			} else {
			    //ajax请求 请求后台写接口
				$.post("${WebUser}/admin/WebUser/save.do",
						{
							'title' : title,
							'InfoType.id' : InfoTypeId,
							'content' : content,
							'summary' : summary,
							'keyWord' : keyWord,
							'contentNoTag' : contentNoTag
						}, function(result) {
							if (result.success) {
								$.messager.alert("系统提示", "发布成功！");
								clearValues();
							} else {
								$.messager.alert("系统提示", "发布失败！");
							}
						}, "json");
			}
		}
		//清空功能
		function clearValues() {
			$("#title").val("");
			$("#InfoTypeId").combobox("setValue", "");
			UE.getEditor("editor").setContent("");
			$("#keyWord").val("");
		}
	</script>
</body>
</html>
