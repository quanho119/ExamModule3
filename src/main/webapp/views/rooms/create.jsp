<%@ page import="org.example.exam.entities.Method" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Method> methods = (List<Method>) request.getAttribute("Methods"); %>
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
        <h1 class="mb-4 text-primary">Hotel Rooms List</h1>
    </a>
    <h2>Create Room</h2>
    <form action="/rooms/create" method="post">
        <div class="form-group">
            <label for="RoomID">Mã phòng trọ</label>
            <input type="text" name="RoomID" id="RoomID" class="form-control" required>
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
            <input type="text" name="StartDate" id="StartDate" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="PaymentMethodID">Type</label>
            <select name="PaymentMethodID" id="PaymentMethodID" class="form-control" required>
                <c:forEach var="method" items="${methods}">
                    <option value="${method.id}">${method.methodName}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="price">Price</label>
            <input type="number" name="price" id="price" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-primary">Create</button>
        <a href="/rooms" class="btn btn-secondary">Back to list</a>
    </form>
</div>
