package board;

import discount.DiscountService;
import product.Menu;
import product.MenuOnSale;

import java.util.ArrayList;
import java.util.Scanner;

import static product.MenuOnSale.allMenu;

public class HomeBoard {
    private Scanner sc = new Scanner(System.in);
    private String selectNum;
    private String introString = "<🍔BugerQueen Order Service🍔>\n"+
            " (1) 메뉴 선택\n (2) 장바구니\n (3) 할인 정보\n (4) 프로그램 종료";
    MenuBoard mb;


    public HomeBoard() {
        new DiscountService();
    }

    // DiscountService dis = new DiscountService();
    public void selectBoard(){

        System.out.println(introString);
        selectNum = sc.nextLine().trim();
        switch(selectNum){
            case "1" : //메뉴판
                MenuBoard.viewMenyByCategory();
                break;
            case "2" : //장바구니
                CartBoard.viewCartBoard();
                break;
            case "3" :
                PrintUtil.printDiscountMenu();
                break;
            case "4" :
                System.out.println("프로그램종료");
                System.exit(0);
            default :
                System.out.println("1~4 숫자를 입력해 주세요.");
        }
        selectBoard();

    }




}