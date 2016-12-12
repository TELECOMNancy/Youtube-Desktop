package main;


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
import java.nio.charset.Charset;


/**
 * Created by quentin on 12/12/2016.
 */
public class Player extends Region {

    final WebView videoPlayer = new WebView();
    final WebEngine webEngine = videoPlayer.getEngine();


    public Player(String videoId) {

        getStyleClass().add("browser");
        File htmlTemplateFile = new File("./resources/template.html");
        try {
            String htmlString = FileUtils.readFileToString(htmlTemplateFile, (Charset) null);
            htmlString = htmlString.replace("$videoId", videoId);
            File newHtmlFile = new File("./resources/player.html");
            FileUtils.writeStringToFile(newHtmlFile, htmlString, (Charset) null);
        }
        catch (java.io.IOException e) {
            System.out.println(e.getMessage());
        }
        this.videoPlayer.getEngine().load(
                main.Main.class.getResource("/player.html").toExternalForm()
        );
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
        layoutInArea(videoPlayer,400,200,700,400,0, HPos.CENTER, VPos.CENTER);
    }

    @Override protected double computePrefWidth(double height) {
        return 750;
    }

    @Override protected double computePrefHeight(double width) {
        return 500;
    }
}
