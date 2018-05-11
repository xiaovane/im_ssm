/**
 * @add:各个页面需要的个性化js
 * @author:whai
 * @version:1.0
 */

// ====================begin userManage============================


$(function(){
	     // 新增页面数据省份数据绑定
		// 省份绑定
		$.post("../../clientPosition/clientPositonProvice.do",{
	　　　　}, function (data) {
	　　		     　var options=$("#province_code").combobox('options');  
	　　		    options.textField="provinceName";// 必须要和数据库查询的字段一样(大小写敏感)
	　　		    options.valueField="provinceId";  
	　　		    // 加载数据
				$("#province_code").combobox("loadData",data.data); 
	　　　　}, 'json');
		
		// 地级市绑定
		$("#province_code").combobox({
			onChange: function (n,o) {
				$.post("../../clientPosition/clientPositonCity.do",{
					"provinceId":$("#province_code").combobox("getValue"),
			　　　　}, function (data) {
			　　		     　var options=$("#city_code").combobox('options');  
			　　		    options.textField="cityName";// 必须要和数据库查询的字段一样(大小写敏感)
			　　		    options.valueField="cityId";  
			　　		    // 加载数据
						$("#city_code").combobox("loadData",data.data); 
			　　　　}, 'json');
			}

		});
		
		// 县级市绑定
		$("#city_code").combobox({
			onChange: function (n,o) {
				$.post("../../clientPosition/clientPositonCounty.do",{
					"cityId":$("#city_code").combobox("getValue"),
			　　　　}, function (data) {
			　　		     　var options=$("#county_code").combobox('options');  
			　　		    options.textField="countyName";// 必须要和数据库查询的字段一样(大小写敏感)
			　　		    options.valueField="countyId";  
			　　		    // 加载数据
						$("#county_code").combobox("loadData",data.data); 
			　　　　}, 'json');
			}

		});
		 
	
	  	   
	   // 请求获取相关信息
		if (getUrlParam("param") == 1) {
			$.post("../position/positionContent.do",{
			    "xiaoQuId":getUrlParam("xiaoQuId"),
		　　　　}, function (data) {
		　　			　$("#province_code").combobox("setValue",data.data.provinceName);
		　　			　$("#city_code").combobox("setValue",data.data.cityName);
		　　			　$("#county_code").combobox("setValue",data.data.countyName);
		　　			 $("#xiaoqu_code").val(data.data.xiaoQuId);
		　　			　$("#xiaoqu").val(data.data.xiaoQuName);
		　　　　}, 'json');
		}
		
		// 新增或者修改
		$("#btnSubmit").click(function(){
			var msg="信息新增";
			
			var url="../position/positionAdd.do";
			if (getUrlParam("param") == 1) {
				msg="信息修改";
				url="../position/positionModify.do";
			}

			//
			// 判断参数是否为空
			if ($("#xiaoqu").val()==null||$("#xiaoqu").val()=="") {				
				$.messager.alert("系统提示", "标题不能为空！");	
				return;
			}
			
			$.post(url,{
			    "countyId":$("#county_code").combobox("getValue"),
			    "xiaoQuId":$("#xiaoqu_code").val(),
			    "xiaoQuName":$("#xiaoqu").val()
		　　　　}, function (data) {
		　　			　　if (data.success) {
						$.messager.alert("系统提示", ""+msg+"成功！");				
					}else if(data.isExit){
						$.messager.alert("系统提示", "小区名称已经存在！");
					}
		　　			else {
						$.messager.alert("系统提示", ""+msg+"失败!");
					}
		　　　　}, 'json');
			
		});
		
		
	
})


　　
