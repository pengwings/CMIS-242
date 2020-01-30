public class Card {
    private char rank;
    private String suit;

    public Card() {
        
    }

    public Card(char rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public String toString() {
        String s = String.format("The suit of the card is " + suit + " and the rank of the card is " + rank);
        return s;
    }

}
