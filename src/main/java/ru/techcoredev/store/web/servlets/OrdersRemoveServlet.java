package ru.techcoredev.store.web.servlets;

import ru.techcoredev.store.db.dbconnect.DBType;
import ru.techcoredev.store.db.dbmanagers.OrdersDBManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/ordersRemove")
public class OrdersRemoveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String deleteDateStr = req.getParameter("deleteDate");
        LocalDate deleteDate = LocalDate.parse(deleteDateStr, DateTimeFormatter.ISO_DATE);
        DBType dbType = DBType.valueOf(req.getServletContext().getAttribute("dbType").toString());
        OrdersDBManager ordersDBManager = new OrdersDBManager(dbType);
        ordersDBManager.deleteOrdersBeforeDate(deleteDate);
        resp.sendRedirect("/allOrders");
    }
}
