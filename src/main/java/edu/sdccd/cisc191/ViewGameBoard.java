package edu.sdccd.cisc191;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Presents the user with the game graphical user interface
 */
public class ViewGameBoard extends Application
{
    private ControllerGameBoard controller;
    private GameBoardLabel fishRemaining;
    private GameBoardLabel guessesRemaining;
    private GameBoardLabel message;

    /**
     * launches the JavaFX application
     * @param args command line input parameters
     */
    public static void main(String[] args)
    {
        // launch the app
        launch(args);
    }

    /**
     * This method updates the header on the application
     */
    public void updateHeader() {
        // update labels
        fishRemaining.setText("Fish: " + controller.modelGameBoard.getFishRemaining());
        guessesRemaining.setText("Bait: " + controller.modelGameBoard.getGuessesRemaining());
        if(controller.fishWin()) {
            message.setText("Fishes win \uD83D\uDE22!");   // Unicode's representation for the 😢 emoji (sad face)
            message.setStyle("-fx-text-fill: red; -fx-font-weight: bold; ");

        } else if(controller.playerWins()) {
            message.setText("You win!");
            message.setText("You win \uD83D\uDE00!");    // Unicode's representation for the 😃  emoji (happy face)
            message.setStyle("-fx-text-fill: blue; -fx-font-weight: bold; ");

        } else {
            message.setText("Find the fish!");
        }
    }

    @Override
    public void start(Stage stage) {
        controller = new ControllerGameBoard();
        fishRemaining = new GameBoardLabel();
        guessesRemaining = new GameBoardLabel();
        message = new GameBoardLabel();

        // Create root to stack background image and game
        StackPane root = new StackPane();

        //Use BorderPane with HBox and VBox to display the game
        BorderPane gamePane = new BorderPane();

        updateHeader();
        HBox header = new HBox(fishRemaining, guessesRemaining, message);
        gamePane.setTop(header);

        VBox vbox = new VBox(ModelGameBoard.DIMENSION);     // Board layout: 6 * 6
        for (int row=0; row < ModelGameBoard.DIMENSION; row++) {
            // create row container
            HBox hbox = new HBox(ModelGameBoard.DIMENSION);
            for (int col=0; col < ModelGameBoard.DIMENSION; col++) {
                GameBoardButton button = new GameBoardButton(controller.modelGameBoard.fishAt(row,col));
                int finalRow = row;
                int finalCol = col;
                button.setOnAction(e -> {
                    controller.makeGuess(finalRow, finalCol);
                    if(!controller.isGameOver()) {
                        button.handleClick();
                        updateHeader();
                    }
                });
                // Add button to row
                hbox.getChildren().add(col, button);
            }
            // Add row to column
            vbox.getChildren().add(hbox);
        }
        // Create scene, stage, set title, and show
        gamePane.setCenter(vbox);

        // Load background image
        Image backgroundImage = new Image("file:src/main/resources/deep ocean.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);

        // Set the size of the background image to match the size of gamePane
        backgroundImageView.fitWidthProperty().bind(gamePane.widthProperty());
        backgroundImageView.fitHeightProperty().bind(gamePane.heightProperty());

        // Add background image to stack pane
        root.getChildren().addAll(backgroundImageView,gamePane);
        Scene scene = new Scene(root, 400, 300);    // Buttons Width:60*6, Height:30*6
        stage.setScene(scene);
        stage.setTitle("Gone Fishing");
        stage.show();
    }
}