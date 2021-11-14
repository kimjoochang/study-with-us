<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>무료 스터디</title>

<base target="_self" />

<link rel="stylesheet" href="../css/theme.css">
<link rel="stylesheet" href="../css/bootstrap.css">
<link rel="stylesheet" href="../css/study/StudyDetail.css">
</head>

<body>
	<div class="container">
		<jsp:include page="../header.jsp"></jsp:include>

		<header class="freepagetop">

			<form action="detail">
				<fieldset class="menu">

					<span class="study-top-status-2">
						<h1 class="study-content-category">
							스터디 상세보기 <input class="input5" id='f-status' type='text'
								name='status' value='${freeStudy.studyStatus}' readonly>
							<span class="study-registered-date" id='f-registeredDate'>${freeStudy.registeredDate}</span>
						</h1>
					</span>

					<!--
        <div class="form-group">
          <label for='f-registeredDate'>등록일</label> 
        </div>
      -->

					<hr class="study-hr">

					<div class="icon-form-group" align:right;>
						<ul class="uldesign">
							<img class="icon-top" src="../img/category.png">
							<input class="input3" id='f-category' type='text' name='category'
								value='${freeStudy.category}' readonly>

							<img class="icon-top" src="../img/onlineIcon.png">
							<input class="input3" id='f-onOffLine' type='number'
								name='onOffLine' value='${freeStudy.onOffLine}' readonly>

							<img class="icon-top" src="../img/area.png">
							<input class="input3" id='f-area' type='text' name='area'
								value='${freeStudy.area}' readonly>

							<img class="icon-top" src="../img/people.png">
							<input class="input3" id='f-members/maxMembers' type='text'
								name='members/maxMembers'
								value='${freeStudy.members} / ${freeStudy.maxMembers}' readonly>

						</ul>
					</div>

					<!--
        <div class="form-group">
          <label for='f-no'>번호</label> 
          <input id='f-no' type='text' name='no' value='${freeStudy.no}' readonly><br>
        </div>
      -->

					<div class="form-group">
						<label for='f-title'>제목</label> <input id='f-title' type='text'
							name='title' value='${freeStudy.title}' readonly>
					</div>

					<div class="form-group">
						<label for='f-name'>작성자</label> <input id='f-name' type='text'
							name='name' value='${freeStudy.writer.nickname}' readonly>
					</div>

					<div class="form-group">
						<label class="study-content2" for='f-content'>내용</label> <input
							class="study-content-box" id='f-content' type='text'
							name='content' cols="69" rows="10" value='${freeStudy.content}'
							readonly>
					</div>

					<div class="form-group">
						<label for='f-startDate'>시작일</label> <input id='f-startDate'
							type='date' name='startDate' value='${freeStudy.startDate}'
							readonly>
					</div>

					<div class="form-group">
						<label for='f-endDate'>종료일</label> <input id='f-endDate'
							type='date' name='endDate' value='${freeStudy.endDate}' readonly>
					</div>

					<!--
        <div class="form-group">
          <label for='f-category'>카테고리</label> 
          <input id='f-category' type='text' name='category' value='${freeStudy.category}' readonly>
        </div>
        
        <div class="form-group">
          <label for='f-status'>스터디 진행상태</label> 
          <input id='f-status' type='text' name='status' value='${freeStudy.studyStatus}' readonly>
        </div>
        
        <div class="form-group">
          <label for='f-area'>지역</label> 
          <input id='f-area' type='text' name='area' value='${freeStudy.area}' readonly>
        </div>
        
        <div class="form-group">
          <label for='f-onOffLine'>온오프라인</label> 
          <input id='f-onOffLine' type='number' name='onOffLine' value='${freeStudy.onOffLine}' readonly>
        </div>

        <div class="form-group">
          <label for='f-viewCount'>조회수</label> 
          <input id='f-viewCount' type='text' name='viewCount' value='${freeStudy.viewCount}' readonly>
        </div>
        
        <div class="form-group">
          <label for='f-likes'>좋아요</label> 
          <input id='f-likes' type='text' name='likes' value='${freeStudy.likes}' readonly>
        </div>
  
        <div class="form-group">
          <label for='f-maxMembers'>모집인원</label> 
          <input id='f-maxMembers' type='number' name='maxMembers' value='${freeStudy.maxMembers}' readonly>
        </div>
        
        <div class="form-group">
          <label for='f-members'>현재인원</label> 
          <input id='f-members' type='number' name='members' value='${freeStudy.members}' readonly>
        </div>
      -->

					<section class="study-info-icon">
						<div class="item2">
							<div class="info_item">
								<img class="icon" src="../img/fillingHeartIcon.png">
								<p class="icon_count">${freeStudy.likes}</p>

								<img class="icon" src="../img/eyeIcon.png">
								<p class="icon_count">${freeStudy.viewCount}</p>

							</div>
						</div>
						<!-- item2 -->
					</section>
					<a class="input-button-bottom" href='/swu/freestudy/list'> 목록 </a>

					<c:if test="${checkWriter eq 1}">
						<a class="input-button-bottom" href='updateform?no=${freeStudy.no}'>수정</a>
						<c:if test="${freeStudy.deleteStatus eq 0}">
							<a class="input-button-bottom" href='delete?no=${freeStudy.no}'>삭제 </a>
						</c:if>
					</c:if>
					
					<c:if test="${checkWriter eq 2}">
							 <c:if test="${result eq 0}">
		              <a class="input-button-bottom" href='/swu/freestudy/interest/add?no=${freeStudy.no}' class="button-group"> 관심목록 추가</a>
		            </c:if>
		
		            <c:if test="${result eq 1}"> 
		            <a class="input-button-bottom" href='/swu/freestudy/interest/delete?no=${freeStudy.no}' class="button-group"> 관심목록 삭제 </a>
		            </c:if>
		          
		          <c:if test="${participateResult eq 0}">
		          <a class="input-button-bottom" href='apply?no=${freeStudy.no}'>스터디 신청</a>
		          </c:if>
		
		          <c:if test="${participateResult eq 1}">
		          <a class="input-button-bottom" href='applycancel?no=${freeStudy.no}'>스터디 신청 취소</a>
		          </c:if>
           </c:if> 




