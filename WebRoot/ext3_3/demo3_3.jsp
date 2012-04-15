<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/ext3_2.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'ext3_3table.jsp' starting page</title>
<script type="text/javascript">
	

	Ext.onReady(function(){
		//控件添加事件类型
		Ext.get('test1').on('click',function(){
			//Ext.Msg.alert('message',"提示信息！");
			//alert(text1);
			getText();
		});
});

	var items=new Array('ONE','TWO','THREE');
	var itemref=items;
	items=new Array("new","array");
	items.push("four");
	var val="abcedfg";
	var arr;
	function clickOntest2(){
		//Ext.Msg.alert('Message','test2='+text1);
		//alert(items!=itemref); //true 二者引用的不是同一个对象
		if(typeof val=="string"){
			arr=val.split(",");
			if(arr.constructor==Array){
				 arr=arr.join(',');
				 alert(arr);
			}
		}
		
	}
	var text3=document.getElementById("xiaoxu");
	function show(){
		
		
		//alert(text3);
	}
	//等待两秒之后
	setTimeout(function(){
		document.getElementById("xiaoxu").style.display='block';
		},2000);
</script>
  </head>
  <body>
    <button id="test1">test1</button>
    <button id="test2" onclick="clickOntest2();">test2</button>
    <div id="xiaoxu" style="display:none">wo chu lai le !</div>
  </body>
</html>
