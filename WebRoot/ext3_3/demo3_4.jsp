<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/ext3_2.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'ext3_3table.jsp' starting page</title>
<script type="text/javascript">
/*	
	var User={
			val:5,
			click:function(){
				alert("Hello User ");
			}
	
	};
	*/
	function User(name){
		this.name=name;
		function getBorn(){
				alert("You Born!");
			};
		}
	var me=new User("My name");
	
function clickOntest2(){

		//alert(me.name);
		me.getBorn();
	}

		

</script>
  </head>
  <body>
    <button id="test1">test1</button>
    <button id="test2" onclick="clickOntest2();">test2</button>
    <button id="test3">test3</button>
  </body>
</html>
