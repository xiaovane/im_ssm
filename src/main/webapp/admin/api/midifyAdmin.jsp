
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改人信息</title>
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
    <div id="p" class="easyui-panel" title="修改人信息" style="padding: 10px">
        <form method="post" id="fm" enctype="multipart/form-data">
            <table cellspacing="20px">
                <tr>
                    <td width="80px">用户名：</td>
                    <td>
                        <input type="hidden" id="id" name="id" value="${UserAdmin.id }"/>
                        <input type="text" id="userName" name="userName" style="width:200px;height:30px" readonly="readonly" value="${UserAdmin.userName }"/>
                    </td>
                </tr>
                <tr id="pwd">
                    <td>密码：</td>
                    <td>
                        <input type="password" id="password" name="password" style="width:200px;height:30px"
                              />
                    </td>
                </tr>
                <tr>
                    <td>昵称：</td>
                    <td>
                        <input type="text" id="nickName" name="nickName" style="width:200px;height:30px"
                              />
                    </td>
                </tr>
                <tr>
                    <td>个性签名：</td>
                    <td>
                        <input type="text" id="sign" name="sign" style="width:200px;height:30px"
                                />
                    </td>
                </tr>
                <tr style="display:none">
                    <td>个人头像：</td>
                    <td>
                        <input type="file" id="imageFile" name="imageFile"/>
                    </td>
                </tr>
                <tr>
                    <td>个人简介：</td>
                    <td>
                        <script id="profile" type="text/plain" style="width:95%; height:300px;"></script>
                        <input type="hidden" id="pf" name="profile"> <%-- UEditor不能作为表单的一部分提交，所以用这种隐藏域的方式 --%>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td><a id="submitData" class="easyui-linkbutton"
                           data-options="iconCls:'icon-submit'">提交</a></td>
                </tr>
            </table>
        </form>
    </div>
<script>
    
    $(function() {

			//页面数据初始化
			var ue = UE.getEditor('profile');
			//判断是新增还是修改
			if (getUrlParam("param") == 1) {
				$("#pwd").css("display","none");
				ue.addListener('ready', function() {
					UE.ajax.request(
							'${WebUser}/admin/UserAdmin/getUserAdminInfo.do',
							{
								method : "post",
								async : false,
								data : {
									"userName" : getUrlParam("userName")
								},
								onsuccess : function(result) {
									result = JSON.parse(result.responseText);
										$("#userName").val(result.userName);
										$("#password").val(result.password);
									 	$("#nickName").val(result.nickName);
						                $("#sign").val(result.sign);
						                UE.getEditor('profile').setContent(result.profile);
								}
							})
				})
			}

			//修改、新增提交
			$("#submitData").click(function(){
				var msg="信息新增";
				var url="../UserAdmin/addUserAdmin.do";
				if (getUrlParam("param") == 1) {
					msg="信息修改";
					url="../UserAdmin/saveUserAdmin.do";
					
				}
				// 判断参数是否为空
				if ($("#userName").val()==null||$("#userName").val()=="") {				
					$.messager.alert("系统提示", "用户名不能为空！");	
					return;
				}
				
				if ($("#password").val()==null||$("#password").val()=="") {				
					$.messager.alert("系统提示", "密码不能为空！");	
					return;
				}
				
				$.post(url,{
				    "userName":$("#userName").val(),				    
		　			"password":$("#password").val(),
		 			"nickName":$("#nickName").val(),
		 			"sign": $("#sign").val(),
		　			"profile":　UE.getEditor("profile").getContent()
			　　　　}, function (data) {
			　　			　　if (data.success) {
							$.messager.alert("系统提示", ""+msg+"成功！");				
						} 
			　　			 else if(data.isExit){
			　　				 $.messager.alert("系统提示", "用户名已存在!");
			　　			 }
			　　			else {
							$.messager.alert("系统提示", ""+msg+"失败!");
						}
			　　　　}, 'json');
			})
		})
</script>

</body>
</html>
