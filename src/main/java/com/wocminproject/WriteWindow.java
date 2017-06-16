package com.wocminproject;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.swing.filechooser.*;
import java.util.*;

public class WriteWindow extends JFrame {

	private JTextField fileNameOutput;
	private JButton fileButton;
	private JPanel panel1, panel2, panel4;
	private JLabel label;
	private String fileName;
	private String fileToWrite;
	private ButtonListener b1;

	// Call new WriteWindow with an argument of the string to be written to the file
	public WriteWindow(String file) {

		fileToWrite = file;

		panel1 = new JPanel();
		panel2 = new JPanel();
		panel4 = new JPanel();




		fileNameOutput = new JTextField(25);
		fileButton = new JButton("Enter");
		label = new JLabel("Enter a file name");

		b1 = new ButtonListener();
		fileButton.addActionListener(b1);

		panel1.add(fileNameOutput, BorderLayout.NORTH);
		panel1.add(label, BorderLayout.NORTH);
		panel2.add(fileButton);


		add(panel1, BorderLayout.NORTH);
		add(panel2, BorderLayout.CENTER);
		add(panel4, BorderLayout.SOUTH);

		setSize(800, 250);
		setLocation(300, 300);
		setTitle("Save Window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == fileButton) {
				fileName = fileNameOutput.getText().trim();

				// allows user to add extension or not
				if (!fileName.endsWith(".txt")) {
					fileName += ".txt";
				}

				fileNameOutput.setEditable(false);


				TextWriter object1 = new TextWriter();
				object1.writeFile(fileName, fileToWrite);
			}

		}
	}

	public class TextWriter {

		public TextWriter() {

		}

		// Takes in a file name and an array of Strings (each string is one line) and writes them to a new file named by the user
		public void writeFile(String fileName, String a) {
			File newFile = new File(fileName);

			try {
				FileWriter newWriter = new FileWriter(newFile);
				PrintWriter pWriter = new PrintWriter(newWriter);

				pWriter.println(a);
				pWriter.close();
			}
			catch (IOException e) {

			}

		}

	}

}
