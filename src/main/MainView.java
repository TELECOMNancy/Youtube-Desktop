package main;

//import com.sun.org.apache.xpath.internal.operations.String;
import javafx.beans.NamedArg;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * Created by quentin on 10/12/2016.
 */
public class MainView extends Scene {
   // private Player player;
    private Group group;
    //private String title;
    private TitleCase titleCase;
    private Region window;
    private double width;
    private double heigth;
    private TextFlow title;


    public MainView(@NamedArg("root") AnchorPane root, @NamedArg("width") final double width, @NamedArg("height") final double height) {
        super(root, width, height);
        this.heigth=height;
        this.width=width;




        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        webEngine.load("https://google.com");


;
        VBox lateral=new LateralRed(width,height);
        root.getChildren().add(lateral);
        root.setBottomAnchor(lateral,0.0);
        root.setTopAnchor(lateral,0.0);
        //Création bouton Connection
        Button signin= new Button("Sign in");
        root.setRightAnchor(signin,20.0);
        root.setTopAnchor(signin,20.0);

        root.getChildren().add(signin);

        root.autosize();
        //intégration player
        /*String l= new String();
        l="l";
        TitleCase titlecase=new TitleCase(l,10,10);
        group.getChildren().add(titlecase);*/
        Player player= new Player("_GuOjXYl5ew");
        root.getChildren().add(player);

        //Browser brow = new Browser();
        //group.getChildren().add(brow);

    }
    /*public String getTitle(){
        return this.title;
    }
    public void setTitle(String title1){

        Text text= new Text(title1);
        this.title= new TextFlow(text);
        .getChildren().add(title);
    }*/
    /*public void updateTitle(){
        TitleCase titlecase=new TitleCase(this.title,10,10);
        group.getChildren().add(titlecase);
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
