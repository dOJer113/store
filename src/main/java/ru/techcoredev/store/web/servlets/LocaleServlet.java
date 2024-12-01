package ru.techcoredev.store.web.servlets;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@WebServlet("/locale")
public class LocaleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String locale = req.getParameter("language");
        this.setLocate(locale);
        req.getSession().setAttribute("locale", locale);
        resp.sendRedirect("/");
    }

    private void setLocate(String language) {
        if (language.equals("ru")) {
            Locale.setDefault(new Locale("ru", "RU"));
        } else {
            Locale.setDefault(Locale.US);
        }
    }
}
