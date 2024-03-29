package com.example.project_ai;

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
        if (numberRowList.isEmpty()){
            chooseWinner();
            return;
        }
        int scoreUpdate = deleteChosenNumber(ChosenNumberIndex);
        //for testing
        //System.out.println("GameLogic.changePlayerScore.scoreUpdate "+scoreUpdate);
        if (scoreUpdate<0){
            if (one instanceof PlayerUser){//class check.
                Main.PlayerUser.playerScore+=scoreUpdate;
            } else if (one instanceof PlayerAI){//class check.
                Main.PlayerAI.playerScore+=scoreUpdate;
            }
        } else {
            if (one instanceof PlayerUser){//class check.
                Main.PlayerAI.playerScore+=scoreUpdate;
            } else if (one instanceof PlayerAI){//class check.
                Main.PlayerUser.playerScore+=scoreUpdate;
            }
        }
    }

    public static int aiTurn()  {
        //null check.
        //If you remove it, then after winning there will be errors in the console.
        // Doesn't interfere with the application,
        // but I don't like it. It's a patch, you can rewrite it better.
        if (numberRowList.isEmpty()){
            return 0;
        }
        int chosenNumberIndex = Main.PlayerAI.chooseNumberInRow(numberRowList);
        int chosenNumber = numberRowList.get(chosenNumberIndex);
        changePlayerScore(Main.PlayerAI, chosenNumberIndex);
        return chosenNumber;
    }

    public static String chooseWinner (){
        //for testing
        //System.out.println("GameLogic.PlayerUser.playerScore "+Main.PlayerUser.playerScore);
        //System.out.println("GameLogic.PlayerUser.playerScore "+Main.PlayerAI.playerScore);
        if (Main.PlayerUser.playerScore<Main.PlayerAI.playerScore){
                return "User";
        } else if (Main.PlayerUser.playerScore>Main.PlayerAI.playerScore){
                return "Ai";
        } else {
            //Not described in the condition,
            // but a draw is theoretically possible.
            return null;
        }
    }
}
