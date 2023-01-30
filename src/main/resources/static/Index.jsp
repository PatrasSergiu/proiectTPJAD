<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    response.setHeader("Expires", "0");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-store,no-cache");
%>
<%@ page session="true"%>
<%@ page import="java.util.*,p.dtos.*,p.beans.*"%>
<html><head><title>Start page</title></head>
<body>
    <%! BeanJSP bean; %>
    <% bean = new BeanJSP(); session.setAttribute("bean", bean); %>
    <form method="post" action="controller"> <input type="submit" name="start" value="START" /> </form>
</body>
</html>
