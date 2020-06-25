package Client.View.Menus;

import animatefx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.example.App;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class AboutUs {
    public AnchorPane aboutUsAnchorPane;
    public ImageView aboutUsImageView;
    public TextArea aboutUsTextArea;

    @FXML
    public void initialize(){
        Timer animTimer = new Timer();
        animTimer.scheduleAtFixedRate(new TimerTask() {
            int i=0;

            @Override
            public void run() {
            FileInputStream fileInputStream = null;
                if (i<3) {
                    try {

                        new SlideInLeft(aboutUsImageView).play();
                        fileInputStream = new FileInputStream("src/main/resources/org/example/images/aboutUsSlide/" + (i+1) + ".jpg");
                        Image image = new Image(fileInputStream);
                        aboutUsImageView.setImage(image);
                    } catch (FileNotFoundException e) {
                        System.out.println(i);
                        e.printStackTrace();
                    }
                } else {
                    i = -1;
                }
                i++;
            }

        }, 0, 2000);
    }

    public void backButtonClicked(MouseEvent mouseEvent) {
        try {
            App.setRoot("mainMenu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
