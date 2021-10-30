<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
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
  </style>
</head>
<body>
<h1>무료 스터디 상세</h1>

<form action='StudyUpdate.jsp'>
    <label for='f-no'>번호</label> 
    <input id='f-no' type='text' name='no' value='<%=freeStudy.getNo()%>' readonly><br>
    
    <label for='f-name'>이름</label>
    <input id='f-name' type='text' name='name' value='<%=freeStudy.getName()%>'><br>
    
    <label for='f-email'>이메일</label> 
    <input id='f-email' type='email' name='email' value='<%=freeStudy.getEmail()%>'><br>
    
    <label for='f-password'>암호</label> 
    <input id='f-password' type='password' name='password'><br>
    
    <label for='f-photo'>사진</label> 
    <input id='f-photo' type='text' name='photo' value='<%=freeStudy.getPhoto()%>'><br>
    
    <label for='f-tel'>전화</label> 
    <input id='f-tel' type='tel' name='tel' value='<%=freeStudy.getTel()%>'><br>
    
    <label for='f-registeredDate'>등록일</label> 
    <span id='f-registeredDate'><%=freeStudy.getRegisteredDate()%></span><br>
<button>변경</button>
 <a href='StudyDelete.jsp?no=<%=freeStudy.getNo()%>'>[삭제]</a> <a href='StudyList.jsp'>[목록]</a><br>
</form>
<%}%>
</body>
</html>
<%! // <== declaration element(tag)
// 자바 서블릿 클래스를 만들 때 그 클래스에 들어갈 변수와 메서드를 이 태그 안에 작성한다.
    StudyDao StudyDao;

    public void jspInit() {
      ServletConfig config = getServletConfig();
      ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
      StudyDao = (StudyDao) 웹애플리케이션공용저장소.getAttribute("StudyDao");
    }
%>









