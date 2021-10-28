<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="com.eomcs.pms.domain.Study"%>
<%@page import="com.eomcs.pms.dao.StudyDao"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%

Study chargeStudy = new Study();

chargeStudy.setTitle(request.getParameter("title"));
chargeStudy.setArea(request.getParameter("area"));
chargeStudy.setContent(request.getParameter("content"));
chargeStudy.setWriter(request.getParameter("writer")); // getlogInUser 어떻게 받아오지
chargeStudy.setMaxMembers(request.getParameter("maxMembers"));
chargeStudy.setPrice(request.getParameter("price"));

chargeStudy.setStartDate(request.getParameter("startDate")); // 얘네도 다른 폼이 있니
chargeStudy.setEndDate(request.getParameter("endDate"));

studyDao.insert(chargeStudy);
sqlSession.commit();
response.sendRedirect("StudyList.jsp"); // 스터디리스트로 이동할건가
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
  
  