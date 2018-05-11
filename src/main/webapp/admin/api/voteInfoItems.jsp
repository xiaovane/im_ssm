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
<script type="text/javascript" src="../../static/api/js/voteItems.js"></script>
<link rel="stylesheet" href="../../static/api/css/common.css">
<link rel="stylesheet" href="../../static/api/css/special.css">
</head>

<body>
	<table id="dg" title="投票管理" class="easyui-datagrid" fitColumns="true"
		pagination="true" toolbar="#tb" striped="true"
		data-options="selected:true,iconCls:'icon-vote'">
		<thead>
			<tr>
				<th field="cb" checkbox="true" align="center"></th>
				<th field="choiceId" width="100" align="center">选项id</th>
				<th field="choiceValue" width="80" align="center"
					formatter="formatchoiceValue">选项值</th>
				<th field="operate" width="120" align="center"
					formatter="formatOperation">保存</th>
			</tr>
		</thead>
	</table>
	<div id="tb">
		<a href="javascript:voteItemAdd()" class="easyui-linkbutton"
			iconCls="icon-add" plain="true">新增</a> <a
			href="javascript:reload()" class="easyui-linkbutton"
			iconCls="icon-reload" plain="true">刷新</a>
	</div>
</body>
</html>
