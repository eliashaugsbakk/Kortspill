package edu.ntnu.idatt2003.eliashaugsbakk;
import edu.ntnu.idatt2003.eliashaugsbakk.model.DeckOfCards;
import edu.ntnu.idatt2003.eliashaugsbakk.model.HandOfCards;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

  HandOfCards playingHand;
  DeckOfCards deckOfCards;
  private final int handSize = 5;
  Text gameInfo = new Text();
  Text handInfo = new Text();

  @Override
  public void start(Stage primaryStage) {
    deckOfCards = new DeckOfCards();
    playingHand = deckOfCards.dealHand(5);
    IO.println("Deck: " + playingHand.getHand());


    primaryStage.setTitle("My first stage with scene");
    Button btn1 = new Button();
    btn1.setText("Deal hand");
    btn1.setId("DealHand");

    Button btn2 = new Button();
    btn2.setText("Shuffle deck");
    btn2.setId("ShuffleDeck");

    btn1.addEventHandler(ActionEvent.ACTION, this::dealHand);
    btn2.addEventHandler(ActionEvent.ACTION, this::shuffleDeck);

    GridPane root = new GridPane();
    root.add(btn1, 1, 1);
    root.add(btn2, 2, 2);

    root.add(gameInfo, 3, 3);
    root.add(handInfo, 3, 4);

    root.setHgap(40);
    root.setVgap(40);
    root.setStyle("-fx-background-color:rgb(21, 135, 0);");

    btn1.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
    btn2.setFont(Font.font("Verdana", FontWeight.BOLD, 30));

    gameInfo.setFont(Font.font("Tahoma", FontWeight.NORMAL, 55));
    gameInfo.setStyle(
        "-fx-fill: rgb(255,224,236);" +
            "-fx-stroke: #838383;" +
            "-fx-stroke-width: 1px;"
    );
    handInfo.setFont(Font.font("Tahoma", FontWeight.NORMAL, 55));
    handInfo.setStyle(
        "-fx-fill: rgb(255,203,226);" +
            "-fx-stroke: #888888;" +
            "-fx-stroke-width: 1px;"
    );

    primaryStage.setScene(new Scene(root, 400, 300));
    primaryStage.show();
  }

  private void dealHand(ActionEvent event) {
      if (deckOfCards.getRemainingCards() > handSize ) {
        playingHand = deckOfCards.dealHand(handSize);
        IO.println("New hand: " + playingHand.toString());
        gameInfo.setText("Hand: " + playingHand.toString());

        StringBuilder handInfoText = new StringBuilder();
        handInfoText.append("Properties of the current hand: ").append("\n")
            .append("Sum of cards: ").append(playingHand.sumOfCardsOnHand()).append("\n")
            .append("Cards of type hearts: ")
            .append(playingHand.getCardsOnHandOfTypeHearts().size())
            .append("\n")
            .append("Queen of Spades: ")
            .append(playingHand.queenOfSpades())
            .append("\n")
            .append("Flush: ").append(playingHand.flush());

        handInfo.setText(handInfoText.toString());

      } else {
        IO.println("Not enough cards");
        gameInfo.setText("Not enough cards in the deck; shuffle the deck.");
    }
  }

  private void shuffleDeck(ActionEvent event) {
    deckOfCards.shuffle();
    System.out.println("Deck shuffled");
    gameInfo.setText("Deck has been shuffled");
  }
}
