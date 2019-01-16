<%@ page import="model.entity.Question" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${appLocale.locale}" scope="session" />
<fmt:setBundle basename="pagecontent.messages" />
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>StudentTesting</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <link href="<c:url value="../css/user.css" />"  rel="stylesheet">
    <script src="<c:url value="../js/my.js"/>"></script>
</head>
<body>
<div class="container">
    <form id="form" method="POST" action="/test">
        <input name="command" type="hidden" value="SAVE_THE_RESULTS">
        <div class="countdown"></div>
        <table class="table table-bordered">
            <tbody>
            <%
                int counte = 0;
                List<Question> questionList = (List<Question>) request.getSession().getAttribute("listOfQuestion");
            %>
            <c:set var="counter" value="0"/>
            <c:forEach var="question" items="${listOfQuestion}">
                <tr>
                    <th scope="row"><c:out value="${counter+1}"/></th>
                    <td>
                        <%
                            Question question = questionList.get(counte++);
                            String descriptionOfQuestion = question.getDescriptionOfQuestion();
                            String[] split = descriptionOfQuestion.split("\\n");
                            for (String s : split) {
                                out.print("<pre>" + s + "</pre>");
                            }
                        %>
                    </td>
                </tr>
                <tr>
                    <th scope="row"></th>
                    <td>
                        <div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="optradio${counter}" value="${question.option1}">${question.option1}
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="optradio${counter}" value="${question.option2}">${question.option2}
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="optradio${counter}" value="${question.option3}">${question.option3}
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="optradio${counter}" value="${question.option4}">${question.option4}
                                </label>
                            </div>
                        </div>
                    </td>
                </tr>
                <c:set var="counter" value="${counter+1}"/>
            </c:forEach>
            </tbody>
        </table>
        <input class="btn btn-primary btn-lg complete-button" type="submit" value="<fmt:message key="complete-test"/>">
    </form>
</div>
<script>
    var timer2 = "<c:out value="${requestScope.user_time}:00" />";
    var interval = setInterval(function() {
        var timer = timer2.split(':');
        var minutes = parseInt(timer[0], 10);
        var seconds = parseInt(timer[1], 10);
        --seconds;
        minutes = (seconds < 0) ? --minutes : minutes;
        seconds = (seconds < 0) ? 59 : seconds;
        seconds = (seconds < 10) ? '0' + seconds : seconds;
        $('.countdown').html(minutes + ':' + seconds);
        if (minutes < 0) clearInterval(interval);
        if ((seconds <= 0) && (minutes <= 0)) {
            clearInterval(interval);
            $('#form').submit();
        }
        timer2 = minutes + ':' + seconds;
    }, 1000);

    $(window).on('beforeunload', function(){
        return "Any changes will be lost";
    });

    // Form Submit
    $(document).on("submit", "form", function(event){
        // disable unload warning
        $(window).off('beforeunload');
    });
</script>
<%@include file="footer.jsp" %>