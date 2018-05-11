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
<script type="text/javascript" src="../../static/api/js/userCardList.js"></script>
<link rel="stylesheet" href="../../static/api/css/common.css">
<link rel="stylesheet" href="../../static/api/css/special.css">
</head>

<body style="margin: 1px; font-family: microsoft yahei">
	<table id="dg" title="用户卡券" class="easyui-datagrid" fitColumns="true"
		pagination="true" toolbar="#tb" data-options="selected:true,iconCls:'icon-custor'" striped="true">
		<thead>
			<tr>
				<th field="cb" checkbox="true" align="center"></th>
				<th field="userId" width="100" align="center">用户id</th>
				<th field="money" width="80" align="center">折扣</th>
				<th field="num" width="80" align="center">数量</th>
				<th field="title" width="80" align="center">标题</th>
				<th field="description" width="80" align="center">简介</th>
				<th field="companyName" width="80" align="center">商家</th>
				<th field="startTime" width="80" align="center">开始时间</th>
				<th field="endTime" width="80" align="center">结束时间</th>
				<th field="cardStatus" width="80" align="center">状态</th>
				<th field="operate" width="80" align="center"
					formatter="formatOperation">操作</th>
			</tr>
		</thead>
	</table>
	<div id="tb">
		<div>
			<span class="tb-span">&nbsp;用户id&nbsp;<input type="text"
				id="m_userId" class="easyui-textbox"
				onkeydown="if(event.keyCode==13) searchFeedBack()"
				placeholder="请输入用户id"></span> <span class="tb-span">&nbsp;用户类型&nbsp;<select
				type="text" id="m_useType" class="easyui-textbox">
					<option>请选择类型</option>
					<option value="0">投诉</option>
					<option value="1">反馈</option>
			</select>
			</span> <a href="javascript:searchFeedBack()" class="easyui-linkbutton"
				iconCls="icon-search" plain="true">搜索</a> <a
				href="javascript:reload()" class="easyui-linkbutton"
				iconCls="icon-reload" plain="true">刷新</a>
		</div>
	</div>
</body>
</html>
