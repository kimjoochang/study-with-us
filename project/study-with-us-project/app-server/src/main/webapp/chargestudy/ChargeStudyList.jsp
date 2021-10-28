<%@page import="com.studywithus.dao.StudyDao"%>
<%@page import="com.studywithus.domain.Study"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>유료 스터디 목록</title>
</head>
<body>
<h1>유료 스터디 목록</h1>
<a href='Form.jsp'>유료 스터디</a><br>
<table border='1'>
<thead>
  <tr>
    <th>제목</th>
    <th>지역</th>
    <th>내용</th>
    <th>금액</th>
    <th>작성자</th>
  </tr>
</thead>
<tbody>
<% 
Collection<Study> chargeStudyList = chargeStudyDao.findAll();

for (Study chargeStudy : chargeStudyList) {%>
<tr>
          chargeStudy.getNo(),
          chargeStudy.getTitle(),
          chargeStudy.getContent(),
          chargeStudy.getArea(),
          chargeStudy.getPrice(),
          chargeStudy.getWriter().getName(),
          chargeStudy.getStartDate(),
          chargeStudy.getEndDate(),
          status,
          chargeStudy.getRegisteredDate(),
          chargeStudy.getMembers(),
          chargeStudy.getMaxMembers(),
          chargeStudy.getViewCount(),
          chargeStudy.getLikes());
          
    <td><%=chargeStudy.getNo()%></td>
    <td><a href='Detail.jsp?no=<%=member.getNo()%>'><%=member.getName()%></a></td> 
    <td><%=member.getEmail()%></td> 
    <td><%=member.getContent()%></td> 
    <td><%=member.getRegisteredDate()%></td>
</tr>
<%} %>
</tbody>
</table>
</body>
</html>
<%! 
    StudyDao studyDao;
    
    public void jspInit() {
      ServletConfig config = getServletConfig();
      ServletContext servletContext = config.getServletContext();
      studyDao = (StudyDao) servletContext.getAttribute("studyDao");
    }
%>









