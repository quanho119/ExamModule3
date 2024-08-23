package org.example.exam.models;

import org.example.exam.databases.DatabaseConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomModel {
    private final Connection conn;

    public RoomModel() {
        this.conn = DatabaseConnect.getConnection();
    }

    public ResultSet getRooms() throws SQLException {
        String sql = "SELECT RoomInfo.RoomID,RoomInfo.TenantName,RoomInfo.PhoneNumber, RoomInfo.StartDate AS StartDate ,roominfo.PaymentMethodID ,PaymentMethod.PaymentType,RoomInfo.Notes FROM RoomInfo JOIN PaymentMethod ON RoomInfo.PaymentMethodID = PaymentMethod.PaymentMethodID" ;
        PreparedStatement ps = this.conn.prepareStatement(sql);
        return ps.executeQuery();
    }
}
