<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="service.FreeService"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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

.freeBoard {
	margin: 15px auto 0px auto;
	width: 100%;
}

.freeBoard, .freeBoard th, .freeBoard td {
	border: 1px solid gray;
	border-width: 1px 0;
	border-collapse: collapse;
	text-align: center;
	padding: 8px;
}

.freeBoard th {
	background-color: #999;
	font-size: 1.1em;
	color: #fff;
	border-width: 2px 0;
}

.freeBoard td {
	border-style: dotted;
}

.freeBoard tr:hover td {
	background-color: rgb(120, 163, 255);
	cursor: pointer;
}

/*로고*/
#title {
	width: 80%;
}

#title_container {
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


.login {
    position: absolute;
    right: 10%;
    bottom: 15%;
    display: block; 
}
.info{
	font-size: 1.4em;
}


.pgnt {
	width: 100%;
	margin-top: 20%;
	text-align: center;
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

.active, .free_menu:hover {
	background-color: rgb(120, 163, 255);
}

ul.topnav {
	display: none;
	list-style-type: none;
	padding: 0;
	overflow: hidden;
	background-color: rgb(120, 163, 255);
	margin-top: 20px;
	width: 100%;
	margin: 0 auto;
	text-align: center;
}

ul.topnav li {
	float: none;
}

ul.topnav li a {
	display: block;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}
ul.topnav li a:hover:not(.active) {background-color: rgb(62, 118, 240);}
ul.topnav li a.active {
	background-color: rgb(62, 118, 240);
}

ul.topnav li.right {
	float: none;
}

ul.info>li {
	list-style: none;
	float: left;
}

/* 글쓰기 버튼 */
.write {
	margin: 10px 0 0 85%;
}
.week-box
{
    position: absolute;
    box-sizing: border-box;
    margin-top: 10%;
    margin-left: 10%;
   
    display: inline-block;
}

.day-container
{
    position: relative;
    box-sizing: border-box;
    margin-top: 5%;
    margin-left: 20%;
    border-width: 3px;
    border-style: solid;
    width: 60%;
    height: 100px;
    display: inline-block;
}

#day-box
{
    position: absolute;
    padding: 10px;
    left:3%;
    top: 25%;
    width: 10%;
    display: inline-block;
}

#time-box
{
    position: absolute;
    top: 43%;
    left:25%;
display: inline-block;
}

#Btn
{
    position: absolute;
    top: 0px;
    left: 130%;
}



/* 화면 사이즈에 따른 변형 
코드 아래쪽에 두는 걸 추천드림다 */
@media screen and (min-width: 768px) and (min-height:1024px) {
	.current_menu {
		display: none;
	}
	ul.topnav {
		display: block;
	}
	ul.topnav {
		width: 80%;
	}
	ul.topnav li.right, ul.topnav li {
		float: left;
	}
	.freeBoard {
		width: 80%;
	}
	#title {
		width: 400px;
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
<title>freeBoard</title>
</head>
<body>

	<!-- 공통사항 -->
	<header class="header">

		<!-- 로고 -->
		<h1 id="title_container">
			<img src="${pageContext.request.contextPath}/img/image.jpg" id="title"/>
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
	<button class="current_menu">예약</button>
	<!--각자 페이지에 맞게 수정하기-->
	<ul class="topnav">
		<li><a href="/GYM/front/notice/noticeBoard" title="공지사항 바로가기">공지사항</a></li>
		<li><a href="/GYM/front/free/freeBoard" title="자유게시판 바로가기">자유게시판</a></li>
		<li><a class="active"  title="예약 바로가기">예약</a></li>
		<li><a href="/GYM/front/purchase/purchaseBoard" title="주문 바로가기">주문</a></li>
	</ul>
 <!--예약-->
    
        <div class="week-box">
            <h2 id="week">6월</h2>
        </div>

		<c:forEach var="timetable" items="${timetable}">
    	    <div class="day-container">
		
        	    <div id="day-box">
 					<td>${timetable.day}</td>
            	</div>

                <div id="time-box">
 					<td>${timetable.time}</td><br /><br />
 					<c:if test="${sessionID != null }"> 
 						<form method="post">
							<input type="hidden" name="user" value="${sessionID}" ><br /><br />
							<input type="hidden" name="ttId" value="${timetable.timetable_id}"><br /><br />
							<button id="Btn" type="submit">예약</button>
						</form>
					</c:if>
                </div>
      		</div>
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
		function PageMove(page){
		        location.href = "freeBoard?page="+page;
		      }
	</script>
</body>

</html>