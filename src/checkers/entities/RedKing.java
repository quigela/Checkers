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
public class RedKing extends Piece{
	/**
	 * CONSTRUCTOR - creates a new RedKing with the position p
	 * @param p - the position to be used
	 */
    public RedKing(Position p) {
        super(Color.RED, true, p);
    }
}
