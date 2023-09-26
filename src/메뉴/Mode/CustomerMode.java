package 메뉴.Mode;

import 메뉴.DAO.CustomerDAO;
import 메뉴.DAO.ReviewDAO;
import 메뉴.VO.OrderVO;
import java.util.List;
import java.util.Scanner;


public class CustomerMode {
    CustomerDAO cDAO = new CustomerDAO();
    boolean isCustomer = false;
    OrderMode orderMode = new OrderMode();
    Scanner sc = new Scanner(System.in);
    ReviewDAO reviewDAO = new ReviewDAO();

    public void runCustomerMode() {
        while (true) {
            if (!isCustomer) {
                System.out.print("아이디를 입력하세요 : ");
                String USER_ID = sc.next();
                System.out.print("비밀번호를 입력하세요 : ");
                String USER_PW = sc.next();
                if (cDAO.isC(USER_ID, USER_PW)) {
                    isCustomer = true;
                    customerReturn();
                } else {
                    System.out.println("다시 입력해주세요.");
                    continue;
                }
            }
        }
    }

    public void customerReturn() {
        System.out.println("=".repeat(10) + "이용자 메뉴 선택" + "=".repeat(10));
        while (true) {
            System.out.print("[1] 주문하기 [2] 주문내역 조회 [3]리뷰 [4] 종료: ");
            int sel = sc.nextInt();
            switch (sel) {
                case 1:
                    // 주문하기
                    orderMode.runOrderMode();
                    List<OrderVO> list = orderMode.orderSelect();
                    orderMode.orderSelectPrint(list);
                    break;
                case 2:
                    //주문내역
                    List<OrderVO> viewList = orderMode.viewOrder();
                    orderMode.viewOrederPrint(viewList);
                    break;
                case 3:
                    reviewDAO.orderMode();
                    break;
                case 4:
                    System.exit(12);
            }
        }
    }
}