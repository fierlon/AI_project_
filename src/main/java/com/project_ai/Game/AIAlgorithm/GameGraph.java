package com.project_ai.Game.AIAlgorithm;

import com.project_ai.Game.GameLogic;
import com.project_ai.Main;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// decision tree
public class GameGraph {

    // Each node in the decision tree.
    public static class Node {
        private int value; // value to remove
        private int scoreDifferenceBetweenAiAndUser; // score diff between players
        private int depth; //index in the number row

        private int UserScore;
        private int AiScore;
        private List<Node> children;

        // node constructor
        private Node(int value, int scoreDifference, int depth) {
            this.value = value;
            this.scoreDifferenceBetweenAiAndUser = scoreDifference;
            this.depth = depth;
            this.children = new LinkedList<>();
        }

        // Getters and setter
        public int getScoreDifferenceBetweenAiAndUser() {
            return scoreDifferenceBetweenAiAndUser;
        }
        private void setScoreDifferenceBetweenAiAndUser(int set) {
            scoreDifferenceBetweenAiAndUser=set;
        }


        public int getValue() {
            return value;
        }

        public int getDepth() {
            return depth;
        }

        public List<Node> getChildren() {
            return children;
        }
    }

    // Constructor for the GameGraph class
    public GameGraph() {
    }

    public static Node generateDecisionTree(int maxDepth) {

        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (Integer number : GameLogic.numberRowList) {
            frequencyMap.put(number, frequencyMap.getOrDefault(number, 0) + 1);
        }
        boolean isMaxPlayer=true;

        Node rootNode = new Node(-1, 0, -1); // Create root node
        generateDecisionTreeRecursive(rootNode, frequencyMap, Main.PlayerUser.getPlayerScore(), Main.PlayerAI.getPlayerScore(), isMaxPlayer,0, maxDepth);
        return rootNode;
    }

    private static void generateDecisionTreeRecursive(Node parentNode, Map<Integer, Integer> frequencyMap, int playerScore, int aiScore, boolean isMaxPlayer, int depth, int maxDepth) {
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {

            int chosenNumber = entry.getKey();
            int frequency = entry.getValue();

            if (depth >= maxDepth) {
                return; // Exit if depth >= maxDepth, so the graph is not too big// can be changed
            }

            int scoreChange = evaluateScore(chosenNumber);
            int updatedPlayerScore = playerScore; // Initialize local copies of scores
            int updatedAiScore = aiScore;

            if (isMaxPlayer) { // AI is maxPlayer, it wants bigger difference
                if (chosenNumber % 2 == 0) {
                    updatedAiScore += scoreChange; // Change AI score
                } else {
                    updatedPlayerScore += scoreChange; // Change player score
                }
            } else {
                if (chosenNumber % 2 == 0) {
                    updatedPlayerScore += scoreChange; // Change player score
                } else {
                    updatedAiScore += scoreChange; // Change AI score
                }
            }

            int scoreDifference = updatedPlayerScore-updatedAiScore; // Calculate score difference

            Node childNode = new Node(chosenNumber, scoreDifference, depth); // Create a new child
            childNode.AiScore = updatedAiScore; // Set AI score for the child node
            childNode.UserScore = updatedPlayerScore; // Set player score for the child node
            parentNode.children.add(childNode); // Add child to parent node

            // Change frequency map for the next recursive call
            Map<Integer, Integer> updatedFrequencyMap = new HashMap<>(frequencyMap);
            updatedFrequencyMap.put(chosenNumber, frequency - 1); // Remove frequency for that number
            if (updatedFrequencyMap.get(chosenNumber) == 0) {
                updatedFrequencyMap.remove(chosenNumber); // Remove number if freq is 0
            }

            // Recursive call with updated scores
            generateDecisionTreeRecursive(childNode, updatedFrequencyMap, updatedPlayerScore, updatedAiScore, !isMaxPlayer, depth + 1, maxDepth);
        }
    }
    // evaluate the score change based on the chosen number
    private static int evaluateScore(int chosenNumber) {
        if(chosenNumber % 2 == 0){
            return -2 * chosenNumber;
        }else{
            return chosenNumber;
        }
    }



    //for testing, show all tree
    public static void printDecisionTree(Node rootnode) {
        printDecisionTreeRecursive(rootnode, 0);
    }
    private static void printDecisionTreeRecursive(Node node, int depth) {
        if (node == null) {
            return;
        }

        // Print node details
        for (int i = 0; i < depth; i++) {
            System.out.print("  "); // Indent according to depth
        }
        System.out.println("Value: " + node.getValue() + ", UserScore: "+node.UserScore+", AiScore: "+node.AiScore+", Score Difference: " + node.getScoreDifferenceBetweenAiAndUser() + ", Depth: " + node.getDepth());

        // Recursively print children
        for (Node child : node.getChildren()) {
            printDecisionTreeRecursive(child, depth + 1);
        }
    }

}