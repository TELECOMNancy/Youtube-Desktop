package main;


import javafx.scene.Parent;
import javafx.scene.web.WebView;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.nio.charset.Charset;


/**
 * Created by quentin on 12/12/2016.
 */
public class Player extends Parent {

protected WebView videoPlayer;


    public Player(String videoId) {
        this.videoPlayer = new WebView();
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


}
