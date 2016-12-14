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


    //returns true after successfully signing in, used for mainView

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






}
