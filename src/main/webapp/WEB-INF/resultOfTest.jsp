<%@include file="user_header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${appLocale.locale}" scope="session"/>
<fmt:setBundle basename="pagecontent.messages"/>

<c:set var="test" value='${sessionScope["test"]}'/>
<c:set var="theme" value='${sessionScope["theme"]}'/>
<c:set var="answers" value='${sessionScope["answers"]}'/>

<div class="container">
    <div class="test-result">
        <table class="table" border="3">
            <thead>
            <tr>
                <th class="text-center table-primary" scope="col" colspan="2"><fmt:message
                        key="headline-result-of-test"/></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row" class="table-active"><fmt:message key="theme-result-of-test"/></th>
                <td class="table-active">${theme.themeName}</td>

            </tr>
            <tr>
                <th scope="row"><fmt:message key="test-date-result-of-test"/></th>
                <fmt:parseDate value="${test.date}" pattern="yyyy-MM-dd HH:mm:ss" var="Date"/>
                <td><fmt:formatDate type="date" value="${Date}"/></td>
            </tr>
            <tr>
                <th scope="row" class="table-active"><fmt:message key="start-testing-result-of-test"/></th>
                <fmt:parseDate value="${test.startTime}" pattern="yyyy-MM-dd HH:mm:ss" var="Start"/>
                <td class="table-active"><fmt:formatDate type="time" value="${Start}"/></td>
            </tr>
            <tr>
                <th scope="row"><fmt:message key="end-testing-result-of-test"/></th>
                <fmt:parseDate value="${test.endTime}" pattern="yyyy-MM-dd HH:mm:ss" var="End"/>
                <td><fmt:formatDate type="time" value="${End}"/></td>
            </tr>
            <tr>
                <th scope="row" class="table-active"><fmt:message key="testing-time-result-of-test"/></th>
                <td class="table-active">${theme.time}<fmt:message key="minutes"/></td>
            </tr>
            <tr>
                <th scope="row"><fmt:message key="your-time-result-of-test"/></th>
                <c:set var="dateParts" value="${fn:split(sessionScope.userTime, ':')}"/>
                <td><c:out value="${dateParts[0]}"/><fmt:message key="minutes"/> <c:out
                        value="${dateParts[1]}"/><fmt:message key="seconds"/></td>
            </tr>
            <tr>
                <th scope="row" class="table-active"><fmt:message key="passing-score-result-of-test"/></th>
                <td class="table-active">${theme.passingGrade}%</td>
            </tr>
            <tr>
                <th scope="row"><fmt:message key="your-score-result-of-test"/></th>
                <td>${test.grade}%</td>
            </tr>
            <tr>
                <c:choose>
                    <c:when test="${test.grade lt theme.passingGrade }">
                        <th scope="row" class="bg-danger"><fmt:message key="status-result-of-test"/></th>
                        <td class="bg-danger"><b><fmt:message key="FAILED"/></b></td>
                    </c:when>
                    <c:otherwise>
                        <th scope="row" class="bg-success"><fmt:message key="status-result-of-test"/></th>
                        <td class="bg-success"><b><fmt:message key="PASSED"/></b></td>
                    </c:otherwise>
                </c:choose>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="answers" style="margin-top: 30px">
        <table class="table table-striped">
            <tbody>
            <c:set var="counter" value="${0}"/>
            <c:forEach items="${answers}" var="answer">
                <c:set var="counter" value="${counter+1}"/>
                <tr>
                    <td rowspan=2><c:out value="${counter}"/></td>
                    <td colspan="2">
                        <div><c:out value="${answer.question}"/></div>
                    </td>
                    <c:choose>
                        <c:when test="${answer.status eq 'INCORRECT'}">
                            <td rowspan=2 class="table-danger">
                                <fmt:message key="${answer.status}"/>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td rowspan=2 class="table-success">
                                <fmt:message key="${answer.status}"/>
                            </td>
                        </c:otherwise>
                    </c:choose>
                </tr>
                <tr>
                    <td style="width: 50%">
                        <div><fmt:message key="your-answer"/> <c:out value="${answer.userAnswer}"/></div>
                    </td>
                    <td style="width: 50%">
                        <div><fmt:message key="correct-answer"/> <c:out value="${answer.correctAnswer}"/></div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<%@include file="footer.jsp" %>