<%-- 
    Document   : TakeQuiz
    Created on : Feb 11, 2021, 8:20:38 PM
    Author     : USER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/style.css">
        <title>Take Quiz</title>
        
    </head>
    <body class="bg-dark">
        <input type="hidden" value="${sessionScope.END_TIME}">
        <div class="container-lg pt-3 my-4 text-white rounded" style="background-color: grey">
            <div class="container justify-content-left">
                <h2>Take Quiz</h2>
                <hr class="style18">
                <h3 class="nav justify-content-end" >Hello ${sessionScope.USER.name}</h3>
                <hr class="style18">
                <div class="nav justify-content-end">
                    <h3 class="nav-item justify-content-end mx-3" id="timer" ></h3>
                    <button class="nav-item btn btn-dark mx-3 text-white" id="Submit" onclick="submit()">Submit</button>
                </div>

                <hr class="style18">
                <ul class="nav d-flex align-content-start flex-wrap">
                    <c:forEach var="question" items="${sessionScope.QUIZ}" varStatus="count">
                        <li class="nav-item mx-3 mb-4">
                            <c:url var="QuestionLink" value="Quiz">
                                <c:param name="index" value="${count.index}">${count.index}</c:param>
                            </c:url>
                            <a class="btn ${sessionScope.QUIZ_RESULT.get(question) != null? "btn-dark": "btn-warning"}  text-white" style="color: antiquewhite"  href="${QuestionLink}">${count.index +1}</a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>

        <div class="container justify-content-center p-5 " style="background-color: gray">
            <form action="Quiz" method="POST">
                <input type="hidden" name="index" value="${requestScope.index}"/>
                <input type="hidden" name="id" value="${requestScope.QUESTION.id}" />
                <div class="content">
                    <div class="container form-group p-3 d-flex flex-column bg-dark rounded text-white" >
                        ${requestScope.QUESTION.content} 
                    </div>
                    <hr class="style18">

                    <div class="container d-flex flex-column w-75 text-white p-3 bg-dark rounded" id="options">
                        <c:forEach var="answer" items="${requestScope.QUESTION.answers}" varStatus="loop">
                            <label class="options">${answer.content}
                                <input type="radio" name="ans" value=&#${loop.index + 97}" ${requestScope.USER_ANSWER == answer? 'checked="checked"' :""}>
                                <span class="checkmark"></span>
                            </label>
                        </c:forEach>
                    </div>
                </div>
                <div class="d-flex  pt-3 justify-content-between">
                    <input type="submit" class="btn btn-dark " id="prev" name="btnAction" value="Previous">
                    <input type="submit" class="btn btn-dark " id="next" name="btnAction" value="${requestScope.index + 1 != sessionScope.QUIZ.size()? 'Next' :'Submit'}">
                </div>
            </form>
        </div>
    </body>
<script>
// Set the date we're counting down to
let countDownDate = new Date(${sessionScope.END_TIME}).getTime();

// Update the count down every 1 second
let x = setInterval(function () {
    // Get today's date and time
    let now = new Date().getTime();

    // Find the distance between now and the count down date
    let distance = countDownDate - now;
    let hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
    let minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
    let seconds = Math.floor((distance % (1000 * 60)) / 1000);


    document.getElementById("timer").innerHTML = hours + "h " + minutes + "m " + seconds + "s ";

    // If the count down is finished, write some text
    if (distance < 0) {
        clearInterval(x);
        document.getElementById("Submit").click();
    }
}, 1000);

function submit() {
    document.getElementById("next").value = "Submit";
    document.getElementById("next").click();
}
</script>
</html>
