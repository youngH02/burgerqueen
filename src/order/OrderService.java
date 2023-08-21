package order;

import cart.CartItem;
import cart.CartService;
import delivery.Delivery;
import delivery.DeliveryService;
import promotion.Promotion;
import board.*;

import java.util.ArrayList;

public class OrderService {

    public StringBuffer createOrder(CartService cart, ArrayList<Promotion> promotions){
        return createOrder(cart, promotions, null);
    }
    public StringBuffer createOrder(CartService cart, ArrayList<Promotion> promotions, Delivery delivery){
        Order order = new Order(
                (int) (Math.random()*100+1),
                cart, promotions
        );
        if(delivery !=null) delivery.addDeliveryToOrder(order);
        order.setPaymentPrice();
        return FileUtil.saveToFile(order);
    }
}