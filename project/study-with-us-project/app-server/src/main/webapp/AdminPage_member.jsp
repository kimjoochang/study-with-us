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
						<li><a class="detail_menu" href="../mentorapplication/list">회원 관리</a></li>
						<li><a class="detail_menu" href="../chargestudy/deleterequestlist">스터디 관리</a></li>
						<li><a href="#">캘린더 관리</a></li>
					</ul>
				</ul>
				<div class="content-section">
					<div class="title">
						<span class="page_subject">회원 관리</span>
						<div>
						<a href="../mentorapplication/list">멘토승인 관리</a>
						<a href="#">블랙리스트 관리</a>
						</div>
					</div>
					<div class="table-content">
						<table class="table">
							<thead>
								<tr>
									<th scope="col">신청자</th>
									<th scope="col">요청일</th>
									<th scope="col">신청서 확인하기</th>
								</tr>
							</thead>
							<c:forEach items="${mentorApplicantList}"
								var="mentorApplication">
								<tbody>
									<tr>
										<td>${mentorApplication.applicant.name}</td>
										<td>${mentorApplication.registeredDate}</td>
										<td>아이콘(모달)</td>
										<!--deleteRequestForm.reason -->
									</tr>
								</tbody>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
		</div>

	</div>


	<script src="../assets/js/jquery-3.5.1.min.js"></script>

	<script src="../assets/js/bootstrap.bundle.min.js"></script>

	<script src="../assets/vendor/wow/wow.min.js"></script>

	<script src="../assets/js/theme.js"></script>

</body>
</html>