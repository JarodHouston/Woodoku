import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class OpeningScreen extends JPanel{

	public OpeningScreen() {
	
		JButton playButton = new JButton("Play"){
	        {
	            setSize(250, 75);
	            setMaximumSize(getSize());
	        }
	    };
	    JButton leaderboard = new JButton("Leaderboard"){
	        {
	            setSize(250, 75);
	            setMaximumSize(getSize());
	        }
	    };

		JPanel mainPanel = new JPanel();	
		
		JLabel title = new JLabel("WOODOKU");
		title.setBounds(160, 200, 280, 75);
		title.setFont(new Font("SansSerif", Font.BOLD, 50));
		mainPanel.add(title);
		
		mainPanel.add(playButton);
		mainPanel.setLayout(null);
		playButton.setBounds(175, 400, 250, 75);
		playButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            WoodokuMain.changeContent();
	         }
	    });
		
		mainPanel.add(leaderboard);
		leaderboard.setBounds(175, 500, 250, 75);
		leaderboard.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            WoodokuMain.leaderboard();
	         }
	    });
		
		
		this.add(mainPanel);
		this.setLayout(new BorderLayout());
		this.add(mainPanel, BorderLayout.CENTER);

	}
}
