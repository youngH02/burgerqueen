package board;

import product.*;
import Cart.*;

import java.util.*;

public class MenuBoard {
    //static ArrayList<Menu> allMenu = new ArrayList<>();

    static String[] displayCategoryOrder = {"햄버거","사이드","음료","옵션"};
    static Scanner sc = new Scanner(System.in);

    public static void viewMenyByCategory(){
        int displayIndex = 1;
        Map<String, String> emoji = Map.of("햄버거","🍔","사이드","🍟","음료","🥤","옵션","🥫");
        ArrayList<Menu> allMenu = AllMenu.allMenu;

        System.out.println("[🔻] 메뉴");
        System.out.println("-".repeat(20));
        for (int i = 0; i< displayCategoryOrder.length; i++) {
            if (emoji.containsKey(displayCategoryOrder[i])); System.out.print(emoji.get(displayCategoryOrder[i]));
            System.out.println(displayCategoryOrder[i]);
            for(int all = 0; all<allMenu.size(); all++) {
                Menu nowMenu = allMenu.get(all);
                if (nowMenu.getCategory() == displayCategoryOrder[i]) {
                    nowMenu.setIndex(displayIndex++);
                    PrintUtil.printAlign(nowMenu);
                    System.out.println();
                }
            }
            System.out.println();
        }
        System.out.println("("+displayIndex+") " + "주문 종료");
        System.out.println("-".repeat(20));
        System.out.print("메뉴 번호를 입력해주세요. ");
        String selectMenuNum = sc.nextLine();
        int selectNum = Integer.parseInt(selectMenuNum.trim());
        Menu selectMenu = AllMenu.getMenuByIndex(selectNum);
        if(selectNum == displayIndex) return; //종료 입력했으면 홈 메뉴판으로 이동
        if( selectMenu != null) {
            System.out.print("[📣] 수량을 입력해 주세요. ");
            selectMenuNum = sc.nextLine();
            int countNum = Integer.parseInt(selectMenuNum.trim());
            Cart.addCart(selectMenu,countNum);
            System.out.println("[📣] "+selectMenu.getName()+"*"+countNum+"를(을) 장바구니에 담았습니다.");
        }else{
            System.out.println("메뉴판 내에서 골라주세요.");
        }
        viewMenyByCategory();
    }


}