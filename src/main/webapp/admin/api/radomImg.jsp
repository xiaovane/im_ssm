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
<script type="text/javascript" src="../../static/api/js/radomImg.js"></script>
<link rel="stylesheet" href="../../static/api/css/common.css">
<link rel="stylesheet" href="../../static/api/css/special.css">
</head>

<body>
	<table id="dg" title="随机昵称图片" class="easyui-datagrid" fitColumns="true"
		pagination="true" toolbar="#tb" striped="true"
		data-options="selected:true,iconCls:'icon-radomImg'">
		<thead>
			<tr>
				<th field="cb" checkbox="true" align="center"></th>
				<th field="nickName" width="100" align="center">昵称</th>
				<th field="image" width="80" align="center">图片地址</th>
				<th field="operate" width="120" align="center"
					formatter="formatOperation">操作</th>
			</tr>
		</thead>
	</table>
	<div id="tb">
		<div>
			<span class="tb-span">&nbsp;昵称&nbsp;<input type="text"
				id="m_nickName" class="easyui-textbox"
				onkeydown="if(event.keyCode==13) searchFeedBack()"
				placeholder="请输入昵称"></span> <a
				href="javascript:searchFeedBack()" class="easyui-linkbutton"
				iconCls="icon-search" plain="true">搜索</a> <a
				href="javascript:reload()" class="easyui-linkbutton"
				iconCls="icon-reload" plain="true">刷新</a>
				<a
				href="javascript:window.parent.openTab('新增随机图片','radomImgModify.jsp?param=0','icon-writeWebUser')"
				class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
				 <a
				href="javascript:commonDelete('../infoList/radomImgDelete.do')"
				class="easyui-linkbutton" iconCls="icon-cancel" plain="true">删除</a>
		</div>
	</div>
</body>
</html>
