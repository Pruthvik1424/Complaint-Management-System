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
</style>


</head>
<body>


<header>
<nav class="navbar navbar-custom shadow p-3 ">
    <div class="container-fluid">
        <a class="navbar-brand " href="#">
            <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" style="bg-color:dark" alt="xworkz" width="140" height="70">
        </a>
        <ul class="nav justify-content-end">

                <ul class="nav justify-content-end">
                             <li class="nav-item">
                                <a class="nav-link " aria-current="page" href="index.jsp">Home</a>
                              </li>
                            <li class="nav-item">
                               <a class="nav-link active" aria-current="page" href="Profile.jsp">Profile</a>
                            </li>

                            <li class="nav-item">
                             <img src="${pageContext.request.contextPath}${sessionScope.profileImage}" alt="Profile" width="80" height="80" class="rounded-circle"/>
                            </li>
                 </ul>
        </ul>
    </div>
</nav>
</header>



      <form action="forgotpassword" method="post">
           <div class="container mt-5 mb-5 d-flex justify-content-center  ">
                 <div class="card px-2 mt-5 mb-5 py-2 bg-body shadow mt-5 mb-6 rounded" style="width:40%; padding:30px;">
                     <div class="card-body ">

                       <center><h2>RESET PASSWORD</h2></center>


                    <div class="card-body px-5">
                        <p class="card-text py-2">
                            Enter your email address and we'll send you an email with another password.
                        </p>

                                    <center>  <span style="color:red">${error}</span> </center>


                        <div class="mb-2">
                              <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
                              <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0"/>
                              <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8m8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1"/>
                             </svg>  <label for="email" class="form-label">Email i'd </label>
                           <input type="email" class="form-control" id="email" placeholder = "Username or Email address"  value="${contactDto.email}" name="email" aria-describedby="emailHelp">

                             <div id="emailError" style="color:red;"></div>
                           </div>

                           <div class="form-group">
                                       <center><button type="submit" id="sendButton" class="btn btn-dark"  }>Reset Password</button></center>
                                     </div>


                    </div>
                </div>


         </div>
        </div>
      </form>
     </body>
  </html>