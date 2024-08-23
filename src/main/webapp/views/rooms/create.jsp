<%@ page import="org.example.exam.entities.Method" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    List<Method> methods = (List<Method>) request.getAttribute("method");
    int id = (int) request.getAttribute("id");
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <a href="${pageContext.request.contextPath}/rooms" class="text-decoration-none">
        <h1 class="mb-4 text-primary">Danh sách phòng</h1>
    </a>
    <h2>Tạo phòng</h2>
    <form action="/rooms/create" method="post">
        <div class="form-group">
            <label for="RoomID">Mã phòng trọ</label>
            <input type="text" name="RoomID" id="RoomID" class="form-control" value='${String.format("PT-%03d", id)}' required disabled>
        </div>
        <div class="form-group">
            <label for="TenantName">Tên người thuê trọ</label>
            <input type="text" name="TenantName" id="TenantName" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="PhoneNumber">Số điện thoại</label>
            <input type="text" name="PhoneNumber" id="PhoneNumber" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="StartDate">Ngày bắt đầu thuê</label>
            <input type="date" name="StartDate" id="StartDate" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="PaymentMethodID">Hình thức thanh toán</label>
            <select name="PaymentMethodID" id="PaymentMethodID" class="form-control" required>
                <c:forEach var="method" items="${methods}">
                    <option value="${method.id}">${method.methodName}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="Notes">Ghi chú</label>
            <input type="text" name="Notes" id="Notes" class="form-control" >
        </div>

        <button type="submit" class="btn btn-primary">Tạo</button>
        <a href="/rooms" class="btn btn-secondary">Hủy</a>
    </form>
</div>
