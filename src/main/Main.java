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
    @Override public void start(Stage stage) {

        // create the scene

        stage.setTitle("Web View");
        scene = new Scene(new AuthenticateView("https://accounts.google.com/o/oauth2/auth?client_id=204695483539-g0pk6ikrf9o6n702rd8ndfq2t8ta859d.apps.googleusercontent.com&redirect_uri=http%3A%2F%2Flocalhost%2Foauth2callback&scope=https://www.googleapis.com/auth/youtube&response_type=code&access_type=offline"),750,500, Color.web("#666970"));
        LocalServerReceiver localReceiver = new LocalServerReceiver.Builder().setPort(8080).build();

        stage.setScene(scene);
        stage.show();

        WebView webview = new WebView();
        webview.getEngine().load(
                main.Main.class.getResource("/player.html").toExternalForm()
        );
        String test = "M7lc1UVf-VE";
        Player player = new Player("M7lc1UVf-VE");

        //stage.setScene(new Scene(player));
        //stage.show();

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
