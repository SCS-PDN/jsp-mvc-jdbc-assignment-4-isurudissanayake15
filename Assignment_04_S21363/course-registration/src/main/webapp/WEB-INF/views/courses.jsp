<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Courses</title>
</head>
<body>

<h2>Available Courses</h2>

<c:forEach var="course" items="${courses}">
    <div style="margin-bottom: 10px;">
        <p>
            ${course.name} - ${course.instructor}
        </p>

        <form method="post" action="register/${course.course_id}">
            <button type="submit">Register</button>
        </form>
    </div>
</c:forEach>

</body>
</html>