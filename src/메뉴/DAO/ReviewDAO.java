package 메뉴.DAO;

import 메뉴.Common;
import 메뉴.VO.ReviewVO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReviewDAO {
    static Connection conn = null;          // DB 연결
    static Statement stmt = null;           // 쿼리문을 날리기 위함
    PreparedStatement pstmt = null;         // 쿼리문을 날리기 위함
    static ResultSet rs = null;            // 결과값을 받는 객체
    Scanner sc = new Scanner(System.in);

    //  어떠한 가게의 리뷰를 보기 위한 리스트 저장 메서드: 해당 가게의 리뷰 전체 조회
    public List<ReviewVO> viewReview() {
        List<ReviewVO> list = new ArrayList<>();
        System.out.print("조회할 매장번호 : ");
        int STORE_NUM = sc.nextInt();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT REVIEW_NUM, F.STORE_NUM, F.MENU_NAME, SCORE, R.WRITE_REVIEW, C.USER_ID\n" +
                    "FROM REVIEW R\n" +
                    "INNER JOIN FOOD F ON R.MENU_NAME = F.MENU_NAME\n" +
                    "INNER JOIN CUSTOMER_INFO C ON R.USER_ID = C.USER_ID\n" +
                    "AND F.STORE_NUM = " + STORE_NUM + "\n" +
                    "ORDER BY REVIEW_NUM";

            rs = stmt.executeQuery(query);
            while(rs.next()) {
                int REVIEW_NUM = rs.getInt("REVIEW_NUM");
                STORE_NUM = rs.getInt("STORE_NUM");
                String MENU_NAME = rs.getString("MENU_NAME");
                int SCORE = rs.getInt("SCORE");
                String WRITE_REVIEW = rs.getString("WRITE_REVIEW");
                String USER_ID = rs.getString("USER_ID");
                ReviewVO vo = new ReviewVO(REVIEW_NUM, STORE_NUM, MENU_NAME, SCORE,  WRITE_REVIEW, USER_ID);
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

    //viewReview로 저장된 리스트를 조회하는 메서드.
    public void viewReviewPrint(List<ReviewVO> list) {
        for (ReviewVO e : list) {
            System.out.println("=".repeat(10) + "가게 리뷰 조회" + "=".repeat(10));
            System.out.println("리뷰번호 : " + e.getREVIEW_NUM() + " ");
            System.out.println("매장번호 : " + e.getSTORE_NUM() + " ");
            System.out.println("메뉴이름 : " + e.getMENU_NAME() + " ");
            System.out.println("메뉴평가 : " + e.getSCORE() + " ");
            System.out.println("리뷰내용 : " + e.getWRITE_REVIEW() + " ");
            System.out.println("작성자 :" + e.getUSER_ID() + " ");
            System.out.println("=".repeat(30));
            System.out.println();
        }
    }


    // 리뷰 쓰기
    public void writeReview() {
        List<ReviewVO> writelist = new ArrayList<>();
        System.out.print("리뷰를 쓸 아이디 입력 : ");
        String USER_ID = sc.next();
        System.out.print("리뷰를 쓸 매장번호 : ");
        int STORE_NUM = sc.nextInt();
        System.out.print("리뷰를 쓸 메뉴이름 : ");
        String MENU_NAME = sc.next();
        System.out.print("별점(5점) : ");
        int SCORE = 0;
        while (true) {
            SCORE = sc.nextInt();
            if (SCORE > 0 && SCORE < 6) {
                SCORE = SCORE;
                break;
            } else {
                System.out.println("다시 입력하시오.");
            }
        }
        System.out.print("리뷰 쓰기(30자 이하) : ");
        String WRITE_REVIEW = "";
        while (true) {
            sc.nextLine();
            WRITE_REVIEW = sc.nextLine();
            if (WRITE_REVIEW.length() <= 30) {
                WRITE_REVIEW = WRITE_REVIEW;
                break;
            }
        }
        String query = "INSERT INTO REVIEW (REVIEW_NUM, STORE_NUM, MENU_NAME, SCORE, WRITE_REVIEW, USER_ID) VALUES (NUMBER_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(query);
            stmt = conn.createStatement();
            pstmt.setInt(1, STORE_NUM);
            pstmt.setString(2, MENU_NAME);
            pstmt.setInt(3, SCORE);
            pstmt.setString(4, WRITE_REVIEW);
            pstmt.setString(5, USER_ID);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("리뷰가 성공적으로 쓰여졌습니다.");
            } else {
                System.out.println("리뷰 쓰기에 실패했습니다.");
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(pstmt);
            Common.close(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("ErrorCode: " + e.getErrorCode());
            System.out.println("Message: " + e.getMessage());
        }
    }
    public void orderMode() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("[1]리뷰쓰기 [2]리뷰보기 [3]뒤로가기 [4]종료 : ");
            int sel = sc.nextInt();
            switch (sel) {
                case 1:
                    writeReview();
                    System.out.println();
                    break;
                case 2:
                    List<ReviewVO> list = viewReview();
                    viewReviewPrint(list);
                    break;
                case 3:
                    return;
                default:
                    System.exit(18);
            }
        }
    }
}