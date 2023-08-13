package order;

import cart.CartItem;
import cart.CartService;
import promotion.Promotion;
import promotion.SetPromotion;

import java.util.ArrayList;

public class Order {
    private int orderId;
    private CartService cart;
    private ArrayList<Promotion> promotions;
    //private ArrayList<Promotion> promotions;
    int paymentPrice;

    public Order(int orderId, CartService cart, ArrayList<Promotion> promotions ) {
        this.orderId = orderId;
        this.cart = cart;
        this.promotions = promotions;
        addPromotion();
        this.paymentPrice = calcuatePaymentPrice();
    }

    public int getOrderId() {
        return orderId;
    }

    public CartService getCart() {
        return cart;
    }
    public void addPromotion() { //선택한 프로모션 적용

        for(Promotion promotion : promotions){
            if (promotion != null) {
            cart.addPromotion(promotion, 1);
            //if(!promotion.isOptional()) cart.addPromotion(promotion, 1);
            //else promotion.checkPromotion(cart);
        }
//        if (promotion != null) {
//            cart.addPromotion(promotion, 1);
//
        }
    }
    private int calcuatePaymentPrice() {
        return cart.getCart().stream()
                .mapToInt(item->item.getTotalPrice())
                .sum();
    }
    public int getPaymentPrice() {
        return paymentPrice;
    }
}