<%@page import="java.util.List"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티 상세</title>
</head>
<body>

<c:choose>
<c:when test="${community.category eq 0}">
<c:set var="type" value="정보"/>
</c:when>

<c:when test="${community.category eq 1}">
<c:set var="type" value="질문"/>
</c:when>

<c:when test="${community.category eq 2}">
<c:set var="type" value="스몰톡"/>
</c:when>
</c:choose>

<form>
    <label for='f-no'>번호</label> 
    <span id='f-no'>${community.no}</span><br>
    
    <label for='f-title'>제목</label>
    <span id='f-title'>${community.title}</span><br>
    
    <label for='f-content'>내용</label> 
    <span id='f-content'>${community.content}</span><br>
    
    <label for='f-writer'>작성자</label> 
    <span id='f-writer'>${community.writer.email}</span><br>
    
    <label for='f-viewCount'>조회수</label> 
    <span id='f-viewCount'>${community.viewCount}</span><br>
    
    <label for='f-like'>좋아요</label> 
    <span id='f-like'>${community.like}</span><br>
    
    <label for='f-registeredDate'>등록일</label> 
    <span id='f-registeredDate'>${community.registeredDate}</span><br>
    
<c:choose>
<c:when test="${loginUser eq null}">
<a href='/swu/community/list?categoryNo=${community.category}'>[목록]</a><br>
</c:when>

<c:when test="${checkWriter eq 1}">
<a href='updateform?no=${community.no}'>[수정]</a> 
<a href='delete?no=${community.no}'>[삭제]</a> 
<a href='/swu/community/list?categoryNo=${community.category}'>[목록]</a><br>
</c:when>

<c:when test="${checkWriter eq 2}">
<c:if test="${result eq 0}">
<a href='/swu/community/likes/add?no=${community.no}'>[좋아요 추가]</a>
</c:if>
<c:if test="${result eq 1}">
<a href='/swu/community/likes/delete?no=${community.no}'>[좋아요 삭제]</a>
</c:if>

<a href='/swu/community/list?categoryNo=${community.category}'>[목록]</a><br>
</c:when>
</c:choose>
</form>

   <br>
      <div id= "comments">
        <div id="comments-head" class="comment-row">
          <span id="comments-count">${fn:length(comments)}</span>
        </div>
      </div>
      
    <form action='/swu/community/comment/add'>
      <div class="comment-row">
      <input type="hidden" id="communityNo" name="communityNo" value="${community.no}">
        <textarea id="content" name="content" rows=2 placeHolder="댓글 입력"></textarea>
        <br>
        <button type="submit">작성</button>
      </div>
    </form>

    <c:forEach items="${comments}" var="comment">
      <div class="comment-row">
        ${comment.registeredDate} &nbsp ${comment.writer.email}&nbsp${comment.content} &nbsp
        <a href='/swu/community/comment/delete?commentNo=${comment.no}&communityNo=${comment.communityNo}'>[삭제]</a>
        </div>
       </c:forEach>
</body>
</html>
