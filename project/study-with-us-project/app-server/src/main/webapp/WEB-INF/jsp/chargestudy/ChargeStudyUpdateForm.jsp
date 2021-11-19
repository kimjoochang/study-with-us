<%@page import="com.studywithus.domain.Study"%>
<%@page import="com.studywithus.dao.StudyDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>스터디위더스 : 멘토링수정</title>
  <link rel="stylesheet" href="${contextPath}/css/theme.css">
  <link rel="stylesheet" href="${contextPath}/css/bootstrap.css">
  <link rel="stylesheet" href="${contextPath}/css/study/StudyUpdateForm.css">
</head>

<body>
  <div class="container">
    <jsp:include page="../header.jsp"></jsp:include>
      
<header class="freepagetop">

  <form action='update?no=${chargeStudy.no}' method='post'>
    <fieldset class="menu">

      <h1 class="study-content-category">
        스터디 수정 <input class="input5" id='f-status' type='text'
          name='status' value='${chargeStudy.studyStatus}' readonly>
        <span class="study-registered-date" id='f-registeredDate'>${chargeStudy.registeredDate}</span>
      </h1>  
      
    <hr>
  
    
    <!--
      <label for='f-no'>번호</label> 
      <input class="study-update-input" type='text' name='no' value='${chargeStudy.no}' readonly><br>
    -->
    
    
      <div class="form-group">
        <label for='f-title'>제목</label>
        <input id='f-title' type='text' name='title' value='${chargeStudy.title}'><br>
      </div>

      <div class="form-group">  
        <label for='f-writer'>작성자</label> 
        <input class="study-update-input" id='f-writer' type='email' value='${chargeStudy.writer.email}' readonly><br>
      </div>
      
      <div class="form-group">
        <label for='f-content'>내용</label> 
        <input class="study-content-box" id='f-content' type='text' name='content' value='${chargeStudy.content}'><br>
      </div>

      <div class="form-group">
        <label for='f-category'>카테고리</label> 
        <input id='f-category' type='text' name='category' value='${chargeStudy.category}'><br>
      </div>
      
      <div class="form-group">
        <label for='f-price'>금액</label> 
        <input id='f-price' type='text' name='price' value='${chargeStudy.price}' readonly><br>
      </div>
      
      <div class="form-group">
        <label for='f-area'>지역</label> 
        <input id='f-area' type='text' name='area' value='${chargeStudy.area}' readonly><br>
      </div>
      
      <div class="form-group">
        <label for='f-startDate'>시작일</label> 
        <input id='f-startDate' type='date' name='startDate' value='${chargeStudy.startDate}'><br>
      </div>
      
      <div class="form-group">
        <label for='f-endDate'>종료일</label> 
        <input id='f-endDate' type='date' name='endDate' value='${chargeStudy.endDate}'><br>
      </div>
      
      <div class="form-group">
        <label for='f-maxMembers'>모집인원</label> 
        <input id='f-maxMembers' type='number' name='maxMembers' value='${chargeStudy.maxMembers}' readonly><br>
      </div>
      
      <div class="form-group">
        <label for='f-members'>현재인원</label> 
        <input class="study-update-input" id='f-members' type='number' name='members' value='${chargeStudy.members}' readonly><br>
      </div>
        <!--
          <label for='f-viewCount'>조회수</label> 
          <input class="study-update-input" id='f-viewCount' type='text' name='viewCount' value='${chargeStudy.viewCount}' readonly><br>
          
          <label for='f-likes'>좋아요</label> 
          <input class="study-update-input" id='f-likes' type='text' name='likes' value='${chargeStudy.likes}' readonly><br>
          
          <label for='f-registeredDate'>등록일</label> 
          <span id='f-registeredDate'>${chargeStudy.registeredDate}</span><br>
        -->
    
  <!-- 관심목록 추가/삭제 아이콘 출력 -->
	<!-- 글쓴이 = 0,1,2 상관없음 -->
	<section class="study-info-icon">
		<div class="item2">
					<div class="info_item">
					<img class="icon" src="${contextPath}/img/fillingHeartIcon.png">
					<p class="icon_count">${freeStudy.likes}</p> 
						
					<img class="icon" src="${contextPath}/img/eyeIcon.png">
					<p class="icon_count">${freeStudy.viewCount}</p>
				</div> <!--info_item-->
		</div> <!--item2-->
	</section>

    <div class="study-bottom-button">
	    <button class="input3">변경</button>
	    <a class="study-a" href='list'>목록</a><br>
    </div> 

  </fieldset>

	</form>

  </header>
  <jsp:include page="../footer.jsp"></jsp:include>
</div> <!-- container -->

</body>
</html>
