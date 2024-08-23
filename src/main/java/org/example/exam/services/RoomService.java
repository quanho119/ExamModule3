package org.example.exam.services;

import org.example.exam.entities.Room;
import org.example.exam.models.RoomModel;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoomService {
    private RoomModel roomModel;

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
            Room room = new Room(tenantName,phoneNumber,startdate,paymentTypeID,notes);
            room.setId(id);
            room.setPaymentMethod(paymentType);
            rooms.add(room);
        }
        return rooms;
    }
}
