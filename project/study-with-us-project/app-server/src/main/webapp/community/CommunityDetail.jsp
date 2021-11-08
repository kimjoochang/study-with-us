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
 <style>
      body {
        width : 2000px;
        margin : 0 auto;
      }
      .wrapper {
        width : 500px;
      }
      .cmnt_title{
        text-align: center;
        width : 70%
      }
      .cmnt_type {
        margin-top : 20px;
      }
      .category {
        padding-bottom: 10px;
      }
      .cmnt_info {
        display : flex;
        justify-content: space-between;
      }


      .cmnt_body {
        border-top: solid 2px;
        margin : 10px 0px;
      }
      .cmnt_content {
        margin-top : 10px;
      }
      .cmnt_icon{
        display: flex;
        justify-content: flex-end;
      }
      .info_item{
        align-items: center;
        box-sizing: border-box;
        color: rgb(0, 0, 0);
        cursor: pointer;
        display: flex;
        font-family: "Spoqa Han Sans Neo", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, "Fira Sans", "Droid Sans", "Helvetica Neue", sans-serif;
        font-size: 12px;
        height: 39px;
        justify-content: center;
        list-style-image: none;
        list-style-position: outside;
        list-style-type: none;
        text-align: left;
        margin: 0px 20px;
        width: 40.3125px; 
      } 

      .icon {
        width: 20px;
      }

      .icon_count {
        color: white;
        margin-left: 2px;
      }

      .comment_header {
        font-size: 20px;
        font-weight : bold;
      }
      textarea {
      margin-top: 15px;
      width: 100%;
      height: 80px;
      padding: 10px;
      box-sizing: border-box;
      border: solid 2px #1E90FF;
      border-radius: 5px;
      font-size: 12px;
      resize: both;
    }
    .comment_button {
      margin-top : 10px;
      display: flex;
      justify-content: flex-end;
    }
    .comment_list{
      border-bottom: solid 2px;
      list-style: none;
    margin: 0;
    padding: 0;
    width: 100%;
    display: block;
    margin-block-start: 1em;
    margin-block-end: 1em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
    }
    .profile{
      width:10%;
      margin-right: 30px;
    }
    .comment_write_info{
      font-size: 12px;
      margin-bottom: 20px;
      display: flex;
    }
    .comment_content {
      font-weight: normal;
      font-size: 15px;
      margin-bottom: 10px;
    }

    </style>
    
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

<div class="wrapper">
    <section class="cmnt_header">
      <div class="cmnt_title">
        <span>${community.title}</span>
      </div>
      <div class="cmnt_info">
        <p>${community.writer.nickname}</p> 
        <p>${community.registeredDate}</p>
      </div>
      <div class="cmnt_type">
        <span class="category">카테고리 : ${type}</span>
      </div>
    </section>
    
     <section class="cmnt_body">
      <div class="cmnt_content">
       ${community.content}
      </div>
      <div class="cmnt_icon">
        <div class="info_item">
          <img class="icon"
               src="user">              
          <p class="icon_count">${community.viewCount}</p>

          <div class="info_item">
            <img class="icon"
                 src="user">              
            <p class="icon_count">${community.like}</p>
        </div>
      </div>
      </div>
    </section>
    
     <section class="comment_header">
      <div class="comment_count">
        ${fn:length(comments)}개의 댓글이 있습니다.
      </div>
     </section>
   <div class="comment_input">
        <textarea placeholder="댓글을 입력하세요."></textarea>
      </div>

      <div class="comment_button">
        <button type="button" onclick="location.href='/swu/community/comment/add';">댓글 등록</button>
      </div>
      
    <c:forEach items="${comments}" var="comment">
       <ul class="comment_list">
        <li class="comment_wrapper">
          <section class="comment_info">
            <div class="comment_write_info">
              <img class="profile" src="user">
              <div class="comment_member_info">
                <div>${comment.writer.nickname}</div>
                <div>${comment.registeredDate}</div>
              </div>
            </div>
          </section>
          <section class="comment_content">
            ${comment.content}
          </section>
        </li>
      </ul>
    </c:forEach>
    </div>
</body>
</html>

<!--<a href='/swu/community/comment/delete?commentNo=${comment.no}&communityNo=${comment.communityNo}'>[삭제]</a>-->

<!-- 
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
 -->