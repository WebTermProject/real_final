<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <style>
   @charset "utf-8";

* {
    margin:0; padding:0
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

/*공지사항 테이블*/
.notice{

    margin-top: 20px;
    width: 100%;
    margin: 30px auto 0px auto;
    align-items: center;
    justify-content: center;
}

.notice, .notice th , .notice td{
    border:1px solid rgb(182, 182, 182);
    border-width: 1px 0;
    border-collapse: collapse;
    text-align: center;
    padding:8px;
}

.notice th{
    background-color: rgb(182, 182, 182);
    font-size: 0.9em;
    color: #fff;
}

.notice td{
    border-style:dotted;
}

.notice tr:hover td{
    background-color: rgb(120,163,255);
    cursor : pointer;
}

/* 글 목록 번호 */
.paging .hide {
    display:block;
    height:0;
    width:0;
    font-size:0;
    line-height:0;
    margin:0;
    padding:0;
    overflow:hidden;
}

.paging{
    padding:19px;
    text-align:center;
}

.paging a{
    display:inline-block;
    width:23px;
    height:23px;
    padding-top:2px;
    vertical-align:middle;
    text-decoration: none;
}

.paging a:hover{
    text-decoration:underline;
}

.paging .on{
    padding-top:1px;
    height:22px;
    font-weight:bold;
}

.paging .on:hover{text-decoration:none;}

.paging .first{
    background: url('image/page_pprev.png') no-repeat center center;
   margin-left:0;
}

.paging .prev{
    background: url('image/page_prev.png') no-repeat center center;
   margin-right:7px;
}

.paging .next{
    background: url('image/page_next.png') no-repeat center center;
   margin-left:7px;
}

.paging .last{
    background: url('image/page_nnext.png') no-repeat center center;
    margin-right:0;
}
 
/* 화면 사이즈에 따른 변형*/
@media screen and (min-width: 768px)and (min-height: 1024px){
    .current_menu {display: none;}
    ul.topnav { display: block;}
    ul.topnav {width: 80%;}
    ul.topnav li.right, 
    ul.topnav li {float: left;}
    .notice{ width: 80%;}
    #title {
      width: 400PX;
   }  
  }
  /*페이징*/
.toolbar-bottom{
  margin: 0 0 0 44%;
}
.toolbar-bottom ul{
    list-style:none;
    margin:0;
    padding:0;
}
.toolbar-bottom li{
    margin: 30px 20px 0 0;
    padding: 0 0 0 0;
    border : 0;
    float: left;
}
   
   </style>
    <title>GYM Notice</title>
</head>
<body>

    <!--공통사항-->
    <header class="header">

         <!--로고-->
        <h1 id="title_container">
            <img id=title src="${pageContext.request.contextPath}/img/image.jpg" /></img>
        </h1>
        
        <!--로그인 버튼-->
        <c:if test="${sessionID == null}">      
           <button type="submit" class = "login"  onclick="location.href='/GYM/front/member/login'">로그인</button>   
      </c:if>
   
         <!--로그아웃 버튼 / 사용자 아이디-->
         <!--  로그인되어 있다면 사용자 아이디 보여주고 로그아웃 버튼 -->
         <c:if test="${sessionID != null}">      
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
        <li><a class="active" href="" title="공지사항 바로가기">공지사항</a></li>
        <li><a href="/GYM/front/free/freeBoard" title="자유게시판 바로가기">자유게시판</a></li>
        <li><a href="/GYM/front/appointment/appointment" title="예약 바로가기">예약</a></li>
        <li><a href="/GYM/front/purchase/purchaseBoard" title="주문 바로가기">주문</a></li>
    </ul>
      
   <form action="list" method="get" align="center"></form>
    <!--공지글 목록-->
    <table class="notice">
         <tr>
            <th><span>번호</span></th>
            <th><span>제목</span></th>
            <th><span>글쓴이</span></th>
            <th><span>날짜</span></th>
         </tr>
         <tbody>
            <c:forEach var="notice" items="${notice}">
               <tr>
                  <td>${notice.id}</td>
                  <td><a onclick="location.href='/GYM/front/notice/noticeDetail?id=${notice.id}'">${notice.title}</a></td>
                  <td>${notice.writer}</td>
                  <td>${notice.regdate}</td>
               </tr>
            </c:forEach>
         </tbody>
      </table>
   </div>
   
   <!-- 페이징처리 -->
   <div class="toolbar-bottom">
      <ul class="pagination">
        <li><a onclick="location.href='javascript:PageMove(${paging.firstPageNo})'">맨앞으로</a></li>
        <li><a onclick="location.href='javascript:PageMove(${paging.prevPageNo})'">이전</a></li>
           <c:forEach var="i" begin="${paging.startPageNo}" end="${paging.endPageNo}" step="1">
                  <c:choose>
                      <c:when test="${i eq paging.pageNo}">
                <li><a onclick='location.href="javascript:PageMove(${i})'">${i}</a></li>
                      </c:when>
                      <c:otherwise>
                        <li><a onclick="location.href='javascript:PageMove(${i})'">${i}</a></li>
                      </c:otherwise>
                  </c:choose>
             </c:forEach>
        <li><a onclick="location.href='javascript:PageMove(${paging.nextPageNo})'">다음</a></li>
        <li><a onclick="location.href='javascript:PageMove(${paging.finalPageNo})'">맨뒤로</a></li>
      </ul>
   </div>

      <script>
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
   function PageMove(page){
        location.href = "/GYM/front/notice/noticeBoard?page="+page;
      }
        </script>
</body>
</html>