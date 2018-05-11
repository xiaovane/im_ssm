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
	src="../../static/api/js/cardInfoModify.js"></script>
<link rel="stylesheet" href="../../static/api/css/common.css">
<link rel="stylesheet" href="../../static/api/css/special.css">
</head>

<body >

	<div class="easyui-panel" title="用户卡券" data-options="selected:true,iconCls:'icon-custor'">
		<div class="feedBack-div">
			<span class="feedBack-span-input">标题:</span> <input type="text"
				class="feedBack-input" id="listName"></input>
		</div>
		<div class="feedBack-div">
			<span class="feedBack-span-input">金额:</span> <input type="text"
				class="feedBack-input" id="listUrl"></input>
		</div>
		<div class="feedBack-div">
			<span class="feedBack-span-input">数量:</span> <input type="text"
				class="feedBack-input" id="listTitle"></input>
		</div>
		<div class="feedBack-div">
			<span class="feedBack-span-input">开始时间:</span> 
			<input id="startTime" type="text" class="easyui-datebox" required="required">
		</div>
		<div class="feedBack-div">
			<span class="feedBack-span-input">结束时间:</span> 
			<input id="startTime" type="text" class="easyui-datebox" required="required">
		</div>
		<div class="feedBack-div">
			<span class="feedBack-span-input">简介:</span> 
			<textarea rows="1" cols="100"></textarea>
		</div>

	</div>
	
	<div class="captureInfo-div" style="clear: left">
		<a href="javascript:submitData()" class="easyui-linkbutton"
			data-options="iconCls:'icon-submit'" id="btnReply">确定</a>
	</div>
</body>
</html>
