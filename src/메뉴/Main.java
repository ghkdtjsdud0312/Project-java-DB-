package 메뉴;

import 메뉴.DAO.CustomerDAO;
import 메뉴.DAO.Master_DAO;
import 메뉴.Mode.CustomerMode;
import 메뉴.Mode.MasterMode;
import java.util.Scanner;

public class Main {
    static Master_DAO masterDao = new Master_DAO();
    static MasterMode masterMode = new MasterMode();
    static CustomerMode customerMode = new CustomerMode();
    static boolean isTrue = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("[1] 로그인 [2] 회원가입 [3] 회원정보수정 [4] 회원탈퇴 [5] 종료 : ");
                int n = sc.nextInt();
                switch (n) {
                    case 1:
                        loginMenu();
                        if (isTrue == true) break;
                        else return;
                    case 2:
                        // 회원 가입 : dao, 메서드
                        CustomerDAO.customerInsert();
                        break;
                    case 3:
                        // 회원 정보 수정 : dao, 메서드
                        CustomerDAO.customerUpdate();
                        System.out.println("회원 정보가 수정 되었습니다.");
                        break;
                    case 4:
                        // 회원 탈퇴
                        CustomerDAO.customerDelete();
                        System.out.println("회원 정보가 탈퇴 되었습니다.");
                        break;
                    case 6:
                        masterDao.masterInsert();
                    case 5:
                        System.out.println("종료되었습니다.");
                        return;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }


    static boolean loginMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.print("[1] 점주 [2] 고객 [3] 뒤로가기 : ");
        int log = sc.nextInt();
        switch (log) {
            case 1: //점주
                masterMode.runMasterMode();
                break;
            case 2: //고객
                customerMode.runCustomerMode();
                customerMode.customerReturn();
                break;
            case 3:
                return true;
        }
        return isTrue = true;
    }

}