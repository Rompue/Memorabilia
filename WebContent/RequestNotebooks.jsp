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
	String name = null;
	for(Notebook notebook: notebooks){
%>
  <option><%= notebook.getName()%></option>
<%
	}
%>
