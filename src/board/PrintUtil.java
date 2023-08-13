package board;

import discount.DiscountPolicy;
import discount.DiscountService;
import product.Menu;
import product.MenuOnSale;
import cart.CartItem;
import promotion.Promotion;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class PrintUtil {

    static String introString = "\n\n<🍔BugerQueen order.Order Service🍔>\n"+
            " (1) 메뉴 선택\n (2) 장바구니\n (3) 할인 정보\n (4) 프로그램 종료";
    static String introPromotion= "프로모션 정보\n (1) 코드스테이츠 수강생 프로모션\n (2) 어린이 프로모션\n (3) 선택 안함";
    static String introCartBoard = " (1) 홈으로 돌아가기\n (2) 상품 삭제\n (3) 장바구니 비우기\n (4) 장바구니 상품 주문\n (5) 프로그램 종료";

    static String[] displayCategoryOrder = {"햄버거","사이드","음료","옵션"};
    static Map<String, String> emoji = Map.of("햄버거","🍔","사이드","🍟","음료","🥤","옵션","🥫");
    static String inputError = "잘못 입력하였습니다.";
    static String dash = "-".repeat(50);
    static String cartTitle= "No  상품명         단가   할인금액    소비자가   수량    금액";
    static DecimalFormat df = new DecimalFormat("###,###");

    public static int printMenuByCategory(String[] category, MenuOnSale allMenu){

        System.out.println("[🔻] 메뉴");
        System.out.println(PrintUtil.dash);

        int displayIndex = 1;
        for (int i = 0; i< category.length; i++) {
            if (emoji.containsKey(category[i])); System.out.print(emoji.get(category[i]));
            System.out.println(category[i]); //카테고리 출력
            for(int all = 0; all<allMenu.size(); all++) {
                Menu nowMenu = allMenu.getMenu().get(all);
                if (nowMenu.getCategory() == displayCategoryOrder[i]) {
                    nowMenu.setIndex(displayIndex++);
                    PrintUtil.printAlign(nowMenu);
                    System.out.println();
                }
            }
            System.out.println();

        }
        System.out.println("("+displayIndex+") " + "주문 종료");
        System.out.println(PrintUtil.dash);
        return displayIndex;

    }
    public static void printAlign(Menu nowMenu){
        System.out.printf("(%1d) %-6s %7dKcal %7d원",nowMenu.getIndex(),nowMenu.getName(),nowMenu.getCalorie(),nowMenu.getPrice());
    }


    public static void printCartItems(ArrayList<CartItem> cart){
        System.out.println("💰장바구니");
        System.out.println(dash);
        System.out.println(cartTitle);
        int index=1;
        int sum = 0;
        for(CartItem cartItem : cart){
            sum += cartItem.getTotalPrice();
            System.out.printf("(%1d) %-7s %7d원 %5d원 %7d원 %4d %7d원"
                    , index++, cartItem.getProductName(),
                    cartItem.getCost(), cartItem.getDiscountPrice(), cartItem.getRetailPrice(), cartItem.getCount(), cartItem.getTotalPrice());
            System.out.println();
        }
        System.out.println(dash);
        System.out.println("합계: : "+df.format(sum)+"원");
        System.out.println();
        System.out.println(introCartBoard);

    }
    public static void printDiscountMenu(){
        System.out.println("상품 할인 정보");

        Set<Map.Entry<Integer, DiscountPolicy>> hashKeySet = DiscountService.getDiscountMenus().entrySet();
        Iterator<Map.Entry<Integer, DiscountPolicy>> it = hashKeySet.iterator();
        while(it.hasNext())
        {
            Map.Entry<Integer, DiscountPolicy> ittmp = it.next();
            Menu menu = MenuOnSale.getMenuByID(ittmp.getKey());
            System.out.println(menu.getName()+" "+ittmp.getValue().getDiscountRate()+ittmp.getValue().getDiscountType()+" 할인" );
        }
    }


    public static void printAllPromotion(ArrayList<Promotion> promotions) {
        System.out.println("프로모션 정보");
        for(Promotion promotion: promotions){
            System.out.printf("(%d) %s", promotion.getpromotionID(), promotion.getpromotion_name());
            System.out.println();
        }

        System.out.printf("(%d) 선택 안함",promotions.size()+1);
        System.out.println();
    }
}