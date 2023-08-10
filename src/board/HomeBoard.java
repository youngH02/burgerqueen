package board;

import discount.DiscountService;

import java.util.Scanner;

public class HomeBoard {
    private Scanner sc = new Scanner(System.in);
    private String selectNum;
    private String introString = "<🍔BugerQueen Order Service🍔>\n"+
            " (1) 메뉴 선택\n (2) 장바구니\n (3) 할인 정보\n (4) 프로그램 종료";
    MenuBoard mb = new MenuBoard();
   // DiscountService dis = new DiscountService();
    public void selectBoard(){

        System.out.println(introString);
        selectNum = sc.nextLine().trim();
        switch(selectNum){
            case "1" : //메뉴판
                mb.viewMenyByCategory();
                break;
            case "2" : //장바구니
                CartBoard.viewCartBoard();
                break;
            case "3" :
                new DiscountService();
                 //TODO: 이거 처리 해야함
                PrintUtil.printDiscountMenu();
                break;
            case "4" :
                System.out.println("프로그램종료");
                System.exit(1);
            default :
                System.out.println("1~4 숫자를 입력해 주세요.");
        }
        selectBoard();

    }




}