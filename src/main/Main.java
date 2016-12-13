package main;

import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.SearchResultSnippet;
import com.google.api.services.youtubeAnalytics.model.Group;
import controller.BackgroundController;
import controller.PlayerViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import model.MainModel;
import model.PlayerModel;
import view.MainView;
import view.OldMainView;


import java.io.IOException;

public class Main extends Application {
    //private Scene scene;
    @Override public void start(Stage stage) throws IOException {



        /*view
        //create Desktop
        AnchorPane root = new AnchorPane();
        //Stage stage = new Stage();
        Scene mainView= new MainView(root,1280,800);*/


        AnchorPane root = new AnchorPane();

        FXMLLoader backgroundLoader = new FXMLLoader(getClass().getResource("../view/BackgroundView.fxml"));
        AnchorPane background = backgroundLoader.load();
        root.getChildren().add(background);
        root.setBottomAnchor(background,0.0);
        root.setTopAnchor(background,0.0);
        root.setLeftAnchor(background,0.0);
        root.setRightAnchor(background,0.0);
        root.autosize();
        BackgroundController backgroundController = backgroundLoader.getController();
        backgroundController.setRoot(root);
        MainModel model = new MainModel();
        backgroundController.initMainModel(model,background);


        FXMLLoader playerViewLoader = new FXMLLoader(getClass().getResource("../view/PlayerView.fxml"));
        AnchorPane playerView = playerViewLoader.load();
        background.getChildren().add(playerView);
        background.setBottomAnchor(playerView,100.0);
        background.setLeftAnchor(playerView,200.0);
        PlayerViewController playerViewController=playerViewLoader.getController();
        PlayerModel playerModel= new PlayerModel("_GuOjXYl5ew","TEST");

        playerViewController.initModel(playerModel);


        Scene scene = new Scene(root,1280,800);
        stage.setScene(scene);
        //stage.setScene(new OldMainView(root,1280,800));
        stage.show();





    }


    public static void main(String[] args) {
        launch(args);


    }
}
