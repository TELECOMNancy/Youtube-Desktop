package main;

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
import java.io.IOException;


public class Main extends Application {

    @Override public void start(Stage stage) throws IOException {

        MainModel mainModel = new MainModel();

        AnchorPane root = new AnchorPane();
        mainModel.initStage(stage);

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
        background.setBottomAnchor(playerView,30.0);
        background.setTopAnchor(playerView,150.0);
        background.setLeftAnchor(playerView,300.0);
        background.setRightAnchor(playerView,50.0);
        PlayerModel playerModel= new PlayerModel("_GuOjXYl5ew","YouTube Rewind: The Ultimate 2016 Challenge | #YouTubeRewind","YouTube Spotlight","UCBR8-60-B28hp2BmDPdntcQ",mainModel);
        mainModel.setPlayerModel(playerModel);
        PlayerViewController playerViewController=playerViewLoader.getController();
        playerViewController.initPlayerModel(playerModel);

        Scene scene = new Scene(root,1280,800);
        stage.setTitle("Youtube Desktop");
        stage.setScene(scene);
        //stage.setScene(new OldMainView(root,1280,800));
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
