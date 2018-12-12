/**
 *
 */
package com.withing.project.action;

import java.awt.*;


public interface PlayingEntity {

    public void getPlayerTurn();

    public boolean supportsUndo();

    public void undoCalled();

    public boolean supportsNewgame();

    public void newgameCalled();

    public boolean supportsSave();

    public void quit();

    public void win();

    public void lose();

    public void endMove();

    public String getName();

    public void setName(String name);

    public Color getColor();

    public void setColor(Color color);

    /**
     * Store a point to play when its your turn
     * */
    public void setMove(Object o, Point hex);

    /**
     * Return true to announce defeat mid-game
     * */
}
