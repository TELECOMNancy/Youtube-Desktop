package controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Created by tld on 15/12/2016.
 */

public class PopUpController {
    private Stage popUpStage;
    @FXML
    private JFXButton buttonPopUp;

    @FXML
    private ImageView imageCredit;


    @FXML
    void closePopUp(){
        popUpStage=(Stage)buttonPopUp.getScene().getWindow();
        popUpStage.close();
    }

    public void initPopUp(Parent popUpView){
        popUpStage = new Stage();
        popUpStage.setScene(new Scene(popUpView));
        popUpStage.setTitle("Error invalid fields");

        popUpStage.showAndWait();
    }

    public void initCredits(Parent popUpView){
        popUpStage = new Stage();
        popUpStage.setScene(new Scene(popUpView));
        popUpStage.setTitle("Crédits");
        imageCredit.setImage();
        popUpStage.showAndWait();
    }

}
