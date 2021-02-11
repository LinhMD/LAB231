<%-- 
    Document   : TakeQuiz
    Created on : Feb 11, 2021, 8:20:38 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body class="bg-dark">
        <div class="container-lg pt-3 my-4 text-white rounded" style="background-color: grey">
            <div class="container justify-content-left">
                <h2>Select Subject</h2>
                <hr class="style18">
                <h3 class="nav justify-content-end" >Hello ${sessionScope.USER.name}</h3>
                <hr class="style18">
                <ul class="nav d-flex flex-row-reverse mb-4">
                    <li class="nav-item mx-3 mb-4">
                        <a class="btn bg-dark  text-white" style="color: antiquewhite" onClick="return confirm('Do you want to logout?')" href="Logout">Logout</a>
                    </li>
                </ul>
            </div>
        </div>
        ${sessionScope.QUIZ}
    </body>
</html>
