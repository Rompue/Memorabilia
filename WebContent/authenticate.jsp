<%@page import="localobjects.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="database.*" %>
<%
	MConnection.connect();
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	try {
		System.out.println("line 10");
		User user = MConnection.authenticate(username, password);
		request.getSession().setAttribute("user", user);
		request.getSession().setMaxInactiveInterval(60000);
		} catch (MException exception) {
			System.out.println("line15");
%>
			<font color = "red"><strong><%= exception.getMessage() %></strong></font>
<%
	}

%>
