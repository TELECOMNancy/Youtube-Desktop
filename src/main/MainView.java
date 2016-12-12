package main;

import com.sun.org.apache.regexp.internal.RE;
import com.sun.org.apache.xpath.internal.operations.Plus;
import com.sun.org.apache.xpath.internal.operations.String;
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
   // private Player player;
    private Group group;
    private String title;


    public MainView(@NamedArg("root") Parent root, @NamedArg("width") double width, @NamedArg("height") double height) {
        super(root, width, height);

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

        //Group group= new Group();
        group = (Group)root;
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
        group.autoSizeChildrenProperty();
        /*String l= new String();
        l="l";
        TitleCase titlecase=new TitleCase(l,10,10);
        group.getChildren().add(titlecase);*/
        Player player= new Player("M7lc1UVf-VE");
        group.getChildren().add(player);

        //Browser brow = new Browser();
        //group.getChildren().add(brow);

    }
   /* public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title=title;
        updateTitle();
    }
    public void updateTitle(){
        String title2=this.title;
    }*/
   /* public Player getPlayer(){
        return this.player;
    }
    public void addPlayer(){
        group.getChildren().add(player);
    }
    public void removePlayer(){

    }*/



}
