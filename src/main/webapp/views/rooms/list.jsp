<%@ page import="org.example.exam.entities.Room" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%List<Room> rooms = (List<Room>) request.getAttribute("rooms");%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Rooms</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <a href="${pageContext.request.contextPath}/rooms" class="text-decoration-none">
        <h1 class="mb-4 text-primary">Danh sách phòng</h1>
    </a>
    <div class="mb-3">
        <a href="${pageContext.request.contextPath}/rooms/create" class="btn btn-primary">Tạo</a>
    </div>

    <form action="${pageContext.request.contextPath}/rooms/search" method="get" class="form-inline mb-3">
        <div class="form-group mr-2">
            <label for="search" class="sr-only">Search</label>
            <input type="text" name="keyword" id="search" class="form-control" placeholder="Search...">
        </div>
        <button type="submit" class="btn btn-secondary">Tìm</button>
    </form>
    <form action="${pageContext.request.contextPath}/rooms/delete" method="get">
        <table class="table table-bordered table-hover">
            <thead class="thead-dark">
            <tr>
                <th>STT</th>
                <th>Mã phòng trọ</th>
                <th>Tên người thuê trọ</th>
                <th>Số điện thoại</th>
                <th>Ngày bắt đầu thuê</th>
                <th>Hình thức thanh toán</th>
                <th>Ghi chú</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:set var="index" value="1"/>
            <c:forEach var="room" items="${rooms}">
                <tr>
                    <td><c:out value="${index}"/></td>
                    <td><c:out value='${String.format("PT-%03d", room.id)}'/></td>
                    <td><c:out value="${room.tenantName}"/></td>
                    <td><c:out value="${room.phoneNumber}"/></td>
                    <td><fmt:formatDate value="${room.startDate}" pattern="dd/MM/yyyy"/></td>
                    <td><c:out value="${room.paymentMethod}"/></td>
                    <td><c:out value="${room.notes}"/></td>
                    <td><input type="checkbox" name="roomIds" value="${room.id}"></td>
                </tr>
                <c:set var="index" value="${index + 1}"/>
            </c:forEach>
            </tbody>
        </table>
        <div class="mb-3">
            <input type="submit" value="Xóa" class="btn btn-primary">
        </div>
    </form>
</div>


<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

</body>
</html>
