package org.example.exam.models;

import org.example.exam.databases.DatabaseConnect;
import org.example.exam.entities.Room;

import java.sql.*;

public class RoomModel {
    private final Connection conn;

    public RoomModel() {
        this.conn = DatabaseConnect.getConnection();
    }

    public ResultSet getRooms() throws SQLException {
        String sql = "SELECT RoomInfo.RoomID,RoomInfo.TenantName,RoomInfo.PhoneNumber, RoomInfo.StartDate ,roominfo.PaymentMethodID ,PaymentMethod.PaymentType,RoomInfo.Notes FROM RoomInfo JOIN PaymentMethod ON RoomInfo.PaymentMethodID = PaymentMethod.PaymentMethodID";
        PreparedStatement ps = this.conn.prepareStatement(sql);
        return ps.executeQuery();
    }

    public ResultSet getIDMax() throws SQLException {
        String sql = "SELECT MAX(RoomID) as max_id FROM RoomInfo";
        PreparedStatement ps = conn.prepareStatement(sql);
        return ps.executeQuery();
    }

    public void create(Room room) throws SQLException {
        String sql = "INSERT INTO RoomInfo(RoomID,TenantName,PhoneNumber,StartDate,PaymentMethodID,Notes) VALUES (?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, room.getId());
        ps.setString(2, room.getTenantName());
        ps.setString(3, room.getPhoneNumber());
        ps.setDate(4, new Date(room.getStartDate().getTime()));
        ps.setInt(5, room.getPaymentMethodID());
        ps.setString(6, room.getNotes());
        ps.executeUpdate();
    }

    public ResultSet getRoomByKeyWord(String keyword) throws SQLException {
        String sql = "SELECT RoomInfo.RoomID,RoomInfo.TenantName,RoomInfo.PhoneNumber, RoomInfo.StartDate, roominfo.PaymentMethodID, PaymentMethod.PaymentType, RoomInfo.Notes FROM RoomInfo JOIN PaymentMethod ON RoomInfo.PaymentMethodID = PaymentMethod.PaymentMethodID WHERE TenantName LIKE? OR PhoneNumber LIKE? OR RoomID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, "%" + keyword + "%");
        ps.setString(2, "%" + keyword + "%");
        int numberPart=0;
        if (keyword != null && keyword.startsWith("PT-")) {
            numberPart= Integer.parseInt(keyword.substring(3));
        }
        ps.setInt(3, numberPart);
        return ps.executeQuery();
    }

    public void removeRoom(int id) throws SQLException {
        String sql = "DELETE FROM RoomInfo WHERE RoomID =?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    }
}
