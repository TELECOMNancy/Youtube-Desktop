package main;

public class Controller {

    private View mainView;
    private MainModel mainModel;

    public void setMainVideoName(String videoName){
        mainModel.setMainVideoName(videoName);
    }

    public String getMainVideoName(){
        mainModel.getMainVideoName();
    }


    public void updateMainView() {
        View.addPlayer(String videoID);
        View.setTitle(String getMainVideoName());
    }

}
