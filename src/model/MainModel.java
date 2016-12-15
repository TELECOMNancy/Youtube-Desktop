package model;


import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStore;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;
import java.io.*;
import java.util.Properties;
import com.google.common.collect.Lists;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import java.util.List;

/**
 * Created by tld on 12/12/2016.
 */

public class MainModel {

    private PlayerModel playerModel;
    private BackgroundModel backgroundModel;
    private UploadModel uploadModel;
    private Stage stage;
    public static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    public static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private static final String CREDENTIALS_DIRECTORY = ".oauth-credentials";
    private static final String PROPERTIES_FILENAME = "youtube.properties";


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


    public void signIn(){

        List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube.upload","https://www.googleapis.com/auth/youtube.readonly");

            try {

               Credential credential = this.authorize(scopes, "myprofile");

            } catch (GoogleJsonResponseException e) {

                e.printStackTrace();
                System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                        + e.getDetails().getMessage());

            } catch (Throwable t) {

                t.printStackTrace();

            }
        }


    public void signOut() {


        try {
            FileUtils.deleteDirectory(new File(System.getProperty("user.home") + "/" + ".oauth-credentials"));


        }
        catch (IOException e) {
            e.printStackTrace();
        }

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
            youtube = new YouTube.Builder(this.HTTP_TRANSPORT, this.JSON_FACTORY, new HttpRequestInitializer() {
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
            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url,snippet/channelId,snippet/channelTitle)");
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


    public String getVideoTitle(SearchResult video) {

        return video.getSnippet().getTitle();
    }


    public String getChannelId(SearchResult video){
        return video.getSnippet().getChannelId();
    }


    public String getChannelTitle(SearchResult video){
        return video.getSnippet().getChannelTitle();
    }


    public String getVideoID(SearchResult video){
        String rId = video.getId().getVideoId();
        return rId;
    }


    public String getVideoThumbnail(SearchResult video){
        Thumbnail thumbnail = video.getSnippet().getThumbnails().getDefault();
        return thumbnail.getUrl();
    }


    public static Credential authorize(List<String> scopes, String credentialDatastore) throws IOException {

        // Load client secrets.
        Reader clientSecretReader = new InputStreamReader(MainModel.class.getResourceAsStream("/client_secrets.json"));
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, clientSecretReader);

        // Checks that the defaults have been replaced (Default = "Enter X here").
        if (clientSecrets.getDetails().getClientId().startsWith("Enter")
                || clientSecrets.getDetails().getClientSecret().startsWith("Enter ")) {
            System.out.println(
                    "Enter Client ID and Secret from https://console.developers.google.com/project/_/apiui/credential "
                            + "into src/main/resources/client_secrets.json");
            System.exit(1);
        }

        // This creates the credentials datastore at ~/.oauth-credentials/${credentialDatastore}
        FileDataStoreFactory fileDataStoreFactory = new FileDataStoreFactory(new File(System.getProperty("user.home") + "/" + CREDENTIALS_DIRECTORY));
        DataStore<StoredCredential> datastore = fileDataStoreFactory.getDataStore(credentialDatastore);



        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, scopes).setCredentialDataStore(datastore)
                .build();

        // Build the local server and bind it to port 8080

        LocalServerReceiver localReceiver = new LocalServerReceiver.Builder().setPort(8080).build();

        // Getting the redirect URI

        //String redirectUri = localReceiver.getRedirectUri();

        //System.out.println("credential(token ? ) : "+datastore.get(credentialDatastore).getAccessToken());
        // Authorize.
        return new AuthorizationCodeInstalledApp(flow, localReceiver).authorize("user");
    }

}
