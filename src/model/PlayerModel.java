package model;

import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Video;
import controller.PlayerViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import main.Main;
import view.Player;

import java.awt.*;
import java.io.IOException;

/**
 * Created by quentin on 13/12/2016.
 */
public class PlayerModel {
    private String title;
    private Player player;
    private MainModel mainModel;

    public PlayerModel(SearchResult video, MainModel mainModel){
        this.mainModel=mainModel;
        this.title= ""+mainModel.getVideoTitle(video);
        this.player=new Player(mainModel.getVideoID(video));
    }

    public PlayerModel(PlaylistItem video, MainModel mainModel){
        this.mainModel=mainModel;
        this.title= ""+mainModel.getVideoTitle(video);
        this.player=new Player(mainModel.getVideoID(video));
    }

    public PlayerModel(String videoId, String videoTitle, MainModel mainModel){
        this.mainModel=mainModel;
        this.title= videoTitle;
        this.player=new Player(videoId);
    }

    public Player getPlayer(){
        return this.player;
    }
    public String getTitle() {return this.title;}
    public MainModel getMainModel(){
        return this.mainModel;
    }


    public void initPlayer() throws IOException {
        mainModel.setPlayerModel(this);
        AnchorPane background= this.getMainModel().getBackgroundModel().getBackground();
        FXMLLoader playerLoader = new FXMLLoader(getClass().getResource("/view/PlayerView.fxml"));
        background.getChildren().remove(this.getMainModel().getBackgroundModel().getMainChildren());
        AnchorPane player = playerLoader.load();
        //background.setPlayerView(player);
        background.getChildren().add(player);
        this.getMainModel().getBackgroundModel().setMainChildren(player);
        background.setBottomAnchor(player,30.0);
        background.setTopAnchor(player,150.0);
        background.setLeftAnchor(player,300.0);
        background.setRightAnchor(player,50.0);
        background.autosize();
        PlayerViewController playerViewController = playerLoader.getController();
        playerViewController.initPlayerModel(this);
    }

}
