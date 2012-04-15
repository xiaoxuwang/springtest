<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/ext3_2.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <title>selectForm</title>
        <script type="text/javascript" src="../js/Adapter.js"></script>
<script type="text/javascript">

	//创建一个Panel对象
	Ext.onReady(function(){

		 Ext.QuickTips.init();
			
		    var grid = new Ext.grid.GridPanel({
		        width: 300,
		        height: 150,
		        title: 'grid',
		        store: new Ext.data.SimpleStore({
		            data: [
		                ['name1','female','descn1'],
		                ['name2','male','descn2'],
		                ['wangwu','F','descn3']
		            ],
		            fields: ['name','sex','descn']
		        }),
		        columns: [
		            {header:'姓名',dataIndex:'name'},
		            {header:'性别',dataIndex:'sex'},
		            {header:'备注',dataIndex:'descn'}
		        ],
		        viewConfig: {
		            forceFit: true
		        }
		    });

		    var selectMenu = new Ext.menu.Menu({
			    //需要导入文件Adapter.js
		        items: [new Ext.menu.Adapter(grid)]
		    });

		    var field = new Ext.form.TriggerField({
		        fieldLabel: '选择',
		        name: 'name',
		        onSelect: function(record){
		        },
		        onTriggerClick: function() {
		            if (this.menu == null) {
		                this.menu = selectMenu;
		            }
		            this.menu.show(this.el, "tl-bl?");
		        }
		    });

		    grid.on('rowclick', function(grid, rowIndex, e) {
		        selectMenu.hide();
		        field.setValue(rowIndex);
		    });


		    var form = new Ext.form.FormPanel({
		        title: 'form',
		        frame: true,
		        items: [field],
		        renderTo: 'form'
		    });

});
</script>
  </head>
  <body>
    <div id="form" style="margin:100px;" ></div>
  </body>
</html>
