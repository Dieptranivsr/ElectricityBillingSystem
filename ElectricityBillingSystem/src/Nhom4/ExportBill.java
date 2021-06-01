/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nhom4;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.scenario.effect.ImageData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 *
 * @author Dieptuantran
 */
public class ExportBill {
    private static final String FILE_NAME = "D:/Kihoc20202/LapTrinhNangCao/hoadon/itext.pdf";
    private static final String IMAGE = "D:/Kihoc20202/LapTrinhNangCao/hoadon/logoevn.png";
    public static void main(String[] args) {
        writeUsingIText();
    }

    private static void writeUsingIText() {

        Document document = new Document();

        try {

            PdfWriter.getInstance(document, new FileOutputStream(new File(FILE_NAME)));

            //open
            document.open();

            Paragraph p = new Paragraph();
            p.add("This is my paragraph 1");
            p.setAlignment(Element.ALIGN_CENTER);

            document.add(p);

            Paragraph p2 = new Paragraph();
            p2.add("This is my paragraph 2"); //no alignment

            document.add(p2);

            Font f = new Font();
            f.setStyle(Font.BOLD);
            f.setSize(18);

            document.add(new Paragraph("This is my paragraph 3", f));

            Image image = Image.getInstance(IMAGE);
            image.setAlignment(Element.ALIGN_LEFT);
            document.add(image);
            
            Paragraph p3 = new Paragraph();
            p3.add("This is my paragraph 4");
            p3.setAlignment(Element.ALIGN_RIGHT);
            document.add(p3);
            //close
            document.close();
            
            System.out.println("Done");
         
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}