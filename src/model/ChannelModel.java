package model;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Channel;
import com.google.api.services.youtube.model.ChannelListResponse;
import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by quentin on 13/12/2016.
 */

public class ChannelModel {

    private MainModel mainModel;
    private static YouTube youtube;
    List<PlaylistItem> playlistItemList = new ArrayList<PlaylistItem>();


    public ChannelModel(MainModel mainModel) {
        this.mainModel=mainModel;
    }

    public MainModel getMainModel(){
        return this.mainModel;
    }

    public List<PlaylistItem> myUploads() {

        // This OAuth 2.0 access scope allows for read-only access to the
        // authenticated user's account, but not other types of account access.
        List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube.upload","https://www.googleapis.com/auth/youtube.readonly");
        try {
            // Authorize the request.
            //Credential credential = this.getMainModel().signIn();


            Credential credential = mainModel.authorize(scopes, "myprofile");
            // This object is used to make YouTube Data API requests.
            youtube = new YouTube.Builder(mainModel.HTTP_TRANSPORT, mainModel.JSON_FACTORY, credential).setApplicationName("youtube-cmdline-myuploads-sample").build();

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

        List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube.upload","https://www.googleapis.com/auth/youtube.readonly");
        try {
            Credential credential = mainModel.authorize(scopes, "myprofile");
            youtube = new YouTube.Builder(mainModel.HTTP_TRANSPORT, mainModel.JSON_FACTORY,credential/*new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {
                }
            }*/).setApplicationName("youtube-cmdline-search-sample").build();
            ChannelListResponse channelListResponse = youtube.channels().list("contentDetails").setId(channelID).execute();
            List<Channel> channelsList = channelListResponse.getItems();
            Channel channel = channelsList.get(0);
            if (channelsList != null) {
            // The user's default channel is the first item in the list.
            // Extract the playlist ID for the channel's videos from the
            // API response.
            String uploadPlaylistId = channelsList.get(0).getContentDetails().getRelatedPlaylists().getUploads();
            // Retrieve the playlist of the channel's uploaded videos.
            YouTube.PlaylistItems.List playlistItemRequest = youtube.playlistItems().list("id,contentDetails,snippet");
            playlistItemRequest.setPlaylistId(uploadPlaylistId);
            // Only retrieve data used in this application, thereby making
            // the application more efficient. See:
            // https://developers.google.com/youtube/v3/getting-started#partial
            playlistItemRequest.setFields("items(contentDetails/videoId,snippet/title,snippet/publishedAt,snippet/thumbnails,snippet/channelId,snippet/channelTitle),nextPageToken,pageInfo");
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
