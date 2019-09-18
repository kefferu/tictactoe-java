package com.keffer.tictactoe.tictactoe_keffer;

import com.keffer.tictactoe.component.CustomErrorDialog;
import com.keffer.tictactoe.main.MainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class TictactoeKefferController {

    private CurrentTurn currentTurn = CurrentTurn.NOUGHT;
    private int scale = 0;

    private final String TICTACTOE_KEFFER_CELL_FXML_PATH = "/static/tictactoe-keffer/tictactoe-keffer-btn-cell.fxml";
    private final int DEFAULT_SCALE = 3;

    private MainController parentController;

    @FXML
    private GridPane gridPaneBoard;

    //PUBLIC METHOD BELOW
    public void setupGame(int scale, VsMode vsMode) {
        VBox.setVgrow(this.gridPaneBoard, Priority.ALWAYS);
        this.scale = scale;

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

            }

            //add the extra ColumnConstraints and RowConstraints which have the same properties with the first one
            //so the extra added column and row have the same size as the first one
            for (int extraCol = 0 ; extraCol < scale - this.DEFAULT_SCALE ; extraCol++) {
                this.gridPaneBoard.getColumnConstraints().add(this.gridPaneBoard.getColumnConstraints().get(0));
            }

            for (int extraRow = 0 ; extraRow < scale - this.DEFAULT_SCALE ; extraRow++) {
                this.gridPaneBoard.getRowConstraints().add(this.gridPaneBoard.getRowConstraints().get(0));
            }

        } catch(Exception ex) {
            ex.printStackTrace();
            new CustomErrorDialog().showDialogError(ex.toString());
        }
    }

    public CurrentTurn getCurrentTurn() {
        return this.currentTurn;
    }

    public void setCurrentTurn(CurrentTurn currentTurn) {
        this.currentTurn = currentTurn;
        this.parentController.setImgCurrentTurn(currentTurn);
    }

    public boolean checkWinner(CurrentTurn lastState, Button cell) {
        //-1 value means the value rowindex or colindex has not been intialized
        int rowIndex = -1;
        int colIndex = -1;

        boolean isWin = false;

        //this looping is to check the row index and col index and what value contained in the last pressed button
        for (Node checkCell : this.gridPaneBoard.getChildren()) {

            if (checkCell.equals(cell)) {
                rowIndex = this.gridPaneBoard.getRowIndex(checkCell);
                colIndex = this.gridPaneBoard.getColumnIndex(checkCell);
                break;
            }
        }

        if (rowIndex > -1 && colIndex > -1) {
            //check col
            for(int startCol = 0 ; startCol < this.scale ; startCol++){
                if(this.getNodeFromGridPane(this.gridPaneBoard, rowIndex, startCol).getUserData() != lastState)
                    break;

                if(startCol == scale-1){
                    //report win
                    isWin = true;
                }
            }

            //check row
            for(int startRow = 0 ; startRow < this.scale ; startRow++){
                if(this.getNodeFromGridPane(this.gridPaneBoard, startRow, colIndex).getUserData() != lastState)
                    break;

                if(startRow == scale-1){
                    //report win
                    isWin = true;
                }
            }

            //check diagonal
            if (rowIndex == colIndex) {

                for(int startIndex = 0 ; startIndex < this.scale ; startIndex++){
                    if(this.getNodeFromGridPane(this.gridPaneBoard, startIndex, startIndex).getUserData() != lastState)
                        break;

                    if(startIndex == scale-1){
                        //report win
                        isWin = true;
                    }
                }
            }

            //check anti diagonal
            if (rowIndex + colIndex == scale - 1) {

                System.out.println("anti row index = " + rowIndex);
                System.out.println("anti col index = " + colIndex);
                for(int startRow = 0 ; startRow < this.scale ; startRow++){
                    if(this.getNodeFromGridPane(this.gridPaneBoard, startRow, scale - 1 - startRow).getUserData() != lastState)
                        break;

                    if(startRow == scale-1){
                        //report win
                        isWin = true;
                    }
                }
            }

        }

        if (isWin) {
            this.parentController.setWinner(lastState);
            return true;
        }
        return false;
    }

    public void stopGame() {
        for (Node cell : this.gridPaneBoard.getChildren()) {
            cell.setDisable(true);
        }
    }

    public void setParentController(MainController parentController) {
        this.parentController = parentController;
    }

    //-------------------

    //PRIVATE METHOD BELOW
    private Node getNodeFromGridPane(GridPane gridPane, int row, int col) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }


    //--------------------

}
