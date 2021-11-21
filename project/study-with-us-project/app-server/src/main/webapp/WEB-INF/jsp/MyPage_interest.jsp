<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <meta http-equiv="X-UA-Compatible" content="ie=edge">

  <meta name="copyright" content="MACode ID, https://macodeid.com/">

  <title>마이페이지 : 관심목록</title>

  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">

  <link rel="stylesheet" href="${contextPath}/css/bootstrap.css">

 <!-- <link rel="stylesheet" href="../assets/vendor/animate/animate.css"> -->

  <link rel="stylesheet" href="${contextPath}/css/theme.css">

  <link rel="stylesheet" href="${contextPath}/css/footer.css">

</head>

<style>

.container{
  margin-bottom: 70px;
}

.myinfo-wrapper {
  padding-bottom: 100px;
  color: #898798;
  overflow: hidden;
}

.both{
  position: fixed;
  display: absolute;
  height: 800px;
}

.userid{
 font-size:14px;
 color: #3b3a44;
}

ul.sub, .my-menu-list {
  list-style-type: none;
  padding: 0px;
  margin: 0px;
  width: 150px;
  background: #eeeef1;
  height: 810px;
  overflow: hidden;
  position: fixed;
  border-radius: 3px;
}


ul.sub li a {
  text-decoration: none;
  padding: 10px;
  display: block;
  color: rgb(82, 71, 92);
  font-weight: bold;
  
}

ul.sub li a:hover {
  background: #8e8aad;
  color: #fff;
}

ul.sub li a.home {
  background : rgb(150, 144, 144);
  color: #fff;
  height: auto;
  
}

.cd1 {
  margin-left: 120px;
}

.divider-sub {
  display: block;
  margin-top: 10px;
  margin-bottom: 16px;
  width: 86px;
  height: 3px;
  border-radius: 40px;
  background-color: #827e9e;
}

.profile-icon{
  text-align:center;
  margin-top:20px;
  margin-bottom:10px;
}

.my-menu-list{
  text-align:center;
}

.my-menu-list .sub{
  margin-top:15px
}

.content-section{
  width: 900px;
  height: 920px;
  background-color: #eeeef1;
  border-radius: 3px;
  margin-top: 0px;
  margin-left: 180px;
}

.subject{
  font-size: 20px;
  margin-left: 30px;
  padding-top: 35px;
}

.box1{
  width: 800px;
  height: 300px;
  border-top: 2px solid rgb(153, 152, 152);
  border-radius: 3px;
  padding: 30px;
  margin-top:15px;
  margin-left:10px;
  margin-right:20px;
  box-sizing:border-box
}

  .box2{
  width: 800px;
  height: 150px;
  border-top: 2px solid rgb(153, 152, 152);
  border-bottom: 2px solid rgb(153, 152, 152);
  border-radius: 3px;
  padding: 30px;
  margin-top:15px;
  margin-left:10px;
  box-sizing:border-box
}

.box3{
  width: 800px;
  height: 100px;
  
  padding: 30px;
  margin-top:0px;
  margin-left:10px;
  box-sizing:border-box
}

input{
  all: unset;
}

.attribute{
 text-align: justify;
  
}

.photo{
  padding: 15px;
  font-size: 12px;
}

 .nickname, .email {
  padding: 20px;
  font-size: 12px;
}

.file{
  padding: 20px;
  margin-left:57px;
}
.pwd{
  font-size: 12px;
  padding-top:20px;
  padding-right:22px;
}

.ph {
  margin-top: 0px;
  padding: 20px;
  font-size: 12px;
}

/* list tag */


