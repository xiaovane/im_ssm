<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>测试接口</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/static/ueditor1_4_3_3/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/static/ueditor1_4_3_3/ueditor.all.min.js">
	
</script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/static/ueditor1_4_3_3/lang/zh-cn/zh-cn.js"></script>
<script
	src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery-1.11.2.min.js"></script>


</head>

<body style="margin: 10px; font-family: microsoft yahei">

	<div id="p" class="easyui-panel" title="编写" style="padding: 10px;">

		<table cellspacing="20px">

			<tr>
				<td></td>
				<td><a href="javascript:submitData()" class="easyui-linkbutton"
					data-options="iconCls:'icon-submit'">提交测试api</a></td>
			</tr>
		</table>
	</div>





	<script type="text/javascript">
		/**
		 * 发布
		 */
		function submitData() {

			//ajax请求 请求后台写接口
			/*$.post("${pageContext.request.contextPath}/clientUser/regist.do", {
			
			 //==========注册接口========
			'useType':'1',
			'userId' : '1234567a',
			'mobile':'15010802405',
			'nickName':'vane',
			'pwd':'123456a',
			'provice':'江苏',
			'dcity':'苏州',
			'xcity':'吴中区',
			'qcity':'工业园区',
			'village':'松泽家园',
			'building':'9区',
			'unit':'96幢',
			'room':'505室',
			'device':'x00l2017'	,*/

			//=======登录接口============
			/*$.post("${pageContext.request.contextPath}/clientUser/login.do", {
			'user' : '1234567a',
			'pwd':'123456a',
			'device':'x00l2017'*/

			//=======修改密码测试============
			/*$.post("${pageContext.request.contextPath}/clientUserOperation/modifyPwd.do", {
			'userId' : 'test0007',
			'pwd':'test3333',
			'newPwd':'test3334',
			'checkPwd':'test3334',*/
				
			//=======修改昵称测试============
			/*$.post("${pageContext.request.contextPath}/clientUserOperation/modifyNickName.do", {
			'userId' : 'test003',
			'nickName':'辰',*/
			
			//=======添加好友============
			/* $.post("${pageContext.request.contextPath}/clientFriend/addFriend.do", {
			'userId' : 'aaa123456434',
			'friendId':'test000001',*/

			//=======删除好友============
			/*$.post("${pageContext.request.contextPath}/clientFriend/deleteFriend.do", {
			'userId' : 'aaa123456434',
			'friendId':'test000001',*/
			
			//=======加入黑名单============
			/*$.post("${pageContext.request.contextPath}/clientFriend/addBlackFriend.do", {
			'userId' : 'aaa123456434',
			'friendId':'test000001',*/
			
			//=======移出黑名单============
			/*$.post("${pageContext.request.contextPath}/clientFriend/removeBlackFriend.do", {
			'userId' : 'aaa123456434',
			'friendId':'test000001',*/
			
			//=======给好友点赞============
			/*$.post("${pageContext.request.contextPath}/clientFriend/thumbFriend.do", {
			'userId' : 'aaa123456434',
			'friendId':'test003',*/
			
			//=======创建群组============
			/*$.post("${pageContext.request.contextPath}/clientUserGroup/createGroup.do", {
			'groupId' : 'test00009',
			'groupNickName':'代码创建',
			'description':'代码创建',
			'createName':'aaa123456344',
			'groupSize':'50',
			'groupLevel':'1',
			'createTime':'2017-10-21',*/
			
			//=======删除群组============
			/*$.post("${pageContext.request.contextPath}/clientUserGroup/deleteUserGroup.do", {
			'groupId' : 'aaa123456434',*/
			
			//=======修改群组============
			/*$.post("${pageContext.request.contextPath}/clientUserGroup/modifyGroup.do", {
			'groupId' : '32144701325313',
			'groupNickName':'jianjie',
			'description':'简介123',
			'createName':'test001',
			'groupSize':'50',
			'groupImage':'34444',*/
			
			//=======申请加入群组============
			/*$.post("${pageContext.request.contextPath}/clientUserGroup/addGroup.do", {
			'groupId' : '32144701325313',
			'userId':'test0005',*/
			
			//=======退出群组============
			/*$.post("${pageContext.request.contextPath}/clientUserGroup/deleteGroup.do", {
			'groupId' : '32144701325313',
			'userId':'test0005',*/
			
			//=======加入黑名单============
			/*$.post("${pageContext.request.contextPath}/clientUserGroup/addBlackGroupUser.do", {
			'groupId' : '32144701325313',
			'userId':'test0005',*/
			
			//=======退出黑名单============
			/*$.post("${pageContext.request.contextPath}/clientUserGroup/deleteBlackGroupUser.do", {
			'groupId' : '32144701325313',
			'userId':'test0005',*/
			
			
			//======信息抓取测试=====
			/*$.post("${pageContext.request.contextPath}/clientCaputeInfo/clientCaptureNews.do", {*/
			
			//=======获取抓取的信息============
			/*$.post("${pageContext.request.contextPath}/clientCaputeInfo/clientNewsList.do", {
			'pageIndex' : '1',
			'pageSize':'10',
			'categoryNum':'001',*/

			//=======短信发送测试============
			$.post("${pageContext.request.contextPath}/clientUser/smsSend.do",{
			 'mobile' : '18297986439',		
			
			
			//=======验证码测试============
			/*$.post("${pageContext.request.contextPath}/clientUser/smsCode.do", {
				'mobile' : '15250011879',
				'code' : '4950',*/

			//========创建问卷测试==============
			/* $.post("${pageContext.request.contextPath}/clientVote/createVote.do", {
			'title' : '关于当选楼长的投票选举',
			'createId' : '006',
			'voteId' : '007',
			'choiceId' : '007001:同意,007002:不同意,007003:选自己,007004:选其他',
			'startTime' : '2017-10-31',
			'endTime' : '2017-11-30',*/

			//========投票测试==============
			/*$.post("${pageContext.request.contextPath}/clientVote/voteInfo.do", {				
			'choiceId' : '007001',
			'userId' : '008',*/

			//========卡券测试=============
			/*$.post("${pageContext.request.contextPath}/clientCard/clientCreateCard.do", {				
			'userId' : '002',
			'money':'550',
			'startTime':'2017-10-11',
			'endTime':'2017-12-01',
			'num':'50',
			'description':'卡券简介',
			'cardId':'002001',*/
			
			//========获取审核通过的卡券列表==============
			/*$.post("${pageContext.request.contextPath}/clientCard/clientCardList.do", {				
			'userId' : '002',
			'pageIndex' : '1',
			'pageSize' : '10',*/
						
			//========获取审核通过的卡券详细表==============
			/*$.post("${pageContext.request.contextPath}/clientCard/clientCardDetail.do", {				
			'userId' : '002',
			'cardId' : '002001'*/
	
			//=======密码加密============
			/*$.post("${pageContext.request.contextPath}/clientUserOperation/md5.do", {
			'pwd' : '123',*/
		
			//=======取出全国省份============
			/*$.post("${pageContext.request.contextPath}/clientPosition/clientPositonProvice.do", {
			*/
			
			//=======取出地级市============
			/*$.post("${pageContext.request.contextPath}/clientPosition/clientPositonCity.do", {
			'provinceId' : '130',*/
			
			//=======取出区县============
			/*$.post("${pageContext.request.contextPath}/clientPosition/clientPositonCounty.do", {
			'cityId' : '130100000000',*/
			
			//=======取出街道============
			/*$.post("${pageContext.request.contextPath}/clientPosition/clientPositonTown.do", {
			'countyId' : '130102000000',*/
			
			//=======查找好友列表============
			/*$.post("${pageContext.request.contextPath}/clientUserOperation/searchFriendList.do", {
			'user' : '15010802405',*/
			
			//=======领取卡券============
			/*$.post("${pageContext.request.contextPath}/clientUserCard/userCardClick.do", {
			'userId' : 'test0007',
			'cardId' : '002001',*/
			
			//=======用户卡券列表============
			/*$.post("${pageContext.request.contextPath}/clientUserCard/userCardList.do", {
			'userId' : 'test0007',*/

			//=======用户卡券详细页面============
			/*$.post("${pageContext.request.contextPath}/clientUserCard/userCardDetail.do", {
			'userId' : 'test0007',
			'cardId' : '002001',*/
			
			//=======问卷调查列表============
			/*$.post("${pageContext.request.contextPath}/clientVote/voteInfoList.do", {
			'userId' : 'test0007',
			'cardId' : '002001',*/
			}, function(result) {

				alert(result.msg);

			}, "json");
		}
	</script>
</body>
</html>
