<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE HTML>
<!--
Striped by HTML5 UP
html5up.net | @n33co
Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
    <title>Blog</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="description" content=""/>
    <meta name="keywords" content=""/>
    <!--[if lte IE 8]>
    <script src="../../css/ie/html5shiv.js"></script><![endif]-->
    <script src="../../resources/js/jquery.min.js"></script>
    <script src="../../resources/js/skel.min.js"></script>
    <script src="../../resources/js/skel-layers.min.js"></script>
    <script src="../../resources/js/init.js"></script>
    <script src="../../resources/js/main.js"></script>
    <script src="../../resources/js/chart.js"></script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

    <link rel="stylesheet" href="../../resources/css/skel.css"/>
    <link rel="stylesheet" href="../../resources/css/style.css"/>
    <link rel="stylesheet" href="../../resources/css/style-desktop.css"/>
    <link rel="stylesheet" href="../../resources/css/style-wide.css"/>
    <link rel="stylesheet" href="../../resources/css/modal.css"/>

    <!--[if lte IE 8]>
    <link rel="stylesheet" href="resources/css/ie/v8.css"/><![endif]-->
    <script>
$(document).ready(function () {
    loading();
})
    </script>
</head>
<!--
    Note: Set the body element's class to "left-sidebar" to position the sidebar on the left.
    Set it to "right-sidebar" to, you guessed it, position it on the right.
-->
<body class="left-sidebar">

<!-- Wrapper -->
<div id="wrapper">
    <c:set var="ac" scope="session" value="${access}"/>

    <!-- Content -->
    <div id="content">
        <div class="inner">
            <script language="javascript" type="text/javascript"
                    src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
            <h2 style="font-size: 30pt">Users attendance</h2>
            <hr>
            <div id="top_x_div" style="width: 900px; height: 500px;"></div>
            <a id="go" class="button">Reset stat!</a>
            <input type="hidden" id="token" name="${_csrf.parameterName}"

                   value="${_csrf.token}"/>
        </div>
    </div>


    <!-- Sidebar -->
    <div id="sidebar">

        <!-- Logo -->

        <h1 id="logo"><a href="#">Blog<c:if test="${ac == true}">${user.firstName }</c:if> </a></h1>

        <!-- Nav -->
        <nav id="nav">
            <ul>
                <li class="current"><a href="/">Post</a></li>

                <sec:authorize access="hasRole('ROLE_ANONYMOUS')" >

                    <li><a href="registration">Registration</a></li>
                    <li><a href="login">Log In</a></li>
                </sec:authorize>
                <li><a href="gallery">Gallery</a></li>


                <sec:authorize access="hasRole('ROLE_USER')" >


                    <li><a href="user/Update?id=0">Add Post</a></li>
                    <li><a href="logout">Log Out</a></li>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_ADMIN')" >
                    <li><a href="admin/userList">Ban List</a></li>
                    <li><a href="admin/stat">Stats</a></li>
                    <li><a href="admin/actions">Actions</a></li>
                    <li><a href="Update?id=0">Add Post</a></li>
                    <li><a href="logout">Log Out</a></li>

                </sec:authorize>
            </ul>
        </nav>

        <!-- Search -->
        <section class="box search">
            <form method="post" action="#">
                <input type="text" class="text" name="search" placeholder="Search"/>
            </form>
        </section>

        <!-- Text -->
        <section class="box text-style1">
            <div class="inner">
                <p>
                    <strong>Striped:</strong> A free and fully responsive HTML5 site
                    template designed by <a href="http://n33.co/">AJ</a> for <a href="http://html5up.net/">HTML5 UP</a>
                </p>
            </div>
        </section>

        <!-- Recent Posts -->
        <section class="box recent-posts">
            <header>
                <h2>Recent Posts</h2>
            </header>
            <ul>
                <li><a href="#">Lorem ipsum dolor</a></li>
                <li><a href="#">Feugiat nisl aliquam</a></li>
                <li><a href="#">Sed dolore magna</a></li>
                <li><a href="#">Malesuada commodo</a></li>
                <li><a href="#">Ipsum metus nullam</a></li>
            </ul>
        </section>

        <!-- Recent Comments -->
        <section class="box recent-comments">
            <header>
                <h2>Recent Comments</h2>
            </header>
            <ul>
                <li>case on <a href="#">Lorem ipsum dolor</a></li>
                <li>molly on <a href="#">Sed dolore magna</a></li>
                <li>case on <a href="#">Sed dolore magna</a></li>
            </ul>
        </section>

        <!-- Calendar -->
        <section class="box calendar">
            <div class="inner">
                <table>
                    <caption>July 2014</caption>
                    <thead>
                    <tr>
                        <th scope="col" title="Monday">M</th>
                        <th scope="col" title="Tuesday">T</th>
                        <th scope="col" title="Wednesday">W</th>
                        <th scope="col" title="Thursday">T</th>
                        <th scope="col" title="Friday">F</th>
                        <th scope="col" title="Saturday">S</th>
                        <th scope="col" title="Sunday">S</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td colspan="4" class="pad"><span>&nbsp;</span></td>
                        <td><span>1</span></td>
                        <td><span>2</span></td>
                        <td><span>3</span></td>
                    </tr>
                    <tr>
                        <td><span>4</span></td>
                        <td><span>5</span></td>
                        <td><a href="#">6</a></td>
                        <td><span>7</span></td>
                        <td><span>8</span></td>
                        <td><span>9</span></td>
                        <td><a href="#">10</a></td>
                    </tr>
                    <tr>
                        <td><span>11</span></td>
                        <td><span>12</span></td>
                        <td><span>13</span></td>
                        <td class="today"><a href="#">14</a></td>
                        <td><span>15</span></td>
                        <td><span>16</span></td>
                        <td><span>17</span></td>
                    </tr>
                    <tr>
                        <td><span>18</span></td>
                        <td><span>19</span></td>
                        <td><span>20</span></td>
                        <td><span>21</span></td>
                        <td><span>22</span></td>
                        <td><a href="#">23</a></td>
                        <td><span>24</span></td>
                    </tr>
                    <tr>

                        <td><a href="#">25</a></td>
                        <td><span>26</span></td>
                        <td><span>27</span></td>
                        <td><span>28</span></td>
                        <td class="pad" colspan="3"><span>&nbsp;</span></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </section>

        <!-- Copyright -->
        <ul id="copyright">
            <li>&copy; Untitled.</li>
            <li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
        </ul>

    </div>

</div>
<div id="modal_form">
    <span id="modal_close">X</span>
    <div style="float: inherit; text-align: center"><h2 style="font-size: 20pt; margin-top: 10%; margin-bottom: 35%">Are you sure?</h2>
        <button style="width: 50%;height: 5%" class="button" onclick="Reset()">Yes</button></div>
</div>
<div id="overlay"></div>
</body>
</html>



