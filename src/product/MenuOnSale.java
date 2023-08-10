package product;

import discount.DiscountService;

import java.util.ArrayList;
import java.util.Arrays;

public class MenuOnSale {
    public static ArrayList<Menu> allMenu = new ArrayList<>(Arrays.asList(
            new Burger("새우버거",3500,500),
            new Burger("치킨버거",4000,600),
            new Side("감자튀김",1000,300),
            new Side("어니언링",1000,300),
            new Drink("코카콜라",1000,200),
            new Drink("제로콜라",1000,0),
            new Option("케챱",0,100)
    ));


    public static Menu getMenuByIndex(int index){
        for (Menu menu : allMenu){
            if(menu.getIndex() == index)  return menu;
        }
        return null;
    }
    public static Menu getMenuByID(int id){
        for (Menu menu : allMenu){
            if(menu.getIndex() == id)  return menu;
        }
        return null;
    }
}