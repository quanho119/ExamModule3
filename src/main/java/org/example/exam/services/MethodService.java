package org.example.exam.services;

import org.example.exam.entities.Method;
import org.example.exam.models.MethodModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MethodService {
    private MethodModel methodModel;
    public MethodService() {
        this.methodModel = new MethodModel();
    }

    public List<Method> getMethods() throws SQLException {
        List<Method> methods = new ArrayList<Method>();
        ResultSet rs = methodModel.getMethods();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            Method method = new Method(name);
            method.setId(id);
            methods.add(method);
        }
        return methods;
    }
}
