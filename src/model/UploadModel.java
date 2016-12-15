package model;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoSnippet;
import com.google.api.services.youtube.model.VideoStatus;
import com.google.common.collect.Lists;
import main.Main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by M.ESCAMEZ on 14/12/2016.
 */

public class UploadModel {
    private MainModel mainModel;

    public UploadModel(MainModel mainModel){
        this.mainModel=mainModel;
    }


    private static YouTube youtube;

    //defines the MIME type. Here: video type (mpeg, mp4, quicktime, x-ms-wmv, x-msvideo, x-flv, webm)
    private static final String VIDEO_FILE_FORMAT = "video/*";

    public UploadModel(){
        //this.mainModel=mainModel;
    }

    public MainModel getMainModel(){
        return this.mainModel;
    }


    public void upload(String videoTitle, String pathToFile, String videoDesc, String videoStatus){

        try {


            List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube.upload","https://www.googleapis.com/auth/youtube.readonly","https://www.googleapis.com/auth/userinfo.profile");
            Credential credential = Auth.authorize(scopes, "myprofile");

           // FileDataStoreFactory.load(String userId, Credential credential);
            youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, credential).setApplicationName(
                        "youtube-uploadvideo").build();




            // step1: Add extra information to the video before uploading.
            // Set the video to be publicly visible. This is the default setting.
            // Other supporting settings are "unlisted" and "private."
            Video videoObjectDefiningMetadata = new Video();
            VideoStatus status = new VideoStatus();
            status.setPrivacyStatus(videoStatus);
            videoObjectDefiningMetadata.setStatus(status);

            // Most of the video's metadata is set on the VideoSnippet object.
            VideoSnippet snippet = new VideoSnippet();

            snippet.setTitle(videoTitle);
            snippet.setDescription(videoDesc);

            videoObjectDefiningMetadata.setSnippet(snippet);



            // step2: Insert the video. The command sends three arguments.
            // The first specifies which information the API request is setting and which
            // information the API response should return. The second argument
            // is the video resource that contains metadata about the new video.
            // The third argument is the actual video content.


            InputStreamContent mediaContent = new InputStreamContent(VIDEO_FILE_FORMAT,
                    new FileInputStream(pathToFile));


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
