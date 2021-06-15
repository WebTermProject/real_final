<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
    * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
  }
  
   /*로고*/
#title {
	width: 90%;
}


#title_container{
    text-align: center;
}

  
  
  .loginForm, .signUpForm {
    display: flex;
    align-items: center;
    justify-content: center;
  }
  
  .signUpForm, .loginForm {
    margin-top: 5%;
  }
  .login, .signUp {
    width: 100%;
    text-align: center;
    background: white;
    border-radius: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    
  }
  
  h2 {
    color: rgb(120,163,255);
    font-size: 1.5em;
  }

  .login_id,  .login_pw{
    margin-top: 20px;
    width: 80%;
  }

  .box {/*input*/
  	background-color: white;
    width: 100%;
    height: 50px;
    border-radius: 0px;
    margin-top: 10px;
    padding: 0px 20px;
    border: 1px solid lightgray;
    outline: none;
  }
  
  .loginBtn, .joinBtn{
    margin-top: 20px;
    width: 80%;
    height: 50px;
    border: 0;
    outline: none;
    border-radius: 0px;
    background: rgb(62,118,240);
    color: white;
    font-size: 1.2em;
  }
  
.backBtn  {
    position: fixed;
    right: 10%;
    bottom: 15%;
    display: block; 
    font-size: 1.2em;
}
  .pbox{
    width: 30%;
    height: 50px;
    border: 1px solid lightgray;
    outline: none;
    padding: 10px;
  }
    
    /* 화면 사이즈에 따른 변형
@media screen and (min-width: 768px) and (min-height:1024px)   */
@media screen and (min-width: 768px)and (min-height: 1024px){
    .login, .signUp {width: 750px;}
 #title {
	width: 400px;
}
 
  }
  
    
    
    </style> 
    <title>login</title>
   
</head>
<body>
 	<!--로고-->
     <h1 id="title_container">
         <img id=title src="${pageContext.request.contextPath}/img/image.jpg" /></img>
    </h1>
     <button type="button" class = "backBtn"  onclick="location.href='/GYM/front/notice/noticeBoard'">돌아가기</button>   
	   
    <!--로그인-->
    <div class="loginForm">
    	<form method="post" action="login">
        	<div class="login">
            	<h2>로그인</h2>

            	<div class="login_id">
            	    <h4>아이디</h4>
               	 <input  onfocuse="onfocuseEvent();"  onblur="onblurEvent();"type="text" name="userID" required="required" placeholder="아이디" class = "box">
            	</div>
            	<div class="login_pw">
                	<h4>비밀번호</h4>
               	 	<input type="password" name="userPW" required="required" placeholder="비밀번호" class = "box">
            	</div>
            <!-- 	<button class="loginBtn">로그인</button> -->
          	 	 <input type="submit" value="로그인" class="loginBtn">
      			 <button type="submit" class = "joinBtn"  onclick="location.href='/GYM/front/member/join'">회원가입</button>
      			 
           </div>
        </form>   
	</div>
	
	

</body>
</html>