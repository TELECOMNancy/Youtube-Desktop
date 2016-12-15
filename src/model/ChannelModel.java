package model;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Channel;
import com.google.api.services.youtube.model.ChannelListResponse;
import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
import com.google.common.collect.Lists;
import javafx.scene.control.ScrollPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by quentin on 13/12/2016.
 */

public class ChannelModel {
    private ScrollPane upload;
    private ScrollPane playlist;
    private ScrollPane liked;
    private MainModel mainModel;
    private long NUMBER_OF_VIDEOS_RETURNED = 20;


    public ChannelModel(MainModel mainModel) {
        this.mainModel=mainModel;
    }

    public MainModel getMainModel(){
        return this.mainModel;
    }

    private String channelTitle;
    private String channelThumbnail;

    public String getChannelTitle(){
        return this.channelTitle;
    }

    public String getChannelThumbnail(){
        return this.channelThumbnail;
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

    private static YouTube youtube;
    // Define a list to store items in the list of uploaded videos.
    List<PlaylistItem> playlistItemList = new ArrayList<PlaylistItem>();

    public List<PlaylistItem> myUploads() {

        // This OAuth 2.0 access scope allows for read-only access to the
        // authenticated user's account, but not other types of account access.
        List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube.upload","https://www.googleapis.com/auth/youtube.readonly","https://www.googleapis.com/auth/userinfo.profile");
        try {
            // Authorize the request.
            //Credential credential = this.getMainModel().signIn();


            Credential credential = Auth.authorize(scopes, "myprofile");
            // This object is used to make YouTube Data API requests.
            youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, credential).setApplicationName("youtube-cmdline-myuploads-sample").build();

            // Call the API's channels.list method to retrieve the
            // resource that represents the authenticated user's channel.
            // In the API response, only include channel information needed for
            // this use case. The channel's contentDetails part contains
            // playlist IDs relevant to the channel, including the ID for the
            // list that contains videos uploaded to the channel.
            YouTube.Channels.List channelRequest = youtube.channels().list("contentDetails");
            channelRequest.setMine(true);
            channelRequest.setFields("items/contentDetails,nextPageToken,pageInfo");
            ChannelListResponse channelResult = channelRequest.execute();

            List<Channel> channelsList = channelResult.getItems();

            if (channelsList != null) {
                // The user's default channel is the first item in the list.
                // Extract the playlist ID for the channel's videos from the
                // API response.
                String uploadPlaylistId =
                        channelsList.get(0).getContentDetails().getRelatedPlaylists().getUploads();




                // Retrieve the playlist of the channel's uploaded videos.
                YouTube.PlaylistItems.List playlistItemRequest =
                        youtube.playlistItems().list("id,contentDetails,snippet");
                playlistItemRequest.setPlaylistId(uploadPlaylistId);

                // Only retrieve data used in this application, thereby making
                // the application more efficient. See:
                // https://developers.google.com/youtube/v3/getting-started#partial
                playlistItemRequest.setFields(
                        "items(contentDetails/videoId,snippet/title,snippet/publishedAt,snippet/thumbnails,snippet/channelTitle,snippet/channelId),nextPageToken,pageInfo");
                playlistItemRequest.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);

                String nextToken = "";

                // Call the API one or more times to retrieve all items in the
                // list. As long as the API response returns a nextPageToken,
                // there are still more items to retrieve.
                do {
                    playlistItemRequest.setPageToken(nextToken);
                    PlaylistItemListResponse playlistItemResult = playlistItemRequest.execute();

                    playlistItemList.addAll(playlistItemResult.getItems());

                    nextToken = playlistItemResult.getNextPageToken();
                } while (nextToken != null);

            }

        } catch (GoogleJsonResponseException e) {
            e.printStackTrace();
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());

        } catch (Throwable t) {
            t.printStackTrace();
        }
        return playlistItemList;
    }


    public List<PlaylistItem> channelUploads(String channelID) throws IOException {

        List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube.upload","https://www.googleapis.com/auth/youtube.readonly","https://www.googleapis.com/auth/userinfo.profile");
        try {
            Credential credential = Auth.authorize(scopes, "myprofile");
            youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY,credential/*new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {
                }
            }*/).setApplicationName("youtube-cmdline-search-sample").build();
            ChannelListResponse channelListResponse = youtube.channels().list("contentDetails, snippet").setId(channelID).execute();

            List<Channel> channelsList = channelListResponse.getItems();
            Channel channel = channelsList.get(0);
            if (channelsList != null) {
            // The user's default channel is the first item in the list.
            // Extract the playlist ID for the channel's videos from the
            // API response.
            String uploadPlaylistId = channelsList.get(0).getContentDetails().getRelatedPlaylists().getUploads();
            this.channelTitle = channelsList.get(0).getSnippet().getTitle();
            this.channelThumbnail = channelsList.get(0).getSnippet().getThumbnails().getHigh().getUrl();
            // Retrieve the playlist of the channel's uploaded videos.
            YouTube.PlaylistItems.List playlistItemRequest = youtube.playlistItems().list("id,contentDetails,snippet");
            playlistItemRequest.setPlaylistId(uploadPlaylistId);
            // Only retrieve data used in this application, thereby making
            // the application more efficient. See:
            // https://developers.google.com/youtube/v3/getting-started#partial
            playlistItemRequest.setFields("items(contentDetails/videoId,snippet/title,snippet/publishedAt,snippet/thumbnails,snippet/channelId,snippet/channelTitle),nextPageToken,pageInfo");
            playlistItemRequest.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
            String nextToken = "";
            // Call the API one or more times to retrieve all items in the
            // list. As long as the API response returns a nextPageToken,
            // there are still more items to retrieve.
            do {
                playlistItemRequest.setPageToken(nextToken);
                PlaylistItemListResponse playlistItemResult = playlistItemRequest.execute();

                playlistItemList.addAll(playlistItemResult.getItems());

                nextToken = playlistItemResult.getNextPageToken();
            } while (nextToken != null);

         }

        } catch (GoogleJsonResponseException e) {
            e.printStackTrace();
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : " + e.getDetails().getMessage());
        } catch (Throwable t) {
            t.printStackTrace(); }
        return playlistItemList;
    }




}
