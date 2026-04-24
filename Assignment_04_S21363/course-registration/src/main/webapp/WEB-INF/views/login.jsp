<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>

<h2>Login</h2>

<form method="post" action="${pageContext.request.contextPath}/login">

    Email:
    <input type="text" name="email" required />
    <br><br>

    Password:
    <input type="password" name="password" required />
    <br><br>

    <button type="submit">Login</button>

</form>

<br>

<p style="color:red;">
    ${error}
</p>

</body>
</html>