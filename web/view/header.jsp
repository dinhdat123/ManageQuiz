<%-- 
    Document   : header
    Created on : Sep 26, 2021, 8:19:49 AM
    Author     : DINHDAT
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <body>
        <header class="header-container">
            <ul class="header">
                <li class="header-item"><a href="home">Home</a></li>
                <li class="header-item"><a href="take-quiz">Take Quiz</a></li>
                <li class="header-item"><a href="make-quiz">Make Quiz</a></li>
                <li class="header-item"><a href="manage-quiz">Manage Quiz</a></li>
            <c:if test="${user!=Empty}">
                <li class="header-item"><a href="logout">Log out</a></li>
            </c:if>
            </ul>
        </header>
    </body>
</html>
