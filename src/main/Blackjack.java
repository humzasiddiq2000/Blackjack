package main;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 * Blackjack card game.
 * Aim to obtain a score of 21
 * 
 * Jack | Queen | King == 10 each
 */
public class Blackjack implements CardGame{

    ArrayList<Card> playersHand = new ArrayList<>();
    Deck cardDeck = new Deck();

    public Blackjack(){
        initaliseDeck();
    }

    private void dealOpeningHand() {
        try {
            playersHand.add(cardDeck.getCard());
            playersHand.add(cardDeck.getCard());
        } catch (DeckEmptyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void play() {
        resetGame();
        boolean stand = false;
        dealOpeningHand();
        printHand();
        printScore(playersHand);

        while(!stand && score(playersHand) <= 21){
            String move = getPlayerMoveChoice();
            if(move.equals("hit")){
                Card nextCard;
                try {
                    nextCard = cardDeck.getCard();
                } catch (DeckEmptyException e) {
                    System.out.println("No cards left...");
                    break;
                }
                playersHand.add(nextCard);
                printHand();
                printScore(playersHand);
            }
            else if(move.equals("stand")){
                printHand();
                printScore(playersHand);
                break;
            }
        }

        if(score(playersHand) > 21)
            System.out.println("BUST!\n");
        else if(score(playersHand) == 21)
            System.out.println("You Win!\n");
        else
            System.out.println("Failed to obtain 21, You lose!\n");
    }

    private void printScore(ArrayList<Card> playersHand2) {
        System.out.println("Score: " + score(playersHand2));
    }

    private void printHand() {
        System.out.println("\n=== Hand ===");
        for(int i = 0; i < playersHand.size(); i++){
            System.out.println(playersHand.get(i).value + "\tOf\t" + playersHand.get(i).suit);
        }
        System.out.println();
    }

    private String getPlayerMoveChoice() {
        System.out.print("> ");
        Scanner scn = new Scanner(System.in);
        return scn.nextLine().toLowerCase().replace("> ", "");
    }

    private int score(ArrayList<Card> playersHand) {
        Map<String, Integer> cardValues = cardDeck.getCardValues();
        int score = 0;
        
        for(int i = 0; i < playersHand.size(); i++){
            boolean aceFlag = false;
            Card card = playersHand.get(i);
            int cardValue = cardValues.get(card.value);

            if(cardValue != 1){
                aceFlag = false;
                score += cardValue;
            }
            else{
                int tempScore1 = score + cardValue;
                int tempScore2 = score + 11;

                // take closest score to 21
                int tempScore1Diff = Math.abs(21 - tempScore1);
                int tempScore2Diff = Math.abs(21 - tempScore2);

                
                score = (tempScore1Diff < tempScore2Diff) ? tempScore1 : tempScore2;
                
            }

        }
        return score;
    }

    @Override
    public void initaliseDeck() {
        
    }

    public void resetGame() {
        playersHand.clear();
        cardDeck.resetDeck();
    }

    public static void main(String args[]){
        CardGame blackjack = new Blackjack();
        Scanner scn = new Scanner(System.in);

        System.out.println("Welcome to Blackjack!");
        
        
        while(true){
            System.out.println("Press Enter to play, (q) to quit, (i) for instructions");
            System.out.print("> ");
            String input = scn.nextLine();

            switch(input){
                case "":
                    blackjack.play();
                    break;
                case "q":
                    System.exit(0);
                    break;
                case "i":
                    System.out.println("=== Instructions ===");
                    System.out.println("The aim of Blackjack is to obtain a score of (or close to) 21.");
                    System.out.println("Enter 'hit' to receive another card, or 'stand' to finish\n");
            }
            blackjack.resetGame();
        }
    }
}