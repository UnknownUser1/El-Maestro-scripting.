package org.parabot.el.maestro.elprayer.ui;

/**
 * Created by Bautista on 2/13/14.
 */

import org.parabot.el.maestro.elprayer.data.Variables;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Gui() {
		setIconImage(Toolkit
				.getDefaultToolkit()
				.getImage(
						Gui.class
								.getResource("/org/parabot/core/ui/images/category/prayer.png")));
		setResizable(false);
		setTitle("ElPrayer");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 216, 221);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel boneLabel = new JLabel("Bone Type: ");
		boneLabel.setFont(new Font("Monotype Corsiva", Font.PLAIN, 16));
		boneLabel.setBounds(76, 11, 79, 14);
		contentPane.add(boneLabel);

		final JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Monotype Corsiva", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[]{
				"Bones", "Big Bones", "Dragon Bones", "Dagannoth Bones",
				"Frost Dragon Bones"}));
		comboBox.setBounds(35, 36, 146, 20);
		contentPane.add(comboBox);

		JLabel lblLocation = new JLabel("Location: ");
		lblLocation.setFont(new Font("Monotype Corsiva", Font.PLAIN, 16));
		lblLocation.setBounds(76, 85, 69, 14);
		contentPane.add(lblLocation);

		final JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setEnabled(false);
		comboBox_1.setModel(new DefaultComboBoxModel(new String[]{
				"Premium Area", "Edgeville"}));
		comboBox_1.setFont(new Font("Monotype Corsiva", Font.PLAIN, 14));
		comboBox_1.setBounds(48, 110, 121, 20);
		contentPane.add(comboBox_1);

		JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Variables.setBoneIndex(comboBox.getSelectedIndex());
				Variables.setBoneType(comboBox.getSelectedItem().toString());
				Variables.setLocation(comboBox_1.getSelectedItem().toString());
				dispose();
			}
		});
		startButton.setFont(new Font("Monotype Corsiva", Font.PLAIN, 16));
		startButton.setBounds(0, 170, 211, 23);
		contentPane.add(startButton);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
