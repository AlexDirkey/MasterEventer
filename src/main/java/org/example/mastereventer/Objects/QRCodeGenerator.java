package org.example.mastereventer.Objects;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class QRCodeGenerator {

    /**
     * Genererer et QR-kodebillede fra en given tekst og gemmer det til den angivne sti.
     *
     * @param text     Den tekst, der skal kodes (fx billet-ID eller anden unik identifier).
     * @param width    Bredden på QR-koden.
     * @param height   Højden på QR-koden.
     * @param filePath Stien til, hvor billede skal gemmes (fx "C:/path/ticket.png").
     * @throws WriterException Hvis der opstår fejl under generering af QR-koden.
     * @throws IOException     Hvis der opstår en IO-fejl under skrivning af billedet.
     */
    public static void generateQRCodeImage(String text, int width, int height, String filePath)
            throws WriterException, IOException {

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);  // sort for true, hvid for false
            }
        }
        File outputFile = new File(filePath);
        ImageIO.write(image, "PNG", outputFile);
    }
}
