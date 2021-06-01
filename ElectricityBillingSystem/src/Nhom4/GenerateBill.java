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

import javax.swing.JOptionPane;
import Nhom4.ConnectionProvider;
import Nhom4.GenerateBill;
import com.itextpdf.text.BaseColor;
import java.awt.Font;
import java.io.FileInputStream;
import java.sql.*;

import java.util.Date;  
import javax.print.Doc;
import javax.print.DocPrintJob;
import javax.print.SimpleDoc;

/**
 *
 * @author Dieptuantran
 */
public class GenerateBill {
    private BaseFont bfBold;
    private BaseFont bf;
    private int pageNumber = 0;
 
    public static void main(String[] args) {
        String pdfFilename1 = "";
        GenerateBill generateBill = new GenerateBill();

        pdfFilename1 = "hoadonmau.doc";
        generateBill.createPDF("0112", "202103");
    }
 
    public void createPDF (String makh, String mathang){
        String pdfFilename = kiemtrahoadon(makh, mathang)+".pdf";
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
            
            //BaseFont bFont = BaseFont.createFont("E:\2.ProgramSoftware\VietFontsWeb1_ttf\vuArial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED));
            
            PdfContentByte cb = docWriter.getDirectContent();
    
            boolean beginPage = true;
            int y = 0;
    
            if(beginPage){
                beginPage = false;
                generateLayout(doc, cb); 
                generateHeader(doc, cb);
                generateHeaderTable(doc, cb);
                generateDetailInfo(doc, cb, makh, mathang);
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
 
            // Invoice Header box layout
            cb.rectangle(45,360,395,260);  // x,yduoi len // dai,rong
            
            //cb.moveTo(45,560);
            //cb.lineTo(440,560);//1
            cb.moveTo(45,600);         // Ke ngang
            cb.lineTo(440,600);//2
            cb.moveTo(45,500);
            cb.lineTo(180,500);//3
            cb.moveTo(440,460);
            cb.lineTo(440,460);//4
            cb.moveTo(180,440);
            cb.lineTo(440,440);
            cb.moveTo(180,420);
            cb.lineTo(440,420);
            cb.moveTo(180,400);
            cb.lineTo(440,400);
            cb.moveTo(45,380);
            cb.lineTo(440,380);
            //cb.moveTo(45,320);
            //cb.lineTo(440,320);
            
            //cb.moveTo(45,320);//1
            //cb.lineTo(45,560);
            cb.moveTo(85,500);//2        // Ke doc
            cb.lineTo(85,620);
            cb.moveTo(135,500);//3
            cb.lineTo(135,620);
            cb.moveTo(180,380);//4
            cb.lineTo(180,620);
            cb.moveTo(225,420);//5
            cb.lineTo(225,620);
            cb.moveTo(300,420);//6
            cb.lineTo(300,620);
            cb.moveTo(350,380);//7
            cb.lineTo(350,620);
            cb.stroke();
 
            //add the image0s
            Image companyLogo = Image.getInstance("D:/Kihoc20202/LapTrinhNangCao/hoadon/logo_NPC.png");
            companyLogo.setAbsolutePosition(35,734);
            companyLogo.scalePercent(35);
            doc.add(companyLogo);
        }catch (DocumentException dex){
            dex.printStackTrace();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
  
    private void generateHeader(Document doc, PdfContentByte cb)  {
        try {
            createTitles(cb, 180, 745, "HÓA ĐƠN GTGT (TIỀN ĐIỆN)", 22);
            createHeadings(cb,250,730,"(Liên 2: Giao Khách hàng)");      createHeadings(cb,450, 725, "Mẫu số: 01GTK01001");
            createHeadings(cb,220,715,"Kỳ : 1");
            createHeadings(cb,260,715,"Từ ngày ");
            createHeadings(cb,360,715,"Đến ngày");
            
            createTitles(cb,40,700,"Công ty Điện lực Gia Viễn", 14);
            createHeadings(cb,40,688,"Địa chỉ: Số 17, thị trấn Me, huyện Gia Viễn, tỉnh Ninh Bình");              createHeadings(cb,200,676,"MST: ");                     
            createHeadings(cb,40,676,"Điện thoại: 0211 369639");            createHeadings(cb,200,676,"MST: 0100100427-027");            createHeadings(cb,300,676,"ĐT sửa chữa: 0211 3862886");
            createHeadings(cb,40,664,"Tên khách hàng:");
            createHeadings(cb,40,652,"Địa chỉ:");
            createHeadings(cb,40,640,"Điện thoại:");     createHeadings(cb,200,640,"MST: ");     /*createHeadings(cb,300,642,"So Cong to: ");*/  createHeadings(cb,300,640,"Số hộ: 1");
    
            createHeadings(cb,215,580,"1");
            
            createHeadings(cb,55,482,"Ngày");
            createHeadings(cb,92,482,"tháng");
            createHeadings(cb,130,482,"năm");
            createHeadings(cb,80,470,"Bên bán điện");
            createHeadings(cb,80,400,"Tạ Ngọc Minh");
            
            createHeadings(cb,460,560,"Mã KH");
            createHeadings(cb,460,535,"Mã HD");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
    private void generateHeaderTable(Document doc, PdfContentByte cb)  {
        try {
            createHeadings(cb,55,604,"Bộ CS");
            createHeadings(cb,90,604,"Chỉ số mới");
            createHeadings(cb,140,604,"Chỉ số cũ");
            createHeadings(cb,185,604,"HS nhân");
            createHeadings(cb,240,604,"Điện năng TT");
            createHeadings(cb,310,604,"Đơn giá");
            createHeadings(cb,380,604,"Thành tiền");
            
            createHeadings(cb,185,424,"Cộng");
            createHeadings(cb,185,404,"Thuế suất GTGT: 10% Thuế GTGT");
            createHeadings(cb,185,384,"Tổng cộng tiền thanh toán");
            createHeadings(cb,50,364,"Số tiền viết bằng chữ:");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
    private void generateDetailInfo(Document doc, PdfContentByte cb, String mahd, String ma)  {
        
        ResultSet rs = getData(mahd, ma);
        
        try {
            //System.out.println(xulymathang("201201"));
            
            String mathang1 = xulymathang(rs.getString(6));
            createHeadings(cb,300,715,"09/"+rs.getString(6).substring(4,6));
            createHeadings(cb,400,715,"09/"+mathang1.substring(4,6));
            
            createHeadings(cb,120,664,rs.getString(3));
            createHeadings(cb,90,652,rs.getString(4));
            createHeadings(cb,90,640,rs.getString(5));
            
            createHeadings(cb,50,580,"BT");//sdmoi
            
            createHeadings(cb,145,580,rs.getString(7));//sdcu
            createHeadings(cb,95,580,rs.getString(8));//sdmoi
            
            createHeadings(cb,240,580,rs.getString(10));//ldtt
            
            int ldtt1 = Integer.parseInt(rs.getString(10));
            double tongtien=0,tien1=0,tien2=0,tien3=0,tien4=0,tien5=0,tien6=0;
            
            if(ldtt1 < 50)
            {
                createHeadings(cb, 270, 560, String.valueOf(ldtt1));
                createHeadings(cb,320,560,"1678");
                tien1 = ldtt1*1678;
                createHeadings(cb,390,560,String.valueOf((int)tien1));
            }else if (ldtt1>=50)
            {
                createHeadings(cb,270,560,"50");
                createHeadings(cb,320,560,"1678");
                createHeadings(cb,390,560,"83900");
                tien1 = 89300;
            }
            
            if (ldtt1 >50 && ldtt1<100)
            {
                createHeadings(cb, 270, 545, String.valueOf(ldtt1-50));
                createHeadings(cb,320,545,"1734");
                tien2 = (ldtt1-50)*1734;
                createHeadings(cb,390,545,String.valueOf((int)tien2));
            }else if(ldtt1>=100)
            {
                createHeadings(cb,270,545,"50");
                createHeadings(cb,320,545,"1734");
                createHeadings(cb,390,545,"85700");
                tien2 = 85700;
            }
            
            if (ldtt1 >100 && ldtt1<200)
            {
                createHeadings(cb, 270, 530, String.valueOf(ldtt1-100));
                createHeadings(cb,320,530,"2014");
                tien3 = (ldtt1-100)*2014;
                createHeadings(cb,390,530,String.valueOf((int)tien3));
            }else if(ldtt1>=200)
            {
                createHeadings(cb,270,530,"100");
                createHeadings(cb,320,530,"2014");
                createHeadings(cb,390,530,"201400");
                tien3 = 201400;
            }
            
            if (ldtt1 >200 && ldtt1<300){
                createHeadings(cb, 270, 530, String.valueOf(ldtt1-200));
                createHeadings(cb,320,530,"2535");
                tien4 = (ldtt1-200)*2535;
                createHeadings(cb,390,530,String.valueOf((int)tien4));
            }else if(ldtt1>=300)
            {
                createHeadings(cb,270,515,"100");
                createHeadings(cb,320,515,"2535");
                createHeadings(cb,390,515,"253600");
                tien4 = 253600;
            }
            
            if (ldtt1 >300 && ldtt1<400){
                createHeadings(cb, 270, 500, String.valueOf(ldtt1-300));
                createHeadings(cb,320,500,"2834");
                tien5 = (ldtt1-300)*2834;
                createHeadings(cb,390,500,String.valueOf((int)tien5));
            }else if(ldtt1>=400)
            {
                createHeadings(cb,270,500,"100");
                createHeadings(cb,320,500,"2834");
                createHeadings(cb,390,500,"283400");
                tien5 = 283400;
            }
            
            if (ldtt1 >400 ){
                createHeadings(cb, 270, 485, String.valueOf(ldtt1-400));
                createHeadings(cb,320,485,"2927");
                tien6 = (ldtt1-400)*2927;
                createHeadings(cb,390,485,String.valueOf((int)tien6));
            }
            
            createHeadings(cb,270,424,rs.getString(10));
            tongtien = (tien1+tien2+tien3+tien4+tien5+tien6);
            createHeadings(cb,390,424,String.valueOf((int)tongtien));
            createHeadings(cb,390,404,String.valueOf((int) Math.ceil(tongtien*0.1*1000 / 1000)));
            createHeadings(cb,390,384,String.valueOf((int) Math.ceil(tongtien*1.1*1000 / 1000)));
            
            String socandoc = ReadNummers.exportNum(String.valueOf((int) Math.ceil(tongtien*1.1*1000 / 1000)));
            String firstletter = socandoc.substring(0,1);
            socandoc = firstletter.toUpperCase() + socandoc.substring(1);
            createHeadings(cb,140,364,socandoc + "đồng");
            
            //Date date = new Date();
            //System.out.println(java.time.LocalDate.now());
            String time = java.time.LocalDate.now().toString();
            createHeadings(cb,78,482,time.substring(8,10));
            createHeadings(cb,116,482,time.substring(5,7));
            createHeadings(cb,150,482,time.substring(0,4));
            
            createHeadings(cb,460,548,rs.getString(2));
            createHeadings(cb,460,523,rs.getString(1));
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    } 
    
    private void createHeadings(PdfContentByte cb, float x, float y, String text) throws DocumentException, IOException{
        cb.beginText();
        String abc = "E:/2.ProgramSoftware/VietFontsWeb1_ttf/vuArial.ttf";
        cb.setFontAndSize(BaseFont.createFont(abc, BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 8);
        cb.setTextMatrix(x,y);
        cb.showText(text.trim());
        cb.endText(); 
    }
 
    private void createTitles(PdfContentByte cb, float x, float y, String text, int size) throws DocumentException, IOException{
        cb.beginText();
        String abc = "E:/2.ProgramSoftware/VietFontsWeb1_ttf/vuArial.ttf";
        cb.setFontAndSize(BaseFont.createFont(abc, BaseFont.IDENTITY_H, BaseFont.EMBEDDED), size);
        cb.setTextMatrix(x,y);
        cb.showText(text.trim());
        cb.endText(); 
    }

    private void printPageNumber(PdfContentByte cb) throws DocumentException, IOException{
        cb.beginText();
        String abc = "E:/2.ProgramSoftware/VietFontsWeb1_ttf/vuArial.ttf";
        cb.setFontAndSize(BaseFont.createFont(abc, BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 8);
        cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, "Page No. " + (pageNumber+1), 570 , 25, 0);
        cb.endText(); 
        
        pageNumber++;
    }

    private void createContent(PdfContentByte cb, float x, float y, String text, int align) throws DocumentException, IOException{
        
        cb.beginText();
        String abc = "E:/2.ProgramSoftware/VietFontsWeb1_ttf/vuArial.ttf";
        cb.setFontAndSize(BaseFont.createFont(abc, BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 8);
        cb.showTextAligned(align, text.trim(), x , y, 0);
        cb.endText(); 
    }

    private void initializeFonts(){
        try {
            //bfBold = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            //bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            String abc = "E:/2.ProgramSoftware/VietFontsWeb1_ttf/vuArial.ttf";
            BaseFont.createFont(abc, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String kiemtrahoadon(String ma, String mathang){
        if (ma == null || mathang == null) {
            return null;
        }
        
        try{
            Connection con=ConnectionProvider.getCon();
            String query = "SELECT hoadon.mahd FROM hoadon WHERE ma='"+ma+"' AND mathang='"+mathang+"'";
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs=pstmt.executeQuery();
            
            if (rs.next())
            {
                return rs.getString(1);
            }
            //return false;
        }
        catch(Exception e)
        {
            return null;
        }
        return null;
    }
    
    private ResultSet getData(String makh, String mathang)
    {
        //String result[] = new String[2];
        
        ResultSet rs=null;
        String mahd1 = kiemtrahoadon(makh,mathang);
        if(mahd1!=null){
            try{
            Connection con=ConnectionProvider.getCon();
            String query = "SELECT hoadon.mahd, hotieuthu.ma, hotieuthu.hoten, hotieuthu.diachi, hotieuthu.sodienthoai, hoadon.mathang, chisodien.chisocu, chisodien.chisomoi, hoadon.loaidien, hoadon.ldtt, hoadon.tien "
                    + "FROM ((hotieuthu INNER JOIN chisodien ON hotieuthu.ma = chisodien.ma) INNER JOIN hoadon ON hotieuthu.ma = hoadon.ma) "
                    + "WHERE hotieuthu.ma='"+makh+"' AND chisodien.mathang='"+mathang+"' AND hoadon.mahd='"+mahd1+"'";
            PreparedStatement pstmt = con.prepareStatement(query);
                
            rs=pstmt.executeQuery();
            if (rs.next())
            {
                return rs;
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Việc tạo hóa đơn bị lỗi, xin vui lòng thực hiện đúng lệnh !");
        }
        }
        return rs;
    }
    
    private String xulymathang(String mathang)
    {
        String mathang1;
        mathang1 = mathang.substring(4);
        //System.out.println(Integer.parseInt(mathang1));
        if(Integer.parseInt(mathang1)==12)
            mathang1 = String.valueOf(Integer.parseInt(mathang.substring(0,4))+1)+"01";
        else if (Integer.parseInt(mathang1) < 9)
            mathang1 = mathang.substring(0,4)+"0"+String.valueOf(Integer.parseInt(mathang.substring(4))+1);
        else
            mathang1 = mathang.substring(0,4)+String.valueOf(Integer.parseInt(mathang.substring(4))+1);
        return mathang1;
    }
}
