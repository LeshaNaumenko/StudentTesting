<%@include file="user_header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${appLocale.locale}" scope="session"/>
<fmt:setBundle basename="pagecontent.messages"/>
<c:set var="listdto" value='${requestScope["testInfoList"]}'/>
<div class="std-info">
    <h3 style="text-align: center"><c:out
            value="${sessionScope.student.firstName} ${sessionScope.student.lastName}"/></h3>
    <h6 style="text-align: center"><c:out value="${sessionScope.student.email}"/></h6>
    <h6 style="text-align: center"><fmt:message key="${sessionScope.student.role}"/></h6>
</div>
<c:choose>
    <c:when test="${not empty listdto}">
        <div class="user-result">
            <table class="table table-sm" border="1">
                <thead>
                <tr>
                    <th scope="col" >#</th>
                    <th scope="col" ><fmt:message key="course-name-test-page"/></th>
                    <th scope="col" ><fmt:message key="theme-name-test-page"/></th>
                    <th scope="col" ><fmt:message key="test-date-result-of-test"/></th>
                    <th scope="col" ><fmt:message key="start-testing-result-of-test"/></th>
                    <th scope="col" ><fmt:message key="end-testing-result-of-test"/></th>
                    <th scope="col" ><fmt:message key="testing-time-result-of-test"/></th>
                    <th scope="col" ><fmt:message key="your-time-result-of-test"/></th>
                    <th scope="col" ><fmt:message key="passing-score-result-of-test"/></th>
                    <th scope="col" ><fmt:message key="your-score-result-of-test"/></th>
                    <th scope="col" ><fmt:message key="status-result-of-test"/></th>
                    <th scope="col" ><fmt:message key="more"/></th>
                </tr>
                </thead>
                <tbody>
                <c:set var="counter" value="1"/>
                <c:forEach var="dto" items="${listdto}">
                    <tr>
                        <th scope="row">${counter}</th>
                        <td >${dto.courseName}</td>
                        <td >${dto.themeName}</td>

                        <fmt:parseDate value="${dto.date}" pattern="yyyy-MM-dd HH:mm:ss" var="Date"/>
                        <td ><fmt:formatDate type="date" value="${Date}"/></td>

                        <fmt:parseDate value="${dto.startTime}" pattern="yyyy-MM-dd HH:mm:ss" var="Start"/>
                        <td ><fmt:formatDate type="time" value="${Start}"/></td>

                        <fmt:parseDate value="${dto.endTime}" pattern="yyyy-MM-dd HH:mm:ss" var="End"/>
                        <td ><fmt:formatDate type="time" value="${End}"/></td>

                        <td >${dto.themeTime}<fmt:message key="minutes"/></td>

                        <c:set var="dateParts" value="${fn:split(dto.userTime, ':')}"/>
                        <td ><c:out value="${dateParts[0]}"/><fmt:message key="minutes"/> <c:out
                                value="${dateParts[1]}"/><fmt:message key="seconds"/></td>

                        <td >${dto.passingGrade}%</td>
                        <td >${dto.grade}%</td>
                        <c:choose>
                            <c:when test="${dto.grade lt dto.passingGrade }">
                                <td align="center" class="bg-danger"><fmt:message key="${dto.status}"/></td>
                            </c:when>
                            <c:otherwise>
                                <td align="center" class="bg-success"><fmt:message key="${dto.status}"/></td>
                            </c:otherwise>
                        </c:choose>
                        <%--!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!--%>
                        <td><a class="nav-link" href="/test?more=<c:out value="${dto.testId}"/>">More</a></td>
                    </tr>
                    <c:set var="counter" value="${counter+1}"/>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <nav aria-label="Navigation for test" >
            <ul class="pagination" style="max-width: 400px;margin-right: auto;margin-left: auto;">
                <c:if test="${sessionScope.currentPage != 1}">
                    <li class="page-item">
                    <form class="my-form-menu" action="/test" method="post">
                        <input type="hidden" name="command" value="${sessionScope.comm}">
                        <input type="hidden" name="recordsPerPage" value="${recordsPerPage}">
                        <input type="hidden" name="currentPage" value="${sessionScope.currentPage-1}">
                        <%--<input class="nav-link" type="submit" value="<fmt:message key="results-menu"/>">--%>
                        <input class="nav-link" type="submit" value="Previous">
                    </form>
                    </li>
                </c:if>

                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${sessionScope.currentPage eq i}">
                            <li class="page-item active">
                                <form class="my-form-menu" >
                                        <%--<input class="nav-link" type="submit" value="<fmt:message key="results-menu"/>">--%>
                                    <input class="nav-link" type="submit" value="${i}">
                                </form>
                            </li>
                            <%--<li class="page-item active"><a class="page-link"> ${i} <span class="sr-only">(current)</span></a> </li>--%>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <form class="my-form-menu" action="/test" method="post">
                                    <input type="hidden" name="command" value="${sessionScope.comm}">
                                    <input type="hidden" name="recordsPerPage" value="${recordsPerPage}">
                                    <input type="hidden" name="currentPage" value="${i}">
                                        <%--<input class="nav-link" type="submit" value="<fmt:message key="results-menu"/>">--%>
                                    <input class="nav-link" type="submit" value="${i}">
                                </form>
                            </li>
                            <%--<li class="page-item"><a class="page-link" href="test?recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a> </li>--%>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${sessionScope.currentPage lt noOfPages}">
                    <li class="page-item">
                        <form class="my-form-menu" action="/test" method="post">
                            <input type="hidden" name="command" value="${sessionScope.comm}">
                            <input type="hidden" name="recordsPerPage" value="${recordsPerPage}">
                            <input type="hidden" name="currentPage" value="${sessionScope.currentPage+1}">
                                <%--<input class="nav-link" type="submit" value="<fmt:message key="results-menu"/>">--%>
                            <input class="nav-link" type="submit" value="Next">
                        </form>
                    </li>
                </c:if>
            </ul>
        </nav>
    </c:when>
    <c:otherwise>
        <h1 style="text-align: center;margin-top: 5%;color:red"><fmt:message key="no-test"/></h1>
    </c:otherwise>
</c:choose>
<%@include file="footer.jsp" %>