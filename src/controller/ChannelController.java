package controller;

import com.google.api.services.youtube.model.PlaylistItem;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.BackgroundModel;
import model.ChannelModel;
import model.PlayerModel;
import model.VideoListModel;

import java.io.IOException;
import java.util.ArrayList;
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

    private ArrayList<JFXButton> uploadListButton = new ArrayList<JFXButton>();

    @FXML
    private VBox myUploadsVBox;



    @FXML
    protected void clickUpload(ActionEvent e) {

    }

    public void initChannelModel(final ChannelModel channelModel/*, FXMLLoader uploadListLoader*/) {
        this.channelModel=channelModel;
        final List<PlaylistItem> myUploadsItemList = channelModel.myUploads();
        final List<PlaylistItem> tempMyUploadsItemList = myUploadsItemList;

        /*AnchorUpload.getChildren().add(model.getUpload());
        VideoListController uploadListController = uploadListLoader.getController();
        VideoListModel uploadListModel = new VideoListModel(this.model);
        //uploadListController.initVideoListModel(uploadListModel, AnchorUpload);*/
        //this.videoListModel = videoListModel;
        //final VideoListModel uploadListModel = this.videoListModel;
        //while(tempMyUploadsItemList.iterator().hasNext()) {
        for (int i=0; i<myUploadsItemList.size();i++) {
            final PlaylistItem myUploadsItem = tempMyUploadsItemList.iterator().next();
            System.out.println(myUploadsItem.getSnippet());
            //ImageView image = new ImageView(channelModel.getMainModel().getVideoThumbnail(myUploadsItem));
            JFXButton button = new JFXButton(channelModel.getMainModel().getVideoTitle(myUploadsItem));
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
            myUploadsVBox.getChildren().add(new HBox(/*image,*/ button));
            System.out.println(myUploadsVBox.getChildren());
        }

        //AnchorUpload.getChildren().add(myUploadsVBox);
        //channelModel.getMainModel().getBackgroundModel().getBackground().getChildren().add(AnchorUpload);




        //prettyPrint(playlistItemList.size(), playlistItemList.iterator());
    }
}
