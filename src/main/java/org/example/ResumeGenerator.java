package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ResumeGenerator {

    public static void main(String[] args) {
        // Resmin dosya adını sabit olarak tanımlıyoruz. (src/main/resources içinde olmalı)
        final String IMAGE_NAME = "nazli_gursoy.png";

        try (PDDocument document = new PDDocument()) {

            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream content = new PDPageContentStream(document, page);

            // ----------------------------------------------------------------
            // 1. Resim Yükleme (Classpath'ten Güvenli Okuma)
            // ----------------------------------------------------------------

            PDImageXObject image = null;

            // Classpath okuma ve readAllBytes hatasını gideren döngü
            try (InputStream is = ResumeGenerator.class.getResourceAsStream("/" + IMAGE_NAME)) {
                if (is != null) {

                    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                    int nRead;
                    byte[] data = new byte[4096];

                    while ((nRead = is.read(data, 0, data.length)) != -1) {
                        buffer.write(data, 0, nRead);
                    }
                    buffer.flush();
                    byte[] bytes = buffer.toByteArray();

                    image = PDImageXObject.createFromByteArray(document, bytes, IMAGE_NAME);
                    System.out.println("✅ Resim, 'resources' klasöründen başarıyla yuklendi.");
                } else {
                    System.err.println("❌ KRITIK HATA: Resim kaynağı Classpath'te bulunamadı. Lutfen dosyanin src/main/resources icinde ve adinin " + IMAGE_NAME + " oldugundan emin olun.");
                    return;
                }
            } catch (IOException e) {
                System.err.println("❌ HATA: Resim yuklenirken bir I/O hatasi olustu: " + e.getMessage());
                return;
            }

            // Resmi PDF'e çiz
            content.drawImage(image, 50, 650, 100, 100);

            // ----------------------------------------------------------------
            // 2. PDF İçeriği (Türkçe karakterler İngilizce karşılıklarıyla yazıldı)
            // ----------------------------------------------------------------

            // Başlık "OZGECMIS"
            content.beginText();
            content.setFont(PDType1Font.HELVETICA_BOLD, 24);
            content.newLineAtOffset(200, 750);
            content.showText("OZGECMIS");
            content.endText();

            // Altına bir çizgi
            content.setStrokingColor(Color.DARK_GRAY);
            content.moveTo(50, 640);
            content.lineTo(550, 640);
            content.stroke();

            int yPos = 610;

            // --- KISISEL BILGILER ---
            content.beginText();
            content.setFont(PDType1Font.HELVETICA_BOLD, 14);
            content.newLineAtOffset(50, yPos);
            content.showText("KISELI BILGILER"); // KISISEL yerine KISELI
            content.endText();

            yPos -= 20;
            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 12);
            content.newLineAtOffset(50, yPos);
            content.showText("Ad Soyad: Nazli Gursoy");
            content.newLineAtOffset(0, -15);
            content.showText("Meslek: Paramedik");
            content.newLineAtOffset(0, -15);
            content.showText("E-posta: nazligursoy@gmail.com");
            content.newLineAtOffset(0, -15);
            content.showText("Telefon: 0511 111 11 11");
            content.endText();

            yPos -= 80;

            // --- IS DENEYIMLERI (3 Adet) ---
            content.beginText();
            content.setFont(PDType1Font.HELVETICA_BOLD, 14);
            content.newLineAtOffset(50, yPos);
            content.showText("IS DENEYIMLERI (3 Adet)");
            content.endText();

            yPos -= 20;
            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 12);
            content.newLineAtOffset(50, yPos);
            content.showText("- Paramedik, Ozel Hayat Hastanesi (2019 - 2021)");
            content.newLineAtOffset(0, -15);
            content.showText("- Paramedik, Devlet Acil Servisi (2021 - 2023)");
            content.newLineAtOffset(0, -15);
            content.showText("- Paramedik, Sehir Hastanesi (2023 - Gunumuz)"); // Sehir ve Gunumuz duzeltildi
            content.endText();

            yPos -= 80;

            // --- YETENEKLER ---
            content.beginText();
            content.setFont(PDType1Font.HELVETICA_BOLD, 14);
            content.newLineAtOffset(50, yPos);
            content.showText("YETENEKLER");
            content.endText();

            yPos -= 20;
            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 12);
            content.newLineAtOffset(50, yPos);
            content.showText("- Ilk yardim ve acil mudahale uzmanligi"); // Ilk duzeltildi
            content.newLineAtOffset(0, -15);
            content.showText("- EKG, oksijen ve damar yolu uygulamalari");
            content.newLineAtOffset(0, -15);
            content.showText("- Stres altinda hizli karar verme");
            content.endText();

            // Alt çizgi ve imza alanı
            content.setStrokingColor(Color.GRAY);
            content.moveTo(50, 100);
            content.lineTo(550, 100);
            content.stroke();

            content.beginText();
            content.setFont(PDType1Font.HELVETICA_OBLIQUE, 10);
            content.newLineAtOffset(50, 85);
            content.showText("Bu ozgecmis PDFBox kutuphanesi kullanilarak ve Google Gemini yardimiyla otomatik olusturulmustur.");
            content.endText();

            content.close();
            document.save("ozgecmis.pdf");

            System.out.println("✅ Profesyonel PDF basariyla olusturuldu: ozgecmis.pdf");

        } catch (IOException e) {
            System.err.println("❌ Kritik Hata: PDF olusturulurken genel bir I/O hatasi olustu.");
            e.printStackTrace();
        }
    }
}