package discount;

import discount.DiscountPolicy;
import product.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DiscountService {
    private ArrayList<DiscountPolicy> discountPolicies = new ArrayList<>(Arrays.asList(
            new DiscountPolicy(500,"원"),
            new DiscountPolicy(300,"원"),
            new DiscountPolicy(10,"%")
    ));


    private static HashMap<Integer, DiscountPolicy> discountMenus = new HashMap<>();
    public void makeDiscount(int menuID, int disPolicyIndex){
        discountMenus.put(menuID, getDiscountPolicy(disPolicyIndex));
        Menu menu = MenuOnSale.getMenuByID(menuID);
        menu.setDiscount(discountPolicies.get(disPolicyIndex));
    }

    public void makeDiscount(int[] menuIDs, int disPolicyIndex){
        for(int menuID : menuIDs){
            discountMenus.put(menuID, getDiscountPolicy(disPolicyIndex));
            Menu menu = MenuOnSale.getMenuByID(menuID);
            menu.setDiscount(discountPolicies.get(disPolicyIndex));
        }
    }

    private DiscountPolicy getDiscountPolicy(int index){
        return discountPolicies.get(index);
    }

    public static HashMap<Integer, DiscountPolicy> getDiscountMenus() {
        return discountMenus;
    }
}