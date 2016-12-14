package model;

import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Video;
import controller.BackgroundController;

import java.util.List;

/**
 * Created by quentin on 13/12/2016.
 */
public class VideoListModel extends Model{

    List<SearchResult> searchResult;

    List<Video> uploadVideo;

    public List<SearchResult> getSearchResult() {
        return searchResult;
    }

    public List<Video> getUploadVideo(){return uploadVideo;}

    public VideoListModel(String query, BackgroundModel model) {
        searchResult = model.search(5,query);
        //System.out.println(searchResult);
    }

    public VideoListModel(ChannelModel model) {
        //uploadVideo=model.myUploads();
    }
}
