<%-- 
    Document   : addTicket
    Created on : Jul 1, 2022, 9:31:44 PM
    Author     : Ahmed Medhat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.html" %>
<div class="addUser-form">

    <form id = "selected" class="input-form" action="/#">
        <label for="msisdn">MSISDN:</label><br>
        <input type="text" name="msisdn" placeholder="010..."><br>
        <label for="classification">Classification: </label><br>
        <select name="classification">
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
        <textarea name="desciption" placeholder="Please Put a description to help other teams" minlength="25" maxlength="200" rows="5" cols="100"></textarea>
        <br>
        <input type="submit" value="Submit">
    </form>
</div>
<%@ include file="footer.html" %>
<%@ include file="footerBody.html" %>


