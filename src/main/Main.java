package main;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import view.MainView;
import view.OldMainView;

import java.io.IOException;

public class Main extends Application {
    private Scene scene;
    @Override public void start(Stage stage) throws IOException {


        //create Desktop
        AnchorPane root = new AnchorPane();
        //Stage stage = new Stage();
        Scene mainView= new MainView(root,1280,800);
        stage.setScene(mainView);
        root.autosize();
        //stage.setScene(new OldMainView(root,1280,800));
        stage.show();



    }


    public static void main(String[] args) {
        launch(args);


    }
}
