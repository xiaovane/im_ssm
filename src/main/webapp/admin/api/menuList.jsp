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
<script type="text/javascript" src="../../static/api/js/menuList.js"></script>
<link rel="stylesheet" href="../../static/api/css/common.css">
<link rel="stylesheet" href="../../static/api/css/special.css">
</head>

<body>
	<table id="dg" title="模块授权" class="easyui-datagrid" fitColumns="true"
		pagination="true" toolbar="#tb" striped="true"
		data-options="selected:true,iconCls:'icon-county'">
		<thead>
			<tr>
				<th field="cb" checkbox="true" align="center"></th>
				<th field="menuNum" width="100" align="center">模块编号</th>
				<th field="menuName" width="80" align="center">模块名</th>				
			</tr>
		</thead>
	</table>
	<div id="tb">
		<div>
			 <a
				href="javascript:selectMenus()"
				class="easyui-linkbutton" iconCls="icon-save" plain="true">授权保存</a>

		</div>
	</div>
</body>
</html>
