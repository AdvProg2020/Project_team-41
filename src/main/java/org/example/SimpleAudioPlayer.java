package org.example;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import java.io.*;




public class SimpleAudioPlayer {

    private static SimpleAudioPlayer simpleAudioPlayer;
    private Player player;
    public static SimpleAudioPlayer getInstance() {
        if (simpleAudioPlayer == null) {
            simpleAudioPlayer = new SimpleAudioPlayer();
        }
        return simpleAudioPlayer;
    }
    public void playMusic(Music music){
        if (player != null) {
            player.close();
        }
        String file = "src/main/resources/org/example/musics/"+ music.getName() +".mp3";

        new Thread(() -> {
            try {
                while (true) {
                    FileInputStream fis = new FileInputStream(file);
                    player = new Player(fis);
                    player.play();
                }
            } catch (JavaLayerException | FileNotFoundException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private SimpleAudioPlayer() {

    }
}

