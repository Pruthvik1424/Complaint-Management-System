<%@ page isELIgnored ="false"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Complaint Management System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>

<nav class="navbar  navbar-dark shadow p-3 mb-5 rounded  bg-dark">
  <!-- Navbar content -->
   <div class="container-fluid  ">
           <!-- Add your logo here -->
                                   <a class="navbar-brand " href="#">
                                       <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz" width="140" height="70"  >
                                   </a>
                                   <!-- End of logo -->
      <ul class="nav justify-content-end">
      <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="SignUp.jsp">Sign Up</a>
              </li>
      <li class="nav-item">
           <a class="nav-link active" aria-current="page" href="SignIn.jsp">Sign In</a>
           </li>
       </ul>
     </div>
</nav>

<form id="index" action="index" method="post" onsubmit="return validateForm()">
<div class="container mt-5 mb-200">
<div class="card mt-5 mb-200">

<center><h2> Welcome To my application </h2></center>

</div>
</div>
</form>
</body>
</html>