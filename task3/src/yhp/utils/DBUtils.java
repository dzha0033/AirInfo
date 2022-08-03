package yhp.utils;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

public class DBUtils {
    //1.定义变量
    private Connection connection;
    private PreparedStatement pps;
    protected ResultSet resultSet;
    private int count;

    private static String user;
    private static String passWord;
    private static String url;
    private static String driver;

    private static DruidDataSource dataSource = new DruidDataSource();

    //2。加载驱动
    static {
        ResourceBundle bundle = ResourceBundle.getBundle("db");
        driver = bundle.getString("driver");
        url = bundle.getString("url");
        user = bundle.getString("user");
        passWord = bundle.getString("password");

        dataSource.setUsername(user);
        dataSource.setPassword(passWord);
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driver);
    }

    //3.获得链接
    protected Connection getConnection() {
        try {
            connection =  dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    //4.得到预状态通道
    protected PreparedStatement getPps(String sql) {
        try {
            pps = getConnection().prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pps;
    }

    //5.绑定参数
    protected void param(List list) {
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                try {
                    pps.setObject(i + 1, list.get(i));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //6.执行操作增删改
    protected int update(String sql, List list) {
        getPps(sql);
        param(list);
        try {
            count = pps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    //7. 查
    protected ResultSet query(String sql, List list) {
        getPps(sql);
        param(list);
        try {
            resultSet = pps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    //8.关闭
    protected  void closeAll(){
        try {
            if(connection != null) {
                connection.close();
            }
            if(pps != null){
                pps.close();
            }
            if(resultSet != null){
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
