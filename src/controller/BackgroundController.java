package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import model.*;

import java.io.IOException;

/**
 * Created by tld on 13/12/2016.
 */
public class BackgroundController {

    private BackgroundModel backgroundModel;
    private MainModel mainModel;
    private VideoListModel videoListModel;
    private AnchorPane root;
    private AnchorPane background;
    private AnchorPane playerView;


    public void initBackgroundModel(BackgroundModel model){
        this.backgroundModel = model;
    }

    public void initVideoListModel(VideoListModel model){
        this.videoListModel = model;
    }

    public void initMainModel(MainModel model, AnchorPane background){
        this.mainModel = model;
        this.background=background;

    }

    public void setPlayerView(AnchorPane playerView) {
        this.playerView = playerView;
    }



    @FXML
    private JFXButton homeButton;


    @FXML
    private  JFXButton testSample;


    @FXML
    private JFXTextField searchField;


    @FXML
    private JFXButton signInButton;

    @FXML
    private  JFXButton profileButton;


    @FXML
    void clickHome() throws IOException{
        FXMLLoader playerLoader = new FXMLLoader(getClass().getResource("/view/PlayerView.fxml"));

        AnchorPane player = playerLoader.load();
        background.getChildren().add(player);
        background.setBottomAnchor(player,100.0);
        background.setTopAnchor(player,100.0);
        background.setLeftAnchor(player,200.0);
        //background.setRightAnchor(player,100.0);
        background.autosize();
        PlayerViewController playerViewController = playerLoader.getController();
        PlayerModel playerModel = new PlayerModel("_GuOjXYl5ew","Youtube Rewind 2016");
        playerViewController.initPlayerModel(playerModel);
    }

    @FXML
    void clickSample(){
    }

    @FXML
    void keySearch(){

    }

    @FXML
    void clickSearch() throws IOException {

        FXMLLoader videoListLoader = new FXMLLoader(getClass().getResource("/view/VideoListView.fxml"));

        ScrollPane videoList = videoListLoader.load();
        background.getChildren().add(videoList);
        background.getChildren().remove(this.playerView);
        background.setBottomAnchor(videoList,100.0);
        background.setTopAnchor(videoList,100.0);
        background.setLeftAnchor(videoList,100.0);
        background.setRightAnchor(videoList,100.0);
        background.autosize();
        VideoListController videoListController = videoListLoader.getController();
        VideoListModel videoListModel = new VideoListModel(searchField.getText(),mainModel,background,videoList);
        videoListController.initVideoListModel(videoListModel);


    }

    @FXML
    void switchToLogged(){

        mainModel.signIn();
        signInButton.setDisable(true);
        profileButton.setDisable(false);
        signInButton.setVisible(false);
        profileButton.setVisible(true);
    }


    @FXML
    void switchToProfile() throws IOException{
        FXMLLoader channelViewLoader = new FXMLLoader(getClass().getResource("/view/ChannelView.fxml"));

        AnchorPane channelView = channelViewLoader.load();
        background.getChildren().add(channelView);
        background.setBottomAnchor(channelView,100.0);
        background.setTopAnchor(channelView,100.0);
        background.setLeftAnchor(channelView,200.0);
        background.setRightAnchor(channelView,100.0);
        background.autosize();

        //ListUpload
        FXMLLoader uploadListLoader = new FXMLLoader(getClass().getResource("/view/VideoListView.fxml"));
        ScrollPane uploadList = uploadListLoader.load();




        ChannelController channelController = channelViewLoader.getController();
        ChannelModel channelModel = new ChannelModel(uploadList);
        channelController.initChannelModel(channelModel,uploadListLoader);
    }


    public void setRoot(AnchorPane root){
        this.root=root;
    }



}
