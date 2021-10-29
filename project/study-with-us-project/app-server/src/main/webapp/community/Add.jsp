<%@page import="com.studywithus.servlet.user.AuthLogInHandler"%>
<%@page import="com.studywithus.domain.Community"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="com.studywithus.dao.CommunityDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
int categoryNo = Integer.parseInt(request.getParameter("no"));

Community community = new Community();

community.setCategory(categoryNo);
community.setTitle(request.getParameter("title"));
community.setContent(request.getParameter("Content"));
community.setWriter(AuthLogInHandler.getLoginUser());

communityDao.insert(community);

sqlSession.commit();

%>

<%!
CommunityDao communityDao;
SqlSession sqlSession;

public void jspInit() {
  ServletConfig config = getServletConfig();
  ServletContext servletContext = config.getServletContext();
  sqlSession = (SqlSession) servletContext.getAttribute("sqlSession");
  communityDao = (CommunityDao) servletContext.getAttribute("communityDao");
}
%>