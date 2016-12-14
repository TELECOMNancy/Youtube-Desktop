package model;

import com.google.api.services.youtube.model.SearchResult;
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

    public List<SearchResult> getSearchResult() {
        return searchResult;
    }

    public VideoListModel(String query,MainModel model, AnchorPane background, ScrollPane videoListView) {
        searchResult = model.search(5,query);
        this.background = background;
        this.videoListView = videoListView;
    }
}
