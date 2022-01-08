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

    private ArrayList<Card> playersHand = new ArrayList<>();
    private Deck cardDeck;
    private boolean bust = false;
    private boolean win = false;

    public Blackjack(){
        initaliseDeck();
    }

    private void dealOpeningHand() {
        try {
            addToPlayersHand(cardDeck.getCard());
            addToPlayersHand(cardDeck.getCard());
        } catch (DeckEmptyException e) {
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
                addToPlayersHand(nextCard);
                printHand();
                printScore(playersHand);
            }
            else if(move.equals("stand")){
                printHand();
                printScore(playersHand);
                stand = true;
            }
        }

        if(bust)
            System.out.println("BUST!\n");
        else if(win)
            System.out.println("You Win!\n");
        else
            System.out.println("Failed to obtain 21, You lose!\n");
    }

    private void printScore(ArrayList<Card> playersHand2) {
        System.out.println("Score: " + score(playersHand2));
    }

    private void printHand() {
        System.out.println("\n=== Hand ===");
        for (Card card : playersHand) {
            System.out.println(card.value + "\tOf\t" + card.suit);
        }
        System.out.println();
    }

    private String getPlayerMoveChoice() {
        System.out.print("> ");
        Scanner scn = new Scanner(System.in);
        return scn.nextLine().toLowerCase().replace("> ", "");
    }

    public int score(ArrayList<Card> playersHand) {
        Map<String, Integer> cardValues = cardDeck.getCardValues();
        int score = 0;
        int aceCounter = 0;
        
        for (Card card : playersHand) {
            int cardValue = cardValues.get(card.value);

            if (cardValue != 1) {
                score += cardValue;
            } else
                aceCounter++;
        }

        for(int i = 0; i < aceCounter; i++){
            int tempScore1 = score + 1;
            int tempScore2 = score + 11;

            /*
                Scoring mechanism for ace(s):
                * if (+1 > 21 AND +11 > 21) => score += MIN (+1)
                * if (+1 > 21 AND +11 <= 21) => score += +11

                * if (+1 <= 21 AND +11 > 21) => score += (+1)
                * if (+1 <= 21 AND +11 <= 21) => score += MAX (+11)
            */

            if(tempScore1 > 21 && tempScore2 > 21)
                score = Math.min(tempScore1, tempScore2);
            else if(tempScore1 > 21 && tempScore2 <= 21)
                score = tempScore2;
            else if(tempScore1 <= 21 && tempScore2 > 21)
                score = tempScore1;
            else if(tempScore1 <= 21 && tempScore2 <= 21)
                score = Math.max(tempScore1, tempScore2);
        }

        if(score > 21) 
            bust = true;
        if(score == 21)
            win = true;
        
        return score;
    }

    @Override
    public void initaliseDeck() {
        cardDeck = new Deck();
    }

    public void resetGame() {
        playersHand.clear();
        cardDeck.resetDeck();
        bust = false;
        win = false;
    }

    public ArrayList<Card> getPlayersHand() {
        return playersHand;
    }

    public Deck getCardDeck() {
        return cardDeck;
    }

    public void addToPlayersHand(Card card){
        playersHand.add(card);
    }

    public boolean getBust(){
        return bust;
    }

    public boolean getWin(){
        return win;
    }

    public static void main(String[] args){
        CardGame blackjack = new Blackjack();
        Scanner scn = new Scanner(System.in);
        
        System.out.println("Welcome to Blackjack!");
        while(true){
            System.out.println("Press (Enter) to play, (q) to quit, (i) for instructions");
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
                    break;
            }
            blackjack.resetGame();
        }
    }
}