<%--
  Created by IntelliJ IDEA.
  User: pnggo
  Date: 6/2/2023
  Time: 4:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Academy</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <link type="text/css" rel="stylesheet" href="css/add-student-style.css">

</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2>Code Academy</h2>
    </div>
</div>
<div id="container">
    <h3>Update Student</h3>
    <form action="StudentControllerServlet" method="get">
        <input type="hidden" name="command" value="UPDATE">
        <input type="hidden" name="studentId" value="${THE_STUDENT.id}"/>

        <table>
            <tbody>
            <tr>
                <td><label>first name</label></td>
                <td><input type="hidden" name="firstName"
                value="${THE_STUDENT.firstName}"></td>
            </tr>
            <tr>
                <td><label>last name</label></td>
                <td><input type="hidden" name="lastName"
                           value="${THE_STUDENT.lastName}"></td>
            </tr>
            <tr>
                <td><label>Email</label></td>
                <td><input type="hidden" name="email"
                           value="${THE_STUDENT.email}"></td>
            </tr>
            <tr>
                <td><label></label></td>
                <td><input type="submit" class="save"
                           value=Save"></td>
            </tr>
            </tbody>
        </table>
    </form>
    <div style="clear: both"></div>
    <p>
        <a href="StudentControllerServlet"></a>
    </p>
</div>
</body>
</html>
