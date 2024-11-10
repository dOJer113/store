package ru.techcoredev.store.servlets;


import ru.techcoredev.store.dbmanagers.UserDBManager;
import ru.techcoredev.store.objects.User;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/users")
@RolesAllowed("admin")
public class AllServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {
        List<User> userList = UserDBManager.getUsers();
        if (userList.size() == 0) {
            request.setAttribute("noUsers", "В базе данных нет пользователей!");
        } else {
            request.setAttribute("users", userList);
        }
        request.getRequestDispatcher(JSPPages.ALL_USERS.getUrl()).forward(request, response);
    }

}
