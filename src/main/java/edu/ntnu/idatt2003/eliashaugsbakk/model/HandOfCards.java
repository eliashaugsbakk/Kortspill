package edu.ntnu.idatt2003.eliashaugsbakk.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HandOfCards {
  private final List<PlayingCard> hand = new ArrayList<>();
  public HandOfCards(Collection<PlayingCard> cards) {
    hand.addAll(cards);
  }
  public List<PlayingCard> getHand() {
    return this.hand;
  }
}
