package discount;

import product.Menu;
import product.MenuOnSale;

import java.util.HashMap;

public class DiscountService {
    private static HashMap<Integer, DiscountInfo> discountMenus = new HashMap<>();
    public DiscountService() {
        DiscountInfo disInfoWon = new DiscountInfo(500,"원");
        DiscountInfo disInfo3Won = new DiscountInfo(300,"원");
        DiscountInfo disInfoRate = new DiscountInfo(10,"%");

        makeDiscount(1,disInfoWon);
        makeDiscount(2,disInfo3Won);
        makeDiscount(3,disInfoWon);
        makeDiscount(4,disInfo3Won);
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