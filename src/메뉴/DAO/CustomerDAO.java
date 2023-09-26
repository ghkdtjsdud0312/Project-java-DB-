package 메뉴.DAO;

import 메뉴.Common;
import 메뉴.Exception.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerDAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pStmt = null;
    ResultSet rs = null;

    //이용자 로그인
    public boolean isC(String USER_ID, String USER_PW) {
        boolean isCustomer = false;
        try {
            conn = Common.getConnection();
            String sql = "SELECT COUNT(*) FROM CUSTOMER_INFO WHERE USER_ID = ? AND USER_PW = ?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, USER_ID);
            pStmt.setString(2, USER_PW);
            rs = pStmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    isCustomer = true;
                }
            }
            Common.close(rs);
            Common.close(pStmt);
            Common.close(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isCustomer;
    }

    public static void customerInsert() throws Exception {
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("=".repeat(10) + "회원가입" + "=".repeat(10));
            System.out.println("회원 정보를 생성 하세요.");
            System.out.print("아이디를 입력하세요 : ");
            String USER_ID = sc.nextLine();
            if (!validateLength(USER_ID, 10)) {
                throw new IllegalLengthException("아이디 길이가 너무 깁니다.");
            }
            if (isCustomer(USER_ID)) {
                throw new UsedIdException();
            }
            System.out.print("비밀번호를 입력하세요(숫자+영어+특수문자 조합해서 15자 이내) : ");
            String c_pw = sc.nextLine();
            validatePwString(c_pw, 15);
            System.out.print("이름 입력 : ");
            String USER_NAME = sc.nextLine();
            System.out.print("성별 선택 [1]여성 [2]남성 : ");
            String GENDER = sc.nextLine();
            if (!validateOptionNumber(GENDER)) {
                throw new IllegalGenderException("성별 선택 옵션을 잘못 입력하였습니다.");
            }
            System.out.print("휴대폰 번호 입력(숫자만 입력) : ");
            String PHONE = sc.nextLine();
            int m = PHONE.length();
            if (!validateLength(PHONE, 11)) {
                throw new IllegalLengthException("휴대폰 번호를 잘못 입력하였습니다.");
            }
            PHONE = insertPhoneNumberhyphen(PHONE);
            System.out.println(PHONE);
            System.out.print("주소 입력 : ");
            String ADDR = sc.nextLine();
            if (!validateAddr(ADDR)) {
                throw new IllegalAddrException("주소를 잘못 입력하였습니다.");
            }
            System.out.print("생년월일 입력: ");
            String inputDateStr = sc.nextLine();

            int month= Integer.parseInt(inputDateStr.substring(4,6));
            int day= Integer.parseInt(inputDateStr.substring(6));
            String BIRTH = "";
            while(true){
                if(inputDateStr.length()==8){
                    if(month>0 && month<13) {
                        if (day > 0 && day < 32) {
                            SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyyMMdd");
                            SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = inputDateFormat.parse(inputDateStr);
                            BIRTH = outputDateFormat.format(date);
                            if (!validateDate(date)) {
                                throw new IllegalDateException("현재 시간보다 미래의 시간을 입력하였습니다.");
                            }
                            break;
                        }
                        else System.out.println("날짜 형식이 아닙니다. 다시 입력해주세요.");
                        return;
                    }
                    else System.out.println("월의 형식이 아닙니다. 다시 입력해주세요.");
                    return;
                }
            }

            System.out.print("이메일 입력 : ");
            String EMAIL = sc.nextLine();
            // @ 가 있어야 한다.
            if (!validateEmail(EMAIL)) {
                throw new IllegalEmailException("이메일 형식이 잘못되었습니다.");
            }
            System.out.print("월정액 구매 선택 [1]월 5000원 [2]1년 15000원 : ");
            String MONTHFEE = sc.nextLine();
            if (!validateOptionNumber(MONTHFEE)) {
                throw new IllegalMonthFeeException("월정액 선택 옵션을 잘못 입력하였습니다.");
            }

            String sql = "INSERT INTO CUSTOMER_INFO(USER_ID,USER_PW,USER_NAME,GENDER,PHONE,ADDR,BIRTH,EMAIL,MONTHFEE) VALUES(?,?,?,?,?,?,?,?,?)";

            try {
                conn = Common.getConnection();
                pStmt = conn.prepareStatement(sql);
                pStmt.setString(1, USER_ID);
                pStmt.setString(2, PHONE);
                pStmt.setString(3, USER_NAME);
                pStmt.setString(4, GENDER);
                pStmt.setString(5, PHONE);
                pStmt.setString(6, ADDR);
                pStmt.setString(7, BIRTH);
                pStmt.setString(8, EMAIL);
                pStmt.setString(9, MONTHFEE);
                int ret = pStmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Common.close(stmt);
            Common.close(conn);
            System.out.println("\u001B[92m" + "회원가입이 성공적으로 완료되었습니다!" + "\u001B[0m");
            break;
        }
    }

    // 회원정보 수정
    public static void customerUpdate() {
        Connection conn = null;
        PreparedStatement pStmt = null;
        Scanner sc = new Scanner(System.in);

        System.out.print("비밀번호를 변경할 회원의 아이디를 입력하세요 : ");
        String USER_ID = sc.next();
        System.out.print("변경할 회원의 비밀번호를 입력하세요 : ");
        String USER_PW = sc.next();

        String sql = "UPDATE CUSTOMER_INFO SET USER_PW = ? WHERE USER_ID = ?";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, USER_PW);
            pStmt.setString(2, USER_ID);
            pStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }




    // 회원 탈퇴
    public static void customerDelete() {
        Connection conn = null;
        PreparedStatement pStmt = null;
        Scanner sc = new Scanner(System.in);

        System.out.print("삭제할 회원의 아이디를 입력 하세요 : ");
        String USER_ID = sc.next();
        String sql = "DELETE FROM CUSTOMER_INFO WHERE USER_ID = ?";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, USER_ID);
            pStmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }

    private static boolean validateEmail(String email) {
        return email.contains("@");
    }

    private static boolean validateAddr(String addr) {
        String[] parts = addr.split(" ");
        int idx = 0;
        for (String part : parts) {
            idx++;
            if (idx == 1) {
                if (part.charAt(part.length() - 1) != '시') {
                    System.out.println("시 가 없습니다");
                    return false;
                }
            } else if (idx == 2) {
                if (part.charAt(part.length() - 1) != '구') {
                    System.out.println("구 가 없습니다");
                    return false;
                }
            } else if (idx == 3) {
                if (part.charAt(part.length() - 1) != '동') {
                    System.out.println("동 이 없습니다");
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean validateDate(Date date) {
        Date currentDate= new Date();
        if (date.compareTo(currentDate) > 0) {
            return false;
        }
        return true;
    }

    private static String insertPhoneNumberhyphen(String PHONE) {
        return PHONE.substring(0, 3) + "-" + PHONE.substring(3, 7) + "-" + PHONE.substring(7);
    }
    private static boolean validateOptionNumber(String GENDER) {
        return Objects.equals(GENDER, "1") || Objects.equals(GENDER, "2");
    }

    private static void validatePwString(String c_pw, int x) {
        if (c_pw.length() >= x) {
            throw new IllegalPasswordException("비밀번호가 너무 깁니다.");
        }
        if (!isContainNumber(c_pw)) {
            throw new IllegalPasswordException("숫자가 포함되지 않았습니다.");
        }
        if (!isContainAlpha(c_pw)) {
            throw new IllegalPasswordException("영어가 포함되지 않았습니다.");
        }
        if (!isContainSpecialChar(c_pw)) {
            throw new IllegalPasswordException("특수문자가 포함되지 않았습니다.");
        }
    }

    private static boolean isContainAlpha(String c_pw) {
        for (char c : c_pw.toCharArray()) {
            if ('A' <= c && c <= 'Z' || 'a' <= c && c <= 'z'){
                return true;
            }
        }
        return false;
    }

    private static boolean isContainNumber(String c_pw) {
        String numbers = "0123456789";

        for (char c : c_pw.toCharArray()) {
            if (numbers.contains(String.valueOf(c))) {
                return true;
            }
        }
        return false;
    }
    private static boolean isContainSpecialChar(String c_pw) {
        String specialChars = "@#$%^&+=!";

        for (char c : c_pw.toCharArray()) {
            if (specialChars.contains(String.valueOf(c))) {
                return true;
            }
        }
        return false;
    }

    private static boolean validateLength(String input, int length) {
        if (input.length() > length) {
            return false;
        }
        return true;
    }

    private static boolean isCustomer(String username) {
        List<String> userList = new ArrayList<>();
        userList.add("");
        userList.add("");
        userList.add("");
        return userList.contains(username);
    }

}