package main;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * Deck class represents a card deck.
 * Containing deck operations.
 * 
 * @author Humza Siddiq
 */
public class Deck {

    private Card[] deck = new Card[52]; // 52 cards in a (standard) deck
    private int nextCardPointer = 0;
    private Map<String, Integer> cardValues = new HashMap<>();
    private Set<String> cardSuits = new HashSet<>();

    public Deck() {
        initialiseValueMapping();
        initaliseSuitMapping();
        populateDeck();
        shuffle();
    }

    public Card getCard() throws DeckEmptyException {
        if(nextCardPointer < deck.length){
            Card nextCard = deck[nextCardPointer];
            nextCardPointer++;
            return nextCard;
        }
        else
            throw new DeckEmptyException();
    }

    public void shuffle() {
        List<Card> deckList = Arrays.asList(deck);
        Collections.shuffle(deckList);
        deck = (Card[]) deckList.toArray(new Card[deck.length]);
    }

    private void populateDeck() {
        String[] cardValuesArray = (String[]) cardValues.keySet().toArray(new String[0]);
        String[] cardSuitsArray  = (String[]) cardSuits.toArray(new String[0]);
        int deckIndex = 0;

        for(int suitIndex = 0; suitIndex < cardSuitsArray.length; suitIndex++){
            for(int valueIndex = 0; valueIndex < cardValuesArray.length; valueIndex++){
                String suit = cardSuitsArray[suitIndex];
                String value = cardValuesArray[valueIndex];

                Card playingCard = new Card(suit, value);
                deck[deckIndex] = playingCard;
                deckIndex++;
            }
        }
    }

    private void initaliseSuitMapping() {
        cardSuits.add("clubs");
        cardSuits.add("diamonds");
        cardSuits.add("hearts");
        cardSuits.add("spades");
    }

    private void initialiseValueMapping() {
        cardValues.put("ace", 1);
        cardValues.put("2", 2);
        cardValues.put("3", 3);
        cardValues.put("4", 4);
        cardValues.put("5", 5);
        cardValues.put("6", 6);
        cardValues.put("7", 7);
        cardValues.put("8", 8);
        cardValues.put("9", 9);
        cardValues.put("10", 10);
        cardValues.put("jack", 10);
        cardValues.put("queen", 10);
        cardValues.put("king", 10);
    }

    public Card[] getDeck() {
        return deck;
    }

    public String toString() {
        return Arrays.toString(deck);
    }
}
