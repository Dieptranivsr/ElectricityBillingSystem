/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ElectricityBillingSystem;

import static ElectricityBillingSystem.Help.admin1;
import Nhom4.ConnectionProvider;
import static Nhom4.GetAnalysis.getDataSum;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Dieptuantran
 */
public class Analysis extends javax.swing.JFrame {
    public static String admin1="Member";

    private static String getAdmin1() {
        return admin1;
    }
    
    private static void setAdmin1(String admin1) {
        Invoice.admin1 = admin1;
    }
    
    private Boolean kiemtraMathang(String mathang)
    {    
        try{
            Connection con=ConnectionProvider.getCon();
            String query = "SELECT *FROM chisodien WHERE mathang='"+mathang+"'";
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
    
    private String demHodaungDien(String mathang)
    {
        try{
            Connection con=ConnectionProvider.getCon();
            //SELECT COUNT(column_name) FROM table_name WHERE condition; 
            String query = "SELECT COUNT(chisocu) FROM chisodien WHERE mathang='"+mathang+"'" ;
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
            return "0";
        }
        return "0";
    }
    
    private String demHodaTTDien(String mathang)
    {
        try{
            Connection con=ConnectionProvider.getCon();
            //SELECT COUNT(column_name) FROM table_name WHERE condition; 
            String query = "SELECT COUNT(ma) FROM hoadon WHERE mathang='"+mathang+"'" ;
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
            return "0";
        }
        return "0";
    }
    
    private String hotieuthusdDien(String mathang,String loai)
    {
        try{
            Connection con=ConnectionProvider.getCon();
            String query = "SELECT COUNT(ma) FROM hoadon WHERE mathang='"+mathang+"' AND loaidien='"+loai+"'" ;
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs=pstmt.executeQuery();
            
            if (rs.next())
            {
                return rs.getString(1);
            }
        }
        catch(Exception e)
        {
            return "0";
        }
        return "0";
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
       
    private static String xulymathang(String mathang, String loai)
    {
        String mathang1;
        mathang1 = mathang.substring(4);
        
        if (loai.equals("11"))
        {
            if(Integer.parseInt(mathang1)==1)
                mathang1 = String.valueOf(Integer.parseInt(mathang.substring(0,4))-1)+"12";
            else if (Integer.parseInt(mathang1) < 11)
                mathang1 = mathang.substring(0,4)+"0"+String.valueOf(Integer.parseInt(mathang.substring(4))-1);
            else
                mathang1 = mathang.substring(0,4)+String.valueOf(Integer.parseInt(mathang.substring(4))-1);
        }           
        if (loai.equals("0"))
        {
            if (Integer.parseInt(mathang1)==12)
                mathang1 = String.valueOf(Integer.parseInt(mathang.substring(0,4))+1)+"01";
            else if (Integer.parseInt(mathang1) < 9)
                mathang1 = mathang.substring(0,4)+"0"+String.valueOf(Integer.parseInt(mathang.substring(4))+1);
            else
                mathang1 = mathang.substring(0,4)+String.valueOf(Integer.parseInt(mathang.substring(4))+1);
        }
        else if (loai.equals("3"))
        {
            if (Integer.parseInt(mathang1)<3)
                mathang1 = String.valueOf(Integer.parseInt(mathang.substring(0,4))-1)+String.valueOf(10+Integer.parseInt(mathang.substring(4)));
            else if (Integer.parseInt(mathang1)==12)
                mathang1 = mathang.substring(0,4)+String.valueOf(Integer.parseInt(mathang.substring(4))-2);
            else
                mathang1 = mathang.substring(0,4)+"0"+String.valueOf(Integer.parseInt(mathang.substring(4))-2);
        }
        else if (loai.equals("6"))
        {
            if (Integer.parseInt(mathang1)>=3 && Integer.parseInt(mathang1)<6)
                mathang1 = String.valueOf(Integer.parseInt(mathang.substring(0,4))-1)+String.valueOf(7+Integer.parseInt(mathang.substring(4)));
            else if (Integer.parseInt(mathang1)<3)
                mathang1 = String.valueOf(Integer.parseInt(mathang.substring(0,4))-1)+"0"+String.valueOf(7+Integer.parseInt(mathang.substring(4)));
            else
                mathang1 = mathang.substring(0,4)+"0"+String.valueOf(Integer.parseInt(mathang.substring(4))-5);
        }
        else if (loai.equals("12"))
        {
            if (Integer.parseInt(mathang1)==12)
                mathang1 = String.valueOf(Integer.parseInt(mathang.substring(0,4)))+"0"+String.valueOf(Integer.parseInt(mathang.substring(4))-11);
            else if (Integer.parseInt(mathang1)<9)
                mathang1 = String.valueOf(Integer.parseInt(mathang.substring(0,4))-1)+"0"+String.valueOf(Integer.parseInt(mathang.substring(4))+1);
            else
                mathang1 = String.valueOf(Integer.parseInt(mathang.substring(0,4))-1)+String.valueOf(Integer.parseInt(mathang.substring(4))+1);
        }
        else if (loai.equals("00"))
        {
            mathang1 = mathang.substring(0,4)+"01";
        }
        return mathang1;
    }
    
    private static String getMathang()
    {
        String mathang=null;
        String time = java.time.LocalDate.now().toString();
        mathang = time.substring(0,4)+time.substring(5,7);
        return mathang;
    }
    
    private static boolean xacnhanDataMathang(String mathang)
    {
        try{
            Connection con=ConnectionProvider.getCon();
            String query = "SELECT *FROM hoadon WHERE mathang='"+mathang+"'" ;
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs=pstmt.executeQuery();
            
            if (rs.next())
            {
                return true;
            }
        }
        catch(Exception e)
        {
            return false;
        }
        return false;
    }
    
    private static String tenThang(String thang)
    {
        if(thang.equals("01"))
            return "Jan";
        else if(thang.equals("02"))
            return "Feb";
        else if(thang.equals("03"))
            return "Mar";
        else if(thang.equals("04"))
            return "Apr";
        else if(thang.equals("05"))
            return "May";
        else if(thang.equals("06"))
            return "Jun";
        else if(thang.equals("07"))
            return "Jul";
        else if(thang.equals("08"))
            return "Aug";
        else if(thang.equals("09"))
            return "Sep";
        else if(thang.equals("10"))
            return "Oct";
        else if(thang.equals("11"))
            return "Nov";
        else if(thang.equals("12"))
            return "Dec";
        return null;
    }
    
    private static String getSumLdtt(String mathang)
    {
        int ldtt=0;
        try{
            Connection con=ConnectionProvider.getCon();
            //SELECT COUNT(column_name) FROM table_name WHERE condition; 
            String query = "SELECT chisocu,chisomoi FROM chisodien WHERE mathang='"+mathang+"'" ;
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs=pstmt.executeQuery();
            
            while (rs.next())
            {
                ldtt += Integer.parseInt(rs.getString(2))-Integer.parseInt(rs.getString(1));
            }
            //return false;
        }
        catch(Exception e)
        {
            return "0";
        }
        return String.valueOf(ldtt);
    }
    
    /**
     * Creates new form Analysis
     */
    public Analysis(String admin) {
        this.admin1 = admin;
        initComponents();
        
        //hotieuthusdDien("202015", "Sinh hoạt");
        showDataChart0("Năm nay");
        //showDataChart3("202105");
        showDataChart2(getMathang());
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
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        Date date = new Date();
        SpinnerDateModel sm = new SpinnerDateModel(date, null, null, Calendar.MONTH);
        jSpinner1 = new javax.swing.JSpinner(sm);
        Date date2 = new Date();
        SpinnerDateModel sm2 = new SpinnerDateModel(date2, null, null, Calendar.YEAR);
        jSpinner2 = new javax.swing.JSpinner(sm2);
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jComboBox3 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Thống kê tiêu thụ điện");

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));
        jPanel2.setLayout(new java.awt.BorderLayout());

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

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tỷ lệ thanh toán", "Loại điện" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Lọc dữ liệu theo ");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Năm nay", "Một năm gần đây", "6 tháng gần nhất", "3 tháng gần nhất" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Lọc dữ liệu theo ");

