package tests;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

import main.Blackjack;
import main.Card;

public class BlackjackTest {
    
    @Test
    public void checkOpeningHand() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        Blackjack blackjack = new Blackjack(); // Factory

        // dealOpeningHand is private, requires use of reflection
        Method dealOpeningHandMethod = Blackjack.class.getDeclaredMethod("dealOpeningHand");
		dealOpeningHandMethod.setAccessible(true);
		dealOpeningHandMethod.invoke(blackjack);

        assertEquals( blackjack.getPlayersHand().size(), 2);

        dealOpeningHandMethod.invoke(blackjack);
        assertEquals(4, blackjack.getPlayersHand().size()); // double dealing
    }

    @Test
    public void checkResetGame() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Blackjack blackjack = new Blackjack();
        
        Method dealOpeningHandMethod = Blackjack.class.getDeclaredMethod("dealOpeningHand");
		dealOpeningHandMethod.setAccessible(true);
		dealOpeningHandMethod.invoke(blackjack);
		dealOpeningHandMethod.invoke(blackjack);

        blackjack.resetGame();
        assertEquals(0, blackjack.getPlayersHand().size()); // 0 cards in players hand
        assertEquals(52, blackjack.getCardDeck().getDeck().length); // 52 cards in deck
    }

    @Test
    public void checkBust() {
        Blackjack blackjack = new Blackjack();
        int score = blackjack.score(blackjack.getPlayersHand());
        assertEquals(score, 0);
        assertEquals(false, blackjack.getBust());

        Card king = new Card("clubs", "king");
        blackjack.addToPlayersHand(king);
        blackjack.addToPlayersHand(king);
        blackjack.addToPlayersHand(king);
        score = blackjack.score(blackjack.getPlayersHand());
        assertEquals(true, blackjack.getBust());
        assertEquals(30, score);
    }

    @Test
    public void checkWin() {
        Blackjack blackjack = new Blackjack();
        int score = blackjack.score(blackjack.getPlayersHand());
        assertEquals(score, 0);
        assertEquals(false, blackjack.getWin());

        Card king = new Card("clubs", "king");
        Card ace = new Card("clubs", "ace");
        blackjack.addToPlayersHand(king);
        blackjack.addToPlayersHand(king);
        blackjack.addToPlayersHand(ace);
        score = blackjack.score(blackjack.getPlayersHand());
        assertEquals(true, blackjack.getWin());
        assertEquals(21, score);
    }

    @Test
    public void checkScoreUpdate(){
        Blackjack blackjack = new Blackjack();
        int score = blackjack.score(blackjack.getPlayersHand());
        assertEquals(score, 0); // initally 0

        Card ace = new Card("clubs", "ace");
        blackjack.addToPlayersHand(ace);
        score = blackjack.score(blackjack.getPlayersHand());
        assertEquals(11, score); // updated to 11
    }

    @Test
    public void checkKingAce() {
        Blackjack blackjack = new Blackjack();
        int score = blackjack.score(blackjack.getPlayersHand());
        assertEquals(score, 0);
        assertEquals(false, blackjack.getWin());

        Card king = new Card("clubs", "king");
        Card ace = new Card("clubs", "ace");
        blackjack.addToPlayersHand(ace);
        blackjack.addToPlayersHand(king);
        score = blackjack.score(blackjack.getPlayersHand());
        assertEquals(true, blackjack.getWin());
        assertEquals(21, score);
    }

    @Test
    public void checkKingQueenAce() {
        Blackjack blackjack = new Blackjack();
        int score = blackjack.score(blackjack.getPlayersHand());
        assertEquals(score, 0);
        assertEquals(false, blackjack.getWin());

        Card king = new Card("clubs", "king");
        Card queen = new Card("clubs", "queen");
        Card ace = new Card("clubs", "ace");
        blackjack.addToPlayersHand(king);
        blackjack.addToPlayersHand(queen);
        blackjack.addToPlayersHand(ace);
        score = blackjack.score(blackjack.getPlayersHand());
        assertEquals(true, blackjack.getWin());
        assertEquals(21, score);
    }

    @Test
    public void checkNineAceAce() {
        Blackjack blackjack = new Blackjack();
        int score = blackjack.score(blackjack.getPlayersHand());
        assertEquals(score, 0);
        assertEquals(false, blackjack.getWin());

        Card nine = new Card("clubs", "9");
        Card ace = new Card("clubs", "ace");
        blackjack.addToPlayersHand(nine);
        blackjack.addToPlayersHand(ace);
        blackjack.addToPlayersHand(ace);
        score = blackjack.score(blackjack.getPlayersHand());
        assertEquals(true, blackjack.getWin());
        assertEquals(21, score);
    }
}
