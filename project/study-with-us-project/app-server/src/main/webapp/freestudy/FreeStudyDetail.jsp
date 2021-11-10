<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"
trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <title>무료 스터디</title>
    <link rel="stylesheet" href="../css/theme.css">
    <link rel="stylesheet" href="../css/bootstrap.css">
    <link rel="stylesheet" href="../css/study/StudyDetail.css">
  </head>

<body>
  <div class="container">
  <jsp:include page="../header.jsp"></jsp:include>
  
<header class="freepagetop">

  <h1>무료 스터디 상세보기</h1>
  <form action="detail" >

    <ul class="uldesign">
      <label class="input3" for='f-area'>스터디종류</label> 
      <input class="input4" type="text" value="무료스터디">
  
      <label class="input3" for='f-onOffLine'>온오프라인</label> 
      <input class="input4" id='f-onOffLine' type='number' name='onOffLine' value='${freeStudy.onOffLine}' readonly>
    
      <label class="input3" for='f-area'>지역</label> 
      <input class="input4" id='f-area' type='text' name='area' value='${freeStudy.area}' readonly>
      <br>

    </ul>

    <br>
    <fieldset class="menu">

      <div class="form-group">
        <label for='f-no'>번호</label> 
        <input id='f-no' type='text' name='no' value='${freeStudy.no}' readonly><br>
      </div>
      
      <hr>

      <div>
        
      </div>
      <div class="form-group">
        <label for='f-title'>제목</label>
        <input id='f-title' type='text' name='title' value='${freeStudy.title}' readonly><br>
      </div>
      
      <div class="form-group">
        <label for='f-name'>작성자</label> 
        <input id='f-name' type='text' name='name' value='${freeStudy.writer.name}' readonly><br>
      </div>
      
      <div class="form-group">
        <label for='f-content'>내용</label> 
        <input id='f-content' type='text' name='content' value='${freeStudy.content}' readonly><br>
      </div>
      
      <div class="form-group">
        <label for='f-category'>카테고리</label> 
        <input id='f-category' type='text' name='category' value='${freeStudy.category}' readonly><br>
      </div>
      
      <div class="form-group">
        <label for='f-status'>스터디 진행상태</label> 
        <input id='f-status' type='text' name='status' value='${freeStudy.studyStatus}' readonly><br>
      </div>
      
      <div class="form-group">
        <label for='f-area'>지역</label> 
        <input id='f-area' type='text' name='area' value='${freeStudy.area}' readonly><br>
      </div>

      <div class="form-group">
        <label for='f-onOffLine'>온오프라인</label> 
        <input id='f-onOffLine' type='number' name='onOffLine' value='${freeStudy.onOffLine}' readonly><br>
      </div>
      
      <div class="form-group">
        <label for='f-startDate'>시작일</label> 
        <input id='f-startDate' type='date' name='startDate' value='${freeStudy.startDate}' readonly><br>
      </div>
      
      <div class="form-group">
        <label for='f-endDate'>종료일</label> 
        <input id='f-endDate' type='date' name='endDate' value='${freeStudy.endDate}' readonly><br>
      </div>
      
      <div class="form-group">
        <label for='f-maxMembers'>모집인원</label> 
        <input id='f-maxMembers' type='number' name='maxMembers' value='${freeStudy.maxMembers}' readonly><br>
      </div>
      
      <div class="form-group">
        <label for='f-members'>현재인원</label> 
        <input id='f-members' type='number' name='members' value='${freeStudy.members}' readonly><br>
      </div>
      
      <div class="form-group">
        <label for='f-viewCount'>조회수</label> 
        <input id='f-viewCount' type='text' name='viewCount' value='${freeStudy.viewCount}' readonly><br>
      </div>
      
      <div class="form-group">
        <label for='f-likes'>좋아요</label> 
        <input id='f-likes' type='text' name='likes' value='${freeStudy.likes}' readonly><br>
      </div>
      
      <div class="form-group">
        <label for='f-registeredDate'>등록일</label> 
        <span id='f-registeredDate'>${freeStudy.registeredDate}</span><br>
      </div>

        <section>
          <div class="item2">
            <div class="info_item">
              <img class="icon"
              src="/Users/haseon-yeong/git/study-with-us/project/study-with-us-project/app-server/src/main/webapp/freestudy/fillingHeartIcon.png">
              <p class="icon_count">1</p>
              
              <img class="icon"
              src="/Users/haseon-yeong/git/study-with-us/project/study-with-us-project/app-server/src/main/webapp/freestudy/eyeIcon copy.png">
              <p class="icon_count">1</p>
              
              <img class="icon"
              src="/Users/haseon-yeong/git/study-with-us/project/study-with-us-project/app-server/src/main/webapp/freestudy/speechBalloonIcon copy.png">              
              <p class="icon_count">1</p>
            </div>
          </div> <!-- item2 -->
        </section>

        <c:choose>
          <c:when test="${loginUser eq null}">
            <a href='/swu/freestudy/list' class="button-group"> 목록 </a><br>
          </c:when>
          
          <br>
          <c:when test="${checkWriter eq 1}">
            <a href='updateform?no=${freeStudy.no}' class="button-group"> 수정 </a> 
            <a href='delete?no=${freeStudy.no}' class="button-group"> 삭제 </a> 
            <a href='/swu/freestudy/list' class="button-group"> 목록 </a><br>
            
          </c:when>
          
          <br>
          <c:when test="${checkWriter eq 2}">
            <c:if test="${result eq 0}">
              <a class= "interestIcon" href='/swu/freestudy/interest/add?no=${freeStudy.no}' class="button-group"> 관심목록 추가</a>
            </c:if>
            
            <c:if test="${result eq 1}">
              <a class = "interestIcon" href='/swu/freestudy/interest/delete?no=${freeStudy.no}' class="button-group"> 관심목록 삭제 </a>
            </c:if>
            
          </c:when>
          
        </c:choose>
      </fieldset>

    </form> <!-- freestudy-detail-->
  </header> <!-- freestudy-top-->
</div> <!-- container -->
  
</body> <!--무료스터디 제일 큰 포맷-->

</html>



