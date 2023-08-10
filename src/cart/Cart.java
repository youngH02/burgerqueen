package cart;

import product.Menu;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

//Cart는 주문서당 1개 이기 때문에 전부 static 으로 처리
public class Cart {
    private static Map<Menu, Integer> cart = new HashMap<>();
    //private static Map<Integer, Integer> cart = new HashMap<>(); //key : 메뉴ID, value : 갯수
    public Cart() {
    }
    public static boolean isEmpty(){
        return cart.isEmpty();
    }
    public static Map<Menu, Integer> getCart(){
        return cart;
    }
    public static String addCart(Menu product, int count){
        if(!cart.containsKey(product)) {
            cart.put(product, count);
        } else {
            int tmp = cart.get(product);
            cart.replace(product, tmp+count);
        }
        return product.getName()+"*"+count;
    }
    public static void deleteCart(Menu product){
        cart.remove(product);
    }
    public static void viewCart(HashMap<Menu, Integer> cart){
        if(cart.isEmpty()) return ;
        System.out.println("장바구니 출력");
    }
    public static void clearCart(){
        cart.clear();
    }
    public static int sumCart(){
        int allSum = 0;
        Set<Menu> tmpCart = cart.keySet();
        Iterator<Menu> it = tmpCart.iterator();
        while(it.hasNext()){
            Menu nowMenu = it.next();
            int count = cart.get(nowMenu); //갯수
            allSum += (nowMenu.getPrice()*count);
        }
        return allSum;
    }
}