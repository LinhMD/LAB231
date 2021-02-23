<%-- 
    Document   : UpdateQuestion
    Created on : Feb 7, 2021, 2:48:44 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Question</title>
         <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body class="bg-dark">
        <div class="container-lg pt-3 my-4 text-white rounded" style="background-color: grey">
            <div class="container justify-content-left">
                <h2>Update Question</h2>
                <hr class="style18">
                <h3 class="nav justify-content-end" >Hello ${sessionScope.USER.name}</h3>
                <hr class="style18">
                <ul class="nav d-flex flex-row text-white">
                    <li class="nav-item mx-3">
                        <a class="btn bg-dark mb-4 text-white"  href="SearchQuestion">Back to Question Bank</a>
                    </li>
                    <li class="nav-item mx-3">
                        <a href="CreateView"  class="btn bg-dark mb-4 text-white" >+Add New</a>
                    </li>
                    <li class="nav-item mx-3">
                        <a class="btn bg-dark mb-4 text-white"  onClick="return confirm('Do you want to logout?')" href="Logout">Logout</a>
                    </li>
                </ul>
                
            </div>
        </div>
                
        <div class="container-lg p-5  rounded " style="background-color: grey">
            <c:if test="${not empty requestScope.message}">
                <div class="container form-group p-3 d-flex flex-column bg-dark  justify-content-center rounded">
                    <label for="content" class="font-weight-bold text-white">${requestScope.message}</label>
                </div>
            </c:if>
            <form action="Update" method="POST" onsubmit="return confirm('Do you want to update?')">
                <input type="hidden" name="id" value="${requestScope.UPDATE_QUESTION.id}" />
                <div class="content">
                    <div class="container form-group p-3 d-flex flex-column bg-dark rounded" >
                        <label for="content" class="font-weight-bold text-white">Question Content: ${requestScope.error.content}</label>
                        <textarea class="form-control h5 font-weight-bold" id="content" name="content" rows="5">
                            ${requestScope.UPDATE_QUESTION.content}
                        </textarea>
                    </div>
                    <hr class="style18">
                    <div class="container d-flex flex-column w-75">
                        <c:forEach var="ans" items="${requestScope.UPDATE_QUESTION.answers}" varStatus="loop">
                            <div class="form-group p-3 bg-dark rounded">
                                <label for="&#${loop.index + 65}" class="font-weight-bold text-white">&#${loop.index + 65}: ${requestScope.error.A}</label>
                                <input type="text" name="&#${loop.index + 65}" class="form-control" id="&#${loop.index + 65}" value ="${ans.content}">
                            </div>
                        </c:forEach>
                    </div>
                    <hr class="style18">
                    <div class="form-group d-flex justify-content-center">
                        <div class="mx-5 bg-dark rounded p-3 ">
                            <label for="correct" class="font-weight-bold text-white">Correct Answer:</label>
                            <select  class=" form-control mdb-select md-form active-cyan float-xl-right" id="correct" name="correct">
                                <c:forEach var="ans" items="${requestScope.UPDATE_QUESTION.answers}" varStatus="loop">
                                    <option ${ans.correct? "selected='selected'": ""}>&#${loop.index + 97}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="mx-5 bg-dark rounded p-3  ">
                            <label for="subject" class="font-weight-bold text-white">Subject:</label>
                            <select class="form-control mdb-select md-form active-cyan float-xl-right" id="subject" name="subject">
                                <c:forEach var="subject" items="${sessionScope.SUBJECTS}">
                                    <option ${subject.code == requestScope.UPDATE_QUESTION.subjectID? "selected='selected'": ""}>
                                            ${subject}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <hr class="style18">
                <div class="form-group d-flex justify-content-center">
                    <input type="submit" value="Update Question"  class="btn bg-dark text-white justify-content-left m-3">
                    <a class="btn bg-dark m-3 text-white justify-content-right" href="#">Back to Question Bank</a>
                </div>
            </form>
        </div>        
    </body>
</html>
