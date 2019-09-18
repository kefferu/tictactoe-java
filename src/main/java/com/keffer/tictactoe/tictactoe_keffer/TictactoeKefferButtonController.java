package com.keffer.tictactoe.tictactoe_keffer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TictactoeKefferButtonController {

    TictactoeKefferController parentController;

    @FXML
    private Button btnCell;

    //PUBLIC METHOD BELOW
    public void setParentController(TictactoeKefferController parentController) {
        this.parentController = parentController;
    }
    //-------------------

}
