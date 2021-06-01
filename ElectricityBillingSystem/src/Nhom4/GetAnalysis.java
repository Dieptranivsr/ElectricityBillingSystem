/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nhom4;

import static Nhom4.GenerateBill.kiemtrahoadon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Dieptuantran
 */
public class GetAnalysis {
    public static void main(String args[]) {
        String abc = getDataSum("202104");
        System.out.println("Sum = "+abc);
    }
    
    public static String getDataSum(String mathang)
    {
        String sum1=null;
        try{
            Connection con=ConnectionProvider.getCon();
            String query = "SELECT SUM(tien) FROM hoadon WHERE mathang='"+mathang+"'";
            PreparedStatement pstmt = con.prepareStatement(query);
                
            ResultSet rs=pstmt.executeQuery();
            if (rs.next())
            {
                sum1=rs.getString(1);
                return sum1;
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Việc tạo hóa đơn bị lỗi, xin vui lòng thực hiện đúng lệnh !");
        }
        
        return sum1;
    }
}
