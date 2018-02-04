<%@page import="localobjects.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="database.*" %>
<%@ page import="java.text.*" %>
<%@ page import="java.util.*" %>
<%
	User user = (User)request.getSession().getAttribute("user");
	MConnection.pullUserInfoByID(user.getIdUser());
  if (MConnection.debug) {
	System.out.println("in requestnotebooks " + user.getNotebooks().size());
  }
	for(Notebook notebook: user.getNotebooks()){
		if (MConnection.debug) {
			System.out.println(notebook.getName());
		  }
%>
  <option><%= notebook.getName()%></option>
<%
	}
%>
