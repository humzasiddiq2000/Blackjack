package tests;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.Test;
import main.Card;
import main.Deck;

/**
 * Card class tests
 * 
 * @author Humza Siddiq
 */
public class CardTest {
    Card card;

    @Test
    public void checkCardValues(){
        Deck deck = new Deck();
        Set<String> cardValues = deck.getCardValues().keySet();

        for(String value : cardValues){
            card = new Card("", value);
            assertEquals(value, card.getValue());
        }
    }

    @Test
    public void checkCardSuits(){
        Deck deck = new Deck();
        Set<String> cardSuits = deck.getSuits();

        for(String suit : cardSuits){
            card = new Card(suit, "");
            assertEquals(suit, card.getSuit());
        }
    }
}
