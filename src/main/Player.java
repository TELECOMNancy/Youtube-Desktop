package main;


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

    final WebView videoPlayer = new WebView();

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


        //htmlString = FileUtils.readFileToString(htmlTemplateFile, (Charset) null);
        htmlStringFinal = htmlString.replace("$videoId", videoId);

        this.videoPlayer.getEngine().loadContent(
                htmlStringFinal
        );

        /*En faire un model, vue?*/
        videoPlayer.getEngine().locationProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> prop, final String before, final String after) {
                System.out.println("Loaded: " + after);
                Platform.runLater(new Runnable() {
                    public void run() {
                        if (after == null || !after.startsWith("http://docs.oracle.com/javafx/2/get_started")) {
                            System.out.println("Access denied: " + after);
                            videoPlayer.getEngine().loadContent(htmlStringFinal);
                        }
                    }
                });
            }
        });




        this.getChildren().add(videoPlayer);

    }

    @Override protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(videoPlayer,400,200,660,380,0, HPos.CENTER, VPos.CENTER);
    }

    @Override protected double computePrefWidth(double height) {
        return 750;
    }

    @Override protected double computePrefHeight(double width) {
        return 500;
    }
}
