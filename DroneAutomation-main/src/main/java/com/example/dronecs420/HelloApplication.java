package com.example.dronecs420;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.IOException;

public class HelloApplication extends Application {

    // Create the Timelines
    Timeline rotate = new Timeline();
    Timeline moveDiagonal = new Timeline();
    Timeline moveUp = new Timeline();
    Timeline rotateNext = new Timeline();
    Timeline rotateLast = new Timeline();
    Timeline moveLeft = new Timeline();
    SequentialTransition sequence = new SequentialTransition();

    // Create the Label
    Label status = new Label("Current State: " + sequence.getStatus());

    @Override
    public void start(Stage stage) throws IOException {
        Dashboard start = Dashboard.getINSTANCE();
        start.setDashboard(new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml")));

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //ImageView drone = new ImageView(new Image("drone.png"));

        //Setting the Scene Object
        //Group root = new Group(drone);
        Scene scene = new Scene(fxmlLoader.load(), 802, 605);
        stage.setTitle("Farm Dashboard");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
