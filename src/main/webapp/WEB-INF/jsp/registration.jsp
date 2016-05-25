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
        <form action="/createUser" method="post">
            <fieldset class="clearfix">

                <p><span class="fontawesome-user"></span><input  name="userName" type="text" placeholder="Login"  required></p> <!-- JS because of IE support; better: placeholder="Username" -->
                <p><span class="fontawesome-user"></span><input  name="firstName" type="text" placeholder="First  Name"  required></p> <!-- JS because of IE support; better: placeholder="Username" -->


                <p><span class="fontawesome-user"></span><input  name="email" type="text" placeholder="Email"  required></p> <!-- JS because of IE support; better: placeholder="Username" -->
                <p><span class="fontawesome-lock"></span><input name="pass" type="password"  placeholder="Password"  required></p> <!-- JS because of IE support; better: placeholder="Password" -->
                <p><input type="submit" value="Registration"></p>

            </fieldset>
            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}" />

        </form>
        <a href="/"> <span class="fontawesome-arrow-left"> Back</span></a>
    </div>
</body>
</html>