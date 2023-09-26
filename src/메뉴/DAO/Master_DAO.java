package 메뉴.DAO;

import 메뉴.VO.Master_VO;
import 메뉴.Common;
import 메뉴.VO.MenuVO;
import 메뉴.VO.OrderVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Master_DAO {
    Connection conn = null; //db연결
    Statement stmt = null;
    PreparedStatement pstmt = null; //쿼리문 날리기. 크리에이트는 복잡해서 프리페어 사용
    ResultSet rs = null;
    Scanner sc = new Scanner(System.in);

    //점포 회원가입 (회원가입에서 점포회원가입 선택시.)
    public void masterInsert() {
        System.out.println("등록할 점포 정보를 입력하세요.");
        System.out.print("사업자번호 : ");
        int bizNum = sc.nextInt();
        System.out.print("점포번호 : ");
        String storeNum = sc.next(String.valueOf(Integer.parseInt("NEW_STORE_NUM")));
        System.out.print("비밀번호 : ");
        String pw = sc.next();
        System.out.print("점포명 : ");
        String store = sc.next();
        System.out.print("대표자 성함 : ");
        String name = sc.next();
        String sql = "INSERT INTO MASTER_INFO(BIZNUM, STORE_NUM, MASTER_PW, MASTER_STORE, MASTER_NAME) VALUES(?,?,?,?,?)";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bizNum);
            pstmt.setString(2,storeNum);
            pstmt.setString(3, pw);
            pstmt.setString(4, store);
            pstmt.setString(5, name);
            int rst = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);
    }

    //점포 총매출 확인 [2]
    public List<OrderVO> viewTotal() {
        List<OrderVO> totalList = new ArrayList<>();
        System.out.println("매출을 조회할 가게 번호를 입력하세요.");
        System.out.print("가게 번호 : ");
        int STORE_NUM = sc.nextInt();
        String sql = "SELECT F.CATEGORY_INFO , M.STORE_NUM, F.MENU_NAME, (F.PRICE * O.AMOUNT) AS TOTAL \n" +
                "FROM ORDERLIST O \n" +
                "INNER JOIN FOOD F ON F.MENU_NAME = O.MENU_NAME\n" +
                "INNER JOIN MASTER_INFO M ON M.STORE_NUM = O.STORE_NUM\n" +
                "AND M.STORE_NUM = " + STORE_NUM;
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String CATEGORY_INFO = rs.getString("CATEGORY_INFO");
                STORE_NUM = rs.getInt("STORE_NUM");
                String MENU_NAME = rs.getString("MENU_NAME");
                int TOTAL = rs.getInt("TOTAL");
                OrderVO vo = new OrderVO(CATEGORY_INFO, STORE_NUM, MENU_NAME, TOTAL);
                totalList.add(vo);
            }
            Common.close(rs);
            Common.close(pstmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalList;
    }



    public void viewTotalPrint(List<OrderVO> totalList) {
        for (OrderVO e : totalList) {
            System.out.println("=".repeat(10) + "매출조회" + "=".repeat(10));
            System.out.println("음식종류 : " + e.getCATEGORY_INFO() + " ");
            System.out.println("매장번호 : " + e.getSTORE_NUM() + " ");
            System.out.println("메뉴이름 : " + e.getMENU_NAME() + " ");
            System.out.println("총 메출 : " +e.getTOTAL() + " ");
            System.out.println();
        }
    }


    //관리자 로그인
    public boolean isM(int bizNum, String pw) {
        boolean isMaster = false;
        try{
            conn = Common.getConnection();
            String sql = "SELECT COUNT(*) FROM MASTER_INFO WHERE BIZNUM = ? AND MASTER_PW = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bizNum);
            pstmt.setString(2, pw);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                if(count > 0) {
                    isMaster = true;
                }
            }
            Common.close(rs);
            Common.close(pstmt);
            Common.close(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isMaster;
    }
}