package model;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * Created by quentin on 13/12/2016.
 */
public class BackgroundModel {

    private Node mainChildren;

    private MainModel mainModel;
    private AnchorPane root;
    private AnchorPane background;

    public BackgroundModel(MainModel mainModel){
        this.mainModel=mainModel;
    }

    public MainModel getMainModel(){
        return this.mainModel;
    }

    public Node getMainChildren(){
        return this.mainChildren;
    }

    public void setMainChildren(Node children){
        this.mainChildren=children;
    }

    public AnchorPane getBackground(){
        return this.background;
    }

    public void setBackground(AnchorPane background){
        this.background=background;
    }





}
