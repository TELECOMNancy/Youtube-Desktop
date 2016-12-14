package view;


import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.Region;
import javafx.scene.web.WebView;
import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.io.InputStream;



/**
 * Created by quentin on 12/12/2016.
 */
public class Player extends Region {

    WebView videoPlayer = new WebView();

    public Player(String videoId) {
        String htmlString;

        final String htmlStringFinal;
        getStyleClass().add("browser");
        InputStream htmlTemplateFile =getClass().getClassLoader().getResourceAsStream("template.html");
        try {
            htmlString = IOUtils.toString(htmlTemplateFile, "UTF-8");
        }
        catch (IOException e) {
            htmlString = "";
        }

        htmlStringFinal = htmlString.replace("$videoId", videoId);

        this.videoPlayer.getEngine().loadContent(
                htmlStringFinal
        );

        //Prevent from quitting the player
        videoPlayer.getEngine().locationProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> prop, final String before, final String after) {
                //System.out.println("Loaded: " + after);
                Platform.runLater(new Runnable() {
                    public void run() {
                        if (after == null || !after.startsWith("http://docs.oracle.com/javafx/2/get_started")) {
                            //System.out.println("Access denied: " + after);
                            videoPlayer.getEngine().loadContent(htmlStringFinal);
                        }
                    }
                });
            }
        });

        this.getVideoPlayer().setMinWidth(1000);
        this.getChildren().add(videoPlayer);

    }

    public WebView getVideoPlayer(){
        return this.videoPlayer;
    }


    @Override protected double computePrefWidth(double height) {
        return 1000;
    }

    @Override protected double computePrefHeight(double width) {
        return 600;
    }

}
