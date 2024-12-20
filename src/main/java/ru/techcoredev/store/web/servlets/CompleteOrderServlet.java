package ru.techcoredev.store.web.servlets;

import ru.techcoredev.store.db.dbconnect.DBType;
import ru.techcoredev.store.db.dbmanagers.OrdersDBManager;
import ru.techcoredev.store.objects.Status;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/changeOrderStatus")
public class CompleteOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int orderNumber = Integer.parseInt(req.getParameter("orderNumber"));
        DBType dbType = DBType.valueOf(req.getServletContext().getAttribute("dbType").toString());
        Status status = Status.valueOf(req.getParameter("newStatus"));
        new OrdersDBManager(dbType).changeOrderStatus(orderNumber,status);
        resp.sendRedirect(req.getContextPath() + "/allOrders");
    }
}
