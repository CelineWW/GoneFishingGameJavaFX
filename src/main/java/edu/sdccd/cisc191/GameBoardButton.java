package edu.sdccd.cisc191;

import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javafx.animation.RotateTransition;
/**
 * Extends the basic JavaFX Button with game functionality
 */
public class GameBoardButton extends Button {
    private final boolean hasFish;
    private RotateTransition rotateAnimation;

    public GameBoardButton(boolean hasFish)
    {
        this.hasFish = hasFish;

        // Set min and preferred width, default text
        setMinWidth(60);
        setMinHeight(30);
        setPrefWidth(60);
        setPrefHeight(30);
        setText("?");
        setDisabled(false);

        // Set button shape to be circular
        setShape(new Circle(25));
        // Set buttons background color
        setStyle("-fx-background-color: radial-gradient(radius 50%, teal, lightcyan); -fx-text-fill: black;");
        setDisable(false);

        // Initialize rotation animation
        initRotateAnimation();
    }

    private void initRotateAnimation() {
        rotateAnimation = new RotateTransition(Duration.seconds(1), this);
        rotateAnimation.setByAngle(10); // Rotate button by 10 degrees
        rotateAnimation.setCycleCount(20); // Play animation 20 times
        rotateAnimation.setAutoReverse(true); // Reverse animation
    }

    public void handleClick() {
        // Update text once clicked
        if(hasFish) {
            setText("<><");
            setStyle("-fx-text-fill: blue; -fx-font-weight: bold;");
            rotateAnimation.play(); // Start rotation animation
        } else {
            setText("X");
            setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
        }
        setDisabled(true);
    }
}