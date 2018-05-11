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
<script type="text/javascript" src="../../static/api/js/voteInfoList.js"></script>
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
				<th field="groupNickName" width="100" align="center">群昵称</th>
				<th field="nickName" width="80" align="center">创建者</th>
				<th field="title" width="80" align="center">标题</th>
				<th field="startTime" width="80" align="center">开始时间</th>
				<th field="endTime" width="80" align="center">结束时间</th>
				<th field="voteStatus" width="80" align="center">状态</th>
				<th field="operate" width="120" align="center"
					formatter="formatOperation">操作</th>
			</tr>
		</thead>
	</table>
	<div id="tb">
		<div>
			<span class="tb-span">&nbsp;标题&nbsp;<input type="text"
				id="v_title" class="easyui-textbox"
				onkeydown="if(event.keyCode==13) searchVoteInfo()"
				placeholder="请输入投票标题"></span> <a href="javascript:searchVoteInfo()"
				class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
			<a href="javascript:reload()" class="easyui-linkbutton"
				iconCls="icon-reload" plain="true">刷新</a>
		</div>
	</div>
</body>
</html>
