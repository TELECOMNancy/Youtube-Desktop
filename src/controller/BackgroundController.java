package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import model.*;

import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 * Created by tld on 13/12/2016.
 */
public class BackgroundController {

    private BackgroundModel backgroundModel;
    private AnchorPane backgroundView;

    @FXML
    private JFXSpinner loadingSpinner;

    public JFXSpinner getLoadingSpinner() {
        return loadingSpinner;
    }

    @FXML
    private JFXButton homeButton;


    @FXML
    private  JFXButton signOutButton;
    @FXML
    private JFXTextField searchField;
    @FXML
    private JFXButton signInButton;
    @FXML
    private  JFXButton profileButton;
    @FXML
    private JFXButton uploadButton;

    @FXML
    private ImageView homeLogo;

    @FXML
    private ImageView profilLogo;

    @FXML
    private ImageView uploadLogo;

    @FXML
    private ImageView profileImageView;


    public void initBackgroundController(BackgroundModel backgroundModel,AnchorPane backgroundView){
        this.backgroundModel=backgroundModel;
        this.backgroundModel.setLoadingSpinner(this.loadingSpinner);
        this.backgroundView=backgroundView;
        this.homeLogo.setImage(new Image("/home.png"));
        this.profilLogo.setImage(new Image("/profil.png"));
        this.uploadLogo.setImage(new Image("/upload.png"));
    }
    @FXML
    void clickHome() throws IOException{

        backgroundModel.getMainModel().getPlayerModel().getPlayer().getVideoPlayer().getEngine().load(null);
        backgroundView.getChildren().remove(backgroundModel.getMainChildren());
        FXMLLoader playerLoader = new FXMLLoader(getClass().getResource("/view/PlayerView.fxml"));
        AnchorPane player = playerLoader.load();
        backgroundView.getChildren().add(player);
        backgroundModel.setMainChildren(player);
        backgroundView.setBottomAnchor(player,30.0);
        backgroundView.setTopAnchor(player,150.0);
        backgroundView.setLeftAnchor(player,300.0);
        backgroundView.setRightAnchor(player,50.0);
        backgroundView.autosize();
        PlayerViewController playerViewController = playerLoader.getController();
        PlayerModel playerModel =  new PlayerModel("_GuOjXYl5ew","YouTube Rewind: The Ultimate 2016 Challenge | #YouTubeRewind","YouTube Spotlight","UCBR8-60-B28hp2BmDPdntcQ",backgroundModel.getMainModel());
        backgroundModel.getMainModel().setPlayerModel(playerModel);
        playerViewController.initPlayerModel(playerModel);
    }

    @FXML
    void clickUpload() throws IOException{
        backgroundModel.getMainModel().getBackgroundModel().getBackground().getChildren().remove(backgroundModel.getMainModel().getBackgroundModel().getMainChildren());
        FXMLLoader uploadLoader = new FXMLLoader(getClass().getResource("/view/UploadView.fxml"));
        AnchorPane uploadView = uploadLoader.load();
        backgroundModel.getMainModel().getBackgroundModel().getBackground().getChildren().add(uploadView);
        backgroundModel.getMainModel().getBackgroundModel().setMainChildren(uploadView);
        backgroundModel.getMainModel().getBackgroundModel().getBackground().setBottomAnchor(uploadView,100.0);
        backgroundModel.getMainModel().getBackgroundModel().getBackground().setTopAnchor(uploadView,100.0);
        backgroundModel.getMainModel().getBackgroundModel().getBackground().setLeftAnchor(uploadView,200.0);
        //background.setRightAnchor(player,100.0);
        backgroundModel.getMainModel().getBackgroundModel().getBackground().autosize();
        UploadController uploadController = uploadLoader.getController();
        UploadModel uploadModel = new UploadModel(backgroundModel.getMainModel());
        backgroundModel.getMainModel().setUploadModel(uploadModel);
        uploadController.initUploadModel(uploadModel);
    }

    @FXML
    void keySearch(){

    }

