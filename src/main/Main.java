package main;

import javafx.application.Application;
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
        AnchorPane root2 = new AnchorPane();
        //MainView mainView= new MainView(root2,300,200);
        Stage stage1 = new Stage();
        //Parent mainView= new MainView(root2,1280,800);
        root2.autosize();
        stage1.setScene(new OldMainView(root2,1280,800));
        stage1.show();
    }


    public static void main(String[] args) {
        launch(args);


    }
}
