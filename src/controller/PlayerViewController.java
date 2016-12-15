package controller;

import com.jfoenix.controls.JFXBadge;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
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
    @FXML
    private JFXButton channelButton;



    public void initPlayerModel(PlayerModel model){
        this.model = model;
        title.setText(model.getTitle());
        channelButton.setText(model.getChannelTitle());
        this.player=model.getPlayer();
        anchorPlayerVideo.getChildren().add(player);
        anchorPlayerVideo.setBottomAnchor(player,0.0);
        anchorPlayerVideo.setTopAnchor(player,0.0);
        anchorPlayerVideo.setRightAnchor(player,0.0);
        anchorPlayerVideo.setLeftAnchor(player,0.0);
    }

    @FXML
    public void clickChannel(){

    }


}
