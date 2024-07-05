<%@ page isELIgnored ="false"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>

<nav class="navbar navbar-dark shadow p-3 mb-5 bg-dark rounded ">
    <div class="container-fluid">
        <!-- Add your logo here -->
        <a class="navbar-brand" href="#">
            <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz" width="140" height="70">
        </a>
        <!-- End of logo -->
        <ul class="nav justify-content-end">
            <li class="nav-item" >
                <a class="nav-link active"  aria-current="page" href="index.jsp">Home</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                    <img src="path/to/profile-pic.jpg" alt="Profile Picture" width="30" height="30" class="rounded-circle"> <!-- Replace with actual path to profile pic -->
                </a>
                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                    <li><a class="dropdown-item" href="#">Profile</a></li>
                    <li><a class="dropdown-item" href="#">Settings</a></li>
                    <li><hr class="dropdown-divider"></li>
                    <li><a class="dropdown-item" href="#">Logout</a></li>
                </ul>
            </li>
        </ul>
    </div>
</nav>

<form action="success" method="post">
    <div class="container mt-5 mb-5 d-flex justify-content-center">
        <div class="card px-2 mt-5 mb-5 py-2 bg-body shadow mt-5 mb-6 rounded" style="width:40%; padding:30px;">
            <div class="card-body">
                <strong style="color:blue">${message}</strong>
            </div>
        </div>
    </div>
</form>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXlHjGx+e+jjMfdxI+pgmI1wxsbJ9OdPpvhyy+Rx1PhEpaZb9FaCuM7lJaG4" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhG1KpG9EG7gDh3k8so7Y3z7Sk3ZOr53y5fAFH1zY8cfHE++IoP5mZQFVrB7" crossorigin="anonymous"></script>

</body>
</html>
