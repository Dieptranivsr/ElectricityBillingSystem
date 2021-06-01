/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ElectricityBillingSystem;
import java.awt.Color;
import javax.swing.JOptionPane;
import Nhom4.ConnectionProvider;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;


import java.util.Date;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author Dieptuantran
 */

public class Electrical extends javax.swing.JFrame {    
    private static String admin1="Member";

    private static String getAdmin1() {
        return admin1;
    }
    
    private static void setAdmin1(String admin1) {
        Electrical.admin1 = admin1;
    }

    private String chisodiencu="0";
    private boolean csdien = false;
    
    private boolean xacnhanChisodien (String ma, String mathang)
    {
        try
        {
            Connection con=ConnectionProvider.getCon();
            String query = "SELECT *FROM chisodien WHERE ma='"+ma+"' AND mathang ='"+mathang+"'";
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs=pstmt.executeQuery();
            
            if(rs.next())
            {
                return true;
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Dữ liệu nhập vào ô tìm kiếm bị lỗi");
        }
        return false;
    }
    
    private String getNgayDk (String ma)
    {
        try
        {
            Connection con=ConnectionProvider.getCon();
            String query = "SELECT ngaydangky FROM hotieuthu WHERE ma='"+ma+"'";
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs=pstmt.executeQuery();
            
            if(rs.next())
            {
                return rs.getString(1);
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Dữ liệu nhập vào ô tìm kiếm bị lỗi");
        }
        return null;
    }
    
    private String getChisocu (String ma, String mathang)
    {
        String mathang1 = xulymathang(mathang);
        try
        {
            Connection con=ConnectionProvider.getCon();
            String query = "SELECT chisomoi FROM chisodien WHERE ma='"+ma+"' AND mathang ='"+mathang1+"'";
            System.out.println(query);
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs=pstmt.executeQuery();
            
            if(rs.next())
            {
                return rs.getString(1);
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Dữ liệu nhập vào ô tìm kiếm bị lỗi");
        }
        return null;
    }
    
    private static String xulymathang(String mathang)
    {
        String mathang1;
        mathang1 = mathang.substring(4);
        if(Integer.parseInt(mathang1)==1)
            mathang1 = String.valueOf(Integer.parseInt(mathang.substring(0,4))-1)+"12";
        else if (Integer.parseInt(mathang1) < 11)
            mathang1 = mathang.substring(0,4)+"0"+String.valueOf(Integer.parseInt(mathang.substring(4))-1);
        else
            mathang1 = mathang.substring(0,4)+String.valueOf(Integer.parseInt(mathang.substring(4))-1);
        return mathang1;
    }
    
    private String getThang(String thang)
    {
        if(thang.equals("Jan"))
            return "01";
        else if(thang.equals("Feb"))
            return "02";
        else if(thang.equals("Mar"))
            return "03";
        else if(thang.equals("Apr"))
            return "04";
        else if(thang.equals("May"))
            return "05";
        else if(thang.equals("Jun"))
            return "06";
        else if(thang.equals("Jul"))
            return "07";
        else if(thang.equals("Aug"))
            return "08";
        else if(thang.equals("Sep"))
            return "09";
        else if(thang.equals("Oct"))
            return "10";
        else if(thang.equals("Nov"))
            return "11";
        else if(thang.equals("Dec"))
            return "12";
        return null;
    }
    
    private static boolean isNumeric(String strNum, int a) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
            if(strNum.length()!=a)
                return false;
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
    /** Creates new form Electrical */
    public Electrical(String admin) {
        this.admin1 = admin;
        
        initComponents();
    }
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        Date date = new Date();
        SpinnerDateModel sm = new SpinnerDateModel(date, null, null, Calendar.MONTH);
        jSpinner1 = new javax.swing.JSpinner(sm);
        Date date2 = new Date();
        SpinnerDateModel sm2 = new SpinnerDateModel(date2, null, null, Calendar.YEAR);
        jSpinner2 = new javax.swing.JSpinner(sm2);
        jLabel6 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();

        jButton5.setText("jButton5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Quản lý chỉ số tiêu thụ điện");

        jLabel2.setText("Mã khách hàng");

        jLabel3.setText("Mã tháng");

        jLabel4.setText("Chỉ số điện cũ");

        jLabel5.setText("Chỉ số điện mới");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/action_add.gif"))); // NOI18N
        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField4.setForeground(new java.awt.Color(102, 102, 102));
        jTextField4.setText("Nhập chỉ số điện mới");
        jTextField4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField4FocusLost(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/reply.gif"))); // NOI18N
        jButton2.setText("Chỉnh sửa");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/action_delete.gif"))); // NOI18N
        jButton3.setText("Xóa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Refresh_16x16.png"))); // NOI18N
        jButton4.setText("Làm mới giao diện");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/arrow_next.gif"))); // NOI18N
        jButton6.setText("Quay lại");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã tháng", "Mã khách hàng", "Tên khách hàng", "Chỉ số điện cũ", "Chỉ số điện mới"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTextField2.setForeground(new java.awt.Color(102, 102, 102));
        jTextField2.setText("Nhập mã tháng");
        jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField2FocusLost(evt);
            }
        });

        jTextField3.setForeground(new java.awt.Color(102, 102, 102));
        jTextField3.setText("Chỉ số điện cũ");
        jTextField3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField3FocusLost(evt);
            }
        });

        jTextField1.setForeground(new java.awt.Color(102, 102, 102));
        jTextField1.setText("Nhập mã khách hàng");
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField1FocusLost(evt);
            }
        });

        JSpinner.DateEditor de = new JSpinner.DateEditor(jSpinner1, "MMM");
        jSpinner1.setEditor(de);
        jSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner1StateChanged(evt);
            }
        });

        JSpinner.DateEditor de2 = new JSpinner.DateEditor(jSpinner2, "yyyy");
        jSpinner2.setEditor(de2);
        jSpinner2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner2StateChanged(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/d19dc4eb.png"))); // NOI18N
        jLabel6.setText("Lọc hiển thị");

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/excel.png"))); // NOI18N
        jButton7.setText("Cập nhật Excel");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(239, 239, 239))
            .addGroup(layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4))
                                .addGap(58, 58, 58)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                                    .addComponent(jTextField3)
                                    .addComponent(jTextField2)
                                    .addComponent(jTextField1))
                                .addGap(95, 95, 95))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton2)
                                    .addComponent(jButton1)
                                    .addComponent(jButton3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton7)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jButton6))
                                    .addComponent(jButton4))))))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel1)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton7)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton6))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /*    */
    private void jTextField4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField4FocusGained
        // TODO add your handling code here:
        if (jTextField4.getText().equals("Nhập chỉ số điện mới"))
        {
            jTextField4.setText("");
            jTextField4.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_jTextField4FocusGained

    private void jTextField4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField4FocusLost
        // TODO add your handling code here:
        if (jTextField4.getText().equals(""))
        {
            jTextField4.setText("Nhập chỉ số điện mới");
            jTextField4.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_jTextField4FocusLost

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String ma=jTextField1.getText();
        String mathang=jTextField2.getText();
        String chisocu=null;
        String chisomoi=jTextField4.getText();

        String time = getNgayDk(ma);
        if (Integer.valueOf(xulymathang(mathang))<Integer.valueOf(time.substring(0,4)+time.substring(5,7)))
        {
            JOptionPane.showMessageDialog(null, "Bạn đã nhập sai mã tháng. Vui lòng nhập lại");
        }
        else if(xulymathang(mathang).equals(time.substring(0,4)+time.substring(5,7)))
        {
            jTextField3.setText("0");
            if(Integer.valueOf(chisomoi)>0)
            {
                try{
                    Connection con=ConnectionProvider.getCon();
                    String query = "INSERT INTO chisodien(mathang,ma,chisocu,chisomoi) VALUES(?,?,?,?)";
                    PreparedStatement pstmt = con.prepareStatement(query);
                    pstmt.setString(1,mathang);
                    pstmt.setString(2,ma);
                    pstmt.setString(3,"0");
                    pstmt.setString(4,chisomoi);
            
                    int a=JOptionPane.showConfirmDialog(null, "Bạn có thực sự muốn lưu thông tin số điện này hay không?","Chọn lựa",JOptionPane.YES_NO_OPTION);
                    if (a != 0)
                    {
                        JOptionPane.showMessageDialog(null, "Hãy thử nhập thông tin mới.");
                    }
                    else{
                        pstmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Đã thêm mới thành công dữ liệu.");
                        Thread.sleep(1000);
                        setVisible(false);
                        new Electrical(admin1).setVisible(true);
                        JOptionPane.showMessageDialog(null, "Bạn có thể tiếp tục thêm mới dữ liệu khác.");
                    }
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null, "Thông tin này đã thực sự tồn tại hoặc bị lỗi nhập dữ liệu. Xin vui lòng thử nhập lại thông tin !");
                }
            }
            else
                JOptionPane.showMessageDialog(null, "Bạn đã nhập sai chỉ số điện của tháng mới. Vui lòng nhập lại");
        }
        else{
            chisocu = getChisocu(ma, mathang);
            if(chisocu!=null&&xacnhanChisodien(ma, mathang)==false)
            {
                if(Integer.valueOf(chisocu)<Integer.valueOf(chisomoi)&&Integer.valueOf(chisocu)>0)
                {
                    try{
                        Connection con=ConnectionProvider.getCon();
                        String query = "INSERT INTO chisodien(mathang,ma,chisocu,chisomoi) VALUES(?,?,?,?)";
                        PreparedStatement pstmt = con.prepareStatement(query);
                        pstmt.setString(1,mathang);
                        pstmt.setString(2,ma);
                        pstmt.setString(3,chisocu);
                        pstmt.setString(4,chisomoi);
            
                        int a=JOptionPane.showConfirmDialog(null, "Bạn có thực sự muốn lưu thông tin số điện này hay không?","Chọn lựa",JOptionPane.YES_NO_OPTION);
                        if (a != 0)
                        {
                            JOptionPane.showMessageDialog(null, "Hãy thử nhập thông tin mới.");
                        }
                        else{
                            pstmt.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Đã thêm mới thành công dữ liệu.");
                            Thread.sleep(1000);
                            setVisible(false);
                            new Electrical(admin1).setVisible(true);
                            JOptionPane.showMessageDialog(null, "Bạn có thể tiếp tục thêm mới dữ liệu khác.");
                        }
                    }
                    catch(Exception e)
                    {
                        JOptionPane.showMessageDialog(null, "Thông tin này đã thực sự tồn tại hoặc bị lỗi nhập dữ liệu. Xin vui lòng thử nhập lại thông tin !");
                    }
                }
                else
                    JOptionPane.showMessageDialog(null, "Bạn đã nhập sai chỉ số điện của tháng mới. Vui lòng nhập lại");
            }
            else
                JOptionPane.showMessageDialog(null, "Thông tin này đã thực sự tồn tại hoặc bị lỗi nhập dữ liệu. Xin vui lòng thử nhập lại thông tin !");
            }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String ma=jTextField1.getText();
        String mathang=jTextField2.getText();
        String chisocu=null;
        String chisomoi=jTextField4.getText();

        chisocu = getChisocu(ma, mathang);
        if(chisocu!=null)
        {
            if(Integer.valueOf(chisocu)<Integer.valueOf(chisomoi)&&Integer.valueOf(chisocu)>0)
            {
                try{
                    Connection con=ConnectionProvider.getCon();
                    String query = "UPDATE chisodien SET mathang=?,ma=?,chisomoi=? WHERE ma ='"+ma+"' AND mathang='"+mathang+"'";
                    PreparedStatement pstmt = con.prepareStatement(query);
                    pstmt.setString(1,mathang);
                    pstmt.setString(2,ma);
                    pstmt.setString(3,chisomoi);
                
                    int a=JOptionPane.showConfirmDialog(null, "Bạn có thực sự muốn cập nhật thông tin số điện này hay không?","Chọn lựa",JOptionPane.YES_NO_OPTION);
                    if (a != 0)
                    {
                        JOptionPane.showMessageDialog(null, "Bạn có thể chỉnh sửa lại thông tin.");
                    }
                    else{
                        pstmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Đã chỉnh sửa thành công tài khoản.");
                        Thread.sleep(1000);
                        setVisible(false);
                        new Electrical(admin1).setVisible(true);
                        JOptionPane.showMessageDialog(null, "Bạn có thể chỉnh sửa thêm thông tin nếu muốn.");
                    }
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null, "Tài khoản này đã thực sự tồn tại hoặc bị lỗi nhập dữ liệu. Xin vui lòng thử nhập lại thông tin !");
                }
            }
            else
                JOptionPane.showMessageDialog(null, "Bạn đã nhập sai chỉ số điện của tháng mới. Vui lòng nhập lại");
        }
        else
            JOptionPane.showMessageDialog(null, "Tài khoản này đã thực sự tồn tại hoặc bị lỗi nhập dữ liệu. Xin vui lòng thử nhập lại thông tin !");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String ma=jTextField1.getText();
        String mathang=jTextField2.getText();
        
        String chisomoi=jTextField4.getText();
        
        try{
            Connection con=ConnectionProvider.getCon();
            String query = "DELETE FROM chisodien WHERE ma='"+ma+"'AND mathang='"+mathang+"'";
            PreparedStatement pstmt = con.prepareStatement(query);
                
            int a=JOptionPane.showConfirmDialog(null, "Bạn có thực sự muốn xóa thông tin số của tài khoản này hay không?","Chọn lựa",JOptionPane.YES_NO_OPTION);
            if (a != 0)
            {
                JOptionPane.showMessageDialog(null, "Bạn đã chắc chắn muốn xóa thông tin !");
            }
            else{
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Đã xóa thành công tài khoản.");
                Thread.sleep(1000);
                setVisible(false);
                new Electrical(admin1).setVisible(true);
                JOptionPane.showMessageDialog(null, "Bạn có thể xóa thêm thông tin nếu muốn.");
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Thông tin chỉ số điện này đã bị lỗi nhập dữ liệu. Xin vui lòng thử nhập lại thông tin muốn xóa !");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        int a=JOptionPane.showConfirmDialog(null, "Bạn có thực sự muốn làm mới lại giao diện này hay không?","Chọn lựa",JOptionPane.YES_NO_OPTION);
        if (a == 0)
        {
            setVisible(false);
            new Electrical(admin1).setVisible(true);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        int a=JOptionPane.showConfirmDialog(null, "Bạn có thực sự muốn quay lại giao diện màn hình chính hay không?","Chọn lựa",JOptionPane.YES_NO_OPTION);
        if (a == 0)
        {
            setVisible(false);
            new Home(admin1).setVisible(true);
        }    
    }//GEN-LAST:event_jButton6ActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        
        try
        {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            
            Connection con=ConnectionProvider.getCon();
            String query = "SELECT chisodien.mathang, chisodien.ma, hotieuthu.hoten, chisodien.chisocu, chisodien.chisomoi FROM chisodien INNER JOIN hotieuthu ON chisodien.ma = hotieuthu.ma";
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs=pstmt.executeQuery();
            
            jTable1.setEnabled(true);
            while(rs.next())
            {
                model.insertRow(0, new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});
            }
            jTable1.setEnabled(false);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_formComponentShown

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained
        // TODO add your handling code here:if (jTextField4.getText().equals("Nhập chỉ số điện mới"))
        if (jTextField1.getText().equals("Nhập mã khách hàng"))
        {
            jTextField1.setText("");
            jTextField1.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_jTextField1FocusGained

    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost
        // TODO add your handling code here:
        if (jTextField1.getText().equals(""))
        {
            jTextField1.setText("Nhập mã khách hàng");
            jTextField1.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_jTextField1FocusLost

    private void jTextField2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusGained
        // TODO add your handling code here:
        if (jTextField2.getText().equals("Nhập mã tháng"))
        {
            jTextField2.setText("");
            jTextField2.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_jTextField2FocusGained

    private void jTextField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusLost
        // TODO add your handling code here:
        if (jTextField2.getText().equals(""))
        {
            jTextField2.setText("Nhập mã tháng");
            jTextField2.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_jTextField2FocusLost

    private void jTextField3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField3FocusGained
        // TODO add your handling code here:
        String ma=jTextField1.getText();
        String mathang=xulymathang(jTextField2.getText());
        
        if(isNumeric(ma,4)==true && isNumeric(mathang,6)==true)
        {
            String time = getNgayDk(ma);
            System.out.println("xulymathang(mathang) = "+mathang);
            System.out.println("time = "+time.substring(0,4)+time.substring(5,7));
            if(mathang.equals(time.substring(0,4)+time.substring(5,7)))
            {
                jTextField3.setText("0");
                
            }
            else
            {
                try
                {
                    Connection con=ConnectionProvider.getCon();
                    String query = "SELECT chisomoi FROM chisodien WHERE ma='"+ma+"' AND mathang ='"+mathang+"'";
                    PreparedStatement pstmt = con.prepareStatement(query);
                    ResultSet rs=pstmt.executeQuery();

                    if(rs.next())
                    {
                        jTextField3.setText(rs.getString(1));
                    }
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null, "Dữ liệu nhập vào ô tìm kiếm bị lỗi");
                }
            }
        }
    }//GEN-LAST:event_jTextField3FocusGained

    private void jTextField3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField3FocusLost
        // TODO add your handling code here:
        String ma=jTextField1.getText();
        String mathang=xulymathang(jTextField2.getText());
        
        if(isNumeric(ma,4)==true && isNumeric(mathang,6)==true)
        {
            String time = getNgayDk(ma);
            System.out.println("xulymathang(mathang) = "+mathang);
            System.out.println("time = "+time.substring(0,4)+time.substring(5,7));
            if(mathang.equals(time.substring(0,4)+time.substring(5,7)))
            {
                jTextField3.setText("0");
            }
            else
            {
                try
                {
                    Connection con=ConnectionProvider.getCon();
                    String query = "SELECT chisomoi FROM chisodien WHERE ma='"+ma+"' AND mathang ='"+mathang+"'";
                    PreparedStatement pstmt = con.prepareStatement(query);
                    ResultSet rs=pstmt.executeQuery();

                    if(rs.next())
                    {
                        jTextField3.setText(rs.getString(1));
                    }
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null, "Dữ liệu nhập vào ô tìm kiếm bị lỗi");
                }
            }
        }
    }//GEN-LAST:event_jTextField3FocusLost

    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner1StateChanged
        // TODO add your handling code here:
        String thang1 = jSpinner1.getValue().toString().substring(4, 7);
        String thang = null;
        String nam = jSpinner2.getValue().toString().substring(24, 28);
        thang = getThang(thang1);
        Connection con=ConnectionProvider.getCon();

        try
        {
            DefaultTableModel model1 = (DefaultTableModel) jTable1.getModel();
            
            String query = "SELECT chisodien.mathang, chisodien.ma, hotieuthu.hoten, chisodien.chisocu, chisodien.chisomoi FROM chisodien INNER JOIN hotieuthu ON chisodien.ma = hotieuthu.ma WHERE mathang ='"+nam+thang+"'";
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs=pstmt.executeQuery();
            
            jTable1.setEnabled(true);
            int r = jTable1.getRowCount();
            while(r-- > 0){
                model1.removeRow(r);
            }
            while(rs.next())
            {
                model1.insertRow(0, new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});
            }
            jTable1.setEnabled(false);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jSpinner1StateChanged

    private void jSpinner2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner2StateChanged
        // TODO add your handling code here:
        String thang1 = jSpinner1.getValue().toString().substring(4, 7);
        String thang = null;
        String nam = jSpinner2.getValue().toString().substring(24, 28);
        thang = getThang(thang1);
        Connection con=ConnectionProvider.getCon();
        try
        {
            DefaultTableModel model2 = (DefaultTableModel) jTable1.getModel();
            
            String query2 = "SELECT chisodien.mathang, chisodien.ma, hotieuthu.hoten, chisodien.chisocu, chisodien.chisomoi FROM chisodien INNER JOIN hotieuthu ON chisodien.ma = hotieuthu.ma WHERE mathang ='"+nam+thang+"'";
            PreparedStatement pstmt2 = con.prepareStatement(query2);
            ResultSet rs2=pstmt2.executeQuery();
            
            jTable1.setEnabled(true);
            int r = jTable1.getRowCount();
            while(r-- > 0){
                model2.removeRow(r);
            }
            while(rs2.next())
            {
                model2.insertRow(0, new Object[]{rs2.getString(1), rs2.getString(2), rs2.getString(3), rs2.getString(4), rs2.getString(5)});
            }
            jTable1.setEnabled(false);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jSpinner2StateChanged

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        JFileChooser jf = new JFileChooser();
        jf.setDialogTitle("Vui lòng chọn file excel chứa chỉ số điện tháng mới này để có thể cập nhật !");
        int result = jf.showOpenDialog(null);
        if(result == JFileChooser.APPROVE_OPTION)
        {
            String excelPath = jf.getSelectedFile().getAbsolutePath();
            File excelFile = new File(excelPath);
            FileInputStream fls = null;
            try {
                fls = new FileInputStream(excelFile);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Electrical.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            // Get workbook
        
            XSSFWorkbook workbook = null;
            try {
                workbook = new XSSFWorkbook(fls);
            } catch (IOException ex) {
                Logger.getLogger(Electrical.class.getName()).log(Level.SEVERE, null, ex);
            }
            XSSFSheet sheet = workbook.getSheetAt(0);
        
            Iterator<Row> rowIt = sheet.iterator();
        
            String chisocu = null;
            String mathang = null;
            int i = 0;
            Vector<String> v = new Vector<>();
            while (rowIt.hasNext()) {
                Row row = rowIt.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                //System.out.println("row.getCell(1)" + row.getCell(1));
                while (cellIterator.hasNext()) {
                    
                    Cell cell = cellIterator.next();
                    System.out.println(cell.toString().replace(".0", "") + " ");
                    if(i>0)
                        v.add(cell.toString().replace(".0", ""));
                }
                if (i==0)
                {
                    i++;
                    continue;
                }
                //System.out.println(v.get(0)+v.get(1)+v.get(2)+v.get(3));
                //System.out.println(v.get(0)+" "+v.get(1)+" "+v.get(2));
                chisocu = getChisocu("0"+v.get(1), v.get(0));
                //System.out.println(chisocu);
                try{
                    Connection con=ConnectionProvider.getCon();
                    String query = "INSERT INTO chisodien(mathang,ma,chisocu,chisomoi) VALUES(?,?,?,?)";
                    PreparedStatement pstmt = con.prepareStatement(query);
                    pstmt.setString(1,v.get(0));
                    pstmt.setString(2,"0"+v.get(1));
                    pstmt.setString(3,chisocu);
                    pstmt.setString(4,v.get(2));
            
                    pstmt.executeUpdate();
                    v.removeAllElements();
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null, "Thông tin này đã thực sự tồn tại hoặc bị lỗi nhập dữ liệu. Xin vui lòng thử nhập lại thông tin !");
                }
                
                System.out.println("");
            }
            try {
                workbook.close();
            } catch (IOException ex) {
                Logger.getLogger(Electrical.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                fls.close();
            } catch (IOException ex) {
                Logger.getLogger(Electrical.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            setVisible(false);
            new Electrical(admin1).setVisible(true);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Electrical.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Electrical.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Electrical.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Electrical.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Electrical(admin1).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables

}
