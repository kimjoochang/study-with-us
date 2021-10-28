<%@page import="com.studywithus.servlet.user.AuthLogInHandler"%>
<%@page import="com.studywithus.menu.Menu"%>
<%@page import="com.studywithus.domain.Member"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="com.studywithus.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String email = request.getParameter("email");
String password = request.getParameter("password");

if (email.equals("root@test.com") && password.equals("0000")) {
  
  Member root = new Member();
  root.setName("관리자");
  root.setEmail("root@test.com");
  loginUser = root;
  userAccessLevel = Menu.ACCESS_ADMIN | Menu.ACCESS_GENERAL;
  response.sendRedirect("../index.jsp");

} else {
    Member member = memberDao.findMemberByEmailPassword(email, password);

    if (member != null) {
      AuthLogInHandler.loginUser = member;
      AuthLogInHandler.userAccessLevel = Menu.ACCESS_GENERAL;
      response.sendRedirect("../index.jsp");
    }
} 
%>

<%!
public static Member loginUser;
public static int userAccessLevel = Menu.ACCESS_LOGOUT;

public static Member getLoginUser() {
  return loginUser;
}

public static int getUserAccessLevel() {
  return userAccessLevel;
}

MemberDao memberDao;
SqlSession sqlSession;

public void jspInit() {
  ServletConfig config = getServletConfig();
  ServletContext servletContext = config.getServletContext();
  sqlSession = (SqlSession) servletContext.getAttribute("sqlSession");
  memberDao = (MemberDao) servletContext.getAttribute("memberDao");
}
%>
