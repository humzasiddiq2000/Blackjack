/**
 * Card class represents a card from the deck.
 * Maintaining its suit and value.
 * 
 * @author Humza Siddiq
 */
public class Card{
    String suit;
    String value;

    public Card(String suit, String value){
        this.suit = suit;
        this.value = value;
    }

    public String getSuit(){
        return suit;
    }

    // return string rather than (int) value - allows for other games to be coded
    public String getValue(){
        return value;
    }
}