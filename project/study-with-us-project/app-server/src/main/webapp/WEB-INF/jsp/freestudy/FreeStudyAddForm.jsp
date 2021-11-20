<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"
trimDirectiveWhitespaces="true" %>

<%@page import="com.studywithus.domain.Member"%>
<%@ page import="java.sql.Date"%>

<% Member loginUser = (Member) session.getAttribute("loginUser"); %>

<!DOCTYPE html>
<html lang="ko">

  <head>
      <title>스터디위더스 : 스터디등록</title>
      <link rel="stylesheet" href="${contextPath}/css/theme.css">
      <link rel="stylesheet" href="${contextPath}/css/bootstrap.css">
      <link rel="stylesheet" href="${contextPath}/css/study/StudyAddForm.css">
      <link rel="stylesheet" href="${contextPath}/css/footer.css">
  </head>

<body>
  <div class="container">
  <jsp:include page="../header.jsp"></jsp:include>
    
  <header class="freepagetop">

    <form action='add' method="post">
      
      <div class="study-box">
      <fieldset class="menu">
        <div class="study-form-content">
          
          <h1 class="study-content-category">스터디 등록</h1>
          <hr>

    <!--
    <div class="study-area-div">
      <div>
      <label class="study-content-label" for='area'>온/오프라인</label>
      <label class="study-content-label"> <input class="study-onoff-radio" type="radio" name="onOffLine"value="0"> 온라인 </label>
      <label class="study-content-label"> <input class="study-onoff-radio" type="radio" name="onOffLine" value="1"> 오프라인 </label>
    </div>  
      -->
      <!--
        <label for='area'>지역</label>
        <select name = onOffLine>
          <option value="" selected="selected">---온/오프라인---</option>
          <option value=0>온라인</option>
          <option value=1 >오프라인</option>
      </select>
        -->
      <div class="study-content-line">
          <label class="study-content-label" for='onOffLine'>온/오프라인</label>
          <input class="study-onoff-radio" type="radio" name="type" value="1" onclick="view()" checked>오프라인 
          <input class = "study-onoff-radio" type="radio" name="type" value="2" onclick="view()">온라인
          
          <div class="study-content-line" id="cattery" style="visibility:hidden;"></div>
        </div> <!--study-content-line-->
         
      <div class="study-content-line">
            <label for='area'>지역</label>
            <select name="h_area1" onChange="cat1_change(this.value,h_area2)" >
              <option>--시/도--</option>
              <option value='서울'>서울</option>
              <option value='부산'>부산</option>
              <option value='대구'>대구</option>
              <option value='인천'>인천</option>
              <option value='광주'>광주</option>
              <option value='대전'>대전</option>
              <option value='울산'>울산</option>
              <option value='강원'>강원</option>
              <option value='경기'>경기</option>
              <option value='경남'>경남</option>
              <option value='경북'>경북</option>
              <option value='전남'>전남</option>
              <option value='전북'>전북</option>
              <option value='제주'>제주</option>
              <option value='충남'>충남</option>
              <option value='충북'>충북</option>
            </select>
          
        <!-- -->
            <script language="javascript">
              function view(){
               var f=document.form;
               if(f.type[0].checked==true){
               document.all.cattery.style.visibility="hidden";
               }else{
               document.all.cattery.style.visibility="visible";
               }
              }
              </script>
        </div> <!--study-content-line-->


<div class="study-content-line">
  <label for='category'>카테고리</label> 
  <select name = category>
    <option value="" selected="selected">---카테고리---</option>
    <option value="일반기업">일반기업</option>
    <option value="공기업" >공기업</option>
    <option value="프로그래밍" >프로그래밍</option>
  </select>
</div>

<div class="study-content-line">
  <label for='title'>제목</label> 
  <input id='title' type='text' name='title' placeholder="제목을 입력해주세요.">
</div>

<div class="study-content-line">
  <label class="study-content-label" for='maxMembers'>정원</label> 
  <input type='number' id='maxMembers' name='maxMembers' placeholder="ex) 4" 정원><br>
</div>
  
<div class="study-content-line">
  <label class="study-content-label" for='content'>내용</label> 
  <textarea class = "study-textarea" cols="69" rows="10" name='content' placeholder="개설할 스터디 내용을 입력해주세요."></textarea>
</div>

<%Date nowDate = new Date(System.currentTimeMillis()); %>

<div class="study-content-line">
  <label class="study-content-label" for='startDate'>시작일</label> 
  <input id='startDate' type='date' min ="<%=nowDate%>" name='startDate'>
</div>
 
<div class="study-content-line">
  <label class="study-content-label" for='endDate'>종료일</label> 
  <input id='endDate' type='date' min ="<%=nowDate%>" name='endDate'>
</div>

</div><!--study-form-content-->

<br>
<div class="study-bottom-button">
  <input class="input5" type="reset" value="초기화">
  <button class="input5">등록</button>
  <button class="input5" type="button" onclick="location.href='list'">취소</button><br>
</div>


</fieldset> <!-- menu-->
</div><!-- "study-box" -->
 </form>
</header>

<jsp:include page="../footer.jsp"></jsp:include>
</div> <!-- container -->

</body>
</html>