<%-- 
    Document   : Login
    Created on : Feb 3, 2021, 6:53:29 PM
    Author     : USER
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <title>Login</title>
    </head>
    <body>
        <input type="hidden" id="is-register" value="${requestScope.isSignUp == null? "false": requestScope.isSignUp}"/>
        <div class="login-reg-panel">
            <div class="login-info-box">
                <h2>Have an account?</h2>
                <label id="label-register" for="log-reg-show">Login</label>
                
                <c:if test="${!requestScope.isSignUp}">
                    <input type="radio" name="active-log-panel" id="log-reg-show"  checked="checked">
                </c:if>
                <c:if test="${requestScope.isSignUp}">
                    <input type="radio" name="active-log-panel" id="log-reg-show" >
                </c:if>
            </div>

            <div class="register-info-box">
                <h2>Don't have an account?</h2>
                <label id="label-login" for="log-login-show">Register</label>
                
                <c:if test="${requestScope.isSignUp}">
                    <input type="radio" name="active-log-panel" id="log-login-show" checked="checked">
                </c:if>
                <c:if test="${!requestScope.isSignUp}">
                    <input type="radio" name="active-log-panel" id="log-login-show" >
                </c:if> 
            </div>

            <div class="white-panel">
                <div class="login-show">
                    <h2>LOGIN</h2>
                    <p style="color: orangered">${requestScope.message}</p>
                    <form action="Login" method="POST">
                        <input type="text" placeholder="Email" name="email" value="${param.email}">
                        <input type="password" placeholder="Password" name="password">
                        <input type="submit" value="Login">
                        
                    </form>
                </div>
                <div class="register-show">
                    <h2>REGISTER</h2>
                    <p style="color: orangered">${requestScope.regisMessage}</p>
                    <form action="Register" method="POST">
                        <input type="text" placeholder="${registerError.email == null? "Email" : registerError.email}" name="email" value="${requestScope.email}">
                        <input type="password" placeholder="${registerError.password == null? "password" : registerError.password}" name="password" >                        
                        <input type="password" placeholder="${registerError.comfirm == null? "Comfirm password" : registerError.comfirm}" name="comfirm">                      
                        <input type="text" placeholder="${registerError.name == null? "Name" : registerError.name}" name="username" value="${requestScope.username}">
                        <input type="submit" name="btnAction"value="Register">
                    </form>
                </div>
            </div>
        </div>
    </body>
    
    <script>
let fun = function(){
    if($('#log-login-show').is(':checked')) {
        $('.register-info-box').fadeOut(); 
        $('.login-info-box').fadeIn();
        
        $('.white-panel').addClass('right-log');
        $('.register-show').addClass('show-log-panel');
        $('.login-show').removeClass('show-log-panel');
        
    }
    else if($('#log-reg-show').is(':checked')) {
        $('.register-info-box').fadeIn();
        $('.login-info-box').fadeOut();
        
        $('.white-panel').removeClass('right-log');
        
        $('.login-show').addClass('show-log-panel');
        $('.register-show').removeClass('show-log-panel');
    }
}        
$(document).ready(fun);

$('.login-reg-panel input[type="radio"]').on('change', fun);
    </script>
</html>
