<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.util.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'ext3_3table.jsp' starting page</title>
<%
HashMap map1 = new HashMap();
map1.put("key1","lzsb");
request.setAttribute("map1", map1);
%>
	
  </head>
  <body>
    MapValue:${map1['key1']}
  </body>
</html>
