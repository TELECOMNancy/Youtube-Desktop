package model;

import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Video;
import controller.BackgroundController;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

import java.util.List;

/**
 * Created by quentin on 13/12/2016.
 */
public class VideoListModel extends Model{

    List<SearchResult> searchResult;
    private AnchorPane background;
    private ScrollPane videoListView;

    public AnchorPane getBackground() {
        return background;
    }

    public ScrollPane getVideoListView() {
        return videoListView;
    }

    List<Video> uploadVideo;

    public List<SearchResult> getSearchResult() {
        return searchResult;
    }

    public VideoListModel(String query,MainModel model, AnchorPane background, ScrollPane videoListView) {
        searchResult = model.search(5,query);
        this.background = background;
        this.videoListView = videoListView;
    }

    public VideoListModel(ChannelModel model) {
        //uploadVideo=model.myUploads();
    }
}
