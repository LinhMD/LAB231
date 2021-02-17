<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2/15/2021
  Time: 8:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <title>Quiz History</title>
</head>
<body class="bg-dark">
    <div class="container-lg pt-3 my-4 text-white rounded" style="background-color: grey">
        <div class="container justify-content-left">
            <h2>Quiz History</h2>
            <hr class="style18">
            <h3 class="nav justify-content-end" >Hello ${sessionScope.USER.name}</h3>
            <hr class="style18">
            <ul class="nav d-flex flex-row-reverse mb-4">
                <li class="nav-item mx-3 mb-4">
                    <a class="btn bg-dark  text-white" style="color: antiquewhite" onClick="return confirm('Do you want to logout?')" href="Logout">Logout</a>
                </li>
                <li class="nav-item mx-3 mb-4">
                    <a class="btn bg-dark  text-white" style="color: antiquewhite" href="ChooseSubject">Choose Subject</a>
                </li>
            </ul>
        </div>
    </div>
    <div class="container-lg p-5 my-3 text-white rounded flex-column " style="background-color: grey">
        <form action="B4QuizHistory" class=" my-3 py-3 rounded" style="background-color: #222831" method="post">
            <input type="hidden" name="pageNum" value="${requestScope.page}">
            <div class="container mb-3 p-1 d-flex justify-content-center">
                <input type="submit" class="btn mx-3 btn-dark text-white " name="btnAction" value="Previous" ${requestScope.page == 0? 'disabled="disabled"':''}/>
                <input type="submit" class="btn mx-3 btn-dark text-white " name="btnAction" value="Next"  ${!requestScope.hasNext? 'disabled="disabled"' : ''}/>
            </div>
        </form>
        <c:forEach var="quiz" items="${sessionScope.QUIZ_RESULTS}" varStatus="counter">
            <c:url var="QuizResultLink" value="QuizResult">
                <c:param name="index">${counter.index}</c:param>
            </c:url>
            <a href="${QuizResultLink}"  class="btn btn-dark container p-3 m-3 flex-colum">
                <div class="container d-flex justify-content-between">
                    <b>
                        Subject: ${quiz.getSubject()}<br/>
                        Date: ${quiz.getDate()}
                    </b>
                    <b>
                        ${quiz.getPoint()} point <br>
                        ${quiz.getNumCorrectAns()} Correct Answer / ${quiz.size()}
                    </b>
                </div>
            </a>
        </c:forEach>
    </div>

</body>
</html>
