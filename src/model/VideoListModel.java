package model;

import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Video;

import java.util.List;

/**
 * Created by quentin on 13/12/2016.
 */
public class VideoListModel extends Model{

    private List<SearchResult> searchResult;

    private MainModel mainModel;


    List<Video> uploadVideo;

    public List<SearchResult> getSearchResult() {
        return searchResult;
    }

    public MainModel getMainModel() {
        return mainModel;
    }

    public VideoListModel(String query, MainModel mainModel, int nbResult) {
        searchResult = mainModel.search(nbResult,query);
        this.mainModel = mainModel;
    }

    public VideoListModel(ChannelModel model) {
        //uploadVideo=model.myUploads();
    }
}
