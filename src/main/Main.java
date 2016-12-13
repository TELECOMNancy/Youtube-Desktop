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
    private Scene scene;
    @Override public void start(Stage stage) throws IOException {


        //create Desktop
        AnchorPane root = new AnchorPane();
        //Parent mainView= new MainView(root,1280,800);
        root.autosize();
        stage.setScene(new TestView(root,1280,800));
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);


    }
}
