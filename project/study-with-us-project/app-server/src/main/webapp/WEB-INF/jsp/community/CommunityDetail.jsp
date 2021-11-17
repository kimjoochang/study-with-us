<%@page import="java.util.List"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티 상세</title>

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
	display: flex;
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
	<jsp:include page="../header.jsp"></jsp:include>
	<div class="container">
		<c:choose>
			<c:when test="${community.category eq 0}">
				<c:set var="type" value="정보" />
			</c:when>

			<c:when test="${community.category eq 1}">
				<c:set var="type" value="질문" />
			</c:when>

			<c:when test="${community.category eq 2}">
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
				<div class="cmnt_content">${community.content}</div>
				<div class="cmnt_icon">
					<div class="community_buttons">
						<c:if test="${loginUser.no eq community.writer.no}">
							<a id="community_button" href="">수정</a>
							<a id="community_button" href="">삭제</a>
						</c:if>
					</div>
					<div class="info_item">
						<img class="icon" src="../img/eyeIcon.png">
						<p class="icon_count">${community.viewCount}</p>
						
						<div class="info_item">
							<img class="icon" src="../img/fillingHeartIcon.png">  
							<p class="icon_count">${community.like}</p>
						</div>
					</div>
				</div>
			</section>

			<section class="comment_header">
				<div class="comment_count">${fn:length(comments)}개의 댓글이 있습니다.
				</div>
			</section>
			<div class="comment_input">
				<form action='comment/add' method='post'>
					<input type='hidden' name='communityNo' value='${community.no}'>
					<textarea name="content" placeholder="댓글을 입력하세요."></textarea>
					<div class="comment_button">
						<input type="submit" value="댓글 등록">
					</div>
				</form>
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
							<div class="comment_content_box">
								<p>${comment.content}</p>
								<c:if test="${loginUser.no eq comment.writer.no}">
									<a id="delete_comment_button"
										href='comment/delete?commentNo=${comment.no}'>삭제</a>
								</c:if>
							</div>
						</section>
					</li>
				</ul>
			</c:forEach>
		</div>
	</div>

	<script>
    const addComment = () => {
     location.replace('comment/add?communityNo=${community.no}')
    }
    
    <!--
    const deleteCommentBtn = document.getElementById('delete_comment_button');
    const CmntBtns = document.getElementById('community_button');
    
    const writerNo = ${community.writer.no};
    const commentWriterNo = "<c:out value='${comment.writer.no}'/>"
    const loginUserNo = "<c:out value='${loginUser.no}'/>"

    const cmntButtonVisible = () => {
    if (loginUserNo != writerNo || String(loginUserNo) == "") {
    	CmntBtns.style.display = "none";
    }
    }
    
    const buttonVisible = () => {
    if (loginUserNo != comment.writer.no || String(loginUserNo) == "") {
    	console.log(1);
    	deleteCommentBtn.style.display = "none";
    }
    }
    
    cmntButtonVisible();
    buttonVisible();
    -->
    </script>
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