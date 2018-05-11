/**
 * @add:各个页面需要的个性化js
 * @author:whai
 * @version:1.0
 */

// ====================begin feedBackManage============================
$(function(){
	//初始化
	var time=new Date();
	dgCardLoad("../cardInfo/cardInfoList.do","",2,"2010/10/10",time.toLocaleDateString());
	
})

function dgCardLoad(url,title,lookStatus,startTime,endTime){	
	// 获取datagrid的实例
	$("#dg").datagrid({  
	    url:url,  
	    pageSize:10,  
	    queryParams: {  
	    	"title":title,
		    "lookStatus":lookStatus,
		    "startTime":startTime,
		    "endTime":endTime,
	    },  
	   
	});  
}
// 自定义选项列表操作
function formatOperation(val, row) {
	var content = "<option ></option>";
	content += "<option value=\"cardInfoModify.jsp?cardId=" + row.cardId
			+ "&param=1\" >修改卡券</option>";
	content += "<option value=\"cardInfoLook.jsp?cardId=" + row.cardId
			+ "&param=1\" >审核卡券</option>";
	var html = "<select id=\"opr\" class=\"icon-item\"   onchange=\"oprFunc(this,'cardInfo')\">"
			+ content + "</select>";

	return html;
}


// 标题、状态、时间进行查询
function searchCard() {
	var time=new Date();
	var lookStatus=$("#c_cardType").combobox('getValue');
	var statTime=$("#c_startTime").datebox("getValue");
	var endTime=$("#c_endTime").datebox("getValue");
	if (lookStatus==""||lookStatus==null||lookStatus=="请选择类型") {
		lookStatus=1;
	}
	if (statTime==""||statTime==null) {
		statTime="2010/10/10";
	}
	if (endTime==""||endTime==null) {
		endTime=time.toLocaleDateString();
	}
	dgCardLoad("../cardInfo/cardInfoList.do",$("#c_cardTitle").val(),lookStatus,statTime,endTime);
}
