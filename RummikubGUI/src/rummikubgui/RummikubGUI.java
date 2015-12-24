/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rummikubgui;

import ScreensControlFrameWork.ScreensController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import rummikub.controller.GameHandler;

/**
 *
 * @author DK
 */
public class RummikubGUI extends Application {

    private GameHandler gameHandler = new GameHandler();
    ScreensController mainContainer = new ScreensController();

    public void start(Stage primaryStage) 
    {
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void addAllScenes()
    {
        
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
