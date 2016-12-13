package controller;

import com.google.api.services.youtube.model.SearchResult;
import view.MainView;
import model.MainModel;



public class Controller {

    private MainView mainView;
    private MainModel mainModel;
    SearchResult result;

    /*public void setMainTitle(String title){
        this.videoID=title;
        mainModel.setVideoTitle(title);
    }*/

    public String getMainTitle(){
         return mainModel.getVideoTitle(result);
    }


    public void updateMainView() {
        //mainView.addPlayer(videoID);
      // mainView.setTitle(getMainTitle());
    }

}
