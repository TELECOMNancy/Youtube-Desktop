package main;

import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import main.MainView;

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
        stage1.setScene(new MainView(root2,1280,800));
        stage1.show();
    }


    public static void main(String[] args) {
        launch(args);


    }
}
