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
public class BlackKing extends Piece{
	/**
	 * CONSTRUCTOR - creates a new BlackKing with the position p
	 * @param p - the position to be used
	 */
    public BlackKing(Position p) {
        super(Color.BLACK, true, p);
    }
}
