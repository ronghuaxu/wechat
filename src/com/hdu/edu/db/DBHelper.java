package com.hdu.edu.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBHelper
{
    public static final String URL = "jdbc:mysql://127.0.0.1/tmp_grad?characterEncoding=UTF-8";
    
    public static final String NAME = "com.mysql.jdbc.Driver";
    
    public static final String USER = "root";
    
    public static final String PASSWORD = "123qwe,./";
    
    public Connection conn = null;
    
    public PreparedStatement pst = null;
    
    public DBHelper()
    {
        try
        {
            Class.forName(NAME);// 指定连接类型
            conn = DriverManager.getConnection(URL, USER, PASSWORD);// 获取连接
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void getConnection(String sql)
    {
        try
        {
            pst = conn.prepareStatement(sql);// 准备执行语句
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void close()
    {
        try
        {
            this.conn.close();
            this.pst.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
