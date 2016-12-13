package main;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * Created by léo on 12/12/2016.
 */
public class LateralRed extends VBox {
    public LateralRed (double width, double heigth) {

        this.setPrefHeight(heigth);
        this.setPrefWidth(width/8);
        double h=this.getPrefHeight();
        double w=this.getPrefWidth();

        //Création bouton home
        //Bouton home = new Bouton("Home", 20, 40, 210, 50, Color.WHITE);

        //Création bouton My Videos
        //Bouton myVideos = new Bouton("My Videos", 20, 110, 210, 50, Color.WHITE);

        Button home=new Button("Home");
        Button myVideos=new Button("My videos");

        //Fond rouge
        this.setBackground((new Background(new BackgroundFill(Color.RED,CornerRadii.EMPTY, Insets.EMPTY))));
        this.setSpacing(20);
        this.setPadding(new Insets(h/8,w/8,h/2,w/8));
        this.getChildren().addAll(myVideos,home);

       /* Button home=new Button("Myvideo");
        Button home1=new Button("Myde");
        this.setPrefHeight(BASELINE_OFFSET_SAME_AS_HEIGHT);
        System.out.println(this.getHeight());
        this.setHeight(800);
        this.setWidth(250);
        this.setSpacing(10);
        this.setBackground((new Background(new BackgroundFill(Color.RED,CornerRadii.EMPTY, Insets.EMPTY))));
        this.getChildren().add(home);
        this.getChildren().add(home1);
        this.setSpacing(30);
        this.setPadding(new Insets(50,50,500,50));
*/



       /* //this.setPadding(new Insets(15,12,15,12));
        //this.setSpacing(10);
        //this.setWidth(250);
        //this.setFillHeight(true);
        //this.fillHeightProperty();
        //this.setHeight(this.maxHeight(250));
        Rectangle rectangleRed= new Rectangle();
        rectangleRed.setLayoutX(0);
        rectangleRed.setLayoutY(0);
        //System.out.println(this.getWidth());
        //System.out.println(this.getHeight());
        rectangleRed.setWidth(250);
        rectangleRed.setHeight(800);
        rectangleRed.setFill(Color.RED);

        //Création bouton home
        Bouton home = new Bouton("Home", 20, 40, 210, 50, Color.WHITE);

        //this.getChildren().add(home);

        //Création bouton My Videos
        Bouton myVideos = new Bouton("My Videos", 20, 110, 210, 50, Color.WHITE);


        this.getChildren().addAll(rectangleRedhome,myVideos);
        this.layoutChildren();

    */}

}
