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

        travelView.showTravelPlanner(user);  
        // 난 지유야 지유지유
    }
}
