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
<script type="text/javascript"
	src="../../static/api/js/voteItemsStatics.js"></script>
<link rel="stylesheet" href="../../static/api/css/common.css">
<link rel="stylesheet" href="../../static/api/css/special.css">
</head>

<body>
	<table id="dg" title="投票统计" class="easyui-datagrid" fitColumns="true"
		pagination="false" toolbar="#tb" striped="true"
		data-options="selected:true,iconCls:'icon-vote'">
		<thead>
			<tr>
				<th field="choiceValue" width="100" align="center">选项</th>
				<th field="num" width="80" align="center">票数</th>

			</tr>
		</thead>
	</table>
	<div id="tb">
		<a href="javascript:window.print()" class="easyui-linkbutton"
			iconCls="icon-print" plain="true">投票结果打印</a>
		<div id="voteTitle" class="voteStatic"></div>
	</div>
</body>
</html>
