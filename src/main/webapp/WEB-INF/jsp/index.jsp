<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="inc/header.jsp"%>
<%@ include file="inc/resource.inc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>SpringMVC EasyUI测试系统</title>
<script type="text/javascript">
	var t;
	var tm;
	function closeAllTabs(t){
		var allTabs = t.tabs('tabs');
		var closeTabsTitle = [];
		$.each(allTabs, function() {
			var opt = this.panel('options');
			closeTabsTitle.push(opt.title);
		});
		for ( var i = 0; i < closeTabsTitle.length; i++) {
			t.tabs('close', closeTabsTitle[i]);
		}
	}
	function add(url, treeNode) {
		if (t.tabs("exists", treeNode.name)) {
			t.tabs("select", treeNode.name);
		} else {
			parent.$.messager.progress({
				title : '提示',
				text : '数据处理中，请稍候....'
			});
			t.tabs("add", {
					title : treeNode.name,
					content : "<iframe src='"
							+ url
							+ "' frameborder='0' style='border:0;width:100%;height:100%;'></iframe>",
					cache : true,
					closable : true,
					selected : true,
					border : false,
					fit : true
				});
			parent.$.messager.progress('close');
		}
	}

	function addTab(url, name){
		if (t.tabs("exists", name)) {
			t.tabs("select", name);
		}else{
			parent.$.messager.progress({
				title : '提示',
				text : '数据处理中，请稍候....'
			});
			closeAllTabs(t);
			t.tabs("add", {
				title : name,
				content : "<iframe src='" + url +"' frameborder='0' style='border:0;width:100%;height:100%;'></iframe>",
				cache : true,
				closable : true,
				selected : true,
				border : false,
				fit : true
			});
			parent.$.messager.progress('close');
		}	
	}
	
	$(function() {
		t = $('#tabs').tabs({
			fit : true,
			border : false,
			onContextMenu : function(e, title) {
				e.preventDefault();
				tm.menu("show", {
					left : e.pageX,
					top : e.pageY
				}).data("tabTitle", title);
			}
		});
		tm = $('#tabsMenu').menu({
			onClick : function(item) {
				var curTabTitle = tm.data('tabTitle');
				var type = $(item.target).attr('title');

				if (type === 'refresh') {
					t.tabs('getTab', curTabTitle).panel('refresh');
					return;
				}

				if (type === 'close') {
					var ct = t.tabs('getTab', curTabTitle);
					if (ct.panel('options').closable) {
						t.tabs('close', curTabTitle);
					}
					return;
				}

				var allTabs = t.tabs('tabs');
				var closeTabsTitle = [];

				$.each(allTabs, function() {
					var opt = this.panel('options');
					if (opt.closable && opt.title != curTabTitle && type === 'closeOther') {
						closeTabsTitle.push(opt.title);
					} else if (opt.closable && type === 'closeAll') {
						closeTabsTitle.push(opt.title);
					}
				});

				for ( var i = 0; i < closeTabsTitle.length; i++) {
					t.tabs('close', closeTabsTitle[i]);
				}
			}
		});
	});

</script>
</head>
<body class="easyui-layout" style="text-align: left">
  <div region="north" border="false" style="background: #666; text-align: center">
    <div id="header-inner">
      <table cellpadding="0" cellspacing="0" style="width: 100%;">
        <tr>
          <td rowspan="2" style="width: 20px;"></td>
          <td style="height: 60px;" rowspan="2">
            <div style="color: #fff; font-size: 22px; font-weight: bold;">
              <a href="javascript:void();"
                style="color: #fff; font-size: 22px; font-weight: bold; text-decoration: none">SpringMVC easyui!测试系统</a>
            </div>
          </td>
        </tr>
      </table>
    </div>

  </div>

  <div data-options="region:'south',split:false" style="height: 25px;"></div>

  <div region="west" split="false" title="导航菜单" style="width: 250px;">

    <div class="easyui-accordion" id="left_nav" fit="true" border="false"></div>

  </div>
  <div region="center">
    <div id="tabs" class="easyui-tabs" fit="true" border="false" plain="true">
      <div title="首页" data-options="border:false" style="overflow: hidden;">
        <iframe src="${ctx}/index/getMain" id="indexFrame" frameborder="0" style="border: 0; width: 100%; height: 100%;"></iframe>
      </div>
    </div>
  </div>

