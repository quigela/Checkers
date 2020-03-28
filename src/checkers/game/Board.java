/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers.game;

import checkers.entities.*;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

/**
 *
 * @author quigeam
 */
public class Board {
	private Piece[][] board;
	private Position selected;
	private int remainingRed = 12;
	private int remainingBlack = 12;

	/**
	 * CONSTRUCTOR - creates a new board
	 * @param d - the dimension to be used for the board (8x8)
	 */
	public Board(Dimension d) {
		board = new Piece[d.width][d.height];
	}

	/**
	 * 
	 * METHOD getBoard - gets the current board and pieces
	 * @return - the board
	 */
	public Piece[][] getBoard() {
		return board;
	}
	
	/**
	 * 
	 * METHOD getRemainingBlack - gets the remaining black pieces on the board
	 * @return - the remaining blacks
	 */
	public int getRemainingBlack() {
		return remainingBlack;
	}

	/**
	 * 
	 * METHOD getRemainingRed - gets the remaining red pieces on the board
	 * @return - the remaining reds
	 */
	public int getRemainingRed() {
		return remainingRed;
	}
	
	/**
	 * 
	 * METHOD setPos - set the position on the board to a new piece
	 * @param row - the row
	 * @param col - the column
	 * @param p - the piece
	 */
	public void setPos(int row, int col, Piece p) {
		board[row][col] = p;
	}

	/**
	 * 
	 * METHOD setPos - set the position on the board to a new piece
	 * @param pos - the position to be set
	 * @param p - the piece
	 */
	public void setPos(Position pos, Piece p) {
		board[pos.getRow()][pos.getCol()] = p;
	}
	
	/**
	 * 
	 * METHOD getPos - get the piece at the position
	 * @param row - the row
	 * @param col - the column
	 * @return - the piece
	 */
	public Piece getPos(int row, int col) {
		return board[row][col];
	}
	
	/**
	 * 
	 * METHOD getPos - get the piece at the position
	 * @param p - the position
	 * @return - the piece
	 */
	public Piece getPos(Position p) {
		return board[p.getRow()][p.getCol()];
	}

	/**
	 * 
	 * METHOD select - set the piece to selected
	 * @param row - the row of the piece
	 * @param col - the column of the piece
	 */
	public void select(int row, int col) {
		if (board[row][col] != null) {
			board[row][col].setSelected(true);
			selected = new Position(row, col);
		}
	}

	/**
	 * 
	 * METHOD jump - jump piece, increase opponents score
	 * @param p - the piece to be jumped
	 */
	public void jump(Piece p) {
		if (p == null) {
			return;
		}
		if (p.getColor() == Color.BLACK) {
			remainingBlack--;
		} else {
			remainingRed--;
		}
		setPos(p.getPosition(), null);
	}

	/**
	 * 
	 * METHOD deselect - deselect the selected piece if there is any
	 */
	public void deselect() {
		if (selected != null) {
			board[selected.getRow()][selected.getCol()].setSelected(false);
			selected = null;
		}
	}

	/**
	 * 
	 * METHOD getSelected - returns the selected piece, null if there is none
	 * @return - the piece, or null if there is none
	 */
	public Piece getSelected() {
		if (selected != null) {
			return getPos(selected);
		} else {
			return null;
		}
	}

	/**
	 * 
	 * METHOD movePiece - move a piece
	 * @param p - the piece to move
	 * @param pos - the position to move to
	 */
	public void movePiece(Piece p, Position pos) {
		setPos(p.getPosition(), null);
		setPos(pos, p);
		p.setPosition(pos);
	}

