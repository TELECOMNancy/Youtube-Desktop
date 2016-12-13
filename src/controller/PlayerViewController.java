package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import view.Player;

import java.io.IOException;

/**
 * Created by léo on 13/12/2016.
 */
public class PlayerViewController  {
    @FXML
    private Text description;
    @FXML
    private Text title;


    @FXML
    void switchToPlayer(ActionEvent event, AnchorPane root, String videoid, String Videodescription, String Videotitle) {
        try {
            AnchorPane centerPlayer = FXMLLoader.load(getClass().getResource("../view/PlayerAnchor.fxml"));
            root.setBottomAnchor(centerPlayer,0.0);
            Player player= new Player(videoid);
            update(Videodescription, Videotitle);
            centerPlayer.getChildren().add(player);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void update(String VideoDescription, String Videotitle) {
        title.setText(Videotitle);
        description.setText(VideoDescription);
    }
}
