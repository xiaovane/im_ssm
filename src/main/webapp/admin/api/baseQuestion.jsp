<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../common/head.jspf"%>
<!-- 添加需要的js和css -->
<script type="text/javascript" src="../../static/api/js/common.js"></script>
<script type="text/javascript" src="../../static/api/js/baseQuestionList.js"></script>
<link rel="stylesheet" href="../../static/api/css/common.css">
<link rel="stylesheet" href="../../static/api/css/special.css">
</head>

<body>
	<table id="dg" title="常见问题" class="easyui-datagrid" fitColumns="true"
		pagination="true" toolbar="#tb" striped="true"
		data-options="selected:true,iconCls:'icon-baseQuestion'">
		<thead>
			<tr>
				<th field="cb" checkbox="true" align="center"></th>
				<th field="title" width="100" align="center">标题</th>
				<th field="infoListType" width="80" align="center">类型</th>
				<th field="operate" width="120" align="center"
					formatter="formatOperation">操作</th>
			</tr>
		</thead>
	</table>
	<div id="tb">
		<div>
			<span class="tb-span">&nbsp;标题&nbsp;<input type="text"
				id="m_nickName" class="easyui-textbox"
				onkeydown="if(event.keyCode==13) searchFeedBack()"
				placeholder="标题"></span> <span class="tb-span">&nbsp;反馈类型&nbsp;<select
				type="text" id="m_type" class="easyui-combobox" style="width:158px;height:26px;">
					<option value="1">投诉列表</option>
					<option value="2">常见问题</option>
					<option value="3">保密条款</option>
			</select>
			</span> <a href="javascript:searchFeedBack()" class="easyui-linkbutton"
				iconCls="icon-search" plain="true">搜索</a> <a
				href="javascript:reload()" class="easyui-linkbutton"
				iconCls="icon-reload" plain="true">刷新</a>
				<a
				href="javascript:window.parent.openTab('新增常见问题','baseQuestionModify.jsp?param=0','icon-writeWebUser')"
				class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
				 <a
				href="javascript:commonDelete('../infoList/baseQuestionDelete.do')"
				class="easyui-linkbutton" iconCls="icon-cancel" plain="true">删除</a>
		</div>
	</div>
</body>
</html>
