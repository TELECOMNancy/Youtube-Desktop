package controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by tld on 15/12/2016.
 */
public class PopUpController {
    private Stage popUpStage;

    @FXML
    private JFXButton buttonPopUp;

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

}
