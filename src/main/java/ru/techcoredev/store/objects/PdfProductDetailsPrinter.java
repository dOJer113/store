package ru.techcoredev.store.objects;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;
import ru.techcoredev.store.ExceptionHandler;
import ru.techcoredev.store.resourcer.ProjectResourcer;
import ru.techcoredev.store.resourcer.Resourcer;

import java.io.FileOutputStream;

public class PdfProductDetailsPrinter {
    public static void printPdf(OrderDetails orderDetails, String pdfPath) {
        try {
            Resourcer resourcer = ProjectResourcer.getInstance();
            PdfWriter writer = new PdfWriter(new FileOutputStream(pdfPath));
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            PdfFont font = PdfFontFactory.createFont("arial unicode ms.otf", PdfEncodings.IDENTITY_H);

            document.add(new Paragraph(resourcer.getString("order.details.page.header")).setFont(font));
            document.add(new Paragraph(resourcer.getString("order.details.page.order.number") + ": " + orderDetails.getNumber()).setFont(font));
            document.add(new Paragraph(resourcer.getString("order.details.page.registration.date") + ": " + orderDetails.getRegistrationDate()).setFont(font));
            document.add(new Paragraph(resourcer.getString("order.details.page.closing.date") + ": " + orderDetails.getClosingDate()).setFont(font));
            document.add(new Paragraph(resourcer.getString("order.details.page.status") + ": " + orderDetails.getStatus()).setFont(font));
            document.add(new Paragraph(resourcer.getString("order.details.page.client.name") + ": " + orderDetails.getClientName()).setFont(font));
            document.add(new Paragraph(resourcer.getString("order.details.page.client.surname") + ": " + orderDetails.getClientSurname()).setFont(font));
            document.add(new Paragraph(resourcer.getString("order.details.page.client.phone.number") + ": " + orderDetails.getClientPhoneNumber()).setFont(font));
            document.add(new Paragraph(resourcer.getString("order.details.page.client.address") + ": " + orderDetails.getClientAddress()).setFont(font));
            document.add(new Paragraph(resourcer.getString("order.details.page.products.header")).setFont(font));
            Table table = new Table(UnitValue.createPercentArray(new float[]{2, 3, 2, 2})).useAllAvailableWidth().setFont(font);
            table.addHeaderCell(resourcer.getString("order.details.page.product.name")).setFont(font);
            table.addHeaderCell(resourcer.getString("order.details.page.product.description")).setFont(font);
            table.addHeaderCell(resourcer.getString("order.details.page.product.price")).setFont(font);
            table.addHeaderCell(resourcer.getString("order.details.page.product.remains")).setFont(font);

            for (Product product : orderDetails.getProductList()) {
                table.addCell(product.getName());
                table.addCell(product.getDescription());
                table.addCell(String.valueOf(product.getPrice()));
                table.addCell(String.valueOf(product.getRemains()));
            }

            document.add(table);
            document.close();

        } catch (Exception e) {
            ExceptionHandler.handleException("Exception making pdf file", e);
        }
    }
}
