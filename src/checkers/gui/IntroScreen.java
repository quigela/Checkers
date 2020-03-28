package checkers.gui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

import checkers.game.Player;
import checkers.game.ScoreManager;

public class IntroScreen extends JFrame {

	private JPanel contentPane;
	
	/**
	 * 
	 * CONSTRUCTOR - creates a new IntroScreen
	 */
	public IntroScreen() {
		setResizable(false);
		setTitle("Checkers");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 180);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAuthor = new JLabel("Checkers by Anthony Quigel");
		lblAuthor.setBounds(150, 11, 197, 14);
		contentPane.add(lblAuthor);
		
		JLabel lblPlayer1 = new JLabel("Player 1: ");
		lblPlayer1.setBounds(10, 41, 56, 14);
		contentPane.add(lblPlayer1);
		
		JLabel lblPlayer2 = new JLabel("Player 2: ");
		lblPlayer2.setBounds(10, 88, 64, 14);
		contentPane.add(lblPlayer2);
		
		final JTextArea txtPlayer1 = new JTextArea();
		txtPlayer1.setBounds(62, 36, 137, 22);
		contentPane.add(txtPlayer1);
		
		final JTextArea txtPlayer2 = new JTextArea();
		txtPlayer2.setBounds(62, 83, 137, 22);
		contentPane.add(txtPlayer2);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtPlayer1.getText().isEmpty() || txtPlayer2.getText().isEmpty()) {
					return;
				}
				ScoreManager manager = new ScoreManager(new Player(txtPlayer1.getText(), Color.BLACK), 
							new Player(txtPlayer2.getText(), Color.RED));
				CheckerFrame cf = new CheckerFrame(manager);
				cf.setVisible(true);
				setVisible(false);
			}
		});
		btnStart.setBounds(62, 113, 137, 23);
		contentPane.add(btnStart);
		
		JLabel lblPlayer1Color = new JLabel("");
		lblPlayer1Color.setBounds(209, 41, 44, 14);
		lblPlayer1Color.setOpaque(true);
		lblPlayer1Color.setBackground(Color.BLACK);
		contentPane.add(lblPlayer1Color);
		
		JLabel lblPlayer2Color = new JLabel("");
		lblPlayer2Color.setBounds(209, 88, 44, 14);
		lblPlayer2Color.setOpaque(true);
		lblPlayer2Color.setBackground(Color.RED);
		contentPane.add(lblPlayer2Color);
	}
}
