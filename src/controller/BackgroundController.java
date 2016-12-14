package controller;

import com.google.api.services.youtube.model.Video;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import model.BackgroundModel;
import model.MainModel;
import model.VideoListModel;
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
    void clickHome(){
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
        background.setBottomAnchor(videoList,100.0);
        background.setTopAnchor(videoList,100.0);
        background.setLeftAnchor(videoList,100.0);
        background.setRightAnchor(videoList,100.0);
        background.autosize();
        VideoListController videoListController = videoListLoader.getController();
        VideoListModel videoListModel = new VideoListModel(searchField.getText(), new BackgroundModel());
        videoListController.initVideoListModel(videoListModel, background, videoList);

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
    void switchToProfile(){
    }


    public void setRoot(AnchorPane root){
        this.root=root;
    }



}
