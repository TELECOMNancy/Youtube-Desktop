package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import model.UploadModel;

import java.io.File;

/**
 * Created by M.ESCAMEZ on 14/12/2016.
 */
public class UploadController {
    private UploadModel uploadModel;
    private String path;
    private String title;
    private String description;
    private String status;

    public void initUploadModel(UploadModel uploadModel){
        this.uploadModel=uploadModel;
    }

    @FXML
    private JFXRadioButton publicButton;
    @FXML
    private JFXRadioButton unreferencedButton;
    @FXML
    private JFXRadioButton privateButton;
    @FXML
    private JFXButton BrowseButton;
    @FXML
    private JFXButton UploadButton;
    @FXML
    private Text pathText;
    @FXML
    private Text TitleText;
    @FXML
    private JFXTextField TitleField;
    @FXML
    private JFXTextField DescriptionField;


    @FXML
    void clickUpload (){
        this.title=TitleField.getText();
        this.description=DescriptionField.getText();
        if (publicButton.isSelected()){
            this.status="public";
        } else if (privateButton.isSelected()) {
            this.status="private";
        } else if (unreferencedButton.isSelected()) {
            this.status="unlisted";
        }
        uploadModel.getMainModel().getUploadModel().upload(title,path,description,status);
    }

    @FXML
    void clickBrowse(){
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose a video");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Video Files","*.mpeg4","*.mp4", "*.mov", "*.avi", "*.wmv", "*.mpegps", "*.flv", "*.3gp", "*.webm"), new FileChooser.ExtensionFilter("All Files", "*.*"));
        File file = chooser.showOpenDialog(uploadModel.getMainModel().getStage());
        this.path=file.getPath();
        pathText.setText(this.path);
    }



}
