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
<script type="text/javascript" src="../../static/api/js/feedBackModify.js"></script>
<link rel="stylesheet" href="../../static/api/css/common.css">
<link rel="stylesheet" href="../../static/api/css/special.css">
</head>

<body>

	<div class="easyui-panel" title="投诉反馈" data-options="selected:true,iconCls:'icon-feedBack'">
		<div class="feedBack-div">
			<span class="feedBack-span-input">昵称:</span> <input type="text"
				class="feedBack-input boderAll" id="nickName" readonly="readonly"></input>
		</div>
		<div class="feedBack-div">
			<span class="feedBack-span-input">类型:</span> <input type="text"
				class="feedBack-input boderAll" id="type" readonly="readonly"></input>
		</div>
		<div class="feedBack-div">
			<span class="feedBack-span-input">时间:</span> <input type="text"
				class="feedBack-input boderAll" id="time" readonly="readonly"></input>
		</div>
		<div class="feedBack-div">
			<span class="feedBack-span">反馈内容:</span>
			<textarea rows="1" cols="100" class="feedBack-txt " id="feedBackContent"
				readonly="readonly"></textarea>
		</div>
		<div class="feedBack-div">
			<span class="feedBack-span">回复内容:</span>
			<textarea rows="1" cols="100" class="feedBack-txt" id="replycontent"></textarea>
		</div>
		<div class="feedBack-div">
			<a href="javascript:submitData()" class="easyui-linkbutton"
				data-options="iconCls:'icon-submit'" id="btnReply">确定</a>
		</div>
	</div>
</body>
</html>
