/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ScreensControlFrameWork;

import java.util.HashMap;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;


/**
 *
 * @author DK
 */
 public class ScreensController extends StackPane {
     
     private HashMap<String, Node> screens = new HashMap<>();

     public void addScreen(String name, Node screen) {
         screens.put(name, screen);
     }
     
     public boolean loadScreen(String name, String resource) {
         try {
             FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource));
             Parent loadScreen = (Parent) myLoader.load();
             ControlledScreen myScreenControler
                     = ((ControlledScreen) myLoader.getController());
             myScreenControler.setScreenParent(this);
             addScreen(name, loadScreen);
             return true;
         } catch (Exception e) {
             System.out.println(e.getMessage());
             return false;
         }
     }
     
     public boolean setScreen(final String name) {

         if (screens.get(name) != null) { //screen loaded 
             final DoubleProperty opacity = opacityProperty();

             //Is there is more than one screen 
             if (!getChildren().isEmpty()) {
                 //remove displayed screen 
                 getChildren().remove(0);
                 //add new screen 
                 getChildren().add(0, screens.get(name));
             } else {
                 //no one else been displayed, then just show 
                 setOpacity(0.0);
                 getChildren().add(screens.get(name));
             }
             return true;
         } else {
             System.out.println("screen hasn't been loaded!\n");
             return false;
         }
     }   
          
     public boolean unloadScreen(String name) 
     {
         if (screens.remove(name) == null) 
         {
             System.out.println("Screen didn't exist");
             return false;
         } 
         else 
         {
             return true;
         }
   } 
}
