<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="database.*" %>
    <%@page import="localobjects.*"%>
    <%@ page import="java.util.*" %>
	<%
	MConnection.connect();
	String diary = request.getParameter("todaydiary");
	Date createDate = new Date();
	@SuppressWarnings("deprecation")
	Date expireDate = new Date(2018-1900, 8, 9);
	Notebook newNotebook = MConnection.createNotebook("notebookname", createDate, expireDate, true, (User)session.getAttribute("user"));
	Date diaryCreateDate = new Date();
	Diary newDiary = MConnection.createDiary(diary, diaryCreateDate, newNotebook);
	%>
	