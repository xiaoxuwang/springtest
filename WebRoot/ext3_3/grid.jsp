<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/ext3_2.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'ext3_3table.jsp' starting page</title>
<script type="text/javascript">
	Ext.onReady(function(){

		//create data model
		var cm=new Ext.grid.ColumnModel([
		       {header:'编号',dataIndex:'id'},
		       {header:'名称',dataIndex:'name'},
		       {header:'描述',dataIndex:'descn'},
		       {header:'日期',dataIndex:'date', renderer: Ext.util.Format.dateRenderer('Y-m-d')}
		]);
 	
		//data
		var data=[
			['1','name1','descn1','1970-01-15T02:58:04'],
	        ['2','name2','descn2','1970-01-15T02:58:04'],
	        ['3','name3','descn3','1970-01-15T02:58:04'],
	        ['4','name4','descn4','1970-01-15T02:58:04'],
	        ['5','name5','descn5','1970-01-15T02:58:04']
		];
		
		//格式化数据
		var store= new Ext.data.Store({
			//获取本地数据
			proxy:new Ext.data.MemoryProxy(data),
			//获取远程(服务端)数据
			//proxy: new Ext.data.ScriptTagProxy({url:'http://www.family168.com/data.json'}),
			reader:new Ext.data.ArrayReader({},[
			  		{name:'id'},
			  		{name:'name'},
			  		{name:'descn'},
			  		{name:'date'}
			  		])
			});
		store.load();
		
		//create tablePanel
		var grid=new　Ext.grid.GridPanel({
			autoHeight:true,
			renderTo:'grid',
			//width: 300,
	        //height: 150,
			//loadMask:true,
			//stripeRows:true,
			store:store,
			cm:cm,
			loadMask : {
	   			msg : '正在加载数据，请稍侯……'
	  		} // 数据读入等待显示界面.
			//viewConfig: {
            //	forceFit: true
        	//}
			//autoExpandColumn:'descn'
		});
});
</script>
  </head>
  <body>
  	<p>一个简单的表格</p>
    <div id="grid"></div>
  </body>
</html>
