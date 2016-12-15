package model;


import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;


/**
 * Created by quentin on 13/12/2016.
 */
public class BackgroundModel {

    private Node mainChildren;
    private MainModel mainModel;
    private AnchorPane background;


    public BackgroundModel(MainModel mainModel){
        this.mainModel=mainModel;
    }

    public MainModel getMainModel(){
        return this.mainModel;
    }

    public Node getMainChildren(){
        return this.mainChildren;
    }

    public void setMainChildren(Node children){
        this.mainChildren=children;
    }

    public AnchorPane getBackground(){
        return this.background;
    }

    public void setBackground(AnchorPane background){
        this.background=background;
    }

}
