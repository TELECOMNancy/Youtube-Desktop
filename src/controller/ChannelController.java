package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import model.BackgroundModel;
import model.ChannelModel;
import model.VideoListModel;

/**
 * Created by léo on 14/12/2016.
 */
public class ChannelController {
    private ChannelModel model;

    @FXML
    private JFXButton UploadButton;
    @FXML
    private AnchorPane AnchorPlaylist;
    @FXML
    private AnchorPane AnchorUpload;
    @FXML
    private AnchorPane AnchorProfilePicture;
    @FXML
    private AnchorPane AnchorLike;



    @FXML
    protected void clickUpload(ActionEvent e) {
    }

    public void initChannelModel(ChannelModel model, FXMLLoader uploadListLoader) {
        this.model=model;
        AnchorUpload.getChildren().add(model.getUpload());
        VideoListController uploadListController = uploadListLoader.getController();
        VideoListModel uploadListModel = new VideoListModel(this.model);
        //uploadListController.initVideoListModel(uploadListModel, AnchorUpload);
    }
}
