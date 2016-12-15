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
    private AnchorPane backgroundView;



    public void initBackgroundController(BackgroundModel backgroundModel,AnchorPane backgroundView){
        this.backgroundModel=backgroundModel;
        this.backgroundView=backgroundView;
    }


    @FXML
    private JFXButton homeButton;


    @FXML
    private  JFXButton signOutButton;


    @FXML
    private JFXTextField searchField;


    @FXML
    private JFXButton signInButton;

    @FXML
    private  JFXButton profileButton;


    @FXML
    void clickHome() throws IOException{
        backgroundModel.getMainModel().getPlayerModel().getPlayer().getVideoPlayer().getEngine().load(null);
        backgroundView.getChildren().remove(backgroundModel.getMainChildren());
        FXMLLoader playerLoader = new FXMLLoader(getClass().getResource("/view/PlayerView.fxml"));
        AnchorPane player = playerLoader.load();
        backgroundView.getChildren().add(player);
        backgroundModel.setMainChildren(player);
        backgroundView.setBottomAnchor(player,30.0);
        backgroundView.setTopAnchor(player,150.0);
        backgroundView.setLeftAnchor(player,300.0);
        backgroundView.setRightAnchor(player,50.0);
        backgroundView.autosize();
        PlayerViewController playerViewController = playerLoader.getController();
        PlayerModel playerModel = new PlayerModel("_GuOjXYl5ew","Youtube Rewind 2016",backgroundModel.getMainModel());
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
        backgroundModel.getMainModel().getPlayerModel().getPlayer().getVideoPlayer().getEngine().load(null);
        FXMLLoader videoListLoader = new FXMLLoader(getClass().getResource("/view/VideoListView.fxml"));
        ScrollPane videoList = videoListLoader.load();
        backgroundView.getChildren().remove(backgroundModel.getMainChildren());
        backgroundView.getChildren().add(videoList);
        backgroundModel.setMainChildren(videoList);
        backgroundView.setBottomAnchor(videoList,100.0);
        backgroundView.setTopAnchor(videoList,100.0);
        backgroundView.setLeftAnchor(videoList,150.0);
        backgroundView.setRightAnchor(videoList,100.0);
        backgroundView.autosize();
        VideoListController videoListController = videoListLoader.getController();
        VideoListModel videoListModel = new VideoListModel(searchField.getText(), backgroundModel.getMainModel(),20);
        videoListController.initVideoListModel(videoListModel);

    }

    @FXML
    void switchToLogged(){

        backgroundModel.getMainModel().signIn();
        signInButton.setDisable(true);
        signInButton.setVisible(false);
        profileButton.setDisable(false);
        profileButton.setVisible(true);
        signOutButton.setDisable(false);
        signOutButton.setVisible(true);
    }

    @FXML
    void switchToUnlogged() {
        signOutButton.setDisable(true);
        signOutButton.setVisible(false);
        profileButton.setDisable(true);
        profileButton.setVisible(false);
        signInButton.setDisable(false);
        signInButton.setVisible(true);
    }


    @FXML
    void switchToProfile() throws IOException{
        backgroundModel.getMainModel().getPlayerModel().getPlayer().getVideoPlayer().getEngine().load(null);
        backgroundView.getChildren().remove(backgroundModel.getMainChildren());
        FXMLLoader channelLoader = new FXMLLoader(getClass().getResource("/view/ChannelView.fxml"));
        AnchorPane channelView = channelLoader.load();
        backgroundView.getChildren().add(channelView);
        backgroundModel.setMainChildren(channelView);
        backgroundView.setBottomAnchor(channelView,100.0);
        backgroundView.setTopAnchor(channelView,100.0);
        backgroundView.setLeftAnchor(channelView,200.0);
        //background.setRightAnchor(player,100.0);
        backgroundView.autosize();
        ChannelController channelController = channelLoader.getController();
        ChannelModel channelModel = new ChannelModel(backgroundModel.getMainModel());
        channelController.initChannelModel(channelModel);
        /*
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
        channelController.initChannelModel(channelModel,uploadListLoader);*/
    }




}
