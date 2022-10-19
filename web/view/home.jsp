<%-- 
    Document   : home
    Created on : Sep 28, 2021, 3:26:01 PM
    Author     : DINHDAT
--%>

<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <title>Home</title>
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
                <div class="mess mess-error">
                    <c:if test="${mess!=Empty}">
                        <h5>${mess}</h5>  
                    </c:if> 
                </div> 
            </div> 
        </div>
    </body>
</html>