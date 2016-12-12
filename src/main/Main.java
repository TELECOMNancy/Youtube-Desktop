package main;

import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;


public class Main extends Application {
    private Scene scene;
    @Override public void start(Stage stage) throws IOException {

        // create the scene

        stage.setTitle("Web View");
        scene = new Scene(new AuthenticateView("https://accounts.google.com/o/oauth2/auth?client_id=204695483539-g0pk6ikrf9o6n702rd8ndfq2t8ta859d.apps.googleusercontent.com&redirect_uri=http%3A%2F%2Flocalhost%2Foauth2callback&scope=https://www.googleapis.com/auth/youtube&response_type=code&access_type=offline"),750,500, Color.web("#666970"));
        LocalServerReceiver localReceiver = new LocalServerReceiver.Builder().setPort(8080).build();

        stage.setScene(scene);
        scene.getStylesheets().add("webviewsample/BrowserToolbar.css");
        stage.show();


        /*WebView webview = new WebView();
        webview.getEngine().load(
                Main.class.getResource("/player.html").toExternalForm()
        );
        stage.setScene(new Scene(webview));
        stage.show();*/
    }


    public static void main(String[] args) {
        launch(args);


    }
}
