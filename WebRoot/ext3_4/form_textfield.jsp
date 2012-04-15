<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/ext3_2.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'ext3_4table.jsp' form</title>
<script type="text/javascript">

	//创建一个Panel对象
	var form=new Ext.form.FormPanel({
			title:'form',
			defaultType:'textfield',
			labelWidth:50,
			buttonAlign:'center',
			frame:true,
			width:220,
			items:[{
				fieldLabel:'文本框',
				}],
			buttons:[{
					text:'按钮'
					}]
		});
	Ext.onReady(function(){
		form.render("form");
		
});
</script>
  </head>
  <body>
    <div id="form" style="margin:100px;" ></div>
  </body>
</html>
