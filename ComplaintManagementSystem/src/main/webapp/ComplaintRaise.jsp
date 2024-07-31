<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script  src="/ComplaintManagementSystem/js/ComplaintRaise.js"></script>

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
                            <a class="nav-link active" aria-current="page" href="Profile.jsp">Home</a>
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

            <li><a class="dropdown-item" data-bs-toggle="modal" href="ComplaintRaise.jsp"><strong>Complaint - raise </strong></a></li>

            <li><a class="dropdown-item" href="view-complaint"><strong>Complaint - View </strong></a></li>

        </ul>
    </div>



  <form action="raise-complaint" method="post">

      <div class="d-flex justify-content-center mt-3 mb-3 align-items-center vh-5">
          <div class="card" style="width: 30rem;">
              <div class="card-body">
                  <center><h5 class="card-title">Raise Complaint Form</h5></center>
                  <hr>
                  <strong style="color:green"/>${user}</strong>
                  <strong style="color:green"/>${raiseComplaintSucess}</strong>
                  <strong style="color:red"/>${ErrorRaiseComplaintSucess}</strong>

                   <div class="row mb-3">
                         <span id="countryNameError"></span>
                         <label for="countryName" class="form-label">Complaint Type:</label>
                         <select class="form-select custom-select-width" id="complaintType" name="complaintType"  placeholder="Complaint Type">
                               <option ${dto.complaintType==null ? 'selected' : ''}  selected value=" "></option>
                               <option value="Water Supply" ${dto.complaintType eq 'Water Supply' ? 'selected' : ''}>Water Supply</option>
                               <option value="System Problem"  ${dto.complaintType eq 'System Problem' ? 'selected' : ''}>System Problem</option>
                               <option value="Network Problem"  ${dto.complaintType eq 'Network Problem' ? 'selected' : ''}>Network Problem</option>
                               <option value="Electrical Problem"  ${dto.complaintType eq 'Electrical Problem' ? 'selected' : ''}>Electrical Problem</option>
                               <option value="Noise Problem"  ${dto.complaintType eq 'GMIT' ? 'selected' : ''}>Noise Problem</option>
                           </select>
                           <span id="collegeNameError"></span>
                       </div>

                       <!----Country ---!>

                                       <div class="row mb-3">
                                           <span id="countryNameError"></span>
                                           <label for="countryName" class="form-label">Country:</label>
                                           <select class="form-select custom-select-width" id="countryName" name="country" onchange="loadStates(this)"  placeholder="Enter country">
                                               <!-- Countries will be loaded here by JavaScript -->
                                           </select><br>
                                       </div>

                                       <!----State ---!>

                                       <div class="row mb-3">
                                           <span id="stateNameError"></span>
                                           <label for="state" class="form-label">State:</label>
                                           <select class="form-select custom-select-width" id="state" name="state" onchange="loadCities(this,countryName)"  placeholder="Enter State" >
                                               <!-- States will be loaded here by JavaScript -->
                                           </select><br>
                                       </div>

                                       <!----City ---!>

                                       <div class="row mb-3">
                                           <span id="cityNameError"></span>
                                           <label for="city" class="form-label">City:</label>
                                           <select class="form-select custom-select-width" id="city" name="city" placeholder="Enter city">
                                               <!-- Cities will be loaded here by JavaScript -->
                                           </select><br>
                                       </div>


                       <div class="row mb-3">
                                 <span id="areaError"></span><br>
                                 <label for="area" class="form-label">Area:</label>
                                 <input type="text" class="form-control" id="area" name="area" placeholder="Enter area">
                        </div>



                       <div class="mb-3">
                                <span id="errorAddress"></span><br>
                                <label for="area" class="form-label">Address:</label>
                                <label for="address" class="form-floating"></label>
                                <textarea class="form-control" placeholder="Enter address" id="address" style="height: 80px" name="address">${jobFormDTO.address}</textarea>
                       </div>



                       <div class="mb-3">
                                       <span id="descriptionError" class="text-danger"></span>
                                     <label for="area" class="form-label">Description:</label>
                                       <div class="form-floating">
                                           <textarea class="form-control" placeholder="Leave a comment here" name="description" id="description"  style="height:80px"  oninput="updateDescriptionCount()" maxlength="300" onblur="validateDescription()">${complaint.description}</textarea>
                                           <label for="description">Description</label>
                                       </div>
                                   </div>


                                       <div class="form-group p-3">
                                              <input type="checkbox" class="form-check-input" id="agree"  name="agree" onchange="agreeValidation()">
                                                           <label class="form-check-label" for="agree">Agree</label>
                                                           <span id="agreeError"></span>
                                                       </div>



                  <div class="form-group p-3">
                      <center><input type="submit" class="btn btn-dark" id="submit" name="submit" value="Raise Complaint" ></center>
                  </div>



              </div>
          </div>
      </div>
  </form>


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