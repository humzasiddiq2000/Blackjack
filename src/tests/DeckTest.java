package tests;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

import main.*;

/**
 * Deck class tests
 * 
 * @author Humza Siddiq
 */
public class DeckTest {

    Deck deck;

    @Before
    public void setUp(){
        deck = new Deck();
    }
    
    @Test
    public void numberOfCards(){
        assertTrue(deck.getDeck().length == 52);
    }

    @Test
    public void checkDeckPopulated(){
        Card[] cardDeck = deck.getDeck();
        for(int i = 0; i < cardDeck.length; i++){
            assertTrue(cardDeck[i] != null);
        }
    }

    @Test
    public void shuffleDeck(){
        Card[] beforeShuffle = deck.getDeck();
        System.out.println(beforeShuffle);
        deck.shuffle();
        Card[] afterShuffle = deck.getDeck();
        assertTrue(beforeShuffle != afterShuffle);
    }

    @Test
    public void getNextCard(){
        deck = new Deck(); // ensure new (complete) deck
        for(int i = 0; i < 52; i++){
            try{
                deck.getCard();
            }
            catch(DeckEmptyException err){
                fail("should not throw DeckEmptyException!");
            }
        }
        assertThrows(DeckEmptyException.class, () -> {
            deck.getCard();
        });
    }
}
