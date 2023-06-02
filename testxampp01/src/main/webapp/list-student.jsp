<%--
  Created by IntelliJ IDEA.
  User: pnggo
  Date: 6/2/2023
  Time: 4:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Tracker app</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <%-- <link> --%>
</head>
<body>
<div id="wrapper">
    <header id="header">
        <h1>Code academy</h1>
    </header>
</div>
<div id="container">
    <div id="content">
        <input type="button" value="Add Student" onclick="window.location.href='add-student-form.jsp'; return false;" class="add-student-button"/>
        <table>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Action</th>
            </tr>
            <c:forEach var="tempStudent" items="${STUDENT_LIST}">
                <!--Set up a link for each student-->
                <c:url var="tempLink" value="StudentControllerServlet">
                    <c:param name="command" value="LOAD"/>
                    <c:param name="studentId" value="${tempStudent.id}"/>
                </c:url>
                <!--Set up a link to delete a student-->
                <c:url var="deleteLink" value="StudentControllerServlet">
                    <c:param name="command" value="DELETE"/>
                    <c:param name="studentId" value="${tempStudent.id}"/>
                </c:url>
                <tr>
                    <td>${tempStudent.firstName}</td>
                    <td>${tempStudent.lastName}</td>
                    <td>${tempStudent.email}</td>
                    <td>
                        <a href="${tempLink}" class="update-link">Update</a>
                        <a href="deleteLink" class="delete-link" onclick="if (!(confirm('Are you sure you want to delete this student ?'))) return false">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
