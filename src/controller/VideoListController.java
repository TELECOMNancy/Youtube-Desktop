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



    public void initVideoListModel(VideoListModel videoListModel) {
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
                               initPlayer(new PlayerModel(tempVideoListModel.getSearchResult().get(j)));

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

    public void initPlayer(PlayerModel playerModel) throws IOException{
        System.out.println(videoListModel.getMainModel());
        System.out.println(videoListModel.getMainModel().getBackgroundModel());
        System.out.println(videoListModel.getMainModel().getBackgroundModel().getMainChildren());
        videoListModel.getMainModel().setPlayerModel(playerModel);



        AnchorPane background= videoListModel.getMainModel().getBackgroundModel().getBackground();
        FXMLLoader playerLoader = new FXMLLoader(getClass().getResource("/view/PlayerView.fxml"));

        background.getChildren().remove(videoListModel.getMainModel().getBackgroundModel().getMainChildren());
        AnchorPane player = playerLoader.load();
        //background.setPlayerView(player);
        background.getChildren().add(player);
        videoListModel.getMainModel().getBackgroundModel().setMainChildren(player);
        background.setBottomAnchor(player,100.0);
        background.setTopAnchor(player,100.0);
        background.setLeftAnchor(player,100.0);
        background.setRightAnchor(player,100.0);
        background.autosize();
        PlayerViewController playerViewController = playerLoader.getController();
        playerViewController.initPlayerModel(playerModel,background);
    }




}
