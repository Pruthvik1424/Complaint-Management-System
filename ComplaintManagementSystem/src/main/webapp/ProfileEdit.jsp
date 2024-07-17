<%@ page isELIgnored ="false"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <!--for dropdown--!>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>

    <style>

    </style>

</head>
<body>

<nav class="navbar navbar-dark shadow p-3 mb-5 bg-dark rounded">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">
            <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz" width="140" height="70">
        </a>
        <ul class="nav justify-content-end">
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="Profile.jsp">Profile</a>
            </li>
             <div class="dropdown">
              <div class=" dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
             <img src="${pageContext.request.contextPath}${sessionScope.profileImage}" alt="Profile" width="80" height="80" class="rounded-circle"/>
                </div>  </button>
               <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton1">
              <li><a class="dropdown-item" href="ChangePassword.jsp"><strong>Change password</strong></a></li>
               <li><a class="dropdown-item" href="viewprofile"><strong>View profile</strong></a></li>
               <li><a class="dropdown-item" href="edit-profile?email=${email}"><strong>Edit profile</strong></a></li>
                </ul>
                </div>
        </ul>
    </div>
</nav>

<form action="edit-profile" method="post"  enctype="multipart/form-data">
    <input type="hidden" name="email" value="${signupdto.email}">
    <div class="container mt-5 mb-5 d-flex justify-content-center">
        <div class="card px-2 mt-5 mb-5 py-2 bg-body shadow mt-5 mb-6 rounded" style="width:40%; padding:30px;">
            <div class="card-body">
                <center><h2>EDIT PROFILE</h2></center>

                <!-- Image upload -->
                  <div class="mb-3">
                      <label for="file" class="form-label">Profile Image:</label>
                      <input type="file" class="form-control" id="file" name="multipartFile">
                  </div>


                <!--Avatar
                <div>
                    <div class="d-flex justify-content-center mb-4">
                        <img id="selectedAvatar" src="https://mdbootstrap.com/img/Photos/Others/placeholder-avatar.jpg"
                        class="rounded-circle" style="width: 100px; height: 100px; object-fit: cover;" alt="example placeholder" />
                    </div>
                    <div class="d-flex justify-content-center">
                        <div data-mdb-ripple-init class="btn btn-dark btn-rounded">
                            <label class="form-label text-white m-1" for="customFile2">Choose picture</label>
                            <input type="file" class="form-control d-none" id="customFile2" onchange="displaySelectedImage(event, 'selectedAvatar')" accept="image/*" />
                        </div>
                    </div>
                </div>  -->

                <span style="color:red;">
                    <c:forEach items="${errors}" var="objectError">
                        ${objectError.defaultMessage}</br>
                    </c:forEach>
                </span>
                 <strong style="color:green;">${msg}</strong><br>
                <strong style="color:green;">${profileMessage}</strong><br>
                <strong style="color:red;">${msg1}</strong><br>
                <strong style="color:red;">${msg1}</strong><br>
                <strong style="color:red;">${error}</strong><br>

                <div class="mb-3">
                    <label for="fname" class="form-label">First Name:</label>
                    <input type="text" class="form-control" id="fname" name="fname" value="${signUpDto.fname}">
                </div>

                <div class="mb-2">
                    <label for="lname" class="form-label">Last Name:</label>
                    <input type="text" class="form-control" id="lname" name="lname" value="${signUpDto.lname}">
                </div>

                <div class="mb-2">
                                    <label for="email" class="form-label">Email:</label>
                                    <input type="email" class="form-control" id="email" name="email" value="${signUpDto.email}" readonly disabled>
                                </div>



                          <!--      ${signUpDto.email}  -->

                <div class="mb-2">
                    <label for="mobilenumber" class="form-label">Mobile Number:</label>
                    <input type="tel" class="form-control" id="mobilenumber" name="mobilenumber" value="${signUpDto.mobilenumber}">
                </div>

                <div class="mb-2">
                    <label for="alternatemobilenumber" class="form-label">Alternate Number:</label>
                    <input type="tel" class="form-control" id="alternatemobilenumber" name="alternatemobilenumber" value="${signUpDto.alternatemobilenumber}">
                </div>

                <div class="mb-2">
                    <label for="address" class="form-label">Address:</label>
                    <textarea class="form-control" id="address" name="address" style="height: 100px">${signUpDto.address}</textarea>
                </div>

                <div class="form-group">
                    <center><button type="submit" class="btn btn-dark">Edit</button></center>
                </div>
            </div>
        </div>
    </div>
</form>


</body>
</html>
