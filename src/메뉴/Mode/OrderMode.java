package 메뉴.Mode;

import 메뉴.Common;
import 메뉴.VO.OrderVO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderMode {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pStmt = null;
    ResultSet rs = null;
    Scanner sc = new Scanner(System.in);

    public List<OrderVO> orderSelect() {    // 주문 후 주문 내역 출력을 위해 list 생성
        List<OrderVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM ORDERLIST ORDER BY ORDER_NUM";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int ORDER_NUM = rs.getInt("ORDER_NUM");
                int STORE_NUM = rs.getInt("STORE_NUM");
                String MENU_NAME = rs.getString("MENU_NAME");
                int AMOUNT = rs.getInt("AMOUNT");
                String DILIVERY_OPTION = rs.getString("DILIVERY_OPTION");
                String PAYMENT_OPTION = rs.getString("PAYMENT_OPTION");
                list.add(new OrderVO(ORDER_NUM, STORE_NUM, MENU_NAME, AMOUNT, DILIVERY_OPTION, PAYMENT_OPTION));
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void orderSelectPrint(List<OrderVO> list) {  //주문 후 정보 조회 list 사용
        for (OrderVO e : list) {
            System.out.print(e.getORDER_NUM() + " ");
            System.out.print(e.getSTORE_NUM() + " ");
            System.out.print(e.getMENU_NAME() + " ");
            System.out.print(e.getAMOUNT() + " ");
            System.out.print(e.getDILIVERY_OPTION() + " ");
            System.out.print(e.getPAYMENT_OPTION() + " ");
            System.out.println();
        }
    }

    public void runOrderMode() { // 주문
        System.out.println("=".repeat(10) + "주문하기" + "=".repeat(10));
        System.out.print("종류 선택 : ");
        String CATEGORY_INFO = sc.next();
        System.out.print("음식점 선택 : ");
        int STORE_NUM = sc.nextInt();
        System.out.print("메뉴 선택 : ");
        String MENU_NAME = sc.next();
        System.out.print("수량 입력 : ");
        int AMOUNT = sc.nextInt();

        String DILIVERY_OPTION = "";
        String PAYMENT_OPTION = "";
        while (!PAYMENT_OPTION.equals("포장") && !PAYMENT_OPTION.equals("배달")) {
            System.out.print("결제방식(포장/배달) : ");
            PAYMENT_OPTION = sc.next();
            if (!PAYMENT_OPTION.equals("포장") && !PAYMENT_OPTION.equals("배달")) {
                System.out.println("올바른 결제방식을 입력하세요.");
            }
        }
        while (!DILIVERY_OPTION.equals("카드") && !DILIVERY_OPTION.equals("현금")) {
            System.out.print("배달방식(카드/현금) : ");
            DILIVERY_OPTION = sc.next();
            if (!DILIVERY_OPTION.equals("카드") && !DILIVERY_OPTION.equals("현금")) {
                System.out.println("올바른 배달방식을 입력하세요.");
            }
        }

        //쿼리문 날리기
        String sql = "INSERT INTO ORDERLIST(ORDER_NUM, DATE_NUM, CATEGORY_INFO, STORE_NUM, MENU_NAME, AMOUNT, DILIVERY_OPTION, PAYMENT_OPTION) VALUES(NEW_ORDER_NUM.NEXTVAL, TO_DATE(SYSDATE,'YY-MM-DD'), ?,?,?,?,?,?)";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, CATEGORY_INFO);
            pStmt.setInt(2, STORE_NUM);
            pStmt.setString(3, MENU_NAME);
            pStmt.setInt(4, AMOUNT);
            pStmt.setString(5, DILIVERY_OPTION);
            pStmt.setString(6, PAYMENT_OPTION);
            int rs = pStmt.executeUpdate();
            if (rs > 0) {
                System.out.println("데이터가 성공적으로 삽입되었습니다.");
            } else {
                System.out.println("데이터 삽입에 실패했습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }

    public List<OrderVO> viewOrder() {    // 주문 후 주문 내역 출력을 위해 list 생성
        List<OrderVO> viewList = new ArrayList<>();
        System.out.println("=".repeat(10) + "주문내역 확인" + "=".repeat(10));
        System.out.print("주문 번호 : ");
        int ORDER_NUM = sc.nextInt();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT O.ORDER_NUM, O.DATE_NUM, O.STORE_NUM, F.MENU_NAME, AMOUNT, F.PRICE, F.PRICE * AMOUNT AS TOTAL_PAYMENT \n" +
                    "FROM FOOD F  \n" +
                    "JOIN ORDERLIST O \n" +
                    "ON F.MENU_NAME = O.MENU_NAME \n" +
                    "WHERE ORDER_NUM = " + ORDER_NUM;
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                ORDER_NUM = rs.getInt("ORDER_NUM");
                Date DATE_NUM = rs.getDate("DATE_NUM");
                int STORE_NUM = rs.getInt("STORE_NUM");
                String MENU_NAME = rs.getString("MENU_NAME");
                int AMOUNT = rs.getInt("AMOUNT");
                int PRICE = rs.getInt("PRICE");
                int TOTAL_PAYMENT = rs.getInt("TOTAL_PAYMENT");
                viewList.add(new OrderVO(ORDER_NUM, DATE_NUM, STORE_NUM, MENU_NAME, AMOUNT, PRICE, TOTAL_PAYMENT));
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return viewList;
    }

    public void viewOrederPrint(List<OrderVO> viewList) {  //주문 후 정보 조회 list 사용
        for (OrderVO e : viewList) {
            System.out.println("=".repeat(10)+ "주문 내역" +"=".repeat(10));
            System.out.println("주문번호 : " + e.getORDER_NUM() + " ");
            System.out.println("주문날짜 : " + e.getDATE_NUM() + " ");
            System.out.println("매장번호 : " + e.getSTORE_NUM() + " ");
            System.out.println("메뉴이름 : " + e.getMENU_NAME() + " ");
            System.out.println("주문수량 : " + e.getAMOUNT() + " ");
            System.out.println("주문가격 : " + e.getPRICE() + " ");
            System.out.println("총 가격 : " + e.getTOTAL_PAYMENT() + " ");
            System.out.println("=".repeat(25));
        }
    }
}