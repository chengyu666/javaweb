package chengyu.javaweb.model;

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
    private Date signup;

    /**
     * 用户角色
     */
    private String role;

    public User() {
    }

    public User(Integer id, String name, Date signup, String role) {
        this.id = id;
        this.name = name;
        this.signup = signup;
        this.role = role;
    }
    public void update(Integer id, String name, Date signup, String role){
        setSignup(signup);
        setId(id);
        setName(name);
        setRole(role);
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

    public Date getSignup() {
        return signup;
    }

    public void setSignup(Date signup) {
        this.signup = signup;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String toString() {
        return "User{id:" + id + ", name:" + name + " signup:" + signup +" role:"+role+ "}";
    }
}
