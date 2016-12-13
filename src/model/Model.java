package model;

import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;

/**
 * Created by quentin on 13/12/2016.
 */
public class Model {
    public String getVideoTitle(SearchResult video) {

        return video.getSnippet().getTitle();
    }


    public ResourceId getVideoID(SearchResult video){
        ResourceId rId = video.getId();
        return rId;
    }


    public String getVideoThumbnail(SearchResult video){
        Thumbnail thumbnail = video.getSnippet().getThumbnails().getDefault();
        return thumbnail.getUrl();
    }

}
