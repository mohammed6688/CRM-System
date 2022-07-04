<%-- 
    Document   : addTicket
    Created on : Jul 1, 2022, 9:31:44 PM
    Author     : Ahmed Medhat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<div class="addUser-form">

    <form id = "selected" class="input-form" action="/#">
        <label for="msisdn">MSISDN:</label><br>
        <input type="text" disabled value=<%=request.getParameter("msisdn")%>  name="msisdn" placeholder="010..."><br>

        <input type="hidden" disabled value=<%=request.getParameter("cid")%>  name="customer_id" placeholder="010..."><br>

        <input type="hidden" disabled value=<%=session.getAttribute("id")%>  name="emp_id_for_creation" placeholder="010..."><br>

        <label for="classification">Classification: </label><br>
        <select name="classification" id="classification">
                <option value="technical">Technical</option>
                <option value="finance">Finance</option>
        </select><br>

        <label for="type">Type: </label><br>
        <select name="type">
            <option value="technical">Technical</option>
            <option value="finance">Finance</option>
        </select><br>


        <label for="area">Area: </label><br>
        <select name="area">
            <optgroup label="Technical">
                <option value="netowrk">Network</option>
                <option value="ran">Radio Access Network</option>
            </optgroup>
            <optgroup label="Finance">
                <option value="invoice">Invoice</option>
                <option value="issue">Issue</option>
            </optgroup>
        </select>
        <br>
        <label for="area">Sub-Area: </label><br>
        <select name="sub-area">
            <optgroup label="Network">
                <option value="4g">Can't Switch to 4G</option>
                <option value="data">Can't Use Mobile Data</option>
            </optgroup>
            <optgroup label="Radio Access Netowrk">
                <option value="txCall">Can't Make Call</option>
                <option value="txSms">Can't Send SMS</option>
                <option value="rxCall">Can't Receive Call</option>
                <option value="rxSms">Can't Receive SMS</option>
            </optgroup>
            
            <optgroup label="Invoice">
                <option value="incorrect">Invoice Incorrect</option>
                <option value="missed">Did not Receive it</option>
            </optgroup>
            <optgroup label="Issue">
                <option value="asset">Can't Buy Product</option>
                <option value="vodafoneCash">Can't Take Money from ATM</option>
            </optgroup>
        </select>
        <br>
        Description: <br>
        <textarea name="description" placeholder="Please Put a description to help other teams" minlength="25" maxlength="200" rows="5" cols="100"></textarea>
        <br>
        <input type="submit" value="Submit">
    </form>
</div>
<%@ include file="footer.html" %>
<script>


    $(document).ready(function () {


        $.ajax({
            method : "POST",
            dataType: "json",
            url: "http://localhost:8081/CRM_API/api/CRM/Login",
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





        $("#classification").click(function (event) {
            event.preventDefault();
            var id_var = $('#ID').val();
            var password_var = $('#password').val();
            if (id_var !=''&&password_var!='') {
                var data = {"ID":  id_var ,"password":  password_var };
                JSON.stringify({ "ID": id_var , "password" : password_var});
                $.ajax({
                    method : "POST",
                    dataType: "json",

                    data:JSON.stringify(data),
                    url: "http://localhost:8081/CRM_API/api/CRM/Login",
                    xhrField:{
                        withCredentials:'false',
                    },
                    headers:{

                    },
                    contentType: "application/json",

                    success: function (data) {
                        console.log(data);
                        if(data.login=="true")
                        {
                            queryString = "?" + "level="+data.level+"&teamID="+data.teamID+"&id="+id_var;
                            var url = "/CRMSite/CheckLogin" + queryString;

                            if (window.XMLHttpRequest) {
                                request = new XMLHttpRequest();
                            } else if (window.ActiveXObject) {
                                request = new ActiveXObject("Microsoft.XMLHTTP");
                            }

                            try {
                                request.onreadystatechange = sendInfo;
                                request.open("POST", url, true);
                                request.send();

                            } catch (e) {
                                alert("Unable to connect server");
                            }
                            function sendInfo() {
                                if (request.readyState == 4) {
                                    //            var res = JSON.parse(request.responseText);
                                    if (request.responseText == "true") {
                                        window.location.replace("http://localhost:8080/CRMSite/mainPage.jsp");
                                    } else {
                                        document.getElementById("error-login").innerHTML = "Wrong user name or password ";
                                    }
                                }
                            }

                        }
                        else{
                            document.getElementById("error-login").innerHTML = "Wrong user name or password "
                        }

// forword
                    },
                    error: function (resp) {
                        console.log(resp);
                        document.getElementById("error-login").innerHTML = "Wrong user name or password "
                    }
                });
            }
        });

        // $(document).ajaxStop(function () {
        //     window.location.reload();
        // });

    });


</script>
<%@ include file="footerBody.html" %>


