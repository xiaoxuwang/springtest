<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/ext3_2.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'ext3_4table.jsp' form</title>
<script type="text/javascript">
Ext.onReady(function(){
	//创建一个Panel对象

	var datefield=new Ext.form.DateField({
		fieldLabel:'日期',
		emptyText:'请选择',
		format:'Ymd', //Y-m-d
		disabledDays:[0,6]
		});
	var form=new Ext.form.FormPanel({
		title:'dateForm',
		frame:true,
		width:'300',
		items:[datefield],
		renderTo:'form'
		});
	

		
		
});
</script>
  </head>
  <body>
    <div id="form" style="margin:100px;" ></div>
  </body>
</html>
