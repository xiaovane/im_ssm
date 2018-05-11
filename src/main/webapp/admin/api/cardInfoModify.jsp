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

<body>

	<div class="easyui-panel" title="商户卡券"
		data-options="selected:true,iconCls:'icon-company'">
		<div class="vote-div">
			<span class="vote-span">标题:</span> <input type="text"
				class="vote-input " id="c_title"></input>
		</div>
		<div class="vote-div">
			<span class="vote-span">折扣率:</span> <select class="easyui-combobox"
				style="width: 158px; height: 30px;" id="c_rate">
				<option value='95'>95折</option>
				<option value='9'>9折</option>
				<option value='85'>85折</option>
				<option value='8'>8折</option>
				<option value='75'>75折</option>
				<option value='7'>7折</option>
				<option value='65'>65折</option>
				<option value='6'>6折</option>
				<option value='55'>55折</option>
				<option value='5'>5折</option>
				<option value='45'>45折</option>
				<option value='4'>4折</option>
				<option value='35'>35折</option>
				<option value='3'>3折</option>
				<option value='25'>25折</option>
				<option value='2'>2折</option>
				<option value='15'>15折</option>
				<option value='1'>1折</option>
			</select>
		</div>
		<div class="vote-div">
			<span class="vote-span">数量:</span> <input type="text"
				class="vote-input" id="c_num"></input>
		</div>
		<div class="vote-div">
			<span class="vote-span">开始时间:</span> <input id="c_startTime"
				type="text" class="easyui-datebox" required="required">
		</div>
		<div class="vote-div">
			<span class="vote-span">结束时间:</span> <input id="c_endTime" type="text"
				class="easyui-datebox" required="required">
		</div>
		<div class="vote-div">
			<span class="vote-span">简介:</span>
			<textarea rows="10" cols="50" id="c_desc"></textarea>
		</div>
		<div class="captureInfo-div">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-submit'"
				id="btnSubmit">确定</a>
		</div>
</body>
</html>
