package model;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import com.google.common.collect.Lists;
import javafx.stage.Stage;

import java.util.List;

/**
 * Created by tld on 12/12/2016.
 */

public class MainModel extends Model{

    private String mainVideoName;
    private PlayerModel playerModel;
    private BackgroundModel backgroundModel;
    private VideoListModel videoListModel;
    private UploadModel uploadModel;
    private Stage stage;





    private static YouTube youtube;
    private static final String VIDEO_FILE_FORMAT = "video/*";
    private static final String SAMPLE_VIDEO_FILENAME = "sample-video.mp4";
    private static final String PROPERTIES_FILENAME = "youtube.properties";



    /*public void initialize(String query){
        this.result = this.search(5,query).get(1);
    }*/

    public void initStage(Stage stage){
        this.stage=stage;
    }

    public Stage getStage(){
        return stage;
    }

    public void setPlayerModel(PlayerModel playerModel){
        this.playerModel=playerModel;
    }

    public PlayerModel getPlayerModel(){
        return this.playerModel;
    }

    public void setBackgroundModel(BackgroundModel backgroundModel){
        this.backgroundModel=backgroundModel;
    }

    public UploadModel getUploadModel(){
        return this.uploadModel;
    }

    public void setUploadModel(UploadModel uploadModel) {
        this.uploadModel=uploadModel;
    }

    public BackgroundModel getBackgroundModel(){
        return this.backgroundModel;
    }

    //Used for UploadView

    public void setVideoTitle(VideoSnippet snippet, String videoName){

        snippet.setDescription(videoName);
    }  //WOP
    public void setVideoThumbnail(VideoSnippet snippet, String img){

        //snippet.setDescription(img);
    } //WOP



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


    //returns true after sucessfully signing in, used for mainView

    public boolean signIn(){

        boolean signedIn=false;

        List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube.upload");

        try {

            Credential credential = Auth.authorize(scopes, "myprofile");
            signedIn=true;


        } catch (GoogleJsonResponseException e) {

            e.printStackTrace();
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());

        } catch (Throwable t) {

            t.printStackTrace();

        }
        return signedIn;
    }


    public List<SearchResult> search(long count, String query) {

        long NUMBER_OF_VIDEOS_RETURNED = count;

        /**
         * Define a global instance of a Youtube object, which will be used
         * to make YouTube Data API requests.
         */
        YouTube youtube;

        /**
         * Initialize a YouTube object to search for videos on YouTube. Then
         * display the name and thumbnail image of each video in the result set.
         *
         * @param args command line args.
         */

        // Read the developer key from the properties file.
        Properties properties = new Properties();
        try {
            InputStream in = YouTube.Search.class.getResourceAsStream("/" + PROPERTIES_FILENAME);
            properties.load(in);

        } catch (IOException e) {
            System.err.println("There was an error reading " + PROPERTIES_FILENAME + ": " + e.getCause()
                    + " : " + e.getMessage());
            System.exit(1);
        }

        try {
            // This object is used to make YouTube Data API requests. The last
            // argument is required, but since we don't need anything
            // initialized when the HttpRequest is initialized, we override
            // the interface and provide a no-op function.
            youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {
                }
            }).setApplicationName("youtube-cmdline-search-sample").build();

            // Prompt the user to enter a query term.
            String queryTerm = query;

            // Define the API request for retrieving search results.
            YouTube.Search.List search = youtube.search().list("id,snippet");

            // Set your developer key from the {{ Google Cloud Console }} for
            // non-authenticated requests. See:
            // {{ https://cloud.google.com/console }}
            String apiKey = properties.getProperty("youtube.apikey");
            search.setKey(apiKey);
            search.setQ(queryTerm);

            // Restrict the search results to only include videos. See:
            // https://developers.google.com/youtube/v3/docs/search/list#type
            search.setType("video");

            // To increase efficiency, only retrieve the fields that the
            // application uses.
            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);

            // Call the API and print results.
            SearchListResponse searchResponse = search.execute();
            List<SearchResult> searchResultList = searchResponse.getItems();
            if (searchResultList != null) {
                //prettyPrint(searchResultList.iterator(), queryTerm);

                return searchResultList;
            }
        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }


        return null;
    }



}
