<%@page import="com.studywithus.domain.Study"%>
<%@page import="com.studywithus.dao.StudyDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>스터디위더스 : 멘토링상세보기</title>

  <base target="_self"/>
  
   <link rel="stylesheet" href="${contextPath}/css/theme.css">
    <link rel="stylesheet" href="${contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${contextPath}/css/study/StudyDetail.css">
    
    <style type="text/css">
    .interest_icon {
      width : 50px;
    }
    
	 #close, button {
	 all : unset;
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
  width: 490px;
  height: 550px;
  background-color: white;
  padding: 30px 0px;
  padding-left : 30px;
  border-radius: 10px;
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.19), 0 6px 6px
    rgba(0, 0, 0, 0.23);
}
.form_box {
  margin : 35px;
}
h1 {
  margin: 0;
}
.hidden {
  display: none;
}
.button_box {
display : flex;
justify-content : flex-end;
margin-right : 25px;
text-align: center;
}
.request_button, #close {
  margin-right : 15px;
}
    </style>
</head>

<body>
  <div class="container">
  <jsp:include page="../header.jsp"></jsp:include>
  
  <header class="freepagetop">

    <form action="detail" >
    <fieldset class="menu">

      <span class="study-top-status-2">
          <h1 class="study-content-category">
            멘토링 상세보기
            <input class="input5" id='f-status' type='text' name='status' value='${chargeStudy.studyStatus}' readonly>
            <span class="study-registered-date" id='f-registeredDate'>${chargeStudy.registeredDate}</span>
          </h1>
      </span>
      
      <hr class="study-hr">

      <div class="icon-form-group" align: right;>
        <ul class="uldesign">          
          <img class="icon-top" src="${contextPath}/img/category.png">
          <input class="input3" id='f-category' type='text' name='category' value='${chargeStudy.category}' readonly>
          
          <img class="icon-top" src="${contextPath}/img/area.png">
          <input class="input3" id='f-area' type='text' name='area' value='${chargeStudy.area}' readonly>
        
          <img class="icon-top" src="${contextPath}/img/people.png">
          <input class="input3" id='f-members/maxMembers' type='text' name='members/maxMembers' value='${chargeStudy.members} / ${chargeStudy.maxMembers}' readonly>
          
          <img class="icon-top" src="${contextPath}/img/won.png">
          <input class="input3" id='f-price' type='text' name='members/maxMembers' value='${chargeStudy.price}' readonly>
        </ul>
      </div>

<!--
  <label for='f-no'>번호</label> 
  <input id='f-no' type='text' name='no' value='${chargeStudy.no}' readonly><br>

    <div class="form-group">
      <label for='f-price'>금액</label> 
      <input id='f-price' type='text' name='price' value='${chargeStudy.price}' readonly><br>
    </div>
