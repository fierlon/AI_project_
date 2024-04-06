package com.project_ai.Game;

import com.project_ai.Game.Player.Player;
import com.project_ai.Game.Player.PlayerAI;
import com.project_ai.Game.Player.PlayerUser;
import com.project_ai.Main;

import java.util.LinkedList;
import java.util.Random;
//The class is responsible for the logic of the game,
// filling the LinkedList, working with the LinkedList,
// scoring points, the bot's turn, choosing the winner.
// There are NO methods that change anything in the UI.
// The UI itself calls these methods.
public class GameLogic {
    //Creating a LinkedList
    public static final LinkedList<Integer> numberRowList = new LinkedList<>();;


//filling LinkedList with numbers from 1 to 4
    public static void fillNumberRowList(int SelectedLength){
        numberRowList.clear();//Clearing of previous values
        Random random = new Random();
        for (int i = 0; i < SelectedLength; i++) {
            int randomNumber = random.nextInt(4) + 1;
            // 4 is the upper limit, 1 is the lower subject
            numberRowList.add(randomNumber);
        }
    }
    public static void fillNumberRowList_test(int SelectedLength){
        numberRowList.add(1);
        numberRowList.add(1);
        numberRowList.add(1);
        numberRowList.add(1);
        numberRowList.add(1);

        numberRowList.add(2);
        numberRowList.add(2);
        numberRowList.add(2);
        numberRowList.add(2);
        numberRowList.add(2);

        numberRowList.add(3);
        numberRowList.add(3);
        numberRowList.add(3);
        numberRowList.add(3);
        numberRowList.add(3);

        numberRowList.add(4);
        numberRowList.add(4);
        numberRowList.add(4);
        numberRowList.add(4);
        numberRowList.add(4);
    }

    //deleting a number and calculating the change in score
    private static int deleteChosenNumber(int ChosenNumberIndex){
        int scoreUpdate;
        if (numberRowList.get(ChosenNumberIndex)%2==0){
            scoreUpdate = -(numberRowList.get(ChosenNumberIndex)*2);
        } else {
            scoreUpdate = numberRowList.get(ChosenNumberIndex);
        }
        numberRowList.remove(ChosenNumberIndex);
        //for testing
        //System.out.println("GameLogic.deleteChosenNumber.scoreUpdate "+scoreUpdate);
        return scoreUpdate;
    }

    //Change of points.
    //Accepts a "player" who selects a number, and the INTDEX of that number.
    public static void changePlayerScore (Player one, int ChosenNumberIndex) {
        //null check. if LinkedList is empty, selects the winner
        int scoreUpdate = deleteChosenNumber(ChosenNumberIndex);
        //for testing
        //System.out.println("GameLogic.changePlayerScore.scoreUpdate "+scoreUpdate);
        if (scoreUpdate<0){
            if (one instanceof PlayerUser){//class check.
                Main.PlayerUser.changePlayerScore(scoreUpdate);
            } else if (one instanceof PlayerAI){//class check.
                Main.PlayerAI.changePlayerScore(scoreUpdate);
            }
        } else {
            if (one instanceof PlayerUser){//class check.
                Main.PlayerAI.changePlayerScore(scoreUpdate);
            } else if (one instanceof PlayerAI){//class check.
                Main.PlayerUser.changePlayerScore(scoreUpdate);
            }
        }
        //null check. if LinkedList is empty, selects the winner
    }

    public static int aiTurn()  {
        //null check.
        //If you remove it, then after winning there will be errors in the console.
        // Doesn't interfere with the application,
        // but I don't like it. It's a patch, you can rewrite it better.
        if (numberRowList.isEmpty()){
            return 0;
        }
        int chosenNumberIndex = Main.PlayerAI.chooseNumberInRow();
        int chosenNumber = numberRowList.get(chosenNumberIndex);
        changePlayerScore(Main.PlayerAI, chosenNumberIndex);
        return chosenNumber;

    }

    public static String chooseWinner (){
        //for testing
        //System.out.println("GameLogic.PlayerUser.playerScore "+Main.PlayerUser.playerScore);
        //System.out.println("GameLogic.PlayerUser.playerScore "+Main.PlayerAI.playerScore);
        if (Main.PlayerUser.getPlayerScore()<Main.PlayerAI.getPlayerScore()){
                return "Spēlētājs";
        } else if (Main.PlayerUser.getPlayerScore()>Main.PlayerAI.getPlayerScore()){
                return "Ai";
        } else {
            //Not described in the condition,
            // but a draw is theoretically possible.
            return "Neizšķirts";
        }
    }
}
