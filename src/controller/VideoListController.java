package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.VideoListModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quentin on 13/12/2016.
 */
public class VideoListController {

    VideoListModel videoListModel;
    private ArrayList<ImageView> listImageView = new ArrayList<ImageView>();

    @FXML
    private JFXButton firstButton;
    @FXML
    private JFXButton secondButton;
    @FXML
    private JFXButton thirdButton;
    @FXML
    private JFXButton fourthButton;
    @FXML
    private JFXButton fifthtButton;

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



    public void initModel(VideoListModel videoListModel) {
        this.videoListModel = videoListModel;
        listImageView.add(imageView0);
        listImageView.add(imageView1);
        listImageView.add(imageView2);
        listImageView.add(imageView3);
        listImageView.add(imageView4);
        for (int i =0; i<5; i++) {
            listImageView.get(i).setImage(new Image(this.videoListModel.getSearchResult().get(i).getSnippet().getThumbnails().getDefault().getUrl()));
        }
    }

    @FXML protected void firstVideo(ActionEvent event) {
        System.out.println(videoListModel.getSearchResult().get(0).getId().getVideoId());
        System.out.println(videoListModel.getSearchResult().get(4).getSnippet().getTitle());
    }

    @FXML protected void secondVideo(ActionEvent event) {
        System.out.println(videoListModel.getSearchResult().get(1).getId().getVideoId());
        System.out.println(videoListModel.getSearchResult().get(4).getSnippet().getTitle());
    }

    @FXML protected void thirdVideo(ActionEvent event) {
        System.out.println(videoListModel.getSearchResult().get(2).getId().getVideoId());
        System.out.println(videoListModel.getSearchResult().get(4).getSnippet().getTitle());
    }

    @FXML protected void fourthVideo(ActionEvent event) {
        System.out.println(videoListModel.getSearchResult().get(3).getId().getVideoId());
        System.out.println(videoListModel.getSearchResult().get(4).getSnippet().getTitle());
    }

    @FXML protected void fifthVideo(ActionEvent event) {
        System.out.println(videoListModel.getSearchResult().get(4).getId().getVideoId());
        System.out.println(videoListModel.getSearchResult().get(4).getSnippet().getTitle());
    }



}
