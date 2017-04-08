package cc.cannot.util;

import java.sql.*;

/**
 * Created by huangzhen on 4/5/2017.
 */
public class DB {
    public static final String db_name = "bigdata_base";
    public static final String db_port = "3306";
    public static final String url = "jdbc:mysql://localhost:" + db_port + "/" + db_name;
    public static final String driver_name = "com.mysql.jdbc.Driver";
    public static final String user = "danceiny";
    public static final String pwd = "1996";
    public static Connection conn = null;

    public void close(){
        try{
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void open(String sql)
    {
        try{
            Class.forName(driver_name);    //指定连接类型
            conn = DriverManager.getConnection(url,user,pwd);
            Statement st = conn.createStatement();
            System.out.println("Connected to database " + db_name + " sucessfully");
            int rs = st.executeUpdate(sql);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String sql){
        open(sql);
    }

}
