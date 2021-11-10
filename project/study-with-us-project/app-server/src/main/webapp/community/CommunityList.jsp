<%@page import="com.studywithus.domain.Community"%>
<%@page import="java.util.Collection"%>
<%@page import="com.studywithus.dao.CommunityDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티 조회</title>

<base target="_self"/>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<link rel="stylesheet" href="../css/bootstrap.css">

<link rel="stylesheet" href="../css/theme.css">

<link rel="stylesheet" href="../css/community/CommunityList.css">

</head>

<body>
	<div class="container">
		<jsp:include page="../header.jsp"></jsp:include>

		<c:choose>
              <c:when test="${community.category eq 0}">
                <c:set var="cmntType" value="정보" />
              </c:when>

              <c:when test="${community.category eq 1}">
                <c:set var="cmntType" value="질문" />
              </c:when>

              <c:when test="${community.category eq 2}">
                <c:set var="cmntType" value="스몰톡" />
              </c:when>
            </c:choose>

		<!-- 글쓰기 버튼 -->
		<div class="add-button">
			<button id="open">글쓰기</button>
		</div>

		<!-- 모달창 -->
		<!--이벤트 발생 시 hidden 삭제-->
		<div class="modal hidden">
			<!--모달 활성화 시 흐린 배경 표현-->
			<div class="modal_overlay">
				<!--모달 화면-->
				<div class="modal_content">
					<div class="form_category_menu">
						<a class="info_box" href='form?categoryNo=0'>정보</a> 
						<a class="qa_box" href='form?categoryNo=1'>질문</a>
						<a class="talk_box" href='form?categoryNo=2'>스몰톡</a>
					</div>
					<div class="form_box">
						<form action='add' target="CommunityList.jsp" method='post'>
							<input type='hidden' name='categoryNo' value='${categoryNo}'>
							<input class="form_input_title" type='text' name='title' size=36
								maxlength=30 placeholder="제목을 입력하세요.">
							<textarea class="input_content" name="content" id="textarea"
								cols="40" rows="5"></textarea>
							<div class="form_buttons">
								<input type="submit" onclick="offClick()" value="등록"> 
								<input id="close" type="button" onclick="offClick()" value="취소">
							</div>
						</form>
					</div>
				</div> <!-- modal_content -->
			</div> <!-- modal_overlay -->
		</div> <!-- modal_hidden -->


		<div class="main-wrapper">
			<div class="side_menu">
				<ul class="categorys">
					<li><a class="info_text" href='list?categoryNo=0'>정보 커뮤니티</a><br>
					<li><a class="qa_text" href='list?categoryNo=1'>질문 커뮤니티</a><br>
					<li><a class="talk_text" href='list?categoryNo=2'>스몰톡 커뮤니티</a><br>
				</ul>
			</div>

			<div class="lists">
				<table class="table">
					<thead>
						<tr>
							<th scope="col">카테고리</th>
							<th scope="col">제목</th>
							<th scope="col">작성자</th>
							<th scope="col">등록일</th>
							<th scope="col"></th>
						</tr>
					</thead>

					<c:forEach items="${communityList}" var="community">
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
						<tbody>
							<tr>
								<td>${type}</td>
								<td><a href='detail?no=${community.no}'>${community.title}</a></td>
								<td>${community.writer.nickname}</td>
								<td>${community.registeredDate}</td>
								<td>

									<ul>
										<li>조회수사진 <span>${community.viewCount}</span>
										</li>
										<li>좋아요사진 <span>${community.like}</span>
										</li>
									</ul>
							</tr>
						</tbody>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>

	<script>
	let type;
	let box;
	let text;

	const changeColor = () => {
	  if (${categoryNo} === 0) {
	    text = document.querySelector('.info_text');
	    box =  document.querySelector('.info_box');

	    text.classList.add('ctry_text_color');
	    box.classList.add('ctry_box_color');
	    
	  } else if (${categoryNo} === 1) {
	    text = document.querySelector('.qa_text');
	    box =  document.querySelector('.qa_box');
	    text.classList.add('ctry_text_color');
	    box.classList.add('ctry_box_color');
	    
	  } else {
	    text = document.querySelector('.talk_text');
	    box =  document.querySelector('.talk_box');

	    text.classList.add('ctry_text_color');
	    box.classList.add('ctry_box_color');
	  }
	}
	
	changeColor();
	
  const openBtn = document.getElementById('open');
//onModal button

const closeBtn = document.getElementById('close');
//offModal button

const modal = document.querySelector('.modal');
//HTML에서의 모달 최상위 요소

const overlay = document.querySelector('.modal_overlay');
//모달창이 활성화되면 흐린 배경을 표현하는 요소

const openModal = () => {
  modal.classList.remove('hidden');
}

const closeModal = () => {
  modal.classList.add('hidden');
}

openBtn.addEventListener('click', openModal);
//onModal

closeBtn.addEventListener('click', closeModal);
//모달창 내부의 닫기 버튼

//overlay.addEventListener('click', closeModal);
//모달창 영역 밖


var trList = document.querySelectorAll("tbody tr"); // 리턴 객체는 HTMLCollection 타입 객체이다.
trList.forEach(function(trTag) {
  trTag.onclick = (e) => {
    window.location.href = e.currentTarget.querySelector("a").href;
  };
});
  </script>
</body>
</html>

