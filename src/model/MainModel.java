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
