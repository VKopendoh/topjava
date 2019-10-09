<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://javawebinar.ru/topjava/functions" prefix="f" %>

<html>

<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<br>
<table border="1" cellpadding="2" cellspacing="0">
    <tr>
        <th>Описание</th>
        <th>Калорий</th>
        <th>Время</th>
        <th>Действие</th>
    </tr>
    <c:forEach var="mealTo" items="${mealsWithExceed}">
        <c:set var="exceed" value="${mealTo.excess ? 'red' : 'green'}"/>
        <tr class="${exceed}">
            <td> ${mealTo.description} </td>
            <td> ${mealTo.calories} </td>
            <td> ${f:formatLocalDateTime(mealTo.dateTime)} </td>
            <td><a href="meals?action=delete&id=${mealTo.id}"> Удалить </a> |
                <a href="meals?action=update&id=${mealTo.id}"> Редактировать </a></td>
        </tr>
    </c:forEach>
</table>
<br><br>
<form method="post" action="meals" enctype="application/x-www-form-urlencoded">
    Описание: <input type="text"
                     name="description"
                     value="${not empty meal.description ? meal.description:'Бранч'}"/> <br><br>
    Количество калорий: <input type="number"
                               name="calories"
                               required="required"
                               value="${not empty meal.calories ? meal.calories:200}"/><br><br>
    Дата и время: <input type="datetime-local"
                         name="dateTime"
                         value="${not empty meal.dateTime ? meal.dateTime:'2015-10-09T21:44'}"/><br><br>
    <input type="hidden" name="id" value="${meal.id}">
    <input type="submit" value="Сохранить">
</form>
</body>
</html>
