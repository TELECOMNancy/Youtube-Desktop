package main;

import javafx.application.Application;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {
    private Scene scene;
    @Override public void start(Stage stage) {

        /*// create the scene
        stage.setTitle("Web View");
        scene = new Scene(new Browser(),750,500, Color.web("#666970"));
        stage.setScene(scene);
        scene.getStylesheets().add("webviewsample/BrowserToolbar.css");
        stage.show();*/

        WebView webview = new WebView();
        webview.getEngine().load(
                main.Main.class.getResource("/player.html").toExternalForm()
        );
        String test = "M7lc1UVf-VE";
        Player player = new Player("M7lc1UVf-VE");

        stage.setScene(new Scene(player));
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
