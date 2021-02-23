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
    <body class="bg-dark">
        <div class="container pt-3 my-4 text-white rounded" style="background-color: grey">
            <div class="container justify-content-left mb-4">
                <h2>View Question</h2>
                <hr class="style18">
                <h3 class="nav justify-content-end" >Hello ${sessionScope.USER.name}</h3>
                <hr class="style18">
                <ul class="nav d-flex flex-row-reverse mb-4">
                    <li class="nav-item mx-3">
                        <a class="btn bg-dark text-white mb-4 justify-content-right" href="CreateView">Create Question</a>
                    </li>
                    <li class="nav-item mx-3">
                        <a href="CreateView"  class="btn bg-dark mb-4 text-white" >+Add New</a>
                    </li>
                    <li class="nav-item "mx-3>
                        <a class="btn bg-dark text-white mb-4" style="color: antiquewhite" onClick="return confirm('Do you want to logout?')" href="Logout">Logout</a>
                    </li>
                </ul>
            </div>
        </div>
                
        <div class="container p-5  rounded " style="background-color: grey">
            <form action="SearchQuestion" class=" my-3 py-3 rounded" style="background-color: #222831" method="post">
                <input type="hidden" name="pageNum" value="${requestScope.page}">
                <div class="container d-flex justify-content-center ">
                    <label class="mx-3">
                        <select class=" form-control mdb-select md-form   active-cyan" name="subject">
                            <option value="">------All Subject------</option>
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
                    <div class="md-form active-cyan active-cyan-2 mx-3">
                        <input class="form-control" type="text" placeholder="Search question" aria-label="Search" name="content" value="${param.content}"/>
                    </div>
                    <div class=" active-cyan mx-3 float-xl-right">
                        <input id="for-search-delete" class="form-check-input" id="materialIndeterminate" type="checkbox" name="status" ${param.status != null? 'checked="checked"':""}>
                        <label for="for-search-delete" class="text-white ">
                            Delete Question
                        </label>
                    </div>
                </div>

                <div class="container mb-3 p-1 d-flex justify-content-center">
                    <input type="submit" class="btn mx-3 btn-dark text-white " name="btnAction" value="Previous" ${page == 0?'disabled="disabled"':''}/>
                    <input type="submit" class="btn mx-3 btn-dark text-white " name="btnAction" value="Search"/>
                    <input type="submit" class="btn mx-3 btn-dark text-white " name="btnAction" value="Next"  ${!hasNext?'disabled="disabled"':''}/>
                </div>
                <c:if test="${not empty requestScope.message}">
                    <p class="container mb-3 p-1 d-flex justify-content-center text-white">
                        ${requestScope.message}
                    </p>
                </c:if>    
                   
            </form>
            
            <div class="container my-3 py-3">
                <table class="table table-hover p-3 table-dark table-striped table-bordered rounded">
                    <thead>
                        <tr>
                            <th>Question</th>
                            <th>Answers</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="question" items="${requestScope.QUESTIONS}">
                            <tr>
                                <td>
                                    <c:url var="UpdateURL" value="B4Update">
                                        <c:param name="id">${question.id}</c:param>
                                    </c:url>
                                    <a href="${UpdateURL}" style="color: white"> ${question.content}</a>
                                </td>
                                <td>
                                    <c:forEach var="ans" items="${question.answers}" varStatus="counter">
                                        <div ${ans.correct? 'style="color: greenyellow"' : ''} > &#${counter.index + 65}) ${ans.content}</div>
                                    </c:forEach>
                                </td>
                                <td>
                                    <c:url var="DeleteURL" value="Delete">
                                        <c:param name="id">${question.id}</c:param>
                                        <c:if test="${not empty param.status}">
                                            <c:param name="status">1</c:param>
                                        </c:if>
                                        <c:param name="content">${param.content}</c:param>
                                        <c:param name="subject">${param.subject}</c:param>
                                    </c:url>
                                    <a href="${DeleteURL}"  class="btn btn-dark  float-right" onClick="return confirm('Do you want to '+ ${param.status == null?'remove': 'retrive'} + ' question')">${param.status == null? "Remove question" : "Retrive question"} </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <a href="CreateView"  class="btn btn-dark  float-right">+Add New</a>
            </div>
        </div>
    </body>
</html>
