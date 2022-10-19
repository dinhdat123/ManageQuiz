<%-- 
    Document   : result
    Created on : Sep 29, 2021, 6:26:01 PM
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
            <%@include file="header.jsp"%>
            <c:set var="mark" scope="request" value="${requestScope.mark}"/>
            <c:set var="percent" scope="request" value="${requestScope.percent}"/>
            <c:set var="isPass" scope="request" value="${requestScope.isPass}"/>
            <c:choose> 
                <c:when test="${mark!=Empty}">
                    <div class="content-container">
                        <div class="infor-container">
                            <h3 class="label">Your score</h3>
                            <span id="mark" class="infor">${mark}</span>
                            <span class="infor" id="percent">(${percent}%)</span> 
                            <span class="infor isPass"> - ${isPass}</span>                 
                        </div>
                        <div class="infor-container">
                            <h3 class="label" >Take another test </h3>
                            <a href="take-quiz">
                                <button class="button btn-start">Start</button>
                            </a>
                        </div>
                    </div>       
                </c:when>
                <c:otherwise>
                    <h3 class="mess mess-error">${requestScope.mess}</h3>
                </c:otherwise>
            </c:choose> 
        </div>
    </body>
</html>
