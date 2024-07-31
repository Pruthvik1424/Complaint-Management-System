<%@ page isELIgnored ="false"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Profile</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">


    <style>

        .navbar-custom {
            background: linear-gradient(90deg,#212529, #000); /* Example gradient */
        }
        .modal-content{
        background: linear-gradient(45deg,#495057, #495057); /* Example gradient */
        }

        #wrapper {
            padding-left: 0;
            -webkit-transition: all 0.5s ease;
            -moz-transition: all 0.5s ease;
            -o-transition: all 0.5s ease;
            transition: all 0.5s ease;
            overflow: hidden;
        }

        #wrapper.toggled {
            padding-left: 250px;
            overflow: hidden;
        }

        #sidebar-wrapper {
            z-index: 1000;
            position: fixed;
            left: 0;
            width: 250px;
            height: 100%;
            background: #000;
            -webkit-transition: all 0.5s ease;
            -moz-transition: all 0.5s ease;
            -o-transition: all 0.5s ease;
            transition: all 0.5s ease;
        }

        #page-content-wrapper {
            position: relative;
            padding: 15px;
            width: 100%;
            overflow-x: hidden;
        }

        .sidebar-nav {
            position: absolute;
            top: 0;
            width: 250px;
            margin: 0;
            padding: 0;
            list-style: none;
        }

        .sidebar-nav li {
            text-indent: 15px;
            line-height: 40px;
        }

        .sidebar-nav li a {
            display: block;
            text-decoration: none;
            color: #999999;
        }

        .sidebar-nav li a:hover {
            text-decoration: none;
            color: #fff;
            background: rgba(255, 255, 255, 0.2);
            border-left: red 2px solid;
        }

        .sidebar-nav > .sidebar-brand {
            height: 65px;
            font-size: 18px;
            line-height: 60px;
        }

        .sidebar-nav > .sidebar-brand a {
            color: #999999;
        }

        .sidebar-nav > .sidebar-brand a:hover {
            color: #fff;
            background: none;
        }

        @media (min-width: 768px) {
            #wrapper {
                padding-left: 250px;
            }

            #wrapper.toggled {
                padding-left: 0;
            }

            #page-content-wrapper {
                padding: 20px;
                position: relative;
                -webkit-transition: all 0.5s ease;
                -moz-transition: all 0.5s ease;
                -o-transition: all 0.5s ease;
                transition: all 0.5s ease;
            }

            #wrapper.toggled #page-content-wrapper {
                position: relative;
                margin-right: 0;
                padding-left: 250px;
            }
        }

        .navbar {
            margin-bottom: 0;
        }

        .container-fluid {
            padding-left: 0;
            padding-right: 0;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-custom shadow p-3 ">
    <div class="container-fluid">
        <a class="navbar-brand " href="#">
            <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" style="bg-color:dark" alt="xworkz" width="140" height="70">
        </a>
        <ul class="nav justify-content-end">
            <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="index.jsp">Home</a>
            </li>
            <div class="dropdown">
                <div class="dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                    <img src="${pageContext.request.contextPath}${sessionScope.profileImage}" alt="Profile" width="80" height="80" class="rounded-circle"/>
                </div>
                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton1">
                    <li><a class="dropdown-item" href="ChangePassword.jsp"><strong>Change password</strong></a></li>
                    <li><a class="dropdown-item" data-bs-toggle="modal" data-bs-target="#ChangePassword"><strong>Change password modal</strong></a></li>
                    <li><a class="dropdown-item" data-bs-toggle="modal" data-bs-target="#ViewModal"><strong>View profile</strong></a></li>
                    <li><a class="dropdown-item" href="edit-profile?email=${email}"><strong>Edit profile</strong></a></li>
                </ul>
            </div>
        </ul>
    </div>
</nav>

<div id="wrapper">
    <div id="sidebar-wrapper">
        <ul class="sidebar-nav nav-pills nav-stacked" id="menu">
            <li class="active">
                <a href="#dashboardSubmenu" data-bs-toggle="collapse" aria-expanded="false" class="dropdown-toggle">
                    <span class="fa-stack fa-lg pull-left"><i class="fa fa-dashboard fa-stack-1x"></i></span> Dashboard
                </a>
                <ul class="collapse list-unstyled" id="dashboardSubmenu">
                    <li><a href="edit-profile?email=${email}">Edit Profile</a></li>
                    <li><a class="daashboard-item" data-bs-toggle="modal" data-bs-target="#ViewModal">View Profile</a></li>
                </ul>
            </li>

            <li><a class="dropdown-item" href="view-user-details"><strong>View-User Details</strong></a></li>

            <li><a class="dropdown-item" href="View-raise-complaint"><strong>View-Complaint Details</strong></a></li>

           <li><a class="dropdown-item" href="AddDepartmentAdmin.jsp"><strong>Add Department Admin</strong></a></li>

            <li><a class="dropdown-item" href="AddDepartments.jsp"><strong>Add Departments</strong></a></li>

        </ul>
    </div>
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <strong style="color:blue">${AdminProfilePageMessage}</strong>
        </div>
    </div>
</div>




<!-- <script>

$("#menu-toggle").click(function(e) {
    e.preventDefault();
    $("#wrapper").toggleClass("toggled");
});
$("#menu-toggle-2").click(function(e) {
    e.preventDefault();
    $("#wrapper").toggleClass("toggled-2");
    $('#menu ul').hide();
});

function initMenu() {
    $('#menu ul').hide();
    $('#menu ul').children('.current').parent().show();
    $('#menu li a').click(function() {
        var checkElement = $(this).next();
        if ((checkElement.is('ul')) && (checkElement.is(':visible'))) {
            return false;
        }
        if ((checkElement.is('ul')) && (!checkElement.is(':visible'))) {
            $('#menu ul:visible').slideUp('normal');
            checkElement.slideDown('normal');
            return false;
        }
    });
}

$(document).ready(function() {
    initMenu();
});

</script>  --!>



<!--End of this if for country city state api --->

<!-- Scripts -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha384-KyZXEAg3QhqLMpG8r+5RAxJ2Lq2M6K90l9K4uH/Og7Y8lTcElY+8ew0MXj0KcN+m" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.10.2/umd/popper.min.js" integrity="sha512-nkGz6ZD3GmL9JzNTCwWBg0oMNJfOZr0bJX8DJw75o8/ooEGQF3wFtQukj/7G4QLFnPRAA5wX8cLxZc8bZq5Hcw==" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWI2wqZpNtZKaRP6Dehc3uIjzunE2O2aHQjFFxIBB7P0TLFj2e0pH/Z1fw" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.2/js/bootstrap.bundle.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3fH0YdKFffV7Ck+nJw2K8fj" crossorigin="anonymous"></script>

<!-- Bootstrap CSS -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">

<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!-- Bootstrap JS -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>


</body>
</html>
