package cart;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import product.*;
import promotion.Promotion;

public class CartService {
    private ArrayList<CartItem> cart = new ArrayList<>(); // 실질적인 장바구니


    public boolean isEmpty(){
        return cart.isEmpty();
    }
    public ArrayList<CartItem> getCart(){
        return cart;
    }
    public int cartSize() {return cart.size();}

    public void clearCart(){
        cart.clear();
    }

    public String addCart(Menu menu, int count){
        //------- 카트 순회하면서 있으면, 수량만 추가
        for(int i = 0; i<cart.size(); i++ ){

            if(cart.get(i).getProductId() == menu.getId()){
                cart.get(i).addCount(count);
                return menu.getName()+"*"+count;
            }
        }
        cart.add(new CartItem(menu.getId(), menu.getName(), menu.getPrice(), count, menu.getDiscountPrice()));
        return menu.getName()+"*"+count;
    }
    public void addPromotion(Promotion promotion, int count){
        //System.out.println(promotion.getPromotion_contents()+promotion.getPromotionDiscount());
        cart.add(new CartItem(promotion.getpromotionID(), promotion.getPromotion_contents(), 0,count,promotion.getPromotionDiscount()));
    }
    public void deleteCart(int index){
        cart.remove(index-1);
    }

    public Set<Integer> getCartIds(){
        Set<Integer> cartids = new HashSet<>();
        for(int i = 0; i<cart.size(); i++ ){
            cartids.add(cart.get(i).getProductId());
            }
        return cartids;
    }
    public int sumCart(){
        int allSum = 0;
        for(CartItem cartItem : cart ){
            allSum += cartItem.getTotalPrice();
        }
        return allSum;
    }


}