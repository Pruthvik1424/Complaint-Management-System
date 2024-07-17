<%@ page isELIgnored ="false"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Change Password</title>
     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

      <!--for dropdown--!>
      <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>

  </head>
  <body>

  <nav class="navbar navbar-dark shadow p-3 mb-5 bg-dark rounded">
      <div class="container-fluid">
          <a class="navbar-brand" href="#">
              <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz" width="140" height="70">
          </a>
          <ul class="nav justify-content-end">
              <li class="nav-item">
                  <a class="nav-link active" aria-current="page" href="index.jsp">Home</a>
                  <a class="nav-link active" aria-current="page" href="Profile.jsp">Profile</a>
              </li>
               <div class="dropdown">
                <div class=" dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">

                    <img src="${pageContext.request.contextPath}${sessionScope.profileImage}" alt="Profile" width="80" height="80" class="rounded-circle"/>

                  </div>  </button>
                 <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton1">
                <li><a class="dropdown-item" href="ChangePassword.jsp"><strong>Change password</strong></a></li>
                 <li><a class="dropdown-item" href="viewprofile"><strong>View profile</strong></a></li>
                 <li><a class="dropdown-item" href="edit-profile?email=${user.email}"><strong>Edit profile</strong></a></li>
                  </ul>
                  </div>
          </ul>
      </div>
  </nav>
<form action="changepassword" method="post">
    <div class="container mt-5 mb-5 d-flex justify-content-center">
        <div class="card px-2 mt-5 mb-5 py-2 bg-body shadow mt-5 mb-6 rounded" style="width:40%; padding:30px;">
            <div class="card-body">
                <center><h2>CHANGE PASSWORD</h2></center>
                <center><span style="color:red">${error}</span></center>
                <div class="mb-2">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" placeholder="Email address" name="email">
                    <div id="emailError" style="color:red;"></div>
                </div>
                <div class="mb-3">
                    <label for="oldpassword" class="form-label">Old Password</label>
                    <input type="password" class="form-control" id="oldpassword" placeholder="Old Password" name="oldpassword">
                    <div id="oldpasswordError" style="color:red;"></div>
                </div>
                <div class="mb-3">
                    <label for="newpassword" class="form-label">New Password</label>
                    <input type="password" class="form-control" id="newpassword" placeholder="New Password" name="newpassword">
                    <div id="newpasswordError" style="color:red;"></div>
                </div>
                <div class="mb-3">
                    <label for="confirmpassword" class="form-label">Confirm Password</label>
                    <input type="password" class="form-control" id="confirmpassword" placeholder="Confirm Password" name="confirmpassword">
                    <div id="confirmpasswordError" style="color:red;"></div>
                </div>
                <div class="form-group">
                    <center><button type="submit" id="sendButton" class="btn btn-dark">Change Password</button></center>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>