</body>
<script type="text/javascript">

  var onlyOpenTitle="welcome";

  window.onload = function() {
    showProgressing();
    $.ajax({
            type: "POST",
            url:"${ctx}/json/index.json",
      async : true,
      error : function(request) {
        closeProgressing();
        alert("发送请求错误!");
      },
      success : function(rows) {
        rows = convert(rows);
        //console.log(rows);
        InitLeftMenu(rows);
      }
    });
  };
  
  
  
  var resources;
  function InitLeftMenu(menuStr) {
    
    resources = menuStr;
    
    $('#left_nav').empty();
    
      $.each(resources, function(i, n) {
        
        var menulist="";
         
      menulist += '<ul id="left_nav_tree_'+n.id+'" class="easyui-tree" style="margin-top:3px;">';
          
          menulist += '</ul>';
          $('#left_nav').accordion('add', {
              title: n.text,
              content: menulist,
        border:false,
        selected:true,
              iconCls: n.icon
           });
           
           $('#left_nav_tree_'+n.id).tree();
          
      });
     setTimeout('addMenuTree()',500);
      
  }
  
	function addMenuTree() {
		$.each(resources,
			function(i, n) {
				$('#left_nav_tree_' + n.id).tree();
				$('#left_nav_tree_' + n.id).tree({
					data : n.children,
					onClick : function(node) {
						var arr = node.children;
						if (arr == undefined) {
							var attrs = node.attributes;
							var menu = node.text;
							if ($('#tabs').tabs('exists', menu)) {
								$('#tabs').tabs('select',menu);
							} else {
								$('#tabs').tabs('add',
								{
									title : menu,
									content :
// 										'<iframe style="width:100%;height:100%;" scrolling="auto" frameborder="0" src="http://www.baidu.com"></iframe>',
										 "<iframe name='"
											+ node.id
											+ "'  scrolling='no' frameborder='0' src='"
											+ attrs.url
											+ "' style='width: 100%; height: 100%; overflow: hidden;'></iframe>",
									closable : true
								});
// 							tabClose();
							}
						}

					}
				});

			});

		$('#left_nav').accordion('select', 0);

		closeProgressing();

	}

	function convert(rows) {
		//rows = jQuery.parseJSON(rows);
		function exists(rows, pid) {
			for (var i = 0; i < rows.length; i++) {
				if (rows[i].id == pid)
					return true;
			}
			return false;
		}

		var nodes = [];
		// 得到顶层节点  
		for (var i = 0; i < rows.length; i++) {
			var row = rows[i];
			if (!exists(rows, row.pid)) {
				nodes.push({
					id : row.id,
					text : row.name
				});
			}
		}

		var toDo = [];
		for (var i = 0; i < nodes.length; i++) {
			toDo.push(nodes[i]);
		}
		while (toDo.length) {
			var node = toDo.shift(); // 父节点   
			// 得到子节点   
			for (var i = 0; i < rows.length; i++) {
				var row = rows[i];
				if (row.pid == node.id) {
					if (row.url) {
						var child = {
							id : row.id,
							text : row.name,
							attributes : {
								url : ctx+row.url
							}
						};
					} else {
						var child = {
							id : row.id,
							text : row.name
						};
					}
					if (node.children) {
						node.children.push(child);
					} else {
						node.children = [ child ];
					}
					toDo.push(child);
				}
			}
		}
		return nodes;
	}

	function showProgressing() {

		$.messager.progress({
			title : '请稍候',
			msg : '数据加载...',
			text : '加载中.......',
			interval : 100,
			closed : false
		});

	}

	function closeProgressing() {
		$.messager.progress('close');
	}
</script>
</html>