<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
  <title>무료 스터디</title>
<style>
  
  
  
  #input1 {
    width: 80px;
    height: 30px;
    border-radius: 15px;
    background-color: rgb(139, 48, 230);
    color: rgb(228, 228, 228);
    xborder: 5px solid rgb(129, 26, 231);
    box-shadow: 3px 3px 0px 0px rgb(19, 17, 17);
    margin: 5px;
  }

  
body {
  border: solid 3px rgb(139, 48, 230);
  padding: 10px;
  line-height: 20px
}

xdiv {
  border: solid 4px rebeccapurple;
  margin: 20px;
  color: white;
  width: 350px;
  display: inline-block;
}




</style>




</head>
<body>
  <h1>무료 스터디 조회</h1>
  <p>
  <div id="status">스터디 진행상태</div>

  <div class="ola-booking">
    <div class="booking-tab">
        <div class="tab-btn-wrapper text-center">
            <a href="/" class="tab tab-active">모집중</a> </div>
        <div class="tab-btn-wrapper text-center">
            
                <a href="/outstation" class="tab">Outstation</a>
            
        </div>
        <div class="tab-btn-wrapper text-center">
            
                <a href="/rentals" class="tab">Rentals</a>
            
        </div>
        <div class="clearfix"></div>
    </div>
    <iframe src="https://bookingwidget.olacabs.com" class="city-iframe"></iframe>
</div>
    <div class="booking-tab">
    <div class="tab-btn-wrapper text-center">
                                    
    <a href="/" class="tab tab-active">City Taxi</a>
                                    
    </div>
    <div class="tab-btn-wrapper text-center">
                                    
    <a href="/outstation" class="tab">Outstation</a>
                                    
    </div>
    <div class="tab-btn-wrapper text-center">
                                    
    <a href="/rentals" class="tab">Rentals</a>
                                    
    </div>
    <div class="clearfix"></div>
    </div>



  <input id="input1" type="submit" value="모집중">
  <input id="input1" type="submit" value="진행중">
  <input id="input1" type="submit" value="진행완료">
  
  <hr size="2" noshade color="gray">

</p>

  <a href='form'>무료 스터디 작성</a>
  <br>
  <table border='1'>
    <thead>
      <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>시작일</th>
        <th>종료일</th>
        <th>온오프라인</th>
        <!-- 오프라인일 경우만 -->
        <th>지역</th> 
        <th>모집인원</th>
        <th>등록일</th>
        <th>조회수</th>
        <th>좋아요수</th>
    </tr>
  </thead>
  <tbody>

<c:forEach items="${freeStudyList}" var="freeStudy">

  <c:choose>
  <c:when test="${freeStudy.studyStatus eq 0}">
<c:set var="type" value="모집중"/>
  </c:when>
  </c:choose>
  <c:choose>
  <c:when test="${freeStudy.studyStatus eq 1}">
<c:set var="type" value="진행중"/>
  </c:when>
  </c:choose>
  <c:choose>
  <c:when test="${freeStudy.studyStatus eq 2}">
<c:set var="type" value="진행완료"/>
  </c:when>
  </c:choose>
  
  <tr>
    <td>${freeStudy.no}</td>
    <td><a href='detail?no=${freeStudy.no}'>${freeStudy.title}</a></td> 
    <td>${freeStudy.writer.name}</td> 
    <td>${freeStudy.startDate}</td>
    <td>${freeStudy.endDate}</td>
    <td>${freeStudy.onOffLine}</td>
    <td>${freeStudy.area}</td>
    <td>${freeStudy.members}/${freeStudy.maxMembers}</td>
    <td>${type}</td>
    <td>${freeStudy.registeredDate}</td>
    <td>${freeStudy.viewCount}</td>
    <td>${freeStudy.likes}</td>
  </tr>
</c:forEach>

</tbody>
</table>
</body>
</html>









