package controller;

import com.google.api.services.youtube.model.SearchResult;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.PlayerModel;

import java.io.IOException;

/**
 * Created by quentin on 13/12/2016.
 */
public class VideoListController {
    @FXML
    private JFXButton firstButton;
    @FXML
    private JFXButton secondButton;
    @FXML
    private JFXButton thirdButton;
    @FXML
    private JFXButton fourthButton;
    @FXML
    private JFXButton fifthtButton;


    @FXML protected void handleSubmitButtonAction(ActionEvent event) {

    }
    @FXML
    void switchToPlayer(ActionEvent event, AnchorPane root, SearchResult video) {
        try {
            AnchorPane centerPlayer = FXMLLoader.load(getClass().getResource("../view/PlayerAnchor.fxml"));
            root.setBottomAnchor(centerPlayer,0.0);
            PlayerModel player= new PlayerModel(video);
            centerPlayer.getChildren().add(player.getPlayer());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
