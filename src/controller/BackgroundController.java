package controller;

import com.google.api.services.youtube.model.Video;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import model.MainModel;
import model.VideoListModel;

import java.awt.event.ActionEvent;

/**
 * Created by tld on 13/12/2016.
 */
public class BackgroundController {
    private MainModel mainModel;
    private VideoListModel videoListModel;


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
    void clickSearch(){

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






}
