<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <title>login</title>
    <link rel="stylesheet" type="text/css" href="resources/css/ext-all.css" />
	<script type="text/javascript" src="js/ext-base.js"></script>
    <script type="text/javascript" src="js/ext-all.js"></script>
    <script type="text/javascript" src="js/ext-all-debug.js"></script>
  	<script type="text/javascript">
  		// login window
var simple
Ext.onReady(function(){
  	  		//Ext.Msg.alert('Message',"Loading……");
  			simple=new Ext.form.FormPanel({
  	  			//renderTo:'Loginform',
  	  			border:false,
  				//title:'登录窗口',
  				defaultType:'textfield',
				labelAlign:'right',
  				labelWidth:120,
  				buttonAlign:'center',
  				frame:true,
  				width:300,
  				//---------------------------------
  				//Ext非AJAX提交Action
  				onSubmit: Ext.emptyFn,
  				submit: function(){
  				this.getEl().dom.action = 'login.do?method=login'; //连接到服务器的url地址
  				this.getEl().dom.submit();
  				},
  				//----------------------------------
  	  	  		items:[{
					fieldLabel:' UserName',
					name:'username'},
					{
					fieldLabel:' Password',
					name:'password',
					inputType:'password'}],
  	  	  	  	buttons:[{
  	  	  	  	  	text:'登录',
					handler:function(){simple.form.submit();}},
					{
  	  	  	  	  	text:'取消',
  	  	  	  	  	handler:function(){simple.form.reset();} 
  	  	  	  	  	 	}]
  	  	  		});

  	  		var win=new Ext.Window({
				title:'登录窗口',
				x:500,
  	  			y:250,
				height:200,
				width:350,
				layout:'fit',
				closeAction:'hide',
				closeable:false,
				items:[simple]
  	  	  		});
  	  	win.show();	
  	  });

function login(){
	simple.form.submit();
}
function reset(){
	simple.form.reset();
}
  	</script>
  </head>
  
  <body>
<%--      <div id='Loginform' style="margin:100px;"></div>--%>
  </body>
</html>
