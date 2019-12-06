package chengyu.printer.model;

import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/s_e";
    private static final String USER = "rjgc";
    private static final String PASSWORD = "rjgc123";

    public User login(String _name ,String _password) throws SQLException{
        User user=new User();
        //id==-1 login fail
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(Exception e){
            System.out.println("in conn:"+e);
        }
        Connection connection= DriverManager.getConnection(URL,USER,PASSWORD);
        List<User> list = new ArrayList<User>();
        if(connection != null){
            //connected
            System.out.println("connected!");
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user where name=\'"+_name+"\'");
            if(rs.next()) {
                //has user with this name
                String password= rs.getString("password");
                String name = rs.getString("name");
                Integer id = rs.getInt("id");
                Date expire = rs.getDate("expiredate");
                String role=rs.getString("role");
                System.out.println("password in DB:"+password);
                if(password.equals(_password)) {
                    //password is correct
                    user = new User(id, name, expire, role);
                }
                System.out.println(user.toString());
            }
            connection.close();
        }else{
            System.out.println("connection fail !");
        }
        return user;
    }

    public List<User> getAllUsers()throws SQLException {
        System.out.println("getting user info");
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(Exception e){
            System.out.println("in getAllUsers:"+e);
        }
        Connection connection= DriverManager.getConnection(URL,USER,PASSWORD);
        List<User> list = new ArrayList<User>();
        if(connection != null){
            System.out.println("connected!");
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user");
            while(rs.next()){
                String name=rs.getString("name");
                Integer id=rs.getInt("id");
                Date expire=rs.getDate("expiredate");
                String role=rs.getString("role");
                System.out.println(name+" ID:"+id+" expiredate:"+expire);
                list.add(new User(id,name,expire,role));
            }
            connection.close();
        }else{
            System.out.println("fail !");
        }
        return list;
    }

    public List<Code> getAllCodes()throws SQLException{
        System.out.println("getting code info");
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(Exception e){
            System.out.println("in getAllCodes:"+e);
        }
        Connection connection= DriverManager.getConnection(URL,USER,PASSWORD);
        List<Code> list = new ArrayList<Code>();
        if(connection != null){
            System.out.println("connected!");
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM code");
            while(rs.next()){
                String code=rs.getString("code");
                Date time=rs.getTimestamp("time");
                String message=rs.getString("message");
                System.out.println(code+" des:"+message+" updatetime:"+time);
                list.add(new Code(code,message,time));
            }
            connection.close();
        }else{
            System.out.println("fail !");
        }
        return list;
    }

}
