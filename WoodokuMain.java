import javax.swing.*;


public class WoodokuMain{
	
	private static JFrame window;
	private static WoodokuView view;
	private static OpeningScreen content;
	private static Leaderboard leaderboard;

	public static void main(String[] args) {
		 window = new JFrame("Woodoku");
		 window.setSize(600,800);
		 window.setLocation(400,300);
		 window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 content = new OpeningScreen();
		 window.setContentPane(content);
		 window.setVisible(true);
		 window.setResizable(false);

		 BlockComposition compositionReference = new BlockComposition();
		 compositionReference.fillCompositions();
	}
	
	public static void changeContent() {
		view = new WoodokuView();
		window.setContentPane(view);
		window.setVisible(true);
		window.repaint();
	}
	
	public static void leaderboard() {
		leaderboard = new Leaderboard();
		window.setContentPane(leaderboard);
		window.setVisible(true);
		window.repaint();
	}
	
	public static void menu() {
		 content = new OpeningScreen();
		 window.setContentPane(content);
		 window.setVisible(true);
		 window.repaint();
	}

	
}
