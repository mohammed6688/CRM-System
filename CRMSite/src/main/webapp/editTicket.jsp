<%--
  Created by IntelliJ IDEA.
  User: nora
  Date: 7/4/2022
  Time: 3:18 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ include file="header.jsp" %>

<div class="viewUsers">
    <h1><center>Edit Ticket</center></h1>

    <form id="editTicket">

        <div class="input-form">
            <label for="IDT">ID</label>
            <input type="text" disabled placeholder="Enter Name Rate Plan" name="IDT" id="IDT" required><br>
        </div>

        <div class="input-form">
            <label for="sr_id">SR ID</label>
            <input type="text" disabled name="sr_id" id="sr_id" required><br>
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

    let setID = document.getElementById('IDT');
    let setsr_id = document.getElementById('sr_id');
    let setdescription = document.getElementById('description');
    let setemp_id_for_management = document.getElementById('emp_id_for_management');
    let setnotfication_detailes = document.getElementById('notfication_detailes');
    let setcustomer_notification = document.getElementById('customer_notification');
    let setis_notified= document.getElementById('is_notified');
    let setstatus = document.getElementById('status');

    // request to get data for specific ticket id
    const xhttp = new XMLHttpRequest();
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const id = urlParams.get('id');
    xhttp.onload = function() {
        var res = JSON.parse(this.responseText);
        console.log(res)
        setID.value = res.id;
        setsr_id.value = res.sr_id;
        setdescription.value = res.description;
        setemp_id_for_management.value = res.emp_id_for_management || "0";
        setnotfication_detailes.value = res.notfication_detailes || " ";
        setcustomer_notification.value = res.customer_notification;
        setis_notified.value = res.is_notified || "false";
        setstatus.value = res.status;


    }
    xhttp.open("POST", "http://localhost:8081/CRM_API/api/CRM/getTicketByID");
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(JSON.stringify({"ID": id}));

    $(document).ready(function () {
        $("#form_edit_ticket").click(function (event) {
            event.preventDefault();
            var ID = $('#IDT').val();
            var sr_id = $('#sr_id').val();
            var description = $('#description').val();
            var emp_id_for_management = $('#emp_id_for_management').val();
            var customer_notification = $('#customer_notification').val();
            var is_notified = $('#is_notified').val();
            var status = $('#status').val();
            var notfication_detailes = $('#notfication_detailes').val();

            if (ID !=''&&sr_id!=''&&sr_id!=''&&description!=''&&sr_id!=''&&emp_id_for_management!=''&&customer_notification!=''&&is_notified!=''&&status!=''&&notfication_detailes!='') {
                var data = {"id":  ID ,"sr_id":  sr_id,"description":  description,"emp_id_for_management":  emp_id_for_management
                    ,"customer_notification":  customer_notification,"is_notified":  is_notified,"status":  status,"notfication_detailes":notfication_detailes };
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
                        alert("Done")
// forword
                    },
                    error: function (resp) {
                        console.log(resp);
                        alert("Try again")
                    }
                });
            }
        });


    });
</script>

<%@ include file="footerBody.html" %>
