package ru.techcoredev.store.web.servlets;

import ru.techcoredev.store.db.dbconnect.DBType;
import ru.techcoredev.store.db.dbmanagers.OrdersDBManager;
import ru.techcoredev.store.objects.Order;
import ru.techcoredev.store.objects.OrderDetails;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/allOrders")
public class AllOrdersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBType dbType = DBType.valueOf(req.getServletContext().getAttribute("dbType").toString());
        List<Order> orders = new OrdersDBManager(dbType).getOrders();
        req.setAttribute("orders", orders);
        req.getRequestDispatcher(JSPPages.ORDERS.getUrl()).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("orderNumber"));
        DBType dbType = DBType.valueOf(req.getServletContext().getAttribute("dbType").toString());
        OrderDetails details = new OrdersDBManager(dbType).getAllInfoAboutOrder(id);
        req.setAttribute("orderDetails", details);
        req.setAttribute("products", details.getProductList());
        req.getRequestDispatcher(JSPPages.ORDER_DETAILS.getUrl()).forward(req, resp);
    }
}
