package main;

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

public class Main extends Application {
    private Scene scene;
    @Override public void start(Stage stage) {

        /*// create the scene
        stage.setTitle("Web MainView");
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
