package com.project_ai.UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.ToggleGroup;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Toggle;

public class SettingsScene extends Scene {

    // Declaring a variable to store the selected player
    private static RadioButton selectedPlayerRadioButton;

    //Declaring a variable to store the selected algorithm
    private static RadioButton selectedAlgoritmRadioButton;

    // Declaring a variable to store the selected row length
    private static int selectedLength;

    public SettingsScene(SceneManager main) {
        //Set the position of the application on the screen
        // and the position of the vertical boxes (VBox) in the application.
        super(new VBox());
        VBox allSettingsBox = (VBox) this.getRoot();
        allSettingsBox.setSpacing(20);
        allSettingsBox.setPadding(new Insets(35));
        allSettingsBox.setAlignment(Pos.CENTER);

        //buttons player
        RadioButton playerRadio = new RadioButton("Jūs");
        RadioButton aiRadio = new RadioButton("Dators");
        //buttons algoritm
        RadioButton minimaxRadio = new RadioButton("Minimaksa");
        RadioButton alphabetaRadio = new RadioButton("Alpha-beta");

        // Create a switch groups
        ToggleGroup playerToggleGroup = new ToggleGroup();
        ToggleGroup algoritmToggleGroup = new ToggleGroup();
        // Adding to a switch group
        playerRadio.setToggleGroup(playerToggleGroup);
        aiRadio.setToggleGroup(playerToggleGroup);
        // Adding to a switch group
        minimaxRadio.setToggleGroup(algoritmToggleGroup);
        alphabetaRadio.setToggleGroup(algoritmToggleGroup);
        //Set the default value for the switch groups and for the variable
        playerToggleGroup.selectToggle(playerRadio);
        selectedPlayerRadioButton = playerRadio;
        algoritmToggleGroup.selectToggle(minimaxRadio);
        selectedAlgoritmRadioButton = minimaxRadio;

        //create a listener for the switch group "playerToggleGroup"
        playerToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (newValue == null) {
                    // You can add a script here if no button is selected.
                    // But it is not needed now.
                } else {
                    //the selected player is written to the variable.
                    selectedPlayerRadioButton = (RadioButton) newValue;
                }
            }
        });

        //create a listener for the switch group "algoritmToggleGroup"
        algoritmToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (newValue == null) {
                    // You can add a script here if no button is selected.
                    // But it is not needed now.
                } else {
                    //the selected algoritm is written to the variable.
                    selectedAlgoritmRadioButton = (RadioButton) newValue;
                }
            }
        });

        //slider creation
        Slider numberSlider = new Slider(15, 25, 15);
        numberSlider.setBlockIncrement(1);
        numberSlider.setSnapToTicks(true);
        numberSlider.setMajorTickUnit(1);
        numberSlider.setMinorTickCount(0);
        numberSlider.setShowTickMarks(true);
        numberSlider.setShowTickLabels(true);
        numberSlider.setValueChanging(false);
        //Set the default value for the sslider and for the variable
        numberSlider.setValue(15);
        selectedLength=15;

        //create a listener for the slider "numberSlider"
        numberSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            //the selected length is written to the variable.
            selectedLength = newValue.intValue();
        });

        //buttons and pressed events
        Button startBtn = new Button("Sākt Spēli!");
        startBtn.setOnAction(event -> main.startGame());

        ////text fields
        Text numberText = new Text("Izvēlies virknes garumu");
        Text playerText = new Text("Kurš sāks spēli?");
        Text algoText = new Text("Kādu algoritmu dators izmantos?");

        ////Adding a button to a horizontal box (HBox)
        HBox selectPlayerRadioBox = new HBox(10, playerRadio, aiRadio);
        selectPlayerRadioBox.setAlignment(Pos.CENTER);
        HBox selectAlgoRadioBox = new HBox(10, minimaxRadio, alphabetaRadio);
        //adding text fields and horizontal boxes in the main vertical box
        allSettingsBox.getChildren().addAll(numberText, numberSlider, playerText, selectPlayerRadioBox, algoText, selectAlgoRadioBox, startBtn);
    }

    //Method for getting the selected player
    public static RadioButton getSelectedPlayerRadioButton() {
        return selectedPlayerRadioButton;
    }

    //Method for getting the selected algoritm
    public static RadioButton getSelectedAlgoritmRadioButton() {
        return selectedAlgoritmRadioButton;
    }

    //Method for getting the selected Length
    public static int getselectedLengthSlider() {
        return selectedLength;
    }
}
