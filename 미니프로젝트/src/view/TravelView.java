package view;

import model.TravelDAO;
import model.TravelVO;
import model.UserVO;

import java.util.*;

public class TravelView {
    private Scanner sc = new Scanner(System.in);
    private Random rd = new Random();
    private TravelDAO travelDAO = new TravelDAO();

    public void showTravelPlanner(UserVO user) {
        System.out.println("⚜ 국내여행플래너 ⚜");
        System.out.println(user.getName() + "님, 반갑습니다!");

        System.out.print("이동수단을 선택하세요 (자가용 / 대중교통): ");
        String transport = sc.nextLine();

        String type = "";
        while (!type.equals("P") && !type.equals("J")) {
            System.out.print("성향을 입력하세요 (P / J): ");
            type = sc.nextLine().trim().toUpperCase();
            if (!type.equals("P") && !type.equals("J")) {
                System.out.println("⚠ P 또는 J만 입력해주세요.");
            }
        }

        char typeChar = type.charAt(0);
        String[] themes = { "먹방여행", "감성여행", "집에서 쉬기" };
        String[] locations = { "광주", "전주", "경주", "대구", "부산" };

        String theme = "";
        String location = "";

        if (typeChar == 'P') {
            theme = themes[rd.nextInt(themes.length)];

            if (theme.equals("집에서 쉬기")) {
                System.out.println("\n--- 랜덤 여행 결과 ---");
                System.out.println("성향: P (즉흥형)");
                System.out.println("테마: " + theme);
                System.out.println("집 밖은 위험… 오늘은 집콕 모드로!");
                return;
            }

            location = locations[rd.nextInt(locations.length)];

            System.out.println("\n--- 랜덤 여행 결과 ---");
            System.out.println("이동수단: " + transport);
            System.out.println("성향: P (즉흥형)");
            System.out.println("테마: " + theme);
            System.out.println("장소: " + location);

            showDBResults(location, theme);

        } else {
            while (!Arrays.asList(themes).contains(theme)) {
                System.out.print("어떤 여행을 원하시나요? (먹방여행 / 감성여행 / 집에서 쉬기): ");
                theme = sc.nextLine().trim();
                if (!Arrays.asList(themes).contains(theme)) {
                    System.out.println("⚠ 유효한 테마를 입력해주세요.");
                }
            }

            if (theme.equals("집에서 쉬기")) {
                System.out.println("집 밖은 위험… 오늘은 집콕 모드로!");
                return;
            }

            while (!Arrays.asList(locations).contains(location)) {
                System.out.print("여행지를 선택하세요 (광주 / 전주 / 경주 / 대구 / 부산): ");
                location = sc.nextLine().trim();
                if (!Arrays.asList(locations).contains(location)) {
                    System.out.println("⚠ 유효한 장소를 입력해주세요.");
                }
            }

            System.out.println("\n--- 계획된 여행 ---");
            System.out.println("이동수단: " + transport);
            System.out.println("성향: J (계획형)");
            System.out.println("테마: " + theme);
            System.out.println("장소: " + location);

            showDBResults(location, theme);
        }

        System.out.println("\n오늘의 추억이 내일의 바이브가 됩니다. 행복한 여행 되세요!");
    }

    private void showDBResults(String location, String theme) {
        List<TravelVO> results = travelDAO.getTravelInfo(location, theme); 

        System.out.println("\n--- 여행 추천 정보 ---");
        if (results.isEmpty()) {
            System.out.println("⚠ 해당 지역의 여행 정보가 없습니다.");
        } else {
            for (TravelVO t : results) {
                System.out.println("<" + t.getTheme() + ">");
                System.out.println("장소명: " + t.getStoreName());
                System.out.println("주소: " + t.getAddress());
                if (t.getDescription() != null && !t.getDescription().isEmpty()) {
                    System.out.println("설명: " + t.getDescription());
                }
                if (t.getRecommendMenu() != null && !t.getRecommendMenu().isEmpty()) {
                    System.out.println("추천메뉴: " + t.getRecommendMenu());
                }
                System.out.println();
            }
        }
    }
}
