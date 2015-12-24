package Scenes;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import rummikub.controller.PlayersManager;
import rummikub.controller.exception.DuplicateNameException;
import rummikub.controller.exception.EmptyNameException;
import rummikub.model.Player;

/**
 * FXML Controller class
 *
 * @author iblecher
 */
public class PlayersController implements Initializable {

    @FXML
    private TextField playerNameTextField;
    
    @FXML
    private CheckBox isHumanCheckBox;

    @FXML
    private Label errorMessageLabel;

    @FXML
    private Button addPlayerButton;

    @FXML
    private Pane playersPane;
    
    @FXML
    private Button continueButton;

    private boolean isErrorMessageShown = false;
    private SimpleBooleanProperty finishedInit;
    
    private PlayersManager playersManager;

    public void setPlayersManager(PlayersManager playersManager) {
        this.playersManager = playersManager;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        playerNameTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                onPlayerNameChange();
            }
        });
        finishedInit = new SimpleBooleanProperty(false);
    }

    @FXML
    protected void addPlayer(ActionEvent event) {
        String name = getPlayerName();
        boolean isHuman = isPlayerHuman();
        try {
            //Player player = playersManager.addPlayer(name, isHuman);
            //addPlayerToList(player);
            clearPlayerDetailsFields();
            hideError();
        } catch (DuplicateNameException | EmptyNameException playersManagerException) {
            showError(playersManagerException.getMessage());
        }
    }

    @FXML
    protected void onPlayerNameChange() {
        updateAddPlayerButtonState();
        hideError();
    }
    
    @FXML
    protected void onContinue (ActionEvent event){
        finishedInit.set(true);
    }

    public SimpleBooleanProperty getFinishedInit() {
        return finishedInit;
    }

    private void updateAddPlayerButtonState() {
        boolean isEmptyName = getPlayerName().trim().isEmpty();
        boolean disable = isEmptyName || isErrorMessageShown;
        addPlayerButton.setDisable(disable);
    }

    private String getPlayerName() {
        return playerNameTextField.getText();
    }
    
    private boolean isPlayerHuman() {
        return isHumanCheckBox.isSelected();
    }

    private void addPlayerToList(Player player) {
        //PlayerView playerView = new PlayerView(player.getName(), player.isHuman());
        //playersPane.getChildren().add(playerView);
    }

    private void clearPlayerDetailsFields() {
        playerNameTextField.clear();
        isHumanCheckBox.setSelected(false);
        playerNameTextField.requestFocus();
    }

    private void showError(String message) {
        if (!isErrorMessageShown) {
            isErrorMessageShown = true;
            errorMessageLabel.setText(message);
            FadeTransition animation = new FadeTransition();
                    animation.setNode(errorMessageLabel);
                    animation.setDuration(Duration.seconds(0.3));
                    animation.setFromValue(0.0);
                    animation.setToValue(1.0);
            animation.play();
        }
        updateAddPlayerButtonState();
    }

    private void hideError() {
        if (isErrorMessageShown) {
            FadeTransition animation = new FadeTransition();
            animation.setNode(errorMessageLabel);
            animation.setDuration(Duration.seconds(0.3));
            animation.setFromValue(1.0);
            animation.setToValue(0.0);
            animation.play();

            isErrorMessageShown = false;
            errorMessageLabel.textProperty().setValue("");
            updateAddPlayerButtonState();
        }
    }
}
