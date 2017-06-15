package com.wocminproject;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.swing.filechooser.*;

public class MappingChooser extends JFrame implements ActionListener {

	private JButton imageButton;
	private JTextField field;
	private JButton uploadButton;
	private JPanel panel;
	private File folder;
	private File[] listFiles;

	private MappingFolderChosenListener directoryChosenListener;

	public MappingChooser() {
		setSize(800, 250);
		setLocation(300, 300);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setResizable(false);
		setTitle("Transcribe Text");


		imageButton = new JButton("Select a Directory");
		field = new JTextField(45);
		uploadButton = new JButton("Upload");
		uploadButton.setEnabled(false);

		imageButton.addActionListener(this);
		uploadButton.addActionListener(this);

		panel = new JPanel();
		panel.add(imageButton, BorderLayout.NORTH);
		panel.add(field, BorderLayout.NORTH);
		panel.add(uploadButton, BorderLayout.CENTER);

		add(panel);

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

				folder = new File(newChooser.getSelectedFile().getPath());
				field.setText(newChooser.getSelectedFile().getPath());
			}


		}

		if (e.getSource() == uploadButton) {
			if (directoryChosenListener != null) {
				directoryChosenListener.mappingFolderChosen(folder);
			}
		}

	}
}
