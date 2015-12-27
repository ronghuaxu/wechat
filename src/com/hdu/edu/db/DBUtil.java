package com.hdu.edu.db;

import java.sql.SQLException;

import com.hdu.edu.bean.ChatRecordBean;

public class DBUtil
{
    private static DBHelper dbhelper = new DBHelper();;
    
    public static void chatRecordTosql(ChatRecordBean chatrecordBean)
    {
        
        // 不存在对应用户,需要插入到数据库中
        String sql =
            "insert into wechat_chat_record (open_id,request_msg,response_msg,category,chat_time) values(?,?,?,?,?)";
            
        int index = 1;
        
        dbhelper.getConnection(sql);
        
        try
        {
            dbhelper.pst.setString(index++, chatrecordBean.getOpen_id());
            dbhelper.pst.setString(index++, chatrecordBean.getRequest_msg());
            dbhelper.pst.setString(index++, chatrecordBean.getResponse_msg());
            dbhelper.pst.setString(index++, chatrecordBean.getCategory());
            dbhelper.pst.setDate(index++, chatrecordBean.getChat_date());
            dbhelper.pst.executeUpdate();
            
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
