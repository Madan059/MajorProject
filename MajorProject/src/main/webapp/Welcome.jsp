<%@ page import="java.util.ArrayList" %>
<%@ page import="com.DAOModel.Restorant" %>
<%@ page import="com.DAOModel.User" %>

<%
    ArrayList<Restorant> restorantList = (ArrayList<Restorant>) request.getAttribute("restorantList");
    User user = (User) session.getAttribute("user");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Restaurants</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
     <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Welcome.css">

    
</head>
<body>

    <!-- 🔝 Navbar -->
    <div class="navbar">
        <h2><i class="fa-solid fa-utensils"></i> Food Delivery</h2>
        <i class="fa-solid fa-user profile-icon" onclick="openSidebar()"></i>
    </div>

    <!-- 🔥 Hero Section -->
    <div class="hero">
        <h1>Discover the best food near you</h1>
    </div>

    <h2>Available Restaurants</h2>

    <!-- 🔍 Search + Filter -->
    <div class="search-filter">
        <input type="text" id="searchInput" class="search-bar" placeholder="Search restaurants...">
        <select id="cuisineFilter" class="filter-dropdown">
            <option value="all">All Cuisines</option>
            <option value="Indian">Indian</option>
            <option value="Chinese">Chinese</option>
            <option value="Italian">Italian</option>
            <option value="Mexican">Mexican</option>
        </select>
    </div>

    <!-- 🏬 Restaurant Grid -->
    <div class="grid-container" id="restaurantGrid">
        <%
            if (restorantList != null) {
                for (Restorant r : restorantList) {
        %>
        <div class="card"
             data-name="<%= r.getName().toLowerCase() %>"
             data-cuisine="<%= r.getCuisineType().toLowerCase() %>">

            <img src="<%= request.getContextPath() + "/" + r.getImagePath() %>" alt="Restaurant Image">
            <div class="card-body">
                <h5 class="card-title"><%= r.getName() %></h5>
                <p class="card-text"><b>Cuisine:</b> <%= r.getCuisineType() %></p>
                <p class="card-text"><b>Delivery Time:</b> <%= r.getDeliveryTime() %> mins</p>
                <p class="card-text"><b>Rating:</b> <span class="rating"><i class="fa fa-star"></i> <%= r.getRatings() %></span></p>
                <p class="card-text"><b>Address:</b> <%= r.getAddress() %></p>
                <a href="menu?RestorantId=<%= r.getRestorantId() %>" class="btn">See Menu</a>
            </div>
        </div>
        <%
                }
            }
        %>
    </div>

    <!-- ⚙️ Sidebar -->
    <div id="sidebar" class="sidebar">
        <span class="closebtn" onclick="closeSidebar()">&times;</span>
        <a href="ProfileServlet"><i class="fa-solid fa-user"></i> Profile</a>
        <a href="OrdersServlet"><i class="fa-solid fa-receipt"></i> Orders</a>
        <a href="LogoutServlet"><i class="fa-solid fa-right-from-bracket"></i> Logout</a>
    </div>

    <div id="overlay" class="overlay" onclick="closeSidebar()"></div>

    <!-- JS -->
    <script>
        function openSidebar() {
            document.getElementById("sidebar").style.width = "250px";
            document.getElementById("overlay").style.display = "block";
        }

        function closeSidebar() {
            document.getElementById("sidebar").style.width = "0";
            document.getElementById("overlay").style.display = "none";
        }

        // Live search & filter
        const searchInput = document.getElementById("searchInput");
        const cuisineFilter = document.getElementById("cuisineFilter");
        const cards = document.querySelectorAll(".card");

        function filterRestaurants() {
            const searchText = searchInput.value.toLowerCase();
            const selectedCuisine = cuisineFilter.value.toLowerCase();

            cards.forEach(card => {
                const name = card.getAttribute("data-name");
                const cuisine = card.getAttribute("data-cuisine");

                if ((name.includes(searchText) || searchText === "") &&
                    (selectedCuisine === "all" || cuisine === selectedCuisine)) {
                    card.style.display = "block";
                } else {
                    card.style.display = "none";
                }
            });
        }

        searchInput.addEventListener("keyup", filterRestaurants);
        cuisineFilter.addEventListener("change", filterRestaurants);
    </script>

</body>
</html>
