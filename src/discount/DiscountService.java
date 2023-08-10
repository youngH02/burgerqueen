package discount;

import product.Menu;
import product.MenuOnSale;

import java.util.HashMap;

public class DiscountService {
    private static HashMap<Integer, DiscountInfo> discountMenus = new HashMap<>();
    public DiscountService() {
        //할인 정책이 늘어나면 어떻게 되지?
        DiscountInfo disInfoWon = new DiscountInfo(500,"원");
        DiscountInfo disInfoRate = new DiscountInfo(10,"%");



        //policy,provider, service -> 묶음처리


        //상품이 많아지면 어떻게되지?
        //할인 정책
        makeDiscount(1,disInfoWon);
        makeDiscount(2,disInfoWon);
        makeDiscount(3,disInfoWon);
        makeDiscount(4,disInfoWon);
        makeDiscount(5,disInfoRate);
        makeDiscount(6,disInfoRate);


    }
    private void makeDiscount(int menuID, DiscountInfo disInfo ){
        discountMenus.put(menuID, disInfo);
        Menu menu = MenuOnSale.getMenuByID(menuID);
        menu.setDiscount(true);
    }

    public static HashMap<Integer, DiscountInfo> getDiscountMenus() {
        return discountMenus;
    }
}