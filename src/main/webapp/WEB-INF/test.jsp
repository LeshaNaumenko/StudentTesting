<%@include file="user_header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${appLocale.locale}" scope="session" />
<fmt:setBundle basename="pagecontent.messages" />
<div class="container">
    <div class="row choose-theme">
        <div class="col-md-3 ">
            <div class="dropdown ">
                <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown"><fmt:message key="choose-theme-test-page"/>
                </button>
                <ul class="dropdown-menu">
                    <c:forEach items="${course_name_list}" var="name">
                        <s:set var="parameter" value="${name}"/>
                        <c:if test="${fn:contains(parameter, '+')}">
                            <s:set var="parameter" value="${fn:replace(parameter,'+','%2B')}"/>
                        </c:if>
                        <a class="dropdown-item" href="/test?course_name=<c:out value="${parameter}"/>&command=GET_LIST_OF_THEMES_BY_COURSES_NAME"><c:out
                                value="${name}"/></a>
                    </c:forEach>
                    <%--<%request.getSession().setAttribute("command", "GET_LIST_OF_THEMES_BY_COURSES_NAME");%>--%>
                </ul>
            </div>
        </div>

        <div class="col-md-9 my-auto">
            <c:if test="${not empty requestScope.error}">
                <c:out value="${requestScope.error}"/>
            </c:if>
            <c:if test="${not empty themesByCourse}">
                <table class="table" border="2">
                    <thead class="thead-dark">
                    <tr>
                        <th style="width: 5%">#</th>
                        <th style="width: 20%"><fmt:message key="course-name-test-page"/></th>
                        <th style="width: 20%"><fmt:message key="theme-name-test-page"/></th>
                        <th style="width: 10%"><fmt:message key="time-test-page"/></th>
                        <th style="width: 20%"><fmt:message key="grade-test-page"/></th>
                        <th style="width: 10%"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="counter" value="0"/>
                    <c:forEach items="${themesByCourse}" var="theme">
                        <tr>
                            <th scope="row"><c:out value="${counter+1}"/></th>
                            <td><c:out value="${theme.courseName}"/></td>
                            <td><c:out value="${theme.themeName}"/></td>
                            <td><c:out value="${theme.time}"/><fmt:message key="minutes"/> </td>
                            <td><c:out value="${theme.passingGrade}"/>%</td>
                            <td bgcolor="#007bff">
                                <form class="my-form-menu" action="/test" method="post">
                                    <input type="hidden" name="theme_id" value="<c:out value="${theme.id}"/>">
                                    <input type="hidden" name="command" value="GET_TEST">
                                    <input style="color: white" class="nav-link" type="submit" value="<fmt:message key="start-test"/>">
                                </form>
                            </td>
                        </tr>
                        <c:set var="counter" value="${counter+1}"/>
                    </c:forEach>
                    </tbody>
                </table>
                <c:remove var="themesByCourse" scope="session"/>
            </c:if>
        </div>
    </div>
</div>

<%@include file="footer.jsp" %>


