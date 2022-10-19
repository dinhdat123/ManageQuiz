<%-- 
    Document   : error
    Created on : Oct 02, 2021, 4:37:49 AM
    Author     : DINHDAT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>500 Server Error</title>
        <link href="css/base.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <%@include file="header.jsp"%>
            <center>
                <h1 class="mess-error mess">500 Server Error</h1>
                <c:set var="error" scope="request" value="${requestScope.error}"/>
                <span>${error}</span>
                <p>Try to refresh this page or feel free to contact us if the problem persists.</p>
            </center>
        </div>
    </body>
</html>
