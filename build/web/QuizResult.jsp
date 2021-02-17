<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2/15/2021
  Time: 5:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <title>Quiz Result</title>
</head>
<body class="bg-dark container ">
    <div class="container-lg pt-3 my-4 text-white rounded" style="background-color: grey">
        <div class="container justify-content-left">
            <h2>Quiz Result</h2>
            <hr class="style18">
            <h3 class="nav justify-content-end" >Hello ${sessionScope.USER.name}</h3>
            <hr class="style18">
            <ul class="nav d-flex flex-row-reverse mb-4">
                <li class="nav-item mx-3 mb-4">
                    <a class="btn bg-dark  text-white" style="color: antiquewhite" onClick="return confirm('Do you want to logout?')" href="Logout">Logout</a>
                </li>
                <li class="nav-item mx-3 mb-4">
                    <p class="btn bg-dark  text-white">Point: ${requestScope.QUIZ_RESULT.getPoint()}</p>
                </li>
            </ul>
        </div>
    </div>
    <div class="container-lg  p-3  text-white rounded" style="background-color: grey">
        <c:forEach var="question" items="${requestScope.QUIZ_RESULT}">
            <div class="m-5 p-3 bg-dark rounded">
                <h4>${question.key.content}</h4>
                <hr class="style18">
                <p>
                    a) ${question.key.ansA} <br/>
                    b) ${question.key.ansB} <br/>
                    c) ${question.key.ansC} <br/>
                    d) ${question.key.ansD} <br/>
                </p>
                <hr class="style18">
                <p>Your choose: ${question.value != 'z'.charAt(0)? question.value : 'none'}</p>
            </div>
        </c:forEach>
    </div>
</body>
</html>