        jButton1.setText("Quay lại");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Doanh thu", "Lượng điện tiêu thụ" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(262, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(623, 623, 623))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(289, 289, 289)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSpinner2, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE))
                        .addGap(88, 88, 88))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 436, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(32, 32, 32)
                .addComponent(jButton1)
                .addGap(54, 54, 54))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner1StateChanged
        // TODO add your handling code here:
        String thang1 = jSpinner1.getValue().toString().substring(4, 7);
        String thang = null;
        String nam = jSpinner2.getValue().toString().substring(24, 28);
        thang = getThang(thang1);
        
        String hienthi1=(String)jComboBox1.getSelectedItem();
        if (hienthi1.equals("Tỷ lệ thanh toán"))
            showDataChart2(nam+thang);
        else if (hienthi1.equals("Loại điện"))
            showDataChart3(nam+thang);
    }//GEN-LAST:event_jSpinner1StateChanged

    private void jSpinner2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner2StateChanged
        // TODO add your handling code here:
        String thang1 = jSpinner1.getValue().toString().substring(4, 7);
        String thang = null;
        String nam = jSpinner2.getValue().toString().substring(24, 28);
        thang = getThang(thang1);
        
        String hienthi1=(String)jComboBox1.getSelectedItem();
        if (hienthi1.equals("Tỷ lệ thanh toán"))
            showDataChart2(nam+thang);
        else if (hienthi1.equals("Loại điện"))
            showDataChart3(nam+thang);
    }//GEN-LAST:event_jSpinner2StateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int a=JOptionPane.showConfirmDialog(null, "Bạn có thực sự muốn quay lại giao diện màn hình chính hay không?","Chọn lựa",JOptionPane.YES_NO_OPTION);
        if (a == 0)
        {
            setVisible(false);
            new Home(admin1).setVisible(true);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
        String hienthi2=(String)jComboBox2.getSelectedItem();
        String hienthi3=(String)jComboBox3.getSelectedItem();

        if (hienthi3.equals("Doanh thu"))
            showDataChart0(hienthi2);
        else if (hienthi3.equals("Lượng điện tiêu thụ"))
            showDataChart1(hienthi2);
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        String thang1 = jSpinner1.getValue().toString().substring(4, 7);
        String thang = null;
        String nam = jSpinner2.getValue().toString().substring(24, 28);
        thang = getThang(thang1);
        
        String hienthi1=(String)jComboBox1.getSelectedItem();                   //Tỷ lệ thanh toán, Loại điện
        if (hienthi1.equals("Tỷ lệ thanh toán"))                                //"Tỷ lệ thanh toán", "Loại điện"
        {
            System.out.println("Trạng thái : "+hienthi1);
            showDataChart2(nam+thang);
        }
        else if (hienthi1.equals("Loại điện"))
            showDataChart3(nam+thang);
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
        String hienthi2=(String)jComboBox2.getSelectedItem();
        String hienthi3=(String)jComboBox3.getSelectedItem();

        if (hienthi3.equals("Doanh thu"))
            showDataChart0(hienthi2);
        else if (hienthi3.equals("Lượng điện tiêu thụ"))
            showDataChart1(hienthi2);
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void showDataChart0(String loai){
        String[] result = new String[12];
        String mathangnow = getMathang();
        String mathanght = getMathang();
        String mathang1 = null;
        //System.out.println("mathang hien tai "+mathanght);
        
        //Năm nay, Một năm gần đây, 6 tháng gần nhất, 3 tháng gần nhất,
        int i=0;
        if(loai.equals("Năm nay"))
        {
            mathanght = xulymathang(mathangnow, "00");
        }
        else if(loai.equals("Một năm gần đây"))
        {
            mathanght = xulymathang(mathangnow, "12");
        }
        else if(loai.equals("6 tháng gần nhất"))
        {
            mathanght = xulymathang(mathangnow, "6");
        }
        else if(loai.equals("3 tháng gần nhất"))
        {
            mathanght = xulymathang(mathangnow, "3");
        }
        
        DefaultCategoryDataset barChartData = new DefaultCategoryDataset();

        while(true)
        {
            if(xacnhanDataMathang(mathanght)==false)
                result[i]="0";
            else
                result[i]=getDataSum(mathanght);
                
            //System.out.println("getSumLdtt["+mathanght+"] "+ result[i]);
            barChartData.setValue(Integer.valueOf(result[i]), "Lượng điện tiêu thụ", tenThang(mathanght.substring(4)));
                
            if(mathanght.substring(4).equals(mathangnow.substring(4))==true)
                break;
            i++;
            mathang1 = xulymathang(mathanght,"0");
            mathanght = mathang1;
        }

        JFreeChart barChartData1 = ChartFactory.createBarChart("Doanh thu tiền điện "+loai, "Tháng", "Doanh thu", barChartData, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot barchrt = barChartData1.getCategoryPlot();
        barchrt.setRangeGridlinePaint(Color.ORANGE);
        
        ChartPanel barPanel = new ChartPanel(barChartData1);
        jPanel1.removeAll();
        jPanel1.add(barPanel, BorderLayout.EAST);
        jPanel1.updateUI();
    }
    
    private void showDataChart1(String loai){
        String[] result = new String[12];
        String mathangnow = getMathang();
        String mathanght = getMathang();
        String mathang1 = null;
        //System.out.println("mathang hien tai "+mathanght);
        
        //Năm nay, Một năm gần đây, 6 tháng gần nhất, 3 tháng gần nhất,
        int i=0;
        if(loai.equals("Năm nay"))
        {
            mathanght = xulymathang(mathangnow, "00");
        }
        else if(loai.equals("Một năm gần đây"))
        {
            mathanght = xulymathang(mathangnow, "12");
        }
        else if(loai.equals("6 tháng gần nhất"))
        {
            mathanght = xulymathang(mathangnow, "6");
        }
        else if(loai.equals("3 tháng gần nhất"))
        {
            mathanght = xulymathang(mathangnow, "3");
        }
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        while(true)
        {
            if(xacnhanDataMathang(mathanght)==false)
                result[i]="0";
            else
                result[i]=getSumLdtt(mathanght);
                
            //System.out.println("getSumLdtt["+mathanght+"] "+ result[i]);
            dataset.setValue(Integer.valueOf(result[i]), "Lượng điện tiêu thụ", tenThang(mathanght.substring(4)));
                
            if(mathanght.substring(4).equals(mathangnow.substring(4))==true)
                break;
            i++;
            mathang1 = xulymathang(mathanght,"0");
            mathanght = mathang1;
        }

        //Create the chart object for the type Line Chart. Tip: the second field "true" is very important to the draw the value top the line
        JFreeChart chart = ChartFactory.createLineChart( "Lượng điện tiêu thụ "+loai, "Tháng", "Lượng điện", dataset, PlotOrientation.VERTICAL, false, true, false); 
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setRangeGridlinePaint(Color.ORANGE);
        
        //Create of the renderer object to the draw point and the value
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setShapesVisible(true);

        //Define the format to the value to the draw
        DecimalFormat decimalformat1 = new DecimalFormat("##");
        renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", decimalformat1));

        renderer.setItemLabelsVisible(true);
        renderer.setSeriesVisible(true);
     
        ChartPanel barPanel = new ChartPanel(chart);
        jPanel1.removeAll();
        jPanel1.add(barPanel, BorderLayout.EAST);
        jPanel1.updateUI();
    }
    
    public void showDataChart2(String mathang){
        //GET DATA
        String dathanhtoan,tongsoho;
        tongsoho = demHodaungDien(mathang);
        dathanhtoan = demHodaTTDien(mathang);
        
        // Create dataset
        DefaultPieDataset barDataset = new DefaultPieDataset();
        barDataset.setValue("Đã thanh toán", new Double(dathanhtoan));
        barDataset.setValue("Chưa thanh toán", new Double(Integer.valueOf(tongsoho)-Integer.valueOf(dathanhtoan)));
        
        // Create chart
        JFreeChart pieChart = ChartFactory.createPieChart3D("Tỷ lệ số người đã thanh toán tiền điện tháng "+mathang.substring(4)+" năm "+mathang.substring(0,4), barDataset, false, true, false);
        
        PiePlot piePlot = (PiePlot) pieChart.getPlot();
        
        piePlot.setSectionPaint("Đã thanh toán", new Color(0,0,255));
        piePlot.setSectionPaint("Chưa thanh toán", new Color(255,0,0));
        
        piePlot.setBackgroundPaint(Color.WHITE);
        
        // Create chartPanel to display chart(graph)
        ChartPanel barChartPanel = new ChartPanel(pieChart);
        jPanel2.removeAll();
        jPanel2.add(barChartPanel,BorderLayout.WEST);
        jPanel2.validate();
    }
    
    public void showDataChart3(String mathang){
        // Create dataset
        DefaultPieDataset barDataset = new DefaultPieDataset();
        
        String[] result = new String[4]; // Sinh hoạt, Kinh doanh, Sản xuất, Cơ quan
        result[0] = hotieuthusdDien(mathang, "Sinh hoạt");
        result[1] = hotieuthusdDien(mathang, "Kinh doanh");;
        result[2] = hotieuthusdDien(mathang, "Sản xuất");;
        result[3] = hotieuthusdDien(mathang, "Cơ quan");;
        System.out.println("result :"+result[0] +"-"+result[1]+"-"+result[2]+"-"+result[3]);
        barDataset.setValue("Sinh hoạt", new Double(result[0]));
        barDataset.setValue("Kinh doanh", new Double(result[1]));
        barDataset.setValue("Sản xuất", new Double(result[2]));
        barDataset.setValue("Cơ quan", new Double(result[3]));
        
        // Create chart
        JFreeChart pieChart = ChartFactory.createPieChart3D("Tỷ lệ hộ tiêu thụ điện theo tháng "+mathang.substring(4)+" năm "+mathang.substring(0,4), barDataset, false, true, false);
        
        PiePlot piePlot = (PiePlot) pieChart.getPlot();
        
        piePlot.setSectionPaint("Sinh hoạt", new Color(0,0,255));
        piePlot.setSectionPaint("Kinh doanh", new Color(255,0,0));
        piePlot.setSectionPaint("Sản xuất", new Color(0,255,0));
        piePlot.setSectionPaint("Cơ quan", new Color(255,0,255));
        
        piePlot.setBackgroundPaint(Color.WHITE);
        
        // Create chartPanel to display chart(graph)
        ChartPanel barChartPanel = new ChartPanel(pieChart);
        jPanel2.removeAll();
        jPanel2.add(barChartPanel,BorderLayout.WEST);
        jPanel2.validate();
    }
    
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
            java.util.logging.Logger.getLogger(Analysis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Analysis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Analysis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Analysis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Analysis(admin1).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    // End of variables declaration//GEN-END:variables
}
