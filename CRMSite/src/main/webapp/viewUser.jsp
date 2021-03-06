<%-- 
    Document   : viewUser
    Created on : Apr 26, 2022, 10:48:58 PM
    Author     : ahmedmedhat
--%>


<%@page import="java.util.Vector"%>
<%@page import="Schema.Customer"%>
<%@page import="Database.HandleDB"%>

<%@ include file="header.jsp" %>

<div class="viewUsers">
    <h1><center>All Customers Data</center></h1>

    <input class="form-control" id="myInput" type="text" placeholder="Search..">



    <table class="table table-hover">
        <thead>
            <tr>
                <th>National ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Password</th>
                <th>View Contracts</th>
                <th>Recurring</th>
                <th>Delete</th>
            </tr>
        </thead>
        <tbody id="myTable">
            <%
                HandleDB db = new HandleDB();
                Vector<Customer> customers = db.getAllUsers();
                for (Customer customer : customers) {%>
            <tr>
                <td><%=customer.getId()%></td>
                <td><%=customer.getName()%></td>
                <td><%=customer.getEmail()%></td>
                <td><%=customer.getPassword()%></td>
                <td>  <a href="detailsUser.jsp?id=<%=customer.getId()%>&name=<%=customer.getName()%>&email=<%=customer.getEmail()%>" class="btn btn-info" role="button">Details</a></td>
                <td>  <a href="displayUserRecurring.jsp?id=<%=customer.getId()%>&name=<%=customer.getName()%>" class="btn btn-primary" role="button">Recurring</a></td>
                <td>  <a href="deleteUser.jsp?id=<%=customer.getId()%>" class="btn btn-primary" id="delete" role="button">Delete All Data</a></td>
            </tr> 
            <% }
            %>

        </tbody>
    </table>
</div>
<%@ include file="footer.html" %>

<script>
    $(document).ready(function () {
        $("#myInput").on("keyup", function () {
            var value = $(this).val().toLowerCase();
            $("#myTable tr").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>

<%@ include file="footerBody.html" %>
