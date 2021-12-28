package tests;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

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
}
