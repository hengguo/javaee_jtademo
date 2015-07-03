<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="inc/header.jsp"%>
<%@ include file="inc/resource.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
	function cellStyler(value, row, index) {
		if (row.name == "总计（元）：") {
			return 'background-color:#ffee00;color:red;';
		}
	};
	var options = {};
	$(function() {
		//初始化
		$("#disgrid")
				.datagrid(
						{
							type : 'POST',
							nowrap : false,
							striped : true,
							fit : true,
							width : 1024,
							height : 500,
							url : '${ctx}/user/getFooter',
							pageSize : 30,
							remoteSort : false,
							pagination : true,
							rownumbers : true,
							singleSelect : true,
							showFooter : true,
							columns : [ [
									{
										field : 'id',
										title : 'ID',
										width : 160,
										editor : 'numberbox'
									},{
										field : 'name',
										title : '姓名',
										width : 160,
										styler : cellStyler,
										editor : 'text'
									},{
										field:'password',
										title:'密码',
										styler : cellStyler,
										width:120
									}, {
										field : 'action',
										title : '修改',
										width : 350,
										align : 'center',
										formatter : function(value, row, index) {
											if(row.name!= "总计（元）：")
												return "修改";
										}
									}] ]
						});
		$("#disgrid").datagrid('options');
	});
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div
			data-options="region:'north',title:'查询条件',border:false,collapsible:false"
			style="height: 120px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed"
					style="width: 100%; padding: 7px 80px 0px 80px">
					<tr>
						<td width="10%" align="right"><a href="#" id="query"
							class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
					</tr>
				</table>
			</form>
		</div>
		<div data-options="region:'center',border:false">
			<table id="disgrid"></table>
		</div>
	</div>
</body>
</html>