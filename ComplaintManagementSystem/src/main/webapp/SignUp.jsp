<%@ page isELIgnored ="false"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sign Up</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <script>
        let isEmailValid = false;
        let isMobileNumberValid = false;

        function updateSubmitButtonState() {
            const sendButton = document.getElementById("sendButton");
            if (isEmailValid && isMobileNumberValid) {
                sendButton.disabled = false;
            } else {
                sendButton.disabled = true;
            }
        }

        function emailValidation() {
            console.log("Validate email");
            let email = document.getElementById("email").value;
            console.log(email);
            let error = document.getElementById("emailError");

            if(email ==''){
            document.getElementById("emailError").innereHTML="Enter Valid email id";
            }

            else{
            const request = new XMLHttpRequest();
            request.open("GET", "http://localhost:8080/ComplaintManagementSystem/validateEmail/" + email);
            request.send();
            request.onload = function () {
                var ref = this.responseText;
                console.log(ref);
                error.innerHTML = ref;

                if (ref.includes("Email Already Exist")) {
                    isEmailValid = false;
                } else {
                    isEmailValid = true;
                }
                updateSubmitButtonState();
            }
        }
 }
        function mobilenumberValidation() {
            console.log("Validate mobilenumber");
            let mobilenumber = document.getElementById("mobilenumber").value;
            console.log(mobilenumber);
            let error = document.getElementById("mobilenumberValidationMessage");

            if(mobilenumber==""){

            document.getElementById=("mobilenumberError").innerHTML="Enter valid Mobile number";

            }
            else{
            const request = new XMLHttpRequest();
            request.open("GET", "http://localhost:8080/ComplaintManagementSystem/validateMobilenumber/" + mobilenumber);
            request.send();
            request.onload = function () {
                var ref1 = this.responseText;
                console.log(ref1);
                error.innerHTML = ref1;

                if (ref1.includes("This mobilenumber is already exist")) {
                    isMobileNumberValid = false;
                } else {
                    isMobileNumberValid = true;
                }
                updateSubmitButtonState();
            }
        }
      }
        function validateForm() {
            var fname = document.getElementById('fname').value.trim();
            var lname = document.getElementById('lname').value.trim();
            var email = document.getElementById('email').value.trim();
            var mobilenumber = document.getElementById('mobilenumber').value.trim();
            var alternatemobilenumber = document.getElementById('alternatemobilenumber').value.trim();
            var address = document.getElementById('address').value.trim();
            var agree = document.getElementById('agree').checked;

            var isValid = true;

            // Reset all error messages
            document.getElementById('fnameError').innerText = '';
            document.getElementById('lnameError').innerText = '';
            document.getElementById('emailError').innerText = '';
            document.getElementById('mobilenumberError').innerText = '';
            document.getElementById('alternatemobilenumberError').innerText = '';
            document.getElementById('addressError').innerText = '';
            document.getElementById('agreeError').innerText = '';

            // Validate First Name
            if (fname === '') {
                document.getElementById('fnameError').innerText = 'First Name is required.';
                isValid = false;
            }

            // Validate Last Name
            if (lname === '') {
                document.getElementById('lnameError').innerText = 'Last Name is required.';
                isValid = false;
            }

            // Validate Email
            if (email === '') {
                document.getElementById('emailError').innerText = 'Email is required.';
                isValid = false;
            } else if (!isValidEmail(email)) {
                document.getElementById('emailError').innerText = 'Enter a valid email address.';
                isValid = false;
            }

            // Validate Mobile Number
            if (mobilenumber === '') {
                document.getElementById('mobilenumberError').innerText = 'Mobile Number is required.';
                isValid = false;
            } else if (isNaN(mobilenumber)) {
                document.getElementById('mobilenumberError').innerText = 'Mobile Number must be a valid number.';
                isValid = false;
            }

            // Validate Alternate Mobile Number
            if (alternatemobilenumber === '') {
                document.getElementById('alternatemobilenumberError').innerText = 'Alternate Mobile Number is required.';
                isValid = false;
            } else if (isNaN(alternatemobilenumber)) {
                document.getElementById('alternatemobilenumberError').innerText = 'Alternate Mobile Number must be a valid number.';
                isValid = false;
            }

            // Validate Address
            if (address === '') {
                document.getElementById('addressError').innerText = 'Address is required.';
                isValid = false;
            }

            // Validate Agree Checkbox
            if (!agree) {
                document.getElementById('agreeError').innerText = 'Please agree to terms.';
                isValid = false;
            }

            return isValid;
        }

        // Function to validate email format
        function isValidEmail(email) {
            // Basic regex for email validation
            var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            return emailRegex.test(email);
        }
    </script>

