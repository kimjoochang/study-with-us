<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="com.studywithus.dao.CommentDao"%>
<%@page import="java.util.List"%>
<%@page import="com.studywithus.domain.Comment"%>
<%@page import="com.studywithus.domain.Community"%>
<%@page import="java.util.Collection"%>
<%@page import="com.studywithus.dao.CommunityDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티 상세</title>
</head>
<body>

<%
int no = Integer.parseInt(request.getParameter("no"));
Community community = communityDao.findByNo(no);

if (community == null) {%>
  해당 번호의 게시글이 없습니다.
<% 
} else {
  community.setViewCount(community.getViewCount() + 1);
%>
<form>
    <label for='f-no'>번호</label> 
    <span id='f-no'><%=community.getNo()%>></span><br>
    
    <label for='f-title'>제목</label>
    <span id='f-title'><%=community.getTitle()%>></span><br>
    
    <label for='f-content'>내용</label> 
    <span id='f-content'<%=community.getContent()%>></span><br>
    
    <label for='f-writer'>작성자</label> 
    <span id='f-writer' <%=community.getWriter().getEmail()%>></span><br>
    
    <label for='f-viewCount'>조회수</label> 
    <span id='f-viewCount'><%=community.getViewCount()+1%>></span><br>
    
    <label for='f-like'>좋아요</label> 
    <span id='f-like'><%=community.getLike()%>></span><br>
    
    <label for='f-registeredDate'>등록일</label> 
    <span id='f-registeredDate'><%=community.getRegisteredDate()%></span><br>
<%}%>
<a href='Update.jsp?no=<%=community.getNo()%>'>[변경]</a> <a href='Delete.jsp?no=<%=community.getNo()%>'>[삭제]</a> <a href='List.jsp'>[목록]</a><br>
</form>

   <br>
    
    <%List<Comment> comments = commentDao.findAll(no);%>
    
      <div id= "comments">
        <div id="comments-head" class="comment-row">
          <span id="comments-count"><%=comments.size()%></span>
        </div>
      </div>
      
    <form action='CommentAdd.jsp'>
      <div class="comment-row">
        <textarea id="new-comment" name="new_comment" rows=5 placeHolder="댓글 입력"></textarea>
        <button type="submit">작성</button>
      </div>
    </form>    

    <%for (Comment myComment : comments) {%>  
      <div class="comment-row">
        <div class="comment-date"><%=myComment.getContent()%></div>
        <div class="comment-writer"><%=myComment.getWriter().getEmail()%></div>
        <div class="comment-content"><%=myComment.getContent()%></div>
        <a href='CommentDelete.jsp?no=<%=myComment.getNo()%>'>[삭제]</a>
        <%}%>
      </div>
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