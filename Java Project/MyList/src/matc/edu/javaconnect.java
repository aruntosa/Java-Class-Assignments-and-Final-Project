package matc.edu;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author admin
 */
import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.*;

public class javaconnect {
    
    Connection conn = null;
    public static Connection ConnecrDb(){
        try{
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\admin\\Documents\\NetBeansProjects\\MyList\\grocery.sqlite");
        JOptionPane.showMessageDialog(null, "Connection Established");
                return conn;            
        }catch (ClassNotFoundException | SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
