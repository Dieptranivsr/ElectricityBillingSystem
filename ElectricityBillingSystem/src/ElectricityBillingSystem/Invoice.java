/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ElectricityBillingSystem;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JOptionPane;

import Nhom4.ConnectionProvider;
import java.sql.*;
import Nhom4.GenerateBill;
import java.io.FileInputStream;
import javax.print.Doc;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.SpinnerDateModel;

import java.util.Date;
import java.util.Calendar;
import javax.swing.JSpinner;
/**
 *
 * @author Dieptuantran
 */
public class Invoice extends javax.swing.JFrame {
    public static String admin1="Member";

    private static String getAdmin1() {
        return admin1;
    }
    
    private static void setAdmin1(String admin1) {
        Invoice.admin1 = admin1;
    }
    
    private static boolean kiemtrahoadon(String ma, String mathang){
        if (ma == null || mathang == null) {
            return false;
        }
        else
        {
            System.out.println("mathang = " + mathang+"ma KH = "+ma);
        }
        
        try{
            Connection con=ConnectionProvider.getCon();
            String query = "SELECT *FROM hoadon WHERE ma='"+ma+"' AND mathang='"+mathang+"'";
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs=pstmt.executeQuery();
            
            if (rs.next())
            {
                return true;
            }
            //return false;
        }
        catch(Exception e)
        {
            return false;
        }
        return false;
    }
    
