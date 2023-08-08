package board;

import product.Menu;
import Cart.*;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class PrintUtil {
    static DecimalFormat df = new DecimalFormat("###,###");
    public static void printAlign(Menu nowMenu){
        System.out.printf("(%1d) %-7s %7dKcal %7d원",nowMenu.getIndex(),nowMenu.getName(),nowMenu.getCalorie(),nowMenu.getPrice());
    }

    public static Map<Integer, Menu> printCart(){
        int allSum = 0;
        int index = 1;
        Map<Integer, Menu> cartOrder = new HashMap<>();
        System.out.println("💰장바구니");
        System.out.println("-".repeat(50));
        System.out.println("No  상품명          단가    수량      금액");

        Set<Menu> tmpCart = Cart.getCart().keySet();
        Iterator<Menu> it = tmpCart.iterator();
        while(it.hasNext()){
            Menu nowMenu = it.next();
            int count = Cart.getCart().get(nowMenu); //갯수
            allSum += (nowMenu.getPrice()*count);
            cartOrder.put(index, nowMenu);
            System.out.printf("(%1d) %-7s %7d원 %4d %7d원", index++, nowMenu.getName(), nowMenu.getPrice(), count, nowMenu.getPrice()*count);
            System.out.println();
        }

        System.out.println("-".repeat(50));
        System.out.println("합계: : "+df.format(allSum)+"원");
        return cartOrder;
    }
    public static void printCartSum(){

        System.out.println("합계: : "+ Cart.sumCart()+"원");
    }
}