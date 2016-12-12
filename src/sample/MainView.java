package sample;

import javafx.beans.NamedArg;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * Created by quentin on 10/12/2016.
 */
public class MainView extends Scene {


    public MainView(@NamedArg("root") Parent root, @NamedArg("width") double width, @NamedArg("height") double height) {
        super(root, width, height);
        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        webEngine.load("https://google.com");
    }

    //@Override
    //public void start(Stage stage) {

        /*// create the scene
        stage.setTitle("Web MainView");
        scene = new Scene(new Browser(),750,500, Color.web("#666970"));
        stage.setScene(scene);
        scene.getStylesheets().add("webviewsample/BrowserToolbar.css");
        stage.show();*/
   // }



}
