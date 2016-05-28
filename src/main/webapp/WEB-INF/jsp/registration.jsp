<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Blog</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="description" content=""/>
    <meta name="keywords" content=""/>

	<link rel="stylesheet" href="resources/css/styleLogIn.css" media="screen" type="text/css" />
    <link rel="stylesheet" href="../../resources/css/modal2.css"/>



    <link rel="stylesheet" href="resources/css/ie/v8.css"/><![endif]-->


    <script src="../../resources/js/jquery.min.js"></script>
    <%--<script src="../../resources/js/alertRegistration.js"></script>--%>
    <script>$(document).ready(function () {


        $('#go').click(function () {
           window.close();
        });
        });
    </script>

</head>
<body>



    <div id="login">
        <form action="/createUser?${_csrf.parameterName}=${_csrf.token}" method="post" >
            <fieldset class="clearfix">

                <p><span class="fontawesome-user"></span><input  name="userName" type="text" placeholder="Login"  required></p> <!-- JS because of IE support; better: placeholder="Username" -->
                <p><span class="fontawesome-user"></span><input  name="firstName" type="text" placeholder="First  Name"  required></p> <!-- JS because of IE support; better: placeholder="Username" -->


                <p><span class="fontawesome-user"></span><input  name="email" type="text" placeholder="Email"  required></p> <!-- JS because of IE support; better: placeholder="Username" -->
                <p><span class="fontawesome-lock"></span><input name="pass" type="password"  placeholder="Password" minlength="6" required></p> <!-- JS because of IE support; better: placeholder="Password" -->

                <p><input id="go" type="submit" value="Register"></p>
            </fieldset>
            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}" />

        </form>


        <a href="/"> <span class="fontawesome-arrow-left"> Back</span></a>
        <%--<iframe name="frame_ajax" src="user/createUser?${_csrf.parameterName}=${_csrf.token}" width="0" height="0" style="display:none">--%>
        <%--</iframe>--%>
    </div>

</body>
</html>