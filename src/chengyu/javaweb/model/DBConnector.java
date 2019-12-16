package chengyu.javaweb.model;

import org.springframework.beans.BeanUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class DBConnector {
    Logger logger = Logger.getLogger("logger");
    private static final String URL = "jdbc:mysql://localhost:3306/javaweb?useSSL=false";
    private static final String USER = "chengyu";
    private static final String PASSWORD = "1998";
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
                Date expire = rs.getDate("signup");
                String role = rs.getString("role");
                Integer money = rs.getInt("money");
                System.out.println("password in DB:" + password);
                if (password.equals(_password)) {
                    //password is correct
                    user = new User(id, name, expire, role, money);
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
        System.out.println("getting all user info");
        refreshConnection();
        List<User> list = new ArrayList<User>();
        if (connection != null) {
            //System.out.println("connected!");
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user");
            while (rs.next()) {
                String name = rs.getString("name");
                Integer id = rs.getInt("id");
                Date signup = rs.getDate("signup");
                String role = rs.getString("role");
                Integer money = rs.getInt("money");
                //System.out.println(name + " ID:" + id + " name:" + name);
                list.add(new User(id, name, signup, role, money));
            }
            connection.close();
        } else {
            System.out.println("fail !");
        }
        return list;
    }

    public List<Printer> getAllPrinters() throws SQLException {
        System.out.println("getting all printer info");
        refreshConnection();
        Printer printer = new Printer();
        List<Printer> list = new ArrayList<Printer>();
        if (connection != null) {
            System.out.println("connected!");
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM printer");
            while (rs.next()) {
                String code = rs.getString("code");
                Integer price = rs.getInt("price");
                String information = rs.getString("information");
                printer = new Printer(code, information, price);
                list.add(printer);
            }
            connection.close();
        } else {
            logger.warning("fail !");
        }
        return list;
    }

    public Printer getPrinterByCode(String inputCode) throws SQLException {
        logger.fine("getting printer info");
        refreshConnection();
        Printer printerCode = new Printer();
        if (connection != null) {
            //logger.info("connected!");
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM printer");
            while (rs.next()) {
                String code = rs.getString("code");
                Integer price = rs.getInt("price");
                String information = rs.getString("information");
                if (code.equals(inputCode)) {
                    printerCode.update(code, information, price);
                    //logger.info(printerCode.toString());
                    //logger.info("find the code!");
                }
            }
            connection.close();
        } else {
            logger.warning("connection fail !");
        }
        return printerCode;
    }

    public User getUserById(String inputId) throws SQLException {
        //logger.info("inputId:" + inputId);
        refreshConnection();
        User user = new User();
        List<User> list = getAllUsers();
        if (list != null) {
            for (User u : list) {
                //logger.info("id in db:" + u.getId());
                if (u.getId().toString().equals(inputId)) {
                    BeanUtils.copyProperties(u, user);
                    //logger.info(user.toString());
                    //logger.info("find the user!");
                    break;
                }
            }
        } else {
            logger.warning("connection fail !");
        }
        return user;
    }

    public boolean updatePassword(Integer id, String oldPassword, String newPassword) throws SQLException {
        refreshConnection();
        if (connection != null) {
            //logger.info("connected!");
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

    public boolean updateMoney(String id, String newMoney) throws SQLException {
        refreshConnection();
        if (connection != null) {
            logger.info("connected!");
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM user where id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String money = rs.getString("money");
                logger.info("old money:" + money);
                sql = "UPDATE user SET money=" + newMoney + " WHERE id=" + id;
                int r = stmt.executeUpdate(sql);
                logger.info("changed rows:" + r);
                return true;
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
    /*public boolean updateUserExpire(Integer id, String newExpire){
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
    }*/

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

    public boolean removePrinterByCode(String code) throws SQLException {
        refreshConnection();
        if (connection != null) {
            logger.info("connected!");
            Statement stmt = connection.createStatement();
            String sql = "DELETE FROM printer WHERE code='" + code + "'";
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

    public boolean updatePrinterInfo(String code, String newInformation) throws SQLException {
        refreshConnection();
        if (connection != null) {
            logger.info("connected!");
            Statement stmt = connection.createStatement();
            String sql = "UPDATE printer SET information='" + newInformation + "' WHERE code='" + code + "'";
            int r = stmt.executeUpdate(sql);
            logger.info("changed rows:" + r);
            if (r == 1) {
                logger.info("update information success!");
                return true;
            } else {
                logger.info("update information fail!");
                return false;
            }
        }
        return false;
    }

    public boolean updatePrinterPrice(String code, String newPrice) throws SQLException {
        refreshConnection();
        if (connection != null) {
            logger.info("connected!");
            Statement stmt = connection.createStatement();
            String sql = "UPDATE printer SET price=" + newPrice + " WHERE code='" + code + "'";
            int r = stmt.executeUpdate(sql);
            logger.info("changed rows:" + r);
            if (r == 1) {
                logger.info("update price success!");
                return true;
            } else {
                logger.info("update price fail!");
                return false;
            }
        }
        return false;
    }

    public boolean addUser(String name, String password, String money) throws SQLException {
        refreshConnection();
        if (connection != null) {
            Statement stmt = connection.createStatement();
            String sql = "INSERT INTO user " +
                    "(name,password,money) VALUES " +
                    "('" + name + "','" + password + "'," + money + ")";
            int r = stmt.executeUpdate(sql);
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

    public boolean addPrinter(String code, String information, String price) throws SQLException {
        refreshConnection();
        if (connection != null) {
            Statement stmt = connection.createStatement();
            String sql = "INSERT INTO printer " +
                    "(code,information,price) VALUES " +
                    "('" + code + "','" + information + "'," + price + ")";
            int r = stmt.executeUpdate(sql);
            if (r == 1) {
                logger.info("add printer success!");
                return true;
            } else {
                logger.info("add printer fail!");
                return false;
            }
        }
        return false;
    }

    public List<CartItem> getCartItems(String id){
        refreshConnection();
        List<CartItem> list = new ArrayList<>();
        if(connection!=null){
            try{
                System.out.println("connected!");
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM cart where userid="+id);
                while (rs.next()) {
                    String cart=rs.getString("cart");
                    logger.info("cart:"+cart);
                    String[] stringList=cart.split(",");
                    System.out.println("length: "+stringList.length);
                    if(cart.equals("")){
                        logger.info("nothing in cart!");
                        return null;
                    }
                    for(String str :stringList){
                        logger.info(str);
                        String[] itemArr=str.split("\\.");
                        logger.info("code:"+itemArr[0]+", number:"+itemArr[1]);
                        String c=itemArr[0];
                        Integer n=Integer.valueOf(itemArr[1]);
                        Integer p=getPrinterByCode(c).getPrice();
                        list.add(new CartItem(n,c,p));
                    }
                }
                connection.close();
            }catch (SQLException e){
                logger.info(e.toString());
            }
        }
        return list;
    }

    public boolean updateCart(String id, List<CartItem> cart){
        refreshConnection();
        if(connection!=null){
            try{
                String cartStr="";
                if(cart!=null){
                    for(CartItem item:cart){
                        cartStr+=item.getCode()+"."+item.getNumber()+",";
                    }
                    cartStr=cartStr.substring(0,cartStr.length()-1);
                }
                logger.info("new cart str:"+cartStr);
                System.out.println("connected!");
                Statement stmt = connection.createStatement();
                List<CartItem> oldCart = getCartItems(id);
                String sql;
                //null则需要增加记录
                logger.info("adding new record to cart table");
                sql="SELECT * FROM cart WHERE userid="+id;
                ResultSet rs= stmt.executeQuery(sql);
                if(rs.next()){
                    sql="UPDATE cart set cart='"+cartStr+"' WHERE userid="+id;
                }else{
                    sql="INSERT INTO cart(userid,cart) VALUES("+id+",'"+cartStr+"')";
                }
                int r=stmt.executeUpdate(sql);
                if (r == 1) {
                    logger.info("update cart success!");
                    return true;
                } else {
                    logger.info("id:"+id+" update cart fail!");
                    return false;
                }
            }catch (SQLException e){logger.warning(e.toString());}
        }
        return false;
    }

    public void addSearchHistory(String id, String code){
        refreshConnection();
        if(connection!=null){
            try{
                Statement stmt = connection.createStatement();
                String sql="INSERT INTO viewHistory " +
                        "(userid,code) VALUES " +
                        "('" + id + "','" + code + "')";
                int r = stmt.executeUpdate(sql);
                if (r == 1) {
                    logger.info("update history success!");
                } else {
                    logger.info("update history fail!");
                }
            }catch (SQLException e){logger.warning(e.toString());}
        }
    }
}
