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
                                <a class="nav-link " style="color : white " aria-current="page" href="index.jsp">Home</a>
                              </li>
                        </ul>
        </ul>
    </div>
</nav>
</header>

      <form action="departmentadminsignin" method="post">
           <div class="container mt-5 mb-5 d-flex justify-content-center  ">
                 <div class="card px-2 mt-5 mb-5 py-2 bg-body shadow mt-5 mb-6 rounded" style="width:40%; padding:30px;">
                     <div class="card-body ">


                          <br>
                          <center><h2>DEPARTMENT ADMIN SIGNIN</h2></center>


            <center>  <span style="color:red">${error}</span> </center>
            <div class="mb-2">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
             <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0"/>
              <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8m8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1"/>
               </svg>  <label for="email" class="form-label">Username</label>
                  <input type="email" class="form-control" id="email" placeholder = "Username or Email address"  value="${contactDto.email}" name="email" aria-describedby="emailHelp">

                  <div id="emailError" style="color:red;"></div>
                </div>

                <div class="mb-3">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-key-fill" viewBox="0 0 16 16">
                <path d="M3.5 11.5a3.5 3.5 0 1 1 3.163-5H14L15.5 8 14 9.5l-1-1-1 1-1-1-1 1-1-1-1 1H6.663a3.5 3.5 0 0 1-3.163 2M2.5 9a1 1 0 1 0 0-2 1 1 0 0 0 0 2"/>
                </svg>
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" placeholder = ""  name="password" aria-describedby="passwordHelp" value="${contactDto.password}">

                  <div id="passwordError" style="color:red;"></div>
                    </div>




          <div class="form-group">
            <center><button type="submit" id="sendButton" class="btn btn-dark"  ${disableButton ? 'disabled' : ''}>Sign in</button></center>
          </div>

          <div class="form-group">
                    <p class="text-center text-muted mt-3 mb-0">Forgot password? <a href="ForgotPassword.jsp"
                                             class="fw-bold text-body"><u>Reset password here</u></a></p>
                    </div>

         </div>
        </div>
      </form>
     </body>
  </html>