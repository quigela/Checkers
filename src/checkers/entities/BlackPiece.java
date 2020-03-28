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
public class BlackPiece extends Piece{
	/**
	 * CONSTRUCTOR - creates a new BlackPiece with the position p
	 * @param p - the position to be used
	 */
    public BlackPiece(Position p) {
        super(Color.BLACK, false, p);
    }
}
