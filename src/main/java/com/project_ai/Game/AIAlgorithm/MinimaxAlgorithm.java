package com.project_ai.Game.AIAlgorithm;


import java.util.List;
import java.util.Map;

public class MinimaxAlgorithm {
    private static final int MAX_DEPTH = 10;
    private static int nodesVisited=0;


    public static int makeDecision() {
        int bestScore = Integer.MIN_VALUE;
        int chosenValue=-1;
        GameGraph.Node rootNode = GameGraph.generateDecisionTree(MAX_DEPTH);
        GameGraph.printDecisionTree(rootNode);

        for (GameGraph.Node childNode : rootNode.getChildren()) {
            int score = minimax(childNode, MAX_DEPTH, false);
            System.out.println("Score for node value " + childNode.getValue() + "depth: "+ childNode.getDepth()+ ",score: "+ + score); // testing // prints score for each node

            if (score > bestScore) {//update best decision
                bestScore = score;
                chosenValue = childNode.getValue();
            }
        }

        System.out.println("Best decision score: " + bestScore); // testing // prints the best decision score
        System.out.println("decision value: " + chosenValue);
        System.out.println("Nodes visited: " + nodesVisited);// print count of nodes visited
        return chosenValue;
    }
    private static int minimax(GameGraph.Node node, int depth, boolean isMaximizingPlayer) {
        nodesVisited++; // counting nodes visited

        // base case ig
        if (depth == 0 || node.getChildren().isEmpty()) {
            return node.getScoreDifferenceBetweenAiAndUser();
        }


        if (isMaximizingPlayer) { // AI is maximizing player  since difference = player - ai
            int bestScore = Integer.MIN_VALUE;
            for (GameGraph.Node childNode : node.getChildren()) {
                int score = minimax(childNode, depth - 1, false);// // Recursively evaluate child nodes
                bestScore = Math.max(bestScore, score); // choose best score from choices
            }
            return bestScore;
        } else { // player is minimising player
            int bestScore = Integer.MAX_VALUE;
            for (GameGraph.Node childNode : node.getChildren()) {
                int score = minimax(childNode, depth - 1, true);// recursively evaluate child nodes
                bestScore = Math.min(bestScore, score); //choose min
            }
            return bestScore;
        }
    }
}