/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers.game;

import java.util.ArrayList;

/**
 *
 * @author
 * Anthony
 */
public class ScoreManager {
    
    private Player p1;
    private Player p2;
    
    /**
     * CONSTRUCTOR - creates a new ScoreManager object
     * @param p1 - the first player
     * @param p2 - the second player
     */
    public ScoreManager(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
    }
    
    /**
     * METHOD toString - overrides the toString method
     * @return - the strings of each player
     */
    @Override
    public String toString() {
            return "[p1: " + p1.toString() + "]; [p2: " + p2.toString() + "];";
    }
    
    /**
     * METHOD getPlayer1 - return player 1
     * @return - the player
     */
    public Player getPlayer1() {
    	return p1;
    }
    
    /**
     * METHOD getPlayer2 - return player 2
     * @return
     */
    public Player getPlayer2() {
    	return p2;
    }
    
    /**
     * METHOD getPlayers - gets an ArrayList of both players
     * @return - the players
     */
    public ArrayList<Player> getPlayers() {
        ArrayList<Player> a = new ArrayList<Player>();
    	a.add(p1);
    	a.add(p2);
        return a;
    }
}
