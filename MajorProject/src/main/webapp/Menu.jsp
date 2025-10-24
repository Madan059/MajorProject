<%@ page import="java.util.ArrayList" %>
<%@ page import="com.DAOModel.Menu" %>

<%
    ArrayList<Menu> menuList = (ArrayList<Menu>) request.getAttribute("menuList");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Menu</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f8f8f8;
            margin: 0;
            padding: 20px;
        }

        h2 {
            color: #333;
            margin-bottom: 20px;
        }

        .menu-container {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
            gap: 20px;
        }

        .menu-card {
            position: relative;
            background: #fff;
            border-radius: 16px;
            overflow: hidden;
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
            transition: transform 0.2s;
        }

        .menu-card:hover {
            transform: translateY(-5px);
        }

        .menu-card img {
            width: 100%;
            height: 180px;
            object-fit: cover;
        }

        .availability-badge {
            position: absolute;
            top: 10px;
            right: 10px;
            background: #24963F;
            color: #fff;
            font-size: 13px;
            font-weight: bold;
            padding: 4px 8px;
            border-radius: 8px;
        }

        .availability-badge.not {
            background: #E23744; /* red for unavailable */
        }

        .menu-content {
            padding: 15px;
        }

        .menu-title {
            font-size: 18px;
            font-weight: bold;
            color: #333;
            margin: 0 0 8px;
        }

        .menu-description {
            font-size: 14px;
            color: #666;
            margin: 0 0 10px;
            height: 40px;
            overflow: hidden;
            text-overflow: ellipsis;
        }

        .menu-footer {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .price {
            font-size: 18px;
            font-weight: bold;
            color: #e74c3c;
        }

        .order-btn {
            background: #E23744;
            color: #fff;
            padding: 6px 12px;
            border-radius: 8px;
            font-size: 14px;
            font-weight: bold;
            border: none;
            cursor: pointer;
            transition: 0.2s;
        }

        .order-btn:hover {
            background: #b91d2d;
        }
    </style>
</head>
<body>

<h2>Menu</h2>

<%
    if (menuList != null && !menuList.isEmpty()) {
%>
    <div class="menu-container">
    <%
        for (Menu menu : menuList) {
            String availabilityClass = menu.isAvailable() ? "availability-badge" : "availability-badge not";
            String availabilityText = menu.isAvailable() ? "Available" : "Not Available";
    %>
        <div class="menu-card">
            <img src="<%= menu.getImagePath() %>" alt="Menu Image">
            <div class="<%= availabilityClass %>"><%= availabilityText %></div>
            <div class="menu-content">
                <h3 class="menu-title"><%= menu.getName() %></h3>
                <p class="menu-description"><%= menu.getDescription() %></p>
                <div class="menu-footer">
                    <span class="price">&#8377;<%= menu.getPrice() %></span>
                    <% if (menu.isAvailable()) { %>
                        <form action="cart" method="post">
                            <input type="hidden" name="action" value="add">
                            <input type="hidden" name="itemId" value="<%= menu.getMenuId() %>">
               
                            <input type="hidden" name="itemName" value="<%= menu.getName() %>">
                            <input type="hidden" name="price" value="<%= menu.getPrice() %>">
                            <input type="hidden" name="restorantId" value="<%= menu.getRestorantId() %>">
                            <button type="submit" class="order-btn">Add to Cart</button>
                        </form>
                    <% } %>
                </div>
            </div>
        </div>
    <%
        }
    %>
    </div>
<%
    } else {
%>
    <p>No menu items found for this restaurant.</p>
<%
    }
%>

</body>
</html>
