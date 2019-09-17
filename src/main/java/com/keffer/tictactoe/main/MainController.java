package com.keffer.tictactoe.main;

import com.keffer.tictactoe.component.CustomErrorDialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    @FXML
    private Pane btnVsPlayerWrapper;

    @FXML
    private Pane btnVsAiWrapper;

    @FXML
    private TextField txtScale;

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

    }

    //PUBLIC METHOD BELOW

    public void checkScale() {

    }

    //-------------------

    //PRIVATE METHOD BELOW
    private void setupValidation() {
        Pattern pattern = Pattern.compile(NUMBER_PATTERN);
        ValidationSupport validationSupport = new ValidationSupport();

        ArrayList<Validator<String>> validatorList = new ArrayList<Validator<String>>();
        validatorList.add(Validator.createEmptyValidator("Scale field is required", Severity.ERROR));
        validatorList.add(Validator.createRegexValidator("Scale field accepts number only", pattern, Severity.ERROR));

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
