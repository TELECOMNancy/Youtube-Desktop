package model;

import com.google.api.services.youtube.model.SearchResult;
import javafx.scene.text.Text;
import view.Player;

import java.awt.*;

/**
 * Created by quentin on 13/12/2016.
 */
public class PlayerModel extends Model{
    private Text title;
    private Player player;
    private Model model;

    public PlayerModel(SearchResult video){
        model=new Model();
        this.title=new Text(""+model.getVideoTitle(video));
        this.player=new Player(""+model.getVideoID(video));
    }

    public Player getPlayer(){
        return this.player;
    }

}
