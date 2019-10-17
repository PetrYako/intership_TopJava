<%@ page import="ru.javawebinar.topjava.web.SecurityUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>
<html>
<head>
    <title>Meal list</title>
    <style>
        .normal {
            color: green;
        }

        .excess {
            color: red;
        }

        .disabled {
            pointer-events: none;
        }

        .enabled {
            pointer-events: all;
        }

    </style>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr/>
    <h2>Meals</h2>
    <a href="meals?action=create">Add Meal</a><br>
    <form method="get" action="meals">
        <input type="hidden" name="action" value="filter">
        <p>From Date: <input type="datetime-local" name="fromDate" required>
            To Date: <input type="datetime-local" name="toDate"  required></p><br>
        <button type="submit">Filter</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" type="ru.javawebinar.topjava.to.MealTo"/>
            <tr class="${meal.excess ? 'excess' : 'normal'}">
                <td>
                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                        ${fn:formatDateTime(meal.dateTime)}
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=update&id=${meal.id}" class=<%=SecurityUtil.authUserId() == meal.getUserId() ? "enabled" : "disabled"%>>Update</a></td>
                <td><a href="meals?action=delete&id=${meal.id}" class=<%=SecurityUtil.authUserId() == meal.getUserId() ? "enabled" : "disabled"%>>Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>