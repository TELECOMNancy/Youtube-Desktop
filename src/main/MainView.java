package main;

//import com.sun.org.apache.xpath.internal.operations.String;
import javafx.beans.NamedArg;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

import java.io.IOException;
import java.net.URL;

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


        Player player= new Player("_GuOjXYl5ew");
        root.getChildren().add(player);

        //Browser brow = new Browser();
        //group.getChildren().add(brow);

    }

    @FXML
    void switchToPlayer(ActionEvent event, AnchorPane root, String videoid) {
        try {
            AnchorPane centerPlayer = FXMLLoader.load(getClass().getResource("PlayerAnchor.fxml"));
            root.setBottomAnchor(centerPlayer,0.0);
            Player player= new Player(videoid);
            centerPlayer.getChildren().add(player);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



   /* public Player getPlayer(){
        return this.player;
    }
    public void addPlayer(){
        group.getChildren().add(player);
    }
    public void removePlayer(){

    }*/



}
