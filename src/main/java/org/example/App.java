package org.example;

import Client.Controller.StartProgram;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        StartProgram.startProgram();
        //scene = new Scene(loadFXML("userSection/sellerSection/seller section"));
        //scene = new Scene(loadFXML("userSection/managerSection/manager section"));
        scene = new Scene(loadFXML("mainMenu"));
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();

    }

    public static void main(String[] args) throws Exception {
        launch();
    }

    public static void setRoot(Parent parent) {
        scene.setRoot(parent);
    }

}