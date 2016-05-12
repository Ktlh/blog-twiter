<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>

	<link rel="stylesheet" href="resources/css/styleLogIn.css" media="screen" type="text/css" />

</head>
<body>



    <div id="login">
        <form action="/user" method="post">
            <fieldset class="clearfix">
                <c:if test="${status == false}">
                    <p>False login or pass  </p>
                    </c:if>
                <p><span class="fontawesome-user"></span><input placeholder="email" name="email" type="text"  required></p> <!-- JS because of IE support; better: placeholder="Username" -->
                <p><span class="fontawesome-lock"></span><input  placeholder="pass" name="pass" type="password"   required></p> <!-- JS because of IE support; better: placeholder="Password" -->
                <p><input type="submit" value="Log in"></p>
            </fieldset>
        </form>
        <a href="/"> BAck</a>

        <p> &nbsp;&nbsp;<a href="registration">Registration</a><span class="fontawesome-arrow-right"></span></p>
    </div>
</body>
</html>