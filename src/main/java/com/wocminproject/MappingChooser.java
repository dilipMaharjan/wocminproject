package com.wocminproject;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.swing.filechooser.*;
import java.util.*;

public class MappingChooser extends JFrame implements ActionListener {

	private JButton imageButton;
	private JTextField field;
	private JButton uploadButton;
	private JPanel panel, panel2, topPanel;
	private JLabel info, info2, label;
	MappingFolderChosenListener directoryChosenListener;

	private File folder;
	private File[] listFiles;

	/**
	private MappingFolderChosenListener directoryChosenListener;
	**/
	public MappingChooser() {

		Color lightBlue = new Color(40, 151, 207);

		topPanel = new JPanel();
		info = new JLabel("Choose a folder that contains your alphabet");
		info.setFont(new Font(null, Font.PLAIN, 14));
		info.setForeground(Color.white);
		topPanel.setBackground(lightBlue);
		topPanel.add(info, BorderLayout.NORTH);


		panel = new JPanel();
		panel.setBackground(Color.white);

		label = new JLabel("Each image's name should be the letter that it corresponds to. For example, the image of the letter A could be A.png");
		label.setFont(new Font(null, Font.PLAIN, 14));

		field = new JTextField(45);
		field.setFont(new Font(null, Font.PLAIN, 14));
		field.setEditable(false);

		imageButton = new JButton("Select a Directory");
		imageButton.setFont(new Font(null, Font.PLAIN, 14));
		imageButton.addActionListener(this);


		panel.add(imageButton);
		panel.add(field);
		panel.add(label, BorderLayout.SOUTH);

		panel2 = new JPanel();
		panel2.setLayout(new FlowLayout(FlowLayout.RIGHT));

		uploadButton = new JButton("Upload");
		uploadButton.setFont(new Font(null, Font.PLAIN, 14));
		uploadButton.setEnabled(false);
		uploadButton.addActionListener(this);

		info2 = new JLabel("Just hit upload!");
		info2.setVisible(false);

		panel2.add(info2);
		panel2.add(uploadButton, BorderLayout.SOUTH);

		add(topPanel, BorderLayout.NORTH);
		add(panel, BorderLayout.CENTER);
		add(panel2, BorderLayout.SOUTH);


		setSize(800, 350);
		setLocation(250, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Transcribe Text");
		setVisible(true);

	}

	public void setMappingFolderChosenListener(MappingFolderChosenListener listener)
	{
		directoryChosenListener = listener;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == imageButton) {

			JFileChooser newChooser = new JFileChooser();

			newChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			//newChooser.setAcceptAllFileFilterUsed(false);

			if(newChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				uploadButton.setEnabled(true);
				info2.setVisible(true);

				folder = new File(newChooser.getSelectedFile().getPath());
				field.setText(newChooser.getSelectedFile().getPath());
			}


		}

		if (e.getSource() == uploadButton) {
			info2.setText("Uploading and processing your letter images...");
			if (directoryChosenListener != null) {
				directoryChosenListener.mappingFolderChosen(folder);
			}
			//finishButton.setEnabled(true);

		}

		/**
		if (e.getSource() == finishButton) {
			switch (JOptionPane.showConfirmDialog(new JFrame(), "Are you sure?")) {
				case 0: // Yes
					JOptionPane.showMessageDialog(new JFrame(), "Finished");
					//setVisible(false);
					//new ImageChooser();
					break;
				case 1: // No
					break;
				case 2: // Cancel
					break;
			}
		}

		**/
	}
}
