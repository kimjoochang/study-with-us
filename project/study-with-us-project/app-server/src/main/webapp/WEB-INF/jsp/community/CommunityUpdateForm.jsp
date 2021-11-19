<%@page import="com.studywithus.domain.Comment"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="com.studywithus.dao.CommentDao"%>
<%@page import="com.studywithus.domain.Community"%>
<%@page import="java.util.Collection"%>
<%@page import="com.studywithus.dao.CommunityDao"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디위더스 : 커뮤니티수정</title>

<link rel="stylesheet" href="${contextPath}/css/bootstrap.css">
<link rel="stylesheet" href="${contextPath}/css/theme.css">
</head>

<style>
    body {
        width: 2000px;
        margin: 0 auto;
    }
    
    .wrapper {
        width: 500px;
    }
    
    .cmnt_title {
        margin-bottom: 50px;
        text-align: center;
    }
    
    .cmnt_type {
        margin-top: 20px;
    }
    
    .category {
        padding-bottom: 10px;
    }
    
    .cmnt_info {
        display: flex;
        justify-content: space-between;
    }
    
    .cmnt_body {
        border-top: solid 2px;
        margin: 10px 0px;
    }
    
    .cmnt_content {
        margin-top: 20px;
        height: 200px;
    }
    
    .cmnt_icon {
        display: inline-block;
        text-align: right;
        xdisplay: flex;
        justify-content: space-between;
    }
    
    .community_buttons {
        display: flex;
        justify-content: space-between;
        width: 90px;
    }
    
    #community_button {
        font-size: 12px;
    }
    
    .info_item {
        align-items: center;
        box-sizing: border-box;
        color: rgb(0, 0, 0);
        cursor: pointer;
        display: flex;
        font-family: "Spoqa Han Sans Neo", -apple-system, BlinkMacSystemFont,
            "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, "Fira Sans",
            "Droid Sans", "Helvetica Neue", sans-serif;
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
        color: black;
        margin-left: 5px;
    margin-top : 0.8rem;
    }
    
    .comment_header {
        font-size: 20px;
        font-weight: bold;
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
        margin-top: 10px;
        display: flex;
        justify-content: flex-end;
    }
    
    .comment_list {
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
    
    .profile {
        width: 10%;
        margin-right: 30px;
    }
    
    .comment_write_info {
        font-size: 12px;
        margin-bottom: 20px;
        display: flex;
    }
    
    .comment_content {
        font-weight: normal;
        font-size: 15px;
        margin-bottom: 10px;
    }
    
    .wrapper {
        margin: 0 auto;
        margin-top: 100px;
        text_align: center;
    }
    
    .comment_content_box {
        display: flex;
        justify-content: space-between;
    }
    
    #delete_comment_button {
        color: black;
        font-size: 10px;
    }
    
    .hidden {
        display: none;
    }
</style>


<body>
	<div class="container">
        <jsp:include page="../header.jsp"></jsp:include>
        
        <form action='update' method='post'>
		<c:choose>
			<c:when test="${community.no eq 0}">
				<c:set var="type" value="정보" />
			</c:when>

			<c:when test="${community.no eq 1}">
				<c:set var="type" value="질문" />
			</c:when>

			<c:when test="${community.no eq 2}">
				<c:set var="type" value="스몰톡" />
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
    <div>
        <label for='f-no'>번호</label> 
        <input class="comment_input" id='f-no' type='text' name='no' value='${community.no}' readonly><br>
    </div>
    
    <div>
        <label for='f-title'>제목</label>
        <input id='f-title' type='text' name='title' value='${community.title}'><br>
    </div>

    <div>
        <label for='f-content'>내용</label> 
        <textarea name="content" id="textarea" cols="50" rows="5">${community.content}'></textarea><br>
    </div>

    <div>
        <label for='f-writer'>작성자</label> 
        <input id='f-writer' type='email' value='${community.writer.email}' readonly><br>
    </div>

    <div>
        <label for='f-registeredDate'>등록일</label> 
        <span id='f-registeredDate'>${community.registeredDate}</span><br>
    </div>

    <div class="cmnt_icon">
        <div class="info_item">
            <img class="icon" src="${contextPath}/img/eyeIcon.png">
            <p class="icon_count">${community.viewCount}</p>
            
            <div class="info_item">
                <img class="icon" src="${contextPath}/img/fillingHeartIcon.png">  
                <p class="icon_count">${community.like}</p>
            </div>
        </div>
    </div> <!--cmnt_icon-->
    
    
    <div class="community_buttons">
        <c:if test="${loginUser.no eq community.writer.no}">
            <button type = "submit">수정</button>
            <a id="community_button" href="detail?no=${community.no}">취소</a>
        </c:if>
    </div>
    <!--
        <button>변경</button> 
        <a href='detail?no=${community.no}'>취소</a>

        <a id="community_button" href="updateform?no=${community.no}">수정</a>
        <a id="community_button" href="delete?no=${community.no}">삭제</a>
    -->
</section> <!--cmnt_body-->
</div> <!--wrapper-->

</form>
</div> <!--container-->

<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>

