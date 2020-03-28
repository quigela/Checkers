/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers.gui;

import javax.swing.JFrame;

import checkers.game.ScoreManager;

/**
 *
 * @author quigeam
 */
public class CheckerFrame extends JFrame{
	/**
	 * 
	 * CONSTRUCTOR - creates a new CheckerFrame
	 * @param manager - the score manager for the current game
	 */
    public CheckerFrame(ScoreManager manager) {
        CheckerCanvas cc = new CheckerCanvas(manager);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Checkers");
        this.setSize(cc.getDimension());
        getContentPane().add(cc);
    }
}
