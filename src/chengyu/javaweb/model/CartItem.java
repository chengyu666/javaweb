package chengyu.javaweb.model;

public class CartItem {

    private Integer number;
    private String code;
    private Integer price;

    public CartItem(Integer n, String c, Integer p){
        this.number=n;
        this.code=c;
        this.price=p;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "number=" + number +
                ", code='" + code + '\'' +
                ", price=" + price +
                '}';
    }
}
