package org.example.exam.models;

import org.example.exam.databases.DatabaseConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MethodModel {
    private final Connection conn;

    public MethodModel() {
        this.conn = DatabaseConnect.getConnection();
    }
    public ResultSet getMethods() throws SQLException {
        String sql = "SELECT * FROM paymentmethod";
        PreparedStatement ps = this.conn.prepareStatement(sql);
        return ps.executeQuery();
    }
}
