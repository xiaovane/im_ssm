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
<script type="text/javascript" src="../../static/api/js/groupList.js"></script>
<link rel="stylesheet" href="../../static/api/css/common.css">
<link rel="stylesheet" href="../../static/api/css/special.css">
</head>

<body>
	<table id="dg" title="群组管理" class="easyui-datagrid" striped="true"
		fitColumns="true" pagination="true" toolbar="#tb" data-options="selected:true,iconCls:'icon-userGroup'">
		<thead>
			<tr>
				<th field="cb" checkbox="true" align="center"></th>
				<th field="groupId" width="100" align="center">群ID</th>
				<th field="groupNickName" width="100">群昵称</th>
				<th field="createName" width="100" align="center">创建者</th>
				<th field="groupSize" width="80" align="center">群最大人数</th>
				<th field="groupLev" width="80" align="center">群等级</th>
				<th field="createTime" width="120" align="center">创建时间</th>
				<th field="groupStatus" width="80" align="center">状态</th>
				<th field="operate" width="80" align="center"
					formatter="formatOperation">操作</th>
			</tr>
		</thead>
	</table>
	<div id="tb">
		<div>
			<span class="tb-span">&nbsp;群id&nbsp;<input type="text"
				id="m_groupId" class="easyui-textbox"
				onkeydown="if(event.keyCode==13) searchGroupInfo()"
				placeholder="请输入群id"></span> <span class="tb-span">&nbsp;群昵称&nbsp;<input
				type="text" id="m_nickName" class="easyui-textbox"
				onkeydown="if(event.keyCode==13) searchGroupInfo()"
				placeholder="请输入群昵称"></span> <span class="tb-span"> </span> <a
				href="javascript:searchGroupInfo()" class="easyui-linkbutton"
				iconCls="icon-search" plain="true">搜索</a> <a
				href="javascript:reload()" class="easyui-linkbutton"
				iconCls="icon-reload" plain="true">刷新</a>
		</div>
	</div>
</body>
</html>
