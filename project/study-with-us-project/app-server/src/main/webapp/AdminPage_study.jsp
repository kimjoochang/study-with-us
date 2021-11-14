<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자페이지 : 회원관리</title>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap"
	rel="stylesheet">

<link rel="stylesheet" href="../css/bootstrap.css">

<link rel="stylesheet" href="../css/theme.css">

<style>
.container {
	margin-bottom: 70px;
}

.myinfo-wrapper {
	padding-bottom: 100px;
	color: #898798;
	overflow: hidden;
}

.both {
	position: fixed;
	display: absolute;
	height: 800px;
}

.userid {
	font-size: 14px;
	color: #3b3a44;
}

ul.sub, .my-menu-list {
	list-style-type: none;
	padding: 0px;
	margin: 0px;
	width: 150px;
	background: #eeeef1;
	height: 785px;
	overflow: hidden;
	position: fixed;
	border-radius: 3px;
}

ul.sub li a {
	text-decoration: none;
	padding: 10px;
	display: block;
	color: rgb(82, 71, 92);
	font-weight: bold;
}

ul.sub li a:hover {
	background: #8e8aad;
	color: #fff;
}

ul.sub li a.home {
	background: rgb(150, 144, 144);
	color: #fff;
	height: auto;
}

.cd1 {
	margin-left: 120px;
}

.divider-sub {
	display: block;
	margin-top: 10px;
	margin-bottom: 16px;
	width: 128px;
	height: 3px;
	border-radius: 40px;
	background-color: #827e9e;
}

.profile-icon {
	text-align: center;
	margin-top: 20px;
	margin-bottom: 10px;
}

.my-menu-list {
	text-align: center;
}

.my-menu-list .sub {
	margin-top: 15px
}

.content-section {
	width: 920px;
	height: 900px;
	background-color: #eeeef1;
	border-radius: 3px;
	margin-top: 0px;
	margin-left: 180px;
}

.page_subject {
	margin-left: 15px;
}

.title {
	font-weight: bold;
	font-size: 20px;
	padding: 20px 0px;
	border-bottom: solid 1px;
}

.table-content {
	text-align: center;
	margin-top: 20px
}

.icon {
	width: 25px;
}

.modal {
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	display: flex;
	justify-content: center;
	align-items: center;
}

.modal_overlay {
	position: fixed;
	width: 100%;
	height: 100%;
}

.modal_content {
	position: fixed;
	top: 30%;
	left: 40%;
	width: 400px;
	height: 450px;
	background-color: white;
	padding: 30px 0px;
	border-radius: 10px;
	box-shadow: 0 10px 20px rgba(0, 0, 0, 0.19), 0 6px 6px
		rgba(0, 0, 0, 0.23);
}

.hidden {
	display: none;
}
</style>

</head>

<body>
	<div class="container">
		<jsp:include page="header.jsp"></jsp:include>

		<div class="col-lg-6 py-3 wow fadeInUp">

			<span class="subhead">admin page</span>
			<!--<h2 class="title-section">나의 정보</h2>-->
			<div class="divider-sub"></div>


			<div class="both">
				<ul class="my-menu-list">
					<div class="profile-icon">

						<img src="img/profile.png">
					</div>
					<li class="userid">admin</li>
					<ul class=sub>
						<li><a href="../mentorapplication/list">회원 관리</a></li>
						<li><a href="../chargestudy/deleterequestlist">스터디 관리</a></li>
						<li><a href="#">캘린더 관리</a></li>
					</ul>
				</ul>
				<div class="content-section">
					<div class="title">
						<span class="page_subject">스터디 관리</span>
					</div>
					<div class="table-content">
						<table class="table">
							<thead>
								<tr>
									<th scope="col">스터디 제목</th>
									<th scope="col">신청자</th>
									<th scope="col">요청일</th>
									<th scope="col">삭제 사유</th>
								</tr>
							</thead>
							<c:forEach items="${deleteRequestFormList}"
								var="deleteRequestForm">
								<tbody>
									<tr>
										<td>${deleteRequestForm.study.title}</td>
										<td>${deleteRequestForm.applicant.name}</td>
										<td>${deleteRequestForm.registeredDate}</td>
										<td><a id="open" href="#"><img class="icon"
												src="../img/document.png"></a></td>
										<!--deleteRequestForm.reason -->
									</tr>
								</tbody>
							<div class="modal hidden">
								<div class="modal_content">
									<div class="form_box">
										<form action='add' target="CommunityList.jsp" method='post'>
											<input type='hidden' name='categoryNo' value='${categoryNo}'>
											<textarea class="input_content" name="content" id="textarea"
										     cols="40" rows="5">${deleteRequestForm.reason}</textarea>
											<div class="form_buttons">
												<input type="submit" onclick="offClick()" value="등록">
												<input id="close" type="button" value="취소">
											</div>
										</form>
									</div>
								</div>
							</div>	
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<script>
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
	</script>


	<script src="../assets/js/jquery-3.5.1.min.js"></script>

	<script src="../assets/js/bootstrap.bundle.min.js"></script>

	<script src="../assets/vendor/wow/wow.min.js"></script>

	<script src="../assets/js/theme.js"></script>

</body>
</html>