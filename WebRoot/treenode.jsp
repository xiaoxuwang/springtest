<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="resources/css/ext-all.css" />
   	<title>EXT</title>
   	  	<style type="text/css">
	    html, body {
	        font:normal 12px verdana;
	        margin:0;
	        padding:0;
	        border:0 none;
	        overflow:hidden;
	        height:100%;
	    }
	    p {
	        margin:5px;
	    }
	    .settings {
	        background-image:url(resources/images/default/folder_wrench.png);
	    }
	    .nav {
	        background-image:url(resources/images/default/folder_go.png);
	    }
    </style>
<%--    <script type="text/javascript" src="js/ext-base.js"></script>--%>
<%--    <script type="text/javascript" src="js/ext-all.js"></script>--%>
<%--    <script type="text/javascript" src="js/ext-all-debug.js"></script>--%>
	<script type="text/javascript" >
	/*Ext.onReady(function()
			{
			    var treeloader = new Ext.tree.TreeLoader({
			            // dataUrl : 'tree.jsp'//这里可以不需要指定URL，在beforeload事件响应里面设置
			     });
			     var tree = new Ext.tree.TreePanel({
			        el: 'tree',
			        useArrows: true,
			        autoScroll: true,
			        animate: true,
			        enableDD: true,
			        containerScroll: true,
			        border: false,
			        loader: treeloader

			    });
			    var rootID = '0';
			    var rootnode = new Ext.tree.AsyncTreeNode({
			        id : rootID,
			        text : 'root',
			        draggable : false,// 根节点不容许拖动
			        expanded : false
			    });

			    // 为tree设置根节点
			    tree.setRootNode(rootnode);
			    tree.on('click',function(node,event)
			    {
			        alert(node.id);
			    });
			    tree.on('beforeload', function(node) 
			    {
			        tree.loader.dataUrl = 'login.do?method=TreeMenu&nodeid='+node.id; // 定义每个节点的Loader
			    });
			    // render the tree
			    tree.render();
			});*/

function sp(){

	var str="1-login.jsp";
	var len=str.indexOf('-');
	//var ss=str.split('-');
	alert("id="+str.substring(0,len)+"\turl="+str.substring(len+1));
}	
</script>
	
  </head>
  
  <body>
<%--      <div id="tree"></div>--%>
      <input type="button" value="Onclick" onclick="sp()"/>
  </body>
</html>
