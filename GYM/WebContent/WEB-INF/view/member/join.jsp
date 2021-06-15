<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="../CSS/loginStyle.css?after" rel="stylesheet" type="text/css">   
    <title>join</title>
	
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
  
  
  .wrap, .signUpForm {
    display: flex;
    align-items: center;
    justify-content: center;
  }
  
  .signUpForm {
    margin-top: 5%;
  }
  .login, .signUp {
    width: 80%;
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

  .login_id,  .login_pw, .signUp_name,.signUp_id, .signUp_pw, .signUp_phone {
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
  
  .loginBtn, .signUpBtn {
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
    @media screen and (min-width: 768px)and (min-height: 1024px){
    .login, .signUp {width: 750px;}
 #title {
	width: 400px;
}
 
  }
  
    
    </style> 
</head>
<body>
 	<!--로고-->
     <h1 id="title_container">
         <img id=title src="${pageContext.request.contextPath}/img/image.jpg" /></img>
    </h1>
<button type="button" class = "backBtn"  onclick="location.href='/GYM/front/notice/noticeBoard'" >돌아가기</button>   

    <!--회원가입-->
    <div class = "signUpForm">
   		<form name = "joinForm" method="post" action="join">
        	<div class="signUp">
            	<h2>회원가입</h2>
            	<div class="signUp_name">
                	<h4>이름</h4>
                	<input type="text" name="userName" id="" required="required" placeholder="이름를 입력하세요" class = "box">
            	</div>
            	<div class="signUp_id">
                	<h4>아이디</h4>
                	<input type="text" name="userID" id="" required="required" placeholder="아이디를 입력하세요" class = "box">
           	 		
           	 	</div>
            	<div class="signUp_pw">
                	<h4>비밀번호</h4>
                	<input id="msg" type="password" name="userPW" required="required" placeholder="비밀번호를 입력하세요" class = "box">
   					<div id = "result"></div>
            	</div>
            	<div class="signUp_phone">
                	<h4>전화번호</h4>
                	<input type="text" name="userPhone1" required="required" class = 'pbox' maxlength="3">- <input type = "text" name="userPhone2"  required="required" class = 'pbox' maxlength="4" /> - <input type = "text" name="userPhone3" required="required" class = 'pbox' maxlength="4" />
            	</div>
          
            	 <input type="submit" value="회원가입" class="signUpBtn" onClick="">
            </div>
        </form>
     </div>
  <script >
 var msg = document.querySelector("#msg");
 var result = document.querySelector("#result");
 
 msg.onfocus = function(e){
	 result.innerHTML = "보안을 위해 4자리 이상 입력하세요";
 }
 msg.onblur = function(e){
	 result.innerHTML = "";
 }
 
 </script>
</body>
</html>