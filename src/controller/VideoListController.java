package controller;

import com.google.api.services.youtube.model.SearchResult;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
    private JFXButton firstButton;
    @FXML
    private JFXButton secondButton;
    @FXML
    private JFXButton thirdButton;
    @FXML
    private JFXButton fourthButton;
    @FXML
    private JFXButton fifthButton;

    @FXML
    private ImageView imageView0;
    @FXML
    private ImageView imageView1;
    @FXML
    private ImageView imageView2;
    @FXML
    private ImageView imageView3;
    @FXML
    private ImageView imageView4;



    public void initVideoListModel(VideoListModel videoListModel) {
        this.videoListModel = videoListModel;
        listButton.add(firstButton);
        listButton.add(secondButton);
        listButton.add(thirdButton);
        listButton.add(fourthButton);
        listButton.add(fifthButton);
        listImageView.add(imageView0);
        listImageView.add(imageView1);
        listImageView.add(imageView2);
        listImageView.add(imageView3);
        listImageView.add(imageView4);
        for (int i =0; i<5; i++) {
            listImageView.get(i).setImage(new Image(this.videoListModel.getSearchResult().get(i).getSnippet().getThumbnails().getDefault().getUrl()));
            listButton.get(i).setText(videoListModel.getSearchResult().get(i).getSnippet().getTitle());
        }
    }

    public void initPlayer(PlayerModel playerModel) throws IOException{
        AnchorPane background= videoListModel.getBackground();

        FXMLLoader playerLoader = new FXMLLoader(getClass().getResource("/view/PlayerView.fxml"));
        videoListModel.getBackground().getChildren().remove(videoListModel.getVideoListView());
        AnchorPane player = playerLoader.load();
        //background.setPlayerView(player);
        background.getChildren().add(player);
        background.setBottomAnchor(player,100.0);
        background.setTopAnchor(player,100.0);
        background.setLeftAnchor(player,100.0);
        background.setRightAnchor(player,100.0);
        background.autosize();
        PlayerViewController playerViewController = playerLoader.getController();
        playerViewController.initPlayerModel(playerModel);
    }

    @FXML protected void firstVideo(ActionEvent event) throws IOException {
        this.initPlayer(new PlayerModel(videoListModel.getSearchResult().get(0)));


    }

    @FXML protected void secondVideo(ActionEvent event)throws IOException {
        this.initPlayer(new PlayerModel(videoListModel.getSearchResult().get(1)));

    }

    @FXML protected void thirdVideo(ActionEvent event) throws IOException{
        this.initPlayer(new PlayerModel(videoListModel.getSearchResult().get(2)));


    }

    @FXML protected void fourthVideo(ActionEvent event) throws IOException{
        this.initPlayer(new PlayerModel(videoListModel.getSearchResult().get(3)));

    }

    @FXML protected void fifthVideo(ActionEvent event) throws IOException{
        this.initPlayer(new PlayerModel(videoListModel.getSearchResult().get(4)));


    }
    @FXML
    void switchToPlayer(ActionEvent event, AnchorPane root, SearchResult video) {
        try {
            AnchorPane centerPlayer = FXMLLoader.load(getClass().getResource("/view/PlayerView.fxml"));
            root.setBottomAnchor(centerPlayer,0.0);
            PlayerModel player= new PlayerModel(video);
            centerPlayer.getChildren().add(player.getPlayer());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
