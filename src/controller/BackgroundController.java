package controller;

import com.google.api.services.youtube.model.Video;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import model.MainModel;
import model.VideoListModel;

import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * Created by tld on 13/12/2016.
 */
public class BackgroundController {
    private MainModel mainModel;
    private VideoListModel videoListModel;
    AnchorPane root;


    public void initMainModel(MainModel model){
        this.mainModel = model;
    }

    public void initMainModel(VideoListModel model){
        this.videoListModel = model;
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
        FXMLLoader videoListLoader = new FXMLLoader(getClass().getResource("../view/VideoListView.fxml"));
        AnchorPane videoList = videoListLoader.load();
        root.getChildren().add(videoList);
        root.setBottomAnchor(videoList,0.0);
        root.setTopAnchor(videoList,0.0);
        root.setLeftAnchor(videoList,0.0);
        root.setRightAnchor(videoList,0.0);
        root.autosize();
        VideoListController videoListController = videoListLoader.getController();
        VideoListModel videoListModel = new VideoListModel(searchField.getText());
        videoListController.initModel(videoListModel);

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
