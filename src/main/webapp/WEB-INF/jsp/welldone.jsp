<%--
  Created by IntelliJ IDEA.
  User: Peter
  Date: 28.05.2016
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>costul</title>
    <link rel="stylesheet" href="../../resources/css/modal2.css"/>
    <link rel="stylesheet" href="resources/css/styleLogIn.css" media="screen" type="text/css" />
    <script src="../../resources/js/jquery.min.js"></script>
    <script src="../../resources/js/alertRegistration.js"></script>
    <script>
        $(document).ready(function () {
        $('#overlay').fadeIn(400,
                function () {
                    $('#modal_form')
                            .css('display', 'block')
                            .animate({opacity: 1, top: '30%'}, 200);
                });
                });
    </script>
</head>
<div id="modal_form">
    <span id="modal_close">X</span>
    <div style="float: inherit; text-align: center"><h2 style="font-family: Verdana; font-size: 14pt; margin-top: 10%; margin-bottom: 5%">Well done! Now go check you email for complete registration.</h2>
        <button style="width: 30%;height: 30%" class="button" onclick="RD()">Got it!</button></div>
</div>
<div id="overlay"></div>
<body>

</body>
</html>
