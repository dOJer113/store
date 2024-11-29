package ru.techcoredev.store.web.servlets;


import ru.techcoredev.store.db.dbconnect.DBType;
import ru.techcoredev.store.db.dbmanagers.UserDBManager;
import ru.techcoredev.store.objects.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class AllServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {
        DBType dbType = DBType.valueOf(request.getServletContext().getAttribute("dbType").toString());
        List<User> userList = new UserDBManager(dbType).getUsers();
        if (userList.size() == 0) {
            request.setAttribute("noUsers", "В базе данных нет пользователей!");
        } else {
            request.setAttribute("users", userList);
        }
        request.getRequestDispatcher(JSPPages.ALL_USERS.getUrl()).forward(request, response);
    }

}
