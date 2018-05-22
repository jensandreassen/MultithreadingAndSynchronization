package assignment1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

/**
 * The GUI for assignment 1, DualThreads
 */
public class Gui {
	/**
	 * These are the components you need to handle. You have to add listeners and/or
	 * code
	 */
	private JFrame frame; // The Main window
	private JButton btnDisplay; // Start thread moving display
	private JButton btnDStop; // Stop moving display thread
	private JButton btnTriangle;// Start moving graphics thread
	private JButton btnTStop; // Stop moving graphics thread
	private JButton btnOpen; // Open audio file
	private JButton btnPlay; // Start playing audio
	private JButton btnStop; // Stop playing
	private JButton btnGo; // Start game catch me
	private JPanel pnlMove; // The panel to move display in
	private JPanel pnlRotate; // The panel to move graphics in
	private JPanel pnlGame; // The panel to play in
	private JLabel lblPlaying; // Playing text
	private JLabel lblAudio; // Audio file
	private JTextArea txtHits; // Dispaly hits
	private JComboBox<String> cmbSkill; // Skill combo box, needs to be filled in
	private JLabel label1;
	private Random r = new Random();
	private Controller cont;
	private JButton gamebutton;

	/**
	 * Constructor
	 */
	public Gui() {
		cont = new Controller(this);
	}

	/**
	 * Starts the application
	 * @throws IOException 
	 */
	public void Start() throws IOException {
		frame = new JFrame();
		frame.setBounds(0, 0, 819, 438);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setTitle("Multiple Thread Demonstrator");
		InitializeGUI(); // Fill in components
		frame.setVisible(true);
		frame.setResizable(false); // Prevent user from change size
		frame.setLocationRelativeTo(null); // Start middle screen
		frame.addWindowListener(new WindowAdapter() {
			//Checks if the user wants to close the application, if so it stops the threads and exits
		    @Override
		    public void windowClosing(WindowEvent e) {
		    	int confirm = JOptionPane.showOptionDialog(frame,
                        "Are You Sure to Close this Application?",
                        "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == 0) {
                	cont.close();
                    System.exit(0);
                }
		        
		    }
		});
	}

	/**
	 * Sets up the GUI with components
	 * @throws IOException 
	 */
	private void InitializeGUI() throws IOException {
		// The music player outer panel
		JPanel pnlSound = new JPanel();
		Border b1 = BorderFactory.createTitledBorder("Music Player");
		pnlSound.setBorder(b1);
		pnlSound.setBounds(12, 12, 450, 100);
		pnlSound.setLayout(null);

		// Add labels and buttons to this panel
		lblPlaying = new JLabel("Now Playing: "); // Needs to be alteraed
		lblPlaying.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblPlaying.setBounds(128, 16, 300, 20);
		pnlSound.add(lblPlaying);
		JLabel lbl1 = new JLabel("Loaded Audio File: ");
		lbl1.setBounds(10, 44, 130, 13);
		pnlSound.add(lbl1);
		lblAudio = new JLabel("..."); // Needs to be altered
		lblAudio.setBounds(115, 44, 300, 13);
		pnlSound.add(lblAudio);
		btnOpen = new JButton("Open");
		btnOpen.setBounds(6, 71, 75, 23);
		pnlSound.add(btnOpen);
		btnPlay = new JButton("Play");
		btnPlay.setBounds(88, 71, 75, 23);
		pnlSound.add(btnPlay);
		btnStop = new JButton("Stop");
		btnStop.setBounds(169, 71, 75, 23);
		pnlSound.add(btnStop);
		frame.add(pnlSound);

		// The moving display outer panel
		JPanel pnlDisplay = new JPanel();
		Border b2 = BorderFactory.createTitledBorder("Display Thread");
		pnlDisplay.setBorder(b2);
		pnlDisplay.setBounds(12, 118, 222, 269);
		pnlDisplay.setLayout(null);

		// Add buttons and drawing panel to this panel
		btnDisplay = new JButton("Start Display");
		btnDisplay.setBounds(10, 226, 121, 23);
		pnlDisplay.add(btnDisplay);
		btnDStop = new JButton("Stop");
		btnDStop.setBounds(135, 226, 75, 23);
		pnlDisplay.add(btnDStop);
		pnlMove = new JPanel();
		pnlMove.setBounds(10, 19, 200, 200);
		Border b21 = BorderFactory.createLineBorder(Color.black);
		pnlMove.setBorder(b21);
		pnlDisplay.add(pnlMove);
		frame.add(pnlDisplay);

		// The moving graphics outer panel
		JPanel pnlTriangle = new JPanel();
		Border b3 = BorderFactory.createTitledBorder("Triangle Thread");
		pnlTriangle.setBorder(b3);
		pnlTriangle.setBounds(240, 118, 222, 269);
		pnlTriangle.setLayout(null);

		// Add buttons and drawing panel to this panel
		btnTriangle = new JButton("Start Rotate");
		btnTriangle.setBounds(10, 226, 121, 23);
		pnlTriangle.add(btnTriangle);
		btnTStop = new JButton("Stop");
		btnTStop.setBounds(135, 226, 75, 23);
		pnlTriangle.add(btnTStop);
		pnlRotate = new JPanel();
		pnlRotate.setBounds(10, 19, 200, 200);
		Border b31 = BorderFactory.createLineBorder(Color.black);
		pnlRotate.setBorder(b31);
		pnlTriangle.add(pnlRotate);
		// Add this to main window
		frame.add(pnlTriangle);

		// The game outer panel
		JPanel pnlCatchme = new JPanel();
		Border b4 = BorderFactory.createTitledBorder("Catch Me");
		pnlCatchme.setBorder(b4);
		pnlCatchme.setBounds(468, 12, 323, 375);
		pnlCatchme.setLayout(null);

		// Add controls to this panel
		JLabel lblSkill = new JLabel("Skill:");
		lblSkill.setBounds(26, 20, 50, 13);
		pnlCatchme.add(lblSkill);
		JLabel lblInfo = new JLabel("Hit Image with Mouse");
		lblInfo.setBounds(107, 13, 150, 13);
		pnlCatchme.add(lblInfo);
		JLabel lblHits = new JLabel("Hits:");
		lblHits.setBounds(240, 20, 50, 13);
		pnlCatchme.add(lblHits);
		String[] levels = {"Svår", "Medel", "Lätt"};
		cmbSkill = new JComboBox<String>(levels); // Need to be filled in with data
		cmbSkill.setSelectedIndex(2);
		cmbSkill.setBounds(19, 41, 61, 23);
		pnlCatchme.add(cmbSkill);
		btnGo = new JButton("GO");
		btnGo.setBounds(129, 41, 75, 23);
		pnlCatchme.add(btnGo);
		txtHits = new JTextArea("0"); // Needs to be updated
		txtHits.setBounds(233, 41, 71, 23);
		Border b40 = BorderFactory.createLineBorder(Color.black);
		txtHits.setBorder(b40);
		pnlCatchme.add(txtHits);
		pnlGame = new JPanel();
		pnlGame.setBounds(19, 71, 285, 283);
		Border b41 = BorderFactory.createLineBorder(Color.black);
		pnlGame.setBorder(b41);
		pnlCatchme.add(pnlGame);
		frame.add(pnlCatchme);
		
		BufferedImage myPicture;
		
		myPicture = ImageIO.read(new File("files\\face.png"));
		gamebutton = new JButton();
		gamebutton.setSize(30, 30);
		gamebutton.setIcon(new ImageIcon(myPicture));
		gamebutton.setBorderPainted(false);

		ButtonListener bl = new ButtonListener();

		btnDisplay.addActionListener(bl);
		btnDStop.addActionListener(bl);
		btnGo.addActionListener(bl);
		gamebutton.addActionListener(bl);
		
		enableButton(btnDStop, false);
		enableButton(btnTStop, false);
		enableButton(btnOpen, false);
		enableButton(btnStop, false);
		enableButton(btnPlay, false);
		enableButton(btnTriangle, false);
		
		frame.repaint();
	}
	/**
	 * Initializes panel for showing moving text.
	 * @param text
	 */
	public void initDisplay(String text) {
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	pnlMove.setLayout(null);
		    	if(label1!=null) {
					pnlMove.remove(label1);
					frame.repaint();
				}
				label1 = new JLabel(text);
				pnlMove.add(label1);
		    }
		});
		frame.repaint();
	}
	/**
	 * relocates the text in the display
	 */
	public void moveDisplay() {
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	label1.setBounds(r.nextInt(pnlMove.getWidth()-30), r.nextInt(pnlMove.getHeight()-50), 50, 70);
		    }
		});
		frame.repaint();
	}
