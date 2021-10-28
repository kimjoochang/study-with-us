<%@page import="com.studywithus.domain.Study"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>

<%
Study freeStudy = new Study();

freeStudy.setWriter(AuthLogInHandler.getLoginUser());
freeStudy.setOnOffLine(Integer.parseInt(request.getParameter("onOffLine")));
freeStudy.setArea(request.getParameter("area"));
freeStudy.setTitle(request.getParameter("title"));
freeStudy.setContent(request.getParameter("content"));
freeStudy.setMaxMembers(Integer.parseInt(request.getParameter("maxMembers")));

studyDao.insert(study);
sqlSession.commit();
response.sendRedirect("studyList.jsp");
%>
<%! // <== declaration element(tag)
// 자바 서블릿 클래스를 만들 때 그 클래스에 들어갈 변수와 메서드를 이 태그 안에 작성한다.
    SqlSession sqlSession;
    studyDao studyDao;
    
    public void jspInit() {
      ServletConfig config = getServletConfig();
      ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
      sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
      studyDao = (studyDao) 웹애플리케이션공용저장소.getAttribute("studyDao");
    }
%>









