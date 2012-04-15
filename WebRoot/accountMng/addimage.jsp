<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/ext3_2.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'ext3_3table.jsp' starting page</title>
<script type="text/javascript">
	

	Ext.onReady(function(){
		//控件添加事件类型
		/*Ext.get('test1').on('click',function(){
			//Ext.Msg.alert('message',"提示信息！");
			//alert(text1);
			getText();
		});*/
});
</script>
  </head>
  <body>
    	<form action="../work.do?method=addimage" enctype="multipart/form-data" method="post">
    		图片：<input type="file" name="image"><br>
    		描述：<input type="text" name="desc"/><br>
    		<input type="submit" value="Save"/>
    	</form>
  </body>
</html>