#input1 {
    width: 80px;
    height: 30px;
    border-radius: 4px;
    background-color: rgb(117, 109, 170);
    color: rgb(246, 245, 252);
    border: 2px solid rgb(117, 109, 170);
    box-shadow: 2px 2px 0px 0px rgb(77, 72, 72);
    margin: 5px;
  }

  #input2 {
    float: right;
    width: 110px;
    height: 30px;
    border-radius: 4px;
    background-color: rgb(246, 245, 252);
    color: rgb(117, 109, 170);
    border: 2px solid rgb(117, 109, 170);
    box-shadow: 2px 2px 0px 0px rgb(77, 72, 72);
    margin: 5px;
  }

  #menu {
    background-attachment: scroll;
  background-clip: border-box;
  background-color: rgb(255, 255, 255);
  background-image: none;
  background-origin: padding-box;
  background-position-x: 0%;
  background-position-y: 0%;
  background-size: auto;
  border-bottom-left-radius: 24px;
  border-bottom-right-radius: 24px;
  border-top-left-radius: 24px;
  border-top-right-radius: 24px;
  box-shadow: rgba(0, 0, 0, 0.15) 0px 5px 25px 0px;
  box-sizing: border-box;
  color: rgb(0, 0, 0);
  cursor: pointer;
  display: list-item;
  font-family: "Spoqa Han Sans Neo", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, "Fira Sans", "Droid Sans", "Helvetica Neue", sans-serif;
  height: 240px;
  list-style-image: none;
  list-style-position: outside;
  list-style-type: none;
  margin-bottom: 10px;
  margin-left: 10px;
  margin-right: 10px;
  margin-top: 10px;
  opacity: 1;
  padding-bottom: 24px;
  padding-left: 24px;
  padding-right: 24px;
  padding-top: 24px;
  position: relative;
  text-align: left;
  transition-delay: 0s;
  transition-duration: 0.2s;
  transition-property: transform;
  transition-timing-function: ease-in;
  width: 244px;
  }

#menu2 {
align-items: center;
box-sizing: border-box;
color: rgb(0, 0, 0);
cursor: pointer;
display: flex;
font-family: "Spoqa Han Sans Neo", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, "Fira Sans", "Droid Sans", "Helvetica Neue", sans-serif;
font-size: 12px;
height: 39px;
justify-content: center;
list-style-image: none;
list-style-position: outside;
list-style-type: none;
text-align: left;
width: 31.15625px;
  }

#icon {
  box-sizing: border-box;
color: rgb(154, 154, 154);
cursor: pointer;
display: block;
fill: rgb(154, 154, 154);
font-family: "Spoqa Han Sans Neo", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, "Fira Sans", "Droid Sans", "Helvetica Neue", sans-serif;
font-size: 12px;
height: 14px;
list-style-image: none;
list-style-position: outside;
list-style-type: none;
overflow-x: hidden;
overflow-y: hidden;
stroke: rgb(154, 154, 154);
stroke-width: 0px;
text-align: left;
width: 14px;
}

#h1{
-webkit-box-orient: vertical;
-webkit-line-clamp: 2;
box-sizing: border-box;
color: rgb(0, 0, 0);
cursor: pointer;
display: -webkit-box;
font-family: "Spoqa Han Sans Neo", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, "Fira Sans", "Droid Sans", "Helvetica Neue", sans-serif;
font-size: 16px;
font-weight: normal;
height: 40px;
line-height: 20.239999771118164px;
list-style-image: none;
list-style-position: outside;
list-style-type: none;
margin-block-end: 0px;
margin-block-start: 24px;
margin-bottom: 0px;
margin-inline-end: 0px;
margin-inline-start: 0px;
margin-left: 0px;
margin-right: 0px;
margin-top: 24px;
overflow-x: hidden;
overflow-y: hidden;
padding-bottom: 0px;
padding-left: 0px;
padding-right: 0px;
padding-top: 0px;
text-align: center;
width: 307.5px;
word-break: keep-all;
  }

#closemenu {
  background-color: rgb(0, 0, 0);
  border-bottom-left-radius: 4px;
  border-bottom-right-radius: 4px;
  border-top-left-radius: 4px;
  border-top-right-radius: 4px;
  box-sizing: border-box;
  color: rgb(255, 255, 255);
  cursor: pointer;
  display: block;
  font-family: "Spoqa Han Sans Neo", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, "Fira Sans", "Droid Sans", "Helvetica Neue", sans-serif;
  height: 37px;
  left: 128px;
  list-style-image: none;
  list-style-position: outside;
  list-style-type: none;
  padding-bottom: 8px;
  padding-left: 8px;
  padding-right: 8px;
  padding-top: 8px;
  position: absolute;
  text-align: left;
  top: 120px;
  transform: matrix(1, 0, 0, 1, -39.25, -18.5);
  width: 78.46875px;
}

xbody {
  border: solid 3px color(756daa);
  padding: 10px;
  line-height: 20px
}

.title {
  font-size: 40px;
}
 
@media (max-width: 600px) {
  .title {
    font-size: 20px;
  }
}

