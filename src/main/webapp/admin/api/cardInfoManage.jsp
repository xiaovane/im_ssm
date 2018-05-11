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
<script type="text/javascript" src="../../static/api/js/cardList.js"></script>
<link rel="stylesheet" href="../../static/api/css/common.css">
<link rel="stylesheet" href="../../static/api/css/special.css">
</head>

<body style="margin: 1px; font-family: microsoft yahei">
	<table id="dg" title="商家卡券" class="easyui-datagrid" fitColumns="true"
		pagination="true" toolbar="#tb"
		data-options="selected:true,iconCls:'icon-company'" striped="true">
		<thead>
			<tr>
				<th field="cb" checkbox="true" align="center"></th>
				<th field="nickName" width="100" align="center">创建者</th>
				<th field="money" width="80" align="center">折扣</th>
				<th field="startTime" width="80" align="center">开始时间</th>
				<th field="endTime" width="80" align="center">结束时间</th>
				<th field="num" width="80" align="center">数量</th>
				<th field="title" width="80" align="center">标题</th>
				<th field="description" width="80" align="center">简介</th>
				<th field="city" width="80" align="center">所在城市</th>
				<th field="cardStatus" width="80" align="center">状态</th>
				<th field="cardLook" width="80" align="center">审核状态</th>
				<th field="reason" width="80" align="center">原因</th>
				<th field="operate" width="120" align="center"
					formatter="formatOperation">操作</th>
			</tr>
		</thead>
	</table>
	<div id="tb">
		<div>
			<span class="tb-span">&nbsp;标题&nbsp;<input type="text"
				id="c_cardTitle" class="easyui-textbox"
				onkeydown="if(event.keyCode==13) searchCard()"
				placeholder="请输入卡券标题"></span> <span class="tb-span">&nbsp;审核状态&nbsp;<select
				type="text" id="c_cardType" class="easyui-combobox">
					
					<option value="2">待审核</option>
					<option value="9">审核通过</option>
					<option value="-1">审核未通过</option>
			</select>
			</span> <span class="tb-span">&nbsp;开始时间&nbsp;<input id="c_startTime"
				type="text" class="easyui-datebox"></span> <span class="tb-span">&nbsp;结束时间&nbsp;<input
				id="c_endTime" type="text" class="easyui-datebox"></span><a
				href="javascript:searchCard()" class="easyui-linkbutton"
				iconCls="icon-search" plain="true">搜索</a> <a
				href="javascript:reload()" class="easyui-linkbutton"
				iconCls="icon-reload" plain="true">刷新</a>
		</div>
	</div>
</body>
</html>