    private String tinhtiendien(String ldtt, String loaidien)
    {
        String thanhtoanString=null;
        int ldtt1 = Integer.parseInt(ldtt);        
        double tongtien=0;
        
        if(loaidien.equals("Sinh hoạt"))
        {
            int tien1=0,tien2=0,tien3=0,tien4=0,tien5=0,tien6=0;
            
            if(ldtt1 < 50)
            {
                tien1 = ldtt1*1678;
            }else if (ldtt1>=50)
            {
                tien1 = 89300;
            }
            
            if (ldtt1 >50 && ldtt1<100)
            {
                tien2 = (ldtt1-50)*1734;
            }else if(ldtt1>=100)
            {
                tien2 = 85700;
            }
            
            if (ldtt1 >100 && ldtt1<200)
            {
                tien3 = (ldtt1-100)*2014;
            }else if(ldtt1>=200)
            {
                tien3 = 201400;
            }
            
            if (ldtt1 >200 && ldtt1<300){
                tien4 = (ldtt1-200)*2535;
            }else if(ldtt1>=300)
            {
                tien4 = 253600;
            }
            
            if (ldtt1 >300 && ldtt1<400){
                tien5 = (ldtt1-300)*2834;
            }else if(ldtt1>=400)
            {
                tien5 = 283400;
            }
            
            if (ldtt1 >400 ){
                tien6 = (ldtt1-400)*2927;
            }
            
            tongtien = (int)(tien1+tien2+tien3+tien4+tien5+tien6)*1.1;
            return(String.valueOf((int) Math.ceil(tongtien*1000 / 1000)));
        }
        else 
        {
            tongtien = ldtt1*3451*1.1;
            return(String.valueOf((int) Math.ceil(tongtien*1000 / 1000)));
        }
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
    /**
     * Creates new form Invoice
     */
    private Boolean chophep = false;
    private String makh;
    private String row11;
    private String row21;
    private String row31;
    private String row41;
    private String row51;
    
    public Invoice() {
        initComponents();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        Date date = new Date();
        SpinnerDateModel sm = new SpinnerDateModel(date, null, null, Calendar.MONTH);
        jSpinner1 = new javax.swing.JSpinner(sm);
        Date date2 = new Date();
        SpinnerDateModel sm2 = new SpinnerDateModel(date2, null, null, Calendar.YEAR);
        jSpinner2 =  new javax.swing.JSpinner(sm2);
        jLabel17 = new javax.swing.JLabel();
        Date date3 = new Date();
        SpinnerDateModel sm3 = new SpinnerDateModel(date3, null, null, Calendar.MONTH);
        jSpinner3 = new javax.swing.JSpinner(sm3);
        jLabel19 = new javax.swing.JLabel();
        Date date4 = new Date();
        SpinnerDateModel sm4 = new SpinnerDateModel(date4, null, null, Calendar.YEAR);
        jSpinner4 = new javax.swing.JSpinner(sm4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jTable2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Họ và tên", "Địa chỉ", "Mã tháng", "Loại điện", "Lượng điện tiêu thụ", "Thành tiền"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jLabel1.setText("Nhập mã Khách hàng");

        jLabel2.setText("Nhập mã tháng");

        jLabel3.setText("Loại điện");

        jTextField1.setText("Nhập mã khách hàng");
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField1FocusLost(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jTextField2.setText("Nhập mã tháng");
        jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField2FocusLost(evt);
            }
        });
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sinh hoạt", "Kinh doanh", "Sản xuất", "Cơ quan" }));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setText("Quản lý hóa đơn");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/bill.png"))); // NOI18N
        jButton1.setText("Tạo hóa đơn");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã tháng", "Mã khách hàng", "Họ và tên", "Chỉ số cũ", "Chỉ số mới"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hoá đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Mã hóa đơn :");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Khách hàng :");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Tháng :");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Lượng điện tiêu thụ :");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Loại điện :");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Thành tiền :");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("jLabel11");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("jLabel12");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("jLabel13");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("jLabel14");

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/thanhtoan.png"))); // NOI18N
        jButton2.setText("Thanh toán");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Print_16x16.png"))); // NOI18N
        jButton3.setText("In hóa đơn");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("jLabel15");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("jLabel16");

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/arrow_next.gif"))); // NOI18N
        jButton4.setText("Quay lại");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jButton2)
                        .addGap(45, 45, 45)
                        .addComponent(jButton3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(110, 110, 110)
                                .addComponent(jLabel13))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel11))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel16))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel14))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel15))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(jButton4)))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel16))
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addContainerGap(22, Short.MAX_VALUE))
        );

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

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/filter.png"))); // NOI18N
        jLabel17.setText("Lọc hiển thị");

        JSpinner.DateEditor de3 = new JSpinner.DateEditor(jSpinner3, "MMM");
        jSpinner3.setEditor(de3);
        jSpinner3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner3StateChanged(evt);
            }
        });

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/filter.png"))); // NOI18N
        jLabel19.setText("Lọc hiển thị");

        JSpinner.DateEditor de4 = new JSpinner.DateEditor(jSpinner4, "yyyy");
        jSpinner4.setEditor(de4);
        jSpinner4.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner4StateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 788, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(56, 56, 56)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel2))
                                        .addGap(37, 37, 37)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(54, 54, 54)
                                                .addComponent(jLabel3)
                                                .addGap(48, 48, 48)
                                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(116, 116, 116)
                                                .addComponent(jButton1)))))
                                .addGap(26, 26, 26))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(36, 36, 36)
                                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)))
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1208, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(494, 494, 494)
                        .addComponent(jLabel19)
                        .addGap(29, 29, 29)
                        .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(527, 527, 527)
                        .addComponent(jLabel5)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(27, 27, 27)
                                        .addComponent(jLabel2))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        Connection con=ConnectionProvider.getCon();
        try
        {
            DefaultTableModel model1 = (DefaultTableModel) jTable1.getModel();
            
            String query1 = "SELECT chisodien.mathang, chisodien.ma, hotieuthu.hoten, chisodien.chisocu, chisodien.chisomoi FROM chisodien INNER JOIN hotieuthu ON chisodien.ma = hotieuthu.ma";
            PreparedStatement pstmt1 = con.prepareStatement(query1);
            ResultSet rs1=pstmt1.executeQuery();
            while(rs1.next())
            {
                model1.insertRow(0, new Object[]{rs1.getString(1), rs1.getString(2), rs1.getString(3), rs1.getString(4), rs1.getString(5)});
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        Connection con2=ConnectionProvider.getCon();
        try
        {
            DefaultTableModel model2 = (DefaultTableModel) jTable2.getModel();
            
            String query2 = "SELECT hoadon.mahd, hotieuthu.hoten, hotieuthu.diachi, hoadon.mathang, hoadon.loaidien, hoadon.ldtt, hoadon.tien "
                    + "FROM hotieuthu INNER JOIN hoadon ON hoadon.ma = hotieuthu.ma";
            PreparedStatement pstmt2 = con2.prepareStatement(query2);
            ResultSet rs2=pstmt2.executeQuery();
            while(rs2.next())
            {
                model2.insertRow(0, new Object[]{rs2.getString(1), rs2.getString(2), rs2.getString(3), rs2.getString(4), rs2.getString(5), rs2.getString(6), rs2.getString(7)});
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_formComponentShown

    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost
        // TODO add your handling code here:
        if (jTextField1.getText().equals(""))
        {
            jTextField1.setText("Nhập mã khách hàng");
            jTextField1.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_jTextField1FocusLost

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained
        // TODO add your handling code here:
        if (jTextField1.getText().equals("Nhập mã khách hàng"))
        {
            jTextField1.setText("");
            jTextField1.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_jTextField1FocusGained

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int r = jTable1.getSelectedRow();
        TableModel model = jTable1.getModel();

        row11 = model.getValueAt(r, 0).toString();
        row21 = model.getValueAt(r, 1).toString();
        row31 = model.getValueAt(r, 2).toString();
        row41 = model.getValueAt(r, 3).toString();
        row51 = model.getValueAt(r, 4).toString();
    }//GEN-LAST:event_jTable1MouseClicked

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

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
        String timkiem1=jTextField1.getText();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        Connection con=ConnectionProvider.getCon();

        int r = jTable1.getRowCount();
        while(r-- > 0){
            model.removeRow(r);
        }

        try {
            String query = "SELECT chisodien.mathang, chisodien.ma, hotieuthu.hoten, chisodien.chisocu, chisodien.chisomoi FROM chisodien INNER JOIN hotieuthu ON chisodien.ma = hotieuthu.ma WHERE chisodien.ma LIKE '"+timkiem1+"%'";
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs=pstmt.executeQuery();

            while(rs.next())
            {
                model.insertRow(0, new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Dữ liệu nhập vào ô tìm kiếm bị lỗi hoặc Thông tin tìm kiếm không tồn tại. Vui lòng nhập lại !");
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        // TODO add your handling code here:
        
        String timkiem2=jTextField2.getText();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        Connection con=ConnectionProvider.getCon();

        int r = jTable1.getRowCount();
        while(r-- > 0){
            model.removeRow(r);
        }

        try {
            String query = "SELECT chisodien.mathang, chisodien.ma, hotieuthu.hoten, chisodien.chisocu, chisodien.chisomoi FROM chisodien INNER JOIN hotieuthu ON chisodien.ma = hotieuthu.ma WHERE chisodien.mathang LIKE '"+timkiem2+"%'";
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs=pstmt.executeQuery();

            while(rs.next())
            {
                model.insertRow(0, new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Dữ liệu nhập vào ô tìm kiếm bị lỗi hoặc Thông tin tìm kiếm không tồn tại. Vui lòng nhập lại !");
        }
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        String hoadon = String.valueOf(number);
        String loaidien =(String)jComboBox1.getSelectedItem();
        int dongia = 0;

        int ldtt = Integer.parseInt(row51) - Integer.parseInt(row41);
        String thang = row11.substring(4);
        String nam = row11.substring(0,4);

        jLabel11.setText(hoadon);
        jLabel12.setText(row31);
        jLabel13.setText(thang + "-" + nam);
        jLabel14.setText(String.valueOf(ldtt));
        jLabel15.setText(loaidien);
        jLabel16.setText(tinhtiendien(String.valueOf(ldtt), loaidien));
        makh = row21;
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(kiemtrahoadon(makh,row11)==false)
        {
        DefaultTableModel model2 = (DefaultTableModel) jTable2.getModel();
        
        try{
            Connection con=ConnectionProvider.getCon();
            System.out.println("mathang = "+row11);
            String query = "INSERT INTO hoadon(mahd,ma,mathang,ldtt,loaidien,tien) VALUES(?,?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1,jLabel11.getText());
            pstmt.setString(2,makh);
            pstmt.setString(3,row11);
            pstmt.setString(4,jLabel14.getText());
            pstmt.setString(5,jLabel15.getText());
            pstmt.setString(6,jLabel16.getText());
            
            int a=JOptionPane.showConfirmDialog(null, "Bạn có thực hiện thanh toán cho hóa đơn này không?","Chọn lựa",JOptionPane.YES_NO_OPTION);
            if (a != 0)
            {
                //JOptionPane.showMessageDialog(null, "Hãy thử nhập thông tin mới.");
            }
            else{
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Xác nhận thanh toán thành công.");
                Thread.sleep(1000);
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Bạn hãy xem lại thông tin thanh toán của hóa đơn !");
        }
        try{
            Connection con=ConnectionProvider.getCon();
            String query = "SELECT hoadon.mahd, hotieuthu.hoten, hotieuthu.diachi, hoadon.mathang, hoadon.loaidien, hoadon.ldtt, hoadon.tien "+
                            "FROM hotieuthu JOIN  hoadon ON hotieuthu.ma=hoadon.ma WHERE hoadon.ma='"+makh+"' AND hoadon.mathang='"+row11+"'";
            PreparedStatement pstmt = con.prepareStatement(query);
                
            ResultSet rs=pstmt.executeQuery();
            
            if (rs.next())
            {
                model2.insertRow(0, new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)});
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Việc tạo hóa đơn bị lỗi, xin vui lòng thực hiện đúng lệnh !");
        }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Hóa đơn của tháng này đối với khách hàng đó đã được thanh toán !");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        GenerateBill generateBill = new GenerateBill();
        generateBill.createPDF(makh,row11);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        int a=JOptionPane.showConfirmDialog(null, "Bạn có thực sự muốn quay lại giao diện màn hình chính hay không?","Chọn lựa",JOptionPane.YES_NO_OPTION);
        if (a == 0)
        {
            setVisible(false);
            new Home(admin1).setVisible(true);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner1StateChanged
        // TODO add your handling code here:
        String thang1 = jSpinner1.getValue().toString().substring(4, 7);
        System.out.println("mathang"+ thang1);
        String thang = null;
        String nam = jSpinner2.getValue().toString().substring(24, 28);
        thang = getThang(thang1);
        System.out.println("mathang"+ thang + nam);
        Connection con=ConnectionProvider.getCon();
        try
        {
            DefaultTableModel model1 = (DefaultTableModel) jTable1.getModel();
            
            String query1 = "SELECT chisodien.mathang, chisodien.ma, hotieuthu.hoten, chisodien.chisocu, chisodien.chisomoi FROM chisodien INNER JOIN hotieuthu ON chisodien.ma = hotieuthu.ma WHERE chisodien.mathang='"+nam+thang+"'";
            PreparedStatement pstmt1 = con.prepareStatement(query1);
            ResultSet rs1=pstmt1.executeQuery();
            
            int r = jTable1.getRowCount();
            while(r-- > 0){
                model1.removeRow(r);
            }
            while(rs1.next())
            {
                model1.insertRow(0, new Object[]{rs1.getString(1), rs1.getString(2), rs1.getString(3), rs1.getString(4), rs1.getString(5)});
            }
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
            DefaultTableModel model1 = (DefaultTableModel) jTable1.getModel();
            
            String query1 = "SELECT chisodien.mathang, chisodien.ma, hotieuthu.hoten, chisodien.chisocu, chisodien.chisomoi FROM chisodien INNER JOIN hotieuthu ON chisodien.ma = hotieuthu.ma WHERE chisodien.mathang='"+nam+thang+"'";
            PreparedStatement pstmt1 = con.prepareStatement(query1);
            ResultSet rs1=pstmt1.executeQuery();
            
            int r = jTable1.getRowCount();
            while(r-- > 0){
                model1.removeRow(r);
            }
            while(rs1.next())
            {
                model1.insertRow(0, new Object[]{rs1.getString(1), rs1.getString(2), rs1.getString(3), rs1.getString(4), rs1.getString(5)});
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jSpinner2StateChanged

    private void jSpinner3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner3StateChanged
        // TODO add your handling code here:
        String thang1 = jSpinner3.getValue().toString().substring(4, 7);
        String thang = null;
        String nam = jSpinner4.getValue().toString().substring(24, 28);
        thang = getThang(thang1);
        Connection con3=ConnectionProvider.getCon();
        try
        {
            DefaultTableModel model3 = (DefaultTableModel) jTable2.getModel();
            
            String query3 = "SELECT hoadon.mahd, hotieuthu.hoten, hotieuthu.diachi, hoadon.mathang, hoadon.loaidien, hoadon.ldtt, hoadon.tien "
                        + "FROM hotieuthu INNER JOIN hoadon ON hoadon.ma = hotieuthu.ma WHERE hoadon.mathang='"+nam+thang+"'";
            PreparedStatement pstmt3 = con3.prepareStatement(query3);
            ResultSet rs3=pstmt3.executeQuery();
            
            int r = jTable2.getRowCount();
            while(r-- > 0){
                model3.removeRow(r);
            }
            while(rs3.next())
            {
                model3.insertRow(0, new Object[]{rs3.getString(1), rs3.getString(2), rs3.getString(3), rs3.getString(4), rs3.getString(5), rs3.getString(6), rs3.getString(7)});
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jSpinner3StateChanged

    private void jSpinner4StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner4StateChanged
        // TODO add your handling code here:
        String thang1 = jSpinner3.getValue().toString().substring(4, 7);
        String thang = null;
        String nam = jSpinner4.getValue().toString().substring(24, 28);
        thang = getThang(thang1);
        Connection con4=ConnectionProvider.getCon();
        try
        {
            DefaultTableModel model4 = (DefaultTableModel) jTable2.getModel();
            
            String query4 = "SELECT hoadon.mahd, hotieuthu.hoten, hotieuthu.diachi, hoadon.mathang, hoadon.loaidien, hoadon.ldtt, hoadon.tien "
                        + "FROM hotieuthu INNER JOIN hoadon ON hoadon.ma = hotieuthu.ma WHERE hoadon.mathang='"+nam+thang+"'";
            PreparedStatement pstmt4 = con4.prepareStatement(query4);
            ResultSet rs4=pstmt4.executeQuery();
            
            int r = jTable2.getRowCount();
            while(r-- > 0){
                model4.removeRow(r);
            }
            while(rs4.next())
            {
                model4.insertRow(0, new Object[]{rs4.getString(1), rs4.getString(2), rs4.getString(3), rs4.getString(4), rs4.getString(5), rs4.getString(6), rs4.getString(7)});
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jSpinner4StateChanged

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
            java.util.logging.Logger.getLogger(Invoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Invoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Invoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Invoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Invoice().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JSpinner jSpinner4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
