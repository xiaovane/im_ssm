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
	src="../../static/api/js/captureInfoModify.js"></script>
<link rel="stylesheet" href="../../static/api/css/common.css">
<link rel="stylesheet" href="../../static/api/css/special.css">
</head>

<body >

	<div class="easyui-panel" title="列表信息抓取配置">
		<div class="feedBack-div">
			<span class="feedBack-span-input">抓取名称:</span> <input type="text"
				class="feedBack-input" id="listName"></input>
		</div>
		<div class="feedBack-div">
			<span class="feedBack-span-input">抓取地址:</span> <input type="text"
				class="feedBack-input" id="listUrl"></input>
		</div>
		<div class="feedBack-div">
			<span class="feedBack-span-input">标题标签:</span> <input type="text"
				class="feedBack-input" id="listTitle"></input>
		</div>
		<div class="feedBack-div">
			<span class="feedBack-span-input">内容标签:</span> <input type="text"
				class="feedBack-input" id="listContent"></input>
		</div>

	</div>

	<div class="easyui-panel" title="详细页抓取配置">
		<div class="feedBack-div">
			<span class="feedBack-span-input">抓取地址:</span> <input type="text"
				class="feedBack-input" id="detailUrl"></input>
		</div>
		<div class="feedBack-div">
			<span class="feedBack-span-input">抓取标签:</span> <input type="text"
				class="feedBack-input" id="detailTitle"></input>
		</div>
	</div>
	<div class="feedBack-div">
		<span class="feedBack-span-input" id="categoryNum">放置栏目:</span>
		<div class="urlcode">
			<select class="easyui-combobox" style="width: 158px; height: 30px;" id="categoryName">
				<option value='001'>法律法规</option>
				<option value='002'>新时代</option>
				<option value='003'>物业</option>
				<option value='004'>新闻</option>
				<option value='005'>财经</option>
				<option value='006'>科技</option>
				<option value='007'>汽车</option>
				<option value='008'>军事</option>
				<option value='009'>社会</option>
				<option value='010'>旅游</option>
				<option value='011'>女性</option>
				<option value='012'>教育</option>
				<option value='013'>健康</option>
				<option value='014'>育儿</option>
				<option value='015'>历史</option>
				<option value='016'>国际</option>
				<option value='017'>健身</option>
				<option value='018'>政务</option>
				<option value='019'>房产</option>
				<option value='020'>家居</option>
				<option value='021'>生活</option>
				<option value='022'>美食</option>
				<option value='023'>宠物</option>
				<option value='024'>亲子</option>
			</select>
		</div>
	</div>
	<div style="clear: both"></div>
	<div class="feedBack-div">
		<span class="feedBack-span-input" id="code-span">页面编码:</span>
		<div class="urlcode">
			<select class="easyui-combobox" style="width: 158px; height: 30px;" id="urlCode">
				<option value='utf-8'>utf-8</option>
				<option value='gbk'>gbk</option>
			</select>
		</div>
	</div>
	<div class="captureInfo-div" style="clear: left">
		<a  class="easyui-linkbutton"
			data-options="iconCls:'icon-submit'" id="btnReply">确定</a>
	</div>
</body>
</html>
