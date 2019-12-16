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
     *
     * @param cart 购物车
     * @param code 待添加的产品代码
     * @return 新的购物车
     */
    private List<CartItem> addToCart(List<CartItem> cart, String code) {
        try {
            Printer p = connector.getPrinterByCode(code);
            if (cart == null) {//购物车之前为空
                cart = new ArrayList<>();
                cart.add(new CartItem(1, code, p.getPrice()));
            } else {//购物车非空
                boolean added = false;
                for (CartItem item : cart) {
                    if (item.getCode().equals(code)) {//之前加过相同产品
                        item.setNumber(item.getNumber() + 1);
                        added = true;
                        logger.info("add num, now:" + item.toString());
                    }
                }
                if (!added) {//第一次添加此商品
                    cart.add(new CartItem(1, code, p.getPrice()));
                    logger.info("newly add:" + code);
                }
            }
        } catch (SQLException e) {
            logger.warning(e.toString());
        }
        return cart;
    }

    /**
     * 计算购物车产品总价
     *
     * @param cart 购物车
     * @return 购物车中产品总价
     */
    private int calcSum(List<CartItem> cart) {
        int sum = 0;
        if (cart != null)
            for (CartItem item : cart) {
                sum += item.getNumber() * item.getPrice();
            }
        return sum;
    }

    @RequestMapping("/cart")
    public String gotoCart(HttpServletRequest request, Model model) {
        logger.info("in cart...");
        String id = request.getParameter("id");
        String addCode = request.getParameter("addCode");
        List<CartItem> cart = connector.getCartItems(id);
        if (addCode != null) {//添加商品入购物车
            List<CartItem> newCart = addToCart(cart, addCode);
            connector.updateCart(id, newCart);
            model.addAttribute("cartResult", "商品加购成功！");
        }
        try {
            User user = connector.getUserById(id);
            model.addAttribute("user", user);
        } catch (SQLException e) {
            logger.warning(e.toString());
        }
        cart = connector.getCartItems(id);//获取新购物车信息
        int sum = calcSum(cart);
        model.addAttribute("sum", sum);
        model.addAttribute("cartList", cart);
        return "../../include/cart";
    }

    @RequestMapping("/buyAll")
    public String buyAll(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        String mailbox = request.getParameter("mail");
        List<CartItem> cart;
        try {
            User user = connector.getUserById(id);
            cart = connector.getCartItems(id);
            int sum = calcSum(cart);
            if (user.getMoney() > sum) {
                int newMoney = user.getMoney() - sum;
                connector.updateMoney(id, Integer.toString(newMoney));
                connector.updateCart(id, null);
                model.addAttribute("cartResult", "购买成功！");
                logger.info("buy success");
            } else {
                model.addAttribute("cartResult", "余额不足，购买失败！");
                logger.info("buy fail...");
            }
            model.addAttribute("user", user);
        } catch (SQLException e) {
            logger.info(e.toString());
        }
        cart = connector.getCartItems(id);//获取新购物车信息
        int sum = calcSum(cart);
        model.addAttribute("sum", sum);
        model.addAttribute("cartList", cart);
        return "../../include/cart";
    }

    @RequestMapping("/clearCart")
    public String clearCart(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        try {
            connector.updateCart(id, null);
            User user = connector.getUserById(id);
            model.addAttribute("user", user);
            model.addAttribute("sum", 0);
            model.addAttribute("cartList", null);
        } catch (SQLException e) {
            logger.warning(e.toString());
        }
        return "../../include/cart";
    }
}