</head>
<body>

<nav class="navbar navbar-dark shadow p-3 mb-5 bg-dark rounded">
    <!-- Navbar content -->
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
        </ul>
    </div>
</nav>

<form action="signup" method="post" >
    <div class="container mt-5 mb-5 d-flex justify-content-center">
        <div class="card px-2 mt-5 mb-5 py-2 bg-body shadow mt-5 mb-6 rounded" style="width:40%; padding:30px;">
            <div class="card-body">
                <br>
                <center><h2>SIGN UP</h2></center>

                <span style="color:red;">
                    <c:forEach items="${errors}" var="objectError">
                        ${objectError.defaultMessage}</br>
                    </c:forEach>
                </span>
                ${dto}

                <div class="mb-3">
                    <strong style="color:green;">${name}</strong><br>
                    <strong style="color:blue;">${msg1}</strong><br>

                    <label for="fname" class="form-label">First Name:</label>
                    <input type="text" class="form-control" id="fname" placeholder=" Enter your first name" name="fname"
                           aria-describedby="fnameHelp" onblur="validateForm()" value="${view.fname}">
                    <div id="fnameError" style="color:red;"></div>
                </div>

                <div class="mb-2">
                    <label for="lname" class="form-label">Last Name:</label>
                    <input type="text" class="form-control" id="lname" placeholder="Enter your last name" name="lname"
                           aria-describedby="lnameHelp" onblur="validateForm()" value="${view.lname}">
                    <div id="lnameError" style="color:red;"></div>
                </div>

                <div class="mb-2">
                    <label for="email" class="form-label">Email Id:</label>
                    <input type="email" class="form-control" id="email" placeholder="Enter your email address"
                           value="${view.email}" name="email" aria-describedby="emailHelp" onblur="validateForm()" onchange="emailValidation()">
                    <div id="emailError" style="color:red;"></div>
                </div>

                <div class="mb-2">
                    <label for="mobilenumber" class="form-label">Mobile Number:</label>
                    <input type="tel" class="form-control" id="mobilenumber" placeholder="Enter your Mobile number"
                           value="${view.mobilenumber}" name="mobilenumber" onblur="validateForm()" onchange="mobilenumberValidation()" aria-describedby="mobilenumberHelp">
                    <div id="mobilenumberError" style="color:red;"></div>
                    <div id="mobilenumberValidationMessage" style="color:red;"></div>
                </div>

                <div class="mb-2">
                    <label for="alternatemobilenumber" class="form-label">Alternate Number:</label>
                    <input type="tel" class="form-control" id="alternatemobilenumber"
                           placeholder="Enter your alternate mobile number" onblur="validateForm()"
                           value="${view.alternatemobilenumber}" name="alternatemobilenumber"
                           aria-describedby="alternatemobilenumberHelp">
                    <div id="alternatemobilenumberError" style="color:red;"></div>
                </div>

                <div class="mb-2">
                    <label for="address" class="form-label">Address:</label>
                    <textarea class="form-control" id="address" placeholder="Enter your address" onblur="validateForm()"
                              value="${view.address}" style="height: 100px" name="address"
                              aria-describedby="addressHelp"></textarea>
                    <div id="addressError" style="color:red;"></div>
                </div>

                <div class="form-check">
                    <input class="form-check-input" type="checkbox" onblur="validateForm()" value="agree" ${signUpDto.agree ? 'checked' : ''}
                           name="agree" id="agree">
                    <label class="form-check-label" for="agree">Agree</label>
                    <div id="agreeError" style="color:red;"></div>
                </div>

                <div class="form-group">
                    <center><button type="submit" id="sendButton" class="btn btn-dark" disabled>Sign Up</button></center>
                </div>

                <div class="form-group">
                    <p class="text-center text-muted mt-5 mb-0">Have already an account? <a href="SignIn.jsp"
                                                                                            class="fw-bold text-body"><u>Sign In here</u></a>
                    </p>
                </div>
            </div>
        </div>
    </div>
</form>

</body>
</html>
