<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/ext3_2.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>ext中css样式使用 2010-10-14</title>
<script type="text/javascript">

	//
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
