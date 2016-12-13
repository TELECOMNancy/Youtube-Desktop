package main;


import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;


/**
 * Created by quentin on 12/12/2016.
 */
public class Player extends Region {

    final WebView videoPlayer = new WebView();
    final WebEngine webEngine = videoPlayer.getEngine();


    public Player(String videoId) {
        String htmlString;
        final String htmlStringFinal;
        getStyleClass().add("browser");
        File htmlTemplateFile;
        URL resource = getClass().getClassLoader().getResource("template.html");
        try {
            String filePath = URLDecoder.decode(resource.getFile(), "UTF-8");
            htmlTemplateFile = new File(filePath);
        }
        catch (UnsupportedEncodingException e) {
            htmlTemplateFile = new File(resource.getFile());
        }

        try {
            htmlString = FileUtils.readFileToString(htmlTemplateFile, (Charset) null);
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
        }

        catch (java.io.IOException e) {
            System.out.println(e.getMessage());
        }



        this.getChildren().add(videoPlayer);

    }

    private Node createSpacer() {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
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
