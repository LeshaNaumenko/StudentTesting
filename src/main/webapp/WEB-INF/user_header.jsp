<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${appLocale.locale}" scope="session"/>
<fmt:setBundle basename="pagecontent.messages"/>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>StudentTesting</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
    <%--<link href="<c:url value="../css/user.css" />"  rel="stylesheet">--%>
    <style>

        .nav-item {
            padding-left: 40px;
        }

        .dropdown {
            margin-top: 5%;
        }

        .choose-theme {
            margin-top: 5%;
        }

        .my-menu {
            box-shadow: 0px -7px 10px -9px #000000;
        }

        .complete-button {
            margin-left: 40%;
        }

        .test-result {
            justify-content: center;
            margin-top: 20px;
            margin-right: auto;
            margin-left: auto;
            width: 600px;
        }

        .countdown {
            position: fixed; /* Фиксированное положение */
            right: 10px; /* Расстояние от правого края окна браузера */
            top: 5%; /* Расстояние сверху */
            padding: 10px; /* Поля вокруг текста */
            background: #ff000057; /* Цвет фона */
            border: 1px solid #333; /* Параметры рамки */
            width: 90px;
            text-align: center;

        }

        .login-help {
            font-size: 12px;
        }

        .my-form-menu {
            margin-bottom: 0;
        }

        .my-form-menu input {
            background: none;
            border: none;
            cursor: pointer;
        }
        .my-form-menu::before,
        .my-form-menu::after {
            box-sizing: content-box;
        }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script>
        function replace_search(name, value) {
            var str = location.search;
            if (new RegExp("[&?]" + name + "([=&].+)?$").test(str)) {
                str = str.replace(new RegExp("(?:[&?])" + name + "[^&]*", "g"), "")
            }
            str += "&";
            str += name + "=" + value;
            str = "?" + str.slice(1);
            // there is an official order for the query and the hash if you didn't know.
            location.assign(location.origin + location.pathname + str + location.hash)
        };
        /*        $(function () {
                    setNavigation();
                });

                function setNavigation() {
                    var path = window.location.pathname;
                    path = path.replace(/\/$/, "");
                    path = decodeURIComponent(path);

                    $(".mynav a").each(function () {
                        var href = $(this).attr('href');
                        if (path.substring(0, href.length) === href) {
                            $(this).closest('li').addClass('active');
                        }
                    });
                };*/

    </script>
    <%--<script type="text/javascript" src="<c:url value="../js/my.js"/>"></script>--%>
</head>
<body style="background-color: #4e555b">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark my-menu">
    <div class="container">
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto mynav">
                <li class="nav-item">
                    <form class="my-form-menu" action="/test" method="post">
                        <input type="hidden" name="command" value="GET_COURSES">
                        <input class="nav-link" type="submit" value="<fmt:message key="pass-test-menu"/>">
                    </form>
                </li>
                <li class="nav-item">
                    <form class="my-form-menu" action="/test" method="post">
                        <input type="hidden" name="currentPage" value="1">
                        <input type="hidden" name="command" value="SHOW_RESULTS">
                        <input type="hidden" name="student_id" value="${sessionScope.user.id}">
                        <input class="nav-link" type="submit" value="<fmt:message key="results-menu"/>">
                    </form>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#"><fmt:message key="edit-profile-menu"/></a>
                </li>
                <c:if test="${sessionScope.role eq 'ADMIN'}">
                    <li class="nav-item">
                        <form class="my-form-menu" action="/test" method="post">
                            <input type="hidden" name="command" value="GET_USERS">
                            <input class="nav-link" type="submit" value="<fmt:message key="accounts-menu"/>">
                        </form>
                    </li>
                </c:if>
                <li class="nav-item">
                    <form class="my-form-menu" action="/test" method="post">
                        <input type="hidden" name="command" value="LOGOUT">
                        <input class="nav-link" class="nav-link" type="submit" value="<fmt:message key="log-out"/>">
                    </form>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="nav-item">
                    <a href="javascript:replace_search('lang', 'UA');">UA</a>
                </li>
                <li class="nav-item">
                    <a href="javascript:replace_search('lang', 'RU');">RU</a>
                </li>
                <li class="nav-item">
                    <a href="javascript:replace_search('lang', 'EN');">EN</a>
                </li>
                <li class="nav-item">
                    <span style="color: white;font-size: 10px">[${sessionScope.user.firstName}</span>
                    <span style="color: white;font-size: 10px">${sessionScope.user.lastName}]</span>
                </li>
            </ul>
        </div>
    </div>
</nav>