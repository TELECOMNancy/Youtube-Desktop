package controller;

import com.google.api.services.youtube.model.Video;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.BackgroundModel;
import model.MainModel;
import model.PlayerModel;
import model.VideoListModel;
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
    private  JFXButton testSample;


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
        backgroundView.setBottomAnchor(player,100.0);
        backgroundView.setTopAnchor(player,100.0);
        backgroundView.setLeftAnchor(player,200.0);
        //background.setRightAnchor(player,100.0);
        backgroundView.autosize();
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
        backgroundModel.getMainModel().getPlayerModel().getPlayer().getVideoPlayer().getEngine().load(null);
        FXMLLoader videoListLoader = new FXMLLoader(getClass().getResource("/view/VideoListView.fxml"));
        ScrollPane videoList = videoListLoader.load();
        backgroundView.getChildren().remove(backgroundModel.getMainChildren());
        backgroundView.getChildren().add(videoList);
        backgroundModel.setMainChildren(videoList);
        backgroundView.setBottomAnchor(videoList,100.0);
        backgroundView.setTopAnchor(videoList,100.0);
        backgroundView.setLeftAnchor(videoList,100.0);
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
        profileButton.setDisable(false);
        signInButton.setVisible(false);
        profileButton.setVisible(true);
    }


    @FXML
    void switchToProfile(){
    }




}
