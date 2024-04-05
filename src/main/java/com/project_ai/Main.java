package com.project_ai;

import com.project_ai.Game.Player.PlayerAI;
import com.project_ai.Game.Player.PlayerUser;
import com.project_ai.UI.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    private Stage primaryStage;//can be removed, but I'm comfortable seeing the access modifier
    private SceneManager sceneManager;//can be placed in "start", but I find it convenient to see the access modifier
    public final static com.project_ai.Game.Player.PlayerUser PlayerUser = new PlayerUser();//first player
    public final static com.project_ai.Game.Player.PlayerAI PlayerAI = new PlayerAI();// second player

    // creation of SceneManager object, see explanations in class
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        sceneManager = new SceneManager(primaryStage);

        sceneManager.showSettings();

        primaryStage.setTitle("Settings");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

//This is to check if something breaks. It is needed to view the LinkedList state when closing the application.
/*        StringBuilder stringBuilder = new StringBuilder();
        for (int element : GameLogic.numberRowList) {
            stringBuilder.append(element).append(", ");
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.setLength(stringBuilder.length() - 2);
        }
        String result = stringBuilder.toString();
        System.out.println("Main.result_numberRowList"+result);*/
    }
}
