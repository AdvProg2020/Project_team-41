package org.example;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import java.io.*;
import java.util.Date;


public class SimpleAudioPlayer {


    public SimpleAudioPlayer() {
        String file = "src/main/resources/org/example/musics/music.mp3";

        new Thread(() -> {
            try {
                while (true) {
                    FileInputStream fis = new FileInputStream(file);
                    Player player = new Player(fis);
                    player.play();
                }
            } catch (JavaLayerException | FileNotFoundException e) {
                e.printStackTrace();
            }
        }).start();
    }

}

