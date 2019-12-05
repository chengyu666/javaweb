package chengyu.printer.model;

import java.util.Date;

public class User {
    /**
     * 用户id
     */
    private Integer id=-1;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 到期时间
     */
    private Date expire;

    public User() {
    }

    public User(Integer id, String name, Date expire) {
        this.id = id;
        this.name = name;
        this.expire = expire;
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

    public Date getexpire() {
        return expire;
    }

    public void setexpire(Date expire) {
        this.expire = expire;
    }

    public String toString(){
        return "User{id:"+id+", name:"+name+" expire:"+expire+"}";
    }
}
