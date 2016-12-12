package main;

import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;

/*
 * Created by M.ESCAMEZ on 12/12/2016.
 */

public class AuthenticateView extends Region {


    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();

    public AuthenticateView(String string) {
        //apply the styles
        getStyleClass().add("browser");
        // load the web page
        webEngine.load(string);
        //add the web view to the scene
        getChildren().add(browser);

    }

}
