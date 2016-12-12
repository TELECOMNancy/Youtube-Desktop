package main;

public class Controller {

    private MainView mainView;
    private MainModel mainModel;

    String videoID = "IddapcoJokTfk";

    /*public void setMainTitle(String title){
        this.videoID=title;
        mainModel.setVideoTitle(title);
    }*/

    public String getMainTitle(){
         return mainModel.getVideoTitle();
    }



    public void updateMainView() {
        //mainView.addPlayer(videoID);
       mainView.setTitle(getMainTitle());
    }

}
