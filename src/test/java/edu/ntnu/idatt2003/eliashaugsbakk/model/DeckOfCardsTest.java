package edu.ntnu.idatt2003.eliashaugsbakk.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DeckOfCardsTest {
  @Test
  void a_deck_of_cards_deals_the_expected_amount_of_cards() {
    DeckOfCards deck = new DeckOfCards();
    HandOfCards hand = deck.dealHand(5);
    assertEquals(5, hand.getHand().size());
  }

  @Test
  void a_deck_of_cards_cannot_deal_more_than_52_cards() {
    DeckOfCards deck = new DeckOfCards();
    HandOfCards hand1 = deck.dealHand(52);
    assertThrows(IllegalArgumentException.class, () -> deck.dealHand(1));
  }

  @Test
  void a_deck_of_cards_cannot_deal_out_a_negative_or_zero_amount_of_cards() {
    DeckOfCards deck = new DeckOfCards();
    assertThrows(IllegalArgumentException.class, () -> deck.dealHand(-1));
    assertThrows(IllegalArgumentException.class, () -> deck.dealHand(0));
  }

  @Test
  void a_deck_of_cards_can_be_reset() {
    DeckOfCards deck = new DeckOfCards();
    HandOfCards hand = deck.dealHand(52);
    assertEquals(52, hand.getHand().size());
    assertThrows(IllegalArgumentException.class, () -> deck.dealHand(1));
    deck.shuffle();
    hand =  deck.dealHand(52);
  }


}
