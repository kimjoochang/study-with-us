<%@page import="com.studywithus.domain.Community"%>
<%@page import="java.util.Collection"%>
<%@page import="com.studywithus.dao.CommunityDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티 조회</title>
</head>
<body>

<%
int categoryNo = Integer.parseInt(request.getParameter("no"));
String type;
if (categoryNo == 0) {
  type = "정보";
%>
<ul>
<li><a href='List.jsp?no=1'>질문 커뮤니티</a><br>
<li><a href='List.jsp?no=2'>스몰톡 커뮤니티</a><br>
</ul>

<%} else if (categoryNo == 1) {%>
  type = "질문";
<ul>
<li><a href='List.jsp?no=0'>정보 커뮤니티</a><br>
<li><a href='List.jsp?no=2'>스몰톡 커뮤니티</a><br>
</ul>

<%} else {%>
  type = "스몰톡";
<ul>
<li><a href='List.jsp?no=0'>정보 커뮤니티</a><br>
<li><a href='List.jsp?no=1'>질문 커뮤니티</a><br>
</ul>
<%
}
%>
<br>  
<br>
<a href='Add.jsp?no=<%=categoryNo%>'>글쓰기</a><br>

<table border='1'>
<thead>
  <tr>
    <th>번호</th>
    <th>카테고리</th>
    <th>제목</th>
    <th>작성자</th>
    <th>조회수</th>
    <th>좋아요</th>
    <th>등록일</th>
  </tr>
</thead>
<tbody>
<% // <== scriptlet (자바 코드 조각을 두는 태그)
Collection<Community> communityList = communityDao.findAll(categoryNo);

for (Community community : communityList) {
%>
<tr>
    <td><%=community.getNo()%></td>
    <td><%=type%></td>
    <td><a href='Detail.jsp?no=<%=community.getNo()%>'><%=community.getTitle()%></a></td> 
    <td><%=community.getWriter().getEmail()%></td> 
    <td><%=community.getViewCount()%></td> 
    <td><%=community.getLike()%></td> 
    <td><%=community.getRegisteredDate()%></td>
</tr>
<%} %>  
</tbody>
</table>
</body>
</html>

<%!
CommunityDao communityDao;

public void jspInit() {
  ServletConfig config = getServletConfig();
  ServletContext servletContext = config.getServletContext();
  communityDao = (CommunityDao) servletContext.getAttribute("communityDao");
}
%>