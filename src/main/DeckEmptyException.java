package main;

public class DeckEmptyException extends Exception{
    public DeckEmptyException() {}
    public DeckEmptyException(String message) {
        super(message);
    }
}