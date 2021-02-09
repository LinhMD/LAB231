<%-- 
    Document   : UpdateQuestion
    Created on : Feb 7, 2021, 2:48:44 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                    <textarea class="form-control h5 m-3 p-2 font-weight-bold" name="content">
                        
                    </textarea>
                    <div class="container d-flex flex-column "> 
                        <label class="rounded  p-2"> 
                            <p>D)</p>
                            <input type="text" name="ansA" class="form-control" value ="His boxing gloves "> 
                        </label>
                        <label class="rounded  p-2"> 
                            <p>D)</p>
                            <input type="text" name="ansB" class="form-control" value ="A parachute"> 
                        </label> 
                        <label class="rounded  p-2"> 
                            <p>C)</p>
                            <input type="text" name="ansD" class="form-control" value ="Nothing"> 
                        </label> 
                        <label class="rounded  p-2"> 
                            <p>D)</p>
                            <input type="text" name="ansD" class="form-control" value ="A world little belt "> 
                        </label> 
                    </div> 

                    <input type="submit" value="Update Question" class="m-3">
                </div> 
            </form>
            
           <a class="btn bg-dark mb-2 text-white justify-content-left" href="#">Back to Question Bank</a>
        </div>        
    </body>
</html>
