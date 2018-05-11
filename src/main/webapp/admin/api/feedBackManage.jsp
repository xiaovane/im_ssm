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
<script type="text/javascript" src="../../static/api/js/feedBackList.js"></script>
<link rel="stylesheet" href="../../static/api/css/common.css">
<link rel="stylesheet" href="../../static/api/css/special.css">
</head>

<body>
	<table id="dg" title="投诉反馈" class="easyui-datagrid" fitColumns="true"
		pagination="true" toolbar="#tb" striped="true"
		data-options="selected:true,iconCls:'icon-feedBack'">
		<thead>
			<tr>
				<th field="cb" checkbox="true" align="center"></th>
				<th field="nickName" width="100" align="center">昵称</th>
				<th field="content" width="80" align="center">反馈内容</th>
				<th field="feedBackTime" width="80" align="center">反馈时间</th>
				<th field="replyContent" width="80" align="center">回复内容</th>
				<th field="feedBackStatus" width="80" align="center">状态</th>
				<th field="feedBackType" width="80" align="center">类型</th>
				<th field="operate" width="120" align="center"
					formatter="formatOperation">操作</th>
			</tr>
		</thead>
	</table>
	<div id="tb">
		<div>
			<span class="tb-span">&nbsp;投诉人昵称&nbsp;<input type="text"
				id="m_nickName" class="easyui-textbox"
				onkeydown="if(event.keyCode==13) searchFeedBack()"
				placeholder="请输入投诉人昵称"></span> <span class="tb-span">&nbsp;反馈类型&nbsp;<select
				type="text" id="m_type" class="easyui-combobox" style="width:158px;height:26px;">
					<option value="0">投诉好友</option>
					<option value="1">系统反馈</option>
			</select>
			</span> <a href="javascript:searchFeedBack()" class="easyui-linkbutton"
				iconCls="icon-search" plain="true">搜索</a> <a
				href="javascript:reload()" class="easyui-linkbutton"
				iconCls="icon-reload" plain="true">刷新</a>
		</div>
	</div>
</body>
</html>
