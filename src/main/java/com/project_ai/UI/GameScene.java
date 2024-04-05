package com.project_ai.UI;

import com.project_ai.Game.GameLogic;
import com.project_ai.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
//game class, what else can I say)
public class GameScene extends Scene {
    Text arrText;
    HBox choiceBtnBox;
    Text playerPointsIntText;
    Text aiPointsIntText;
    public GameScene(SceneManager main) {
        //Set the position of the application on the screen
        // and the position of the vertical boxes (VBox) in the application.
        super(new VBox());
        VBox allGameBox = (VBox) this.getRoot();
        allGameBox.setSpacing(20);
        allGameBox.setPadding(new Insets(15));
        allGameBox.setAlignment(Pos.CENTER);

        //buttons
        Button restartBtn = new Button("Jauna Spēle!");
        Button settingsBtn = new Button("Iestatījumi");
        //buttons pressed events
        restartBtn.setOnAction(event -> main.startNewGame());
        settingsBtn.setOnAction(event -> main.showSettings());

        //text fields
        Text pointsText = new Text("Punkti:");
        Text playerPointsText = new Text("Spēlētājs");
        Text aiPointsText = new Text("AI");
        playerPointsIntText = new Text("100");
        aiPointsIntText = new Text("100");
        Text choiceText = new Text("Izvēlies:");
        arrText = new Text();

        //Adding a text fields to a horizontal box (HBox)
        HBox pointsTextBox = new HBox(10, playerPointsText, aiPointsText);
        pointsTextBox.setAlignment(Pos.CENTER);
        HBox pointsIntTextBox = new HBox(10, playerPointsIntText, aiPointsIntText);
        pointsIntTextBox.setAlignment(Pos.CENTER);
        choiceBtnBox = new HBox(10);
        choiceBtnBox.setAlignment(Pos.CENTER);
        //Adding a button to a horizontal box (HBox)
        HBox btnGameBox = new HBox(10, restartBtn, settingsBtn);
        //adding text fields and horizontal boxes in the main vertical box
        allGameBox.getChildren().addAll(pointsText, pointsTextBox, pointsIntTextBox, arrText, choiceText, choiceBtnBox, btnGameBox);
    }

    //method to update text in a field with all numbers from LinkedList
    public void updateArrText(String text) {
        arrText.setText(text);
    }

    //method to update the text in the "PlayerPoints" field
    public void updatePlayerPointsIntText(String text) {
        playerPointsIntText.setText(text);
    }

    //method to update the text in the "AiPoints" field
    public void updateAiPointsIntText(String text) {
        aiPointsIntText.setText(text);
    }

    //method for adding buttons to a horizontal box
    public void addToChoiceBtnBox(Button button) {
        choiceBtnBox.getChildren().add(button);
    }

    //update scene method
    //updates points to scene from objects,
    // updates list of numbers on scene from LinkedList,
    // removes selected button from horizontal box
    public void updatePage(int chosenNumber) {
        //updates points to scene from objects
        updatePlayerPointsIntText(String.valueOf(Main.PlayerUser.getPlayerScore()));
        updateAiPointsIntText(String.valueOf(Main.PlayerAI.getPlayerScore()));

        //updates list of numbers on scene from LinkedLis
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer value : GameLogic.numberRowList) {
            stringBuilder.append(value).append(" ");
        }
        updateArrText(stringBuilder.toString());

        //removes selected button from horizontal box
        removeButtonFromChoiceBtnBox(chosenNumber);

        SceneManager.checkWinnerAndShow();
    }

    //Deleting the desired button (by the text on it) from the horizontal box
    private void removeButtonFromChoiceBtnBox(int number) {
        for (var node : choiceBtnBox.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                if (button.getText().equals(String.valueOf(number))) {
                    choiceBtnBox.getChildren().remove(button);
                    break;
                }
            }
        }
    }

    //Removing all buttons from the horizontal box
    public void removeAllButtonFromChoiceBtnBox() {
                choiceBtnBox.getChildren().clear();
    }
}
