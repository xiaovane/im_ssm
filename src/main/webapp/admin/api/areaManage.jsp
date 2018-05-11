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
<script type="text/javascript" src="../../static/api/js/areaList.js"></script>
<link rel="stylesheet" href="../../static/api/css/common.css">
<link rel="stylesheet" href="../../static/api/css/special.css">
</head>

<body>
	<table id="dg" title="小区管理" class="easyui-datagrid" fitColumns="true"
		pagination="true" toolbar="#tb" striped="true"
		data-options="selected:true,iconCls:'icon-county'">
		<thead>
			<tr>
				<th field="cb" checkbox="true" align="center"></th>
				<th field="provinceName" width="100" align="center">省份</th>
				<th field="cityName" width="80" align="center">地级市</th>
				<th field="countyName" width="80" align="center">区县市</th>
				<th field="xiaoQuName" width="80" align="center">小区</th>
				<th field="operate" width="120" align="center"
					formatter="formatOperation">操作</th>
			</tr>
		</thead>
	</table>
	<div id="tb">
		<div>
			<span class="tb-span">&nbsp;小区名&nbsp;<input type="text"
				id="m_xiaoqu" class="easyui-textbox"
				onkeydown="if(event.keyCode==13) searchAreaInfo()"
				placeholder="请输入小区名"></span> <a href="javascript:searchAreaInfo()"
				class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
			<a href="javascript:reload()" class="easyui-linkbutton"
				iconCls="icon-reload" plain="true">刷新</a>
				<a
				href="javascript:window.parent.openTab('新增小区','areaModify.jsp?param=0','icon-writeWebUser')"
				class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
				 <a
				href="javascript:commonDelete('../position/positionDelete.do')"
				class="easyui-linkbutton" iconCls="icon-cancel" plain="true">删除</a>
		</div>
	</div>
</body>
</html>
