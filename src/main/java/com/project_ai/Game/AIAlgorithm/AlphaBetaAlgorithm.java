package com.project_ai.Game.AIAlgorithm;

public class AlphaBetaAlgorithm {
    private static final int MAX_DEPTH = 10;
    private static int nodesVisited = 0;

    public static int makeDecision() {
        int bestScore = Integer.MIN_VALUE;
        int chosenValue = -1;
        GameGraph.Node rootNode = GameGraph.generateDecisionTree(MAX_DEPTH);
        //for testing.  show Game Graph
        //GameGraph.printDecisionTree(rootNode);

        for (GameGraph.Node childNode : rootNode.getChildren()) {
            int score = alphabeta(childNode, MAX_DEPTH, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
            //for testing
            //System.out.println("Score for node value " + childNode.getValue() + "depth: "+ childNode.getDepth()+ ",score: "+ + score); // testing // prints score for each node

            if (score > bestScore) {
                bestScore = score;
                chosenValue = childNode.getValue();
            }
        }
        //for testing
        /*System.out.println("Best decision score: " + bestScore); // testing // prints the best decision score
        System.out.println("decision value: " + chosenValue);
        System.out.println("Nodes visited: " + nodesVisited);// print count of nodes visited*/
        return chosenValue;
    }

    private static int alphabeta(GameGraph.Node node, int depth, int alpha, int beta, boolean isMaximizingPlayer) {
        nodesVisited++;

        if (depth == 0 || node.getChildren().isEmpty()) {
            return node.getScoreDifferenceBetweenAiAndUser();
        }

        if (isMaximizingPlayer) {
            int bestScore = Integer.MIN_VALUE;
            for (GameGraph.Node childNode : node.getChildren()) {
                int score = alphabeta(childNode, depth - 1, alpha, beta, false);
                bestScore = Math.max(bestScore, score);
                alpha = Math.max(alpha, bestScore);
                if (beta <= alpha) {
                    break; // Beta cutoff
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (GameGraph.Node childNode : node.getChildren()) {
                int score = alphabeta(childNode, depth - 1, alpha, beta, true);
                bestScore = Math.min(bestScore, score);
                beta = Math.min(beta, bestScore);
                if (beta <= alpha) {
                    break; // Alpha cutoff
                }
            }
            return bestScore;
        }
    }
}