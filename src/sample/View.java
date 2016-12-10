package sample;

import javafx.beans.NamedArg;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Created by quentin on 10/12/2016.
 */
public class View extends Scene {


    public View(@NamedArg("root") Parent root, @NamedArg("width") double width, @NamedArg("height") double height) {
        super(root, width, height);
    }



}
