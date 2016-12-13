package model;

import com.google.api.services.youtube.model.SearchResult;

import java.util.List;

/**
 * Created by quentin on 13/12/2016.
 */
public class VideoListModel extends Model{
    Model mainModel;

    public List<SearchResult> getSearchResult() {
        return searchResult;
    }

    List<SearchResult> searchResult;

    public VideoListModel(String query, Model model) {
        searchResult = model.search(5,query);
    }
}
