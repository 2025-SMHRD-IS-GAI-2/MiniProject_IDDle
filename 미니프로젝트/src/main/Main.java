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
        // 난 이서야

        travelView.showTravelPlanner(user);  

    }
}
