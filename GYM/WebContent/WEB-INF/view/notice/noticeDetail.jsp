<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>freeBoard_View</title>
<style type="text/css">
* {
   margin: 0;
   padding: 0
}

body {
   font-family: '맑은 고딕' 돋움;
   font-size: 0.75em;
   color: #333
}
/*로고*/
#title {
   width: 80%;
}

#title_container{
    text-align: center;
}


.header{
    position: relative;
    margin: 0 auto;
}

/*로그인 했다고 가정
사용자 정보, 로그아웃*/
.header >.account{
    position: absolute;
    right: 10%;
    bottom: 15%;
}

.account{
    display: inline-block; 
  
}

.info{
   font-size: 1.4em;
}

.login {
    position: absolute;
    right: 10%;
    bottom: 15%;
    display: block; 
}

/*메뉴바*/
.current_menu {
    display: block;
    background-color: rgb(62, 118, 240);
    color: rgb(255, 255, 255);
    cursor: pointer;
    padding: 18px;
    width: 100%;
    border: none;
    text-align: center;
    outline: none;
    transition: 0.4s;
  }

.active, .current_menu:hover {
    background-color: rgb(120,163,255);
}

ul.topnav {
    display: none;
    list-style-type: none;
    padding: 0;
    overflow: hidden;
    background-color: rgb(120,163,255);
    margin-top: 20px;
    width: 100%;
    margin: 0 auto;
    text-align: center;
}
  
ul.topnav li {float: none;}
  
ul.topnav li a {
    display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}
  
ul.topnav li a:hover:not(.active) {background-color: rgb(62, 118, 240);}
  
ul.topnav li a.active {background-color: rgb(62, 118, 240);}
  
ul.topnav li.right {float: none;}

ul.info > li{
    list-style:none;
    float: left;
}

/* 글내용 자세히 보기 */
.viewDetail {
   margin: 2% auto 0px auto;
   width: 100%;
}

.viewDetail, .viewDetail th, .viewDetail td {
   border: 1px solid gray;
   border-collapse: collapse;
   text-align: center;
   padding: 8px;
}

.viewDetail th {
   background-color: #999;
   font-size: 1.1em;
   color: #fff;
   border-width: 2px 0;
}

/* 화면 사이즈에 따른 변형 */
@media screen and (min-width: 768px)and (min-height: 1024px){
    .current_menu {display: none;}
    ul.topnav { display: block;}
    ul.topnav {width: 80%;}
    ul.topnav li.right, 
    ul.topnav li {float: left;}
    .viewDetail{ width: 80%;}
    #title {
      width: 400PX;
   }  
  }
</style>
</head>
<body>
   
    <!--공통사항-->
    <header class="header">

         <!--로고-->
        <h1 id="title_container">
            <img id=title src="${pageContext.request.contextPath}/img/image.jpg" /></img>
        </h1>
        
        <!--로그인 버튼-->
        <c:if test="${sessionID == null }">      
           <button type="submit" class = "login"  onclick="location.href='/GYM/front/member/login'">로그인</button>   
      </c:if>
   
         <!--로그아웃 버튼 / 사용자 아이디-->
         <!--  로그인되어 있다면 사용자 아이디 보여주고 로그아웃 버튼 -->
         <c:if test="${sessionID != null }">      
           <div class="account">
               <ul class="info">
                   <li>         
                  ${sessionID} 님 
                   </li>
                     <li>
                       <a href="/GYM/front/member/login"><button class = "logout"  >로그아웃</button></a>
                   </li>
               </ul>
           </div>
      </c:if>
      
    </header>

    <!--메뉴-->
   <button class="current_menu">공지사항</button>   <!--각자 페이지에 맞게 수정하기-->
    <ul class="topnav">
        <li><a class="active" href="/GYM/front/notice/noticeBoard" title="공지사항 바로가기">공지사항</a></li>
        <li><a href="/GYM/front/free/freeBoard" title="자유게시판 바로가기">자유게시판</a></li>
        <li><a href="/GYM/front/appointment/appointment" title="예약 바로가기">예약</a></li>
        <li><a href="/GYM/front/purchase/purchaseBoard" title="주문 바로가기">주문</a></li>
    </ul>
    
   <!-- 게시판 -->
   <c:forEach var="detail" items="${detail}">
      <form action="list" method="get" align="center">
         <table class="viewDetail">
            <tr class="sectr">
               <td class="no">번호</td>
               <td>${detail.id}</td>
               <td class="author">글쓴이</td>
               <td>${detail.writer}</td>
               <td class="date">날짜</td>
               <td>${detail.regdate}</td>
            </tr>
            <tr class="firtr">
               <td class="title">제목</td>
               <td colspan="5">${detail.title}</td>
            </tr>
            <tr class="lastr">
               <td class="contents">내용</td>
               <td colspan="5">${detail.contents}</td>
            </tr>
         </table>
      </form>
   </c:forEach>
   <script>
      // ----------------------- 자바스크립트 ----------------------- 
      // ----------------------- 아코디언 ----------------------- 
      var acc = document.getElementsByClassName("current_menu");
      var i;

      for (i = 0; i < acc.length; i++) {
         acc[i].addEventListener("click", function() {

            // 클릭한 아코디언은 투글한다
            this.classList.toggle("active");
            var topnav = this.nextElementSibling
            if (topnav.style.display === "block") {
               topnav.style.display = "none";
            } else {
               topnav.style.display = "block";
            }
         });
      }
   </script>
</body>
</html>