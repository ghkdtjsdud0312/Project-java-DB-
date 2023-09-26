package 메뉴.Mode;
import 메뉴.DAO.Master_DAO;
import 메뉴.DAO.MenuDAO;
import 메뉴.DAO.ReviewDAO;
import 메뉴.VO.MenuVO;
import 메뉴.VO.OrderVO;
import 메뉴.VO.ReviewVO;
import java.util.List;
import java.util.Scanner;

public class MasterMode {
    Master_DAO mDao = new Master_DAO();
    MenuDAO menuDAO = new MenuDAO();
    Scanner sc = new Scanner(System.in);
    boolean isMaster = false;
    OrderMode orderMode = new OrderMode();
    static ReviewDAO reviewDAO = new ReviewDAO();

    public void runMasterMode() {
        while (true) {
            if (!isMaster) {
                System.out.print("사업자 번호를 입력하세요 : ");
                int bizNum = sc.nextInt();
                System.out.print("비밀번호를 입력하세요 : ");
                String pw = sc.next();
                if (mDao.isM(bizNum, pw)) {
                    isMaster = true;
                } else {
                    System.out.println("사업자번호 또는 비밀번호 오류. 다시 입력하세요.");
                    continue;
                }

                while(true){
                    System.out.println("=".repeat(10) + "관리자 메뉴 선택" + "=".repeat(10));
                    System.out.print("[1] 매출조회 [2] 주문내역 조회 [3] 메뉴관리 [4]리뷰보기 [5] 뒤로가기: ");
                    int sel = sc.nextInt();
                    switch (sel) {
                        case 1:
                            List<OrderVO> totalList = mDao.viewTotal();
                            mDao.viewTotalPrint(totalList);
                            break;
                        case 2:
                            List<OrderVO> viewList = orderMode.viewOrder();
                            orderMode.viewOrederPrint(viewList);
                            break;
                        case 3:
                            manageStore();
                            break;
                        case 4:
                            List<ReviewVO> list = reviewDAO.viewReview();
                            reviewDAO.viewReviewPrint(list);
                            break;
                        case 5:
                            return;
                    }
                }
            }
        }
    }

    void manageStore() {
        while (true) {
            System.out.println();
            System.out.println("=".repeat(10) + "메뉴관리" + "=".repeat(10));
            System.out.print("[1]메뉴추가 [2] 메뉴삭제 [3] 메뉴조회 [4]뒤로가기: ");
            int mSel = sc.nextInt();
            switch (mSel) {
                case 1:
                    menuDAO.menuInsert();
                    System.out.println("메뉴등록이 완료되었습니다.");
                    System.out.println();
                    break;
                case 2:
                    menuDAO.menuDelete();
                    System.out.println();
                    break;
                case 3:
                    List<MenuVO> list = menuDAO.menuSelect();
                    menuDAO.menuSelectPrint(list);
                    break;
                case 4:
                    return;
            }
        }
    }
}