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
import model.BackgroundModel;
import model.MainModel;
import model.PlayerModel;
import view.MainView;



import java.io.IOException;

public class Main extends Application {

    //private Scene scene;
    @Override public void start(Stage stage) throws IOException {



        /*view
        //create Desktop
        AnchorPane root = new AnchorPane();
        //Stage stage = new Stage();
        Scene mainView= new MainView(root,1280,800);*/

        MainModel mainModel = new MainModel();


        AnchorPane root = new AnchorPane();


        FXMLLoader backgroundLoader = new FXMLLoader(getClass().getResource("/view/BackgroundView.fxml"));
        AnchorPane background = backgroundLoader.load();
        root.getChildren().add(background);
        root.setBottomAnchor(background,0.0);
        root.setTopAnchor(background,0.0);
        root.setLeftAnchor(background,0.0);
        root.setRightAnchor(background,0.0);
        //root.autosize();
        BackgroundModel backgroundModel = new BackgroundModel(mainModel);
        backgroundModel.setBackground(background);
        mainModel.setBackgroundModel(backgroundModel);
        BackgroundController backgroundController = backgroundLoader.getController();
        backgroundController.initBackgroundController(backgroundModel,background);






        FXMLLoader playerViewLoader = new FXMLLoader(getClass().getResource("/view/PlayerView.fxml"));
        AnchorPane playerView = playerViewLoader.load();
        background.getChildren().add(playerView);
        backgroundModel.setMainChildren(playerView);
        background.setBottomAnchor(playerView,100.0);
        background.setTopAnchor(playerView,100.0);
        background.setLeftAnchor(playerView,200.0);
        //background.setRightAnchor(playerView,100.0);
        PlayerModel playerModel= new PlayerModel("_GuOjXYl5ew","Youtube Rewind 2016",mainModel);
        mainModel.setPlayerModel(playerModel);
        PlayerViewController playerViewController=playerViewLoader.getController();
        playerViewController.initPlayerModel(playerModel);







        Scene scene = new Scene(root,1280,800);
        stage.setScene(scene);
        //stage.setScene(new OldMainView(root,1280,800));
        stage.show();





    }


    public static void main(String[] args) {
        launch(args);


    }
}
