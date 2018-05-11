
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
					<td width="80px">昵称：</td>
					<td><input type="text" id="title"
						style="width: 458px; height: 30px;" name="title" /></td>
				</tr>

				<tr>
					<td>图像：</td>
					<td><input type="file" id="image" name="image" /></td>
				</tr>

				<tr  id="pre" >
					<td width="80px">预览：</td>
					
					<td><img alt="请上传相关图片" id="img" style="width: 200px; height: 200px;"></td>
				</tr>

				<tr style="height: 50px">
                   <td><input type="hidden" id="guid" name="guid"></td>
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
			//判断是新增还是修改
			if (getUrlParam("param") == 1) {	
				$.post("../infoList/radomImgDetail.do",{
				    "guid":getUrlParam("guid")	
				   
			　　　　}, function (data) {
			　　			　$("#title").val(data.data.nickName);	
			　　			 $("#guid").val(data.data.guid);　　			
			　　			 $("#img").attr("src","/ssm/"+data.data.image+"");
			　　			 
			　　　　}, 'json');
			}

			//修改、新增提交
			$("#submitData").click(function(){
				var msg="信息新增";
				var url="../infoList/radomImgAdd.do";
				if (getUrlParam("param") == 1) {
					msg="信息修改";
					url="../infoList/radomImgUpdate.do";
				}
				// 判断参数是否为空
				if ($("#title").val()==null||$("#title").val()=="") {				
					$.messager.alert("系统提示", "昵称不能为空！");	
					return;
				}
				
				if ($("#image").val()==null||$("#image").val()=="") {				
					$.messager.alert("系统提示", "图片不能为空！");	
					return;
				}
				
				 $('#fm').form('submit',{
		                url:url,
		                onSubmit:function() {
		                    $('#title').attr('required',true);
		                    $('#image').attr('required',true);
		                    return $(this).form("validate");
		                },//进行验证，通过才让提交
		                success:function (result) {
		                    var result = JSON.parse(result);
		                    if (result.success) {
								$.messager.alert("系统提示", ""+msg+"成功！");				
							} else if (result.same){
								$.messager.alert("系统提示", "昵称已存在!");
							}
							else{
								$.messager.alert("系统提示", ""+msg+"失败!");
							}
		                }
		            });
			})
		})
	</script>

</body>
</html>
