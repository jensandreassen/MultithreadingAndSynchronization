package assignment3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * The GUI for assignment 3
 */
public class GUISemaphore {
	/**
	 * These are the components you need to handle. You have to add listeners and/or
	 * code Static controls are defined inline
	 */
	private JFrame frame; // The Main window
	private JProgressBar bufferStatus; // The progressbar, showing content in buffer

	// Data for Producer Scan
	private JButton btnStartS; // Button start Scan
	private JButton btnStopS; // Button stop Scan
	private JLabel lblStatusS; // Status Scan
	// DAta for producer Arla
	private JButton btnStartA; // Button start Arla
	private JButton btnStopA; // Button stop Arla
	private JLabel lblStatusA; // Status Arla
	// Data for producer AxFood
	private JButton btnStartX; // Button start AxFood
	private JButton btnStopX; // Button stop AxFood
	private JLabel lblStatusX; // Status AxFood

	// Data for consumer ICA
	private JLabel lblIcaItems; // Ica limits
	private JLabel lblIcaWeight;
	private JLabel lblIcaVolume;
	private JLabel lblIcaStatus; // load status
	private JTextArea lstIca; // The cargo list
	private JButton btnIcaStart; // The buttons
	private JButton btnIcaStop;
	private JCheckBox chkIcaCont; // Continue checkbox
	// Data for consumer COOP
	private JLabel lblCoopItems;
	private JLabel lblCoopWeight;
	private JLabel lblCoopVolume;
	private JLabel lblCoopStatus; // load status
	private JTextArea lstCoop; // The cargo list
	private JButton btnCoopStart; // The buttons
	private JButton btnCoopStop;
	private JCheckBox chkCoopCont; // Continue checkbox
	// Data for consumer CITY GROSS
	private JLabel lblCGItems;
	private JLabel lblCGWeight;
	private JLabel lblCGVolume;
	private JLabel lblCGStatus; // load status
	private JTextArea lstCG; // The cargo list
	private JButton btnCGStart; // The buttons
	private JButton btnCGStop;
	private JCheckBox chkCGCont; // Continue checkbox

	private Storage storage;

	private Factory scanFactory;
	private Factory arlaFactory;
	private Factory axFactory;

	private Truck icaTruck;
	private Truck coopTruck;
	private Truck cgrTruck;

	/**
	 * Constructor, creates storage with parameter capacity
	 */
	public GUISemaphore(int capacity) {
		bufferStatus = new JProgressBar();
		this.storage = new Storage(capacity, bufferStatus);
	}

	/**
	 * Starts the application
	 */
	public void Start() {
		frame = new JFrame();
		frame.setBounds(0, 0, 730, 526);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setTitle("Food Supply System");
		InitializeGUI(); // Fill in components
		frame.setVisible(true);
		frame.setResizable(false); // Prevent user from change size
		frame.setLocationRelativeTo(null); // Start middle screen
	}

