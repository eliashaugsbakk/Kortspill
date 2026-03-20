package edu.ntnu.idatt2003.eliashaugsbakk.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class HandOfCardsTest {


  // Takes a while to run; just for fun.
  @Test
  void a_flush_is_possible() {
    DeckOfCards deck = new DeckOfCards();
    HandOfCards hand = deck.dealHand(5);

    long runs = 0;

    while (true) {
      runs++;
      if (deck.getRemainingCards() < 5) {
        deck.shuffle();
      }
      hand = deck.dealHand(5);
      if (hand.flush()) break;
      IO.println(runs);
    }
  }

  @Test
  void test_DeckOfCards_toString() {
    DeckOfCards deck = new DeckOfCards();
    HandOfCards hand = deck.dealHand(52);
    for (PlayingCard card : hand.getHand()) {
      assertTrue(hand.toString().contains("" + card.getAsString()));
    }
  }

  @Test
  void flush_test() {
    var cards = new ArrayList<PlayingCard>(
        List.of(
            new PlayingCard('H', 1),
            new PlayingCard('H', 2),
            new PlayingCard('H', 3),
            new PlayingCard('H', 4),
            new PlayingCard('C', 1)
        ));
    HandOfCards hand = new HandOfCards(cards);

    assertFalse(hand.flush());

    cards = new ArrayList<PlayingCard>(
        List.of(
            new PlayingCard('H', 1),
            new PlayingCard('H', 2),
            new PlayingCard('H', 3),
            new PlayingCard('H', 4),
            new PlayingCard('H', 5)
        ));
    hand = new HandOfCards(cards);
    assertTrue(hand.flush());
  }

  @Test
  void queen_of_spades_gets_reported() {
    var hand = new HandOfCards(List.of());
    hand.addCard(new PlayingCard('S', 1));
    hand.addCard(new PlayingCard('C', 12));
    assertFalse(hand.queenOfSpades());
    hand.addCard(new PlayingCard('S', 12));
    assertTrue(hand.queenOfSpades());
  }

  @Test
  void cards_of_the_type_heart_gets_reported() {
    var hand = new HandOfCards(List.of());
    hand.addCard(new PlayingCard('S', 1));
    hand.addCard(new PlayingCard('S', 2));
    hand.addCard(new PlayingCard('S', 3));
    hand.addCard(new PlayingCard('H', 1));
    assertEquals(1, hand.getCardsOnHandOfTypeHearts().size());
    hand.addCard(new PlayingCard('H', 2));
    assertEquals(2, hand.getCardsOnHandOfTypeHearts().size());
  }

  @Test
  void sum_of_hand_gets_reported() {
    var hand = new HandOfCards(List.of());
    hand.addCard(new PlayingCard('S', 1));
    assertEquals(1, hand.sumOfCardsOnHand());
    hand.addCard(new PlayingCard('S', 2));
    assertEquals(3, hand.sumOfCardsOnHand());
    hand.addCard(new PlayingCard('S', 13));
    assertEquals(16, hand.sumOfCardsOnHand());
  }

}
