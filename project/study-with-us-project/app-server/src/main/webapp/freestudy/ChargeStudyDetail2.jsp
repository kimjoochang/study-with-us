<%@page import="com.studywithus.domain.Study"%>
<%@page import="com.studywithus.dao.StudyDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>유료 스터디</title>
  <base target="_self"/>
  
   <link rel="stylesheet" href="../css/theme.css">
    <link rel="stylesheet" href="../css/bootstrap.css">
    <link rel="stylesheet" href="../css/study/StudyDetail.css">
<style>
 input {
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
margin-right : 5px;
text-align: center;
}
.request_button, #close {
  margin-right : 15px;
}
 
</style>
  <base target="_self"/>

   <link rel="stylesheet" href="../css/theme.css">
    <link rel="stylesheet" href="../css/bootstrap.css">
    <link rel="stylesheet" href="../css/study/StudyDetail.css">
</head>

<body>
  <div class="container">
  <jsp:include page="../header.jsp"></jsp:include>
  
  <header class="freepagetop">
    <form action="detail" >

    <fieldset class="menu">
      <h1 class="study-content-category">멘토링 상세보기</h1>
      
     <section class="study-info-icon">
        <div class="item2">
          <div class="info_item">
            <img class="icon"
            src="../img/fillingHeartIcon.png">
            <p class="icon_count">1</p>
            
            <img class="icon"
            src="../img/eyeIcon.png">
            <p class="icon_count">1</p>
            
            <img class="icon"
            src="../img/speechBalloon.png">              
            <p class="icon_count">1</p>
          </div>
        </div> <!-- item2 -->
      </section>

      <hr>

	<label for='f-no'>번호</label> 
    <input id='f-no' type='text' name='no' value='${chargeStudy.no}' readonly><br>
    
    <label for='f-title'>제목</label>
    <input id='f-title' type='text' name='title' value='${chargeStudy.title}' readonly><br>
    
    <label for='f-writer'>작성자</label> 
    <input id='f-writer' type='email' name='writer' value='${chargeStudy.writer.email}' readonly><br>
    
    <label for='f-content'>내용</label> 
    <input id='f-content' type='text' name='content' value='${chargeStudy.content}' readonly><br>
    
    <label for='f-category'>카테고리</label> 
    <input id='f-category' type='text' name='category' value='${chargeStudy.category}' readonly><br>
    
    <label for='f-maxMembers'>모집인원</label> 
    <input id='f-maxMembers' type='number' name='maxMembers' value='${chargeStudy.maxMembers}' readonly><br>
     
    <label for='f-members'>현재인원</label> 
    <input id='f-members' type='number' name='members' value='${chargeStudy.members}' readonly><br>
    
    <label for='f-price'>금액</label> 
    <input id='f-price' type='text' name='price' value='${chargeStudy.price}' readonly><br>
    
    <label for='f-startDate'>시작일</label> 
    <input id='f-startDate' type='date' name='startDate' value='${chargeStudy.startDate}' readonly><br>
    
    <label for='f-endDate'>종료일</label> 
    <input id='f-endDate' type='date' name='endDate' value='${chargeStudy.endDate}' readonly><br>
    
    <label for='f-likes'>좋아요</label> 
    <input id='f-likes' type='text' name='likes' value='${chargeStudy.likes}' readonly><br>
    
    <label for='f-viewCount'>조회수</label> 
    <input id='f-viewCount' type='text' name='viewCount' value='${chargeStudy.viewCount}' readonly><br>
    
    <label for='f-registeredDate'>등록일</label> 
    <span id='f-registeredDate'>${chargeStudy.registeredDate }</span><br>
    
    <div class="study-bottom-button">
    <c:choose>
<c:when test="${loginUser eq null}">
<a href='/swu/chargerstudy/list'>목록</a><br>
</c:when>

<c:when test="${checkWriter eq 1}">
<a href='updateform?no=${chargeStudy.no}'>수정</a> 

<c:if test="${chargeStudy.deleteStatus eq 0}">
<a id ="open" href='#'>[삭제요청 취소]</a> 
</c:if>

<c:if test="${chargeStudy.deleteStatus eq 1}">
<a href='deletecancel?no=${chargeStudy.no}'>[삭제요청 취소]</a> 


</c:if>
<a href='/swu/chargestudy/list'>목록</a><br>
</c:when>

<c:when test="${checkWriter eq 2}">
<c:if test="${result eq 0}">
<a href='/swu/chargestudy/interest/add?no=${chargeStudy.no}'>관심목록 추가</a>
</c:if>

<c:if test="${result eq 1}">
<a href='/swu/chargestudy/interest/delete?no=${chargeStudy.no}'>관심목록 삭제</a>
</c:if>

<a href='/swu/chargestudy/list'>목록</a><br>
</c:when>
</c:choose>
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
							<textarea name ="reason" cols="50" rows="15" placeholder="삭제 사유를 입력해주세요.">
							</textarea>
							<br>
							<div class = "button_box">
							<button id="close" onclick="offClick()">취소</button><br>
							<button type="submit" class ="request_button" onclick="offClick()">등록</button>
							</div>
            </form>
          </div>
        </div> <!-- modal_content -->
      </div> <!-- modal_overlay -->
    </div> <!-- modal_hidden -->
</div>
</fieldset>

</form> <!-- freestudy-detail-->
</header> <!-- freestudy-top-->
</div> <!-- container -->

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

</body> <!--무료스터디 제일 큰 포맷-->
</html>