-->

    <div class="form-group">
      <label for='f-title'>제목</label>
      <input id='f-title' type='text' name='title' value='${chargeStudy.title}' readonly><br>
    </div>
    
    <div class="form-group">
      <label for='f-writer'>작성자</label> 
      <input id='f-writer' type='text' name='writer' value='${chargeStudy.writer.nickname}' readonly><br>
    </div>

    <div class="form-group">
      <label class="study-content2" for='f-content'>내용</label> 
      <input class="study-content-box" id='f-content' type='text' 
      name='content' cols="69" rows="10" value='${chargeStudy.content}' readonly><br>
    </div>
    
    <label for='f-startDate'>시작일</label> 
    <input id='f-startDate' type='date' name='startDate' value='${chargeStudy.startDate}' readonly><br>
    
    <label for='f-endDate'>종료일</label> 
    <input id='f-endDate' type='date' name='endDate' value='${chargeStudy.endDate}' readonly><br>
    

		<!-- 관심목록 추가/삭제 아이콘 출력 -->
		<!-- 글쓴이(0) = 비회원 -->
		<section class="study-info-icon">
			<div class="item2">
				<c:if test="${loginUser eq null}">
						<div class="info_item">
						<img class="icon" src="${contextPath}/img/fillingHeartIcon.png">
						<p class="icon_count">${chargeStudy.likes}</p> 
						
						<img class="icon" src="${contextPath}/img/eyeIcon.png">
						<p class="icon_count">${chargeStudy.viewCount}</p>
					</div> <!--info_item-->
				</c:if> <!--글쓴이(0) 비회원-->
			</div> <!--item2-->
		</section>

		
		<section class="study-info-icon">
			<div class="item2">
				<!-- 회원(1) = 작성자-->
				<c:if test="${chargeStudy.writer.no eq loginUser.no}">
					<!-- 관심목록 추가 전 상태인 경우 추가 버튼 출력 -->
						<c:if test="${result eq 0}">
							<div class="info_item">
								<img class="icon" src="${contextPath}/img/fillingHeartIcon.png">
								<p class="icon_count">${chargeStudy.likes}</p> 
								
								<img class="icon" src="${contextPath}/img/eyeIcon.png">
								<p class="icon_count">${chargeStudy.viewCount}</p>

								<a href='interest/add?no=${chargeStudy.no}'><img class="icon" src="${contextPath}/img/interestAdd.png"></a>
								<p class="icon_count">${chargeStudy.likes}</p> 
							</div> <!--info_item-->
						</c:if> <!-- 글쓴이(1) 관심목록 추가 전 -->
				
						<!-- 관심목록 추가 상태인 경우 삭제 버튼 출력 -->
						<c:if test="${result eq 1}">
							<div class="info_item">
								<img class="icon" src="${contextPath}/img/fillingHeartIcon.png">
								<p class="icon_count">${chargeStudy.likes}</p> 
								
								<img class="icon" src="${contextPath}/img/eyeIcon.png">
								<p class="icon_count">${chargeStudy.viewCount}</p>

								<a href='interest/delete?no=${chargeStudy.no}'><img class="icon" src="${contextPath}/img/interestDelete.png"></a>
								<p class="icon_count">${chargeStudy.likes}</p> 
							</div> <!--info_item-->
						</c:if> <!-- 글쓴이(1) 관심목록 추가 상태인 경우 -->
				</c:if> <!--checkWriter eq 1-->
			</div> <!-- item2 -->
	  	</section> <!--  회원(1) = 작성자 -->


	  <section class="study-info-icon">
			<div class="item2">
				<!-- 회원(2) != 작성자-->
				<c:if test="${chargeStudy.writer.no ne loginUser.no}">
					<!-- 관심목록 추가 전 상태인 경우 추가 버튼 출력 -->
					<c:if test="${result eq 0}">
						<div class="info_item">
							<img class="icon" src="${contextPath}/img/fillingHeartIcon.png">
								<p class="icon_count">${chargeStudy.likes}</p> 
								
								<img class="icon" src="${contextPath}/img/eyeIcon.png">
								<p class="icon_count">${chargeStudy.viewCount}</p>

								<a href='interest/add?no=${chargeStudy.no}'><img class="icon" src="${contextPath}/img/interestAdd.png"></a>
								<p class="icon_count">${chargeStudy.likes}</p> 
							</div> <!--info_item-->
						</c:if> <!-- 회원(2) 관심목록 추가 전 -->
							
						<!-- 관심목록 추가 상태인 경우 삭제 버튼 출력 -->
						<c:if test="${result eq 1}">
							<div class="info_item">
								<img class="icon" src="${contextPath}/img/fillingHeartIcon.png">
								<p class="icon_count">${chargeStudy.likes}</p> 
								
								<img class="icon" src="${contextPath}/img/eyeIcon.png">
								<p class="icon_count">${chargeStudy.viewCount}</p>

								<a href='interest/delete?no=${chargeStudy.no}'><img class="icon" src="${contextPath}/img/interestDelete.png"></a>
							</div> <!--info_item-->
						</c:if> <!-- 회원(2) 관심목록 추가 상태인 경우 -->
				</c:if> <!-- checkWriter eq 2 -->
			</div> <!-- item2 -->
	  	</section> <!--  회원(2) != 작성자 -->
		<!-- 관심목록 추가/삭제 아이콘 출력 끝!!! -->


    <!--
        <label for='f-category'>카테고리</label> 
        <input id='f-category' type='text' name='category' value='${chargeStudy.category}' readonly><br>
        
        <label for='f-maxMembers'>모집인원</label> 
        <input id='f-maxMembers' type='number' name='maxMembers' value='${chargeStudy.maxMembers}' readonly><br>
        
        <label for='f-members'>현재인원</label> 
        <input id='f-members' type='number' name='members' value='${chargeStudy.members}' readonly><br>
        
        <label for='f-likes'>좋아요</label> 
        <input id='f-likes' type='text' name='likes' value='${chargeStudy.likes}' readonly><br>
        
        <label for='f-viewCount'>조회수</label> 
        <input id='f-viewCount' type='text' name='viewCount' value='${chargeStudy.viewCount}' readonly><br>
        
        <label for='f-registeredDate'>등록일</label> 
        <span id='f-registeredDate'>${chargeStudy.registeredDate}</span><br>
    -->

    
