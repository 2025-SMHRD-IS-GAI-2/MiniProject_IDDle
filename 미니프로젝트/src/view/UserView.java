package view;

import controller.UserController;
import model.UserVO;
import java.util.Scanner;

public class UserView {
    private Scanner sc = new Scanner(System.in);
    private UserController userController;

    public UserView(UserController userController) {
        this.userController = userController;
    }

    public UserVO start() {
        System.out.println("=== ✈ 국내 여행 플래너 로그인 시스템 ===");

        UserVO user = null;

        while(user == null) {
            System.out.print("회원가입을 하시겠습니까? (Y/N): ");
            String choice = sc.nextLine();

            if(choice.equalsIgnoreCase("Y")) {
                user = register();
                if(user != null) {
                    System.out.println("✅ 회원가입 성공! 바로 로그인 진행");
                } else {
                    System.out.println("❌ 회원가입 실패 (ID 중복 가능)");
                }
            } else {
                user = login();
                if(user == null) {
                    System.out.println("⚠ 로그인 실패, 다시 시도해주세요.");
                }
            }
        }

        return user;
    }

    private UserVO register() {
        System.out.print("ID 입력: ");
        String id = sc.nextLine();
        System.out.print("PW 입력: ");
        String pw = sc.nextLine();
        System.out.print("이름 입력: ");
        String name = sc.nextLine();
        System.out.print("나이 입력: ");
        int age = 0;
        try {
            age = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("⚠ 나이는 숫자로 입력해주세요.");
            return null;
        }

        return userController.registerUser(id, pw, name, age);
    }

    private UserVO login() {
        System.out.println("\n=== 로그인 ===");
        System.out.print("ID: ");
        String id = sc.nextLine();
        System.out.print("PW: ");
        String pw = sc.nextLine();

        return userController.loginUser(id, pw);
    }
}
