package model;

import com.google.api.services.youtube.model.SearchResult;
import controller.BackgroundController;

import java.util.List;

/**
 * Created by quentin on 13/12/2016.
 */
public class VideoListModel extends Model{

    List<SearchResult> searchResult;

    public List<SearchResult> getSearchResult() {
        return searchResult;
    }

    public VideoListModel(String query, BackgroundModel model) {
        searchResult = model.search(5,query);
        //System.out.println(searchResult);
    }
}
