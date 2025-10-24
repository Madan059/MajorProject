<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register | Food Delivery</title>

    <!-- Bootstrap & Font Awesome -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">

    <style>
        /* 🌈 Background */
        body {
            margin: 0;
            padding: 0;
            background: linear-gradient(135deg, #E23744, #ff6b6b, #ffd1d1);
            background-size: 300% 300%;
            animation: gradientMove 6s ease infinite;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            font-family: 'Poppins', sans-serif;
        }

        @keyframes gradientMove {
            0% { background-position: 0% 50%; }
            50% { background-position: 100% 50%; }
            100% { background-position: 0% 50%; }
        }

        /* 🍔 Glassmorphic Card */
        .register-card {
            width: 420px;
            background: rgba(255, 255, 255, 0.15);
            backdrop-filter: blur(12px);
            border-radius: 20px;
            box-shadow: 0 8px 32px rgba(0, 0, 0, 0.25);
            padding: 40px 35px;
            color: #fff;
        }

        .register-card h3 {
            text-align: center;
            font-weight: 700;
            margin-bottom: 25px;
            color: #fff;
            letter-spacing: 1px;
        }

        /* 🔒 Input Fields */
        .form-control {
            background: rgba(255, 255, 255, 0.2);
            border: none;
            border-radius: 30px;
            padding: 12px 45px 12px 20px;
            color: #fff;
            transition: 0.3s ease;
        }

        .form-control:focus {
            background: rgba(255, 255, 255, 0.3);
            outline: none;
            box-shadow: 0 0 8px rgba(255, 255, 255, 0.5);
        }

        label {
            font-size: 0.9rem;
            font-weight: 500;
            color: #fff;
            margin-bottom: 6px;
        }

        .input-group-text {
            background: transparent;
            border: none;
            color: #fff;
        }

        /* 🖱️ Button */
        .btn-register {
            border: none;
            border-radius: 30px;
            padding: 12px 0;
            font-weight: 600;
            background: #fff;
            color: #E23744;
            transition: all 0.3s ease;
        }

        .btn-register:hover {
            background: #E23744;
            color: #fff;
            box-shadow: 0 5px 20px rgba(226, 55, 68, 0.5);
        }

        /* 👇 Login link */
        .login-link {
            color: #fff;
            font-size: 0.9rem;
        }

        .login-link a {
            color: #fff;
            font-weight: 600;
            text-decoration: underline;
        }

        .login-link a:hover {
            color: #ffd1d1;
        }

        /* 🌟 Icons inside inputs */
        .input-group i {
            position: absolute;
            right: 20px;
            top: 38px;
            color: #fff;
            opacity: 0.8;
        }
        
        .alert-message {
    background-color: #fff3cd;
    color: #856404;
    border: 1px solid #ffeeba;
    border-radius: 8px;
    padding: 12px 20px;
    text-align: center;
    font-weight: 500;
    margin-bottom: 20px;
    box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
    width: 90%;
    max-width: 400px;
    margin-left: auto;
    margin-right: auto;
    animation: fadeIn 0.4s ease-in-out;
}

/* Optional animation for smooth entrance */
@keyframes fadeIn {
    from { opacity: 0; transform: translateY(-5px); }
    to { opacity: 1; transform: translateY(0); }
}
        
    </style>
</head>
<body>
    <div class="register-card">
        <h3><i class="fa-solid fa-user-plus"></i> Create Account</h3>
        
         <%-- message area --%>
    <%
        String msg = (String) request.getAttribute("message");
        String successMsg = (String) request.getAttribute("successMessage");
    %>

    <% if (msg != null && !msg.isEmpty()) { %>
        <div class="alert-message" role="alert">
            <%= msg %>
        </div>
    <% } else if (successMsg != null) { %>
        <div class="alert alert-success text-center mt-2" role="alert">
            <%= successMsg %>
        </div>
    <% } %>
    
  
    
        <form action="register" method="post">

    <div class="mb-3 position-relative">
        <label for="username">Full Name</label>
        <input type="text" class="form-control" id="username" name="username" placeholder="Enter your full name" required>
        <i class="fa-solid fa-user"></i>
    </div>

    <div class="mb-3 position-relative">
        <label for="email">Email</label>
        <input type="email" class="form-control" id="email" name="email" placeholder="Enter your email" required>
        <i class="fa-solid fa-envelope"></i>
    </div>

    <div class="mb-3 position-relative">
        <label for="address">Address</label>
        <input type="text" class="form-control" id="address" name="address" placeholder="Enter your address" required>
        <i class="fa-solid fa-location-dot"></i>
    </div>

    <div class="mb-3 position-relative">
        <label for="password">Password</label>
        <input type="password" class="form-control" id="password" name="password" placeholder="Create password" required>
        <i class="fa-solid fa-lock"></i>
    </div>

    <div class="mb-4 position-relative">
        <label for="confirmPassword">Confirm Password</label>
        <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Confirm password" required>
        <i class="fa-solid fa-lock"></i>
    </div>

    <button type="submit" class="btn btn-register w-100">Register</button>

    <p class="text-center mt-3 login-link">
        Already have an account? <a href="Login.jsp">Login here</a>
    </p>
</form>
        
    </div>
</body>
</html>
