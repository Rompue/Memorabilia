<%@page import="localobjects.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="database.*" %>
<%@ page import="java.text.*" %>
<%@ page import="java.util.*" %>
<%
	MConnection.connect();
	String name = request.getParameter("name");
	String createDateString = request.getParameter("createDate");
	String expireDateString = request.getParameter("expireDate");
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date createDate = simpleDateFormat.parse(createDateString);
	Date expireDate = simpleDateFormat.parse(expireDateString);
	String isPublicString = request.getParameter("isPublic");
	boolean isPublic = isPublicString.equals("on") ? true : false;
	try {
		User user = (User)request.getSession().getAttribute("user");
		Notebook newNotebook = MConnection.createNotebook(name, createDate, expireDate, isPublic, user);
		} catch (MException exception) {
%>
			<font color = "red"><strong><%= exception.getMessage() %></strong></font>
<%
	}

%>
