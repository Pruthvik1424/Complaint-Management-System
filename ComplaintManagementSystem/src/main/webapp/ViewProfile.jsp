<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Profile View</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<header>
<nav class="navbar navbar-dark shadow p-3 mb-5 rounded bg-dark">
    <div class="container-fluid">
        <!-- Add your logo here -->
        <a class="navbar-brand" href="#">
            <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz" width="140" height="70">
        </a>
        <!-- End of logo -->
        <span style="color: white; font-style: italic;">ComplaintManagementSystem</span>
        <ul class="nav justify-content-end">
            <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="Profile.jsp">Profile</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="index.jsp">Index</a>
            </li>
            <li>
            <img src="${pageContext.request.contextPath}${sessionScope.profileImage}" alt="Profile" width="80" height="80" class="rounded-circle"/>
            </li>

        </ul>
    </div>

</nav>
</header>

    <div class="container mt-5">
        <h2>User Profile</h2>
        <div class="card">
            <div class="card-body">
                <strong class="card-title">Name: ${signUpDto.fname} ${signUpDto.lname}</strong><br><br>

            </div>
        </div>
    </div>
</body>
</html>