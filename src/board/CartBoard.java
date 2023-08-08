package board;
import Cart.*;
import product.AllMenu;
import product.Menu;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CartBoard {
    private static Scanner sc = new Scanner(System.in);
    private static String inputString;

    public static void viewCartBoard(){
        Map<Integer, Menu> cartOrder = new HashMap<>();
        cartOrder = PrintUtil.printCart();
        System.out.println(" (1) 홈으로 돌아가기\n (2) 상품 삭제\n (3) 장바구니 비우기\n (4) 장바구니 상품 주문\n (5) 프로그램 종료");
        String inputString = sc.nextLine();
        switch(inputString){
            case "1" : //메뉴판으로 돌아가기
                return;
            case "2" : //장바구니 상품삭제
                System.out.println("삭제할 상품의 번호를 입력해주세요.");
                int selectNum = Integer.parseInt(sc.nextLine().trim());
                if(cartOrder.containsKey(selectNum)) Cart.deleteCart(cartOrder.get(selectNum));
                else System.out.println("장바구니에 없는 상품입니다.");
                break;
            case "3" :
                Cart.clearCart();
                break;
            case "4" :
                System.out.println("상품주문");
                break;
            case "5" :
                System.out.println("프로그램종료");
                System.exit(1);
            default :
                System.out.println("1~5 숫자를 입력해 주세요.");
        }
        viewCartBoard();

    }
}