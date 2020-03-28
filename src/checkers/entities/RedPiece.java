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
public class RedPiece extends Piece{
	/**
	 * CONSTRUCTOR - creates a new RedPiece with the position p
	 * @param p - the position to be used
	 */
    public RedPiece(Position p) {
        super(Color.RED, false, p);
    }
}
