<%@page import="com.studywithus.domain.Comment"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="com.studywithus.dao.CommentDao"%>
<%@page import="com.studywithus.domain.Community"%>
<%@page import="java.util.Collection"%>
<%@page import="com.studywithus.dao.CommunityDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티 수정</title>
</head>
<body>

<%
int no = Integer.parseInt(request.getParameter("no"));
Community community = communityDao.findByNo(no);

if (community == null) {%>
  해당 번호의 게시글이 없습니다.
<% 
} else {
%>
<form action='UpdateForm.jsp'>
    <label for='f-no'>번호</label> 
    <input id='f-no' type='text' name='no' value='<%=community.getNo()%>' readonly><br>
    
    <label for='f-title'>제목</label>
    <input id='f-title' type='text' name='title' value='<%=community.getTitle()%>'><br>
    
    <label for='f-content'>내용</label> 
    <textarea name="textarea" id="textarea" cols="50" rows="5">value='<%=community.getContent()%>'></textarea><br>
    
    <label for='f-writer'>작성자</label> 
    <input id='f-writer' type='email' name='writer' value='<%=community.getWriter().getEmail()%>' readonly>><br>
    
    <label for='f-viewCount'>조회수</label> 
    <input id='f-tel' type='tel' name='tel' value='<%=community.getViewCount()+1%>'><br>

    <label for='f-like'>좋아요</label> 
    <input id='f-like' type='tel' name='like' value='<%=community.getLike()%>'><br>
    
    <label for='f-registeredDate'>등록일</label> 
    <span id='f-registeredDate'><%=community.getRegisteredDate()%></span><br>
<button>변경</button> <a href='List.jsp'>[목록]</a> <a href='Detail.jsp'>[이전]</a>
</form>
 
</body>
</html>

<%!
CommunityDao communityDao;
CommentDao commentDao;
SqlSession sqlSession;

public void jspInit() {
  ServletConfig config = getServletConfig();
  ServletContext servletContext = config.getServletContext();
  communityDao = (CommunityDao) servletContext.getAttribute("communityDao");
  commentDao = (CommentDao) servletContext.getAttribute("commentDao");
  sqlSession = (SqlSession) servletContext.getAttribute("sqlSession");
}
%>