.studyTitle{
-webkit-box-orient: vertical;
-webkit-line-clamp: 2;
box-sizing: border-box;
color: rgb(0, 0, 0);
cursor: pointer;
display: -webkit-box;
font-family: "Spoqa Han Sans Neo", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, "Fira Sans", "Droid Sans", "Helvetica Neue", sans-serif;
font-size: 16px;
font-weight: normal;
height: 20px;
line-height: 20.239999771118164px;
list-style-image: none;
list-style-position: outside;
list-style-type: none;
margin-block-end: 0px;
margin-block-start: 24px;
margin-bottom: 0px;
margin-inline-end: 0px;
margin-inline-start: 0px;
margin-left: 0px;
margin-right: 0px;
margin-top: 24px;
overflow-x: hidden;
overflow-y: hidden;
padding-bottom: 0px;
padding-left: 0px;
padding-right: 0px;
padding-top: 0px;
text-align: center;
xwidth: 268px;
word-break: keep-all;
}

main {
  box-sizing: border-box;
  color: rgb(0, 0, 0);
  display: flex;
  flex-direction: column;
  font-family: "Spoqa Han Sans Neo", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, "Fira Sans", "Droid Sans", "Helvetica Neue", sans-serif;
  height: 5422px;
  margin: 0 auto;
xwidth: 711px;
}

.main_main {
 xdisplay: flex;
 flex-direction: column;
 box-sizing: inherit;
}

.ul2 {
box-sizing: border-box;
color: rgb(0, 0, 0);
display: flex;
flex-wrap: wrap;
font-family: "Spoqa Han Sans Neo", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, "Fira Sans", "Droid Sans", "Helvetica Neue", sans-serif;
height: 520px;
list-style-image: none;
list-style-position: outside;
list-style-type: none;
margin-block-end: 160px;
margin-block-start: 0px;
margin-bottom: 160px;
margin-inline-end: 0px;
margin-inline-start: 0px;
margin-left: 0px;
margin-right: 0px;
margin-top: 0px;
padding-bottom: 0px;
padding-inline-start: 0px;
padding-left: 0px;
padding-right: 0px;
padding-top: 0px;
width: 800px;
}

.li2 {
background-attachment: scroll;
background-clip: border-box;
background-color: rgb(255, 255, 255);
background-image: none;
background-origin: padding-box;
background-position-x: 0%;
background-position-y: 0%;
background-size: auto;
border-bottom-left-radius: 24px;
border-bottom-right-radius: 24px;
border-top-left-radius: 24px;
border-top-right-radius: 24px;
box-shadow: rgba(0, 0, 0, 0.15) 0px 5px 25px 0px;
box-sizing: border-box;
color: rgb(0, 0, 0);
cursor: pointer;
display: list-item;
font-family: "Spoqa Han Sans Neo", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, "Fira Sans", "Droid Sans", "Helvetica Neue", sans-serif;
height: 220px;
width: 240px;
list-style-image: none;
list-style-position: outside;
list-style-type: none;
margin-bottom: 10px;
margin-left: 10px;
margin-right: 10px;
margin-top: 10px;
opacity: 1;
padding: 0px;
position: relative;
text-align: center;
transform: matrix(1.02, 0, 0, 1.02, 0, 0);
transition-delay: 0s;
transition-duration: 0.2s;
transition-property: transform;
transition-timing-function: ease-in;
}
/*
.content{
  text-align: center;
}

.img1{
  text-align: center;
}
*/

.subhead{
margin-left:2px;
}

.on_offline {
box-sizing: border-box;
color: rgb(0, 0, 0);
cursor: pointer;
display: block;
font-family: "Spoqa Han Sans Neo", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, "Fira Sans", "Droid Sans", "Helvetica Neue", sans-serif;
height: 100px;
list-style-image: none;
list-style-position: outside;
list-style-type: none;
xtext-align: center;
width:90px;
}

span {
  box-sizing: border-box;
color: rgb(0, 0, 0);
cursor: pointer;
display: flex;
font-family: "Spoqa Han Sans Neo", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, "Fira Sans", "Droid Sans", "Helvetica Neue", sans-serif;
xheight: 82px;
justify-content: center;
list-style-image: none;
list-style-position: outside;
list-style-type: none;
margin-block-end: 0px;
margin-block-start: 0px;
margin-bottom: 0px;
margin-inline-end: 0px;
margin-inline-start: 0px;
margin-left: -20px;
margin-right: 20px;
margin-top: 20px;
padding-bottom: 0px;
padding-inline-start: 0px;
padding-left: 0px;
padding-right: 0px;
padding-top: 0px;
text-align: left;
width: 275.5px;
}

