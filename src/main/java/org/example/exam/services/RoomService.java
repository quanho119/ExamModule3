package org.example.exam.services;

import org.example.exam.entities.Room;
import org.example.exam.models.RoomModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoomService {
    private final RoomModel roomModel;

    public RoomService() {
        roomModel = new RoomModel();
    }

    public List<Room> getAllRooms(HttpServletRequest req) throws SQLException {
        List<Room> rooms = new ArrayList<>();
        ResultSet resultSet = this.roomModel.getRooms();
        while (resultSet.next()) {
            int id = resultSet.getInt("RoomID");
            String tenantName = resultSet.getString("TenantName");
            String phoneNumber = resultSet.getString("PhoneNumber");
            Date startdate = resultSet.getDate("StartDate");
            int paymentTypeID = resultSet.getInt("PaymentMethodID");
            String paymentType = resultSet.getString("PaymentType");
            String notes = resultSet.getString("Notes");
            Room room = new Room(tenantName, phoneNumber, startdate, paymentTypeID, notes);
            room.setId(id);
            room.setPaymentMethod(paymentType);
            rooms.add(room);
        }
        return rooms;
    }

    public int getIdMax() throws SQLException {
        ResultSet resultSet = this.roomModel.getIDMax();
        if (resultSet.next()) {
            int id = resultSet.getInt("max_id");
            return id;
        }
        return 0;
    }

    public void create(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        int id = getIdMax() + 1;
        String tenantName = req.getParameter("TenantName");
        String phoneNumber = req.getParameter("PhoneNumber");
        String startDateStr = req.getParameter("StartDate");
        int paymentMethodID = Integer.parseInt(req.getParameter("PaymentMethodID"));
        String notes = req.getParameter("Notes");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        try {
            startDate = dateFormat.parse(startDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Room room = new Room(tenantName, phoneNumber, startDate, paymentMethodID, notes);
        room.setId(id);
        this.roomModel.create(room);
    }

    public List<Room> getRoomByKeyWord(HttpServletRequest req) throws SQLException {
        String keyword = req.getParameter("keyword");
        List<Room> rooms = new ArrayList<>();
        ResultSet resultSet = this.roomModel.getRoomByKeyWord(keyword);
        while (resultSet.next()) {
            int id = resultSet.getInt("RoomID");
            String tenantName = resultSet.getString("TenantName");
            String phoneNumber = resultSet.getString("PhoneNumber");
            Date startdate = resultSet.getDate("StartDate");
            int paymentTypeID = resultSet.getInt("PaymentMethodID");
            String paymentType = resultSet.getString("PaymentType");
            String notes = resultSet.getString("Notes");
            Room room = new Room(tenantName, phoneNumber, startdate, paymentTypeID, notes);
            room.setId(id);
            room.setPaymentMethod(paymentType);
            rooms.add(room);
        }
        return rooms;
    }

    public void deleteRoom(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        String[] roomIds = req.getParameterValues("roomIds");
        for (String roomId : roomIds) {
            int id = Integer.parseInt(roomId);
            this.roomModel.removeRoom(id);
        }
    }
}
