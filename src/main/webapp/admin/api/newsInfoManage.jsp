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
<script type="text/javascript" src="../../static/api/js/newsInfoList.js"></script>
<link rel="stylesheet" href="../../static/api/css/common.css">
<link rel="stylesheet" href="../../static/api/css/special.css">
</head>

<body>
	<table id="dg" title="新闻管理" class="easyui-datagrid" fitColumns="true"
		pagination="true" toolbar="#tb" striped="true"
		data-options="selected:true,iconCls:'icon-news'">
		<thead>
			<tr>
				<th field="cb" checkbox="true" align="center"></th>
				<th field="title" width="120" align="center">标题</th>
				<th field="url" width="140" align="center">详细页面地址</th>
				<th field="infoDate" width="80" align="center">日期</th>
				<th field="zhuanZai" width="80" align="center">转载</th>
				<th field="author" width="80" align="center">作者</th>
				<th field="categoryName" width="80" align="center">栏目</th>
				<th field="operate" width="80" align="center"
					formatter="formatOperation">操作</th>
			</tr>
		</thead>
	</table>
	<div id="tb">
		<div>
			<span class="tb-span">&nbsp;标题&nbsp;<input type="text"
				id="c_newsTitle" class="easyui-textbox"
				onkeydown="if(event.keyCode==13) searchNews()"
				placeholder="请输入新闻标题"></span> <span class="tb-span">&nbsp;开始时间&nbsp;<input
				id="c_startTime" type="text" class="easyui-datebox"></span> <span
				class="tb-span">&nbsp;结束时间&nbsp;<input id="c_endTime"
				type="text" class="easyui-datebox"></span><a
				href="javascript:searchNews()" class="easyui-linkbutton"
				iconCls="icon-search" plain="true">搜索</a> <a
				href="javascript:reload()" class="easyui-linkbutton"
				iconCls="icon-reload" plain="true">刷新</a>
				<a
				href="javascript:window.parent.openTab('新增小区','newsInfoModify.jsp?param=0','icon-writeWebUser')"
				class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
				 <a
				href="javascript:commonDelete('../captureInfo/newsInfoDelete.do')"
				class="easyui-linkbutton" iconCls="icon-cancel" plain="true">删除</a>
		</div>
	</div>
</body>
</html>
