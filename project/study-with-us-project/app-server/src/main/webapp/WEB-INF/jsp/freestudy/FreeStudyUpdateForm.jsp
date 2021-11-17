<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
    
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>스터디위더스 : 스터디수정</title>
  <link rel="stylesheet" href="${contextPath}/css/theme.css">
  <link rel="stylesheet" href="${contextPath}/css/bootstrap.css">
  <link rel="stylesheet" href="${contextPath}/css/study/StudyUpdateForm.css">
</head>

<body>
  <div class="container">
    <jsp:include page="../header.jsp"></jsp:include>
      
<header class="freepagetop">
 
  <form action='update?no=${freeStudy.no}'>
    
    <fieldset class="menu">
      
    <!--
      <h1 class="study-content-category">스터디 수정</h1>
    -->

    <h1 class="study-content-category">
      스터디 수정 <input class="input5" id='f-status' type='text'
        name='status' value='${freeStudy.studyStatus}' readonly>
      <span class="study-registered-date" id='f-registeredDate'>${freeStudy.registeredDate}</span>
    </h1>  

  <hr>
    
  <!--
    <label for='f-status'>스터디 진행상태</label> 
    <input id='f-status' type='text' name='status' value='${freeStudy.studyStatus}' readonly><br>
    
    <label for='f-no'>번호</label> 
    <input class="study-update-input" id='f-no' type='text' name='no' value='${freeStudy.no}' readonly><br>
  -->
  
      <div class="form-group">
        <label for='f-title'>제목</label>
        <input id='f-title' type='text' name='title' value='${freeStudy.title}' ><br>
      </div>  

      <div class="form-group">
        <label for='f-name'>작성자</label> 
        <input class="study-update-input" id='f-name' type='text' name='name' value='${freeStudy.writer.name}' readonly><br>
      </div>
      
      <div class="form-group">
        <label for='f-content'>내용</label> 
        <input class="study-content-box" id='f-content' type='text' name='content' value='${freeStudy.content}' ><br>
      </div>

      <div class="form-group">
        <label for='f-category'>카테고리</label> 
        <input id='f-category' type='text' name='category' value='${freeStudy.category}' ><br>
      </div>
      
      <div class="form-group">
        <label for='f-onOffLine'>온오프라인</label> 
        <input id='f-onOffLine' type='number' name='onOffLine' value='${freeStudy.onOffLine}' readonly><br>
      </div>
      
      <div class="form-group">
        <label for='f-startDate'>시작일</label> 
        <input class="study-update-input" id='f-startDate' type='date' name='startDate' value='${freeStudy.startDate}' ><br>
      </div>  

      <div class="form-group">
        <label for='f-endDate'>종료일</label> 
        <input class="study-update-input" id='f-endDate' type='date' name='endDate' value='${freeStudy.endDate}' ><br>
      </div>

      <div class="form-group">
        <label for='f-maxMembers'>모집인원</label> 
        <input id='f-maxMembers' type='number' name='maxMembers' value='${freeStudy.maxMembers}' ><br>
      </div>

      <div class="form-group">
        <label for='f-maxMembers'>현재인원</label> 
        <input class="study-update-input" id='f-maxMembers' type='number' name='members' value='${freeStudy.members}' readonly><br>
      </div>      
        <!--  
          <label for='f-viewCount'>조회수</label> 
          <input class="study-update-input" id='f-viewCount' type='text' name='viewCount' value='${freeStudy.viewCount}' readonly><br>
          
          <label for='f-likes'>좋아요</label> 
          <input class="study-update-input" id='f-likes' type='text' name='likes' value='${freeStudy.likes}' readonly><br>
          
          <label for='f-registeredDate'>등록일</label> 
          <span id='f-registeredDate'${freeStudy.registeredDate}></span><br>
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
      <a class="study-a" href='list'> 목록 </a> 
    </div>
      
</fieldset>

</form>

</div> <!-- container -->

<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>

