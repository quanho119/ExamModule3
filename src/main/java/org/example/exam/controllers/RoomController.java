package org.example.exam.controllers;

import org.example.exam.entities.Method;
import org.example.exam.entities.Room;
import org.example.exam.services.MethodService;
import org.example.exam.services.RoomService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "RoomController", value = "/rooms/*")
public class RoomController extends HttpServlet {
    private RoomService roomService;
    private MethodService methodService;
    @Override
    public void init() throws ServletException {
        roomService = new RoomService();
        methodService = new MethodService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getPathInfo();
        try {
            if (url == null || url.equals("/")) {
                this.renderRoomList(req, resp);
            }
            switch (Objects.requireNonNull(url)) {
                case "/create":
                    this.renderRoomCreate(req, resp);
                    break;
//                case "/delete":
//                    this.deleteRoom(req, resp);
//                    break;
                case "/search":
                    this.searchRoom(req, resp);
                    break;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getPathInfo();
        try {
            switch (Objects.requireNonNull(url)) {
                case "/create":
                    this.createRoom(req, resp);
                    break;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private void searchRoom(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        List<Room> rooms = this.roomService.getRoomByKeyWord(req);
        req.setAttribute("rooms", rooms);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/rooms/list.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void createRoom(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        this.roomService.create(req, resp);
        resp.sendRedirect("/rooms/");
    }


    private void renderRoomList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        List<Room> rooms = this.roomService.getAllRooms(req);
        req.setAttribute("rooms", rooms);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/rooms/list.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void renderRoomCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        List<Method> methods = this.methodService.getMethods();
        int id = this.roomService.getIdMax()+1;
        req.setAttribute("methods", methods);
        req.setAttribute("id",id);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/rooms/create.jsp");
        requestDispatcher.forward(req, resp);
    }
}
