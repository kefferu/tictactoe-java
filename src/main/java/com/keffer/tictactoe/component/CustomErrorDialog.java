package com.keffer.tictactoe.component;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;

public class CustomErrorDialog {

    public CustomErrorDialog() {

    }

    public void showDialogError(String message) {
        Alert dialogError = new Alert(Alert.AlertType.ERROR);

        dialogError.setHeaderText("Error");
        dialogError.initModality(Modality.APPLICATION_MODAL);

        dialogError.getDialogPane().setContent(this.createDialogContent(message));

        dialogError.showAndWait();

    }

    private Node createDialogContent(String message) {
        VBox parent = new VBox();

        Label contentText = new Label("Error Message :");

        TextArea contentTextArea = new TextArea(message);
        contentTextArea.setWrapText(true);
        contentTextArea.setEditable(false);

        parent.getChildren().addAll(contentText, contentTextArea);
        return parent;
    }
}
