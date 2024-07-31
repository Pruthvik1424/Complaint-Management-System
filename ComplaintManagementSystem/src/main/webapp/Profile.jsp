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


            <li><a class="dropdown-item" href="ComplaintRaise.jsp"><strong>Complaint - raise </strong></a></li>

            <li><a class="dropdown-item" href="view-complaint"><strong>Complaint - View </strong></a></li>


        </ul>
    </div>
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <strong style="color:blue">${message}</strong>
        </div>
    </div>
</div>


<!-- View Profile Modal -->

<div class="modal fade" id="ViewModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header" style="color:dark">
                <h5 class="modal-title" id="exampleModalLabel">USER PROFILE</h5>
            </div>
            <center> <img src="${pageContext.request.contextPath}${sessionScope.profileImage}" alt="Profile" width="80" height="80" class="rounded-circle"/></center>
            <div class="modal-body">
                <strong class="card-title">Name: ${signUpDto.fname} ${signUpDto.lname}</strong><br><br>
                <p class="card-text"><strong>Email: ${signUpDto.email}</strong></p>
                <p class="card-text">Contact Number: ${signUpDto.mobilenumber}</p>
                <p class="card-text">Alternative Contact Number: ${signUpDto.alternatemobilenumber}</p>
                <p class="card-text">Address: ${signUpDto.address}</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-dark" data-bs-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<!--End of View Profile Modal -->

<!-- ChangePassword Modal -->

<div class="modal fade" id="ChangePassword" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" style="color:blue">
                <h2 class="modal-title" id="exampleModalLabel" style="text-align:center; color:red">Change Password</h2>
            </div>
            <div class="modal-body">
                <center><span style="color:red">${error}</span></center>
                <div class="mb-2">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" placeholder="example@gmail.com" name="email">
                    <div id="emailError" style="color:red;"></div>
                </div>
                <div class="mb-3">
                    <label for="oldpassword" class="form-label">Old Password</label>
                    <input type="password" class="form-control" id="oldpassword" placeholder="" name="oldpassword">
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
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button type="button" class="btn btn-dark">Change Password</button>
            </div>
        </div>
    </div>
</div>

<!--End of Change Password Modal -->


<!-- Complaint Raise modal -->


<div class="modal fade" id="complaintraiseModal" tabindex="-1"   aria-labelledby="complaintraisemodallabel1" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="complaintraisemodallabel1"> <i class="fas fa-plus"></i>  Raise Complaint</h5>
        <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
         </button>
       </div>
      <div class="modal-body">
        <form id="raiseComplaintForm" action="raise-complaint" method="post">

            <div class="mb-3">
                <span id="complaintTypeError"></span>
                <label for="complaintType" class="form-label"><b>Complaint Type:</b></label>
                <select class="form-select custom-select-width" id="complaintType" name="complaintType">
                    <option value="0" ${ComplaintDto.complaintType == null ? 'selected' : ''}>Select</option>
                    <option value="Electric issue" ${ComplaintDto.complaintType == 'Electric issue' ? 'selected' : ''}>Electric issue</option>
                    <option value="Water Supply" ${ComplaintDto.complaintType == 'Water Supply' ? 'selected' : ''}>Water Supply</option>
                    <option value="Network Problem" ${ComplaintDto.complaintType == 'Network Problem' ? 'selected' : ''}>Network Problem</option>
                    <option value="System Problem" ${ComplaintDto.complaintType == 'System Problem' ? 'selected' : ''}>System Problem</option>
                    <option value="Water Problem" ${ComplaintDto.complaintType == 'Water Problem' ? 'selected' : ''}>Water Problem</option>
                </select><br>
            </div>

            <!----Country ---!>

                           <div class=" mb-3">
                               <span id="countryNameError"></span>
                               <label for="countryName" class="form-label"><b>Country:</b></label>
                               <select class="form-select custom-select-width" id="countryName" name="country"  placeholder="Enter country">
                                   <!-- Countries will be loaded here by JavaScript -->
                               </select><br>
                           </div>

                           <!----State ---!>

                           <div class=" mb-3">
                               <span id="stateNameError"></span>
                               <label for="state" class="form-label"><b>State:</b></label>
                               <select class="form-select custom-select-width" id="state" name="state"  >
                                   <!-- States will be loaded here by JavaScript -->
                               </select><br>
                           </div>

                           <!----City ---!>

                           <div class=" mb-3">
                               <span id="cityNameError"></span>
                               <label for="city" class="form-label"><b>City:</b></label>
                               <select class="form-select custom-select-width" id="city" name="city" placeholder="Enter city">
                                   <!-- Cities will be loaded here by JavaScript -->
                               </select><br>
                           </div>

            <div class="mb-3">
                <span id="areaError"></span><br>
                <label for="area" class="form-label"><b>Area:</b></label>
                <input type="text" class="form-control" id="area" name="area" placeholder="Enter area">
            </div>

            <div class="mb-3">
                <span id="errorAddress"></span><br>
                <b>Address</b>
                <label for="address" class="form-floating"></label>
                <textarea class="form-control" placeholder="Enter address" id="address" style="height: 80px" name="address">${jobFormDTO.address}</textarea>
            </div>

            <div class="mb-3">
                <span id="descriptionError" class="text-danger"></span>
                <b>Description:</b>
                <div class="form-floating">
                    <textarea class="form-control" placeholder="Leave a comment here" name="description" id="description" style="height:80px" oninput="updateDescriptionCount()" maxlength="300" onblur="validateDescription()">${complaint.description}</textarea>
                    <label for="description">Description</label>
                </div>
            </div>

            <div class="form-check">
                <input class="form-check-input" type="checkbox" onblur="validateForm()" value="agree" ${signUpDto.agree ? 'checked' : ''}
                name="agree" id="agree">
                <label class="form-check-label" for="agree">Agree</label>
                <div id="agreeError" style="color:red;"></div>
            </div><br>
        </form>
      </div>

      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-dark" id="raiseComplaintButton"> <i class="fas fa-check"></i>Raise Complaint</button>
      </div>
    </div>
  </div>
</div>



<!-- end of complaint raise -->


<!--------------------------------------------------------- Complaint Raise Success Modal ---------------------->
	<!-- Complaint Raise Success Modal  -->

	<div class="modal fade" id="complaintRaiseSuccessModal" tabindex="-1"
		role="dialog" aria-labelledby="complaintRaiseSuccessModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-sm modal-dialog-centered"
			role="document">
			<div class="modal-content bg-success text-white">
				<div class="modal-body">Complaint Raised successfully.</div>
			</div>
		</div>
	</div>

	<!-- Complaint raise Error Modal -->
	<div class="modal fade" id="ComplaintRaiseErrorModal" tabindex="-1"
		role="dialog" aria-labelledby="ComplaintRaiseErrorModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-sm modal-dialog-centered"
			role="document">
			<div class="modal-content bg-danger text-white">
				<div class="modal-body">An error occurred while Complaint raise .</div>
			</div>
		</div>
	</div>
<!-- end  Complaint Raise Success Modal  -->


<!------------------------complaint view form ------------------------------------!>




<p>${msg}</p>

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
