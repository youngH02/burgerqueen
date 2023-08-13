package order;

import cart.CartItem;
import cart.CartService;
import promotion.Promotion;
import board.*;

import java.util.ArrayList;

public class OrderService {

    public StringBuffer createOrder(CartService cart, ArrayList<Promotion> promotions){
        Order order = new Order(
                (int) (Math.random()*100+1),
                cart, promotions
        );
        return FileUtil.saveToFile(order);
    }
}