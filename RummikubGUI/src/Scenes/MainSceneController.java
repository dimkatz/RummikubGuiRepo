/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scenes;

import ScreensControlFrameWork.ControlledScreen;
import ScreensControlFrameWork.ScreensController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author DK
 */
public class MainSceneController implements Initializable, ControlledScreen {
    
    private ScreensController myController; 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
        
    }
    
    @FXML
    private void buttonLoadGame_Action(ActionEvent event) 
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.show
    }
    
    
    
}
