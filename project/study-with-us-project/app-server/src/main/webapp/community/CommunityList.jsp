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

<style>
.icon {
  width : 15px;
}

ul {
  list-style : none;
}

.icon_td {
  width : 30px;
}

</style>

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


			<!-- 검색창 -->
			<div class="search-view">
			<form class="search-form">
				<input type="search" value="" placeholder="키워드를 검색해주세요." class="search-input">
				<button type="submit" class="search-button">
						<img class="reading-glasses-icon" src="../img/search icon.png" href="#search"></use>
					<!--
				</button>
					<div class="search-option">
						<div>
							<input name="type" type="radio" value="type-users" id="type-users">
							<label for="type-users">
								<svg class="edit-pen-title">
									<use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#user"></use>
								</svg>
								<span>Users</span>
							</label>
						</div>
						
						<div>
							<input name="type" type="radio" value="type-posts" id="type-posts">
							<label for="type-posts">
								<svg class="edit-pen-title">
									<use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#post"></use>
								</svg>
								<span>Posts</span>
							</label>
						</div>
						<div>
							<input name="type" type="radio" value="type-images" id="type-images">
							<label for="type-images">
								<svg class="edit-pen-title">
									<use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#images"></use>
								</svg>
								<span>Images</span>
							</label>
						</div>
						<div>
							<input name="type" type="radio" value="type-special" id="type-special" checked="">
							<label for="type-special">
								<svg class="edit-pen-title">
									<use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#special"></use>
								</svg>
								<span>Special</span>
							</label>
						</div>
					</div>
				-->
			  </form> 
			</div> <!--검색창-->
			  
			  <svg xmlns="http://www.w3.org/2000/svg" width="0" height="0" display="none">
				
				<symbol id="search" viewBox="0 0 32 32">
				  <path d="M 19.5 3 C 14.26514 3 10 7.2651394 10 12.5 C 10 14.749977 10.810825 16.807458 12.125 18.4375 L 3.28125 27.28125 L 4.71875 28.71875 L 13.5625 19.875 C 15.192542 21.189175 17.250023 22 19.5 22 C 24.73486 22 29 17.73486 29 12.5 C 29 7.2651394 24.73486 3 19.5 3 z M 19.5 5 C 23.65398 5 27 8.3460198 27 12.5 C 27 16.65398 23.65398 20 19.5 20 C 15.34602 20 12 16.65398 12 12.5 C 12 8.3460198 15.34602 5 19.5 5 z" />
				</symbol>

				<!--
					<symbol id="user" viewBox="0 0 32 32">
						<path d="M 16 4 C 12.145852 4 9 7.1458513 9 11 C 9 13.393064 10.220383 15.517805 12.0625 16.78125 C 8.485554 18.302923 6 21.859881 6 26 L 8 26 C 8 21.533333 11.533333 18 16 18 C 20.466667 18 24 21.533333 24 26 L 26 26 C 26 21.859881 23.514446 18.302923 19.9375 16.78125 C 21.779617 15.517805 23 13.393064 23 11 C 23 7.1458513 19.854148 4 16 4 z M 16 6 C 18.773268 6 21 8.2267317 21 11 C 21 13.773268 18.773268 16 16 16 C 13.226732 16 11 13.773268 11 11 C 11 8.2267317 13.226732 6 16 6 z" /></symbol>
						<symbol id="images" viewbox="0 0 32 32">
							<path d="M 2 5 L 2 6 L 2 26 L 2 27 L 3 27 L 29 27 L 30 27 L 30 26 L 30 6 L 30 5 L 29 5 L 3 5 L 2 5 z M 4 7 L 28 7 L 28 20.90625 L 22.71875 15.59375 L 22 14.875 L 21.28125 15.59375 L 17.46875 19.40625 L 11.71875 13.59375 L 11 12.875 L 10.28125 13.59375 L 4 19.875 L 4 7 z M 24 9 C 22.895431 9 22 9.8954305 22 11 C 22 12.104569 22.895431 13 24 13 C 25.104569 13 26 12.104569 26 11 C 26 9.8954305 25.104569 9 24 9 z M 11 15.71875 L 20.1875 25 L 4 25 L 4 22.71875 L 11 15.71875 z M 22 17.71875 L 28 23.71875 L 28 25 L 23.03125 25 L 18.875 20.8125 L 22 17.71875 z" />
						</symbol>
						<symbol id="post" viewbox="0 0 32 32">
							<path d="M 3 5 L 3 6 L 3 23 C 3 25.209804 4.7901961 27 7 27 L 25 27 C 27.209804 27 29 25.209804 29 23 L 29 13 L 29 12 L 28 12 L 23 12 L 23 6 L 23 5 L 22 5 L 4 5 L 3 5 z M 5 7 L 21 7 L 21 12 L 21 13 L 21 23 C 21 23.73015 21.221057 24.41091 21.5625 25 L 7 25 C 5.8098039 25 5 24.190196 5 23 L 5 7 z M 7 9 L 7 10 L 7 13 L 7 14 L 8 14 L 18 14 L 19 14 L 19 13 L 19 10 L 19 9 L 18 9 L 8 9 L 7 9 z M 9 11 L 17 11 L 17 12 L 9 12 L 9 11 z M 23 14 L 27 14 L 27 23 C 27 24.190196 26.190196 25 25 25 C 23.809804 25 23 24.190196 23 23 L 23 14 z M 7 15 L 7 17 L 12 17 L 12 15 L 7 15 z M 14 15 L 14 17 L 19 17 L 19 15 L 14 15 z M 7 18 L 7 20 L 12 20 L 12 18 L 7 18 z M 14 18 L 14 20 L 19 20 L 19 18 L 14 18 z M 7 21 L 7 23 L 12 23 L 12 21 L 7 21 z M 14 21 L 14 23 L 19 23 L 19 21 L 14 21 z" />
						</symbol>
						<symbol id="special" viewbox="0 0 32 32">
							<path d="M 4 4 L 4 5 L 4 27 L 4 28 L 5 28 L 27 28 L 28 28 L 28 27 L 28 5 L 28 4 L 27 4 L 5 4 L 4 4 z M 6 6 L 26 6 L 26 26 L 6 26 L 6 6 z M 16 8.40625 L 13.6875 13.59375 L 8 14.1875 L 12.3125 18 L 11.09375 23.59375 L 16 20.6875 L 20.90625 23.59375 L 19.6875 18 L 24 14.1875 L 18.3125 13.59375 L 16 8.40625 z M 16 13.3125 L 16.5 14.40625 L 17 15.5 L 18.1875 15.59375 L 19.40625 15.6875 L 18.5 16.5 L 17.59375 17.3125 L 17.8125 18.40625 L 18.09375 19.59375 L 17 19 L 16 18.40625 L 15 19 L 14 19.59375 L 14.3125 18.40625 L 14.5 17.3125 L 13.59375 16.5 L 12.6875 15.6875 L 13.90625 15.59375 L 15.09375 15.5 L 15.59375 14.40625 L 16 13.3125 z" />
						</symbol>
					-->
			  </svg>


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
								<input id="close" type="button" value="취소">
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
							<th scope="col"> </th>
							<th scope="col"> </th>
							<th scope="col"> </th>
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
								<td class="icon_td">
								  <img class="icon" src="../img/eyeIcon.png">
  							  <span>${community.viewCount}</span>
                </td>
                <td class="icon_td">
								  <img class="icon" src="../img/fillingHeartIcon.png">
								  <span>${community.like}</span>
                </td>
                <td class="icon_td">
								  <img class="icon" src="../img/speechBalloon.png">
  							  <span>${community.commentCount}</span>
                </td>
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

// 검색창 JS
$('.search-input').focus(function(){
  $(this).parent().addClass('focus');
}).blur(function(){
  $(this).parent().removeClass('focus');
})
  </script>
</body>
</html>

