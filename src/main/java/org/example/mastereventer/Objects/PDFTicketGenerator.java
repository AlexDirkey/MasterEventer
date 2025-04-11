package org.example.mastereventer.Objects;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class PDFTicketGenerator {

    /**
     * Genererer en PDF for en billet, med en QR-kode baseret på 'ticketInfo'.
     *
     * @param ticketInfo Teksten, der skal kodes i QR-koden (fx billettets unikke id eller anden info)
     * @param outputFilePath Stien til den PDF, der skal oprettes (fx "ticket.pdf")
     * @throws WriterException Hvis der opstår fejl under generering af QR-koden.
     * @throws IOException Hvis der opstår IO-fejl.
     */
    public static void generateTicketPDF(String ticketInfo, String outputFilePath) throws WriterException, IOException {
        // 1. Generer QR-koden med ZXing
        int qrWidth = 150;
        int qrHeight = 150;
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(ticketInfo, BarcodeFormat.QR_CODE, qrWidth, qrHeight);

        BufferedImage qrImage = new BufferedImage(qrWidth, qrHeight, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < qrWidth; x++) {
            for (int y = 0; y < qrHeight; y++) {
                qrImage.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }

        // 2. Konverter QR-koden til en array, der kan bruges af PDFBox
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(qrImage, "PNG", baos);
        byte[] qrBytes = baos.toByteArray();

        // 3. Opret en PDF med PDFBox og indsæt QR-koden
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            // Opret et PDImageXObject fra QR-byte-arrayet
            PDImageXObject pdImage = PDImageXObject.createFromByteArray(document, qrBytes, "QR Code");

            // Start en content stream til at tegne på siden
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                // Tegn QR-koden på siden (placeret f.eks. ved koordinaterne 50, 700)
                contentStream.drawImage(pdImage, 50, 700, qrWidth, qrHeight);

                // Du kan her tilføje tekst eller yderligere grafik, hvis du vil
            }

            // Gem PDF'en til angivet sti
            document.save(new File(outputFilePath));
        }
    }

    public static void main(String[] args) {
        try {
            generateTicketPDF("TicketID: e3f8c047-ac6a-4098-8378-b5a502df844f", "ticket.pdf");
            System.out.println("PDF genereret og gemt som 'ticket.pdf'.");
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }
}
