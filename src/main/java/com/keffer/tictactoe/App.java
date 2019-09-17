package com.keffer.tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    private final String STAGE_TITLE = "";
    private final double MIN_STAGE_WIDTH = 600;
    private final double MIN_STAGE_HEIGHT = 400;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/static/com.keffer.tictactoe.main/com.keffer.tictactoe.main.fxml"));

        Parent parent = loader.load();

        Scene mainScene = new Scene(parent);

        primaryStage.setScene(mainScene);
        primaryStage.setTitle(STAGE_TITLE);
        primaryStage.setMinWidth(MIN_STAGE_WIDTH);
        primaryStage.setMinHeight(MIN_STAGE_HEIGHT);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

}