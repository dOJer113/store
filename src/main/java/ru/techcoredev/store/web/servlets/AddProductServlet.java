package ru.techcoredev.store.web.servlets;

import ru.techcoredev.store.db.dbconnect.DBType;
import ru.techcoredev.store.db.dbmanagers.ProductsManager;
import ru.techcoredev.store.objects.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

@WebServlet("/addProduct")
public class AddProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JSPPages.ADD_PRODUCT.getUrl()).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = this.convertStringToUtf8(req.getParameter("name"));
        String description = this.convertStringToUtf8(req.getParameter("description"));
        BigDecimal price = new BigDecimal(req.getParameter("price"));
        double remains = Double.parseDouble(req.getParameter("remains"));
        Product product = new Product(name, description, price, remains);
        DBType dbType = (DBType) req.getServletContext().getAttribute("dbType");
        if (new ProductsManager(dbType).createProduct(product) != 0) {
            resp.sendRedirect(req.getContextPath() + "/success");
        } else {
            req.getSession().setAttribute("exception", "Exception! Product wasn`t added");
            this.doGet(req, resp);
        }
    }

    private String convertStringToUtf8(String value) {
        return new String(value.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
    }
}
