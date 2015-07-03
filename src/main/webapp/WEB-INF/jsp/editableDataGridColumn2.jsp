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
	$.extend($.fn.datagrid.methods, {
		editCell : function(jq, param) {
			return jq.each(function() {
				var opts = $(this).datagrid('options');
				var fields = $(this).datagrid('getColumnFields', true).concat(
						$(this).datagrid('getColumnFields'));
				for (var i = 0; i < fields.length; i++) {
					var col = $(this).datagrid('getColumnOption', fields[i]);
					col.editor1 = col.editor;
					if (fields[i] != param.field) {
						col.editor = null;
					}
				}
				$(this).datagrid('beginEdit', param.index);
				for (var i = 0; i < fields.length; i++) {
					var col = $(this).datagrid('getColumnOption', fields[i]);
					col.editor = col.editor1;
				}
			});
		}
	});

	var editIndex = undefined;
	function endEditing() {
		if (editIndex == undefined) {
			return true
		}
		if ($('#disgrid').datagrid('validateRow', editIndex)) {
			$('#disgrid').datagrid('endEdit', editIndex);
			editIndex = undefined;
			return true;
		} else {
			return false;
		}
	};
	function onClickCell(index, field) {
		if (endEditing()) {
			$('#disgrid').datagrid('selectRow', index).datagrid('editCell', {
				index : index,
				field : field
			});
			editIndex = index;
		}
	};
	var options = {};
	$(function() {
		var myNj = 8;
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
							url : '${ctx}/user/getDynamicUsers',
							pageSize : 30,
							remoteSort : false,
							pagination : true,
							rownumbers : true,
							singleSelect : true,
							onClickCell: onClickCell,
							columns : [ [
									{
										field : 'id',
										title : 'ID',
										width : 160,
										editor : 'numberbox'
									},
									{
										field : 'name',
										title : '姓名',
										width : 160,
										editor : 'text'
									}] ],
							queryParams : {
								nj : 9,
								unitType : 1
							}
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
		<div id="toolbar" style="display: none;">

			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-excel',plain:true"
				onclick="exportExcel();">导出</a>
		</div>
		<div data-options="region:'center',border:false">
			<table id="disgrid"></table>
		</div>
	</div>
</body>
</html>