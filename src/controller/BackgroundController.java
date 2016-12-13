package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import model.MainModel;

import java.awt.event.ActionEvent;

/**
 * Created by tld on 13/12/2016.
 */
public class BackgroundController {
    private MainModel model;

    public void initModel(MainModel model){
        this.model = model;
    }

    @FXML
    private JFXButton homeButton;



    @FXML
    private  JFXButton testSample;


    @FXML
    private JFXTextField searchField;

    @FXML
    private  JFXButton profileButton;

    @FXML
    private JFXButton signInButton;


    @FXML
    void clickHome(){
    }

    @FXML
    void clickSample(){
    }


    @FXML
    private  JFXButton profileButton;

    @FXML
    void switchToLogged(){

        model.signIn();
        signInButton.setDisable(true);
        profileButton.setDisable(false);
        signInButton.setVisible(false);
        profileButton.setVisible(true);
    }


    @FXML
    void switchToProfile(){
    }






}
