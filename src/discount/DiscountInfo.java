package discount;
import product.*;

import java.util.HashMap;

public class DiscountInfo {
    //Menu menu;
    int menuId;
    int discountRate;
    String discountType;
 //   enum discountType {원, %}

    //static HashMap<Integer, String> discountMenus = new HashMap<>();

//    public DiscountInfo() {
//        makeDiscountInfo(500,"원");
//        makeDiscountInfo(10,"%");
//    }
    public DiscountInfo(int discountRate, String discountType){
        //this.menuId = menuId;
       //Menu menu = MenuOnSale.getMenuByID(menuId);
       // menu.setDiscount(true);
        //this.menu = menu;
        this.discountType = discountType;
        this.discountRate = discountRate;
    }

    public int getDiscountRate() {
        return discountRate;
    }

    public String getDiscountType() {
        return discountType;
    }
}