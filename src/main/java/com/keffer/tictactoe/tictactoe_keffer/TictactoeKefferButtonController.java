package com.keffer.tictactoe.tictactoe_keffer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TictactoeKefferButtonController {

    TictactoeKefferController parentController;

    @FXML
    private Button btnCell;

    @FXML
    private void initialize() {

    }

    @FXML
    void onBtnCellClick(ActionEvent event) {
        CurrentTurn currentTurn = this.parentController.getCurrentTurn();

        this.btnCell.setUserData(currentTurn);

        switch (currentTurn) {
            case NOUGHT:
                this.parentController.setCurrentTurn(CurrentTurn.CIRCLE);
                this.btnCell.setStyle("-fx-graphic:url(/asset/icomoon/nought.png);");
                break;
            case CIRCLE:
                this.parentController.setCurrentTurn(CurrentTurn.NOUGHT);
                this.btnCell.setStyle("-fx-graphic:url(/asset/icomoon/circle.png);");
                break;
        }
    }

    //PUBLIC METHOD BELOW
    public void setParentController(TictactoeKefferController parentController) {
        this.parentController = parentController;
    }
    //-------------------

}
