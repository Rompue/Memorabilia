<%@page import="localobjects.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="database.*" %>
<%@ page import="java.text.*" %>
<%@ page import="java.util.*" %>
<%
	User user = (User)request.getSession().getAttribute("user");
	MConnection.pullUserInfoByID(user.getIdUser());
	List<Notebook> notebooks = user.getNotebooks();
  if (MConnection.debug) {
	System.out.println("in requestnotebooks " + user.getUsername());
  }
	for(Notebook notebook: notebooks){
		if (MConnection.debug) {
			System.out.println(notebook.getName());
		  }
%>
  <option><%= notebook.getName()%></option>
<%
	}
%>
