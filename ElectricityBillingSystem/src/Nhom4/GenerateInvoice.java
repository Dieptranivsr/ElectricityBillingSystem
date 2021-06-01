/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nhom4;
 
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
 
public class GenerateInvoice {
    private BaseFont bfBold;
    private BaseFont bf;
    private int pageNumber = 0;
 
    public static void main(String[] args) {
        String pdfFilename = "";
        GenerateInvoice generateInvoice = new GenerateInvoice();

        pdfFilename = "bacdeg.pdf";
        generateInvoice.createPDF(pdfFilename);
    }
 
    private void createPDF (String pdfFilename){
        Document doc = new Document();
        PdfWriter docWriter = null;
        initializeFonts();
        try {
            String path = "D:/Kihoc20202/LapTrinhNangCao/hoadon/" + pdfFilename;
            docWriter = PdfWriter.getInstance(doc , new FileOutputStream(path));
            doc.addAuthor("betterThanZero");
            doc.addCreationDate();
            doc.addProducer();
            doc.addCreator("MySampleCode.com");
            doc.addTitle("Invoice");
            doc.setPageSize(PageSize.LETTER);
 
            doc.open();
            PdfContentByte cb = docWriter.getDirectContent();
    
            boolean beginPage = true;
            int y = 0;
    
            if(beginPage){
                beginPage = false;
                generateLayout(doc, cb); 
                generateHeader(doc, cb);
                generateCustomer(doc, cb);
                generateElectical(doc, cb);
                generateExportElec(doc, cb);
                generateHelp(doc, cb);
                
            }
        }
        catch (DocumentException dex)
        {
            dex.printStackTrace();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if (doc != null)
            {
                doc.close();
            }
            if (docWriter != null)
            {
                docWriter.close();
            }
        }
    }
 
private void generateLayout(Document doc, PdfContentByte cb)  {
    try {
        cb.setLineWidth(1f);
 
        // Invoice Customer
        cb.moveTo(45,600);
        cb.lineTo(370,600);
        cb.moveTo(45,580);
        cb.lineTo(370,580);
        cb.moveTo(45,560);
        cb.lineTo(370,560);
        cb.moveTo(45,540);
        cb.lineTo(370,540);
        cb.moveTo(45,520);
        cb.lineTo(370,520);
        cb.moveTo(45,500);
        cb.lineTo(370,500);
        cb.stroke();
        
        // Invoice Detail Customer box Text Headings
        createHeadings(cb,60,585,"Khach hang");
        createHeadings(cb,60,565,"Dia chi");
        createHeadings(cb,60,545,"Muc dich su dung dien");
        createHeadings(cb,60,525,"So ho su dung dien");
        createHeadings(cb,60,505,"He so nhan");
        
        cb.moveTo(45,370);
        cb.lineTo(370,370);
        cb.moveTo(45,350);
        cb.lineTo(370,350);
        cb.moveTo(45,330);
        cb.lineTo(370,330);
        cb.moveTo(45,310);
        cb.lineTo(370,310);
        cb.moveTo(45,290);
        cb.lineTo(370,290);
        cb.moveTo(45,270);
        cb.lineTo(370,270);
        cb.moveTo(45,250);
        cb.lineTo(370,250);
        cb.moveTo(45,230);
        cb.lineTo(370,230);
        cb.stroke();
 
        // Invoice Detail box Text Headings 
        createTitles(cb,60,380,"Tong so tien thanh toan",16);
 
        //add the images
        Image companyLogo = Image.getInstance("D:/Kihoc20202/LapTrinhNangCao/hoadon/logoevn.png");
        companyLogo.setAbsolutePosition(35,680);
        companyLogo.scalePercent(30);
        doc.add(companyLogo);
        
        Image companyLogo2 = Image.getInstance("D:/Kihoc20202/LapTrinhNangCao/hoadon/mahd.png");
        companyLogo2.setAbsolutePosition(400,500);
        companyLogo2.scalePercent(90);
        doc.add(companyLogo2);
 
    }
 
  catch (DocumentException dex){
   dex.printStackTrace();
  }
  catch (Exception ex){
   ex.printStackTrace();
  }
 
 }
  
