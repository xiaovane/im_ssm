<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>抓取配置</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../common/head.jspf"%>
<!-- 添加需要的js和css -->
<script type="text/javascript" src="../../static/api/js/common.js"></script>
<script type="text/javascript" src="../../static/api/js/captureInfoList.js"></script>
<link rel="stylesheet" href="../../static/api/css/common.css">
<link rel="stylesheet" href="../../static/api/css/special.css">
</head>

<body >
	<table id="dg" title="抓取配置" class="easyui-datagrid" fitColumns="true"
		pagination="true" toolbar="#tb" striped="true"
		data-options="selected:true,iconCls:'icon-setting'">
		<thead>
			<tr>
				<th field="cb" checkbox="true" align="center"></th>
				<th field="urlList" width="100" align="center">列表地址</th>
				<th field="urlName" width="80" align="center">列表名称</th>
				<th field="urlCode" width="80" align="center">页面编码</th>
				<th field="title" width="80" align="center">标题标签</th>
				<th field="content" width="80" align="center">内容标签</th>
				<th field="urlDetail" width="80" align="center">详细页地址</th>
				<th field="from" width="80" align="center">详细页面标签</th>
				<th field="categoryNum" width="80" align="center">栏目</th>
				<th field="operate" width="80" align="center"
					formatter="formatOperation">操作</th>
			</tr>
		</thead>
	</table>
		<div id="tb">
		<div>
			<span class="tb-span">&nbsp;列表名称&nbsp;<input type="text"
				id="m_xiaoqu" class="easyui-textbox"
				onkeydown="if(event.keyCode==13) searchAreaInfo()"
				placeholder="请输入列表名称"></span> <a href="javascript:searchAreaInfo()"
				class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
			<a href="javascript:reload()" class="easyui-linkbutton"
				iconCls="icon-reload" plain="true">刷新</a>
				<a
				href="javascript:window.parent.openTab('新增小区','captureInfoModify.jsp?param=0','icon-writeWebUser')"
				class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
				 <a
				href="javascript:commonDelete('../captureInfo/captureInfoDelete.do')"
				class="easyui-linkbutton" iconCls="icon-cancel" plain="true">删除</a>
		</div>
	</div>
</body>
</html>
