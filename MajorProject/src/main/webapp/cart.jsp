<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta charset="UTF-8">

<%@ page import="com.DAOModel.Cart" %>
<%@ page import="com.DAOModel.CartItem" %>
<%@ page import="java.util.Map" %>

<%
    Cart cart = (Cart) session.getAttribute("cart");
    Integer lastRestorantId = (Integer) session.getAttribute("lastRestorantId");
    String menuUrl = "menu";
    if (lastRestorantId != null) {
        menuUrl += "?restorantId=" + lastRestorantId;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Your Cart</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background: #fff;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 900px;
            margin: 40px auto;
            padding: 20px;
            background: #fefefe;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            border-radius: 12px;
        }

        h2 {
            text-align: center;
            color: #E23744;
            margin-bottom: 30px;
        }

        .cart-item {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background: #fff;
            padding: 16px;
            margin-bottom: 12px;
            border: 1px solid #eee;
            border-radius: 8px;
        }

        .item-name {
            font-size: 18px;
            font-weight: 600;
            color: #333;
            flex: 2;
        }

        .quantity-controls {
            display: flex;
            align-items: center;
            gap: 8px;
        }

        .quantity-controls form {
            display: inline;
        }

        .quantity-btn {
            background-color: #E23744;
            color: white;
            border: none;
            padding: 6px 10px;
            border-radius: 50%;
            font-size: 16px;
            cursor: pointer;
        }

        .quantity-btn:hover {
            background-color: #c62828;
        }

        .price, .subtotal {
            flex: 1;
            text-align: center;
            font-weight: 500;
            color: #444;
        }

        .remove-btn {
            background-color: #999;
            color: white;
            border: none;
            padding: 6px 12px;
            border-radius: 6px;
            cursor: pointer;
        }

        .remove-btn:hover {
            background-color: #666;
        }

        .total {
            text-align: right;
            font-size: 20px;
            font-weight: bold;
            margin-top: 30px;
            color: #333;
        }

        .browse-link {
            display: block;
            width: fit-content;
            margin: 30px auto 0;
            background-color: #24963F;
            color: white;
            padding: 12px 24px;
            border-radius: 8px;
            text-decoration: none;
            font-weight: bold;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }

        .browse-link:hover {
            background-color: #1e7e34;
        }

        @media (max-width: 600px) {
            .cart-item {
                flex-direction: column;
                align-items: flex-start;
            }

            .price, .subtotal {
                text-align: left;
                margin-top: 8px;
            }
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Your Cart</h2>

    <%
        if (cart != null && !cart.getItems().isEmpty()) {
            double total = 0;
            for (CartItem item : cart.getItems().values()) {
                double subtotal = item.getPrice() * item.getQuantity();
                total += subtotal;
    %>
        <div class="cart-item">
            <div class="item-name"><%= item.getName() %></div>

            <div class="quantity-controls">
                <form action="cart" method="post">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                    <input type="hidden" name="quantity" value="<%= item.getQuantity() - 1 %>">
                    <button type="submit" class="quantity-btn">−</button>
                </form>

                <span><%= item.getQuantity() %></span>

                <form action="cart" method="post">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                    <input type="hidden" name="quantity" value="<%= item.getQuantity() + 1 %>">
                    <button type="submit" class="quantity-btn">+</button>
                </form>
            </div>

            <div class="price">₹<%= item.getPrice() %></div>
            <div class="subtotal">₹<%= subtotal %></div>

            <form action="cart" method="post">
                <input type="hidden" name="action" value="remove">
                <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                <button type="submit" class="remove-btn">Remove</button>
            </form>
        </div>
    <%
            }
    %>
        <div class="total">Total: ₹<%= total %></div>
    <%
        } else {
    %>
        <p style="text-align:center; font-size:18px; color:#666;">Your Cart is Empty.</p>
    <%
        }
    %>

    <a href="<%= menuUrl %>" class="browse-link">Add More Items</a>
   <a href="Checkout.jsp" class="browse-link">Proceed to Checkout</a>
</div>

</body>
</html>
