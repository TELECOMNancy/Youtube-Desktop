package model;

import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Video;
import javafx.scene.text.Text;
import view.Player;

import java.awt.*;

/**
 * Created by quentin on 13/12/2016.
 */
public class PlayerModel extends Model{
    private String title;
    private Player player;
    private Model model;

    public PlayerModel(SearchResult video){
        model=new Model();
        this.title= ""+model.getVideoTitle(video);
        this.player=new Player(model.getVideoID(video));
    }

    public PlayerModel(String videoid, String videotitle){
        model=new Model();
        this.title= videotitle;
        this.player=new Player(videoid);
    }


    public Player getPlayer(){
        return this.player;
    }
    public String getTitle() {return this.title;}

}
