<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.DAOModel.Orders" %>
<%
    Orders order = (Orders) session.getAttribute("order");
    String fullName = (String) session.getAttribute("fullName");
    String phoneNumber = (String) session.getAttribute("phoneNumber");
    String deliveryAddress = (String) session.getAttribute("deliveryAddress");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Confirmation | Food Delivery</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">
    <style>
        body {
            margin: 0;
            font-family: 'Poppins', sans-serif;
            background: linear-gradient(135deg, #E23744, #ff6b6b, #ffd1d1);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            overflow: hidden;
            animation: fadeInBody 1s ease-in;
        }

        @keyframes fadeInBody {
            from { opacity: 0; }
            to { opacity: 1; }
        }

        /* 🌟 Main Card */
        .confirmation-container {
            background: rgba(255, 255, 255, 0.15);
            backdrop-filter: blur(10px);
            padding: 45px;
            border-radius: 25px;
            box-shadow: 0 10px 35px rgba(0, 0, 0, 0.3);
            text-align: center;
            width: 480px;
            color: #fff;
            position: relative;
            animation: popUp 1s ease;
        }

        @keyframes popUp {
            from { transform: scale(0.8); opacity: 0; }
            to { transform: scale(1); opacity: 1; }
        }

        .success-icon {
            font-size: 65px;
            color: #4BB543;
            animation: bounce 1.2s infinite alternate;
        }

        @keyframes bounce {
            from { transform: translateY(0); }
            to { transform: translateY(-10px); }
        }

        h2 {
            font-size: 28px;
            font-weight: 700;
            margin: 15px 0 8px;
        }

        p {
            font-size: 16px;
            color: #fefefe;
            margin: 6px 0;
        }

        .details {
            background: rgba(255, 255, 255, 0.2);
            padding: 18px;
            border-radius: 15px;
            margin-top: 15px;
            text-align: left;
            color: #fff;
            box-shadow: 0 3px 10px rgba(255, 255, 255, 0.1);
        }

        .details p { margin: 5px 0; }

        .btn {
            margin-top: 25px;
            padding: 12px 25px;
            border: none;
            background: #fff;
            color: #E23744;
            font-weight: bold;
            font-size: 16px;
            border-radius: 30px;
            cursor: pointer;
            transition: 0.3s;
        }

        .btn:hover {
            background: #E23744;
            color: #fff;
            transform: scale(1.05);
            box-shadow: 0 6px 20px rgba(226, 55, 68, 0.5);
        }

        /* 🛵 Animated Delivery Scooter */
        .delivery {
            position: absolute;
            bottom: -120px;
            left: -150px;
            animation: rideAcross 6s linear infinite;
        }

        @keyframes rideAcross {
            0% { left: -180px; }
            100% { left: 100vw; }
        }

        .delivery img {
            width: 160px;
        }

        /* 🎉 Confetti */
        .confetti {
            position: absolute;
            width: 8px;
            height: 8px;
            background: #fff;
            top: 0;
            animation: fall 3s linear infinite;
            opacity: 0.8;
        }

        @keyframes fall {
            0% { transform: translateY(0) rotate(0); opacity: 1; }
            100% { transform: translateY(100vh) rotate(360deg); opacity: 0; }
        }

        /* Random confetti colors */
        .confetti:nth-child(1) { left: 10%; background: #FFD700; animation-delay: 0s; }
        .confetti:nth-child(2) { left: 30%; background: #FF4500; animation-delay: 0.3s; }
        .confetti:nth-child(3) { left: 50%; background: #00FA9A; animation-delay: 0.6s; }
        .confetti:nth-child(4) { left: 70%; background: #1E90FF; animation-delay: 0.9s; }
        .confetti:nth-child(5) { left: 90%; background: #FF69B4; animation-delay: 1.2s; }

    </style>
</head>
<body>
    <!-- 🎉 Confetti elements -->
    <div class="confetti"></div>
    <div class="confetti"></div>
    <div class="confetti"></div>
    <div class="confetti"></div>
    <div class="confetti"></div>

    <div class="confirmation-container">
        <div class="success-icon"><i class="fa-solid fa-circle-check"></i></div>
        <h2>Order Placed Successfully!</h2>
        <p>Thank you, <b><%= fullName != null ? fullName : "Customer" %></b>.</p>
        <p>Your delicious meal is being prepared 🍕</p>

        <div class="details">
            <p><b>Order ID:</b> <%= order != null ? order.getOrderId() : "N/A" %></p>
            <p><b>Phone:</b> <%= phoneNumber != null ? phoneNumber : "N/A" %></p>
            <p><b>Address:</b> <%= deliveryAddress != null ? deliveryAddress : "N/A" %></p>
            <p><b>Payment Mode:</b> <%= order != null ? order.getPaymentMode() : "N/A" %></p>
            <p><b>Total Amount:</b> ₹<%= order != null ? order.getTotalAmount() : 0 %></p>
            <p><b>Status:</b> <span style="color: #4BB543;"><%= order != null ? order.getStatus() : "Pending" %></span></p>
        </div>

        <form action="GetRestorants">
            <button type="submit" class="btn">Continue Shopping</button>
        </form>

        <!-- 🛵 Animated delivery image -->
        <div class="delivery">
            <img src="https://cdn-icons-png.flaticon.com/512/3144/3144456.png" alt="Delivery Scooter">
        </div>
    </div>
</body>
</html>
