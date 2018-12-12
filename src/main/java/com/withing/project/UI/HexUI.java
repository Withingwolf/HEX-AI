package com.withing.project.UI;

import com.withing.project.AI.*;
import com.withing.project.action.*;

import javax.swing.*;

public class HexUI {
    public final static int SIDE = 11;
    public static JFrame frame;
    public static JPanel panel;
    public static JMenuBar menuBar;
    public final AnyShapeButton[][] gamePiece;
    public final int gridSize;
    public final boolean swap;
    public int moveNumber;
    public MoveList moveList;
    public int currentPlayer;
    public PlayingEntity player2;
    public boolean gameOver = false;
    public PlayingEntity player1;
    public int player1Type = 0;
    public int player2Type = 1;
    private boolean game = true;

    HexUI(int gridSize, boolean swap) {
        this.gridSize = gridSize;
        gamePiece = new AnyShapeButton[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                gamePiece[i][j] = new AnyShapeButton();
                gamePiece[i][j].x1 = i;
                gamePiece[i][j].y1 = j;
                gamePiece[i][j].addActionListener(new MouseAction(i, j, this));
            }
        }
        this.swap = swap;
        moveNumber = 1;
        moveList = new MoveList();
        currentPlayer = 1;
        game = true;
        gameOver = false;
        frame = new JFrame("HEX");
        panel = new JPanel();
        panel.setLayout(null);
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
//				setBounds(坐标x，坐标y，矩形长，矩形宽)j为横向计数，i为纵向计数
                gamePiece[i][j].setBounds(30 + 38 * j + 19 * i, 30 + 34 * i, 34, 40);
                panel.add(gamePiece[i][j]);
            }
        }
        JLabel text[] = new JLabel[11];
        text[0] = new JLabel("A");
        text[1] = new JLabel("B");
        text[2] = new JLabel("C");
        text[3] = new JLabel("D");
        text[4] = new JLabel("E");
        text[5] = new JLabel("F");
        text[6] = new JLabel("G");
        text[7] = new JLabel("H");
        text[8] = new JLabel("I");
        text[9] = new JLabel("J");
        text[10] = new JLabel("K");
        for (int i = 0; i < SIDE; i++) {
            text[i].setBounds(40 + 38 * i, 5, 34, 40);
            panel.add(text[i]);
        }
        JLabel text2[] = new JLabel[11];
        for (int i = 0; i < SIDE; i++) {
            text2[i] = new JLabel("" + (i + 1));
            text2[i].setBounds(10 + 19 * i, 30 + 34 * i, 34, 40);
            panel.add(text2[i]);
        }
        //菜单***************************************
        menuBar = new JMenuBar();
        JMenu Operate = new JMenu("操作");
        JMenuItem buildTree = new JMenuItem("后手");
        buildTree.addActionListener(new Menu(this));
        Operate.add(buildTree);
        menuBar.add(Operate);
        frame.setJMenuBar(menuBar);
        //*******************************************
        frame.add(panel);
        frame.setSize(80 + 38 * SIDE + 19 * SIDE, 110 + 34 * SIDE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    public static void setPlayer1(HexUI game) {
        if (game.player1Type == 0) game.player1 = new PlayerObject(1, game);
        else if (game.player1Type == 1) game.player1 = new BeeGameAI(1, game);
    }

    public static void setPlayer2(HexUI game) {
        if (game.player2Type == 0) game.player2 = new PlayerObject(2, game);
        else if (game.player2Type == 1) game.player2 = new BeeGameAI(2, game);
    }

    public static void main(String[] args) {
        HexUI hex_ui = new HexUI(11, true);
        setPlayer1(hex_ui);
        setPlayer2(hex_ui);
        hex_ui.run();

    }

    public void run() {
        while (game) {//Loop the game
            if (!checkForWinner()) {
                GameAction.getPlayer((currentPlayer % 2) + 1, this);
                if (GameAction.getPlayer(currentPlayer, this) == null) {
                    System.out.println("为空");
                }
                GameAction.getPlayer(currentPlayer, this).getPlayerTurn();
            }
            currentPlayer = (currentPlayer % 2) + 1;
        }
        System.out.println("Thread died");
    }

    private boolean checkForWinner() {
        GameAction.checkedFlagReset(this);
        if (GameAction.checkWinPlayer(1, this)) {
            game = false;
            gameOver = true;
            player1.win();
            player2.lose();

        } else if (GameAction.checkWinPlayer(2, this)) {
            game = false;
            gameOver = true;
            player1.lose();
            player2.win();

        }

        return gameOver;
    }
}


