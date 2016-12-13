package main;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Created by léo on 12/12/2016.
 */
public class TitleCase extends Parent {

    public TitleCase(String l, int posX, int posY){
        Text titlecase=new Text(l);
        titlecase.setFont(new Font(22));
        titlecase.setFill(Color.BLACK);
        titlecase.setX(10);
        titlecase.setY(25);
        this.getChildren().add(titlecase);
        this.setTranslateX(posX);
        this.setTranslateY(posY);

    }
}
