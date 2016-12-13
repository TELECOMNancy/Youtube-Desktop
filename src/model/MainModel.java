package model;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by tld on 12/12/2016.
 */

public class MainModel extends Model{

    private String mainVideoName;
    private static final String PROPERTIES_FILENAME = "youtube.properties";



    private static YouTube youtube;
    private static final String VIDEO_FILE_FORMAT = "video/*";
    private static final String SAMPLE_VIDEO_FILENAME = "sample-video.mp4";


    /*public void initialize(String query){
        this.result = this.search(5,query).get(1);
    }*/





    //Used for UploadView

    public void setVideoTitle(VideoSnippet snippet, String videoName){

        snippet.setDescription(videoName);
    }  //WOP
    public void setVideoThumbnail(VideoSnippet snippet, String img){

        //snippet.setDescription(img);
    } //WOP




    //search by keywords, used for videoListView
    @Override
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



    //returns true after sucessfully signing in, used for mainView

    public boolean signIn(){

        boolean signedIn=false;

        List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube.upload");

        try {

            Credential credential = Auth.authorize(scopes, "myuploads");
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



    //used for uploadView

    public void upload(){

        try {

            // step1: Add extra information to the video before uploading.

            // Set the video to be publicly visible. This is the default setting.
            // Other supporting settings are "unlisted" and "private."
            Video videoObjectDefiningMetadata = new Video();
            VideoStatus status = new VideoStatus();
            String stringStatus="";                                         //a choisir dans la vue
            status.setPrivacyStatus(stringStatus);
            videoObjectDefiningMetadata.setStatus(status);

            // Most of the video's metadata is set on the VideoSnippet object.
            VideoSnippet snippet = new VideoSnippet();
            //ADD SET METADATA FUNCTION HERE
            setVideoTitle(snippet, "TEXTFIELD LISTENER HERE");

            //setthumbnail ?

            videoObjectDefiningMetadata.setSnippet(snippet);



            // step2:Insert the video. The command sends three arguments.
            // The first specifies which information the API request is setting and which
            // information the API response should return. The second argument
            // is the video resource that contains metadata about the new video.
            // The third argument is the actual video content.


            InputStreamContent mediaContent = new InputStreamContent(VIDEO_FILE_FORMAT,
                    MainModel.class.getResourceAsStream("/sample-video.mp4")); //ADD VIDEO PATH TO FILE HERE


            YouTube.Videos.Insert videoInsert = youtube.videos()
                    .insert("snippet,statistics,status", videoObjectDefiningMetadata, mediaContent);

            // Set the upload type and add an event listener.
            MediaHttpUploader uploader = videoInsert.getMediaHttpUploader();

            // Indicate whether direct media upload is enabled. A value of
            // "True" indicates that direct media upload is enabled and that
            // the entire media content will be uploaded in a single request.
            // A value of "False," which is the default, indicates that the
            // request will use the resumable media upload protocol, which
            // supports the ability to resume an upload operation after a
            // network interruption or other transmission failure, saving
            // time and bandwidth in the event of network failures.
            uploader.setDirectUploadEnabled(false);

            //ADD PROGRESSLISTENER HERE ?

            // Call the API and upload the video.
            Video returnedVideo = videoInsert.execute();

        } catch (GoogleJsonResponseException e) {
        System.err.println("GoogleJsonResponseException code: " + e.getDetails().getCode() + " : "
                + e.getDetails().getMessage());
        e.printStackTrace();
        } catch (IOException e) {
        System.err.println("IOException: " + e.getMessage());
        e.printStackTrace();
        } catch (Throwable t) {
        System.err.println("Throwable: " + t.getMessage());
        t.printStackTrace();
        }

    }



}
