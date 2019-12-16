package chengyu.javaweb.model;

import java.sql.Timestamp;

public class HistoryItem {
    private Integer id;
    private String code;
    private Timestamp time;

    public HistoryItem(Integer id, String code, Timestamp time) {
        this.id = id;
        this.code = code;
        this.time = time;
    }

    public HistoryItem() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "HistoryItem{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", time=" + time +
                '}';
    }
}
