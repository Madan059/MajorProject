<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login | Food Delivery</title>
 
    <!-- Bootstrap & Font Awesome -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Login.css">

  
</head>
<body>
    <div class="login-card">
        <h3><i class="fa-solid fa-utensils"></i> Food Delivery Login</h3>
        <form action="login" method="post">

            <div class="mb-3 position-relative">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" name="email" placeholder="Enter your email" required>
                <i class="fa-solid fa-envelope"></i>
            </div>

            <div class="mb-4 position-relative">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Enter your password" required>
                <i class="fa-solid fa-lock"></i>
            </div>

            <button type="submit" class="btn btn-login w-100">Login</button>

            <p class="text-center mt-3 register-link">
                Don’t have an account? <a href="register.jsp">Register here</a>
            </p>
        </form>
    </div>
</body>
</html>
