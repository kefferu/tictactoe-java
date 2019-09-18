package com.keffer.tictactoe.tictactoe_keffer;

import com.keffer.tictactoe.component.CustomErrorDialog;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public class TictactoeKeffer {
    private TictactoeKefferController controller;
    private Node node;

    public TictactoeKeffer(String fxmlPath, int scale, VsMode vsMode) {
        this.createNew(fxmlPath, scale, vsMode);
    }

    private boolean isEmpty() {
        if (this.node == null || this.controller == null) {
            return true;
        } else {
            return false;
        }
    }

    //public method below
    public void createNew(String fxmlPath, int scale, VsMode vsMode) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));

        try {
            this.node = loader.load();
            this.controller = loader.getController();

            this.controller.setupGame(scale, vsMode);

        } catch(Exception ex) {
            new CustomErrorDialog().showDialogError(ex.toString());
            ex.printStackTrace();
        }
    }

    public void discardView() {
        this.node = null;
        this.controller = null;
    }

    public  Node getNode() {
        return this.node;
    }

    public TictactoeKefferController getController() {
        return this.controller;
    }

}