/**
 * Changes the counter for the game.
 * @param gameHits
 */
	public void setHits(int gameHits) {
		txtHits.setText(String.valueOf(gameHits));
	}
/**
 * initializes the game panel.
 */
	public void initializeGame() {
		pnlGame.setLayout(null);
		pnlGame.add(gamebutton);
		MouseListen ml = new MouseListen();
		pnlGame.addMouseListener(ml);
		gamebutton.setVisible(true);
		frame.repaint();
	}
/**
 * relocates the game piece
 */
	public void setGamePiece() {
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	gamebutton.setLocation(r.nextInt(pnlGame.getWidth() - 30), r.nextInt(pnlGame.getHeight() - 30));
		    	frame.repaint();
		    }
		});
		frame.repaint();
	}
/**
 * ends the game
 * @param text output to user
 */
	public void endGame(String text) {
		gamebutton.setVisible(false);
		enableButton(btnGo, true);
		frame.repaint();
		JOptionPane.showMessageDialog(null, text);
		pnlGame.addMouseListener(null);
	}
/**
 * Sets button enabled
 * @param button to change
 * @param b enable or dissable
 */
	public void enableButton(JButton button, Boolean b) {
		button.setEnabled(b);
	}
/**
 * listener for game panel, recording misses
 * @author Jens ANdreassen
 *
 */
	private class MouseListen implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == pnlGame) {
				cont.gameClick(false);
			}
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
		@Override
		public void mousePressed(MouseEvent e) {
		}
		@Override
		public void mouseReleased(MouseEvent e) {
		}
	}
/**
 * Listener fr buttons
 * @author Jens ANdreassen
 *
 */
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnDisplay) {
				cont.task1(true);
				enableButton(btnDisplay, false);
				enableButton(btnDStop, true);
			} else if (e.getSource() == btnDStop) {
				cont.task1(false);
				enableButton(btnDisplay, true);
				enableButton(btnDStop, false);
			} else if (e.getSource() == btnGo) {
				cont.task2(cmbSkill.getSelectedIndex());
				enableButton(btnGo, false);
			} else if (e.getSource() == gamebutton) {
				cont.gameClick(true);
			} 
		}
	}

}
