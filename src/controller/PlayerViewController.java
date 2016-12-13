package controller;

import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Video;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.MainModel;
import model.PlayerModel;
import view.Player;

import java.io.IOException;

/**
 * Created by léo on 13/12/2016.
 */
public class PlayerViewController  {
    private PlayerModel model;
    private Player player;
    @FXML
    private Text description;
    //private MainModel model;
    @FXML
    private Text title;
    @FXML
    private AnchorPane anchorPlayerVideo;


    public void initModel(PlayerModel model){
        this.model = model;
        title.setText(model.getTitle());
        this.player=model.getPlayer();
        anchorPlayerVideo.getChildren().add(player);
        anchorPlayerVideo.setBottomAnchor(player,0.0);
        anchorPlayerVideo.setTopAnchor(player,0.0);
        anchorPlayerVideo.setRightAnchor(player,0.0);
        anchorPlayerVideo.setLeftAnchor(player,0.0);

    }


}
