package org.monieo.monieoclient;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

public class UI {
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	public void Start() {
		
		initialize();
	}
	
	public UI() {
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 764, 485);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Version 0.1");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 414, 119, 21);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Advanced");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton.setBounds(649, 414, 89, 24);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("(address or something idk never watched it)");
		lblNewLabel_1.setBounds(10, 38, 461, 24);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel label = new JLabel("Address:");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(10, 3, 484, 24);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Balance:");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setBounds(10, 70, 216, 24);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("0.0");
		label_2.setBounds(10, 105, 216, 24);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("Transfer:");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_3.setBounds(10, 128, 484, 24);
		frame.getContentPane().add(label_3);
		
		textField = new JTextField();
		textField.setBounds(10, 179, 461, 52);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel label_4 = new JLabel("Address to transfer to:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_4.setBounds(10, 157, 484, 24);
		frame.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("Amount to transfer:");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_5.setBounds(180, 242, 137, 24);
		frame.getContentPane().add(label_5);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(180, 264, 137, 21);
		frame.getContentPane().add(textField_1);
		
		JButton btnTransfer = new JButton("Transfer");
		btnTransfer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnTransfer.setBounds(205, 296, 89, 24);
		frame.getContentPane().add(btnTransfer);
		
		JLabel lblMining = new JLabel("Mining:");
		lblMining.setHorizontalAlignment(SwingConstants.CENTER);
		lblMining.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblMining.setBounds(529, 3, 185, 24);
		frame.getContentPane().add(lblMining);
		
		final JButton btnNewButton_1 = new JButton();
		btnNewButton_1.setBackground(Color.GRAY);
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.setBounds(580, 71, 27, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		
		final JButton button = new JButton();
		button.setBackground(Color.GRAY);
		button.setEnabled(false);
		button.setBounds(628, 71, 27, 23);
		frame.getContentPane().add(button);
		
		final JToggleButton tglbtnNewToggleButton = new JToggleButton("");
		tglbtnNewToggleButton.setBounds(580, 71, 75, 23);
		tglbtnNewToggleButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				btnNewButton_1.setVisible(!tglbtnNewToggleButton.isSelected());
				button.setVisible(tglbtnNewToggleButton.isSelected());
				
				/*
				 * super cool code to start mining
				 */
			}
		});
		frame.getContentPane().add(tglbtnNewToggleButton);
		
		JLabel lblMiningEnabled = new JLabel("Mining enabled:");
		lblMiningEnabled.setHorizontalAlignment(SwingConstants.CENTER);
		lblMiningEnabled.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMiningEnabled.setBounds(529, 38, 185, 24);
		frame.getContentPane().add(lblMiningEnabled);
		
		JLabel label_6 = new JLabel("Off");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_6.setBounds(525, 70, 50, 24);
		frame.getContentPane().add(label_6);
		
		JLabel label_7 = new JLabel("On");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_7.setBounds(657, 70, 50, 24);
		frame.getContentPane().add(label_7);
		
		JLabel lblMiningInformation = new JLabel("Mining Statistics:");
		lblMiningInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblMiningInformation.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblMiningInformation.setBounds(529, 105, 185, 24);
		frame.getContentPane().add(lblMiningInformation);
		
		JLabel label_8 = new JLabel("Mining Live View:");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		label_8.setBounds(529, 267, 185, 24);
		frame.getContentPane().add(label_8);
		
		JLabel lblNewLabel_2 = new JLabel("(the hash or someone idk never watched it)");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(502, 296, 236, 24);
		frame.getContentPane().add(lblNewLabel_2);
	}
}