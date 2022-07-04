<%-- 
    Document   : history
    Created on : Jul 1, 2022, 9:31:06 PM
    Author     : Ahmed Medhat
--%>

<%@ include file="header.jsp" %>

<div class="viewUsers">
    <h1><center>All Tickets</center></h1>

<%--    <input class="form-control" id="myInput" type="text" placeholder="Search..">--%>

<%
//    System.out.println(request.getParameter("c"));
    if(request.getParameter("c").equalsIgnoreCase("t")){
%>

<%--    <form id="statusTicket" class="input-form" action="/#">--%>
        <label for="status" >Ticket Status</label>
        <select class="form-control  w-25" name="status" id="status">
            <option value="Open">Open</option>
            <option value="Closed">Closed</option>
        </select>
<%--        <input type="submit" value="Submit">--%>
<%--    </form>--%>
    <%
        }
    %>
    <table class="table table-hover" id="ticket">
        <thead>
        <tr>
            <th>id</th>
            <th>emp_id_for_creation</th>
            <th>description</th>
            <th>time_created</th>
            <th>sr_id</th>
            <th>customer_id</th>
            <th>status</th>
            <th>customer_notification</th>
            <th>modify</th>
        </tr>
        </thead>
        <tbody id="bodyTable">
        </tbody>
    </table>
</div>
<%@ include file="footer.html" %>

<script>

    let select = document.getElementById('status');
    let table = document.getElementById('ticket');
    getTickets();
    // $("#statusTicket").click(function (event) {
    //     event.preventDefault();
    //     getTickets();
    // });





    function getTickets() {

        // request to get all classification
        const xhttp = new XMLHttpRequest();
        xhttp.onload = function () {
            var res = JSON.parse(this.responseText);
            console.log(res)
table.innerHTML="<thead><tr> <th>id</th> <th>emp_id_for_creation</th> <th>description</th><th>time_created</th> <th>sr_id</th><th>customer_id</th> <th>status</th> <th>customer_notification</th><th>modify</th></tr></thead><tbody id='bodyTable'></tbody>";
            res.map(val => {
                var row = table.insertRow(-1);

                var id = row.insertCell(0);
                var emp_id_for_creation = row.insertCell(1);
                var description = row.insertCell(2);
                var time_created = row.insertCell(3);
                var sr_id = row.insertCell(4);
                var customer_id = row.insertCell(5);
                var status = row.insertCell(6);
                var customer_notification = row.insertCell(7);
                var modify = row.insertCell(8);

                sr_id.innerHTML = val.sr_id;
                emp_id_for_creation.innerHTML = val.emp_id_for_creation;
                description.innerHTML = val.description;
                time_created.innerHTML = val.time_created;
                id.innerHTML = val.id;
                customer_id.innerHTML = val.customer_id;
                status.innerHTML = val.status;
                customer_notification.innerHTML = val.customer_notification;
console.log(val.id);
                modify.innerHTML = val.status=="open" ? "<a href=editTicket.jsp?id="+val.id+ " class='btn btn-primary' role='button'>Edit</a>" : " ";

            })
        }

        const queryString = window.location.search;
        const urlParams = new URLSearchParams(queryString);
        const id = urlParams.get('id')
        const Iscontract = urlParams.get('c')

        var url = "http://localhost:8081/CRM_API/api/CRM/getTickets";
        <% int levelTeam = Integer.parseInt((String) session.getAttribute("level"));%>
        var sendData = {"ID": id,"level":<%=levelTeam%>}

        if (Iscontract == "t") {
             sendData = {"ID": id}
            if (select.value == "Open")
                url = "http://localhost:8081/CRM_API/api/CRM/getOpenTicket";
            else
                url = "http://localhost:8081/CRM_API/api/CRM/getTicketHistory"
        }


        xhttp.open("POST", url, true);
        xhttp.setRequestHeader("Content-type", "application/json");
        xhttp.send(JSON.stringify(sendData));


    }

    $(document).ready(function () {

        $('#status').on('change', getTickets);


        $("#myInput").on("keyup", function () {
            var value = $(this).val().toLowerCase();
            $("#bodyTable tr").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>

<%@ include file="footerBody.html" %>