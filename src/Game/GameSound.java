package Game;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
public class GameSound {
    public static boolean musicStatus = true;
    private File musicPath = null;
    private static Clip clip = null;
    private static long clipTimePosition = 0;
    public GameSound(String fileLocation){
        try{
            musicPath = new File(fileLocation);
            if(musicPath.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            }

        } catch (Exception e){
            System.out.println("Error in GameSound");
        }

    }
    public static void playMusic(){
        if(musicStatus){
            clip.setMicrosecondPosition(clipTimePosition);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } else {
            clipTimePosition = clip.getMicrosecondPosition();
            clip.stop();
        }

    }


}
