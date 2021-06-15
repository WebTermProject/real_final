<%@ page language="java" contentType="text/html; charset=utf-8"
   pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

/*로고*/
#title {
   width: 80%;
}

#title_container {
   text-align: center;
}

.header {
   position: relative;
   margin: 0 auto;
}

/*로그인 했다고 가정
사용자 정보, 로그아웃*/
.header>.account {
   position: absolute;
   right: 10%;
   bottom: 15%;
}

.account {
   display: inline-block;
}

.info {
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

/* -------------------------------------컨텐츠 컨테이너------------------------------------- */
.goodNames-container {
   padding-left: 150px;
   /* margin-top: 30px; */
   padding-top: 30px;
}

.detail-form {
   background-color: gainsboro;
}

.detail-totalPrice {
   float: right;
   margin-top: 10px;
   font-weight: bold;
}

.detail-purchaseBtn {
   float: left;
   margin-top: 10px;
}

/* -------------------------------------슬라이드 쇼------------------------------------- */
.mySlides {
   display: none
}

img {
   vertical-align: middle;
}

/* Slideshow container */
.slideshow-container {
   max-width: 500px;
   position: relative;
   float: left;
   margin-left: -50px;
}

/* Next & previous buttons */
.prev, .next {
   cursor: pointer;
   position: absolute;
   top: 50%;
   width: auto;
   padding: 16px;
   margin-top: -22px;
   color: aqua;
   font-weight: bold;
   font-size: 18px;
   transition: 0.6s ease;
   border-radius: 0 3px 3px 0;
   user-select: none;
}

/* Position the "next button" to the right */
.next {
   right: 0;
   border-radius: 3px 0 0 3px;
}

/* On hover, add a black background color with a little bit see-through */
.prev:hover, .next:hover {
   background-color: rgba(0, 0, 0, 0.8);
}

/* Caption text */
.text {
   color: #f2f2f2;
   font-size: 15px;
   padding: 8px 12px;
   position: absolute;
   bottom: 8px;
   width: 100%;
   text-align: center;
}

/* Number text (1/3 etc) */
.numbertext {
   color: brown;
   font-size: 12px;
   padding: 8px 12px;
   position: absolute;
   top: 0;
}

/* The dots/bullets/indicators */
.dot {
   cursor: pointer;
   height: 15px;
   width: 15px;
   margin: 0 2px;
   background-color: #bbb;
   border-radius: 50%;
   display: inline-block;
   transition: background-color 0.6s ease;
}

.active, .dot:hover {
   background-color: rgb(120, 163, 255);
}

/* Fading animation */
.fade {
   -webkit-animation-name: fade;
   -webkit-animation-duration: 1.5s;
   animation-name: fade;
   animation-duration: 1.5s;
}

@
-webkit-keyframes fade {
   from {opacity: .4
}

to {
   opacity: 1
}

}
@
keyframes fade {
   from {opacity: .4
}

to {
   opacity: 1
}

}

/* ------------------------------------- 디테일 --------------------------------------- */
.detail-container {
   width: 80%;
   max-width: 1000px;
   position: relative;
   float: left;
   margin-left: -50px;
   background-color:pink;
}

.orderList {
   list-style: none;
   padding-left: 0px;
}

.price {
   text-align: right;
}

/* ------------------------------------- 아코디언 ------------------------------------- */
.accordion {
   background-color: #eee;
   color: #444;
   cursor: pointer;
   padding: 18px;
   width: 100%;
   border: none;
   text-align: left;
   outline: none;
   font-size: 15px;
}

//주문정보
.arc_active, .accordion:hover {
   background-color: #CCC;
}

.panel {
   padding: 0 18px;
   display: none;
   background-color: white;
   overflow: hidden;
}

/*--------------------------------------상세정보--------------------------------*/
.detail-container {
   width: 100%;
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
   .detail-container {
      width: 80%;
      background-color:pink;
   }
   #title {
      width: 400px;
   }
}
</style>
<title>freeBoard</title>
</head>
<body>

   <!-- 공통사항 -->
   <header class="header">

      <!-- 로고 -->
      <h1 id="title_container">
         <img src="${pageContext.request.contextPath}/img/image.jpg"
            id="title" />
      </h1>

      <!--로그인 버튼-->
      <c:if test="${sessionID == null }">
         <button type="submit" class="login"
            onclick="location.href='/GYM/front/member/login'">로그인</button>
      </c:if>

      <!--로그아웃 버튼 / 사용자 아이디-->
      <!--  로그인되어 있다면 사용자 아이디 보여주고 로그아웃 버튼 -->
      <c:if test="${sessionID != null }">
         <div class="account">
            <ul class="info">
               <li>${sessionID}님</li>
               <li><a href="/GYM/front/member/login"><button
                        class="logout">로그아웃</button></a></li>
            </ul>
         </div>
      </c:if>
   </header>

   <!--메뉴-->
   <button class="current_menu">주문</button>
   <!--각자 페이지에 맞게 수정하기-->
   <ul class="topnav">
      <li><a href="/GYM/front/notice/noticeBoard" title="공지사항 바로가기">공지사항</a></li>
      <li><a class="/GYM/front/free/freeBoard" title="자유게시판 바로가기">자유게시판</a></li>
      <li><a href="/GYM/front/appointment/appointment" title="예약 바로가기">예약</a></li>
      <li><a class="active" href="" title="주문 바로가기">주문</a></li>
   </ul>

   <!-- 컨텐츠 -->
   <div class="goodNames-container">
      <!-- 슬라이드쇼 -->
      <div class="slideshow-container">

         <div class="mySlides fade">
            <div class="numbertext">1 / 2</div>
            <img src="${pageContext.request.contextPath}/img/item1.jpg"
               style="width: 100%" />
         </div>

         <div class="mySlides fade">
            <div class="numbertext">2 / 2</div>
            <img src="${pageContext.request.contextPath}/img/item2.jpg"
               style="width: 100%" />
         </div>

         <a class="prev" onclick="plusSlides(-1)">&#10094;</a> <a class="next"
            onclick="plusSlides(1)">&#10095;</a>

      </div>
      <br>

      <!-- 상세정보 -->
      <div class="detail-container">
         <form action="/GYM/front/purchase/purchaseBoard" method="post">
            <!-- value를 sessionId로 변경해주세요 -->
            <input type="hidden" name="MemberID" value="${sessionID}"></input>
            <fieldset>
               <legend>주문정보</legend>

               <!-- 주문정보 -->
               <button class="accordion" type="button">주문 정보</button>
               <div class="panel">
                  <select id="order" onchange="changeOrderSelect()">
                     <option value="">선택</option>
                     <option value="A">닭가슴살</option>
                     <option value="B">물</option>
                  </select>
                  <ul id="orderList" class="orderList">

                  </ul>
               </div>

               <div class="detail-totalPrice">
                  <span id="totalPrice">총 상품금액 0원</span>
               </div>
               <c:if test="${sessionID != null }">
                  <button class="detail-purchaseBtn">구매하기</button>
               </c:if>
            </fieldset>
         </form>
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

         function onLoadFunctions() {
            resetSelected();
         }
         window.onload = onLoadFunctions;

         // -----------------------  사이드바 ----------------------- 
         function openNav() {
            document.getElementById("mySidenav").style.width = "250px";
         }

         function closeNav() {
            document.getElementById("mySidenav").style.width = "0";
         }

         // ----------------------- 슬라이드쇼 ----------------------- 
         var slideIndex = 1;
         showSlides(slideIndex);

         function plusSlides(n) {
            showSlides(slideIndex += n);
         }

         function currentSlide(n) {
            showSlides(slideIndex = n);
         }

         function showSlides(n) {
            var i;
            var slides = document.getElementsByClassName("mySlides");
            // var dots = document.getElementsByClassName("dot");
            if (n > slides.length) {
               slideIndex = 1
            }
            if (n < 1) {
               slideIndex = slides.length
            }
            for (i = 0; i < slides.length; i++) {
               slides[i].style.display = "none";
            }
            // for (i = 0; i < dots.length; i++) {
            //        dots[i].className = dots[i].className.replace(" active", "");
            // }
            slides[slideIndex - 1].style.display = "block";
            // dots[slideIndex-1].className += " active";
         }

         // ----------------------- 아코디언 ----------------------- 
         var acc = document.getElementsByClassName("accordion");
         var i;

         for (i = 0; i < acc.length; i++) {
            acc[i].addEventListener("click", function() {
               // 다른 모든 아코디언 비활성화
               for (j = 0; j < acc.length; j++) {
                  if (acc[j] != this) {
                     acc[j].classList.remove("arc_active")
                     acc[j].nextElementSibling.style.display = "none";
                  }
               }

               // 클릭한 아코디언은 투글한다
               this.classList.toggle("arc_active");
               var panel = this.nextElementSibling
               if (panel.style.display === "block") {
                  panel.style.display = "none";
               } else {
                  panel.style.display = "block";
               }
            });
         }

         // ----------------------- 주문정보 ----------------------- 

         var newElemHeader = "newElem-";
         function changeOrderSelect() {
            var orderSelect = document.getElementById("order");
            var selectedValue = orderSelect.options[orderSelect.selectedIndex].value;
            var selectedText = orderSelect.options[orderSelect.selectedIndex].text;

            // 선택 클릭했으면 아무것도 하지 않는다.
            if (orderSelect.selectedIndex == 0) {
               return;
            }

            // 이미 추가되있으면 아무것도 하지 않는다.
            var find = document.getElementById(newElemHeader
                  + selectedValue);
            if (find != null || find != undefined) {
               alert("이미 해당 항목이 존재합니다.")
               return;
            }

            // 항목 추가
            addElem(selectedValue, selectedText);
         }

         function addElem(selectedValue, selectedText) {
            var orderList = document.getElementById("orderList");
            var newElem = document.createElement("li");
            newElem.id = newElemHeader + selectedValue;

            // 상품명 텍스트 추가
            var goodName = document.createTextNode(selectedText);
            newElem.appendChild(goodName);

            // 텍스트
            var goodNamehidden = document.createElement("input");
            goodNamehidden.type = "hidden";
            goodNamehidden.value = selectedText;
            goodNamehidden.readOnly = true;
            goodNamehidden.setAttribute("name", "item");
            newElem.appendChild(goodNamehidden);

            // 수량 input readonly로 추가
            var quantityText = document.createElement("input");
            quantityText.value = 1;
            quantityText.readOnly = true;
            quantityText.setAttribute("name", "count");
            newElem.appendChild(quantityText);

            // 가격 input readonly로 생성
            var price = document.createElement("input");
            price.readOnly = true;
            price.classList.add("price");
            price.value = getPrice(selectedValue) + "원";

            // + 버튼 추가
            var plusBtn = document.createElement("button");
            plusBtn.textContent = "+";
            plusBtn.addEventListener("click", function(event) {
               var newQuantity = Number(quantityText.value) + 1;
               quantityText.value = newQuantity;
               price.value = getPrice(selectedValue) * newQuantity + "원";
               event.preventDefault(); // form 태그 안에 버튼이 있으면 버튼 기본 타입이 submit이기 때문에 페이지 자꾸 초기화됨
               updateTotalPrice();
            });
            newElem.appendChild(plusBtn);

            // - 버튼 추가
            var minusBtn = document.createElement("button");
            minusBtn.textContent = "-";
            minusBtn.addEventListener("click", function(event) {
               var newQuantity = Number(quantityText.value) - 1;
               newQuantity = newQuantity > 1 ? newQuantity : 1;
               quantityText.value = newQuantity;
               price.value = getPrice(selectedValue) * newQuantity + "원";
               event.preventDefault(); // form 태그 안에 버튼이 있으면 버튼 기본 타입이 submit이기 때문에 페이지 자꾸 초기화됨
               updateTotalPrice();
            });
            newElem.appendChild(minusBtn);

            // x 버튼 추가
            var deleteBtn = document.createElement("button");
            deleteBtn.textContent = "x";
            deleteBtn.stopP
            deleteBtn.addEventListener("click", function(event) {
               newElem.remove();
               resetSelected();
               event.preventDefault(); // form 태그 안에 버튼이 있으면 버튼 기본 타입이 submit이기 때문에 페이지 자꾸 초기화됨
               updateTotalPrice();
            });
            newElem.appendChild(deleteBtn);

            // 가격  추가
            newElem.appendChild(price);

            // ol에 추가
            orderList.appendChild(newElem);

            updateTotalPrice();
         }

         function getPrice(selectedValue) {
            switch (selectedValue) {
            case "A":
               return 3000;
            case "B":
               return 1000;

            default:
               return 0;
            }
         }

         function getTotalPrice() {
            var prices = document.getElementsByClassName("price");
            var total = 0;
            for (var i = 0; i < prices.length; i++) {
               var StrPrice = String(prices[i].value).replace("원", "");
               total += Number(StrPrice);
            }
            return total;
         }

         // 총 상품금액 업데이트
         function updateTotalPrice() {
            var totalPriceText = document.getElementById("totalPrice");
            var a = getTotalPrice();
            console.log(a);
            totalPriceText.innerHTML = "총 상품금액 " + a + "원";
         }

         function resetSelected() {
            var orderSelect = document.getElementById("order");
            orderSelect.selectedIndex = 0; // 선택으로 초기화
         }
      </script>
</body>
</html>