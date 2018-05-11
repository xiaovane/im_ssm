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
	src="../../static/api/js/voteInfoModify.js"></script>
<link rel="stylesheet" href="../../static/api/css/common.css">
<link rel="stylesheet" href="../../static/api/css/special.css">
</head>

<body>

	<div class="easyui-panel" title="投票管理"
		data-options="selected:true,iconCls:'icon-vote'">
		<div class="vote-div">
			<span class="vote-span">所在群:</span> <input type="text"
				class="vote-input boderAll" id="groupName" readonly="readonly"></input>
		</div>
		<div class="vote-div">
			<span class="vote-span">创建人:</span> <input type="text"
				class="vote-input boderAll" id="createName" readonly="readonly"></input>
		</div>
		<div class="vote-div">
			<span class="vote-span">标题:</span> <input type="text"
				class="vote-input" id="title"></input>
		</div>
		<div class="vote-div">
			<span class="vote-span">开始时间:</span> <input id="startTime"
				type="text" class="easyui-datebox" required="required">
		</div>
		<div class="vote-div">
			<span class="vote-span">结束时间:</span> <input id="endTime"
				type="text" class="easyui-datebox" required="required">
		</div>
		<div class="captureInfo-div">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-submit'"
				id="btnSubmit">确定</a>
		</div>
	</div>
</body>
</html>
