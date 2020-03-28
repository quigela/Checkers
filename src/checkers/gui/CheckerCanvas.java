/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers.gui;

import checkers.game.Board;
import checkers.entities.*;
import checkers.game.Player;
import checkers.game.Position;
import checkers.game.ScoreManager;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author
 * quigeam
 */
public class CheckerCanvas extends Canvas implements MouseListener {

    private final int BOX_WIDTH = 50;
    private final int BOX_HEIGHT = 50;
    private final int GRID_WIDTH = 8;
    private final int GRID_HEIGHT = 8;
    private Board board;
    private ScoreManager players;
    private Player currentPlayer;

    /**
     *
     * CONSTRUCTOR
     * -
     * creates
     * a
     * new
     * CheckerCanvas
     *
     * @param
     * manager
     * -
     * the
     * ScoreManager
     * for
     * the
     * current
     * match
     */
    public CheckerCanvas(ScoreManager manager) {
        this.addMouseListener(this);
        board = new Board(new Dimension(BOX_WIDTH * GRID_WIDTH, BOX_HEIGHT * GRID_HEIGHT));
        this.players = manager;
        board.setupBoard();
        this.setSize(getDimension());
        currentPlayer = players.getPlayer1();
    }

    /**
     *
     * METHOD
     * getDimension
     * -
     * get
     * the
     * dimension
     * of
     * the
     * window
     * to
     * properly
     * house
     * the
     * board
     *
     * @return
     * -
     * the
     * dimension
     */
    public Dimension getDimension() {
        int width = BOX_WIDTH * GRID_WIDTH + 100;
        int height = BOX_HEIGHT * GRID_HEIGHT + 100;
        return new Dimension(width, height);
    }

    /**
     *
     * METHOD
     * paint
     * -
     * paints
     * to
     * the
     * graphics
     * object
     *
     * @param
     * g
     * -
     * the
     * graphics
     * object
     */
    @Override
    public void paint(Graphics g) {
        drawGrid(g);
        checkerGrid(g);
        drawPieces(g);
        drawSelection(g);
        drawCounter(g);
    }

    /**
     *
     * METHOD
     * update
     * -
     * handles
     * double
     * buffering
     * for
     * the
     * graphics
     * object
     *
     * @param
     * g
     * -
     * the
     * graphics
     * object
     */
    public void update(Graphics g) {
        g.clearRect(0, 0, this.getSize().width, this.getSize().height);

        Graphics offScreenGraphics;
        Dimension dim = this.getSize();
        BufferedImage offScreen =
                new BufferedImage(dim.width,
                dim.height, BufferedImage.TYPE_INT_ARGB);

        offScreenGraphics = offScreen.getGraphics();
        offScreenGraphics.setColor(this.getBackground());
        offScreenGraphics.fillRect(this.getX(), this.getY(),
                dim.width, dim.height);

        offScreenGraphics.setColor(this.getForeground());
        paint(offScreenGraphics);

        g.drawImage(offScreen, 0, 0, this);
    }

    /**
     *
     * METHOD
     * drawGrid
     * -
     * draws
     * the
     * grid
     * lines
     * onto
     * the
     * graphics
     * object
     *
     * @param
     * g
     * -
     * the
     * graphics
     * object
     */
    public void drawGrid(Graphics g) {
        Color origColor = g.getColor();
        g.setColor(Color.BLACK);

        g.drawRect(0, 0, BOX_WIDTH * GRID_WIDTH, BOX_HEIGHT * GRID_HEIGHT);

        for (int x = BOX_WIDTH; x <= GRID_WIDTH * BOX_WIDTH;
                x += BOX_WIDTH) {
            g.drawLine(x, 0, x, BOX_HEIGHT * GRID_HEIGHT);
        }
        for (int y = BOX_HEIGHT; y <= GRID_HEIGHT * BOX_HEIGHT;
                y += BOX_HEIGHT) {
            g.drawLine(0, y, GRID_WIDTH * BOX_WIDTH, y);
        }
        g.setColor(origColor);
    }

