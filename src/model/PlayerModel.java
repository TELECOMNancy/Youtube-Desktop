package model;

import com.google.api.client.repackaged.com.google.common.base.Strings;
import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.SearchResult;
import controller.PlayerViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import view.Player;
import java.io.IOException;
import java.util.*;

/**
 * Created by quentin on 13/12/2016.
 */
public class PlayerModel {
    private String title;
    private String channelTitle;
    private String channelId;
    private Player player;
    private MainModel mainModel;
    private static YouTube youtube;


    public PlayerModel(SearchResult video, MainModel mainModel){
        this.mainModel=mainModel;
        this.title= ""+mainModel.getVideoTitle(video);
        this.channelTitle=mainModel.getChannelTitle(video);
        this.channelId=mainModel.getChannelId(video);
        this.player=new Player(mainModel.getVideoID(video));


    }

    public PlayerModel(PlaylistItem video, MainModel mainModel){
        this.mainModel=mainModel;
        this.title= ""+mainModel.getVideoTitle(video);
        this.player=new Player(mainModel.getVideoID(video));
        this.channelTitle=video.getSnippet().getChannelTitle();
        this.channelId=video.getSnippet().getChannelId();
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
    public String getChannelTitle(){return channelTitle;}
    public String getChannelId() {return channelId;}


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
