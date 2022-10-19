<%-- 
    Document   : editQuiz
    Created on : Sep 28, 2021, 11:37:49 AM
    Author     : DINHDAT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Quiz</title>
        <link href="css/base.css" rel="stylesheet" type="text/css"/>
        <link href="css/make-quiz.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <%@include file="header.jsp"%>
            <div class="content-container">
                <form id="make-form" class="form" method="post" action="edit">
                    <div class="form-item">
                        <label for="question" class="form-label">Question: </label>
                        <textarea id="question" required name="question">${question.content}</textarea>
                    </div>   
                    <div class="form-item">
                        <label for="option1" class="form-label">Option1: </label>   
                        <textarea id="option1" required name="option1"><c:if test="${listAns.size() ge 1}"> ${listAns.get(0).content}</c:if></textarea>
                    </div>
                    <div class="form-item">
                        <label for="option2" class="form-label">Option2: </label>     
                        <textarea id="option2" required name="option2"><c:if test="${listAns.size() ge 2}"> ${listAns.get(1).content}</c:if></textarea>
                    </div>
                    <div class="form-item">
                        <label for="option3" class="form-label">Option3: </label>
                        <textarea id="option3" required name="option3"><c:if test="${listAns.size() ge 3}"> ${listAns.get(2).content}</c:if></textarea>
                    </div>
                    <div class="form-item">
                        <label for="option4" class="form-label">Option4: </label> 
                        <textarea id="option4" required name="option4"><c:if test="${listAns.size() ge 4}"> ${listAns.get(3).content}</c:if></textarea>
                    </div>

                    <div class="form-item">  
                        <label class="form-label">Answer(s):</label> 
                        <div class="option-container">
                            <div class="option-item">
                                <input class="option-answer"  name="op1" id="op1" type="checkbox" value="true" <c:if test="${listAns.size() ge 1 and listAns.get(0).isTrue}">
                                       checked="true" </c:if>/>
                                <label class="form-label op-label" for="op1">Option 1</label>
                            </div>
                            <div class="option-item">
                                <input class="option-answer" name="op2" id="op2" type="checkbox" value="true"<c:if test="${listAns.size() ge 2 and listAns.get(1).isTrue}">
                                       checked="true" </c:if>/>
                                <label class="form-label op-label" for="op2">Option 2</label>
                            </div>
                            <div class="option-item">
                                <input class="option-answer" name="op3" id="op3" type="checkbox" value="true"<c:if test="${listAns.size() ge 3 and listAns.get(2).isTrue}">
                                       checked="true" </c:if>/> 
                                <label class="form-label op-label" for="op3">Option 3</label>
                            </div>
                            <div class="option-item">
                                <input class="option-answer" name="op4" id="op4" type="checkbox" value="true"<c:if test="${listAns.size() ge 4 and listAns.get(3).isTrue}">
                                       checked="true" </c:if>/> 
                                <label class="form-label op-label" for="op4">Option 4</label>       
                            </div>
                        </div>
                    </div>
                    <input type="hidden" name="id" value="${question.id}"/>
                    <div class="mess">
                        <input id="btn-save" class="button save-btn" type="submit" value="Save"/> 
                        <h5 id="mess" style="color: #270">${mess}</h5>  
                    </div>                   
                </form>
            </div>
        </div>
        <script src="js/makeQuiz.js" type="text/javascript"></script>
    </body>
</html>
