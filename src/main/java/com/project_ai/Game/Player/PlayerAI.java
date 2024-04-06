package com.project_ai.Game.Player;

import com.project_ai.Game.GameLogic;
import com.project_ai.UI.SettingsScene;
import javafx.scene.control.RadioButton;

public class PlayerAI extends Player {

    RadioButton algorithm;
    //here is the algorithm that the user chooses

    public int chooseNumberInRow() {
        String algorithm = SettingsScene.getSelectedAlgoritmRadioButton();//here is the algorithm that the user chooses

        if (algorithm.equals("MinMax")) {
            //for testing
            //System.out.println("MinMax");
            return GameLogic.numberRowList.indexOf(com.project_ai.Game.AIAlgorithm.MinimaxAlgorithm.makeDecision());
        } else if (algorithm.equals("AlphaBeta")){
            //for testing
            //System.out.println("AlphaBeta");
            return GameLogic.numberRowList.indexOf(com.project_ai.Game.AIAlgorithm.AlphaBetaAlgorithm.makeDecision());
        }
        System.out.println("ERROR");
        return 0;
    }
}
