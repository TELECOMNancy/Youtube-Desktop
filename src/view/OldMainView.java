package view;

import javafx.beans.NamedArg;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.TextFlow;
import main.TitleCase;

import java.io.IOException;

/**
 * Created by quentin on 10/12/2016.
 */
public class OldMainView extends Scene {
    // private Player player;
    private Group group;
    //private String title;
    private TitleCase titleCase;
    private Region window;
    private double width;
    private double heigth;
    private TextFlow title;


    public OldMainView(@NamedArg("root") AnchorPane root, @NamedArg("width") final double width, @NamedArg("height") final double height) throws IOException {
        super(root, width, height);
        this.heigth=height;
        this.width=width;

        /*AnchorPane background = FXMLLoader.load(getClass().getResource("../view/BackgroundView.fxml"));
        root.getChildren().add(background);
        root.setBottomAnchor(background,0.0);
        root.setTopAnchor(background,0.0);
        root.setLeftAnchor(background,0.0);
        root.setRightAnchor(background,0.0);*/




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
        //l="l";
        TitleCase titlecase=new TitleCase(l,10,10);
        group.getChildren().add(titlecase);*/
        Player player= new Player("_GuOjXYl5ew");
        root.getChildren().add(player);


    }

    @FXML
    void switchToPlayer(ActionEvent event, AnchorPane root, String videoid) {
        try {
            AnchorPane centerPlayer = FXMLLoader.load(getClass().getResource("PlayerView.fxml"));
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
