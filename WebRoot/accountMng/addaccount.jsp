<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/ext3_2.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>注册用户表单验证</title>
<script type="text/javascript">
	

	Ext.onReady(function(){
		
		var isExit=false;
		Ext.QuickTips.init();
		Ext.form.Field.prototype.msgTarget = 'side';
		//LabelAlign:'right',buttonAlign:'center'
		//直接写在FormPanel中有效，在对应的Items中写则无效
		var regform=new Ext.form.FormPanel({
			labelWidth:80,
			frame:true,
			labelAlign:'right',//有效
			buttonAlign:'center',
			//monitorValid:true,//进行表单验证
			url:'../login.do?method=addaccount',
			defaultType:'textfield',
			items:[{
				
				fieldLabel:"用户名",
				labelAlign:'right',//写在此处无效
				id:"username",
				name:"username",
				width:150,
				allowBlank:false,//值为 false 时将效验输入字符,默认为true
				blankText:"请输入用户名！",
				/*
				类型一
				form验证中vtype的默认支持类型
				1.alpha //只能输入字母，无法输入其他（如数字，特殊符号等）
				2.alphanum//只能输入字母和数字，无法输入其他
				3.email//email验证，要求的格式是"langsin@gmail.com"
				4.url//url格式验证，要求的格式是[url]http://www.langsin.com[/url]
				5.一下例子是自定义的验证类型 
				*/
				vtype:"chkusername",
				vtypeText:"用户名已经被注册！" 
				/*
				：类型二	
				validator:function(){}//验证函数
				*/			
				//validator:check,
				//invalidText:"用户名已经被注册！"
			},
			{
				inputType:'password',
				fieldLabel:"密码",
				id:"pwd1",
				name:"pwd1",
				width:150,
				allowBlank:false,
				blankText:"密码不能为空！"		
			},{
				inputType:'password',
				fieldLabel:'确认密码',
				id:"pwd2",
				name:"password",
				width:150,
				allowBlank:false,
				blankText:"确认密码不能为空！	",
				validator:function(){ //表单验证函数
					//alert("mima");
					if(Ext.get("pwd1").dom.value!=Ext.get("pwd2").dom.value){
						
						return false;
					}else{
						return true;
					}
				},
				invalidText:"两次密码不一致！"
			}],
				buttons:[{
					text:"注册",
					formBind:true,
					handler:function(){
						//以下代码可防止表单重复提交
						Ext.Msg.show({
							wait:true,
							title:"请稍候",
							width:150,
							progressText:"saving ",
							waitConfig:{interval:200,duration:2000,fn:function(){
								//Ext.MessageBox.hide();
								//Ext.MessageBox.alert("提示信息","注册成功！");
								regform.getForm().submit({
									success:function(form,action){
										regform.form.reset();
										win.hide();
										Ext.Msg.alert('提示信息', action.result.msg);
									},
				                    failure:function(){
				                        Ext.Msg.alert('提示信息', '操作失败！');
				                    }
								});
							}}
						});
						
					}
					
				},{
					text:"取消",
					handler:function(){
						regform.form.reset();
					}
				}]
			});
		//创建一个窗体
		var win=new Ext.Window({
			title:"注册",
			width:300,
			height:200,
			closeAction:"hide",
			closable:true,
			collapsible:true,
			maximizable:true,
			items:regform
			});
		win.show();
		//检测用户名是否可用
		/*Ext.get("username").on('blur',function(){
			//alert('onblur');
			Ext.Ajax.request({
				url:'../login.do?method=cheUsername',
				params:{username:Ext.get("username").dom.value},
				success:function(response,options){
					var data = Ext.util.JSON.decode(response.responseText);
					if(data.success ==true)Ext.Msg.alert('Message','用户名已存在！');
					else Ext.Msg.alert('Message','用户名有效！');
				}
			});
		});*/
		function check(){
			//alert("22");
			Ext.Ajax.request({
				url:'../login.do?method=cheUsername',
				params:{username:Ext.get("username").dom.value},
				success:function(response,options){
					var data = Ext.util.JSON.decode(response.responseText);
					if(data.success ==true)SetValue(false);//Ext.Msg.alert('Message','用户名已存在！');
					else SetValue(true); //else //Ext.Msg.alert('Message','用户名有效！');
				}
			});
			function SetValue(b){   
				isExit = b;//给变量赋值   
	     	}  
			//alert("33");
			return isExit;
		}
		//添加自定义验证函数
		Ext.apply(Ext.form.VTypes,{ 
			chkusername:function(val,field){//val指这里的文本框值，field指这个文本框组件，大家要明白这个意思 
				Ext.Ajax.request({
					url:'../login.do?method=cheUsername',
					params:{username:Ext.get("username").dom.value},
					success:function(response,options){
						var data = Ext.util.JSON.decode(response.responseText);
						if(data.success ==true)SetValue(false);//Ext.Msg.alert('Message','用户名已存在！');
						else SetValue(true); //else //Ext.Msg.alert('Message','用户名有效！');
					}
				});
				function SetValue(b){   
					isExit = b;//给变量赋值   
		     	}  
				//alert("33");
				return isExit;
			} 
			});
});
</script>
  </head>
  <body>
	<li>新用户注册校验用户名是否已存在</li>
  </body>
</html>
