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

<body style="margin: 1px; font-family: microsoft yahei">
	<div class="easyui-panel" title="重置密码"
		data-options="selected:true,iconCls:'icon-modify'">
		<div class="userSendMsg-div">
			<ul>
				<li>
					<div class="resetPwd-div">新密码:</div>
					<div class="resetPwd-div">
						<input type="password" id="txtPwd" data-options="required:true"  />
					</div>
				</li>
				<li class="resetClear" ></li>
				<li>
					<div class="resetPwd-div">确认密码:</div>
					<div class="resetPwd-div">
						<input type="password" id="txtPwdAgin" />
					</div>
				</li>
			</ul>
		</div>
		<div class="resetPwd-btn">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-submit'"
				id="btnResetPwd">发送</a>
		</div>
	</div>
</body>
</html>
