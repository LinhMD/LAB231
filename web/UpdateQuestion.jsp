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
    <body style="background-color: #fff3e6" >
        <div class="container-lg pt-3 my-4 bg-primary text-white rounded">
            <div class="container justify-content-left">
                <h2>Update Question</h2>
                <p class="nav justify-content-end">Hello ${sessionScope.USER.name}</p>
                <ul class="nav d-flex flex-row-reverse">
                    <li class="nav-item">
                        <a class="btn bg-dark mb-4 text-white " style="color: antiquewhite" href="#">Link</a>
                    </li>
                    <li class="nav-item">
                        <a class="btn bg-dark mb-4 text-white" style="color: antiquewhite" href="#">Link</a>
                    </li>
                    <li class="nav-item">
                        <a class="btn bg-dark mb-4 text-white" style="color: antiquewhite" onClick="return confirm('Do you want to logout?')" href="Logout">Logout</a>
                    </li>
                </ul>
                
            </div>
        </div>
                
        <div class="container-lg p-5 rounded " style="background-color: #00ccff">
            <form action="UpdateQuestion" method="POST">
                <div class="content">
                    <label>
                        Subject
                        <select class=" form-control mdb-select md-form mx-3  active-cyan float-xl-right" name="subject">
                            <c:forEach var="subject" items="${sessionScope.SUBJECTS}">
                                <c:if test="${subject == sessionScope.UPDATE_QUESTION.subjectID}">
                                    <option selected="selected">${subject}</option>
                                </c:if>
                                <c:if test="${subject != sessionScope.UPDATE_QUESTION.subjectID}">
                                    <option>${subject}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </label>
                    <label class="rounded  p-2" >
                        <textarea class="form-control h5 m-3 p-2 font-weight-bold" name="content">
                            ${sessionScope.UPDATE_QUESTION.content}
                        </textarea>
                    </label>
                    <div class="container d-flex flex-column "> 
                        <label class="rounded  p-2"> 
                            D)
                            <input type="text" name="a" class="form-control" value ="${sessionScope.UPDATE_QUESTION.ansA}">
                        </label>
                        <label class="rounded  p-2"> 
                            B)
                            <input type="text" name="b" class="form-control" value ="${sessionScope.UPDATE_QUESTION.ansB}">
                        </label> 
                        <label class="rounded  p-2"> 
                            C)
                            <input type="text" name="c" class="form-control" value ="${sessionScope.UPDATE_QUESTION.ansC}">
                        </label> 
                        <label class="rounded  p-2"> 
                            D)
                            <input type="text" name="d" class="form-control" value ="${sessionScope.UPDATE_QUESTION.ansD}">
                        </label> 
                    </div>
                    <label>
                        Subject
                        <select class=" form-control mdb-select md-form mx-3  active-cyan float-xl-right" name="subject">
                            <c:forEach var="subject" items="">
                                <c:if test="${subject == sessionScope.UPDATE_QUESTION.subjectID}">
                                    <option selected="selected">${subject}</option>
                                </c:if>
                                <c:if test="${subject != sessionScope.UPDATE_QUESTION.subjectID}">
                                    <option>${subject}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </label>
                    <input type="submit" value="Update Question" class="m-3">
                </div> 
            </form>
            
           <a class="btn bg-dark mb-2 text-white justify-content-left" href="#">Back to Question Bank</a>
        </div>        
    </body>
</html>
