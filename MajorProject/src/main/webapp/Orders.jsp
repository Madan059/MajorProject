<%@ page import="java.util.*, com.DAOModel.OrderSummary" %>
<html>
<head>
    <title>Your Orders</title>
    <style>
        body { font-family: 'Segoe UI'; background: #fdfdfd; padding: 40px; }
        .order-card {
            background: white;
            border-radius: 12px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
            padding: 20px;
            margin-bottom: 20px;
        }
        .order-card h3 { margin: 0; color: #E23744; }
        .order-meta { font-size: 0.9rem; color: #555; margin-top: 5px; }
    </style>
</head>
<body>
<h2>Your Orders</h2>
<%
    List<OrderSummary> orders = (List<OrderSummary>) request.getAttribute("orders");
    if (orders != null && !orders.isEmpty()) {
        for (OrderSummary os : orders) {
%>
    <div class="order-card">
        <h3><%= os.getName() %> (Menu ID: <%= os.getMenuId() %>)</h3>
        <p><%= os.getDescription() %></p>
        <p class="order-meta">Ordered On: <%= os.getDateAndTime() %> | Status: <%= os.getStatus() %></p>
    </div>
<%
        }
    } else {
%>
    <p>No orders found.</p>
<%
    }
%>
</body>
</html>
