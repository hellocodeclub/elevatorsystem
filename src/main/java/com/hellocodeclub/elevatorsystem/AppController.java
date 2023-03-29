package com.hellocodeclub.elevatorsystem;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AppController {

    public final static int ELEVATOR_WIDTH =90;
    public final static int ELEVATOR_HEIGHT =90;
    public final static int FLOOR_HEIGHT = 100;

    ElevatorState elevatorState;

    @FXML
    Canvas elevatorCanvas;

    @FXML
    ImageView floor4;

    @FXML
    ImageView floor3;

    @FXML
    ImageView floor2;

    @FXML
    ImageView floor1;

    @FXML
    ImageView floor0;

    @FXML
    public void initialize() throws FileNotFoundException {

        Image buttonImage = new Image(new FileInputStream("/Users/martarey/dev/javafx/elevatorsystem/elevator-button.jpg"));
        floor0.setImage(buttonImage);
        floor1.setImage(buttonImage);
        floor2.setImage(buttonImage);
        floor3.setImage(buttonImage);
        floor4.setImage(buttonImage);

        elevatorState = new ElevatorState();
        elevatorCanvas.getGraphicsContext2D().setFill(Color.BLACK);
        elevatorCanvas.getGraphicsContext2D().fillRect(0,0,elevatorCanvas.getWidth(),elevatorCanvas.getHeight());


        Timeline timeline = new Timeline(
          new KeyFrame(
                  Duration.seconds(0),
                  event -> paintElevatorState(elevatorCanvas.getGraphicsContext2D())
          ),
          new KeyFrame(
                  Duration.millis(500)
          )
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

    public void moveLiftTo4Floor(){
        int selectedLift = elevatorState.findCloserLift(0);
        elevatorState.moveLiftToFloor(0, selectedLift);
    }

    public void moveLiftTo3Floor(){
        int selectedLift = elevatorState.findCloserLift(1);
        elevatorState.moveLiftToFloor(1, selectedLift);
    }

    public void moveLiftTo2Floor(){
        int selectedLift = elevatorState.findCloserLift(2);
        elevatorState.moveLiftToFloor(2, selectedLift);
    }

    public void moveLiftTo1Floor(){
        int selectedLift = elevatorState.findCloserLift(3);
        elevatorState.moveLiftToFloor(3, selectedLift);
    }

    public void moveLiftTo0Floor(){
        int selectedLift = elevatorState.findCloserLift(4);
        elevatorState.moveLiftToFloor(4, selectedLift);
    }


    public void paintElevatorState(GraphicsContext gc){

        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,elevatorCanvas.getWidth(),elevatorCanvas.getHeight());

        for(int i=0;i<3;i++){ // each lift
            for(int j=0;j<5;j++){ // each floor
                if(elevatorState.state[i][j]==1) {
                    gc.setFill(Color.WHITE);
                    Image elevatorImage = null;
                    try {
                        elevatorImage = new Image(new FileInputStream("/Users/martarey/dev/javafx/elevatorsystem/elevator-bitmap.png"));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    gc.drawImage(elevatorImage,21 + (ELEVATOR_WIDTH + 26) * i, FLOOR_HEIGHT * j);

                }



            }
        }
    }
}
