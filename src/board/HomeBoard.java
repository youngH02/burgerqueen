package board;

import order.OrderService;
import product.Menu;
import product.MenuOnSale;
import cart.CartService;
import promotion.GeneralPromotion;
import promotion.Promotion;
import promotion.PromotionService;
import promotion.SetPromotion;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class HomeBoard {

    private Scanner sc;
    private String input;
    private int selectNum;
    private MenuOnSale menuOnSale;
    private CartService cartService;
    private PromotionService promotions;
    private OrderService orderService;

    public HomeBoard() {

        this.sc = new Scanner(System.in);
        this.menuOnSale = new MenuOnSale(); //판매중인 메뉴, 할인되는 메뉴 생성
        this.cartService = new CartService();
        this.promotions = new PromotionService();
//        this.promotions = new ArrayList<>(Arrays.asList(
//                new GeneralPromotion(1, "코드스테이츠 수강생 프로모션", "[프로모션]아이스크림"),
//                new GeneralPromotion(2, "어린이 프로모션", "[프로모션]장난감"),
//                new SetPromotion(10,"햄버거 음료세트 할인","[프로모션] 세트할인",-500,new int[]{1,3})
//        ));
        this.orderService = new OrderService();
    }

    public void moveToHome(){
        System.out.println(PrintUtil.introString);
        input = sc.nextLine();
        try{
        switch(input){
            case "1":
                viewMenyByCategory();
                break;
            case "2" :
                viewCartItems();
                break;
            case "3" :
                PrintUtil.printDiscountMenu();
                break;
            case "4":
                System.exit(0);
            default:
                System.out.println(PrintUtil.inputError);
               // moveToHome();
        }}
        catch(Exception e){
            System.out.println("입력값 오류 : 숫자입력!!! "+e.getMessage());
        }
        moveToHome();
    }

    private void viewMenyByCategory() throws Exception{
        int displayIndex = PrintUtil.printMenuByCategory(PrintUtil.displayCategoryOrder, menuOnSale);

        System.out.print("메뉴 번호를 입력해주세요. ");

        input = sc.nextLine();
        selectNum = Integer.parseInt(input.trim());
        if(selectNum == displayIndex) {
            moveToHome(); //종료 입력했으면 홈 메뉴판으로 이동
        }

        Menu selectMenu = menuOnSale.getMenuByIndex(selectNum);
        if( selectMenu != null) {
            System.out.print("[📣] 수량을 입력해 주세요. ");
            input = sc.nextLine();
            selectNum = Integer.parseInt(input.trim());
            cartService.addCart(selectMenu,selectNum );
            System.out.println("[📣] "+selectMenu.getName()+"*"+selectNum+"를(을) 장바구니에 담았습니다.");
        }else{
            System.out.println(PrintUtil.inputError);
        }

        viewMenyByCategory();
    }


    private void viewCartItems() throws Exception {
        PrintUtil.printCartItems(cartService.getCart());
        input  = sc.nextLine().trim();

        switch (input) {
            case "1":
                moveToHome();
                break;
            case "2": // 상품 하나를 삭제
                System.out.println("삭제할 상품의 번호를 입력해주세요.");
                input = sc.nextLine();
                selectNum = Integer.parseInt(input.trim());
                if(selectNum <= cartService.cartSize()) cartService.deleteCart(selectNum);
                else System.out.println("장바구니에 없는 상품입니다.");
                break;
            case "3": // 장바구니 비우기
                cartService.clearCart();
                break;
            case "4": // 장바구니 상품 주문
                processOrder();
                break;
            case "5":
                System.exit(0);
            default:
                System.out.println(PrintUtil.inputError);
        }
        viewCartItems();
    }
    private void processOrder() throws Exception{
        if(cartService.isEmpty()){
            System.out.println("장바구니가 비어있습니다. 상품을 담아주세요.");
            moveToHome();
        }
        PrintUtil.printAllPromotion(promotions.getGeneralPromotion());
        input = sc.nextLine();
        selectNum = Integer.parseInt(input.trim());
        //프로모션 적용 주문서 생성
        StringBuffer stringBuffer = null;
        stringBuffer = orderService.createOrder(cartService,promotions.findapplyPromotion(selectNum, cartService));
                //((selectNum-1) < promotions.size())? (promotions.get(selectNum-1)): null);
        //영수증 출력
        System.out.println(stringBuffer.toString());
        //장바구니 초기화
        cartService.clearCart();
        System.out.println("주문이 완료되었습니다. 홈으로 돌아갑니다.");
        input = sc.nextLine();
        moveToHome();
    }




}