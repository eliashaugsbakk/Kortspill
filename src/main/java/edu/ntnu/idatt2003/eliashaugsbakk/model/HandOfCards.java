package edu.ntnu.idatt2003.eliashaugsbakk.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class HandOfCards {
  private final List<PlayingCard> hand = new ArrayList<>();

  public HandOfCards(Collection<PlayingCard> cards) {
    if (cards == null) {
      throw new IllegalArgumentException("Cards cannot be null");
    }
    if (cards.size() > 52) {
      throw new IllegalArgumentException("Cards cannot be greater than 52");
    }
    hand.addAll(cards);
  }
  public List<PlayingCard> getHand() {
    return this.hand;
  }

  public int sumOfCardsOnHand() {
    return hand.stream()
        .mapToInt(PlayingCard::getFace)
        .sum();
  }

  public List<PlayingCard> getCardsOnHandOfTypeHearts() {
    return hand.stream()
        .filter(card -> card.getSuit() == 'H')
        .collect(Collectors.toList());
  }

  public boolean queenOfSpades() {
    return hand.stream()
        .anyMatch(card -> card.getSuit() == 'S' && card.getFace() == 12);
  }

  public boolean flush() {
    return hand.stream()
        .map(PlayingCard::getSuit)
        .distinct()
        .count() == 1;
  }


  /**
   * Adds a card to the deck of remaining cards.
   * Useful for testing.
   *
   * @param card the card to add
   */
  public void addCard(PlayingCard card) {
    if (!hand.contains(card)) {
      hand.add(card);
    }
  }

  @Override
  public String toString() {
    StringBuilder returnString = new StringBuilder();
    for (PlayingCard card : hand) {
      returnString.append(card.getAsString()).append(", ");
    }
    return returnString.toString();
  }
}
