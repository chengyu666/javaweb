package chengyu.printer.model;

import java.util.Date;

public class User {
    /**
     * 用户id
     */
    private Integer id = -1;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 到期时间
     */
    private Date expire;

    /**
     * 用户角色
     */
    private String role;

    public User() {
    }

    public User(Integer id, String name, Date expire, String role) {
        this.id = id;
        this.name = name;
        this.expire = expire;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String toString() {
        return "User{id:" + id + ", name:" + name + " expire:" + expire +" role:"+role+ "}";
    }
}
