package chengyu.javaweb.controller;

import chengyu.javaweb.model.CartItem;
import chengyu.javaweb.model.DBConnector;
import chengyu.javaweb.model.Printer;
import chengyu.javaweb.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class CartViewController {
    Logger logger = Logger.getLogger("logger");
    DBConnector connector = new DBConnector();

    @Value("${config.version}")
    String version;

    /**
     * 将code对应的商品加入购物车list
     * @param cart
     * @param code
     * @return
     */
    private List<CartItem> addToCart(List<CartItem> cart, String code){
        try{
            Printer p=connector.getPrinterByCode(code);
            if(cart==null){//购物车之前为空
                cart= new ArrayList<>();
                cart.add(new CartItem(1,code,p.getPrice()));
            }else{//购物车非空
                boolean added=false;
                for(CartItem item:cart){
                    if(item.getCode().equals(code)){//之前加过相同产品
                        item.setNumber(item.getNumber()+1);
                        added=true;
                        logger.info("add num, now:"+item.toString());
                    }
                }
                if(!added){//第一次添加此商品
                    cart.add(new CartItem(1,code,p.getPrice()));
                    logger.info("newly add:"+code);
                }
            }
        }catch (SQLException e){logger.warning(e.toString());}
        return cart;
    }

    @RequestMapping("/cart")
    public String gotoCart(HttpServletRequest request, Model model) {
        logger.info("in cart...");
        String id = request.getParameter("id");
        String addCode=request.getParameter("addCode");
        List<CartItem> cart = connector.getCartItems(id);
        if(addCode!=null){
            List<CartItem> newCart = addToCart(cart,addCode);
            //logger.info("new cart to string:"+newCart.toString());
            connector.updateCart(id,newCart);
            model.addAttribute("addCartResult","商品加购成功！");
        }
        try {
            User user = connector.getUserById(id);
            model.addAttribute("user", user);
        } catch (SQLException e) {
            logger.warning(e.toString());
        }
        int sum = 0;
        if (cart != null)
            for (CartItem item : cart) {
                sum += item.getNumber() * item.getPrice();
            }
        model.addAttribute("sum", sum);
        cart = connector.getCartItems(id);//获取新购物车信息
        model.addAttribute("cartList", cart);
        return "../../include/cart";
    }

}
