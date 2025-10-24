<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error - Food Delivery App</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f8f8f8;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .error-container {
            background: #fff;
            padding: 30px;
            border-radius: 10px;
            text-align: center;
            box-shadow: 0px 4px 10px rgba(0,0,0,0.2);
            max-width: 400px;
        }
        .error-container h1 {
            color: #e74c3c;
            font-size: 28px;
            margin-bottom: 15px;
        }
        .error-container p {
            font-size: 16px;
            color: #555;
            margin-bottom: 20px;
        }
        .error-container a {
            text-decoration: none;
            background: #3498db;
            color: #fff;
            padding: 10px 20px;
            border-radius: 6px;
            transition: background 0.3s ease;
        }
        .error-container a:hover {
            background: #2980b9;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <h1>⚠ Oops! Something went wrong</h1>
        <p>There was a problem processing your request. Please try again.</p>
        <a href="GetAllRestorants">Go Back to Home</a>
    </div>
</body>
</html>
