package com.project_ai.UI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class EndScene extends Scene {
    static Text winnerNameText;
    //Final Scene Class.
    // Attention to the content ().
    // main is not the Main class, but the SceneManager.
    public EndScene(SceneManager main) {//here
        //Set the position of the application on the screen
        // and the position of the vertical boxes (VBox) in the application.
        super(new VBox());
        VBox allEndBox = (VBox) this.getRoot();
        allEndBox.setSpacing(20);
        allEndBox.setAlignment(Pos.CENTER);

        //text fields
        Text winnerText = new Text("Uzvarētājs:");
        winnerNameText = new Text();

        //buttons
        Button restartBtn2 = new Button("Sākt Jaunu spēli");
        Button settingsBtn2 = new Button("Iestatījumi");
        //buttons pressed events
        restartBtn2.setOnAction(event -> main.startNewGame());//main
        settingsBtn2.setOnAction(event -> main.showSettings());//main

        //Adding a button to a horizontal box (HBox)
        HBox btnEndBox = new HBox(10, restartBtn2, settingsBtn2);
        //adding text fields and horizontal boxes in the main vertical box
        allEndBox.getChildren().addAll(winnerText, winnerNameText, btnEndBox);
    }

    //method to update the text in the "winner" field
    public static void updateWinnerNameText(String text) {
        winnerNameText.setText(text);
    }
}
