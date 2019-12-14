package chengyu.javaweb.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class DBConnector {
    Logger logger = Logger.getLogger("logger");
    private static final String URL = "jdbc:mysql://localhost:3306/s_e?useSSL=false";
    private static final String USER = "rjgc";
    private static final String PASSWORD = "rjgc123";
    private Connection connection;

    private void refreshConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            logger.warning("in conn:" + e);
        }
    }

    public User login(String _name, String _password) throws SQLException {
        User user = new User();
        //id==-1 login fail
        refreshConnection();
        List<User> list = new ArrayList<User>();
        if (connection != null) {
            //connected
            System.out.println("connected!");
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user where name=\'" + _name + "\'");
            if (rs.next()) {
                //has user with this name
                String password = rs.getString("password");
                String name = rs.getString("name");
                Integer id = rs.getInt("id");
                Date expire = rs.getDate("expiredate");
                String role = rs.getString("role");
                System.out.println("password in DB:" + password);
                if (password.equals(_password)) {
                    //password is correct
                    user = new User(id, name, expire, role);
                }
                System.out.println(user.toString());
            }
            connection.close();
        } else {
            logger.warning("connection fail !");
        }
        return user;
    }

    public List<User> getAllUsers() throws SQLException {
        System.out.println("getting user info");
        refreshConnection();
        List<User> list = new ArrayList<User>();
        if (connection != null) {
            System.out.println("connected!");
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user");
            while (rs.next()) {
                String name = rs.getString("name");
                Integer id = rs.getInt("id");
                Date expire = rs.getDate("expiredate");
                String role = rs.getString("role");
                System.out.println(name + " ID:" + id + " expiredate:" + expire);
                list.add(new User(id, name, expire, role));
            }
            connection.close();
        } else {
            System.out.println("fail !");
        }
        return list;
    }

    public List<Code> getAllCodes() throws SQLException {
        System.out.println("getting all code info");
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            logger.warning("in getAllCodes:" + e);
        }
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        List<Code> list = new ArrayList<Code>();
        if (connection != null) {
            System.out.println("connected!");
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM code");
            while (rs.next()) {
                String code = rs.getString("code");
                Date time = rs.getTimestamp("time");
                String message = rs.getString("message");
                //System.out.println(code+" des:"+message+" updatetime:"+time);
                list.add(new Code(code, message, time));
            }
            connection.close();
        } else {
            logger.warning("fail !");
        }
        return list;
    }

    public Code getInfoByCode(String inputCode) throws SQLException {
        logger.fine("getting code info");
        refreshConnection();
        Code errorCode = new Code();
        if (connection != null) {
            logger.info("connected!");
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM code");
            while (rs.next()) {
                String code = rs.getString("code");
                Date time = rs.getTimestamp("time");
                String message = rs.getString("message");
                if (code.equals(inputCode)) {
                    errorCode.update(code, message, time);
                    logger.info(errorCode.toString());
                    logger.info("find the code!");
                }
            }
            connection.close();
        } else {
            logger.warning("connection fail !");
        }
        return errorCode;
    }

    public User getUserById(String inputId) throws SQLException {
        logger.info("inputId:" + inputId);
        refreshConnection();
        User user = new User();
        if (connection != null) {
            logger.info("connected!");
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user");
            while (rs.next()) {

                String name = rs.getString("name");
                Integer id = rs.getInt("id");
                Date expire = rs.getDate("expiredate");
                String role = rs.getString("role");
                logger.info("id in db:" + id);
                if (id.toString().equals(inputId)) {
                    user.update(id, name, expire, role);
                    logger.info(user.toString());
                    logger.info("find the user!");
                }
            }
            connection.close();
        } else {
            logger.warning("connection fail !");
        }
        return user;
    }

    public boolean updatePassword(Integer id, String oldPassword, String newPassword) throws SQLException {
        refreshConnection();
        if (connection != null) {
            logger.info("connected!");
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM user where id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String password = rs.getString("password");
                if (password.equals(oldPassword)) {
                    logger.info("password is correct!");
                    sql = "UPDATE user SET password='" + newPassword + "' WHERE id=" + id.toString();
                    int r = stmt.executeUpdate(sql);
                    logger.info("changed rows:" + r);
                    return true;
                } else {
                    logger.info("wrong password!");
                    return false;
                }
            }
        }
        return false;
    }

    public boolean updateUserName(Integer id, String newName) throws SQLException {
        refreshConnection();
        if (connection != null) {
            logger.info("connected!");
            Statement stmt = connection.createStatement();
            String sql = "UPDATE user SET name='" + newName + "' WHERE id=" + id.toString();
            int r = stmt.executeUpdate(sql);
            logger.info("changed rows:" + r);
            if (r == 1) {
                logger.info("update name success!");
                return true;
            } else {
                logger.info("update name fail!");
                return false;
            }
        }
        return false;
    }
    public boolean updateUserExpire(Integer id, String newExpire){
        refreshConnection();
        if (connection != null) {
            logger.info("connected!new expire:"+newExpire);
            try{
                Statement stmt = connection.createStatement();
                String sql = "UPDATE user SET expiredate='" + newExpire + "' WHERE id=" + id.toString();
                int r = stmt.executeUpdate(sql);
                logger.info("changed rows:" + r);
                if (r == 1) {
                    logger.info("update expire success!");
                    return true;
                } else {
                    logger.info("update expire fail!");
                    return false;
                }
            }catch (SQLException e){
                logger.warning(e.toString());
                return false;
            }
        }
        return false;
    }

    public boolean removeUserById(Integer id) throws SQLException {
        refreshConnection();
        if (connection != null) {
            logger.info("connected!");
            Statement stmt = connection.createStatement();
            String sql = "DELETE FROM user WHERE id=" + id.toString();
            int r = stmt.executeUpdate(sql);
            logger.info("changed rows:" + r);
            if (r == 1) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    public boolean removeCodeByCode(String code) throws SQLException {
        refreshConnection();
        if (connection != null) {
            logger.info("connected!");
            Statement stmt = connection.createStatement();
            String sql = "DELETE FROM code WHERE code='" + code+"'";
            int r = stmt.executeUpdate(sql);
            logger.info("changed rows:" + r);
            if (r == 1) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public boolean updateCodeMessage(String code, String newMessage) throws SQLException{
        refreshConnection();
        if (connection != null) {
            logger.info("connected!");
            Statement stmt = connection.createStatement();
            //TODO update time
            String sql = "UPDATE code SET message='" + newMessage + "' WHERE code='" + code+"'";
            int r = stmt.executeUpdate(sql);
            logger.info("changed rows:" + r);
            if (r == 1) {
                logger.info("update message success!");
                return true;
            } else {
                logger.info("update message fail!");
                return false;
            }
        }
        return false;
    }

    public boolean addUser(String name, String password, String expire) throws SQLException{
        refreshConnection();
        if(connection!=null){
            Statement stmt = connection.createStatement();
            String sql="INSERT INTO user " +
                    "(name,password,expiredate) VALUES " +
                    "('"+name+"','"+password+"','"+expire+"')";
            int r=stmt.executeUpdate(sql);
            if (r == 1) {
                logger.info("add user success!");
                return true;
            } else {
                logger.info("add user fail!");
                return false;
            }
        }
        return false;
    }

    public boolean addCode(String code, String message)throws SQLException{
        refreshConnection();
        if(connection!=null){
            Statement stmt = connection.createStatement();
            String sql="INSERT INTO code " +
                    "(code,message) VALUES " +
                    "('"+code+"','"+message+"')";
            int r=stmt.executeUpdate(sql);
            if (r == 1) {
                logger.info("add code success!");
                return true;
            } else {
                logger.info("add code fail!");
                return false;
            }
        }
        return false;
    }
}
