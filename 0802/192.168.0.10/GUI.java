import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.swing.JButton;
import javax.swing.JFrame;


public class GUI {

	private JFrame frame;
	private JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(200,80);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		
		button = new JButton("Blockchain TimeSync");
		frame.add(button);
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				myIP mip = new myIP("wlan0");
				String ip = mip.getMyIP();
				System.out.println("I am " + ip);

				String algorithm = "RSA"; // or RSA, DH, DSA etc.
				// Generate a 1024-bit Digital Signature Algorithm (DSA) key pair
				KeyPairGenerator keyGen = null;
				try {
					keyGen = KeyPairGenerator.getInstance(algorithm);
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				keyGen.initialize(512, new SecureRandom());
				KeyPair keypair = keyGen.genKeyPair();

				KeyPair keyPair2 = keyGen.generateKeyPair();

				KeyManager km = new KeyManager("/" + ip, keypair);
				km.setKeyFile("KeyFile");

				DBHelper db = new DBHelper();
				db.setDB();

				/*
				 * db.addtoFile(db.getBlockchainFile(),
				 * "1:1522070181958:null:000p+balQpGNwlxX5+q14YJGHE/WoVcEC2s5ylN7Zkc=");
				 */
				IpScanManager ism = new IpScanManager("192.168.0");
				ism.start();

				Blockchain block_chain = new Blockchain();
				block_chain.start();

				TimeManagement tm = new TimeManagement(km);

				BlockNetworkManager bnm = new BlockNetworkManager(block_chain, tm);
				bnm.start();

				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				PeerNetworkManager pnm = new PeerNetworkManager(block_chain, km, bnm,
						tm);
				pnm.start();

				try {
					Thread.sleep(30000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				while (true) {
					try {
						pnm.unicast("192.168.0.11");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						Thread.sleep(300000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		});
		
		
		
	}

}
