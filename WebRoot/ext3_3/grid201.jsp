<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/ext3_2.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'ext3_3table.jsp' starting page</title>
<script type="text/javascript">
	Ext.onReady(function(){

		 function renderSex(value) {
		        if (value == 'male') {
		            return "<span style='color:red;font-weight:bold;'>红男</span><img src='user_male.png' />";
		        } else {
		            return "<span style='color:green;font-weight:bold;'>绿女</span><img src='user_female.png' />";
		        }
		    }

		    function renderDescn(value, cellmeta, record, rowIndex, columnIndex, store) {
		        var str = "<input type='button' value='查看详细信息' onclick='alert(\"" +
		            "这个单元格的值是：" + value + "\\n" +
		            "这个单元格的配置是：{cellId:" + cellmeta.cellId + ",id:" + cellmeta.id + ",css:" + cellmeta.css + "}\\n" +
		            "这个单元格对应行的record是：" + record + "，一行的数据都在里边\\n" +
		            "这是第" + rowIndex + "行\\n" +
		            "这是第" + columnIndex + "列\\n" +
		            "这个表格对应的Ext.data.Store在这里：" + store + "，随便用吧。" +
		            "\")'>";
		        return str;
		    }

		    var cm = new Ext.grid.ColumnModel([
				new Ext.grid.RowNumberer(),
		        {header:'编号',dataIndex:'id'},
		        {header:'性别',dataIndex:'sex',renderer:renderSex}, //显示的内容放到标签上
		        {header:'名称',dataIndex:'name'},
		        {header:'操作',dataIndex:'descn',renderer:renderDescn}
		    ]);

		    var data = [
		        ['1','male','name1','descn1'],
		        ['2','female','name2','descn2'],
		        ['3','male','name3','descn3'],
		        ['4','female','name4','descn4'],
		        ['5','male','name5','descn5']
		    ];

		    var store = new Ext.data.Store({
		        proxy: new Ext.data.MemoryProxy(data),
		        reader: new Ext.data.ArrayReader({}, [
		            {name: 'id'},
		            {name: 'sex'},
		            {name: 'name'},
		            {name: 'descn'}
		        ])
		    });
		    store.load();

		    var grid = new Ext.grid.GridPanel({
		        autoHeight: true,
		        renderTo: 'grid',
		        store: store,
		        cm: cm
		    });

});
</script>
  </head>
  <body>
    <div id="grid"></div>
  </body>
</html>
