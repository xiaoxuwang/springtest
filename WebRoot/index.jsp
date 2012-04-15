<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="resources/css/ext-all.css" />
   	<title>Spring+EXT 练习 </title>
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
    <script type="text/javascript" src="js/ext-base.js"></script>
    <script type="text/javascript" src="js/ext-all.js"></script>
    <script type="text/javascript" src="js/ext-all-debug.js"></script>
	<script type="text/javascript">
	 Ext.onReady(function(){
		var menulist=<%=session.getAttribute("MenuList") %>;
		Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
		var viewport = new Ext.Viewport({
            layout: 'border',
            items: [
            // create instance immediately
            new Ext.BoxComponent({
                region: 'south',
                height: 32, // give north and south regions a height
                autoEl: {
                    tag: 'div',
                    html:'<p><center> EXT 3.2.0 王霄旭 2010-09</center></p>'
                }
            }),
            new Ext.BoxComponent({
                region: 'north',
                height: 32, // give north and south regions a height
                autoEl: {
                    tag: 'div',
                    html:'<p> EXT 3.2.0 </p>'
                }
            }),/* {
                region: 'east',
                title: 'East Side',
                collapsible: true,
                hide:true,
                split: true,
                width: 225, // give east and west regions a width
                minSize: 175,
                maxSize: 400,
                margins: '0 5 0 0',
                layout: 'fit', // specify layout manager for items
                items:            // this TabPanel is wrapped by another Panel so the title will be applied
                new Ext.TabPanel({
                    border: false, // already wrapped so don't add another border
                    activeTab: 1, // second tab initially active
                    tabPosition: 'bottom',
                    items: [{
                        html: '<p>A TabPanel component can be a region.</p>',
                        title: 'A Tab',
                        autoScroll: true
                    }, new Ext.grid.PropertyGrid({
                        title: 'Property Grid',
                        closable: true,
                        source: {
                            "(name)": "Properties Grid",
                            "grouping": false,
                            "autoFitColumns": true,
                            "productionQuality": false,
                            "created": new Date(Date.parse('10/15/2006')),
                            "tested": false,
                            "version": 0.01,
                            "borderWidth": 1
                        }
                    })]
                })
            },*/{
                region: 'west',
                id: 'west-panel', // see Ext.getCmp() below
                title: 'West',
                split: true,
                width: 200,
                minSize: 175,
                maxSize: 400,
                collapsible: true,
                autoScroll: true,
                margins: '0 0 0 5',
                layout: {
                    type: 'accordion',
                    animate: true
                },
                items: [{
                    contentEl: 'west',
                    title: '操作菜单',
                    border: false,
                    iconCls: 'nav' // see the HEAD section for style used
                }, {
                    title: 'Settings',
                    html: '<p>Some settings in here.</p>',
                    border: false,
                    iconCls: 'settings'
                }]
            },
  
            new Ext.TabPanel({
                region: 'center', // a center region is ALWAYS required for border layout
                id:'center-panel',
                deferredRender: false,
                activeTab: 0,     // first tab initially active
                items: [{
                    contentEl: 'center1',
                    title: 'Welcome',
                    closable: true,
                    html:'<p>EXT 3.2.0 Welcome!</p>',
                    autoScroll: true
                }]
            })]
        });
        // get a reference to the HTML element with id "hideit" and add a click listener to it 
        Ext.get("hideit").on('click', function(){
            // get a reference to the Panel that was created with id = 'west-panel' 
           var w = Ext.getCmp('west-panel');
            // expand or collapse that Panel based on its collapsed property state
            w.collapsed ? w.expand() : w.collapse();
        });

        
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
     var rootID = '1';
     var rootnode = new Ext.tree.AsyncTreeNode({
         id : '1-',
         text : 'root',
         draggable : false,// 根节点不容许拖动
         expanded : false
     });
     // 为tree设置根节点
     tree.setRootNode(rootnode);
     tree.on('beforeload', function(node) 
     {
    	 var idurl=node.id;
		 var len=idurl.indexOf('-');
         var id=idurl.substring(0,len);
         id=node.id=='0'?'0':id;
         tree.loader.dataUrl = 'login.do?method=TreeMenu&nodeid='+id; // 定义每个节点的Loader
     });
     // render the tree
     tree.render();
		//给节点添加单击事件
     tree.on("click", function(node){
         var idurl=node.id;
		 var len=idurl.indexOf('-');
         var url=idurl.substring(len+1);
         if(url!=""&&url!=null){
        	 var c=Ext.getCmp('center-panel');
             var n=c.getComponent(node.id);
             if(n){
    			c.remove(n);
                }
             n=c.add({
    				'id':node.id,
    				'title':node.text,
    				closable:true,
    				html:'<iframe name="if'+node.text+'" scrolling="auto" frameborder="0" width="100%" height="100%" src="'+url+'"></iframe>'
                 });
             c.setActiveTab(n); 
          } 
        });
        
	});
	</script>
  </head>
  
  <body>
  	<div id="west" class="x-hide-display">
        <div id="tree"></div>
    </div>
    <div id="center2" class="x-hide-display">
    	<a id="hideit" href="#">Toggle the west region</a>
    </div>
    <div id="center1" class="x-hide-display"></div>
    <div id="props-panel" class="x-hide-display" style="width:200px;height:200px;overflow:hidden;">
    </div>
    
    <div id="south" class="x-hide-display"></div>
  </body>
</html>
