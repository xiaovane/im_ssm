
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>SSM聊天管理平台</title>
<%@include file="../common/head.jspf"%>
<link rel="stylesheet" href="../../static/api/css/common.css">
<script type="text/javascript" src="../../static/api/js/common.js"></script>
</head>
<body class="easyui-layout">
	<!-- 顶部logo栏 -->
	<div region="north" class="mainLeftNav">
		<table class="mainLeftTable">
			<tr>
				<td class="icon-logo"></td>
				<td valign="middle"><font class="h1-class"><strong>SSM</strong></font>&nbsp;&nbsp;<font
					class="h2-class">聊天管理平台</font></td>
				<td valign="middle" width="160px"><font size="3">&nbsp;&nbsp;<strong>欢迎：</strong><font
						color="#ff6600" id="userName">${currentUser.userName }</font></font></td>
				<td valign="middle" width="100px"><a href="javascript:openTab('个人信息','midifyAdmin.jsp?userName=${currentUser.userName}&param=1','icon-manage')"
					class="easyui-linkbutton  hidden"
					data-options="plain:true,iconCls:'icon-person'" style="color: #fff;display:block !important;">个人信息</a></td>
				<td valign="middle" width="100px"><a href="javascript:logout()"
					class="easyui-linkbutton  hidden"
					data-options="plain:true,iconCls:'icon-exit'" style="color: #fff;display:block !important;" onclick="loginOut()">安全退出</a></td>
			</tr>
		</table>
	</div>
	<!-- 左侧菜单栏 -->
	<div region="west" class="mainNav " title="导航菜单" split="true"
		data-options="plain:true,iconCls:'icon-menu'" id="menu">
		<div class="easyui-accordion" data-options="fit:true,border:false">
			<div title="用户/群组管理"
				data-options="selected:true,iconCls:'icon-userGroup'" >
				<a id="001001"
					href="javascript:openTab('用户管理','userManage.jsp','icon-user')"
					class="easyui-linkbutton  hidden"
					data-options="plain:true,iconCls:'icon-user'" title="userManage.jsp">用户管理</a> <a
					id="001002"
					href="javascript:openTab('群组管理','groupManage.jsp','icon-group')"
					class="easyui-linkbutton  hidden"
					data-options="plain:true,iconCls:'icon-group'" title="groupManage.jsp">群组管理</a> <a
					id="001003"
					href="javascript:openTab('系统反馈','feedBackManage.jsp','icon-feedBack')"
					class="easyui-linkbutton  hidden"
					data-options="plain:true,iconCls:'icon-feedBack'" title="feedBackManage.jsp">系统反馈 </a>
					<a
					id="001005"
					href="javascript:openTab('常见问题','baseQuestion.jsp','icon-baseQuestion')"
					class="easyui-linkbutton  hidden"
					data-options="plain:true,iconCls:'icon-baseQuestion'" title="baseQuestion.jsp">常见问题 </a>
					<a
					id="001006"
					href="javascript:openTab('随机昵称图片','radomImg.jsp','icon-radom')"
					class="easyui-linkbutton  hidden"
					data-options="plain:true,iconCls:'icon-radom'" title="radomImg.jsp">随机昵称图片 </a>
				    <a
					id="001004"
					href="javascript:openTab('小区管理','areaManage.jsp','icon-county')"
					class="easyui-linkbutton  hidden"
					data-options="plain:true,iconCls:'icon-county'" title="areaManage.jsp">小区管理 </a>
			</div>
			<div title="新闻管理" data-options="iconCls:'icon-news'" >
				<a id="002001"
					href="javascript:openTab('抓取配置','captureInfoManage.jsp','icon-setting')"
					class="easyui-linkbutton  hidden"
					data-options="plain:true,iconCls:'icon-setting'" title="captureInfoManage.jsp">抓取配置 </a> <a
					id="002002"
					href="javascript:openTab('新闻管理','newsInfoManage.jsp','icon-news')"
					class="easyui-linkbutton  hidden"
					data-options="plain:true,iconCls:'icon-news'" title="newsInfoManage.jsp">新闻管理 </a>
			</div>
			<div title="卡券管理" data-options="iconCls:'icon-card'" >
				<a id="003001"
					href="javascript:openTab('商家卡券管理','cardInfoManage.jsp','icon-company')"
					class="easyui-linkbutton  hidden"
					data-options="plain:true,iconCls:'icon-company'" title="cardInfoManage.jsp">商家卡券管理 </a> <a
					id="003002"
					href="javascript:openTab('用户卡券管理','userCardInfoManage.jsp','icon-custor')"
					class="easyui-linkbutton  hidden"
					data-options="plain:true,iconCls:'icon-custor'" title="userCardInfoManage.jsp">用户卡券管理 </a>
			</div>
			<div title="投票问卷管理" data-options="iconCls:'icon-vote'" >
				<a id="004001"
					href="javascript:openTab('普通投票管理','voteInfoManage.jsp?param=1','icon-vote')"
					class="easyui-linkbutton  hidden"
					data-options="plain:true,iconCls:'icon-vote'" title="voteInfoManage.jsp?param=1">普通投票管理 </a> <a
					id="004002"
					href="javascript:openTab('提名投票管理','voteInfoManage.jsp?param=2','icon-xuanju')"
					class="easyui-linkbutton  hidden"
					data-options="plain:true,iconCls:'icon-xuanju'" title="voteInfoManage.jsp?param=2">提名投票管理 </a>
			</div>
			<div title="个人中心" data-options="iconCls:'icon-manage'"  >
				<a href="javascript:openTab('管理员','adminList.jsp','icon-manage')"
					class="easyui-linkbutton  hidden"
					data-options="plain:true,iconCls:'icon-manage'"
					style="width: 150px;" id="005001">管理员</a>
			</div>
		</div>
	</div>
	<div data-options="region:'center'" style="background: #eee;">
		<div class="easyui-tabs" fit="true" border="false" id="tabs">
			<div title="首页" data-options="iconCls:'icon-home'">
				<iframe src="userManage.jsp" width="100%" height="100%"
					frameborder="0" id="iframe"></iframe>
			</div>
		</div>
	</div>
	<!-- 底部信息栏 -->
	<div region="south" class="footer" align="center">Copyright ©
		2017-2020 whai的SSM系统 版权所有 whai</div>
	<script type="text/javascript">
		//根据权限控制左边的菜单
		$(function() {
			var userName = $("#userName").text();
			if (userName == "admin") {
				$("#menu").find("a").each(function() {
					$(this).removeClass("hidden");
				})
			}
			else {
				$("#menu").find("a").each(function() {
					var currentMenu=$(this).attr("id");
					
					$("#"+currentMenu+"").parent().parent().addClass("hidden");
					if (currentMenu!=""&&currentMenu!=undefined) {
						$.post("../userAdmin/getMenu.do", {
							"userName":userName						
						}, function(result) {
							for (var i = 0; i < result.data.length; i++) {
								//alert(result.data[i].menu);								
								if (result.data[i].menu==currentMenu) {
									$("#"+currentMenu+"").removeClass("hidden");
									$("#"+currentMenu+"").parent().parent().removeClass("hidden");
									$("#iframe").attr("src",""+$("#"+result.data[i].menu+"").attr("title")+"");
								}
								
							}
							
							
						}, "json")
					}
					
				})
			}
		})
	</script>
</body>
</html>

