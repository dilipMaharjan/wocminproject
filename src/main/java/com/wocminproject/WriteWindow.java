package com.wocminproject;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.swing.filechooser.*;
import java.util.*;

public class WriteWindow extends JFrame {

	private JButton fileButton, selectButton;
	private JTextField field;
	private JPanel topPanel, panel1, panel2;
	private JLabel info, info2;
	private String fileToWrite, location;

	// Call new WriteWindow with an argument of the string to be written to the file
	public WriteWindow(String file) {

		fileToWrite = file;

		Color lightBlue = new Color(40, 151, 207);

		topPanel = new JPanel();
		topPanel.setBackground(lightBlue);

		info = new JLabel("Choose where to save the transcribed text");
		info.setFont(new Font(null, Font.PLAIN, 14));
		info.setForeground(Color.white);
		topPanel.add(info);

		panel1 = new JPanel();
		panel1.setBackground(Color.white);

		selectButton = new JButton("Save file as");
		selectButton.setFont(new Font(null, Font.PLAIN, 14));
		selectButton.addActionListener(new ButtonListener());

		field = new JTextField(30);
		field.setFont(new Font(null, Font.PLAIN, 14));
		field.setEditable(false);

		panel1.add(selectButton);
		panel1.add(field);

		panel2 = new JPanel();
		panel2.setLayout(new FlowLayout(FlowLayout.RIGHT));

		info2 = new JLabel("Just hit Save!");
		info2.setVisible(false);

		fileButton = new JButton("Save");
		fileButton.setFont(new Font(null, Font.PLAIN, 14));
		fileButton.setEnabled(false);
		fileButton.addActionListener(new ButtonListener());

		panel2.add(info2);
		panel2.add(fileButton, BorderLayout.SOUTH);

		add(topPanel, BorderLayout.NORTH);
		add(panel1, BorderLayout.CENTER);
		add(panel2, BorderLayout.SOUTH);

		setSize(800, 350);
		setLocation(250, 250);
		setResizable(false);
		setTitle("Save As");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if(e.getSource() == selectButton) {
				JFileChooser newChooser = new JFileChooser();
				newChooser.setCurrentDirectory(null);

				int saveFile = newChooser.showSaveDialog(null);
				if(saveFile == JFileChooser.APPROVE_OPTION) {

						location = newChooser.getSelectedFile().getPath().trim();
						if(!location.endsWith(".txt")){
							location += ".txt";
						}
				}
				field.setText(location);
				fileButton.setEnabled(true);
				info2.setVisible(true);
			}

			if (e.getSource() == fileButton) {


				switch(JOptionPane.showConfirmDialog(new JFrame(), "Are you sure?")) {
					case 0:
						try {
							FileWriter newWriter = new FileWriter(location);
							PrintWriter pWriter = new PrintWriter(newWriter);

							pWriter.println(fileToWrite);
							pWriter.close();
						} catch (IOException error1) {}
						System.exit(0);
						break;
					case 1:
						break;
					case 2:
						break;
				}
			}
		}
	}
}
