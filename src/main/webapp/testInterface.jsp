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
					data-options="iconCls:'icon-submit'">api调试提交</a></td>
			</tr>
		</table>
	</div>





	<script type="text/javascript">
		/**
		 * 发布
		 */
		function submitData() {

			//ajax请求 请求后台写接口
			//==========注册前验证========
			/*$.post("${pageContext.request.contextPath}/clientUser/checkUser.do", { 
			'useType':'1',
			'userId' : 'vane1234567',
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
			
			//==========注册接口========
			/*$.post("${pageContext.request.contextPath}/clientUser/regist.do", {
				'useType':'1',
				'userId' : 'base54321',
				'mobile':'18362766710',
				'nickName':'普通用户',
				'pwd':'11111',
				'provice':'江苏省',
				'dcity':'苏州市',
				'xcity':'工业园区',				
				'device':'60FAB008-FE87-4C8F-A146-7B378CA0451A'	,*/
				
			//============客服注册===================
			/*$.post("${pageContext.request.contextPath}/clientUser/regist.do", {
				'useType':'3',
				'userId' : 'kefu000001',
				'nickName':'北京客服',
				'pwd':'11111',
				'provice':'北京市',
			
			//==========补充个人信息========
			/*$.post("${pageContext.request.contextPath}/clientUser/registUpdate.do", {
				'useType':'1',
				'userId' : 'base54321',
				'provice':'江苏省',
				'dcity':'苏州市',
				'xcity':'工业园区',
				'village':'松泽家园',
				'building':'97楼',
				'unit':'第二单元',
			
			//=======登录接口============
			/*$.post("${pageContext.request.contextPath}/clientUser/login.do", {
			'user' : '123456789w',
			'pwd':'123456789w',
			'device':'x00l2017'	,

			//=======修改密码============
			/*$.post("${pageContext.request.contextPath}/clientUserOperation/modifyPwd.do", {
			'userId' : 'test0007',
			'pwd':'test3333',
			'newPwd':'test3334',
			'checkPwd':'test3334',*/
				
			//=======修改昵称============
			/*$.post("${pageContext.request.contextPath}/clientUserOperation/modifyNickName.do", {
			'userId' : 'test003',
			'nickName':'辰',*/
			
			//=======添加好友============
			 /*$.post("${pageContext.request.contextPath}/clientFriend/addFriend.do", {
			'userId' : 'aaa123456434',
			'friendId':'test000001',

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
			'userId' : '123456w',
			'friendId':'12345678w',*/
			
			//=======创建群组============
			/*$.post("${pageContext.request.contextPath}/clientUserGroup/createGroup.do", {			
			'groupNickName':'代码创建',			
			'createName':'wh1234567',
			'groupSize':'50',
			'groupLevel':'1',
			'createTime':'2017-10-21',
			'userId':'ceshi1,ceshi2,ceshi3,wh1234567',
			
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
			/*$.post("${pageContext.request.contextPath}/clientUserGroup/addGroupBatch.do", {
			'groupId' : '34581084438530',
			'userId':'12345678w,123456a'*/
			
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
			'pageIndex' : '2',
			'pageSize':'10',
			'categoryNum':'001',*/

			//=======短信发送测试============
			/*$.post("${pageContext.request.contextPath}/clientUser/smsSend.do",{
			 'mobile' : '15250011879',*/
			 
		   //=======短信发送测试============
			/*$.post("${pageContext.request.contextPath}/clientUser/smsSendBacth.do",{
			 'mobile' : '15250011879,13013755088',
			
			
			//=======验证码测试============
			/*$.post("${pageContext.request.contextPath}/clientUser/smsCode.do", {
				'mobile' : '18297986439',
				'code' : '4607',*/

			//========创建问卷测试==============
			$.post("${pageContext.request.contextPath}/clientVote/createVote.do", {
			'title' : 'ceshi',
			'groupId':'36042716545025',
			'createId' : '12345w',
			'voteId' : '18012011564910',
			'choiceId' : '180120115649110:测试1,18012011564951:测试2',
			'startTime' : '2018-01-20',
			'endTime' : '2018-01-29',

			//========投票测试==============
			/*$.post("${pageContext.request.contextPath}/clientVote/voteInfo.do", {				
			'choiceId' : '007001',
			'userId' : '008',*/

			//========创建卡券测试=============
			/*$.post("${pageContext.request.contextPath}/clientCard/clientCreateCard.do", {				
			'userId' : '12345w',
			'money':'550',
			'startTime':'2017-10-11',
			'endTime':'2018-12-01',
			'num':'50',
			'description':'卡券简介',
			'cardId':'002001',
            'city':'苏州'
			
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
			
			//=======取出小区============
			/*$.post("${pageContext.request.contextPath}/clientPosition/clientPositonXiaoQu.do", {
			'townId' : '320506103000',*/
			
			//=======搜索小区============
			/*$.post("${pageContext.request.contextPath}/clientPosition/clientSearchXiaoQu.do", {
			'countyId' : '110101000000',
			'xiaoQuName' : '',
			
			//=======查找好友============
			/*$.post("${pageContext.request.contextPath}/clientUserOperation/searchFriendList.do", {
			'user' : '123456w',
			'userId' : '12345w',*/
			
			//=======获取好友列表============
			/*$.post("${pageContext.request.contextPath}/clientUserOperation/getFriendList.do", {
			'userId' : '12345w',
			
			//=======获取客服列表============
			/*$.post("${pageContext.request.contextPath}/clientUserOperation/getKeFu.do", {
			'provice' : '北京市',
			
			//=======领取卡券============
			/*$.post("${pageContext.request.contextPath}/clientUserCard/userCardClick.do", {
			'userId' : 'test0007',
			'cardId' : '002001',*/
			
			//=======用户卡券列表============
			/*$.post("${pageContext.request.contextPath}/clientUserCard/userCardList.do", {
			'userId' : '123456w',
			'pageIndex' : '1',
			'pageSize' : '20',*/
			
			//赠送好友卡券
			/*$.post("${pageContext.request.contextPath}/clientUserCard/giveCardList.do", {
			'userId' : 'vane123',
			'friendId' : '12345w',
			'cardId' : '1712251605409,17122516194511',

			//=======用户卡券详细页面============
			/*$.post("${pageContext.request.contextPath}/clientUserCard/userCardDetail.do", {
			'userId' : 'test0007',
			'cardId' : '002001',*/
			
			//=======上传头像============
			/*$.post("${pageContext.request.contextPath}/clientUserOperation/modifyPersonInfo.do", {
			'userId' : 'test0007',
			'userImage' : '4f7261636c653ab6febdf8d6c6b6d4cff3d0b4c8eb4442c8d5d6be3a2032',
			
			//=======隐藏个人信息============
			/*$.post("${pageContext.request.contextPath}/clientUserOperation/hiddenInfo.do", {
			'userId' : 'test0007',*/
			
			//=======投诉反馈============
			/*$.post("${pageContext.request.contextPath}/clientFriend/feedBack.do", {
			'type' : '1',
			'userId' : 'test0007',
			'friendId' : 'test0008',
			'content' : '投诉你',*/
			
			/*$.post("${pageContext.request.contextPath}/clientFriend/feedBack.do", {
			'type' : '2',
			'userId' : 'test0007',
			'content' : '系统反馈2',
			
			//=======设置星标好友============
			/*$.post("${pageContext.request.contextPath}/clientFriend/setStar.do", {
			'userId' : '12345w',
			'friendId' : '123456w',*/
			
			//=======取消星标好友============
			/*$.post("${pageContext.request.contextPath}/clientFriend/setStarNO.do", {
				'userId' : '12345w',
				'friendId' : '123456w',
			
			// ======给好友设置备注名======
			/*$.post("${pageContext.request.contextPath}/clientFriend/setBakName.do", {
			'userId' : '123456w',
			'friendId' : '12345678w',
			'bakName' : 'nihao',*/
			
			
			//=======扫码卡券============
			/*$.post("${pageContext.request.contextPath}/clientCard/scanCard.do", {
			'userId' : 'test0007',
			'cardId' : '002001',
			'sellerId' : '123456w',
			'pwd' : 'xxx',*/
			
			//=======获取全部商户发布的卡券列表============
			/*$.post("${pageContext.request.contextPath}/clientCard/sellerCardList.do", {				
				'city':'苏州',
				'pageIndex' : '1',
				'pageSize' : '10',
			//=======获取当前商户商户发布的卡券列表============
			/*$.post("${pageContext.request.contextPath}/clientCard/getCardList.do", {				
				'userId':'12345',
				'pageIndex' : '1',
				'pageSize' : '10',
			
			//=======问卷调查列表============
			/*$.post("${pageContext.request.contextPath}/clientVote/voteInfoList.do", {
				'groupId' : '001',
				'pageIndex' : '1',
				'pageSize' : '10',*/
				
			//=======获取问卷调查统计============
			/*$.post("${pageContext.request.contextPath}/clientVote/voteStatistics.do", {
				'voteId' : '002',*/
				
			//=====重置密码==========
			/*$.post("${pageContext.request.contextPath}/clientUserOperation/restPwd.do", {
		    'mobile' : '18362766707',
			'newPwd':'12345678w',
			'checkPwd':'12345678w',*/
			
			//=====获取常见问题和投诉列表1 是投诉；2是常见问题==========
			/*$.post("${pageContext.request.contextPath}/infoList/clientInfoList.do", {
		    'type' : '1',
			'pageIndex':'1',
			'pageSize':'10',*/
			
			//=====获取随机昵称和图片==========
			/*$.post("${pageContext.request.contextPath}/infoList/clientRadomNickName.do", {		   
			*/	
			//=======获取系统群============
			/*$.post("${pageContext.request.contextPath}/clientUserOperation/getAdminGroupList.do", {
			'userId' : '12345w',*/
			//=======获取群详细
			/*$.post("${pageContext.request.contextPath}/clientUserOperation/getAdminGroupDetail.do", {
			'groupId' : '35962989117441',*/
				
			}, function(result) {

				alert(result.msg);

			}, "json");
		}
	</script>
</body>
</html>
