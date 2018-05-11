/**
 * @add:各个页面需要的个性化js
 * @author:whai
 * @version:1.0
 */

// ====================begin userManage============================


$(function(){	
	   // 信息初始化
		$.post("../cardInfo/cardInfoDetail.do",{
		    "cardId":getUrlParam("cardId"),
	　　　　}, function (data) {
				// 列表页
	　　			　$("#c_title").val(data.data.title);
	　　			　$("#c_rate").combobox('setValue',data.data.money);
	　　			　$("#c_num").val(data.data.num);
	　　			　$("#c_startTime").datebox("setValue", ""+data.data.startTime+"");	　　			
	　　			  $("#c_endTime").datebox("setValue", ""+data.data.endTime+"");
	　　			  $("#c_desc").val(data.data.description);
	　　　　}, 'json');
		
		
		// 提交
		$("#btnSubmit").click(function(){
			
			// 标题验证
			if ($("#c_title").val()==""||$("#c_title").val()==null) {
				$.messager.alert("系统提示", "标题不能为空！");
				return;
			}
			
			// 数量验证
			if ($("#c_num").val()==""||$("#c_num").val()==null) {
				$.messager.alert("系统提示", "数量不能为空！");
				return;
			}
			// 日期验证
			if( $("#c_endTime").datebox("getValue")< $("#c_startTime").datebox("getValue")){
				$.messager.alert("系统提示", "开始时间不能大于结束时间！");
				return;
			}
			
			$.post("../cardInfo/cardInfoModify.do",{
			    "cardId":getUrlParam("cardId"),
			    "title":$("#c_title").val(),
			    "num":$("#c_num").val(),
			    "money":$("#c_rate").combobox('getValue'),
			    "startTime":$("#c_startTime").datebox("getValue"),
			    "endTime":$("#c_endTime").datebox("getValue"),
			    "description":$("#c_desc").val()
		　　　　}, function (data) {
		　　			　　if (data.success) {
						$.messager.alert("系统提示", "修改卡券成功！");				
					} else {
						$.messager.alert("系统提示", "修改卡券失败！");
					}
		　　　　}, 'json');
			
		});
		
		//提交审核
		
	$("#btnLook").click(function(){
		// 标题验证
		if ($("#c_title").val()==""||$("#c_title").val()==null) {
			$.messager.alert("系统提示", "标题不能为空！");
			return;
		}
		
		// 数量验证
		if ($("#c_num").val()==""||$("#c_num").val()==null) {
			$.messager.alert("系统提示", "数量不能为空！");
			return;
		}
		// 日期验证
		if( $("#c_endTime").datebox("getValue")< $("#c_startTime").datebox("getValue")){
			$.messager.alert("系统提示", "开始时间不能大于结束时间！");
			return;
		}
		
		//不通过时，原因不能为空
		if ($('input:radio:checked').val()=="-1"&&($("#c_reason").val()==""||$("#c_reason").val()==null)) {
			$.messager.alert("系统提示", "退回原因不能为空！");
			return;
		}
		
		//卡券审核
		$.post("../cardInfo/cardInfoLook.do",{
		    "cardId":getUrlParam("cardId"),
		    "lookStatus":$('input:radio:checked').val(),
		    "reason":$("#c_reason").val()
	　　　　}, function (data) {
	　　			　　if (data.success) {
					$.messager.alert("系统提示", "审核成功！");				
				} else {
					$.messager.alert("系统提示", "审核失败！");
				}
	　　　　}, 'json');
	})
})

	
	  // 通过和不通过选择
	 function selectNO(item){
		 if(item.value=="-1"){
			 $("#cardLook").removeClass("hidden");
		 }
		 else {
			 $("#cardLook").addClass("hidden");
			 $("#c_reason").val(null);
		}
	 };


　　
