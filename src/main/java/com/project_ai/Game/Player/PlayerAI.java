package com.project_ai.Game.Player;

import com.project_ai.Game.GameLogic;
import com.project_ai.UI.SettingsScene;
import javafx.scene.control.RadioButton;

public class PlayerAI extends Player {

    RadioButton algorithm;
    //here is the algorithm that the user chooses

    public int chooseNumberInRow() {
        //Gets the current LinkedList,
        // gives the INDEX by which the number that the bot selects is located
        RadioButton algorithm = SettingsScene.getSelectedAlgoritmRadioButton();//here is the algorithm that the user chooses

        if (algorithm.getText().equals("Minimaksa")) {
            return GameLogic.numberRowList.indexOf(com.project_ai.Game.AIAlgorithm.MinimaxAlgorithm.makeDecision());
        } else if (algorithm.getText().equals("Minimaksa")){
            return 0;
        }
        return 0;
    }
}
