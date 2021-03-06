<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>
    <head>
        <title>Dashboard</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="icon" href="https://logosvector.net/wp-content/uploads/2013/06/vodafone-plc-vector-logo-200x200.png">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <div class="header">

            <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
                <!-- Brand/logo -->
                <a class="navbar-brand" href="mainPage.jsp">
                    <img src="https://logosvector.net/wp-content/uploads/2013/06/vodafone-plc-vector-logo-200x200.png" alt="logo" style="width:40px;">
                </a>

                <!-- Links -->
                <ul class="navbar-nav">
                    <% int level = Integer.parseInt((String) session.getAttribute("level"));
                    %>

                    <li class="nav-item">
                        <a class="nav-link" href="addUserForm.jsp">Add user</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="viewRatePlan.jsp">List Rate Plan</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="viewOneTimeFee.jsp">List One Time Fee</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="viewAllRecurring.jsp">List Recurring</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="listInvoice.jsp">List Invoices</a>
                    </li>

                    <%  if(level == 3 ) { %>
                        <li class="nav-item">
                            <a class="nav-link" href="addRatePlan.jsp">Add Rate Plan</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="addOneTimeFeeFormat.jsp">Add One Time Fee</a>
                        </li>
                    <% }%>
                    <%  if(level > 0 ) { %>
                    <li class="nav-item">
                        <a class="nav-link" href="viewUser.jsp">List Users</a>
                    </li>


                    <li class="nav-item">
                        <a class="nav-link" href="history.jsp?id=<%=session.getAttribute("teamID")%>&c=f"  >Work</a>
                    </li>

                    <% }%>


                    <li class="nav-item">
                        <a class="nav-link" href="Logout">Logout</a>
                    </li>

                </ul>
            </nav>
        </div>  
        <div class="content">
