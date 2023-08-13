package product;



import discount.DiscountService;

import java.util.ArrayList;
import java.util.Arrays;

public class MenuOnSale {

    public static DiscountService discountService;
    public static ArrayList<Menu> allMenu = new ArrayList<>(Arrays.asList(
            new Burger(1,"새우버거",3500,500),
            new Burger(2,"치킨버거",4000,600),
            new Burger(5,"더블와퍼",7000,900),
            new Side(10,"감자튀김",1000,300),
            new Side(11,"어니언링",1000,300),
            new Drink(20,"코카콜라",1000,200),
            new Drink(21,"제로콜라",1000,0),
            new Option(100,"케챱",0,100)
    ));

    public MenuOnSale() {
        //--- 상품별 할인정보 세팅
        discountService = new DiscountService();
        discountService.makeDiscount(new int[]{1,2}, 2);
        discountService.makeDiscount(10, 1);
        discountService.makeDiscount(11, 0);

        //--- 프로모션 정보 세팅
    }

    public static Menu getMenuByID(int id){
        for (Menu menu : allMenu){
            if(menu.getId() == id)  return menu;
        }
        return null;
    }
    public Menu getMenuByIndex(int index){
        for (Menu menu : allMenu){
            if(menu.getIndex() == index)  return menu;
        }
        return null;
    }

    public int size() {
        return allMenu.size();
    }

    public ArrayList<Menu> getMenu() {
        return allMenu;
    }
}