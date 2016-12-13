package controller;

import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Video;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.MainModel;
import view.Player;

import java.io.IOException;

/**
 * Created by léo on 13/12/2016.
 */
public class PlayerViewController  {
    //@FXML
    //private Text description;
    private MainModel model;
    @FXML
    private Text title;


    @FXML
    void switchToPlayer(ActionEvent event, AnchorPane root, SearchResult video) {
        try {
            AnchorPane centerPlayer = FXMLLoader.load(getClass().getResource("../view/PlayerAnchor.fxml"));
            root.setBottomAnchor(centerPlayer,0.0);
            Player player= new Player(""+video.getId());
            update(video);
            centerPlayer.getChildren().add(player);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void update(SearchResult video) {
        //title.setText(""+model.getVideoId());
        //description.setText(VideoDescription);
    }
}