	/**
	 * 
	 * METHOD isValidPos - checks if the position is in the grid
	 * @param p - the position to check
	 * @return - boolean if the position is valid
	 */
	public boolean isValidPos(Position p) {
		if (p.getRow() > 7 || p.getCol() > 7) {
			return false;
		}
		if (p.getRow() < 0 || p.getCol() < 0) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * METHOD gameWon - checks if any sides have no more pieces
	 * @return - boolean if there is a winner
	 */
	public boolean gameWon() {
		return (remainingRed == 0) || (remainingBlack == 0);
	}

	/**
	 * 
	 * METHOD getWinningColor - color of the winner
	 * @return - the color of the winner, null if there is none
	 */
	public Color getWinningColor() {
		if (gameWon()) {
			if (remainingRed == 0) {
				return Color.BLACK;
			}
			if (remainingBlack == 0) {
				return Color.RED;
			}
		} else {
			return null;
		}
		return null;
	}

	/**
	 * 
	 * METHOD getValidMoves - gets valid moves for the piece
	 * @param p - the piece
	 * @return - ArrayList of valid moves and jumps
	 */
	public ArrayList<Position> getValidMoves(Piece p) {
		ArrayList<Position> moves = new ArrayList<Position>();
		if (p.getColor() == Color.BLACK) {
			if (p.isKing()) {
				moves.addAll(getMovesUp(p));
			}
			moves.addAll(getMovesDown(p));
		} else if (p.getColor() == Color.RED) {
			if (p.isKing()) {
				moves.addAll(getMovesDown(p));
			}
			moves.addAll(getMovesUp(p));
		}
		return moves;
	}

	/**
	 * 
	 * METHOD setupBoard - adds all the default pieces
	 */
	public void setupBoard() {
		setPos(0, 0, new BlackPiece(new Position(0, 0)));
		setPos(0, 2, new BlackPiece(new Position(0, 2)));
        setPos(0, 4, new BlackPiece(new Position(0, 4)));
        setPos(0, 6, new BlackPiece(new Position(0, 6)));
        setPos(1, 1, new BlackPiece(new Position(1, 1)));
        setPos(1, 3, new BlackPiece(new Position(1, 3)));
        setPos(1, 5, new BlackPiece(new Position(1, 5)));
        setPos(1, 7, new BlackPiece(new Position(1, 7)));
        setPos(2, 0, new BlackPiece(new Position(2, 0)));
        setPos(2, 2, new BlackPiece(new Position(2, 2)));
        setPos(2, 4, new BlackPiece(new Position(2, 4)));
        setPos(2, 6, new BlackPiece(new Position(2, 6)));
        setPos(7, 7, new RedPiece(new Position(7, 7)));
        setPos(7, 5, new RedPiece(new Position(7, 5)));
        setPos(7, 3, new RedPiece(new Position(7, 3)));
        setPos(7, 1, new RedPiece(new Position(7, 1)));
        setPos(6, 6, new RedPiece(new Position(6, 6)));
        setPos(6, 4, new RedPiece(new Position(6, 4)));
        setPos(6, 2, new RedPiece(new Position(6, 2)));
        setPos(6, 0, new RedPiece(new Position(6, 0)));
        setPos(5, 7, new RedPiece(new Position(5, 7)));
        setPos(5, 5, new RedPiece(new Position(5, 5)));
        setPos(5, 3, new RedPiece(new Position(5, 3)));
        setPos(5, 1, new RedPiece(new Position(5, 1)));
	}

	/**
	 * 
	 * METHOD getMovesDown - get valid moves in the bottom left and bottom right direction
	 * @param piece - the piece to get the moves for
	 * @return - the valid moves
	 */
	private ArrayList<Position> getMovesDown(Piece piece) {
		Position p = piece.getPosition();
		ArrayList<Position> moves = new ArrayList<Position>();
		if (isValidPos(getBottomRight(p))) {
			if (getPos(getBottomRight(p)) == null) {
				moves.add(getBottomRight(p));
			} else {
				Position pos = getBottomRight(p);
				if (isValidPos(getBottomRight(pos))) {
					if (getPos(getBottomRight(pos)) == null) {
						if (getPos(pos).getColor() != piece.getColor()) {
							pos = getBottomRight(pos);
							moves.add(pos);
							Piece a = piece.clone();
							a.setPosition(pos);
							moves.addAll(getJumpsInAllDirections(a));
						}
					}
				}
			}
		}
		if (isValidPos(getBottomLeft(p))) {
			if (getPos(getBottomLeft(p)) == null) {
				moves.add(getBottomLeft(p));
			} else {
				Position pos = getBottomLeft(p);
				if (isValidPos(getBottomLeft(pos))) {
					if (getPos(getBottomLeft(pos)) == null) {
						if (getPos(pos).getColor() != piece.getColor()) {
							pos = getBottomLeft(pos);
							moves.add(pos);
							Piece a = piece.clone();
							a.setPosition(pos);
							moves.addAll(getJumpsInAllDirections(a));
						}
					}
				}
			}
		}
		return moves;
	}

	/**
	 * 
	 * METHOD getMovesUp - get valid moves in the top left and top right directions
	 * @param piece - the piece to get the moves for
	 * @return - the valid moves
	 */
	private ArrayList<Position> getMovesUp(Piece piece) {
		ArrayList<Position> moves = new ArrayList<Position>();
		Position p = piece.getPosition();
		if (isValidPos(getTopRight(p))) {
			if (getPos(getTopRight(p)) == null) {
				moves.add(getTopRight(p));
			} else {
				Position pos = getTopRight(p);
				if (isValidPos(getTopRight(pos))) {
					if (getPos(getTopRight(pos)) == null) {
						if (getPos(pos).getColor() != piece.getColor()) {
							pos = getTopRight(pos);
							moves.add(pos);
							Piece a = piece.clone();
							a.setPosition(pos);
							moves.addAll(getJumpsInAllDirections(a));
						}
					}
				}
			}
		}
		if (isValidPos(getTopLeft(p))) {
			if (getPos(getTopLeft(p)) == null) {
				moves.add(getTopLeft(p));
			} else {
				Position pos = getTopLeft(p);
				if (isValidPos(getTopLeft(pos))) {
					if (getPos(pos).getColor() != piece.getColor()) {
						if (getPos(getTopLeft(pos)) == null) {
							pos = getTopLeft(pos);
							moves.add(pos);
							Piece a = piece.clone();
							a.setPosition(pos);
							moves.addAll(getJumpsInAllDirections(a));
						}
					}
				}
			}
		}
		return moves;
	}
	
	/**
	 * 
	 * METHOD getJumpsInAllDirections - get jumps in all directions
	 * @param p - the piece to get the jumps for
	 * @return - the jumps in each direction
	 */
	public ArrayList<Position> getJumpsInAllDirections(Piece p) {
		ArrayList<Position> a = new ArrayList<Position>();
		Position pos = p.getPosition();
		if (p.getColor() == Color.BLACK) {
			if (p.isKing()) {
				if (isValidPos(getTopLeft(pos))) {
					if (getPos(getTopLeft(pos)) != null) {
						Position x = getTopLeft(pos);
						if (isValidPos(getTopLeft(x))) {
							if (getPos(getTopLeft(x)) == null) {
								if (getPos(x).getColor() != p.getColor()) {
									a.add(getTopLeft(x));
								}
							}
						}
					}
				}
				if (isValidPos(getTopRight(pos))) {
					if (getPos(getTopRight(pos)) != null) {
						Position x = getTopRight(pos);
						if (isValidPos(getTopRight(x))) {
							if (getPos(getTopRight(x)) == null) {
								if (getPos(x).getColor() != p.getColor()) {
									a.add(getTopRight(x));
								}
							}
						}
					}
				}
			}

			if (isValidPos(getBottomLeft(pos))) {
				if (getPos(getBottomLeft(pos)) != null) {
					Position x = getBottomLeft(pos);
					if (isValidPos(getBottomLeft(x))) {
						if (getPos(getBottomLeft(x)) == null) {
							if (getPos(x).getColor() != p.getColor()) {
								a.add(getBottomLeft(x));
							}
						}
					}
				}
			}
			if (isValidPos(getBottomRight(pos))) {
				if (getPos(getBottomRight(pos)) != null) {
					Position x = getBottomRight(pos);
					if (isValidPos(getBottomRight(x))) {
						if (getPos(getBottomRight(x)) == null) {
							if (getPos(x).getColor() != p.getColor()) {
								a.add(getBottomRight(x));
							}
						}
					}
				}
			}
		}
		if (p.getColor() == Color.RED) {
			if (p.isKing()) {
				if (isValidPos(getBottomLeft(pos))) {
					if (getPos(getBottomLeft(pos)) != null) {
						Position x = getBottomLeft(pos);
						if (isValidPos(getBottomLeft(x))) {
							if (getPos(getBottomLeft(x)) == null) {
								if (getPos(x).getColor() != p.getColor()) {
									a.add(getBottomLeft(x));
								}
							}
						}
					}
				}
				if (isValidPos(getBottomRight(pos))) {
					if (getPos(getBottomRight(pos)) != null) {
						Position x = getBottomRight(pos);
						if (isValidPos(getBottomRight(x))) {
							if (getPos(getBottomRight(x)) == null) {
								if (getPos(x).getColor() != p.getColor()) {
									a.add(getBottomRight(x));
								}
							}
						}
					}
				}
			}
			if (isValidPos(getTopLeft(pos))) {
				if (getPos(getTopLeft(pos)) != null) {
					Position x = getTopLeft(pos);
					if (isValidPos(getTopLeft(x))) {
						if (getPos(getTopLeft(x)) == null) {
							if (getPos(x).getColor() != p.getColor()) {
								a.add(getTopLeft(x));
							}
						}
					}
				}
			}
			if (isValidPos(getTopRight(pos))) {
				if (getPos(getTopRight(pos)) != null) {
					Position x = getTopRight(pos);
					if (isValidPos(getTopRight(x))) {
						if (getPos(getTopRight(x)) == null) {
							if (getPos(x).getColor() != p.getColor()) {
								a.add(getTopRight(x));
							}
						}
					}
				}
			}
		}
		return a;
	}

	/**
	 * 
	 * METHOD getJumpsInAllDirections - get jumps in all directions for the piece
	 * @param pos - position to get jumps for
	 * @param c - color not to jump
	 * @return - the jumps
	 */
	public ArrayList<Position> getJumpsInAllDirections(Position pos, Color c) {
		ArrayList<Position> a = new ArrayList<Position>();
		if (isValidPos(getTopLeft(pos))) {
			if (getPos(getTopLeft(pos)) != null) {
				Position x = getTopLeft(pos);
				if (isValidPos(getTopLeft(x))) {
					if (getPos(getTopLeft(x)) == null) {
						if (getPos(x).getColor() != c) {
							a.add(getTopLeft(x));
						}
					}
				}
			}
		}
		if (isValidPos(getTopRight(pos))) {
			if (getPos(getTopRight(pos)) != null) {
				Position x = getTopRight(pos);
				if (isValidPos(getTopRight(x))) {
					if (getPos(getTopRight(x)) == null) {
						if (getPos(x).getColor() != c) {
							a.add(getTopRight(x));
						}
					}
				}
			}
		}
		if (isValidPos(getBottomLeft(pos))) {
			if (getPos(getBottomLeft(pos)) != null) {
				Position x = getBottomLeft(pos);
				if (isValidPos(getBottomLeft(x))) {
					if (getPos(getBottomLeft(x)) == null) {
						if (getPos(x).getColor() != c) {
							a.add(getBottomLeft(x));
						}
					}
				}
			}
		}
		if (isValidPos(getBottomRight(pos))) {
			if (getPos(getBottomRight(pos)) != null) {
				Position x = getBottomRight(pos);
				if (isValidPos(getBottomRight(x))) {
					if (getPos(getBottomRight(x)) == null) {
						if (getPos(x).getColor() != c) {
							a.add(getBottomRight(x));
						}
					}
				}
			}
		}
		return a;
	}
	
	/**
	 * 
	 * METHOD isJump - checks if the distance between the two points merits a jump
	 * @param p1 - the first position
	 * @param p2 - the second position
	 * @return - if the distance makes a jump
	 */
	public static boolean isJump(Position p1, Position p2) {
		int changeX = Math.abs(p2.getRow() - p1.getRow());
		int changeY = Math.abs(p2.getCol() - p1.getCol());
		if (changeX == 2 && changeY == 2) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * METHOD isDoubleJump - checks if the distance between the two points merits a double jump
	 * @param p1 - the first position
	 * @param p2 - the second position
	 * @return - if the distance makes a double jump
	 */
	public static boolean isDoubleJump(Position p1, Position p2) {
		int changeX = Math.abs(p2.getRow() - p1.getRow());
		int changeY = Math.abs(p2.getCol() - p1.getCol());
		if (changeX > 3 || changeY > 3) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * METHOD getTopLeft - get the top left position
	 * @param p - the original position
	 * @return - the new position
	 */
	private Position getTopLeft(Position p) {
		return new Position(p.getRow() - 1, p.getCol() - 1);
	}

	/**
	 * 
	 * METHOD getTopRight - get the top right position
	 * @param p - the original position
	 * @return - the new position
	 */
	private Position getTopRight(Position p) {
		return new Position(p.getRow() - 1, p.getCol() + 1);
	}

	/**
	 * 
	 * METHOD getBottomLeft - get the bottom left position
	 * @param p - the original position
	 * @return - the new position
	 */
	private Position getBottomLeft(Position p) {
		return new Position(p.getRow() + 1, p.getCol() - 1);
	}

	/**
	 * 
	 * METHOD getBottomRight - get the bottom right position
	 * @param p - the original position
	 * @return - the new position
	 */
	private Position getBottomRight(Position p) {
		return new Position(p.getRow() + 1, p.getCol() + 1);
	}
}