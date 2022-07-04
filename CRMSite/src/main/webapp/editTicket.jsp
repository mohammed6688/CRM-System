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
            <input type="text"  name="sr_id" id="sr_id" required><br>
        </div>

        <div class="input-form">
            <label for="description">Description</label>
            <input type="text" placeholder="Enter Description" name="description"  id="description"required><br>
        </div>

        <div class="input-form">
            <label for="emp_id_for_management">Employee ID For Management</label>
            <input type="number" placeholder="Enter Employee ID For Management" name="emp_id_for_management" id="emp_id_for_management" required><br>
        </div>

        <div class="input-form">
            <label for="notfication_detailes">Notfication Detailes</label>
            <input type="text" placeholder="Enter >Notfication Detailes" name="notfication_detailes"  id="notfication_detailes"required><br>
        </div>


        <div class="input-form">
            <label for="customer_notification">Customer Notification</label>
            <select class="form-control" name="customer_notification" id="customer_notification">
                <option value="true">true</option>
                <option value="false">false</option>
            </select>
        </div>

        <div class="input-form">
            <label for="is_notified">Is Notified</label>
            <select class="form-control" name="is_notified" id="is_notified">
                <option value="true">true</option>
                <option value="false">false</option>
            </select>
        </div>

        <div class="input-form">
            <label for="status">Status</label>
            <select class="form-control" name="status" id="status">
                <option value="open">open</option>
                <option value="close">close</option>
            </select>
        </div>

        <div class="btns">
            <button type="submit" class="btnSumbit" id="form_edit_ticket">Edit</button>
        </div>

        <p id="error-login"></p>
    </form>



</div>
<%@ include file="footer.html" %>

<script>

    $(document).ready(function () {
        $("#form_edit_ticket").click(function (event) {
            event.preventDefault();
            var ID = $('#ID').val();
            var sr_id = $('#sr_id').val();
            var description = $('#description').val();
            var emp_id_for_management = $('#emp_id_for_management').val();
            var customer_notification = $('#customer_notification').val();
            var is_notified = $('#is_notified').val();
            var status = $('#status').val();
            var notfication_detailes = $('#notfication_detailes').val();

            if (id_var !=''&&password_var!='') {
                var data = {"ID":  id_var ,"password":  password_var };
                $.ajax({
                    method : "POST",
                    contentType: "application/json; charset=utf-8",
                    dataType: "json",

                    data:JSON.stringify(data),
                    url: "http://localhost:8081/CRM_API/api/CRM/modifyTicket",
                    xhrField:{
                        withCredentials:'false',
                    },
                    headers:{

                    },

                    success: function (data) {
                        console.log(data);

// forword
                    },
                    error: function (resp) {
                        console.log(resp);
                    }
                });
            }
        });


    });
</script>

<%@ include file="footerBody.html" %>
