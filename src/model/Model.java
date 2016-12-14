package model;

import com.google.api.services.youtube.model.*;

/**
 * Created by quentin on 13/12/2016.
 */

public class Model {



    public String getVideoTitle(SearchResult video) {
        return video.getSnippet().getTitle();
    }
    public String getVideoID(SearchResult video){
        String rId = video.getId().getVideoId();
        return rId;
    }
    public String getVideoThumbnail(SearchResult video){
        Thumbnail thumbnail = video.getSnippet().getThumbnails().getDefault();
        return thumbnail.getUrl();
    }



    public String getVideoTitle(PlaylistItem video) {
        return video.getSnippet().getTitle();
    }

    public String getVideoID(PlaylistItem video){
        String rId = video.getContentDetails().getVideoId();
        return rId;
    }

    public String getVideoThumbnail(PlaylistItem video){
        Thumbnail thumbnail = video.getSnippet().getThumbnails().getDefault();
        return thumbnail.getUrl();
    }


}