<!--  
					<div class="study-bottom-button">
						<c:choose>
							<c:when test="${loginUser eq null}">
							</c:when>

							<c:when test="${checkWriter eq 1}">
								<a class="input-button-bottom" href='updateform?no=${freeStudy.no}'> 수정 </a>
								
								<a class="input-button-bottom" href='/swu/freestudy/list'>
									목록 </a>

							</c:when>

							<c:when test="${checkWriter eq 2}">
								<c:if test="${result eq 0}">
									<a class="input-button-bottom"
										href='/swu/freestudy/interest/add?no=${freeStudy.no}'
										class="button-group"> 관심목록 추가</a>
								</c:if>

								<c:if test="${result eq 1}">
									<a class="input-button-bottom"
										href='/swu/freestudy/interest/delete?no=${freeStudy.no}'
										class="button-group"> 관심목록 삭제 </a>
								</c:if>

							</c:when>
						</c:choose>
						<div class="interest-add-button">
							<button id="open">
								<img class="icon" src="../img/fillingHeartIcon.png">
							</button>
						</div>
-->
						<!-- 관심목록 추가하기 버튼 -->
						<!-- interest-add-button -->

					</div>
					<!--study-bottom-button-->
				</fieldset>
				<!--menu-->

			</form>
			<!--detail-->
		</header>
		<!--freestudy-top-->
	</div>
	<!--container-->

	<script>
  
  const openBtn = document.getElementById('open');
  // interest-add-button
  
  const closeBtn = document.getElementById('close');
  // interest-add-button
 
  var trList = document.querySelectorAll("li"); // 리턴 객체는 HTMLCollection 타입 객체이다.
  trList.forEach(function(trTag) {
    trTag.onclick = (e) => {
      window.location.href = e.currentTarget.querySelector("a").href;
    };
  });
  </script>

</body>
<!--무료스터디 제일 큰 포맷-->

</html>



