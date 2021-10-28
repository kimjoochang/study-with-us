<%@page import="com.studywithus.servlet.user.AuthLogInHandler"%>
<%@page import="com.studywithus.menu.Menu"%>
<%@page import="com.studywithus.domain.Member"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="com.studywithus.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
AuthLogInHandler.loginUser.setUserAccessLevel(AuthLogInHandler.getUserAccessLevel());

AuthLogInHandler.loginUser = null;
AuthLogInHandler.userAccessLevel = Menu.ACCESS_LOGOUT;

response.sendRedirect("../index.jsp");
%>
