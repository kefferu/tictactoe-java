package com.keffer.tictactoe.tictactoe_keffer;

import com.keffer.tictactoe.component.CustomErrorDialog;
import com.keffer.tictactoe.main.MainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class TictactoeKefferController {

    private final String TICTACTOE_KEFFER_CELL_FXML_PATH = "/static/tictactoe-keffer/tictactoe-keffer-btn-cell.fxml";

    private MainController parentController;

    @FXML
    private GridPane gridPaneBoard;

    //PUBLIC METHOD BELOW
    public void setupGame(int scale, VsMode vsMode) {
        VBox.setVgrow(this.gridPaneBoard, Priority.ALWAYS);

        try {

            for (int row = 0 ; row < scale ; row++) {
                for (int col = 0 ; col < scale ; col++) {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource(TICTACTOE_KEFFER_CELL_FXML_PATH));
                    Node tictactoeCell = loader.load();
                    ((TictactoeKefferButtonController) loader.getController()).setParentController(this);

                    this.gridPaneBoard.add(tictactoeCell, col, row);
                    GridPane.setHalignment(tictactoeCell, HPos.CENTER);
                    GridPane.setValignment(tictactoeCell, VPos.CENTER);
                }

                //change the column and row size of grid pane
                List<ColumnConstraints> listColConstraint = new ArrayList<ColumnConstraints>();

                for (int col = 0 ; col < scale ; col++) {
                    ColumnConstraints colCons = new ColumnConstraints();
                    colCons.setPercentWidth(100);

                    listColConstraint.add(colCons);
                }

                ColumnConstraints[] arrColCons = new ColumnConstraints[scale];
                listColConstraint.toArray(arrColCons);

                this.gridPaneBoard.getColumnConstraints().addAll(arrColCons);
            }

        } catch(Exception ex) {
            ex.printStackTrace();
            new CustomErrorDialog().showDialogError(ex.toString());
        }
    }

    public void setParentController(MainController parentController) {
        this.parentController = parentController;
    }

    //-------------------

}
