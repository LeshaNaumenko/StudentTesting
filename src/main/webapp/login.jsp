<%@ page import="java.util.Locale" %>
<%@ page import="utility.LanguageManager" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${appLocale eq null}">
    <c:set scope="session" var="appLocale" value="<%=LanguageManager.INSTANCE%>"/>
</c:if>
<fmt:setLocale value="${appLocale.locale}" scope="session"/>
<fmt:setBundle basename="pagecontent.messages"/>

<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <style>
        <%@include file="css/style.css" %>
    </style>
</head>
<body class="text-center">
<div class="container">
    <div class="row">
        <form method="post" action="/test" class="form-signin">
            <input name="command" type="hidden" value="LOGIN_CHECK">
            <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="signIn"/></h1>
            <label for="inputEmail" class="sr-only">Email address</label>
            <input  type="email" id="inputEmail" name="email" class="form-control"
                   placeholder="<fmt:message key="email-form"/>" required
                   autofocus>
            <c:if test="${requestScope.errEmailMessage != null}">
                <small class="err-message-register"><c:out value="${requestScope.errEmailMessage}"/></small>
            </c:if>
            <label for="inputPassword" class="sr-only">Password</label>
            <input  type="password" id="inputPassword" name="password" class="form-control"
                   placeholder="<fmt:message key="password-form"/>" required>
            <c:if test="${requestScope.errPassMessage != null}">
                <small class="err-message-register"><c:out value="${requestScope.errPassMessage}"/></small>
            </c:if>
            <div class="form-group form-check">
                <c:if test="${requestScope.error != null}">
                    <small class="err-message-register"><c:out value="${requestScope.error}"/></small>
                </c:if>
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="signIn-btn"/></button>
            <br>
        </form>
    </div>
    <div class="row ">
        <form class="my-form-menu" action="/test" method="post">
            <input type="hidden" name="command" value="REGISTER_FORWARD">
            <input class="nav-link" type="submit" value="<fmt:message key="create-account"/>">
        </form>
    </div>
</div>


<%--<script src="<c:url value="/js/jquery-3.3.1.min.js"/>"></script>--%>
<%--<script src="<c:url value="js/jquery-3.3.1.min.js"/>"></script>--%>
<%--<script src="<c:url value="/js/bootstrap.js"/>"></script>--%>

</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
</html>
