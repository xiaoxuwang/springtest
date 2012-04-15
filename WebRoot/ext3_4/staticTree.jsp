<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/ext3_2.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'ext3_4table.jsp' form</title>
<script type="text/javascript">
Ext.onReady(function(){
    Ext.QuickTips.init();
   var mytree=new Ext.tree.TreePanel({
       el:"container",
       animate:true,
       title:"简单Extjs动态树",
       collapsible:true,
       enableDD:true,
       enableDrag:true,
       rootVisible:true,
       autoScroll:true,
       autoHeight:true,
       width:150,
       lines:false,
       //这里简简单单的loader的几行代码是取数据的,很经典哦
       loader:new Ext.tree.TreeLoader({
          dataUrl:"json.ashx"
       })
   });
   
   //根节点
   var root=new Ext.tree.AsyncTreeNode({
       id:"root",
       text:"控制面板",
       expanded:true
   });
   
   mytree.setRootNode(root);
   mytree.render();//不要忘记render()下,不然不显示哦
});
</script>
  </head>
  <body>
    <div id="form" style="margin:100px;" ></div>
  </body>
</html>
