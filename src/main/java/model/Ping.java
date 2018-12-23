package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;

/**
 * @author LoganDuck
 * @version 1
 */
public class Ping {
	private static JFrame frame;
	
	private static JButton startButton;
	private static JButton stopButton;
	private static JPanel buttonPanel;
	
	private static JLabel pingCountLabel;
	private static JPanel pingPanel;
	
	private static JLabel statusLabel;
	private static JFXPanel loadingFXPanel;
	
	private static int timerCount = 0;
	
	private static Color royalblue = new Color(65, 105, 225);
	private static Color darkblue = new Color(50, 95, 140);
	
	public Ping() {
		frame = new JFrame("Ping");
		initFrame();
		
		startButton = initButton("Start", 2);
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				startButton.setEnabled(false);
				
				Scene scene = Transition.createScene();
				loadingFXPanel.setScene(scene);

				statusLabel = new JLabel("Loading...");
				statusLabel.setFont(new Font("Normal", Font.BOLD, 12));
				statusLabel.setForeground(royalblue);
				statusLabel.setHorizontalAlignment(SwingUtilities.CENTER);
				statusLabel.setBounds(0, 120, 318, 14);				
				frame.add(statusLabel);
				frame.repaint();
				
				pingPanel.add(pingCountLabel);
				Timer timer = new Timer(0, new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (timerCount < 100) {
							pingCountLabel.setText(String.valueOf(++timerCount) + " of 100");
						} else {
							loadingFXPanel.setVisible(false);
							statusLabel.setFont(new Font("Cambrira", Font.BOLD, 10));
							statusLabel.setText("Ping tests complete. \"PingTest.txt\" saved to desktop.");	
						}
					}
				});
				timer.setDelay(1000);
				timer.start();
				
				new ExecuteTerminal();
			}
		});
		
		stopButton = initButton("Stop", 41);
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.setBounds(10, 10, 92, 78);
		buttonPanel.add(startButton);
		buttonPanel.add(stopButton);
		frame.add(buttonPanel);
		
		pingCountLabel = new JLabel("0 of 100");
		pingCountLabel.setFont(new Font("Cambria", Font.BOLD, 28));
		pingCountLabel.setForeground(royalblue);
		pingCountLabel.setBounds(10, 22, 156, 34);
		
		pingPanel = new JPanel();
		pingPanel.setLayout(null);
		pingPanel.setBackground(Color.WHITE);
		pingPanel.setBounds(110, 10, 200, 58);
		frame.add(pingPanel);
		
		loadingFXPanel = new Transition();
		loadingFXPanel.setBounds(10, 90, 300, 20);
		frame.add(loadingFXPanel);

		frame.setVisible(true);
	}
	
	public void initFrame() {
		frame.setLayout(null);
		frame.setSize(new Dimension(318, 200));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(Color.WHITE);
	}
	
	public JButton initButton(String title, int yPos) {
		final JButton button = new JButton(title);
		button.setFont(new Font("Normal", Font.BOLD, 20));
		button.setBackground(royalblue);
		button.setForeground(Color.WHITE);
		button.setOpaque(true);
		button.setBorderPainted(false);
		button.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (button.getModel().isPressed()) {
					button.setBackground(darkblue);
				} else {
					button.setBackground(royalblue);
				}
			}
		});
		button.setBounds(0, yPos, 92, 34);
		return button;
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Ping();
			}
		});
	}
}