package main;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Created by léo on 12/12/2016.
 */
public class Bouton extends Parent {
    public Bouton(String l, double posX, double posY, double width, double height, Color color) {
        Rectangle home = new Rectangle();
        Text texthome= new Text(l);
        home.setWidth(width);
        home.setHeight(height);
        home.setFill(color);
        texthome.setFont(new Font(2*height/3));
        texthome.setFill(Color.BLACK);
        texthome.setX(10);
        texthome.setY(5);
        this.getChildren().add(home);
        this.getChildren().add(texthome);
        this.setTranslateX(posX);
        this.setTranslateY(posY);
    }
}
