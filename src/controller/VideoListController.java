package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.PlayerModel;
import model.VideoListModel;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by quentin on 13/12/2016.
 */
public class VideoListController {

    private VideoListModel videoListModel;

    private ArrayList<ImageView> listImageView = new ArrayList<ImageView>();
    private ArrayList<JFXButton> listButton = new ArrayList<JFXButton>();


    @FXML
    private VBox mainVBox;



    public void initVideoListModel(final VideoListModel videoListModel) {
        this.videoListModel = videoListModel;
        final VideoListModel tempVideoListModel = this.videoListModel;
        final int nbResults = 20;
        for (int i=0; i<nbResults;i++) {
           ImageView image = new ImageView(this.videoListModel.getSearchResult().get(i).getSnippet().getThumbnails().getDefault().getUrl());
           JFXButton button = new JFXButton(videoListModel.getSearchResult().get(i).getSnippet().getTitle());
           button.setOnAction(new EventHandler<ActionEvent>() {
               public void handle(ActionEvent event) {
                   for (int j=0; j<nbResults; j++) {
                       if (event.getSource().equals(listButton.get(j))) {
                           try {
                               PlayerModel playerModel = new PlayerModel(tempVideoListModel.getSearchResult().get(j),videoListModel.getMainModel());
                               playerModel.initPlayer();

                           }
                           catch (IOException e){

                           }
                       }
                   }
               }
           });
           listButton.add(button);
           mainVBox.getChildren().add(new HBox(image, button));
        }

    }




}
