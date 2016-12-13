package main;

import javafx.application.Application;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import view.MainView;
import view.OldMainView;
import view.TestView;

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
        backgroundController.initMainModel(model);


        Scene scene = new Scene(root,1280,800);
        stage.setScene(scene);
        //stage.setScene(new OldMainView(root,1280,800));
        stage.show();





    }


    public static void main(String[] args) {
        launch(args);


    }
}
