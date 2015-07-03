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
	var options = {};
	$(function() {
		var myNj = 8;
		//初始化
		$("#disgrid").datagrid({
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
			columns : [[{
				field:'id',
				title:'ID',
				width:160,
				editor:'numberbox'
			},{
				field:'name',
				title:'姓名',
				width:160,
				editor:'text'
			}, {field:'action',title:'Action',width:70,align:'center',
                formatter:function(value,row,index){
                    if (row.editing){
                        var s = '<a href="#" onclick="saverow(this)">Save</a> ';
                        var c = '<a href="#" onclick="cancelrow(this)">Cancel</a>';
                        return s+c;
                    } else {
                        var e = '<a href="#" onclick="editrow(this)">Edit</a> ';
                        return e;
                    }
                }
            }
			]],
	        onBeforeEdit:function(index,row){
	            row.editing = true;
	            updateActions(index);
	        },
	        onAfterEdit:function(index,row){
	            row.editing = false;
	            updateActions(index);
	        },
	        onCancelEdit:function(index,row){
	            row.editing = false;
	            updateActions(index);
	        },
			queryParams : {
				nj : 9,
				unitType : 1
			}
		});
		$("#disgrid").datagrid('options');

	});
	function updateActions(index){
	    $('#disgrid').datagrid('updateRow',{
	        index: index,
	        row:{}
	    });
	};
	function getRowIndex(target){
	    var tr = $(target).closest('tr.datagrid-row');
	    return parseInt(tr.attr('datagrid-row-index'));
	};
	function editrow(target){
	    $('#disgrid').datagrid('beginEdit', getRowIndex(target));
	};
	function deleterow(target){
	    $.messager.confirm('Confirm','Are you sure?',function(r){
	        if (r){
	            $('#disgrid').datagrid('deleteRow', getRowIndex(target));
	        }
	    });
	};
	function saverow(target){
	    $('#disgrid').datagrid('endEdit', getRowIndex(target));
	};
	function cancelrow(target){
	    $('#disgrid').datagrid('cancelEdit', getRowIndex(target));
	}
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