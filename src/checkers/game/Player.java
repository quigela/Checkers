/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers.game;

import java.awt.Color;

/**
 *
 * @author
 * Anthony
 */
public class Player {
    
    private String name;
    private Color color;
    
    /**
     * CONSTRUCTOR - creates a new Player object
     * @param name - name of the player
     * @param c - color of the player
     */
    public Player(String name, Color c) {
        this.name = name;
        this.color = c;
    }
    
    /**
     * METHOD getName - gets the name of the player
     * @return - the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * METHOD getColor - gets the color of the player
     * @return - the color
     */
    public Color getColor() {
    	return color;
    }
    
    /**
     * METHOD toString - overrides the toString method
     * @return - the name and color of the player
     */
    @Override
    public String toString() {
    	return name + ":" + color.toString();
    }
}
