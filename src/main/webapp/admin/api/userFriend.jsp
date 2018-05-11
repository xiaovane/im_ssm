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
<script type="text/javascript" src="../../static/api/js/friendList.js"></script>
<link rel="stylesheet" href="../../static/api/css/common.css">
<link rel="stylesheet" href="../../static/api/css/special.css">
</head>

<body >
	<table id="dg" title="用户好友/黑名单" class="easyui-datagrid" fitColumns="true"
		pagination="true" toolbar="#tb" striped="true" data-options="selected:true,iconCls:'icon-userGroup'">
		<thead>
			<tr>
				<th field="cb" checkbox="true" align="center"></th>
				<th field="userId" width="100" align="center">用户ID</th>
				<th field="mobile" width="100">手机号</th>
				<th field="nickName" width="100" align="center">昵称</th>
				<th field="provice" width="80" align="center">省份</th>
				<th field="dcity" width="80" align="center">地级市</th>
				<th field="xcity" width="80" align="center">县级市</th>
				<th field="qcity" width="80" align="center">区县市</th>
				<th field="village" width="80" align="center">小区</th>
				<th field="building" width="80" align="center">栋</th>
				<th field="unit" width="80" align="center">单元</th>
				<th field="room" width="80" align="center">室</th>
				<th field="userStatus" width="60" align="center">状态</th>
				<th field="userThumb" width="60" align="center">点赞</th>
				<th field="userType" width="60" align="center">用户类型</th>
				<th field="regist" width="100" align="center">注册日期</th>
				<th field="device" width="100" align="center">设备号</th>
			</tr>
		</thead>
	</table>
	<div id="tb">
		<div>

			<a href="javascript:reload()" class="easyui-linkbutton"
				iconCls="icon-reload" plain="true">刷新</a>
		</div>
	</div>
</body>
</html>