.on_offline_status {
  xbox-sizing: border-box;
cursor: pointer;
xdisplay: block;
font-family: "Spoqa Han Sans Neo", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, "Fira Sans", "Droid Sans", "Helvetica Neue", sans-serif;
font-size: 16px;
xheight: 15px;
xlist-style-image: none;
xlist-style-position: outside;
xlist-style-type: none;
xmargin-block-end: 0px;
xmargin-block-start: 0px;
xmargin-bottom: 0px;
xmargin-inline-end: 0px;
xmargin-inline-start: 0px;
margin-left: -90px;
margin-right: 0px;
margin-top: 100px;
text-align: center;
xwidth: px;
}

section{
  bottom: 8px;
box-sizing: border-box;
color: rgb(0, 0, 0);
cursor: pointer;
display: flex;
font-family: "Spoqa Han Sans Neo", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, "Fira Sans", "Droid Sans", "Helvetica Neue", sans-serif;
font-size: 12px;
height: 39px;
list-style-image: none;
list-style-position: outside;
list-style-type: none;
position: absolute;
right: 16px;
text-align: left;
width: 103.625px;
}
/*8
.info_item{
align-items: left;
box-sizing: border-box;
color: rgb(0, 0, 0);
cursor: pointer;
display: fixed;
font-family: "Spoqa Han Sans Neo", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, "Fira Sans", "Droid Sans", "Helvetica Neue", sans-serif;
font-size: 12px;
height: 39px;
justify-content: center;
list-style-image: none;
list-style-position: outside;
list-style-type: none;
text-align: left;
width: 50.3125px;
}
*/

.icon {
  width: 60px;
  height: 50px;
  margin-left: 180px;
  margin-bottom: 1000px;
}


</style>

<body>
  
  
  <!-- <div class="page-section" id="myinfo"> -->
    <div class="container">
     <jsp:include page="header.jsp"></jsp:include>
       <div class="col-lg-6 py-3 wow fadeInUp">
         
         <span class="subhead">my page</span>
         <!--<h2 class="title-section">나의 정보</h2>-->
         <div class="divider-sub"></div>
         

         <div class ="both">
          <ul class="my-menu-list">
            <div class="profile-icon">
            
            <img src="${contextPath}/img/profile.png">
          </div>
            <li class ="userid">jeje2kim 님</li>
            <ul class=sub>
            <li><a href="#">나의 정보</a></li>
            <li><a href="#">결제 내역</a></li>
            <li><a href="#">관심 목록</a></li>
            <li><a href="#">나의 활동</a></li>
          </ul>
          </ul>
          <div class="content-section"> 


  <section class="all-contents">
  </section>

    <div class="subject"> 관심 목록 </div>

    
    <div class="box1">
      <div class="wrapper">
        <main>
          <div class="main_main">
      
            <ul class="ul2">

              <li class="li2">
                <h1 class="studyTitle"> 제목1 </h1>
                <span>
                  <img class="on_offline"
                  src="../../img/onlineIcon.png">
                  <p class="on_offline_status">온라인, 지명</p>
                </span>
              
                
                    <img class="icon"
                    src="../../img/fillingHeartIcon.png"></img>
                  
                </section>
              </li>

              <li class="li2">
                <h1 class="studyTitle"> 제목1 </h1>
                <span>
                  <img class="on_offline"
                  src="../../img/onlineIcon.png">
                  <p class="on_offline_status">온라인, 지명</p>
                </span>
              
                <div class="info_item">
                    <img class="icon"
                    src="../../img//fillingHeartIcon.png"></img>
                  </div>
                </section>
              </li>

              <li class="li2">
                <h1 class="studyTitle"> 제목1 </h1>
                <span>
                  <img class="on_offline"
                  src="../../img//onlineIcon.png">
                  <p class="on_offline_status">온라인, 지명</p>
                </span>
              
                <div class="info_item">
                    <img class="icon"
                    src="../../img//fillingHeartIcon.png"></img>
                  </div>
                </section>
              </li>

  </div>







        </div>
      </div> <!--box1-->
          
        </div> <!--content-section-->
      </div> <!--both-->
    </div> <!-- col-lg-6 py-3 wow fadeInUp -->

  <jsp:include page="footer.jsp"></jsp:include>
  </div> <!-- container -->
 <!-- </div> <!-- .page-section -->
    
    
    


<script src="../assets/js/jquery-3.5.1.min.js"></script>

<script src="../assets/js/bootstrap.bundle.min.js"></script>

<script src="../assets/vendor/wow/wow.min.js"></script>

<script src="../assets/js/theme.js"></script>
  
</body>
</html>