package edu.ntnu.idatt2003.eliashaugsbakk.model;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class DeckOfCards {
  private final Random rand = new Random();
  // Using ArrayList for fast random access based on placement in list.
  private final List<PlayingCard> fullDeckOfCards = new ArrayList<>();
  private final List<PlayingCard> remainingCards = new ArrayList<>();
  private final char[] suit = { 'S', 'H', 'D', 'C' };

  public DeckOfCards() {
   for (char suit : suit) {
     for (int i = 0; i < 13; i++) {
       fullDeckOfCards.add(new PlayingCard(suit, i + 1));
     }
   }
   remainingCards.addAll(fullDeckOfCards);
  }

  public HandOfCards dealHand(int numberOfCards) {
    if (numberOfCards <= 0) {
      throw new IllegalArgumentException("number of cards must be greater than zero");
    }
    if (numberOfCards > remainingCards.size()) {
      throw new IllegalArgumentException("Number of cards exceeds the remaining number of cards.");
    }
    Set<PlayingCard> hand = new HashSet<>();
    for (int i = 0; i < numberOfCards; i++) {
      int randInt = rand.nextInt(remainingCards.size());
      hand.add(remainingCards.get(randInt));
      remainingCards.remove(randInt);
    }
    return new HandOfCards(hand);
  }
  public void resetDeck() {
    remainingCards.clear();
    remainingCards.addAll(fullDeckOfCards);
  }
}
