<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디위더스 : 스터디관리</title>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap"
	rel="stylesheet">

<link rel="stylesheet" href="${contextPath}/css/bootstrap.css">

<link rel="stylesheet" href="${contextPath}/css/theme.css">

<style>

table {
table-layout: fixed;
word-break : break-all;
}

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
  position:fixed;
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

h1 {
  margin: 0;
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

						<img src="${contextPath}/img/manager.png">
					</div>
					<li class="userid">admin</li>
					<ul class=sub>
						<li><a href="mentorapplicationlist">회원 관리</a></li>
						<li><a href="deleterequestlist">스터디 관리</a></li>
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
								var="deleteRequestForm" varStatus="vs">
								<tbody>
									<tr>
										<td>${deleteRequestForm.study.title}</td>
										<td>${deleteRequestForm.applicant.name}</td>
										<td>${deleteRequestForm.registeredDate}</td>
										<td><a id="open" onclick="openModal(${vs.index});" href="#"><img class="icon" src="${contextPath}/img/document.png"></a></td>
										<!--deleteRequestForm.reason -->
									</tr>
								</tbody>

								<!-- 모달창 -->
								<!--이벤트 발생 시 hidden 삭제-->
								<div id="${vs.index}" class ="modal hidden">
									<!--모달 활성화 시 흐린 배경 표현-->
									<div class="modal_overlay">
										<!--모달 화면-->
										<div class="modal_content">
											<div class="form_box">
												<form action='requestapprove' method='post'>
												  <input type="hidden" name="no" value="${deleteRequestForm.no}">
												   <input type="hidden" name="studyNo" value="${deleteRequestForm.study.no}">
                          <textarea class="input_content" name="content"
                            id="textarea" cols="40" rows="5">${deleteRequestForm.reason}</textarea>
                          <div class="form_buttons">
                            <input type="submit" value="승인">
                            <input id="modal_close_btn" type="button" onclick="closeModal(${vs.index});" value="거절">
                          </div>
                        </form>
											</div>
										</div>
										<!-- modal_content -->
									</div>
									<!-- modal_overlay -->
								</div>
								<!-- modal_hidden -->
								
								<!-- 거절사유 모달 -->
								<div id="reason_${vs.index}" class ="modal hidden">
                  <!--모달 활성화 시 흐린 배경 표현-->
                  <div class="modal_overlay">
                    <!--모달 화면-->
                    <div class="modal_content">
                      <div class="form_box">
                        <form action='requestreject' method='post'>
                        <input type="hidden" name="studyNo" value="${deleteRequestForm.study.no}">
                          <input type="hidden" name="no" value="${deleteRequestForm.no}">
                          <textarea class="input_content" name= "remarks"
                            id="textarea" cols="40" rows="5" placeholder="거절 사유를 입력하세요."></textarea>
                          <div class="form_buttons">
                            <input type="submit" value="작성">
                            <input id="modal_close_btn" type="button" onclick="closeReasonModal(${vs.index});" value="취소">
                          </div>
                        </form>
                      </div>
                    </div>
                    <!-- modal_content -->
                  </div>
                  <!-- modal_overlay -->
                </div>
                <!-- modal_hidden -->
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script>
 // 삭제사유 모달 여는 함수
	const openModal = (e) => {
	const modal = document.getElementById(e);
		 modal.classList.remove('hidden');
	}
	
	// 삭제사유에서 거절 누르면 거절 사유 여는 함수
	const closeModal = (e) => {
	  const modal = document.getElementById(e);
	     modal.classList.add('hidden');
	 
	 const param = "reason_" + e;
	 
	 console.log(param);
	 const reasonModal = document.getElementById(param);
		reasonModal.classList.remove('hidden');
	  }

	// 거절 사유 모달 여는 함수
	const closeReasonModal = (e) => {
	 const param = "reason_" + e;
	  const reasonModal = document.getElementById(param);
	     reasonModal.classList.add('hidden');
	  }

	</script>

</body>
</html>