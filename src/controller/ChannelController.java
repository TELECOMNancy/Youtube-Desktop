package controller;

import com.google.api.services.youtube.model.PlaylistItem;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by léo on 14/12/2016.
 */
public class ChannelController {

    private ChannelModel channelModel;
    private AnchorPane channelView;

    public void initChannelController(ChannelModel channelModel){
        this.channelModel=channelModel;
    }


    /*@FXML
    private JFXButton UploadButton;
    */
    @FXML
    private AnchorPane AnchorProfilePicture;
    @FXML
    private AnchorPane AnchorLike;

    private ArrayList<JFXButton> uploadListButton = new ArrayList<JFXButton>();

    @FXML
    private VBox myUploadsVBox;

    @FXML
    private ImageView channelThumbnail;

    @FXML
    private Text channelTitle;




    public void initMyChannelModel(final ChannelModel channelModel/*, FXMLLoader uploadListLoader*/) {
        this.channelModel=channelModel;
        final List<PlaylistItem> myUploadsItemList = channelModel.myUploads();
        final List<PlaylistItem> tempMyUploadsItemList = myUploadsItemList;
        final Iterator iterator = tempMyUploadsItemList.iterator();
        /*AnchorUpload.getChildren().add(model.getUpload());
        VideoListController uploadListController = uploadListLoader.getController();
        VideoListModel uploadListModel = new VideoListModel(this.model);
        //uploadListController.initVideoListModel(uploadListModel, AnchorUpload);*/
        //this.videoListModel = videoListModel;
        //final VideoListModel uploadListModel = this.videoListModel;
        //while(tempMyUploadsItemList.iterator().hasNext()) {
        for (int i=0; i<myUploadsItemList.size();i++) {
            PlaylistItem myUploadsItem = (PlaylistItem)iterator.next();
            ImageView image = new ImageView(channelModel.getMainModel().getVideoThumbnail(myUploadsItem));
            JFXButton button = new JFXButton(myUploadsItem.getSnippet().getTitle());
            button.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    for (int j=0; j<myUploadsItemList.size(); j++) {
                        if (event.getSource().equals(uploadListButton.get(j))) {
                            try {
                                PlayerModel playerModel = new PlayerModel(tempMyUploadsItemList.get(j),channelModel.getMainModel());
                                playerModel.initPlayer();

                            }
                            catch (IOException e){

                            }
                        }
                    }
                }
            });
            uploadListButton.add(button);
            myUploadsVBox.getChildren().add(new HBox(image, button));
        }

        //AnchorUpload.getChildren().add(myUploadsVBox);
        //channelModel.getMainModel().getBackgroundModel().getBackground().getChildren().add(AnchorUpload);




        //prettyPrint(playlistItemList.size(), playlistItemList.iterator());
    }

    public void initChannelModel(final ChannelModel channelModel) throws IOException {
        this.channelModel=channelModel;
        final List<PlaylistItem> UploadsItemList = channelModel.channelUploads(channelModel.getMainModel().getPlayerModel().getChannelId());
        final List<PlaylistItem> tempUploadsItemList = UploadsItemList;
        final Iterator iterator = tempUploadsItemList.iterator();
        channelTitle.setText(channelModel.getChannelTitle());
        channelThumbnail.setImage(new ImageView(channelModel.getChannelThumbnail()).getImage());
        this.channelThumbnail = new ImageView(channelModel.getChannelThumbnail());
        for (int i=0; i<20;i++) {
            PlaylistItem UploadsItem = (PlaylistItem)iterator.next();
            ImageView image = new ImageView(channelModel.getMainModel().getVideoThumbnail(UploadsItem));
            JFXButton button = new JFXButton(UploadsItem.getSnippet().getTitle());
            button.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    for (int j=0; j<20; j++) {
                        if (event.getSource().equals(uploadListButton.get(j))) {
                            try {
                                PlayerModel playerModel = new PlayerModel(tempUploadsItemList.get(j),channelModel.getMainModel());
                                playerModel.initPlayer();

                            }
                            catch (IOException e){

                            }
                        }
                    }
                }
            });
            uploadListButton.add(button);
            myUploadsVBox.getChildren().add(new HBox(image, button));
        }
    }


}
