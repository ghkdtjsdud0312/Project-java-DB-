package 메뉴.DAO;
import 메뉴.VO.MenuVO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import 메뉴.Common;
import java.sql.Connection;

public class MenuDAO {
    Connection conn = null;         // DB 연결
    Statement stmt = null;          // 쿼리문을 날리기 위함
    PreparedStatement pstmt = null; // 쿼리문을 날리기 위함
    ResultSet rs = null;            // 결과값을 받는 객체
    Scanner sc = new Scanner(System.in);

    public void menuDelete() {
        System.out.print("삭제할 메뉴의 이름을 입력하세요 : ");
        String mName = sc.next();
        String sql = "DELETE FROM FOOD WHERE MENU_NAME = ?";

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, mName);
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("메뉴 삭제가 완료되었습니다.");
            } else {
                System.out.println("메뉴를 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);
    }

    public void menuInsert() {
        System.out.print("가게(음식) 종류  : ");
        String CATEGORY_INFO = sc.next();
        System.out.print("가게 번호 : ");
        int STORE_NUM = sc.nextInt();
        System.out.print("메뉴 이름 : ");
        String MENU_NAME = sc.next();
        System.out.print("가격 : ");
        int PRICE = sc.nextInt();
        String sql = "INSERT INTO FOOD(CATEGORY_INFO, STORE_NUM, MENU_NAME, PRICE) VALUES (?,?,?,?) ";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, CATEGORY_INFO);
            pstmt.setInt(2, STORE_NUM);
            pstmt.setString(3, MENU_NAME);
            pstmt.setInt(4, PRICE);
            int rst = pstmt.executeUpdate();    // 실행 결과가 정수 값으로 반환 됨 (영향을 받은 행의 개수가 반환 됨)
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);
    }

    public List<MenuVO> menuSelect() {
        List<MenuVO> list = new ArrayList<>();
        System.out.print("가게 번호를 입력하세요 : ");
        int STORE_NUM = sc.nextInt();
        String query = "SELECT * FROM FOOD WHERE STORE_NUM=" + STORE_NUM;
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()) {
                String CATEGORY_INFO = rs.getString("CATEGORY_INFO");
                STORE_NUM = rs.getInt("STORE_NUM");
                String MENU_NAME = rs.getString("MENU_NAME");
                int PRICE = rs.getInt("PRICE");
                MenuVO vo = new MenuVO(CATEGORY_INFO, STORE_NUM, MENU_NAME, PRICE);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public void menuSelectPrint(List<MenuVO> list) {
        for (MenuVO e : list) {
            System.out.print(e.getSTORE_NUM() + " ");
            System.out.print(e.getCATEGORY_INFO() + " ");
            System.out.print(e.getMENU_NAME() + " ");
            System.out.print(e.getPRICE() + " ");
            System.out.println();
        }
    }
}