package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import model.UploadModel;

import java.io.File;

/**
 * Created by M.ESCAMEZ on 14/12/2016.
 */
public class UploadController {
    private UploadModel model;
    private String path;
    private String title;
    private String description;
    private String status;
    @FXML
    private JFXCheckBox PublicCheck;
    @FXML
    private JFXCheckBox UnlistedCheck;
    @FXML
    private JFXCheckBox PrivateCheck;
    @FXML
    private JFXButton BrowseButton;
    @FXML
    private JFXButton UploadButton;
    @FXML
    private Text pathText;
    @FXML
    private Text TitleText;



    @FXML
    void clickPublic (ActionEvent e){

    }

    @FXML
    void clickPrivate (ActionEvent e){

    }

    @FXML
    void clickUnlisted(ActionEvent e){

    }

    @FXML
    void clickUpload (ActionEvent e){
        model.getMainModel().getUploadModel().upload(title,path,description,status);
    }

    @FXML
    void clickBrowse(ActionEvent e){
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose a video");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Video Files","mpeg","mp4", "quicktime", "x-ms-wmv", "x-msvideo", "x-flv, webm"), new FileChooser.ExtensionFilter("All Files", "*.*"));
        File file = chooser.showOpenDialog(model.getMainModel().getStage());
        this.path=file.getPath();
        pathText.setText(this.path);
    }



}
