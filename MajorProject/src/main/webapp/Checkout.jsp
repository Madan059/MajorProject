<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Checkout</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Checkout.css">
</head>
<body>
<div class="checkout-container">
    <h2>Checkout</h2>
    <form action="checkout" method="post">
        <label>Full Name:</label>
        <input type="text" name="fullName" placeholder="Enter your full name" required>

        <label>Mobile Number:</label>
        <input type="number" name="phoneNumber" placeholder="Enter your mobile number" required>

        <label>Delivery Address:</label>
        <textarea name="deliveryAddress" placeholder="Enter your delivery address" required></textarea>

        <label>Payment Method:</label>
        <div class="payment-methods">
            <label><input type="radio" name="paymentMethod" value="COD" checked> Cash on Delivery</label>
            <label><input type="radio" name="paymentMethod" value="UPI"> UPI (Google Pay / PhonePe / Paytm)</label>
            <label><input type="radio" name="paymentMethod" value="Card"> Credit / Debit Card</label>
        </div>

        <button type="submit" class="btn">Place Order</button>
        <a href="cart.jsp" class="back-link">← Back to Cart</a>
    </form>
</div>
</body>
</html>
