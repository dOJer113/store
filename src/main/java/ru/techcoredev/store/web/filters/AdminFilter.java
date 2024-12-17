package ru.techcoredev.store.web.filters;

import ru.techcoredev.store.objects.Role;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/id", "/users", "/admin", "/allOrders", "/download", "/addUser", "/requestRemove", "/addProduct"})
public class AdminFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        try {
            Role role = Role.valueOf(session.getAttribute("role").toString());
            if (role.equals(Role.ADMIN)) {
                chain.doFilter(req, res);
            } else {
                session.setAttribute("exception", "У вас нет доступа к этой странице!");
                res.sendRedirect("/login");
            }
        } catch (NullPointerException ignored) {
            session.setAttribute("exception", "У вас нет доступа к этой странице!");
            res.sendRedirect("/login");
        }
    }
}

