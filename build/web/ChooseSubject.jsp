<%-- 
    Document   : ChooseSubject
    Created on : Feb 11, 2021, 1:13:56 PM
    Author     : USER
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Choose Subject</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
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
                
        <div class="container-lg p-5 my-3 text-white rounded flex-column " style="background-color: grey">
            <c:forEach var="subject" items="${sessionScope.SUBJECTS}">
                <c:url var="TakeQuiz" value="Prepare">
                    <c:param name="id" >${subject.code}</c:param>
                </c:url>
                <a href="${TakeQuiz}" class="btn btn-dark container p-3 m-3 flex-colum">
                    <div class="container d-flex justify-content-between">
                        <b>${subject.code}</b>
                        <p>
                            ${subject.limit} questions <br/>
                            time: ${subject.time}
                        </p>
                    </div>
                    <hr class="style18">
                    <div class="container-lg d-flex justify-content-left text-white">
                        ${subject.name}
                    </div>
                </a>
            </c:forEach>
        </div>   
    </body>
</html>
