package control;

import Game.GameField;

import java.awt.*;
import java.awt.event.*;

import static util.Constant.*;

public class KeyHandler implements MouseMotionListener , MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(GameField.gameStatus == START_MENU){
            GameField.startMenu.click(e.getButton());
        } else GameField.store.click(e.getButton());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        GameField.ingameButton.click(e.getButton());
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        GameField.mse = new Point(e.getX() -8 ,e.getY() - 32);
    }
}
