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
<script type="text/javascript" src="../../static/api/js/userOpr.js"></script>
<link rel="stylesheet" href="../../static/api/css/common.css">
<link rel="stylesheet" href="../../static/api/css/special.css">
</head>

<body>
	<div class="easyui-panel" title="发送消息"
		data-options="selected:true,iconCls:'icon-feedBack'">
		<div class="userSendMsg-div">
			<span class="userSendMsg-span">发送内容:</span>
			<textarea rows="1" cols="100" class="userSendMsg-txt" id="msgContent"></textarea>
		</div>
		<div class="userSendMsg-div">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-submit'"
				id="btnSendmsg">发送</a>
		</div>
	</div>
</body>
</html>
