package controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.ChannelModel;
import model.PlayerModel;
import view.Player;
import java.io.IOException;

/**
 * Created by léo on 13/12/2016.
 */
public class PlayerViewController  {
    private PlayerModel playerModel;
    private Player player;
    @FXML
    private Text title;
    @FXML
    private AnchorPane anchorPlayerVideo;
    @FXML
    private JFXButton channelButton;


    public void initPlayerModel(PlayerModel model){
        this.playerModel = model;
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
    public void clickChannel() throws IOException {
        playerModel.getMainModel().getPlayerModel().getPlayer().getVideoPlayer().getEngine().load(null);
        playerModel.getMainModel().getBackgroundModel().getBackground().getChildren().remove(playerModel.getMainModel().getBackgroundModel().getMainChildren());
        FXMLLoader channelLoader = new FXMLLoader(getClass().getResource("/view/ChannelView.fxml"));
        AnchorPane channelView = channelLoader.load();
        playerModel.getMainModel().getBackgroundModel().getBackground().getChildren().add(channelView);
        playerModel.getMainModel().getBackgroundModel().setMainChildren(channelView);
        playerModel.getMainModel().getBackgroundModel().getBackground().setBottomAnchor(channelView,100.0);
        playerModel.getMainModel().getBackgroundModel().getBackground().setTopAnchor(channelView,100.0);
        playerModel.getMainModel().getBackgroundModel().getBackground().setLeftAnchor(channelView,200.0);
        //background.setRightAnchor(player,100.0);
        playerModel.getMainModel().getBackgroundModel().getBackground().autosize();
        ChannelController channelController = channelLoader.getController();
        ChannelModel channelModel = new ChannelModel(playerModel.getMainModel());
        channelController.initChannelModel(channelModel);
    }

}