    @FXML
    void clickSearch() throws IOException {

        backgroundModel.getMainModel().getPlayerModel().getPlayer().getVideoPlayer().getEngine().load(null);
        FXMLLoader videoListLoader = new FXMLLoader(getClass().getResource("/view/VideoListView.fxml"));
        ScrollPane videoList = videoListLoader.load();
        backgroundView.getChildren().remove(backgroundModel.getMainChildren());
        backgroundView.getChildren().add(videoList);
        backgroundModel.setMainChildren(videoList);
        backgroundView.setBottomAnchor(videoList,100.0);
        backgroundView.setTopAnchor(videoList,100.0);
        backgroundView.setLeftAnchor(videoList,150.0);
        backgroundView.setRightAnchor(videoList,100.0);
        backgroundView.autosize();
        VideoListController videoListController = videoListLoader.getController();
        if (searchField.getText().equals("kamino")) {
            FXMLLoader popUpLoader = new FXMLLoader(getClass().getResource("/view/credits.fxml"));
            AnchorPane popUpView = popUpLoader.load();
            PopUpController popUpController = popUpLoader.getController();
            popUpController.initCredits(popUpView);
        }
        VideoListModel videoListModel = new VideoListModel(searchField.getText(), backgroundModel.getMainModel(),20);
        videoListController.initVideoListModel(videoListModel);

    }

    @FXML
    void pressEnter(javafx.scene.input.KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER){
            clickSearch();
        }
    }


    @FXML
    void switchToLogged(){
        backgroundModel.getMainModel().signIn();
        signInButton.setDisable(true);
        signInButton.setVisible(false);
        profileButton.setDisable(false);
        profileButton.setVisible(true);
        signOutButton.setDisable(false);
        signOutButton.setVisible(true);
        uploadButton.setDisable(false);
        uploadButton.setVisible(true);
        profileImageView.setImage(new ImageView(backgroundModel.getMainModel().getMyChannelThumbnail()).getImage());
    }

    @FXML
    void switchToUnlogged() throws IOException{
        backgroundModel.getMainModel().signOut();
        signOutButton.setDisable(true);
        signOutButton.setVisible(false);
        profileButton.setDisable(true);
        profileButton.setVisible(false);
        signInButton.setDisable(false);
        signInButton.setVisible(true);
        uploadButton.setDisable(true);
        uploadButton.setVisible(false);
        profileImageView.setImage(null);
        backgroundModel.getMainModel().getPlayerModel().getPlayer().getVideoPlayer().getEngine().load(null);
        backgroundView.getChildren().remove(backgroundModel.getMainChildren());
        FXMLLoader playerLoader = new FXMLLoader(getClass().getResource("/view/PlayerView.fxml"));
        AnchorPane player = playerLoader.load();
        backgroundView.getChildren().add(player);
        backgroundModel.setMainChildren(player);
        backgroundView.setBottomAnchor(player,30.0);
        backgroundView.setTopAnchor(player,150.0);
        backgroundView.setLeftAnchor(player,300.0);
        backgroundView.setRightAnchor(player,50.0);
        backgroundView.autosize();
        PlayerViewController playerViewController = playerLoader.getController();
        PlayerModel playerModel =  new PlayerModel("_GuOjXYl5ew","YouTube Rewind: The Ultimate 2016 Challenge | #YouTubeRewind","YouTube Spotlight","UCBR8-60-B28hp2BmDPdntcQ",backgroundModel.getMainModel());
        playerViewController.initPlayerModel(playerModel);


    }

    @FXML
    void switchToProfile() throws IOException {



        backgroundModel.getMainModel().getPlayerModel().getPlayer().getVideoPlayer().getEngine().load(null);
        backgroundView.getChildren().remove(backgroundModel.getMainChildren());
        FXMLLoader channelLoader = new FXMLLoader(getClass().getResource("/view/ChannelView.fxml"));
        AnchorPane channelView = channelLoader.load();
        backgroundView.getChildren().add(channelView);
        backgroundModel.setMainChildren(channelView);
        backgroundView.setBottomAnchor(channelView,100.0);
        backgroundView.setTopAnchor(channelView,100.0);
        backgroundView.setLeftAnchor(channelView,200.0);
        //background.setRightAnchor(player,100.0);
        backgroundView.autosize();
        ChannelController channelController = channelLoader.getController();
        ChannelModel channelModel = new ChannelModel(backgroundModel.getMainModel());
        channelController.initMyChannelModel(channelModel);

    }

}
