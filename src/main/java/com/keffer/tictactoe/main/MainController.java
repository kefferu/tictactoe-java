package com.keffer.tictactoe.main;

import com.keffer.tictactoe.tictactoe_keffer.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class MainController {

    private final String NUMBER_PATTERN = "\\d+";
    private final String MORE_THAN_TWO_NUMBER_PATTERN = "\\b[3-9]\\b|[0-9]{2,}+";
    private final String TICTACTOE_KEFFER_FXML_PATH = "/static/tictactoe-keffer/tictactoe-keffer.fxml";

    private TictactoeKefferController tictactoeKefferController;

    @FXML
    private BorderPane borderPaneMain;

    @FXML
    private Pane btnVsPlayerWrapper;

    @FXML
    private Pane btnVsAiWrapper;

    @FXML
    private VBox vboxCurrentTurn;

    @FXML
    private ImageView imgCurrentTurn;

    @FXML
    private VBox vboxWinner;

    @FXML
    private ImageView imgWinner;

    @FXML
    private TextField txtScale;

    @FXML
    private Label lblScale;

    @FXML
    private Button btnVsPlayer;

    @FXML
    private Button btnVsAi;

    @FXML
    private ImageView picCover;

    @FXML
    private VBox vboxGame;

    @FXML
    public void initialize() {
        this.setupValidation();

    }

    @FXML
    void onBtnVsAiClick(ActionEvent event) {

    }

    @FXML
    void onBtnVsPlayerClick(ActionEvent event) {
        TictactoeKeffer tictactoeKeffer = new TictactoeKeffer(this.TICTACTOE_KEFFER_FXML_PATH, Integer.parseInt(this.txtScale.getText()), VsMode.VS_PLAYER);
        this.tictactoeKefferController = tictactoeKeffer.getController();
        this.tictactoeKefferController.setParentController(this);

        this.setMode(PlayMode.PLAY_MODE);
        this.setGame(tictactoeKeffer.getNode());
    }

    //PUBLIC METHOD BELOW
        public void setGame(Node node) {
            this.vboxGame.getChildren().add(node);
        }

    public void setMode(PlayMode playMode) {
        switch (playMode) {
            case PLAY_MODE:
                this.txtScale.setVisible(false);
                this.lblScale.setVisible(false);
                this.btnVsPlayer.setVisible(false);
                this.btnVsAi.setVisible(false);
                this.vboxCurrentTurn.setVisible(true);
                this.vboxWinner.setVisible(true);
                this.vboxGame.getChildren().remove(this.picCover);
                break;

            case WAIT_MODE:
                this.txtScale.setVisible(true);
                this.lblScale.setVisible(true);
                this.vboxCurrentTurn.setVisible(true);
                this.vboxWinner.setVisible(true);
                this.vboxGame.getChildren().add(this.picCover);
                break;
        }
    }

    public void setImgCurrentTurn(CurrentTurn currentTurn) {
        if (currentTurn == CurrentTurn.NOUGHT) {
            this.imgCurrentTurn.setImage(new Image(getClass().getResource("/asset/icomoon/nought.png").toExternalForm()));

        } else {
            this.imgCurrentTurn.setImage(new Image(getClass().getResource("/asset/icomoon/circle.png").toExternalForm()));

        }
    }

    public void setWinner(CurrentTurn currentTurn) {
        if (currentTurn == CurrentTurn.NOUGHT) {
            this.imgWinner.setImage(new Image(getClass().getResource("/asset/icomoon/nought.png").toExternalForm()));

        } else {
            this.imgWinner.setImage(new Image(getClass().getResource("/asset/icomoon/circle.png").toExternalForm()));

        }
    }

    //-------------------

    //PRIVATE METHOD BELOW
    private void setupValidation() {
        Pattern pattern = Pattern.compile(NUMBER_PATTERN);
        ValidationSupport validationSupport = new ValidationSupport();

        ArrayList<Validator<String>> validatorList = new ArrayList<Validator<String>>();
        validatorList.add(Validator.createEmptyValidator("Scale field is required", Severity.ERROR));
        validatorList.add(Validator.createRegexValidator("Scale field accepts number only", pattern, Severity.ERROR));

        Pattern patternMoreThanTwo = Pattern.compile(MORE_THAN_TWO_NUMBER_PATTERN);
        validatorList.add(Validator.createRegexValidator("Scale field accepts more than 2 only", patternMoreThanTwo, Severity.ERROR));

        Validator<String>[] validatorArr = new Validator[validatorList.size()];
        validatorList.toArray(validatorArr);

        validationSupport.registerValidator(this.txtScale, Validator.combine(validatorArr));

        validationSupport.validationResultProperty().addListener((o, oldValidationResult, newValidationResult) -> {

            Tooltip toolTip = new Tooltip();

            Image image = new Image(getClass().getResourceAsStream("/asset/icomoon/error2.png"));
            toolTip.setGraphic(new ImageView(image));

            if (newValidationResult.getErrors().isEmpty()) {

                this.btnVsPlayer.setDisable(false);
                this.btnVsAi.setDisable(false);
                Tooltip.uninstall(this.btnVsAiWrapper, toolTip);
                Tooltip.uninstall(this.btnVsPlayerWrapper, toolTip);

            } else {
                String fullErrorMessage = "";

                for (ValidationMessage errorMessage : newValidationResult.getErrors()) {
                    fullErrorMessage += errorMessage + "\n";
                    toolTip.setText(fullErrorMessage);
                }
                Tooltip.install(this.btnVsAiWrapper, toolTip);
                Tooltip.install(this.btnVsPlayerWrapper, toolTip);
                this.btnVsPlayer.setDisable(true);
                this.btnVsAi.setDisable(true);
            }
        });
    }
    //-------------------

}
