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
    
    body {
    width : 1000px;
    margin : 0 auto;
    }
    
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

<div class="modal_background"></div>
  <div class="modal_content">
    <div class="modal_close"><a href="#">닫기</a></div>
    
    <c:choose>
      <c:when test="${categoryNo eq 0}">
      <c:set var="type" value="정보"/>
      </c:when>
      
      <c:when test="${categoryNo eq 1}">
      <c:set var="type" value="질문"/>
      </c:when>
      
      <c:when test="${categoryNo eq 2}">
      <c:set var="type" value="스몰톡"/>
      </c:when>
  </c:choose>

    <div class="main">
        <div class="input_form">
            <div class="category_menu">
                <a href='form?categoryNo=0'>정보</a>
                <a href='form?categoryNo=1'>질문</a>
                <a href='form?categoryNo=2'>스몰톡</a>
            </div>
        
         <form action='add' target="CommunityList.jsp">
                <input type='hidden' name='categoryNo' value='${categoryNo}'>
                <input class="input_title" type='text' name='title' size=63 maxlength=30 placeholder="제목을 입력하세요."> 
                <textarea class="input_content" name="content" id="textarea" cols="50" rows="10"></textarea><br><br><br>
            <div class="buttons">
            <input id=button type="submit" onclick="offClick()" value="등록">
            <input id=button type="button" onclick="offClick()" value="취소">
            </div>    
        </form>
        </div>
    </div>
   </div>
 </div>