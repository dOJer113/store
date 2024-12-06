package ru.techcoredev.store.web.servlets;

import ru.techcoredev.store.db.dbconnect.DBType;
import ru.techcoredev.store.db.dbmanagers.OrdersDBManager;
import ru.techcoredev.store.objects.OrderDetails;
import ru.techcoredev.store.objects.PdfProductDetailsPrinter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tempDir = System.getProperty("java.io.tmpdir");
        String pdfPath = tempDir + "/report.pdf";
        int id = Integer.parseInt(req.getParameter("orderNumber"));
        DBType dbType = DBType.valueOf(req.getServletContext().getAttribute("dbType").toString());
        OrderDetails details = new OrdersDBManager(dbType).getAllInfoAboutOrder(id);
        PdfProductDetailsPrinter.printPdf(details, pdfPath);
        File file = new File(pdfPath);
        resp.setContentType("application/pdf");
        resp.setHeader("Content-Disposition", "attachment; filename=\"generated.pdf\"");
        resp.setContentLengthLong(file.length());
        try (FileInputStream fis = new FileInputStream(file);
             OutputStream os = resp.getOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        }
        file.delete();
    }
}
