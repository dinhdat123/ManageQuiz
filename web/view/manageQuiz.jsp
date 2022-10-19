<%-- 
    Document   : manageQuiz
    Created on : Sep 28, 2021, 1:26:01 PM
    Author     : DINHDAT
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.Question"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Quiz</title>
        <link href="css/base.css" rel="stylesheet" type="text/css"/>
        <link href="css/manage-quiz.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <%@include file="header.jsp"%>
            <div class="content-container main-container">
                <div class="form-item">
                    <h3 class="label">Number of Question:</h3> 
                    <span class="infor number-question">${requestScope.number}</span>
                </div>
                <ul class="form-item ques-item">

                    <li><h3 class="infor">Question</h3></li>              
                    <li><h3 class="infor date-created">Date Created</h3></li>

                </ul>
                <c:forEach items="${requestScope.questions}" var="q">
                    <ul class="form-item ques-item">     
                        <li class="left">
                            <h3 class="label">${q.getContent()}</h3>
                        </li> 
                        <li class="right">
                            <h3 class="label date">${q.getCreatedString()}</h3>
                            <a class="edit" href="edit?qid=${q.getId()}">Edit</a> 
                            <a class="delete" href="delete?qid=${q.getId()}">Delete</a> 
                        </li>  

                    </ul>
                </c:forEach>
            </div>

            <nav class="pagination-container">
                <ul class="pagination">
                    <c:forEach var="index" begin="1" end="${requestScope.totalPage}">
                        <c:choose>
                            <c:when test="${index == requestScope.pageIndex}">
                                <li class="page-item page-item-active">
                                    <a class="page-link page-link-active" href="manage-quiz?page=${index}">${index}</a>
                                </li> 
                            </c:when>
                            <c:otherwise>
                                <li class="page-item">
                                    <a class="page-link" href="manage-quiz?page=${index}">${index}</a>
                                </li>    
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                </ul>
            </nav>
        </div>
    </body>

</html>