    /**
     *
     * METHOD
     * checkerGrid
     * -
     * checkers
     * the
     * grid
     * alternating
     * black
     * and
     * white
     * tiles
     *
     * @param
     * g
     * -
     * the
     * graphics
     * object
     */
    public void checkerGrid(Graphics g) {
        Color origColor = g.getColor();
        g.setColor(Color.WHITE);

        for (int x = 0; x < GRID_WIDTH * BOX_WIDTH;
                x += BOX_WIDTH) {
            for (int y = 0; y < GRID_HEIGHT * BOX_HEIGHT;
                    y += BOX_HEIGHT) {
                g.fillRect(x + 1, y + 1, BOX_WIDTH - 1, BOX_HEIGHT - 1);
                if (g.getColor() == Color.WHITE) {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.WHITE);
                }
            }
            if (g.getColor() == Color.WHITE) {
                g.setColor(Color.BLACK);
            } else {
                g.setColor(Color.WHITE);
            }
        }
        g.setColor(origColor);
    }

    /**
     *
     * METHOD
     * drawPieces
     * -
     * draws
     * each
     * piece
     * onto
     * the
     * board
     *
     * @param
     * g
     * -
     * the
     * graphics
     * object
     */
    public void drawPieces(Graphics g) {
        Color origColor = g.getColor();
        Piece[][] pieces = board.getBoard();
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                Piece p = pieces[i][j];
                if (p != null) {
                    g.setColor(p.getColor());
                    if (p.isSelected()) {
                        g.setColor(Color.CYAN);
                    }
                    g.fillOval(j * BOX_WIDTH, i * BOX_HEIGHT,
                            BOX_WIDTH, BOX_HEIGHT);
                    if (p.isKing()) {
                        if (p.getColor() == Color.RED) {
                            g.setColor(Color.BLACK);
                        } else if (p.getColor() == Color.BLACK) {
                            g.setColor(Color.RED);
                        }
                        drawCenteredCircle(g, j * BOX_WIDTH + (BOX_WIDTH / 2),
                                i * BOX_HEIGHT + (BOX_HEIGHT / 2),
                                (BOX_WIDTH + BOX_HEIGHT) / 4);
                    }
                }
            }
        }
        g.setColor(origColor);
    }

    /**
     *
     * METHOD
     * drawSelection
     * -
     * colors
     * the
     * currently
     * selected
     * piece
     * cyan
     * as
     * well
     * as
     * all
     * the
     * valid
     * moves
     * it
     * can
     * make
     *
     * @param
     * g
     * -
     * the
     * graphics
     * object
     */
    public void drawSelection(Graphics g) {
        Color origColor = g.getColor();
        if (board.getSelected() != null) {
            g.setColor(Color.CYAN);
            ArrayList<Position> moves = board.getValidMoves(
                    board.getSelected());
            if (moves.size() > 0) {
                for (Position p : moves) {
                    g.fillRect((p.getCol() * BOX_WIDTH) + 1,
                            (p.getRow() * BOX_HEIGHT) + 1,
                            BOX_WIDTH - 1, BOX_HEIGHT - 1);
                }
            }
        }
        g.setColor(origColor);
    }

    /**
     *
     * METHOD
     * drawCounter
     * -
     * draws
     * the
     * current
     * player
     * and
     * score
     * counter
     *
     * @param
     * g
     * -
     * the
     * graphics
     * object
     */
    public void drawCounter(Graphics g) {
        if (currentPlayer == null) {
            return;
        }
        g.drawString("It is currently " + currentPlayer.getName() + "'s turn", 0, BOX_HEIGHT * GRID_HEIGHT + 25);
        g.drawString(players.getPlayer1().getName() + " Score: " + Math.abs(board.getRemainingRed() - 12),
                0, BOX_HEIGHT * GRID_HEIGHT + 40);
        g.drawString(players.getPlayer2().getName() + " Score: " + Math.abs(board.getRemainingBlack() - 12),
                0, BOX_HEIGHT * GRID_HEIGHT + 55);
    }

    /**
     *
     * METHOD
     * drawCenteredCircle
     * -
     * draws
     * a
     * circle
     * with
     * a
     * center
     * point
     * and
     * radius
     * instead
     * of
     * startX,
     * startY,
     * width,
     * and
     * height
     *
     * @param
     * g
     * -
     * the
     * graphics
     * object
     * @param
     * x
     * -
     * x
     * of
     * the
     * center
     * point
     * @param
     * y
     * -
     * y
     * of
     * the
     * center
     * point
     * @param
     * r
     * -
     * radius
     * of
     * the
     * circle
     */
    private void drawCenteredCircle(Graphics g, int x, int y, int r) {
        x = x - (r / 2);
        y = y - (r / 2);
        g.drawOval(x, y, r, r);
    }

    /**
     *
     * METHOD
     * mouseClicked
     * -
     * handles
     * mouse
     * clicking
     *
     * @param
     * me
     * -
     * the
     * mouse
     * event
     */
    @Override
    public void mouseClicked(MouseEvent me) {
        int x = me.getX();
        int y = me.getY();
        if (x <= BOX_WIDTH * GRID_WIDTH && y <= BOX_HEIGHT * GRID_HEIGHT) {
            int gridX = y / BOX_WIDTH;
            int gridY = x / BOX_HEIGHT;
            Position clicked = new Position(gridX, gridY);
            if (board.isValidPos(clicked)) {
                if (board.getPos(clicked.getRow(), clicked.getCol()) != null) {
                    if (board.getPos(clicked.getRow(), clicked.getCol()).getColor().equals(currentPlayer.getColor())) {
                        board.deselect();
                        board.select(clicked.getRow(), clicked.getCol());
                    }
                }
                if (board.getSelected() != null && board.getPos(clicked) == null) {
                    move(clicked);
                }
            }
        }
        repaint();
    }

    /**
     *
     * METHOD
     * move
     * -
     * moves
     * the
     * selected
     * piece
     * to
     * the
     * clicked
     * position
     * if
     * possible.
     * Also
     * handles
     * jumps
     * if
     * any
     *
     * @param
     * clicked
     * -
     * the
     * position
     * clicked
     */
    private void move(Position clicked) {
        checkDraw();
        ArrayList<Position> moves = board.getValidMoves(board.getSelected());
        Piece piece = board.getSelected();
        board.deselect();
        boolean moved = false;
        if (moves.contains(clicked)) {
            if (Board.isJump(piece.getPosition(), clicked)
                    && !Board.isDoubleJump(piece.getPosition(), clicked)) {
                int midX = (piece.getPosition().getRow() + clicked.getRow()) / 2;
                int midY = (piece.getPosition().getCol() + clicked.getCol()) / 2;
                Position jumped = new Position(midX, midY);
                if (board.isValidPos(jumped)) {
                    if (board.getPos(jumped) != null) {
                        board.jump(board.getPos(jumped));
                    }
                }
            } else if (Board.isDoubleJump(piece.getPosition(), clicked)) {
                ArrayList<Position> a = board.getJumpsInAllDirections(clicked, piece.getColor());
                Position midJump = null;
                for (Position pos : a) {
                    if (moves.contains(pos)) {
                        midJump = pos;
                    }
                }
                if (midJump != null) {
                    int x1 = (piece.getPosition().getRow() + midJump.getRow()) / 2;
                    int y1 = (piece.getPosition().getCol() + midJump.getCol()) / 2;
                    Position jump1 = new Position(x1, y1);

                    int x2 = (midJump.getRow() + clicked.getRow()) / 2;
                    int y2 = (midJump.getCol() + clicked.getCol()) / 2;
                    Position jump2 = new Position(x2, y2);

                    if (board.isValidPos(jump1)) {
                        if (board.getPos(jump1) != null) {
                            board.jump(board.getPos(jump1));
                        }
                    }

                    if (board.isValidPos(jump2)) {
                        if (board.getPos(jump2) != null) {
                            board.jump(board.getPos(jump2));
                        }
                    }
                }
            }
            board.movePiece(piece, clicked);
            moved = true;
            if (piece.getColor() == Color.BLACK) {
                if (piece.getPosition().getRow() == 7) {
                    Piece king = board.getPos(piece.getPosition());
                    king.king();
                    board.setPos(king.getPosition(), king);
                }
            }
            if (piece.getColor() == Color.RED) {
                if (piece.getPosition().getRow() == 0) {
                    Piece king = board.getPos(piece.getPosition());
                    king.king();
                    board.setPos(king.getPosition(), king);
                }
            }
        }
        if (moved) {
            if (currentPlayer.equals(players.getPlayer1())) {
                currentPlayer = players.getPlayer2();
            } else {
                currentPlayer = players.getPlayer1();
            }
        }
        repaint();
        checkWin();
    }

    /**
     *
     * METHOD
     * checkWin
     * -
     * checks
     * if
     * the
     * game
     * has
     * been
     * won
     */
    private void checkWin() {
        if (board.gameWon()) {
            if (board.getWinningColor() == Color.BLACK) {
                JOptionPane.showMessageDialog(this, "The winner is " + players.
                        getPlayer1().getName());
            } else {
                JOptionPane.showMessageDialog(this, "The winner is " + players.
                        getPlayer2().getName());
            }
            System.exit(0);
        }
    }

    /**
     *
     * METHOD
     * checkDraw
     * -
     * checks
     * that
     * the
     * current
     * player
     * actually
     * has
     * somewhere
     * to
     * move
     */
    private void checkDraw() {
        Piece[][] pieces = board.getBoard();
        boolean noMoves = true;
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                if (pieces[i][j] != null
                        && pieces[i][j].getColor() == currentPlayer.getColor()) {
                    if (board.getValidMoves(pieces[i][j]).size() > 0) {
                        noMoves = false;
                    }
                }
            }
        }
        if (noMoves) {
            JOptionPane.showMessageDialog(this, currentPlayer.getName()
                    + " has no moves! The game is a draw!");
            System.exit(0);
        }
    }

    /**
     * All
     * unused
     * methods.
     * Only
     * exist
     * because
     * the
     * MouseListener
     * interface
     * requires
     * them
     * to
     * be
     * present.
     * Felt
     * unnecessary
     * to
     * comment
     * as
     * they
     * do
     * f-all
     */
    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
}