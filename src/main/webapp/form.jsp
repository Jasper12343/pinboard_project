<!DOCTYPE html>
<html>
<head>   
<meta charset="ISO-8859-1">
<title>Function Page</title>
<style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }
        :root {
            --pastel-blue: #AED9E0; /* Light blue */
            --pastel-green: #D2EFD2; /* Lighter green */
            --pastel-yellow: #FFF3B0; /* Light yellow */
            --pastel-pink: #F7C9B7; /* Light pink */
            --pastel-purple: #A993A9; /* Darker purple */
        }
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            background-color: rgba(255, 243, 176, 0.8); /* Set background color with reduced opacity */
            color: #333;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            height: 500px;
            max-width: 600px;
            width: 100%;
            padding: 20px;
            border: 2px solid var(--pastel-blue); /* Add border */
            border-radius: 8px; /* Add border radius */
            animation: fadeInFromLeft 2s ease forwards; /* Fade in animation */
            background-color: white; /* Set container background color */
            opacity: 0; /* Initially hidden */
            transform: scale(0.8); /* Set initial scale */
            margin-bottom: 20px; /* Add margin bottom to create gap between containers */
        }

        form {
            margin-bottom: 20px;
        }
        h1 {
            font-size: 36px;
            margin-bottom: 20px;
            color: var(--pastel-purple); /* Set text color */
            text-align: center;
            text-transform: uppercase;
            letter-spacing: 2px;
            animation: slideInFromTop 0.5s ease-out; /* Slide in from top animation */
        }
        input[type="number"],
        input[type="password"],
        input[type="text"],
        input[type="submit"] {
            padding: 8px;
            margin-bottom: 10px;
            width: 100%; /* Adjust width to fill the container */
            height: 40px;
            border-radius: 4px;
            border: 1px solid var(--pastel-blue); /* Set border color */
        }
        input[type="submit"] {
            background-color: #6A994E; /* Set background color */
            color: white;
            border: none;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #4E7C3F; /* Darker shade for hover effect */
        }
        label {
            display: block;
            margin-bottom: 5px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        
        @keyframes slideInFromTop {
            from {
                transform: translateY(-25px);
                opacity: 0;
            }
            to {
                transform: translateY(0);
                opacity: 1;
            }
        }
        /* Custom style for the image button */
        .toggle-password {
            display: inline-block;
            width: 30px; /* Set the width of the image */
            height: 30px; /* Set the height of the image */
            background-image: url('1.jpg'); /* Set the image for the checked state */
            background-size: cover;
            cursor: pointer;
        }
        .toggle-password.checked {
            background-image: url('2.jpg'); /* Set the image for the checked state */
        }
           
        
        .animation1{
            margin-bottom: 15px;
        }
        
        /* Define fade-in animation */
        @keyframes fadeInFromLeft {
            0% {
                opacity: 0;
                transform: translateX(-50px) scale(0.6); /* Initial position and scale */
            }
            100% {
                opacity: 1;
                transform: translateX(0) scale(0.9); /* Final position and scale */
            }
        }

        /* Styling for dropdown */
        .searchCriteria {
            width: 100%;
            height: 40px;
            border-radius: 4px;
            border: 1px solid var(--pastel-blue);
            background-color: white;
            font-size: 16px;
            padding: 8px;
            margin-bottom: 10px;
        }
        
        /* Custom style for the image button */
        .toggle-password {
            display: inline-block;
            width: 30px; /* Set the width of the image */
            height: 30px; /* Set the height of the image */
            background-image: url('1.jpg'); /* Set the image for the checked state */
            background-size: cover;
            cursor: pointer;
        }
        .toggle-password.checked {
            background-image: url('2.jpg'); /* Set the image for the checked state */
        }	

    </style>
</head>
<body>
    <%
    // Get session from request
    HttpSession session1 = (HttpSession) request.getSession(false);
    if (session1 == null || session1.getAttribute("role") == null) {
    %>
        <script>
            window.location.href = "login.html";
        </script>
    <%
        }
    
    if ("ADMIN".equals(session1.getAttribute("role")))
    {
        %>
       	<style>
       		.createNewUser{
       			display: block;
       		}
       		.postFunction{
       			display: block;
       		}
       		.getFunction{
       			display: block;
       		}
    	</style>
    	<%
    } 
    
    if ("USER".equals(session1.getAttribute("role")))
    {
        %>
       	<style>
       		.createNewUser{
       			display: none;
       		}
       		.postFunction{
       			display: none;
       		}
       		.getFunction{
       			display: block;
       		}
    	</style>
    	<%
    }  
    
    if ("GUEST".equals(session1.getAttribute("role")))
    {
        %>
       	<style>
       		.createNewUser{
       			display: none;
       		}
       		.postFunction{
       			display: block;
       		}
       		.getFunction{
       			display: none;
       		}
    	</style>
    	<%
    } 
    %>
    
    
    
    <div class="container postFunction">
        <h2 class="animation1">Post</h2>
        <form id="myForm" action="webapi/myresource/create" method="POST" class="form-group animation1" style="--delay: 1;">
            <div class="one animation1">Name</div><input class="one animation1" type="text" name="name2" id="name2" required><br>
            <div class ="two animation1">Department</div><input class="two animation1" type="text" name="department2" id="department2" required><br>
            <div class="three animation1">Salary</div><input class="three animation1" type="number" name="salary2" id="salary2" required><br>
            <input class="button animation1" type="submit" value="Submit">
        </form>
    </div>  
    <div class="container getFunction">
        <h2 class="animation1">Get</h2>
        <select id="searchCriteria" class="searchCriteria animation1">
            <option value="id1">ID</option>
            <option value="name">Name</option>
            <option value="department">Department</option>
            <option value="salary">Salary</option>
            <option value="all">Get All</option>
        </select>
        <!-- Forms for searching employees -->
        <form id="idForm" action="webapi/myresource/getWithId" method="GET" class="form-group1 animation1" style="--delay: 1;">
            <label for="id1" class="animation1">Employee ID:</label>
            <input class="animation1" type="number" id="id1" name="id1" required><br>
            <input class="animation1" type="submit" value="Get Employee">
        </form>
        <form id="nameForm" action="webapi/myresource/getWithName" method="GET" class="form-group1 animation1" style="--delay: 1;">  
            <label for="name" class="animation1">Employee Name:</label>
            <input class="animation1" type="text" id="name" name="name" required><br>
            <input class="animation1" type="submit" value="Get Employees">
        </form>
        <form id="departmentForm" action="webapi/myresource/getWithDepartment" method="GET" class="form-group1 animation1" style="--delay: 1;">   
            <label for="department" class="animation1">Employee Department:</label>
            <input class="animation1" type="text" id="department" name="department" required><br>
            <input class="animation1" type="submit" value="Get Employees">
        </form>
        <form id="salaryForm" action="webapi/myresource/getWithSalary" method="GET" class="form-group1 animation1" style="--delay: 1;">   
            <label for="salary" class="animation1">Employee Salary:</label>
            <input class="animation1" type="number" id="salary" name="salary" required><br>
            <input class="animation1" type="submit" value="Get Employees">
        </form>
        <form id="allForm" action="webapi/myresource/getAll" method="GET" class="form-group1 animation1" style="--delay: 1;">
            <input class="animation1" type="submit" value="Get All Employees">
        </form>
    </div>
    <div class="container createNewUser">
        <h2 class="animation1">Create New User</h2>
        <form action="webapi/myresource/createNewUser" method="POST" class="form-group animation1" style="--delay: 1;">
            <div class="one animation1">Uesrname</div><input class="one animation1" type="text" id="username" name="username" required><br>
            <div class ="two animation1">Password</div><input class="two animation1" type="password" id="password" name="password" required><br>
            <label class="toggle-password" onclick="togglePasswordVisibility()"></label><br>
            Choose your Role
        	<select id="searchCriteria1" name="searchCriteria1" class="searchCriteria animation1">
            	<option value="ADMIN" id="admin">Admin</option>
            	<option value="USER" id="user">User</option>
            	<option value="GUEST" id="guest">Guest</option>
            </select>
            <input class="button animation1" type="submit" value="Submit">
        </form> 
    </div>  
    
    <script>
        // Trigger fade-in animation when the page loads
        document.addEventListener('DOMContentLoaded', function() {
            var container = document.querySelector('.container');
            container.style.opacity = 1; // Set container opacity to 1
         	// Disable all forms except id1Form initially
            disableForms('idForm');
            
        });

        // Function to disable all forms except the chosen one
        function disableForms(chosenFormId) {
            var forms = document.querySelectorAll('.form-group1');
            forms.forEach(function(form) {
                if (form.id === chosenFormId) {
                    form.style.display = 'block'; // Show chosen form
                } else {
                    form.style.display = 'none'; // Hide other forms
                }
            });
        }

        // Event listener for dropdown change
        document.getElementById('searchCriteria').addEventListener('change', function() {
            var selectedOption = this.value;
            switch (selectedOption) {
                case 'id1':
                    disableForms('idForm');
                    break;
                case 'name':
                    disableForms('nameForm');
                    break;
                case 'department':
                    disableForms('departmentForm');
                    break;
                case 'salary':
                    disableForms('salaryForm');
                    break;
                case 'all':
                    disableForms('allForm');
                    break;
                default:
                    disableForms('allForm');
                    break;
            }
        });
        
        function togglePasswordVisibility() {
            var passwordField = document.getElementById("password");
            var toggleButton = document.querySelector(".toggle-password");
            
            if (passwordField.type === "password") {
                passwordField.type = "text";
                toggleButton.classList.add("checked");
            } else {
                passwordField.type = "password";
                toggleButton.classList.remove("checked");
            }
        }
        
    </script>
</body>
</html>
