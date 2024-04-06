package com.project_ai.Game.Player;

//We need to work with access modifiers,
//rewrite direct references to methods,
// and write the methods themselves.
public abstract class Player {//parent class for playersparent class for players
    private int playerScore = 100;
    public int getPlayerScore() {
        return playerScore;
    }

    public void refreshPlayerScore() {
        playerScore=100;
    }

    public void changePlayerScore(int changedScore) {
        playerScore+=changedScore;
    }
}

