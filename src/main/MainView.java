package main;

import com.sun.org.apache.regexp.internal.RE;
import javafx.beans.NamedArg;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
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
        // create the Desktop
        Stage stage= new Stage();
        stage.setTitle("Youtube Desktop");
        //Création du rectangle rouge
        Rectangle rectangleRed= new Rectangle();
        rectangleRed.setTranslateX(0);
        rectangleRed.setTranslateX(0);
        rectangleRed.setWidth(250);
        rectangleRed.setHeight(height);
        rectangleRed.setFill(Color.RED);
        Group group= new Group();
        group= (Group)root;
        group.getChildren().add(rectangleRed);
        //Création bouton home
        Bouton home= new Bouton("Home",20,40,210,50,Color.WHITE);
        group.getChildren().add(home);
        //Création bouton My Videos
        Bouton myVideos= new Bouton("My Videos",20,110,210,50,Color.WHITE);
        group.getChildren().add(myVideos);
        //Création bouton Connection
        Bouton connection= new Bouton("Connection",1100,40,210,50,Color.WHITE);
        group.getChildren().add(connection);
        //intégration player


    }




}
