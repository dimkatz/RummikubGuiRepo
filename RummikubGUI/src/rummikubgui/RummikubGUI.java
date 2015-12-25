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
import scenes.SceneIdentifier;

/**
 *
 * @author DK
 */
public class RummikubGUI extends Application {

    private GameHandler gameHandler = new GameHandler();
    private ScreensController mainContainer = new ScreensController();

    public void start(Stage primaryStage) 
    {
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        addAllScreens();
        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        
        mainContainer.setScreen(SceneIdentifier.MAIN_MENU_SCENE_NAME);
        mainContainer.setScreen(SceneIdentifier.GAME_SCENE_NAME);

        primaryStage.show();
    }
    
    private void addAllScreens()
    {
        
        mainContainer.loadScreen(SceneIdentifier.MAIN_MENU_SCENE_NAME, 
                                 SceneIdentifier.MAIN_MENU_SCENE_PATH);
        
        mainContainer.loadScreen(SceneIdentifier.GAME_SCENE_NAME, 
                                 SceneIdentifier.GAME_SCENE_PATH);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}