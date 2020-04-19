package Game;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static util.Constant.*;

public class GameStage extends JFrame {



    public GameStage(){
        pack();
        setSize(MAP_WIDTH,MAP_HEIGHT);
        setTitle("Tower Defense");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setContentPane(new GameField(this));
        init();
    }
    public void init(){
        setVisible(true);
    }

    public static void main(String[] args) {
        new GameStage();
        GameSound g = new GameSound("sound/TowerDefense.wav");
    }

}
