package view;

import javafx.beans.NamedArg;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.TextFlow;
import main.TitleCase;

import java.io.IOException;

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


    public MainView(@NamedArg("root") AnchorPane root, @NamedArg("width") final double width, @NamedArg("height") final double height) throws IOException {
        super(root, width, height);
        this.heigth=height;
        this.width=width;

        AnchorPane background = FXMLLoader.load(getClass().getResource("../view/BackgroundView.fxml"));
        root.getChildren().add(background);
        root.setBottomAnchor(background,0.0);
        root.setTopAnchor(background,0.0);
        root.setLeftAnchor(background,0.0);
        root.setRightAnchor(background,0.0);
        root.autosize();

        //AnchorPane player = FXMLLoader.load(getClass().getResource("../view/PlayerAnchor.fxml"));
        //background.setBottomAnchor(player,0.0);




    }




}
