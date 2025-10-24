<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="jakarta.servlet.http.*,jakarta.servlet.*,java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Your Profile</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        body {
            margin: 0;
            font-family: 'Segoe UI', sans-serif;
            background: linear-gradient(135deg, #fff 0%, #fdf0f1 50%, #f8f8f8 100%);
        }

        .sidebar {
            width: 220px;
            background-color: #E23744;
            color: white;
            position: fixed;
            height: 100%;
            padding: 30px 20px;
        }

        .sidebar h2 {
            font-size: 1.8rem;
            margin-bottom: 30px;
        }

        .sidebar a {
            display: block;
            color: white;
            text-decoration: none;
            margin: 15px 0;
            font-size: 1rem;
            transition: 0.3s;
        }

        .sidebar a:hover {
            color: #ffdede;
        }

        .main-content {
            margin-left: 240px;
            padding: 40px;
        }

        .profile-card {
            background: white;
            border-radius: 16px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.08);
            padding: 30px;
            max-width: 600px;
            margin: auto;
        }

        .profile-card h2 {
            font-size: 2rem;
            color: #E23744;
            margin-bottom: 20px;
        }

        .profile-info {
            font-size: 1.1rem;
            color: #333;
            margin: 10px 0;
        }

        .profile-info i {
            margin-right: 10px;
            color: #E23744;
        }
    </style>
</head>
<body>

<!-- 🔴 Sidebar -->
<div class="sidebar">
    <h2><i class="fa fa-user-circle"></i> Profile</h2>
    <a href="ProfileServlet"><i class="fa fa-user"></i> My Profile</a>
    <a href="OrdersServlet"><i class="fa fa-box"></i> My Orders</a>
    <a href="LogoutServlet"><i class="fa fa-sign-out-alt"></i> Logout</a>
</div>

<!-- 🧾 Main Profile Content -->
<div class="main-content">
    <div class="profile-card">
        <h2>Your Profile</h2>
        <p class="profile-info"><i class="fa fa-user"></i> <b>Name:</b> <%= request.getAttribute("name") %></p>
        <p class="profile-info"><i class="fa fa-envelope"></i> <b>Email:</b> <%= request.getAttribute("email") %></p>
        <p class="profile-info"><i class="fa fa-phone"></i> <b>Address:</b> <%= request.getAttribute("address") %></p>
    </div>
</div>

</body>
</html>
