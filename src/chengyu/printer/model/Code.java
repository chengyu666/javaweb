package chengyu.printer.model;

import java.util.Date;

public class Code {
    /**
     * 错误代码
     * E0000即未找到
     */
    private String code="E0000";
    /**
     * 描述
     * 错误的原因以及解决办法等等
     */
    private String message;
    /**
     * 更新时间
     */
    private Date time;

    public Code(){

    }
    public Code(String code,String message,Date time){
        this.code=code;
        this.message=message;
        this.time=time;
    }
    public void setCode(String code){
        this.code=code;
    }
    public String getCode(){
        return code;
    }
    public void setMessage(String message){
        this.message=message;
    }
    public String getMessage(){
        return message;
    }
    public void setTime(Date time){
        this.time=time;
    }
    public Date getTime(){
        return time;
    }
}
