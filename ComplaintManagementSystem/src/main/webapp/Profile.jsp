<%@ page isELIgnored ="false"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

<!--for dropdown--!>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>




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
            <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="index.jsp">Home</a>
            </li>


                     <div class="dropdown">
                            <div class=" dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">

                                <!--<img src="${pageContext.request.contextPath}/images/profileicon.jpg" alt="Profile" width="30" height="30" class="rounded-circle"> <!-- Add your profile icon path -->
                                <img src="https://static.vecteezy.com/system/resources/previews/005/544/718/non_2x/profile-icon-design-free-vector.jpg" alt="Profile" width="40" height="40" class="rounded-circle"> <!-- Add your profile icon path -->
                                <img th:src="${profile.profileImageUrl}" alt="Profile" width="40" height="40" class="rounded-circle">


                                       </div>  </button>
                             <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton1">
                               <li><a class="dropdown-item" href="ChangePassword.jsp"><strong>Change password</strong></a></li>
                               <li><a class="dropdown-item" href="viewprofile"><strong>View profile</strong></a></li>
                               <li><a class="dropdown-item" href="edit-profile?email=${user.email}"><strong>Edit profile</strong></a></li>
                             </ul>
                           </div>


                              </div>
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


</body>
</html>
