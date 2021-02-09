<%-- 
    Document   : view
    Created on : Feb 5, 2021, 1:53:29 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Question</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body style="background-color: #fff3e6">
        <div class="container-lg p-3 my-3 bg-primary text-white">
            <div class="container justify-content-left">
                <h2>Question Bank</h2>
                <p class="nav justify-content-end">Hello ${sessionScope.USER.name}</p>
                <ul class="nav justify-content-end">
                    <li class="nav-item">
                        <a class="btn btn-primary mb-5  " href="#">Link</a>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-primary mb-5   " style="color: antiquewhite" href="#">Link</a>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-primary mb-5   " style="color: antiquewhite" href="#">Link</a>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-primary mb-5" style="color: antiquewhite" onClick="return confirm('Do you want to logout?')" href="Logout">Logout</a>
                    </li>
                </ul>
            </div>
        </div>
        <form action="SearchQuestion">
            <div class="container-lg mt-5 p-2 border-top d-flex justify-content-center">
                <label>
                    <select class=" form-control mdb-select md-form mx-3  active-cyan float-xl-left" name="subject">
                        <option value="" disabled="true">------All Subject------</option>
                        <c:forEach var="subject" items="${SUBJECTS}">
                            <c:if test="${subject == param.subject}">
                                <option selected="selected">${subject}</option>
                            </c:if>
                            <c:if test="${subject != param.subject}">
                                <option>${subject}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </label>
                <div class="md-form active-cyan active-cyan-2 mx-3  float-xl-right">
                    <input class="form-control" type="text" placeholder="Search question" aria-label="Search" name="content" value="${param.content}"/>
                </div>
                <div class=" active-cyan mx-3 float-xl-right">
                    <input id="for-search-delete" class="form-check-input" type="checkbox" name="status" value="Delete Question">
                    <label for="for-search-delete" class="text-black">
                        Delete Question
                    </label>
                </div>
            </div>

            <div class="container-lg mb-3 p-1 d-flex justify-content-center">
                <input type="submit" class="btn btn-primary m-5 p-3 float-xl-left" name="btnAction" value="Previous"/>
                <input type="submit" class="btn btn-primary m-5 p-3 float-xl-right" name="btnAction" value="Search"/>
                <input type="submit" class="btn btn-primary m-5 p-3 float-xl-right" name="btnAction" value="Next"/>
            </div>
        </form>
        <div class="container-lg my-3 py-3">
            <table class="table table-hover table-primary table-striped table-bordered">
                <thead>
                    <tr>
                        <th>Question</th>
                        <th>Answers</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="question" items="${sessionScope.QUESTIONS}">
                        <tr>
                            <td>
                                <c:url var="UpdateURL" value="UpdateView">
                                    <c:param name="id">${question.id}</c:param>
                                </c:url>
                                <a href="${UpdateURL}" style="color: black"> ${question.content}</a>
                            </td>
                            <td>
                                a) ${question.ansA} <br/>
                                b) ${question.ansB} <br/>
                                c) ${question.ansC} <br/>
                                d) ${question.ansD} <br/>
                                Corrected Answer: ${question.correct}
                            </td>
                            <td>
                                <a href="#"  class="btn btn-primary  float-right">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <a href="Login.jsp"  class="btn btn-primary mb-5  float-right ">+Add New</a>

        </div>
    </body>
</html>
