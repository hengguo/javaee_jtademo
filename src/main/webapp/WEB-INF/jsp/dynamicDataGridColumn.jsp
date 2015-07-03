<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
			// 			url : '',
			pageSize : 30,
			remoteSort : false,
			pagination : true,
			rownumbers : true,
			singleSelect : true,
			queryParams : {
				nj : myNj,
				unitType : 1
			}
		});

// 		fetchData(myNj);
	});

	function fetchData(nj) {
		var s = "";
		s = "[[";
		s = s
				+ "{field:'id',title:'ID',width:160},{field:'name',title:'姓名',width:160},";

		//lu todo 列的定义可从服务器获得
		if (nj == 9) {
			s = s + "{field:'password',title:'密码',width:120}";

		} else if (nj == 7 || nj == 8) {
			s = s + "{field:'createTime',title:'创建时间',width:120}";
		}
		s = s + "]]";
		var options = {};
		options.url = '${ctx}/user/getDynamicUsers';
		options.queryParams = {
			nj : nj,
			unitType : 1
		};
		options.columns = eval(s);
		options.columns[0].push({
			field : 'desc',
			title : '查看详情',
			width : 60,
			formatter : function(value, rec) {
				return "<a href=\"javascript:showDescInfo(\'" + rec.id
						+ "\');\">详情</a>";
			}
		});

		$('#disgrid').datagrid(options);
// 		$('#disgrid').datagrid('reload');

	};

	function show(rec){
		fetchData(rec.value); 
	}
</script>
</head>
<body>
  <div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',title:'查询条件',border:false,collapsible:false"
      style="height: 120px; overflow: hidden;">
      <form id="searchForm">
        <table class="table table-hover table-condensed" style="width: 100%; padding: 7px 80px 0px 80px">
          <tr>
            <td width="10%"><input name="type" id="tt" class="easyui-combobox"
              data-options="valueField: 'value', textField: 'text', url: '${ctx}/code/getCombobox/dg',
               onSelect : function(rec){ show(rec) }"></td>
              
              
              <td><input type="button" value="Button"></td>
            <td width="10%" align="right"><a href="#" id="query" class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
          </tr>
        </table>
      </form>
    </div>
    <div id="toolbar" style="display: none;">

      <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-excel',plain:true"
        onclick="exportExcel();">导出</a>
    </div>
    <div data-options="region:'center',border:false">
      <table id="disgrid"></table>
    </div>
  </div>
</body>
</html>