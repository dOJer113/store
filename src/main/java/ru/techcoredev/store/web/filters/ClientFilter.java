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

@WebFilter(urlPatterns = {})
public class ClientFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        try {
            Role role = Role.valueOf(session.getAttribute("role").toString());
            if (role.equals(Role.CLIENT)) {
                chain.doFilter(req, res);
            }
        } catch (NullPointerException ignored) {
        }
        session.setAttribute("exception", "У вас нет доступа к этой странице!");
        res.sendRedirect("/login");
    }
}