    private void generateHeader(Document doc, PdfContentByte cb)  {
        try {
            createHeadings(cb,115,750,"Cong ty Dien luc Hoan Kiem");
            createHeadings(cb,115,735,"69C, Dinh Tien Hoang, Phuong Ly Thai To,");      createHeadings(cb,300, 735, "Mau so: 01GTK01001");
            createHeadings(cb,115,720,"Quan Hoan Kiem");                                createHeadings(cb,300,720,"Ky hieu: AA/13E");
            createHeadings(cb,115,705,"MST: 0100101114-001");                           createHeadings(cb,300,705,"So: 11111111");
            createHeadings(cb,115,690,"Hotline: 1900 1288");
    
            //createHeadings(cb,482,743,"ABC0001");
            //createHeadings(cb,482,723,"123456");
            //createHeadings(cb,482,703,"09/26/2012");
        
            createTitles(cb, 54, 635, "HOA DON GTGT (TIEN DIEN)", 23);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
  
    private void generateCustomer(Document doc, PdfContentByte cb)  {
        try {
            createHeadings(cb,180,585,"Tran Thai Linh");
            createHeadings(cb,180,565,"Hang Khoai, Hoan Kiem, Ha Noi"); 
            createHeadings(cb,180,545,"Sinh hoat");
            createHeadings(cb,180,525,"1");
            createHeadings(cb,180,505,"1");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
    private void generateElectical(Document doc, PdfContentByte cb)  {
        try {
            createTitles(cb,60,465,"Dien nang tieu thu",16);
            createTitles(cb,60,448,"Ky hoa don: Thang 5/2019, tu 15/04/2019 den 15/05/2019 (30 ngay)",10);
            createHeadings(cb,60,430,"Ma khach hang(So cong to)");
            createHeadings(cb,180,430,"Chi so moi"); 
            createHeadings(cb,240,430,"Chi so cu");
            createHeadings(cb,300,430,"Dien nang tieu thu");
            
            createHeadings(cb,60,415,"20148323");
            createHeadings(cb,180,415,"3997"); 
            createHeadings(cb,240,415,"3407");
            createHeadings(cb,300,415,"590");          
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
    private void generateHelp(Document doc, PdfContentByte cb)  {
        try {
            createTitles(cb,408,440,"Thong tin lien he",16);
            createTitles(cb,408,420,"Trung tam CSKH EVN HANOI",10);
            createHeadings(cb,408,405,"69 Dinh Tien Hoang, Phuong Ly Thai To,");
            createHeadings(cb,408,393,"Hoan Kiem, Ha Noi"); 
            createHeadings(cb,408,379,"Hotline 1900 1288");
            createHeadings(cb,408,367,"http://cskh.evnhanoi.com.vn");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
 private void generateDetail(Document doc, PdfContentByte cb, int index, int y)  {
  DecimalFormat df = new DecimalFormat("0.00");
   
  try {
 
   createContent(cb,95,y,String.valueOf(index+1),PdfContentByte.ALIGN_RIGHT);
   createContent(cb,135,y, "ITEM" + String.valueOf(index+1),PdfContentByte.ALIGN_LEFT);
   createContent(cb,190,y, "Product Description - SIZE " + String.valueOf(index+1),PdfContentByte.ALIGN_LEFT);
    
   double price = Double.valueOf(df.format(Math.random() * 10));
   double extPrice = price * (index+1) ;
   createContent(cb,400,y, df.format(price),PdfContentByte.ALIGN_RIGHT);
   createContent(cb,500,y, df.format(extPrice),PdfContentByte.ALIGN_RIGHT);
    
  }
 
  catch (Exception ex){
   ex.printStackTrace();
  }
 
 }
    private void generateExportElec(Document doc, PdfContentByte cb)  {
        try {
            createHeadings(cb,60,357,"Bac tieu thu");
            createHeadings(cb,122,357,"Don gia (Dong/KWh)");
            createHeadings(cb,218,357,"San luong (KWh)");
            createHeadings(cb,296,357,"Thanh tien (Dong)");
            
            createHeadings(cb,70,337,"Bac 1");
            createHeadings(cb,70,317,"Bac 2");
            createHeadings(cb,70,297,"Bac 3");
            createHeadings(cb,70,277,"Bac 4");
            createHeadings(cb,70,257,"Bac 5");
            createHeadings(cb,70,237,"Bac 6");
            
            createHeadings(cb,60,215,"Tong dien nang tieu thu (kWh)");
            createHeadings(cb,60,195,"Tong tien dien chua thue (dong) ");
            createHeadings(cb,60,175,"Thue suat GTGT");
            createHeadings(cb,330,175,"10 %");
            createHeadings(cb,60,155,"Thue GTGT (dong");
            createHeadings(cb,60,135,"Tong so tien thanh toan (dong)");
            }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
    private void createHeadings(PdfContentByte cb, float x, float y, String text){
        cb.beginText();
        cb.setFontAndSize(bfBold, 8);
        cb.setTextMatrix(x,y);
        cb.showText(text.trim());
        cb.endText(); 
    }
 
    private void createTitles(PdfContentByte cb, float x, float y, String text, int size){
        cb.beginText();
        cb.setFontAndSize(bfBold, size);
        cb.setTextMatrix(x,y);
        cb.showText(text.trim());
        cb.endText(); 
    }
  
 private void printPageNumber(PdfContentByte cb){
 
 
  cb.beginText();
  cb.setFontAndSize(bfBold, 8);
  cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, "Page No. " + (pageNumber+1), 570 , 25, 0);
  cb.endText(); 
   
  pageNumber++;
   
 }
  
 private void createContent(PdfContentByte cb, float x, float y, String text, int align){
 
 
  cb.beginText();
  cb.setFontAndSize(bf, 8);
  cb.showTextAligned(align, text.trim(), x , y, 0);
  cb.endText(); 
 
 }
 
 private void initializeFonts(){
 
 
  try {
   bfBold = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
   bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
 
  } catch (DocumentException e) {
   e.printStackTrace();
  } catch (IOException e) {
   e.printStackTrace();
  }
 
 
 }
 
}