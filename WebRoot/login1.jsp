<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <title>My JSP 'login.jsp' starting page</title>
    <link rel="stylesheet" type="text/css" href="resources/css/ext-all.css" />
	<script type="text/javascript" src="js/ext-base.js"></script>
    <script type="text/javascript" src="js/ext-all.js"></script>
    <script type="text/javascript" src="js/ext-all-debug.js"></script>
  	<script type="text/javascript">
  		// login window
Ext.onReady(function(){
  	  		//Ext.Msg.alert('Message',"Loading……");
  			var form=new Ext.form.FormPanel({
  				title:'form',
  				defaultType:'textfield',
				labelAlign:'center',
  				labelWidth:60,
  				buttonAlign:'center',
  				frame:true,
  				width:300,
  				url:'login.do',
  				//height:200,
  	  	  		items:[{
					fieldLabel:' UserName',
					name:'username'
  	  	  	  		},{
  	  	  	  	  		
					fieldLabel:' Password',
					name:'password',
					inputType:'password'
  	  	  	  		}],
  	  	  	  	buttons:[{
  	  	  	  	  	text:'登录',
					handler:function(){
						form.getForm().submit({
							success:function(form, action){
		                        Ext.Msg.alert('信息', action.result.msg);
		                    },
		                    failure:function(){
		                        Ext.Msg.alert('错误', '操作失败！');
		                    }
							});
					}
  	  	  	  	  	},{
  	  	  	  	  	text:'取消'
  	  	  	  	  	 	}]
  	  	  		});
  	  		form.render("form");
  	  		});
  	</script>
  </head>
  
  <body>
      <div id='form' style="margin:100px;"></div>
<%--    	<form action="login.do" method="post">--%>
<%--		用户名:<input type="text" name="username" onkeyup="alert(this.value);">--%>
<%--		<br>--%>
<%--		密     &nbsp;&nbsp;码:<input type="password" name="password">--%>
<%--		<br>--%>
<%--		<input type="submit"  value="提交">--%>
<%--		<input type="button"  value="注册"  onclick="zhuce();">--%>
<%--		<a href="javascript:window.history.back();">back</a>--%>
<%--  		</form>--%>
  	
  </body>
</html>
