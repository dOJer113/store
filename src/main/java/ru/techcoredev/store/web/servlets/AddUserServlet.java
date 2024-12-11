package ru.techcoredev.store.web.servlets;

import ru.techcoredev.store.db.dbconnect.DBType;
import ru.techcoredev.store.db.dbmanagers.UserDBManager;
import ru.techcoredev.store.objects.Role;
import ru.techcoredev.store.objects.builders.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addUser")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JSPPages.ADD_USER.getUrl()).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBType dbType = DBType.valueOf(req.getServletContext().getAttribute("dbType").toString());
        Role role = Role.valueOf(req.getParameter("role"));
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        UserDBManager userDBManager = new UserDBManager(dbType);
        User user = new RegistrationUserBuilder().email(email).password(password).role(role).build();
        if (!user.getEmail().equals("null email")) {
            if (role.equals(Role.CLIENT)) {
                String name = req.getParameter("name");
                String surname = req.getParameter("surname");
                String phone = req.getParameter("phone");
                String address = req.getParameter("address");
                Client client = new RegistrationClientBuilder().name(name).surname(surname).phoneNumber(phone).address(address).build();
                if (!client.getName().equals(Client.NO_USER_NAME)) {
                    if (!userDBManager.addUserAndClient(user, client)) {
                        req.getSession().setAttribute("exception", "Exception");
                        req.getRequestDispatcher(JSPPages.ADD_USER.getUrl()).forward(req, resp);
                    }
                } else {
                    req.getSession().setAttribute("exception", "Client`s field is not valid");
                    req.getRequestDispatcher(JSPPages.ADD_USER.getUrl()).forward(req, resp);
                }
            } else {
                if (userDBManager.addUser(user) == 0) {
                    req.getSession().setAttribute("exception", "User`s field is not valid");
                    req.getRequestDispatcher(JSPPages.ADD_USER.getUrl()).forward(req, resp);
                }
            }
        } else {
            req.getSession().setAttribute("exception", "User`s field is not valid");
            req.getRequestDispatcher(JSPPages.ADD_USER.getUrl()).forward(req, resp);
        }
        resp.sendRedirect(req.getContextPath() + "/success");
    }
}
