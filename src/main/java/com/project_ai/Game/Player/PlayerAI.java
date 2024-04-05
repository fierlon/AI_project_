package com.project_ai.Game.Player;

import com.project_ai.UI.SettingsScene;
import javafx.scene.control.RadioButton;

import java.util.LinkedList;

public class PlayerAI extends Player {

    RadioButton algoritm = SettingsScene.getSelectedAlgoritmRadioButton();
    //here is the algorithm that the user chooses

    public int chooseNumberInRow() {
        //Gets the current LinkedList,
        // gives the INDEX by which the number that the bot selects is located
        return 0;
    }
}
