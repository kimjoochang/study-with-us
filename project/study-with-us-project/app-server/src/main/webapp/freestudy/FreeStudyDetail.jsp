<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  
  <title>무료 스터디</title>
  <style>
    
  label {
    margin-right: 5px;
    text-align: right;
    display: inline-block;
    width: 60px;
  }

  .freepagetop{
    -webkit-font-smoothing: antialiased;
    background-attachment: scroll;
    font-family: "Noto Sans KR", -apple-system, system-ui, BlinkMacSystemFont, "Apple SD Gothic Neo", "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif, Oxygen, Ubuntu, Cantarell, "Fira Sans", "Droid Sans", Helvetica;
    font-size: 14px;
    font-weight: bolder;
    line-height: 24px;
    padding-top: 32px;
    width: 845px;
    height: 1000px;
  }

  ul {
    float: left;
  }

  .menu {

    background-attachment: scroll;
    background-clip: border-box;
    background-color: rgb(255, 255, 255);

    border-bottom-left-radius: 24px;
    border-bottom-right-radius: 24px;
    border-top-left-radius: 24px;
    border-top-right-radius: 24px;
    box-shadow: rgba(0, 0, 0, 0.15) 0px 5px 25px 0px;
    box-sizing: border-box;

    color: rgb(0, 0, 0);
    cursor: pointer;
    display: list-item;
    text-align: center;
    font-family: "Spoqa Han Sans Neo", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, "Fira Sans", "Droid Sans", "Helvetica Neue", sans-serif;
    width: 750px;
    xheight: 500px;

    margin-bottom: 10px;
    margin-left: 40px;
    margin-right: 40px;
    margin-top: 10px;
    opacity: 1;

    padding-bottom: 24px;
    padding-left: 24px;
    padding-right: 24px;
    padding-top: 24px;

    text-align: left;
    transition-delay: 0s;
    transition-duration: 0.2s;
    transition-property: transform;
    transition-timing-function: ease-in;
  }

  fieldset {
    border: 0px;
  }

  label {
    text-align: left;
    width: 100px;
    margin-left: 10px;
  }
  
  input {
    border: none;
    width: 560px;
  }

  .input3 {
    text-align: center;
    width: 107px;
    height: 30px;
    border-radius: 4px;
    background-color: rgb(246, 245, 252);
    color: rgb(117, 109, 170);
    border: 2px solid rgb(117, 109, 170);
    margin: 5px;
  }

  /*input3,4 속성 같은데 사각형크기 왜 다르니?*/
  .input4 {
    text-align: center;
    font-weight:700;
    font-size: 15px;
    width: 107px;
    height: 30px;
    border-radius: 4px;
    background-color: rgb(255, 255, 255);
    color: rgb(117, 109, 170);
    border: 2px solid rgb(202, 199, 224);
    margin: 5px;
  }

  .info_item{
align-items: center;
box-sizing: border-box;
color: rgb(0, 0, 0);
cursor: pointer;
display: flex;
font-family: "Spoqa Han Sans Neo", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, "Fira Sans", "Droid Sans", "Helvetica Neue", sans-serif;
font-size: 12px;
height: 18px;
xjustify-content: center;
list-style-image: none;
list-style-position: outside;
list-style-type: none;
text-align: left;
width: 50px;
}

.icon {
  width: 20px;
  margin-left: 10px;
}

.icon_count {
  margin-left: 2px;
}


a {
  text-decoration-line: none;
  float: left;
  text-align: center;
    width: 110px;
    height: 30px;
    border-radius: 4px;
    background-color: rgb(117, 109, 170);
    color: rgb(96, 86, 161);
    border: 2px solid rgb(117, 109, 170);
    box-shadow: 2px 2px 0px 0px rgb(77, 72, 72);
    margin: 5px;
}

a:link { color: rgb(246, 245, 252); }
a:visited { color: rgb(210, 207, 226); }
a:link { text-decoration: none; text-shadow: 0 0 24px; }
a:visited { text-decoration: none; text-shadow: none; }
  
  </style>


</head>
<body>
<header class="freepagetop">

  <h1>무료 스터디 상세보기</h1>
  <form action="detail" >

    <ul>
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
</div>
  
</body> <!--무료스터디 제일 큰 포맷-->
</html>



