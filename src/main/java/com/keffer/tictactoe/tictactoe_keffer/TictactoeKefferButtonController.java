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

        if (this.btnCell.getUserData() == null) {

            CurrentTurn lastState = this.parentController.getCurrentTurn();
            this.btnCell.setUserData(lastState);

            if (!this.parentController.checkWinner(lastState, this.btnCell)) {
                switch (lastState) {
                    case NOUGHT:
                        this.parentController.setCurrentTurn(CurrentTurn.CIRCLE);
                        this.btnCell.setStyle("-fx-graphic:url(/asset/icomoon/nought.png);");
                        break;
                    case CIRCLE:
                        this.parentController.setCurrentTurn(CurrentTurn.NOUGHT);
                        this.btnCell.setStyle("-fx-graphic:url(/asset/icomoon/circle.png);");
                        break;
                }
            } else {
                System.out.println("win");

                switch (lastState) {
                    case NOUGHT:
                        this.btnCell.setStyle("-fx-graphic:url(/asset/icomoon/nought.png);");
                        break;
                    case CIRCLE:
                        this.btnCell.setStyle("-fx-graphic:url(/asset/icomoon/circle.png);");
                        break;
                }
            }
        }

    }

    //PUBLIC METHOD BELOW
    public void setParentController(TictactoeKefferController parentController) {
        this.parentController = parentController;
    }
    //-------------------

}