	/**
	 * Sets up the GUI with components
	 */
	private void InitializeGUI() {
		// First create the three main panels
		JPanel pnlBuffer = new JPanel();
		pnlBuffer.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Storage"));
		pnlBuffer.setBounds(13, 403, 693, 82);
		pnlBuffer.setLayout(null);
		// Then create the progressbar, only component in buffer panel
		// bufferStatus = new JProgressBar();
		bufferStatus.setBounds(155, 37, 500, 23);
		bufferStatus.setBorder(BorderFactory.createLineBorder(Color.black));
		bufferStatus.setForeground(Color.GREEN);
		pnlBuffer.add(bufferStatus);
		JLabel lblmax = new JLabel("Max capacity (items):");
		lblmax.setBounds(10, 42, 126, 13);
		pnlBuffer.add(lblmax);
		frame.add(pnlBuffer);

		JPanel pnlProd = new JPanel();
		pnlProd.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Producers"));
		pnlProd.setBounds(13, 13, 229, 379);
		pnlProd.setLayout(null);

		JPanel pnlCons = new JPanel();
		pnlCons.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Consumers"));
		pnlCons.setBounds(266, 13, 440, 379);
		pnlCons.setLayout(null);

		// Now add the three panels to producer panel
		JPanel pnlScan = new JPanel();
		pnlScan.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Producer: Scan"));
		pnlScan.setBounds(6, 19, 217, 100);
		pnlScan.setLayout(null);

		// Content Scan panel
		btnStartS = new JButton("Start Producing");
		btnStartS.setBounds(10, 59, 125, 23);
		pnlScan.add(btnStartS);
		btnStopS = new JButton("Stop");
		btnStopS.setBounds(140, 59, 65, 23);
		pnlScan.add(btnStopS);
		lblStatusS = new JLabel("Staus Idle/Stop/Producing");
		lblStatusS.setBounds(10, 31, 200, 13);
		pnlScan.add(lblStatusS);
		// Add Scan panel to producers
		pnlProd.add(pnlScan);

		// The Arla panel
		JPanel pnlArla = new JPanel();
		pnlArla.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Producer: Arla"));
		pnlArla.setBounds(6, 139, 217, 100);
		pnlArla.setLayout(null);

		// Content Arla panel
		btnStartA = new JButton("Start Producing");
		btnStartA.setBounds(10, 59, 125, 23);
		pnlArla.add(btnStartA);
		btnStopA = new JButton("Stop");
		btnStopA.setBounds(140, 59, 65, 23);
		pnlArla.add(btnStopA);
		lblStatusA = new JLabel("Staus Idle/Stop/Producing");
		lblStatusA.setBounds(10, 31, 200, 13);
		pnlArla.add(lblStatusA);
		// Add Arla panel to producers
		pnlProd.add(pnlArla);

		// The AxFood Panel
		JPanel pnlAxfood = new JPanel();
		pnlAxfood.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Producer: AxFood"));
		pnlAxfood.setBounds(6, 262, 217, 100);
		pnlAxfood.setLayout(null);

		// Content AxFood Panel
		btnStartX = new JButton("Start Producing");
		btnStartX.setBounds(10, 59, 125, 23);
		pnlAxfood.add(btnStartX);
		btnStopX = new JButton("Stop");
		btnStopX.setBounds(140, 59, 65, 23);
		pnlAxfood.add(btnStopX);
		lblStatusX = new JLabel("Staus Idle/Stop/Producing");
		lblStatusX.setBounds(10, 31, 200, 13);
		pnlAxfood.add(lblStatusX);
		// Add Axfood panel to producers
		pnlProd.add(pnlAxfood);
		// Producer panel done, add to frame
		frame.add(pnlProd);

		// Next, add the three panels to Consumer panel
		JPanel pnlICA = new JPanel();
		pnlICA.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Consumer: ICA"));
		pnlICA.setBounds(19, 19, 415, 100);
		pnlICA.setLayout(null);

		// Content ICA panel
		// First the limits panel
		JPanel pnlLim = new JPanel();
		pnlLim.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Package Limits"));
		pnlLim.setBounds(6, 19, 107, 75);
		pnlLim.setLayout(null);
		JLabel lblItems = new JLabel("Items:");
		lblItems.setBounds(7, 20, 50, 13);
		pnlLim.add(lblItems);
		JLabel lblWeight = new JLabel("Weight:");
		lblWeight.setBounds(7, 35, 50, 13);
		pnlLim.add(lblWeight);
		JLabel lblVolume = new JLabel("Volume:");
		lblVolume.setBounds(7, 50, 50, 13);
		pnlLim.add(lblVolume);
		lblIcaItems = new JLabel("Data");
		lblIcaItems.setBounds(60, 20, 47, 13);
		pnlLim.add(lblIcaItems);
		lblIcaWeight = new JLabel("Data");
		lblIcaWeight.setBounds(60, 35, 47, 13);
		pnlLim.add(lblIcaWeight);
		lblIcaVolume = new JLabel("Data");
		lblIcaVolume.setBounds(60, 50, 47, 13);
		pnlLim.add(lblIcaVolume);
		pnlICA.add(pnlLim);
		// Then rest of controls
		lstIca = new JTextArea();
		lstIca.setEditable(false);
		JScrollPane spane = new JScrollPane(lstIca);
		spane.setBounds(307, 16, 102, 69);
		spane.setBorder(BorderFactory.createLineBorder(Color.black));
		pnlICA.add(spane);
		btnIcaStart = new JButton("Start Loading");
		btnIcaStart.setBounds(118, 64, 120, 23);
		pnlICA.add(btnIcaStart);
		btnIcaStop = new JButton("Stop");
		btnIcaStop.setBounds(240, 64, 60, 23);
		pnlICA.add(btnIcaStop);
		lblIcaStatus = new JLabel("Ica stop status here");
		lblIcaStatus.setBounds(118, 16, 150, 23);
		pnlICA.add(lblIcaStatus);
		chkIcaCont = new JCheckBox("Continue load");
		chkIcaCont.setBounds(118, 39, 130, 17);
		pnlICA.add(chkIcaCont);
		// All done, add to consumers panel
		pnlCons.add(pnlICA);

		JPanel pnlCOOP = new JPanel();
		pnlCOOP.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Consumer: COOP"));
		pnlCOOP.setBounds(19, 139, 415, 100);
		pnlCOOP.setLayout(null);
		pnlCons.add(pnlCOOP);

		// Content COOP panel
		// First the limits panel
		JPanel pnlLimC = new JPanel();
		pnlLimC.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Package Limits"));
		pnlLimC.setBounds(6, 19, 107, 75);
		pnlLimC.setLayout(null);
		JLabel lblItemsC = new JLabel("Items:");
		lblItemsC.setBounds(7, 20, 50, 13);
		pnlLimC.add(lblItemsC);
		JLabel lblWeightC = new JLabel("Weight:");
		lblWeightC.setBounds(7, 35, 50, 13);
		pnlLimC.add(lblWeightC);
		JLabel lblVolumeC = new JLabel("Volume:");
		lblVolumeC.setBounds(7, 50, 50, 13);
		pnlLimC.add(lblVolumeC);
		lblCoopItems = new JLabel("Data");
		lblCoopItems.setBounds(60, 20, 47, 13);
		pnlLimC.add(lblCoopItems);
		lblCoopWeight = new JLabel("Data");
		lblCoopWeight.setBounds(60, 35, 47, 13);
		pnlLimC.add(lblCoopWeight);
		lblCoopVolume = new JLabel("Data");
		lblCoopVolume.setBounds(60, 50, 47, 13);
		pnlLimC.add(lblCoopVolume);
		pnlCOOP.add(pnlLimC);
		// Then rest of controls
		lstCoop = new JTextArea();
		lstCoop.setEditable(false);
		JScrollPane spaneC = new JScrollPane(lstCoop);
		spaneC.setBounds(307, 16, 102, 69);
		spaneC.setBorder(BorderFactory.createLineBorder(Color.black));
		pnlCOOP.add(spaneC);
		btnCoopStart = new JButton("Start Loading");
		btnCoopStart.setBounds(118, 64, 120, 23);
		pnlCOOP.add(btnCoopStart);
		btnCoopStop = new JButton("Stop");
		btnCoopStop.setBounds(240, 64, 60, 23);
		pnlCOOP.add(btnCoopStop);
		lblCoopStatus = new JLabel("Coop stop status here");
		lblCoopStatus.setBounds(118, 16, 150, 23);
		pnlCOOP.add(lblCoopStatus);
		chkCoopCont = new JCheckBox("Continue load");
		chkCoopCont.setBounds(118, 39, 130, 17);
		pnlCOOP.add(chkCoopCont);
		// All done, add to consumers panel
		pnlCons.add(pnlCOOP);

		JPanel pnlCG = new JPanel();
		pnlCG.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Consumer: CITY GROSS"));
		pnlCG.setBounds(19, 262, 415, 100);
		pnlCG.setLayout(null);
		pnlCons.add(pnlCG);

		// Content CITY GROSS panel
		// First the limits panel
		JPanel pnlLimG = new JPanel();
		pnlLimG.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Package Limits"));
		pnlLimG.setBounds(6, 19, 107, 75);
		pnlLimG.setLayout(null);
		JLabel lblItemsG = new JLabel("Items:");
		lblItemsG.setBounds(7, 20, 50, 13);
		pnlLimG.add(lblItemsG);
		JLabel lblWeightG = new JLabel("Weight:");
		lblWeightG.setBounds(7, 35, 50, 13);
		pnlLimG.add(lblWeightG);
		JLabel lblVolumeG = new JLabel("Volume:");
		lblVolumeG.setBounds(7, 50, 50, 13);
		pnlLimG.add(lblVolumeG);
		lblCGItems = new JLabel("Data");
		lblCGItems.setBounds(60, 20, 47, 13);
		pnlLimG.add(lblCGItems);
		lblCGWeight = new JLabel("Data");
		lblCGWeight.setBounds(60, 35, 47, 13);
		pnlLimG.add(lblCGWeight);
		lblCGVolume = new JLabel("Data");
		lblCGVolume.setBounds(60, 50, 47, 13);
		pnlLimG.add(lblCGVolume);
		pnlCG.add(pnlLimG);
		// Then rest of controls
		lstCG = new JTextArea();
		lstCG.setEditable(false);
		JScrollPane spaneG = new JScrollPane(lstCG);
		spaneG.setBounds(307, 16, 102, 69);
		spaneG.setBorder(BorderFactory.createLineBorder(Color.black));
		pnlCG.add(spaneG);
		btnCGStart = new JButton("Start Loading");
		btnCGStart.setBounds(118, 64, 120, 23);
		pnlCG.add(btnCGStart);
		btnCGStop = new JButton("Stop");
		btnCGStop.setBounds(240, 64, 60, 23);
		pnlCG.add(btnCGStop);
		lblCGStatus = new JLabel("CITY GROSS stop status here");
		lblCGStatus.setBounds(118, 16, 150, 23);
		pnlCG.add(lblCGStatus);
		chkCGCont = new JCheckBox("Continue load");
		chkCGCont.setBounds(118, 39, 130, 17);
		pnlCG.add(chkCGCont);
		// All done, add to consumers panel
		pnlCons.add(pnlCOOP);

		// Add consumer panel to frame
		frame.add(pnlCons);
		// Create and add buttonListener to buttons
		ButtonListener bl = new ButtonListener();
		btnStartS.addActionListener(bl); // Button start Scan
		btnStopS.addActionListener(bl); // Button stop Scan
		btnStartA.addActionListener(bl); // Button start Arla
		btnStopA.addActionListener(bl); // Button stop Arla
		btnStartX.addActionListener(bl); // Button start AxFood
		btnStopX.addActionListener(bl); // Button stop AxFood

		btnIcaStart.addActionListener(bl);
		btnIcaStop.addActionListener(bl);
		btnCoopStart.addActionListener(bl);
		btnCoopStop.addActionListener(bl);
		btnCGStart.addActionListener(bl);
		btnCGStop.addActionListener(bl);

		btnStopS.setEnabled(false);
		btnStopA.setEnabled(false);
		btnStopX.setEnabled(false);
		btnIcaStop.setEnabled(false);
		btnCoopStop.setEnabled(false);
		btnCGStop.setEnabled(false);
	}

