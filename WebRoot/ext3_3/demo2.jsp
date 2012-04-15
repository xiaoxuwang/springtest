<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/ext3_2.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'ext3_3table.jsp' starting page</title>
<script type="text/javascript">
	
	//创建命名空间
	
	var text1="@";
	var getText=function(){
			alert(text1);
		};
	
	var items=new Array('ONE','TWO','THREE');
	var itemref=items;
	items.push("four");
	function clickOntest2(){
		//Ext.Msg.alert('Message','test2='+text1);
		alert(items.length==itemref.length); //true 二者引用的是同一个对象
	}
	//页面加载时执行
	Ext.onReady(function(){
		//控件添加事件类型
		Ext.get('test1').on('click',function(){
			Ext.Msg.alert('Message',"提示信息！");
		});
		Ext.get('test2').on('click',function(){
			Ext.Msg.confirm('Message','Are you sure !');
		});
		Ext.get('test3').on('click',function(){
			Ext.Msg.show({
				   title: 'Address',
				   msg: 'Please enter your address:',
				   width: 300,
				   buttons: Ext.MessageBox.OKCANCEL,
				   animEl: 'addAddressBtn',
				   icon: Ext.MessageBox.INFO
				});

		});
		Ext.get('test4').on('click',function(){
			Ext.Msg.wait('Message','Are you sure !','true');
		});
});
</script>
  </head>
  <body>
  <p>Ext.Msg.alert();  </p>
    <input type="button" id="test1" value="Msg" />
    <p>Ext.Msg.confirm();  </p>
    <button id="test2">confirm</button>
    <p>Ext.Msg.getDialog(); </p>
    <button id="test3">INFO</button>
    <p>Ext.Msg.wait(); </p>
    <button id="test4">Wait</button>
  </body>
</html>
