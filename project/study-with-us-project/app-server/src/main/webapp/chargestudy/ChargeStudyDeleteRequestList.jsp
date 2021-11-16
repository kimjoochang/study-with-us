<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>스터디위더스 : 멘토링삭제요청목록</title>
<style>
ul{
    list-style:none;
}

li{
    height: 30px;
}

a{  
    text-align: center;
    height:20px;
    width: 50px;
    border-radius: 20%;
    margin-top: 2%;
    margin-right: 20px;
    text-decoration: none;
}

#main{
    display: flex;

}

#input_form{
    padding-top: 5%;
    margin: 0 auto;
}

.modal_content{
        display: none;
        width: 500px;
        height: 500px;
        position: absolute;
        top:50%;
        left: 50%;
        margin: -250px 0 0 -250px;
        background:#eee;
        z-index: 2;
    }
    .modal_background{
        display: none;
        position: absolute;
        content: "";
        width: 100%;
        height: 100%;
        background-color:rgba(0, 0,0, 0.5);
        top:0;
        left: 0;
        z-index: 1;
    }
    .modal_close{
        width: 26px;
        height: 26px;
        position: absolute;
        top: -30px;
        right: 0;
    }
    .modal_close> a{
        display: block;
        width: 100%;
        height: 100%;
        background:url(https://img.icons8.com/metro/26/000000/close-window.png);
        text-indent: -9999px;
    }
</style>

<script>
    window.onload = function() {
 
    function onClick() {
        document.querySelector('.modal_content').style.display ='block';
        document.querySelector('.modal_background').style.display ='block';
    }   
    function offClick() {
        document.querySelector('.modal_content').style.display ='none';
        document.querySelector('.modal_background').style.display ='none';
    }
 
    document.getElementById('write_modal').addEventListener('click', onClick);
    document.querySelector('.modal_close').addEventListener('click', offClick);
};
</script>
</head>
<body>
	<h1>삭제요청 스터디 목록</h1>
	<a href='form'>유료 스터디</a>
	<br>
	<table border='1'>
		<thead>
			<tr>
				<th>번호</th>
				<th>스터디 제목</th>
				<th>스터디 시작일</th>
				<th>스터디 종료일</th>
				<th>삭제 요청일</th>
				<th>삭제 사유</th>
			</tr>
		</thead>
		<tbody>
		
  <c:forEach items="${deleteRequestFormList}" var="requestForm">
  
			<tr>
				<td>${requestForm.no}</td>
				<td><a href='detail?no=${requestForm.no}'>${requestForm.study.title}</a></td> 
        <td>${requestForm.study.startDate}</td>
        <td>${requestForm.study.endDate}</td>
        <td>${requestForm.registeredDate}</td>
				<td><button id="write_modal">읽기</button></td> 
      </tr>
<div class="modal_background"></div>
  <div class="modal_content">
    <div class="modal_close"><a href="#">닫기</a></div>
    <div>
    <div id="main">
    <div id="input_form">
        <form action='' target="ChargeStudyDeleteRequestList.jsp">
            
            <textarea name="content" id="textarea" cols="50" rows="10"readonly>${requestForm.reason}</textarea>
        <input id=button type="submit" onclick="offClick()" value="승인">
        <input id=button type="button" onclick="offClick()" value="거절">
        </form>
    </div>
</div>
    </div>
  </div>
  </c:forEach>
 
	</tbody>
	</table>
	</body>
	</html>



