package com.withing.project.action;

import java.io.*;

public class MoveList implements Serializable {
    private static final long serialVersionUID = 1L;
    public Move thisMove;
    public MoveList nextMove;

    public MoveList() {
    }

    public MoveList(int x, int y, byte teamNumber, long time, int moveNumber) {
        thisMove = new Move(x, y, teamNumber, moveNumber);
    }

    public MoveList(MoveList oldMove, int x, int y, byte teamNumber, int moveNumber) {
        thisMove = new Move(x, y, teamNumber, moveNumber);
        nextMove = oldMove;
    }

    public MoveList(MoveList oldMove, Move thisMove) {
        this.thisMove = thisMove;
        nextMove = oldMove;
    }

    public Move getmove() {
        return thisMove;
    }

    public void makeMove(int x, int y, byte teamNumber, int moveNumber) {
        nextMove = new MoveList(nextMove, thisMove);
        thisMove = new Move(x, y, teamNumber, moveNumber);
    }
}