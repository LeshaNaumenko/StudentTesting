<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${appLocale.locale}" scope="session"/>
<fmt:setBundle basename="pagecontent.messages" />
<html>
<head>
    <title>Sign up</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <%--<link href="<c:url value="../css/bootstrap.css" />" rel="stylesheet">--%>
    <style>
        .err-message-register{
            color: #e4606d;
        }
    </style>
</head>
<body>
<form class="form-horizontal" method="Post" action="/test">
    <input name="command" type="hidden" value="REGISTER">
    <fieldset>
        <legend><fmt:message key="headline-reg-form"/></legend>
        <div class="form-group">
            <label class="col-md-4 control-label" for="fname"><fmt:message key="fname"/></label>
            <div class="col-md-4">
                <input id="fname" name="fname" type="text" placeholder="John" class="form-control input-md" required="">
                <input value="REGISTER" name="Command" type="text" hidden>
                <c:if test="${requestScope.errFirstMessage != null}">
                    <small class="err-message-register"><c:out value="${requestScope.errFirstMessage}"/></small>
                </c:if>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label" for="lname"><fmt:message key="lname"/></label>
            <div class="col-md-4">
                <input id="lname" name="lname" type="text" placeholder="Doe" class="form-control input-md" required="">
                <c:if test="${requestScope.errLastMessage != null}">
                    <small class="err-message-register"><c:out value="${requestScope.errLastMessage}"/></small>
                </c:if>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label" for="email"><fmt:message key="email-form"/></label>
            <div class="col-md-4">
                <input id="email" name="email" type="text" placeholder="johndoe@example.com" class="form-control input-md" required="">
                <c:if test="${requestScope.errEmailMessage != null}">
                    <small class="err-message-register"><c:out value="${requestScope.errEmailMessage}"/></small>
                </c:if>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label" for="password"><fmt:message key="password-form"/></label>
            <div class="col-md-4">
                <input id="password" name="password" type="password" placeholder="" class="form-control input-md"
                       required="">
                <c:if test="${requestScope.errPassMessage != null}">
                    <small class="err-message-register"><c:out value="${requestScope.errPassMessage}"/></small>
                </c:if>
                <c:if test="${requestScope.errMessage != null}">
                    <small class="err-message-register"><c:out value="${requestScope.errMessage}"/></small>
                </c:if>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label" for="save"></label>
            <div class="col-md-8">
                <button id="save" name="save" class="btn btn-success"><fmt:message key="reg-button-form"/></button>
            </div>
        </div>
    </fieldset>
</form>
</body>
<%--<script src="<c:url value="../js/bootstrap.js"/>" type="text/javascript"></script>--%>
<%--<script src="<c:url value="../js/jquery-3.3.1.min.js"/>" type="text/javascript"></script>--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>

</html>
