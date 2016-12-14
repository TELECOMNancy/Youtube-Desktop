package model;

import com.google.api.services.youtube.model.Channel;
import javafx.scene.control.ScrollPane;


/**
 * Created by quentin on 13/12/2016.
 */
public class ChannelModel extends Model{
    private ScrollPane upload;
    private ScrollPane playlist;
    private ScrollPane liked;
    public ChannelModel(ScrollPane upload) {
        this.upload=upload;
    }

    public ScrollPane getUpload(){
        return upload;
    }
    public ScrollPane getPlaylist(){
        return playlist;
    }
    public ScrollPane getLiked(){
        return liked;
    }
}

