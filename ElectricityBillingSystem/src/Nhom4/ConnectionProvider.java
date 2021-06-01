/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nhom4;
import java.sql.*;

/**
 *
 * @author Dieptuantran
 */
public class ConnectionProvider {
    public static Connection getCon()
    {
        try
        {
            //Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nhom4", "root", "1234");
            return con;
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
