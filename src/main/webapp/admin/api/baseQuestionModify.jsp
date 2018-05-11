
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>常见问题</title>
<%@include file="../common/head.jspf"%>
<script type="text/javascript" charset="utf-8"
	src="${WebUser}/static/ueditor1_4_3_3/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${WebUser}/static/ueditor1_4_3_3/ueditor.all.min.js">
	
</script>
<script type="text/javascript" src="../../static/api/js/common.js"></script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8"
	src="${WebUser}/static/ueditor1_4_3_3/lang/zh-cn/zh-cn.js"></script>

</head>
<body>
	<div id="p" class="easyui-panel" title="常见问题">
		<form method="post" id="fm" enctype="multipart/form-data">
			<table cellspacing="20px">
				<tr>
					<td>类型：</td>
					<td><select class="easyui-combobox"
						style="width: 158px; height: 30px;" id="type">
							<option value='1'>好友投诉</option>
							<option value='2'>常见问题</option>
							<option value="3">保密条款</option>
					</select></td>
				</tr>
				<tr>
					<td width="80px">标题：</td>
					<td><input type="text" id="title"
						style="width: 458px; height: 30px;" /></td>
				</tr>


				<tr style="display:none">
					<td>图像：</td>
					<td><input type="file" id="imageFile" name="imageFile" /></td>
				</tr>
				<tr>
					<td>内容：</td>
					<td><script id="content" type="text/plain"
							style="width: 95%; height: 300px;"></script> <input type="hidden"
						id="pf" name="content"> <%-- UEditor不能作为表单的一部分提交，所以用这种隐藏域的方式 --%>
					</td>
				</tr>
				<tr>
					<td></td>
					<td><a class="easyui-linkbutton"
						data-options="iconCls:'icon-submit'" id="submitData">提交</a></td>
				</tr>
			</table>
		</form>
	</div>
	<script>
		$(function() {

			//页面数据初始化
			var ue = UE.getEditor('content');
			//判断是新增还是修改
			if (getUrlParam("param") == 1) {				
				ue.addListener('ready', function() {
					UE.ajax.request(
							'${WebUser}/admin/infoList/baseQuestionDetail.do',
							{
								method : "post",
								async : false,
								data : {
									"infoId" : getUrlParam("infoId")
								},
								onsuccess : function(result) {
									result = JSON.parse(result.responseText);
									$("#type").combobox('setValue',
											result.data.type);
									$("#title").val(result.data.title);
									UE.getEditor('content').setContent(
											result.data.content);
								}
							})
				})
			}

			//修改、新增提交
			$("#submitData").click(function(){
				var msg="信息新增";
				var url="../infoList/baseQuestionAdd.do";
				if (getUrlParam("param") == 1) {
					msg="信息修改";
					url="../infoList/baseQuestionUpdate.do";
				}
				// 判断参数是否为空
				if ($("#title").val()==null||$("#title").val()=="") {				
					$.messager.alert("系统提示", "标题不能为空！");	
					return;
				}
				
				$.post(url,{
				    "infoId":getUrlParam("infoId"),				    
		　			"type":$("#type").combobox('getValue'),
		 			"title":$("#title").val(),
		　			"content":　UE.getEditor("content").getContent()
			　　　　}, function (data) {
			　　			　　if (data.success) {
							$.messager.alert("系统提示", ""+msg+"成功！");				
						} else {
							$.messager.alert("系统提示", ""+msg+"失败!");
						}
			　　　　}, 'json');
			})
		})
	</script>

</body>
</html>
