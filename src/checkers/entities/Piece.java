/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers.entities;

import checkers.game.Position;
import java.awt.Color;

/**
 *
 * @author quigeam
 */
public class Piece {
    private Color c;
    private boolean isKing;
    private boolean isSelected;
    private Position pos;
    
    /**
     * CONSTRUCTOR - create a new piece object
     * @param c - color of the piece
     * @param isKing - boolean if the piece is a king
     * @param p - position of the piece on the board
     */
    public Piece(Color c, boolean isKing, Position p) {
        this.c = c;
        this.isKing = isKing;
        this.isSelected = false;
        pos = p;
    }
    
    /**
     * METHOD getColor - returns the color of the piece
     * @return the color of the piece
     */
    public Color getColor() {
        return c;
    }
    
    /**
     * METHOD king - kings the piece
     */
    public void king() {
        isKing = true;
    }
    
    /**
     * METHOD isKing - checks if the piece is a king
     * @return - boolean if the piece is a king or not
     */
    public boolean isKing() {
        return isKing;
    }
    
    /**
     * METHOD isSelected - checks if the piece is seleced
     * @return - boolean if the piece is selected or not
     */
    public boolean isSelected() {
        return isSelected;
    }
    
    /**
     * METHOD setSelected - sets the current selected status
     * @param b - the status
     */
    public void setSelected(boolean b) {
        isSelected = b;
    }
    
    /**
     * METHOD getPosition - gets the position of the piece
     * @return - the piece's position
     */
    public Position getPosition() {
        return pos;
    }
    
    /**
     * METHOD setPosition - sets the piece's position
     * @param p - the new position for the piece
     */
    public void setPosition(Position p) {
    	this.pos = p;
    }
    
    /**
     * METHOD clone - overrides the clone method
     * @return - the cloned piece
     */
    @Override
    public Piece clone() {
    	return new Piece(this.c, this.isKing, this.pos);
    }
}
