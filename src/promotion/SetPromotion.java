package promotion;

import cart.CartItem;
import cart.CartService;
import discount.DiscountPolicy;

import java.lang.reflect.Array;
import java.util.*;

public class SetPromotion extends Promotion{
    private int[] setItems;
    public SetPromotion(int promotionID, String promotion_name, String promotion_contents, int discount, int[] setItems) {
        super(promotionID, promotion_name, promotion_contents, discount, true);
        this.setItems = setItems;
    }

    public int[] getSetItems() {
        return setItems;
    }

    public boolean checkPromotion(CartService carts){
        //집합으로 변경해서 id 포함되어 있는지 비교
        Set<Integer> cart = carts.getCartIds();
        for(int i = 0; i< setItems.length; i ++){
            if( !cart.contains(setItems[i])) return false; ;
        }
        return true;
    }
}