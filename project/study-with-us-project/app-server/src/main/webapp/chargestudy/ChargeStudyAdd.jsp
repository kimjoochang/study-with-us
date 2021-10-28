<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="com.studywithus.servlet.user.AuthLogInHandler.java"%> //jsp import?
<%@page import="com.studywithus.dao.StudyDao"%>
<%@page import="com.studywithus.domain.Study"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%

Study chargeStudy = new Study();

chargeStudy.setTitle(request.getParameter("title"));
chargeStudy.setArea(request.getParameter("area"));
chargeStudy.setContent(request.getParameter("content"));
// chargeStudy.setWriter(request.getParameter("writer")); 직접 입력 말고
chargeStudy.setWriter(AuthLogInHandler.getLoginUser()); // 이렇게 받아와도 되니
chargeStudy.setMaxMembers(request.getParameter("maxMembers"));
chargeStudy.setPrice(request.getParameter("price"));

studyDao.insert(chargeStudy);
sqlSession.commit();
response.sendRedirect("List.jsp"); // 스터디리스트로 이동할건가
%>
<%!
  SqlSession sqlSession;
  StudyDao studyDao;
  
  public void jspInit() {
    ServletConfig config = getServletConfig();
    ServletContext servletContext = config.getServletContext();
    sqlSession = (SqlSession) servletContext.getAttribute("sqlSession");
    studyDao = (StudyDao) servletContext.getAttribute("studyDao");
  }
%>
  
  