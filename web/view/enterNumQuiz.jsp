<%-- 
    Document   : enterNumQuiz
    Created on : Sep 28, 2021, 7:37:49 AM
    Author     : DINHDAT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Take Quiz</title>
        <link href="css/take-quiz.css" rel="stylesheet" type="text/css"/>        
        <link href="css/base.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <c:set var="user" scope="session" value="${sessionScope.user}"/>
            <c:set var="mess" scope="request" value="${requestScope.mess}"/>
            <%@include file="header.jsp"%>
            <div class="content-container">
                <h3 class="label">Welcome </h3> 
                <span class="infor">${sessionScope.user.getUserName()}</span> 
                <h3 id="number-ques" class="label">Enter number of Questions</h3><br>
                <form id="number-form" class="form" method="get" action="take-quiz">
                    <div class="form-item">
                        <input min="1" max="${requestScope.maxQuiz}" type="number" id="number-input" name="number"/>
                    </div>
                    <div class="form-item form-button">
                        <input class="button" id="number-button" type="submit" value="Start"/>
                    </div>                        
                </form>
                <c:if test="${mess!=Empty}">
                    <h5 class="mess mess-error">${mess}</h5>  
                </c:if>   
            </div>
        </div> 
    </body>
</html>
