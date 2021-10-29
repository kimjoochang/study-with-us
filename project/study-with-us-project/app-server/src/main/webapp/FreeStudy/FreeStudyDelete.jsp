<%@page import="com.studywithus.domain.Study"%>
<%@page import="com.studywithus.dao.StudyDao"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%
int no = Integer.parseInt(request.getParameter("no"));

Study freeStudy = StudyDao.findByNo(no);

if (freeStudy == null) {
  throw new Exception("해당 번호의 회원이 없습니다.");
} else {
  StudyDao.delete(no);
  sqlSession.commit();
}
response.sendRedirect("StudyList.jsp");
%>
<%! // <== declaration element(tag)
// 자바 서블릿 클래스를 만들 때 그 클래스에 들어갈 변수와 메서드를 이 태그 안에 작성한다.
    SqlSession sqlSession;
    StudyDao StudyDao;
    
    public void jspInit() {
      ServletConfig config = getServletConfig();
      ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
      sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
      StudyDao = (StudyDao) 웹애플리케이션공용저장소.getAttribute("StudyDao");
    }
%>









