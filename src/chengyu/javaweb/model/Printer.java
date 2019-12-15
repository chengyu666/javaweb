package chengyu.javaweb.model;

public class Printer {
    /**
     * 机器型号
     * E0000即未找到
     */
    private String code="E0000";
    /**
     * 描述
     * 对于机器情况的描述
     */
    private String information;
    /**
     * 更新时间
     */
    private Integer price;

    public Printer(){

    }
    public Printer(String code, String information, Integer price){
        this.code=code;
        this.information = information;
        this.price = price;
    }
    public void update(String code,String information,Integer price){
        setCode(code);
        setInformation(information);
        setPrice(price);
    }
    public void setCode(String code){
        this.code=code;
    }
    public String getCode(){
        return code;
    }
    public void setInformation(String information){
        this.information = information;
    }
    public String getInformation(){
        return information;
    }
    public void setPrice(Integer price){
        this.price = price;
    }
    public Integer getPrice(){
        return price;
    }
    public String toString(){
        return code+", info:"+ information +", price:"+ price;
    }
}
