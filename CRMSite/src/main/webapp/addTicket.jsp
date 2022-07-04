<%-- 
    Document   : addTicket
    Created on : Jul 1, 2022, 9:31:44 PM
    Author     : Ahmed Medhat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<div class="addUser-form">

    <form id = "submit-ticket" class="input-form" action="/#">
        <label for="msisdn">MSISDN:</label><br>
        <input type="text" disabled value=<%=request.getParameter("msisdn")%>  name="msisdn" placeholder="010..."><br>

        <input type="hidden" disabled value=<%=request.getParameter("cid")%>  id="customer_id" name="customer_id" placeholder="010..."><br>

        <input type="hidden" disabled value=<%=session.getAttribute("id")%> id="emp_id_for_creation" name="emp_id_for_creation" placeholder="010..."><br>

        <label for="classification">Classification: </label><br>
        <select class="form-control" name="classification" id="classification">
            <option value=""></option>
        </select><br>

        <label for="type">Type: </label><br>
        <select class="form-control" name="type" id="type">
            <option value=""></option>
        </select><br>


        <label for="area">Area: </label><br>
        <select class="form-control" name="area" id="area">
            <option value=""></option>
        </select>
        <br>
        <label for="sub-area" >Sub-Area: </label><br>
        <select class="form-control" name="sub-area" id="sub-area">
            <option value=""></option>
        </select>
        <br>
        Description: <br>
        <textarea name="description" id="description" placeholder="Please Put a description to help other teams" minlength="25" maxlength="200" rows="5" cols="100"></textarea>
        <br>
        <input type="submit" value="Submit">
    </form>
</div>
<%@ include file="footer.html" %>
<script>
    select = document.getElementById('classification');
    selectType = document.getElementById('type');
    selectArea = document.getElementById('area');
    selectSubArea = document.getElementById('sub-area');

    // request to get all classification
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        var res = JSON.parse(this.responseText);
        console.log(res)

        res.map(val => {
            var opt = document.createElement('option');
            opt.value = val.id;
            opt.innerHTML = val.sr_classification;
            select.appendChild(opt);
        })
    }
    xhttp.open("POST", "http://localhost:8081/CRM_API/api/CRM/getClassifications");
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();


    function clearData(selectToClear) {
        var opt = document.createElement('option');
        selectToClear.innerHTML = "";
        opt.value = "";
        opt.innerHTML = "";
        selectToClear.appendChild(opt);
    }

    $(document).ready(function () {

        $('#classification').on('change', function () {
            clearData(selectType);
            clearData(selectArea);
            clearData(selectSubArea);
            var classification_id = $(this).val(); // get selected value
            if (classification_id !='') {
                var data = {"ID":  classification_id};
                $.ajax({
                    method : "POST",
                    dataType: "json",
                    data:JSON.stringify(data),
                    url: "http://localhost:8081/CRM_API/api/CRM/getType",
                    xhrField:{
                        withCredentials:'false',
                    },
                    headers:{

                    },
                    contentType: "application/json",

                    success: function (data) {
                        console.log(data);

                        data.map(val => {
                            var opt = document.createElement('option');
                            opt.value = val.id;
                            opt.innerHTML = val.sr_type;
                            selectType.appendChild(opt);
                        })

// forword
                    },
                    error: function (resp) {
                        console.log(resp);
                        clearData(selectType);

                    }
                });
            }

        });

        $('#type').on('change', function () {
            clearData(selectArea);
            clearData(selectSubArea);
            var id = $(this).val(); // get selected value
            if (id !='') {
                var data = {"ID":  id};
                $.ajax({
                    method : "POST",
                    dataType: "json",
                    data:JSON.stringify(data),
                    url: "http://localhost:8081/CRM_API/api/CRM/getArea",
                    xhrField:{
                        withCredentials:'false',
                    },
                    headers:{

                    },
                    contentType: "application/json",

                    success: function (data) {
                        console.log(data);

                        data.map(val => {
                            var opt = document.createElement('option');
                            opt.value = val.id;
                            opt.innerHTML = val.sr_area;
                            selectArea.appendChild(opt);
                        })

// forword
                    },
                    error: function (resp) {
                        clearData(selectArea);
                        console.log(resp);
                    }
                });
            }

        });

        $('#area').on('change', function () {
            clearData(selectSubArea);
            var id = $(this).val(); // get selected value
            if (id !='') {
                var data = {"ID":  id};
                $.ajax({
                    method : "POST",
                    dataType: "json",
                    data:JSON.stringify(data),
                    url: "http://localhost:8081/CRM_API/api/CRM/getSubArea",
                    xhrField:{
                        withCredentials:'false',
                    },
                    headers:{

                    },
                    contentType: "application/json",

                    success: function (data) {
                        console.log(data);

                        data.map(val => {
                            var opt = document.createElement('option');
                            opt.value = val.id;
                            opt.innerHTML = val.sr_subarea;
                            selectSubArea.appendChild(opt);
                        })

// forword
                    },
                    error: function (resp) {
                        clearData(selectSubArea);
                        console.log(resp);
                    }
                });
            }

        });


        $("#submit-ticket").click(function (event) {
            event.preventDefault();

            var emp_id_for_creation = $('#emp_id_for_creation').val();
            var description = $('#description').val();
            var sr_id = $('#sub-area').val();
            var customer_id = $('#customer_id').val();

            if (emp_id_for_creation !=''&&description!=''&&sr_id!=''&&customer_id!='') {
                var data = {"emp_id_for_creation":  emp_id_for_creation ,"description":  description,
                    "sr_id":  sr_id,"customer_id":  customer_id, };
                $.ajax({
                    method : "POST",
                    contentType: "application/json; charset=utf-8",
                    dataType: "json",

                    data:JSON.stringify(data),
                    url: "http://localhost:8081/CRM_API/api/CRM/submitTicketsubmitTicket",
                    xhrField:{
                        withCredentials:'false',
                    },
                    headers:{

                    },

                    success: function (data) {
                        alert("Ticket number : "+ data.TicketID)
                        console.log(data);
                    },
                    error: function (resp) {
                        alert("Sorry try again")
                        console.log(resp);
                    }
                });
            }
        });



    });


</script>
<%@ include file="footerBody.html" %>


