import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.*;

public class Leaderboard extends JPanel{
	
	private JButton returnButton;
	private JLabel text;
	private JLabel one;
	private JLabel two;
	private JLabel three;
	private JLabel four;
	private JLabel five;

	public Leaderboard() {
		returnButton = new JButton("Menu"){
	        {
	            setSize(250, 75);
	            setMaximumSize(getSize());
	        }
	    };
	    
	    this.add(returnButton);
		this.setLayout(null);
		returnButton.setBounds(175, 500, 250, 75);
		returnButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            WoodokuMain.menu();
	         }
	    });
		
		TextIO.readFile("result.txt");
		String highscore = TextIO.getlnString();
		TextIO.readStandardInput();
		
		String[] leaderString = new String[5];
		leaderString = highscore.split(", ");
		
		text = new JLabel("Top 5 Scores:");
		this.add(text);
		text.setBounds(161, 50, 277, 50);
		text.setFont(new Font("SansSerif", Font.BOLD, 40));
		
		one = new JLabel(leaderString[0]);
		this.add(one);
		one.setBounds(161, 150, 400, 50);
		one.setFont(new Font("SansSerif", Font.BOLD, 30));
		
		two = new JLabel(leaderString[1]);
		this.add(two);
		two.setBounds(161, 200, 400, 50);
		two.setFont(new Font("SansSerif", Font.BOLD, 30));
		
		three = new JLabel(leaderString[2]);
		this.add(three);
		three.setBounds(161, 250, 400, 50);
		three.setFont(new Font("SansSerif", Font.BOLD, 30));
		
		four = new JLabel(leaderString[3]);
		this.add(four);
		four.setBounds(161, 300, 400, 50);
		four.setFont(new Font("SansSerif", Font.BOLD, 30));
		
		five = new JLabel(leaderString[4]);
		this.add(five);
		five.setBounds(161, 350, 400, 50);
		five.setFont(new Font("SansSerif", Font.BOLD, 30));
	}
}
