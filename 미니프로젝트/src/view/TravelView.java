package view;

import model.TravelDAO;
import model.TravelVO;
import model.UserVO;

import java.util.*;

public class TravelView {
    private Scanner sc = new Scanner(System.in);
    private Random rd = new Random();
    private TravelDAO travelDAO = new TravelDAO();

    // 🎨 ANSI 컬러 코드 정의
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String MAGENTA = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";
    private static final String WHITE = "\u001B[37m";
    private static final String BOLD = "\u001B[1m";

    public void showTravelPlanner(UserVO user) {
        System.out.println(CYAN + "⚜ 국내여행플래너 ⚜" + RESET);
        System.out.println(BOLD + user.getName() + "님, 반갑습니다!" + RESET);

        System.out.print("이동수단을 선택하세요 (자가용 / 대중교통): ");
        String transport = sc.nextLine();

        String type = "";
        while (!type.equals("P") && !type.equals("J")) {
            System.out.print("성향을 입력하세요 (P / J): ");
            type = sc.nextLine().trim().toUpperCase();
            if (!type.equals("P") && !type.equals("J")) {
                System.out.println(RED + "⚠ P 또는 J만 입력해주세요." + RESET);
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
                System.out.println("성향: " + GREEN + "P (즉흥형)" + RESET);
                System.out.println("테마: " + YELLOW + theme + RESET);
                System.out.println(RED + "집 밖은 위험… 오늘은 집콕 모드로!" + RESET);
                return;
            }

            location = locations[rd.nextInt(locations.length)];

            System.out.println("\n--- 여행 결과 ---");
            System.out.println("이동수단: " + transport);
            System.out.println("성향: " + GREEN + "P (즉흥형)" + RESET);
            System.out.println("테마: " + YELLOW + theme + RESET);
            System.out.println("장소: " + CYAN + location + RESET);

            showDBResults(location, theme);
            printMapWithPin(location);

        } else {
            while (!Arrays.asList(themes).contains(theme)) {
                System.out.print("어떤 여행을 원하시나요? (먹방여행 / 감성여행 / 집에서 쉬기): ");
                theme = sc.nextLine().trim();
                if (!Arrays.asList(themes).contains(theme)) {
                    System.out.println(RED + "⚠ 유효한 테마를 입력해주세요." + RESET);
                }
            }

            if (theme.equals("집에서 쉬기")) {
                System.out.println(RED + "집 밖은 위험… 오늘은 집콕 모드로!" + RESET);
                return;
            }

            while (!Arrays.asList(locations).contains(location)) {
                System.out.print("여행지를 선택하세요 (광주 / 전주 / 경주 / 대구 / 부산 / 대전): "); //커밋 연습(대전 추가)
                location = sc.nextLine().trim();
                if (!Arrays.asList(locations).contains(location)) {
                    System.out.println(RED + "⚠ 유효한 장소를 입력해주세요." + RESET);
                }
            }

            System.out.println("\n--- 계획된 여행 ---");
            System.out.println("이동수단: " + transport);
            System.out.println("성향: " + BLUE + "J (계획형)" + RESET);
            System.out.println("테마: " + YELLOW + theme + RESET);
            System.out.println("장소: " + CYAN + location + RESET);

            showDBResults(location, theme);
            printMapWithPin(location);
        }

        System.out.println("\n" + GREEN + "오늘의 추억이 내일의 바이브가 됩니다. 행복한 여행 되세요! ✈" + RESET);
    }

    private void showDBResults(String location, String theme) {
        List<TravelVO> results = travelDAO.getTravelInfo(location, theme);

        System.out.println("\n--- 여행 추천 정보 ---");
        if (results.isEmpty()) {
            System.out.println(RED + "⚠ 해당 지역의 여행 정보가 없습니다." + RESET);
        } else {
            for (TravelVO t : results) {
                System.out.println(BOLD + "<" + t.getTheme() + ">" + RESET);
                System.out.println("장소명: " + CYAN + t.getStoreName() + RESET);
                System.out.println("주소: " + WHITE + t.getAddress() + RESET);
                if (t.getDescription() != null && !t.getDescription().isEmpty()) {
                    System.out.println("설명: " + t.getDescription());
                }
                if (t.getRecommendMenu() != null && !t.getRecommendMenu().isEmpty()) {
                    System.out.println("추천메뉴: " + YELLOW + t.getRecommendMenu() + RESET);
                }
                System.out.println();
            }
        }
    }

    private void printMapWithPin(String selectedCity) {
        System.out.println("\n==========================================");
        System.out.println("🗺️ " + BOLD + "여행지 위치 보기" + RESET);
        System.out.println("==========================================\n");

        String g = GREEN + "광주 🎨" + RESET;
        String jn = YELLOW + "전주 🍲" + RESET;
        String gj = BLUE + "경주 🏯" + RESET;
        String dg = MAGENTA + "대구 🌆" + RESET;
        String bs = CYAN + "부산 🌊" + RESET;

        String pin = RED + "📍" + RESET;

        System.out.println("          " + (selectedCity.equals("경주") ? pin : "  ") + " " + gj);
        System.out.println("              │");
        System.out.println("      " + (selectedCity.equals("대구") ? pin : "  ") + " " + dg + " ───── "
                + (selectedCity.equals("부산") ? pin : "  ") + " " + bs);
        System.out.println("              │");
        System.out.println("          " + (selectedCity.equals("전주") ? pin : "  ") + " " + jn);
        System.out.println("              │");
        System.out.println("          " + (selectedCity.equals("광주") ? pin : "  ") + " " + g);

        System.out.println("\n==========================================");
        System.out.println("📍 선택된 도시: " + RED + selectedCity + RESET);
        System.out.println("==========================================");
    }
}
