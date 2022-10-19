<%-- 
    Document   : takeQuiz
    Created on : Sep 28, 2021, 3:26:01 PM
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
            <c:set var="questions" scope="session" value="${sessionScope.questions}"/>
            <c:set var="labeltime" scope="request" value="${requestScope.labeltime}"/>
            <%@include file="header.jsp"%>
            <div class="content-container">
                <h3 class="label">Welcome </h3> 
                <span class="infor">${user.getUserName()}</span> 
                <div id="timer">
                    <h3 class="label">Time remaining</h3>
                    <span id="time">${labeltime}</span>
                </div>
                <div id="answer-container">                    
                    <c:forEach items="${questions}" var="q">
                        <div class="ques" id="${q.getId()}">
                            <h3 class="ques-title">${q.getContent()}</h3> 
                            <c:set var="answers" value="${q.getAnswers()}"/>
                            <c:forEach items="${answers}" var="a">
                                <input name="${q.getId()}" class="op"
                                       id="${a.getId()}" 
                                       type="checkbox"
                                       value="${a.getId()}"/>
                                <label class="answer-label login-label text-display" for="${a.getId()}">${a.getContent()}</label>
                                <br> 
                            </c:forEach>                           
                        </div>
                    </c:forEach>    
                    <button class="button" id="nextBtn">Next</button>
                </div>
            </div>
            <form id="result-form" type="hidden" method="post" action="take-quiz">
                <input id="result" name="result" type="hidden"/> 
            </form>  
        </div>
        <script src="js/takeQuiz.js" type="text/javascript"></script>
    </body>
</html>
