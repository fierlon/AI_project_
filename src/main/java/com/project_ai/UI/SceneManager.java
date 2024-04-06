package com.project_ai.UI;

import com.project_ai.Game.AIAlgorithm.GameGraph;
import com.project_ai.Game.AIAlgorithm.MinimaxAlgorithm;
import com.project_ai.Game.GameLogic;
import com.project_ai.Main;
import javafx.scene.control.Button;
import javafx.stage.Stage;
//A class for managing scenes.
//Does not describe what is on the scene, only how it changes.
public class SceneManager {

    private static Stage stage;
    private SettingsScene settingsScene;
    private GameScene gameScene;
    private static EndScene endScene;

    public SceneManager(Stage stage) {//contructor
        this.stage = stage;
        this.settingsScene = new SettingsScene(this);
        this.gameScene = new GameScene(this);
        this.endScene = new EndScene(this);
    }

    public void showSettings() {
        refreshGame();
        stage.setScene(settingsScene);
        stage.setTitle("Settings");
    }

    public void startNewGame() {
        refreshGame();
        startGame();
    }

    public void handleButtonAction(String buttonText) {
            int chosenNumber = Integer.parseInt(buttonText);
            int index = GameLogic.numberRowList.indexOf(chosenNumber);
            //for testing
            //System.out.println("SceneManager.handleButtonAction.chosenNumbe "+chosenNumber);
            if (index != -1) {
                GameLogic.changePlayerScore(Main.PlayerUser,index);
                gameScene.updatePage(chosenNumber);//Updating the scene, watch the method itself
            }
            //null check. if LinkedList is empty,
            //selects the winner and switches to the end scene
            checkWinnerAndShow();
    }

    public static void checkWinnerAndShow() {
        if (GameLogic.numberRowList.isEmpty()){
            EndScene.updateWinnerNameText(GameLogic.chooseWinner());
            stage.setScene(endScene);
            stage.setTitle("End Scene");
        }
    }


    public void startGame() {
        GameLogic.fillNumberRowList(SettingsScene.getselectedLengthSlider());
        //Writes LinkedList data to the scene for the first time
        //(should be replaced by a separate method and called here)
        StringBuilder stringBuilder = new StringBuilder();//creates a string                (StringBuilder)
        for (Integer value : GameLogic.numberRowList) {//iterates LinkedList
            stringBuilder.append(value).append(" ");//string filling                        (StringBuilder)
            //Creating buttons with numbers from LinkedList
            Button numberButton = new Button(value.toString());
            //adds a "click action" to each button
            numberButton.setOnAction(event -> {
                handleButtonAction(value.toString());//player click method, in button
                if (!GameLogic.numberRowList.isEmpty()){//null check
                    gameScene.updatePage(GameLogic.aiTurn());//bot turn and updating the scene, in button
                }
            });
            gameScene.addToChoiceBtnBox(numberButton);//adding a button to a box
        }
        gameScene.updateArrText(stringBuilder.toString());//updating text on the game scene  (StringBuilder)
        //check, what the player has selected on the settings page
        if (SettingsScene.getSelectedPlayerRadioButton().getText().equals("Dators")){
            gameScene.updatePage(GameLogic.aiTurn());//bot turn and updating the scene
        }
        stage.setScene(gameScene);
        stage.setTitle("Game");
    }

    //clearing the game scene, setting player scores to 100
    public void refreshGame() {
        gameScene.removeAllButtonFromChoiceBtnBox();
        Main.PlayerUser.refreshPlayerScore();
        Main.PlayerAI.refreshPlayerScore();
        gameScene.updatePlayerPointsIntText("100");
        gameScene.updateAiPointsIntText("100");
    }
}
