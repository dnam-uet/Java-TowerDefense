package Map;

import Game.GameField;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Save {
    public void loadSave(File loadPath){
        try {
            Scanner scanner = new Scanner(loadPath);
            GameField.killsToWin = scanner.nextInt();
            while (scanner.hasNext()){
                for(int y = 0 ; y < GameField.room.blocks.length ; y++){
                    for(int x = 0 ; x < GameField.room.blocks[0].length ; x++){
                        GameField.room.blocks[y][x].groundID = scanner.nextInt();
                    }
                }
                for(int y = 0 ; y < GameField.room.blocks.length ; y++){
                    for(int x = 0 ; x < GameField.room.blocks[0].length ; x++){
                        GameField.room.blocks[y][x].airID = scanner.nextInt();
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
