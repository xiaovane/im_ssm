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
<script type="text/javascript" src="../../static/api/js/areaModify.js"></script>
<script type="text/javascript" src="../../static/api/js/userOpr.js"></script>
<link rel="stylesheet" href="../../static/api/css/common.css">
<link rel="stylesheet" href="../../static/api/css/special.css">
</head>

<body>

	<div class="easyui-panel" title="小区管理"
		data-options="selected:true,iconCls:'icon-county'">
		<div class="xiaoqu-div">
			<div class="xiaoqu">省份:</div>
			<div class="xiaoqu">
				<select class="easyui-combobox"
					id="province_code"  onchange="getPrivice()"  style="width:158px;height:28px;">
					<option value="">请选择省份</option>
				</select>
		  	</div> 
		</div>
		<div class="xiaoqu-div">
			<div class="xiaoqu">地级市:</div>
			<div class="xiaoqu">
				<select class="easyui-combobox"
					id="city_code" onchange="getCity()" style="width:158px;height:28px;">
					<option value="">请选择地级市</option>
				</select>
		  	</div> 
		</div>
		<div class="xiaoqu-div">
			<div class="xiaoqu">区县市:</div>
			<div class="xiaoqu">
				<select class="easyui-combobox"
					id="county_code"  onchange="getCounty()" style="width:158px;height:28px;">
					<option value="">请选择区县市</option>
				</select>
		  	</div> 
		</div>
		
		<!-- <div class="xiaoqu-div">
			<span class="feedBack-span-code">编码:</span>
			<div class="xiaoqu-input">
				<input type="text" id="xiaoqu_code"></input><font color="red"></font>
			</div>
		</div> -->
		<input type="hidden" id="xiaoqu_code">
		<div class="xiaoqu-div">
			<span class="feedBack-span">小区:</span>
			<textarea rows="1" cols="100" class="feedBack-txt" id="xiaoqu"></textarea>
		</div>
		<div class="xiaoqu-div">
			<a href="javascript:btnSubmit()" class="easyui-linkbutton"
				data-options="iconCls:'icon-submit'"  id="btnSubmit">确定</a>
		</div>
	</div>
</body>
</html>
