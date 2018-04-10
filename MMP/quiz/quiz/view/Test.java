package quiz.view;
import java.awt.event.*;
import javax.swing.*;

import quiz.Controller;

public class Test {
	
	public static void assessMode() {
		
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		panel.setLayout(null);
		JPasswordField passwordField = new JPasswordField();
		JButton button = new JButton("Submit");
		JLabel label = new JLabel("");
		JLabel label2 = new JLabel("Enter assessor password");
		JLabel label3 = new JLabel("");
		frame.setAlwaysOnTop(true);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String get = "r";
				@SuppressWarnings("deprecation")
				String test = passwordField.getText();

				if (get.equals(test)) {
					label.setText("Confirmed");
					label3.setText("");	
					Timer timer = new Timer(1000, new ActionListener() {
	                    @Override
	                    public void actionPerformed(ActionEvent e) {
	                        frame.dispose();
	                    }
	                });
	                timer.start();
				} else {
					label3.setText("Invalid password");
					label.setText("");
					passwordField.setText("");
				}
			}
		});

		panel.add(passwordField);
		passwordField.setBounds(5, 30, 70, 20);
		panel.add(button);
		button.setBounds(80, 30, 75, 20);
		panel.add(label);
		label.setBounds(50, 55, 100, 20);
		panel.add(label2);
		label2.setBounds(7, 5, 500, 20);
		panel.add(label3);
		label3.setBounds(30, 55, 100, 20);
		frame.setSize(275, 200);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}
				
	}