package controller;

import view.MainView;
import model.MainModel;

public class Controller {

    private MainView mainView;
    private MainModel mainModel;


    /*public void setMainTitle(String title){
        this.videoID=title;
        mainModel.setVideoTitle(title);
    }*/

    public String getMainTitle(){
         return mainModel.getVideoTitle();
    }


    public void updateMainView() {
        //mainView.addPlayer(videoID);
      // mainView.setTitle(getMainTitle());
    }

}
