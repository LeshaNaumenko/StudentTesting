<%@include file="user_header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${appLocale.locale}" scope="session"/>
<fmt:setBundle basename="pagecontent.messages"/>

<div class="container">
    <div class="row" style="margin-top: 5%">
        <div class="col-md-1"></div>

        <div class="col-md-10 my-auto">
            <table class="table" border="2">
                <thead class="thead-dark">
                <tr>
                    <th>#</th>
                    <th><fmt:message key="fname"/></th>
                    <th><fmt:message key="lname"/></th>
                    <th><fmt:message key="email-form"/></th>
                    <th><fmt:message key="role"/></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:set var="counter" value="1"/>
                <c:forEach items="${allUser}" var="students">
                    <tr>
                        <th scope="row"><c:out value="${counter}"/></th>
                        <td><c:out value="${students.firstName}"/></td>
                        <td><c:out value="${students.lastName}"/></td>
                        <td><c:out value="${students.email}"/></td>
                        <td><fmt:message key="${students.role}"/></td>
                        <td>
                            <form class="my-form-menu" action="/test" method="post">
                                <input type="hidden" name="currentPage" value="1">
                                <input type="hidden" name="command" value="SHOW_RESULTS">
                                <input type="hidden" name="student_id" value="${students.id}">
                                <input style="color: blue" class="nav-link" type="submit"
                                       value="<fmt:message key="more"/>">
                            </form>
                        </td>
                    </tr>
                    <c:set var="counter" value="${counter+1}"/>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col-md-1"></div>
    </div>
</div>

<%@include file="footer.jsp" %>

