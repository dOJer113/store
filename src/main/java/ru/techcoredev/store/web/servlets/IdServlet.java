package ru.techcoredev.store.web.servlets;

import ru.techcoredev.store.db.dbconnect.DBType;
import ru.techcoredev.store.db.dbmanagers.OrdersDBManager;
import ru.techcoredev.store.db.dbmanagers.UserDBManager;
import ru.techcoredev.store.objects.Order;
import ru.techcoredev.store.objects.builders.RegistrationUserBuilder;
import ru.techcoredev.store.objects.builders.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/id")
public class IdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            DBType dbType = DBType.valueOf(req.getServletContext().getAttribute("dbType").toString());
            User user = new UserDBManager(dbType).getUsers().stream().filter(x -> x.getUserId() == id).findFirst().orElse(new RegistrationUserBuilder().build());
            List<Order> orders = new OrdersDBManager(dbType).getOrdersByUserId(id);
            if (orders.size() == 0) {
                req.setAttribute("noOrders", "No orders");
            } else {
                req.setAttribute("orders", orders);
            }
            req.setAttribute("user", user);
            req.getRequestDispatcher(JSPPages.USER_BY_ID.getUrl()).forward(req, resp);
        } catch (NullPointerException | NumberFormatException e) {
            req.getSession().setAttribute("exception", "Ошибка! Id пользователя введён неверно!");
            req.getRequestDispatcher(JSPPages.ADMIN.getUrl()).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
