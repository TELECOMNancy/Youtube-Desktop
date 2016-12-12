package main;

public class Controller {

    private MainView mainView;
    private MainModel mainModel;

    String videoID = "IddapcoJokTfk";

    public void setMainVideoName(String videoName){
        mainModel.setMainVideoName(videoName);
    }

    //public String getVideoTitle(){
      //  mainModel.getVideoTitle();
    //}


    public void updateMainView() {
        //mainView.addPlayer(videoID);
       // mainView.setTitle(getVideoTitle());
    }

}