<a href='list'>목록</a><br>
    
    <c:if test="${chargeStudy.writer.no eq loginUser.no}">
      <a class="input-button-bottom" href='updateform?no=${chargeStudy.no}'>수정</a> 
        <c:if test="${chargeStudy.deleteStatus eq 0}">
          <a class="input-button-bottom" id ="open" href='#'>삭제요청</a> 
        </c:if>

        <c:if test="${chargeStudy.deleteStatus eq 1}">
          <input class="input-button-bottom" type="button" onclick="reqCancel();" value="삭제요청 취소">
        </c:if>
    </c:if>
    
    <c:if test="${checkWriter eq 2}">
<!-- 결제 취소 조건 좀 더 고민해야함! 
(스터디멤버 테이블에서 스터디번호와 회원번호 주고 멤버상태에 따라 구분할 지 ,
 결제테이블에서 스터디번호와 회원번호 주고 존재 여부로 확인할지) 
<a class="input-button-bottom" href='#'>결제</a>
<a class="input-button-bottom" href='#'>결제취소</a>
 -->
    </c:if>
	</form>
	
	<!-- 모달창 -->
    <!--이벤트 발생 시 hidden 삭제-->
    <div class="modal hidden">
      <!--모달 활성화 시 흐린 배경 표현-->
      <div class="modal_overlay">
        <!--모달 화면-->
        <div class="modal_content">
          <div class="form_box">
          <h1>삭제요청서 등록</h1>
            <form action='deleterequest' method='post'>
             <input type ="hidden" name="no" value="${chargeStudy.no}"/>
							<label for='reason'></label>
							<textarea name ="reason" cols="50" rows="15" placeholder="삭제 사유를 입력해주세요."></textarea>
							<br>
							<div class = "button_box">
							<input type="reset" id="close" onclick=reqCancel value="취소">
							<button type="submit" class ="request_button">등록</button>
							</div>
            </form>
          </div>
        </div> <!-- modal_content -->
      </div> <!-- modal_overlay -->
    </div> <!-- modal_hidden -->
</div>

<script>
const reqCancel = () => {
	 if (!confirm("삭제요청을 취소하시겠습니까?")) {
	     console.log(1);
	          return;
	      } else {
	     console.log(2);
	    location.href = "deletecancel?no=${chargeStudy.no}";
	      }
	     console.log(3);
}
</script>
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
//모달창 내부의 닫기 버튼

//overlay.addEventListener('click', closeModal);
//모달창 영역 밖
</script>

<jsp:include page="../footer.jsp"></jsp:include>
</body> <!--유료스터디 제일 큰 포맷-->
</html>
