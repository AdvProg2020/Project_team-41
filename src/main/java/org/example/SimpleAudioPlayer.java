package org.example;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import java.io.*;

class musicThread extends Thread{
    private Player player;
    private FileInputStream fis;
    private final String file;

    public musicThread(String file) {
        this.file = file;
    }
    @Override
    public void run() {
        try {
            while (true) {
                fis = new FileInputStream(file);
                player = new Player(fis);
                player.play();
            }
        } catch (JavaLayerException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}


public class SimpleAudioPlayer {

    private static SimpleAudioPlayer simpleAudioPlayer;

    private String file;
    private musicThread musicThread;
    public static SimpleAudioPlayer getInstance() {
        if (simpleAudioPlayer == null) {
            simpleAudioPlayer = new SimpleAudioPlayer();
        }
        return simpleAudioPlayer;
    }
    public void playMusic(Music music)  {
        String previousMusic = file;
        file = "src/main/resources/org/example/musics/"+ music.getName() +".mp3";
        if (file.equals(previousMusic)) {
            return;
        }
        if (musicThread != null) {
            musicThread.stop();
        }
        musicThread = new musicThread(file);
        musicThread.start();
    }


    private SimpleAudioPlayer() {

    }

}


