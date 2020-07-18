package org.example;

import Client.Controller.Connector;
import Client.Controller.StartProgram;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static final int PORT = 12666;
    private final String IP = "tcp://2.tcp.ngrok.io";

    @Override
    public void start(Stage stage) throws IOException {
        Socket socket = new Socket(IP, PORT);
        Connector.makeInstance(socket);
        SimpleAudioPlayer.getInstance().playMusic(Music.MAIN_MENU);
        setIconImage();
        StartProgram.startProgram();
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

    public static void main(String[] args) {
        launch();
    }

    public static void setRoot(Parent parent) {
        scene.setRoot(parent);
    }

    private void setIconImage(){

        //loading an image from a file
        final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        final URL imageResource = App.class.getClassLoader().getResource("org/example/images/appIcon.png");
        final Image image = defaultToolkit.getImage(imageResource);

        //this is new since JDK 9
        final Taskbar taskbar = Taskbar.getTaskbar();

        try {
            //set icon for mac os (and other systems which do support this method)
            taskbar.setIconImage(image);
        } catch (final UnsupportedOperationException e) {
            System.out.println("The os does not support: 'taskbar.setIconImage'");
        } catch (final SecurityException e) {
            System.out.println("There was a security exception for: 'taskbar.setIconImage'");
        }
    }
}