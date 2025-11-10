package main;

import controller.UserController;
import view.UserView;
import view.TravelView;
import model.UserVO;

public class Main {
    public static void main(String[] args) {
        UserController userController = new UserController();
        UserView userView = new UserView(userController);
        TravelView travelView = new TravelView();

        UserVO user = userView.start();  
        // 난 이서야 이서 
        // 두번째 이서
        travelView.showTravelPlanner(user);  
        // 난 지유야 진짜지유
    }
}
