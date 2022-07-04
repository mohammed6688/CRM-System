<%--
  Created by IntelliJ IDEA.
  User: nora
  Date: 7/4/2022
  Time: 3:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.Vector"%>
<%@page import="Schema.Customer"%>
<%@page import="Database.HandleDB"%>

<%@ include file="header.jsp" %>

<div class="viewUsers">
    <h1><center>Edit Ticket</center></h1>

    <form id="editTicket">

        <div class="input-form">
            <label for="ID">ID</label>
            <input type="text" disabled placeholder="Enter Name Rate Plan" name="ID" id="ID" required><br>
        </div>

        <div class="input-form">
            <label for="sr_id">SR ID</label>
            <input type="text" placeholder="Enter Monthly Fee" name="sr_id" id="sr_id" required><br>
        </div>

        <div class="input-form">
            <label for="description">description</label>
            <input type="text" placeholder="Enter Monthly Fee" name="description"  id="description"required><br>
        </div>

        <div class="input-form">
            <label for="emp_id_for_management">emp_id_for_management</label>
            <input type="text" placeholder="Enter Monthly Fee" name="emp_id_for_management" id="emp_id_for_management" required><br>
        </div>

        <div class="input-form">
            <label for="customer_notification">customer_notification</label>
            <input type="text" placeholder="Enter Monthly Fee" name="customer_notification" id="customer_notification" required><br>
        </div>

        <div class="input-form">
            <label for="is_notified">is_notified</label>
            <input type="text" placeholder="Enter Monthly Fee" name="is_notified" id="is_notified" required><br>
        </div>

        <div class="btns">
            <button type="submit" class="btnSumbit">Edit</button>
        </div>

        <p id="error-login"></p>
    </form>



</div>
<%@ include file="footer.html" %>

<script>

</script>

<%@ include file="footerBody.html" %>
