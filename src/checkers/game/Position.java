/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers.game;

/**
 *
 * @author quigeam
 */
public class Position {
    private int row;
    private int col;
    
    /**
     * CONSTRUCTOR - creates a new Position object
     * @param row - the row
     * @param col - the column
     */
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }
    
    /**
     * METHOD getRow - gets the row of the position
     * @return - the row
     */
    public int getRow() {
        return row;
    }
    
    /**
     * METHOD getCol - gets the column of the position
     * @return - the column
     */
    public int getCol() {
        return col;
    }
    
    /**
     * METHOD toString - overrides the toString method
     * @return - both points of the Position
     */
    @Override
    public String toString() {
        return getRow() + ":" + getCol();
    }
    
    /**
     * METHOD equals - overrides the equals method
     * @param o - object to be compared to
     * @return - if the Positions equal
     */
    @Override
    public boolean equals(Object o) {
    	if (!(o instanceof Position)) {
    		return false;
    	}
    	Position p = (Position) o;
    	if (p.getRow() == row && p.getCol() == col) {
    		return true;
    	} else {
    		return false;
    	}
    }
}
