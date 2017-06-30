
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
  Created by IntelliJ IDEA.
  User: tianf
  Date: 2016/10/31
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>


</head>
<body>

<table class="table table-striped">
    <%--<caption>条纹表格布局</caption>--%>
    <h1 align="center">知乎情感话题精华</h1>
    <thead>
    <tr>
        <th>id</th>
        <th>url</th>
        <th>question</th>
        <th>upvote</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${recordList}" var="record">
        <tr>
            <td> ${record.id}</td>
            <td> <a href="${record.url}">${record.url}</a></td>
            <td> ${record.question}</td>
            <th>${record.upvote}</th>
        </tr>

    </c:forEach>


    </tbody>
</table>

</body>
</html>