	/**
	 * ButtonListener, starts threads and handles actions
	 * @author Jens Andreassen
	 *
	 */
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnStartS) {
				scanFactory = new Factory(lblStatusS, storage);
				scanFactory.start();
				btnStartS.setEnabled(false);
				btnStopS.setEnabled(true);
			} else if (e.getSource() == btnStopS) {
				scanFactory.setflag();
				btnStartS.setEnabled(true);
				btnStopS.setEnabled(false);
			} else if (e.getSource() == btnStartA) {
				arlaFactory = new Factory(lblStatusA, storage);
				arlaFactory.start();
				btnStartA.setEnabled(false);
				btnStopA.setEnabled(true);
			} else if (e.getSource() == btnStopA) {
				arlaFactory.setflag();
				btnStartA.setEnabled(true);
				btnStopA.setEnabled(false);
			} else if (e.getSource() == btnStartX) {
				axFactory = new Factory(lblStatusX, storage);
				axFactory.start();
				btnStartX.setEnabled(false);
				btnStopX.setEnabled(true);
			} else if (e.getSource() == btnStopX) {
				axFactory.setflag();
				btnStartX.setEnabled(true);
				btnStopX.setEnabled(false);
			} else if (e.getSource() == btnIcaStart) {
				icaTruck = new Truck(storage, lblIcaWeight, lblIcaVolume, lblIcaStatus, lblIcaItems, lstIca,
						chkIcaCont.isSelected());
				icaTruck.start();
				chkIcaCont.setEnabled(false);
				btnIcaStart.setEnabled(false);
				btnIcaStop.setEnabled(true);
			} else if (e.getSource() == btnIcaStop) {
				icaTruck.setflag();
				btnIcaStart.setEnabled(true);
				btnIcaStop.setEnabled(false);
				chkIcaCont.setEnabled(true);
				chkIcaCont.setSelected(false);
			} else if (e.getSource() == btnCoopStart) {
				coopTruck = new Truck(storage, lblCoopWeight, lblCoopVolume, lblCoopStatus, lblCoopItems, lstCoop,
						chkCoopCont.isSelected());
				coopTruck.start();
				chkCoopCont.setEnabled(false);
				btnCoopStart.setEnabled(false);
				btnCoopStop.setEnabled(true);
			} else if (e.getSource() == btnCoopStop) {
				coopTruck.setflag();
				btnCoopStart.setEnabled(true);
				btnCoopStop.setEnabled(false);
				chkCoopCont.setEnabled(true);
				chkCoopCont.setSelected(false);
			} else if (e.getSource() == btnCGStart) {
				cgrTruck = new Truck(storage, lblCGWeight, lblCGVolume, lblCGStatus, lblCGItems, lstCG,
						chkCGCont.isSelected());
				cgrTruck.start();
				chkCGCont.setEnabled(false);
				btnCGStart.setEnabled(false);
				btnCGStop.setEnabled(true);
			} else if (e.getSource() == btnCGStop) {
				cgrTruck.setflag();
				btnCGStart.setEnabled(true);
				btnCGStop.setEnabled(false);
				chkCGCont.setEnabled(true);
				chkCGCont.setSelected(false);
			}
		}
	}

	public static void main(String[] args) {
		new GUISemaphore(10).Start();
	}
}
