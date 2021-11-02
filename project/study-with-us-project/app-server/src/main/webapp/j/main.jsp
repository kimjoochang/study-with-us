
<!doctype html>
<html lang="ko">
  <head>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="stylemain.css">
    <link rel="stylesheet" type="text/css" href="img/all.css">
  </head>

  <style>
  *{
    font-family: 'Noto Sans KR', sans-serif;
    list-style: none;
    text-decoration: none;
    border-collapse: collapse;
    margin:0px;
    padding: 0px;
    color: rgb(44, 43, 43);
  }

  h1 {
    font-size: 48px;
    font-weight: 100;
  }
  .contents1{
    font-size: 20px;
    font-weight: lighter;
  }
  .contents2{
    font-size: 18px;
  }
  .contents3 {
    font-size: 14px;
    font-weight: 100;
  }
  .contents1_bold{
    font-size: 18px;
    font-weight: bold;
  }
  
  .result{
    font-size: 24px;
  }
  
  .intro_bg{
    background-image: url("bg-gray.jpg");
    width: 100%;
    height: 714px;
    line-height: 86px;
  }
  
  .header{
    width:1280px;
    margin:auto;
    display:flex;
    height: 86px;
  }

  .searchArea{
    width:500px;
    height:100px;
    background: rgba(163, 156, 156, 0.5);
    border-radius: 5px;
    margin-top:24px;
  }
  .searchArea > form > input{
    border:none;
    width:250px;
    height:40px;
    background: rgba(0, 0, 0, 0.0);
    color:#fff;
    padding-left:10px;
  }
  .searchArea > form > span{
    width:50px;
    color:#fff;
  }

  .searchKey{
    width:220px;
    height:40px;
  }

  .searchLoc{
    width:220px;
    height:40px;
  }

  .nav{
    display: flex;
    justify-content: flex-end;
    width:calc(1280px - 300px);
    line-height: 86px;
  }
  .nav > li {
    margin-left:84px;
  }
  
  .nav > li > a {
    color: #fff;
  }
  
  .intro_text{
    width:100%;
    margin:131px auto 131px auto;
    text-align: center;
  }
  .intro_text > h1,
  .intro_text > h4{
    color:#fff;
  }
  
  .main_text0{
    width: 100%;
    height:601px;
    margin-top:-132px;
    background: #f1f2f3;
  }
  .main_text0 > h1{
    padding-top:116px;
    text-align: center;
  }
  .main_text0 > .contents1 {
    text-align: center;
  }
  
  .icons{
    display: flex;
    width:1280px;
    height: 302px;
    margin:auto;
  }

  .icons > li {
    flex: 1;
    background: white;
    margin-top:49px;
    height:302px;
    text-align: center;
  }
  .icons > li > .icon_img{
    margin-top:18px;
  }
  
  .icons > li:not(:last-child){
    margin-right: 20px;
  }
  
  .icons .contents2 {
    width:280px;
    margin:auto;
    letter-spacing: -1px;
  }
  .more{
    width:100px;
    height: 30px;
    background: #5f4a63;
    color: #fff;
    font-size: 12px;
    line-height: 30px;
    margin:25px auto;
  }
  .main_text0{
      margin-top:132px;
  }
  .main_text1{
    width: 100%;
    height:659px;
    /* margin-top:-132px; */
    /* background: #2F7AF4 */
  }
  .main_text1 > h1{
    padding-top:50px;
    text-align: center;
  }
  .main_text1 > .contents1 {
    text-align: center;
  }
  .service {
    width:1280px;
    display: flex;
    margin: 49px auto;
    height:427px;
  }
  
  .service > .contents2 {
    padding: 20px;
  }
  .service > .contents2 > h2{
    margin-bottom: 27px;
  }
  
  /* */
  .main_text2 {
    width:100%;
    height:418px;
    background-image: url("image/contact.png");
  }
  
  .main_text2 > ul {
    display: flex;
    padding-top:138px;
  }
  
  .main_text2 > ul > li {
    flex: 1;
    text-align: center;
  }
  .main_text2 > ul > li > div,
  .main_text2 > ul > li > div > h1{
    color: #fff;
  }
  .more2{
    width:220px;
    height:40px;
    border: 1px solid #fff;
    color: #fff;
    line-height: 40px;
    cursor: pointer;
    margin:16px auto;
  }
  
  .footer{
    display: flex;
    background: #1f1f1f;
    padding:30px;
  }
  .footer > div:first-child {
    flex:3;
    text-align: center;
    color: #fff;
  }
  .footer > div:last-child {
    flex:9;
    color: #fff;
  }
  .search{
    display: flex;
    justify-content: center;
  }
  .etc{
    margin-top:20px;
    display: flex;
    justify-content: center;
  }

  </style>


  <body>
    <div class="wrap">
      <div class="intro_bg">
        <div class="header">
            <!--
          <div class="searchArea">
            <form>
              <input type="search" placeholder="키워드를 입력하세요">
              <button><span>search</span></button>
            </form>
            -->
          </div>
          <ul class="nav">
            <li><a href="#">CHARGE STUDY</a></li>
            <li><a href="#">COMMUNITY</a></li>
            <li><a href="#">CALENDAR</a></li>
            <li><a href="#">MORE</a></li>
          </ul>
        </div>
        <div class="intro_text">
          <h1>STUDY WITH US</h1><br>
          <h4 class="contents1">LET'S STUDY WITH US</h4>
        </div>
      </div>
      </div>

      <!-- 아래부터 이제 메인페이지 ? -->
      
        <div class="search">
            <span class="serarchKey">
              <input type="search" placeholder="키워드를 입력하세요">
              <span class="serch_i">
                <i class="fas fa-search"></i></span>
            </span>

            <span class="serarchLoc">
              <input type="search" placeholder="장소를 입력하세요">
              <span class="serch_i">
              <i class="fas fa-search"></i></span>
              
            </span>
        </div>

          <div class="etc">
              <sapn><br>기타 기업<br><br></sapn>
            <span><button>공기업</button></span> 
            <span><button>외국계 기업</button></span>
             <span><button>회화</button></span>
         </div>

      <div class="main_text0">
        <h1>스터디위더스와 함께 어쩌고</h1>
        <div class="contents1">스터디위더스의 메인메뉴를 어쩌고.</div>
       <!-- <i class="far fa-book-reader.jpg"></i> -->
        <ul class="icons">
          <li>
            <div class="icon_img">
              <i class="fas fa-book-reader"></i>
            </div>
            <div class="contents1_bold">스터디 등록/참여</div><br>
            <div class="contents2">
            스터디 설명을 간략하게 적어주나            </div>
            <div class="more">
              MORE
            </div>
          </li>

          <li>
            <div class="icon_img">
              <i class="far fa-calendar-check"></i>
            </div>
            <div class="contents1_bold">주요일정 캘린더</div><br>
            <div class="contents2">
                여기에도 주요일정 캘린더에 대한 설명 적어주고
            </div>
            <div class="more">
              MORE
            </div>
          </li>

          <li>
            <div class="icon_img">
              <i class="fas fa-search-dollar"></i>
            </div>
            <div class="contents1_bold">유료스터디 둘러보기</div><br>
            <div class="contents2">
            유료스터디 참여 독려하는 문구 적어줘서 수익성 극대화            </div>
            <div class="more">
              MORE
            </div>
          </li>
        </ul>

        <div class="bottom">
          JOIN US
         </div>

      </div>

      
      
    

      <div class="footer">
        <div>LOGO</div>
        <div>
          스터디위더스 <br>
          Addr. 서울특별시 강남구 역삼동 12-7번지. <br>
          02 - 123 - 4567 <br>
          COPYRIGHT 2021. TEAM3. ALL RIGHT RESERVED.
        </div>
      </div>
    </div>
  </body>
</html>