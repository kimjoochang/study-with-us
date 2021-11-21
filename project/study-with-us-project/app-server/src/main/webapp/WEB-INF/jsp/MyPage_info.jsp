<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<meta http-equiv="X-UA-Compatible" content="ie=edge">

<meta name="copyright" content="MACode ID, https://macodeid.com/">

<title>마이페이지 : 나의정보</title>

<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap"
	rel="stylesheet">

	<link rel="stylesheet" href="${contextPath}/css/bootstrap.css">

	<link rel="stylesheet" href="${contextPath}/css/theme.css">


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
	height: 810px;
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
	width: 86px;
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
	width: 900px;
	height: 920px;
	background-color: #eeeef1;
	border-radius: 3px;
	margin-top: 0px;
	margin-left: 180px;
}

.subject {
	font-size: 20px;
	margin-left: 30px;
	padding-top: 35px;
}

.box1 {
	width: 800px;
	height: 300px;
	border-top: 2px solid rgb(153, 152, 152);
	border-radius: 3px;
	padding: 30px;
	margin-top: 15px;
	margin-left: 20px;
	box-sizing: border-box
}

.box2 {
	width: 800px;
	height: 150px;
	border-top: 2px solid rgb(153, 152, 152);
	border-bottom: 2px solid rgb(153, 152, 152);
	border-radius: 3px;
	padding: 30px;
	margin-top: 15px;
	margin-left: 20px;
	box-sizing: border-box
}

.box3 {
	width: 800px;
	height: 100px;
	padding: 30px;
	margin-top: 0px;
	margin-left: 10px;
	box-sizing: border-box
}

input {
	all: unset;
}

.attribute {
	text-align: justify;
}

.photo {
	padding: 15px;
	font-size: 12px;
}

.nickname, .email {
	padding: 20px;
	font-size: 12px;
}

.file {
	padding: 20px;
	margin-left: 57px;
}

.pwd {
	font-size: 12px;
	padding-top: 20px;
	padding-right: 22px;
}

.ph {
	margin-top: 0px;
	padding: 20px;
	font-size: 12px;
}

</style>

</head>


<body>
	<jsp:include page="header.jsp"></jsp:include>

		<!-- <div class="page-section" id="myinfo"> -->
		<div class="container">
			<div class="col-lg-6 py-3 wow fadeInUp">

				<span class="subhead">my page</span>
				<!--<h2 class="title-section">나의 정보</h2>-->
				<div class="divider-sub"></div>


				<div class="both">
					<ul class="my-menu-list">
						<div class="profile-icon">

							<img src="${contextPath}/img/profile.png">
						</div>
						<li class="userid">${loginUser.nickname}님</li>
						<ul class="sub">
							<li><a href="#">나의 정보</a></li>
							<li><a href="#">결제 내역</a></li>
							<li><a href="#">관심 목록</a></li>
							<li><a href="#">나의 활동</a></li>
						</ul>
					</ul>
					<div class="content-section">

						<form>
							<section class="all-contents">

								<div class="subject">개인 정보</div>

								<div class="box1">

									<div class="attribute">
										사진 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input class="photo"
											type="text" value="사진을 등록하여 계정 맞춤 설정"></input>
										<!---->
										<input class="file" type="file" name="fileName">
									</div>
									<hr>
									<div class="box">

										닉네임 &nbsp;&nbsp;&nbsp;&nbsp; <input class="nickname"
											value="쫄지마"></input>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button" class="btn btn-primary btn-sm">변경</button>
										<br> 이메일 <input class="email"
											type="email" name="email" id="email"
											value="studywithus@gmail.com"></input>
										
										<br> 비밀번호 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
											class="pwd" type="password" name="my_password"
											id="my_password" value="11111111"></input>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<a href="user/resetpwd"><button type="button" class="btn btn-primary btn-sm">변경</button></a>
									</div>
								</div>


								<div class="subject">연락처 정보</div>
								<div class="box2">

									휴대폰 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input class="ph"
										type="text" value="010-1234-5678"></input>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<button type="button" class="btn btn-primary btn-sm">변경</button>
								</div>
								<div class="box3">
									<span class="with"><button type="button"
											class="btn btn-secondary btn-sm">회원 탈퇴</button>
								</div>
								</span>
					</div>
				</div>


				</section>
				</form>


			</div>

		</div>
		</div>

		</div>
		</div>
		</div>
		<!-- .container -->
		<!-- </div> <!-- .page-section -->





		<script src="../assets/js/jquery-3.5.1.min.js"></script>

		<script src="../assets/js/bootstrap.bundle.min.js"></script>

		<script src="../assets/vendor/wow/wow.min.js"></script>

		<script src="../assets/js/theme.js"></script>
</body>
</html>