<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/ext3_2.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'ext3_3table.jsp' starting page</title>
<script type="text/javascript">
	Ext.onReady(function(){
		Ext.get('test').on('click',function(){
			Ext.Msg.alert('message',"提示信息！");
		});
});
</script>
  </head>
  
  <body>
    <button id="test">test</button>
  </body>
</html>
