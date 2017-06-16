package com.wocminproject;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.swing.filechooser.*;

//	Main Frame, includes
// 	> Image Select Button
//  > Text Field to display file path
//	> Upload Button
public class ImageChooser extends JFrame implements ActionListener {

	private JLabel info, info2;
	private JButton uploadButton, imageButton;
	private JPanel topPanel, panel1, panel2; // panel1 - image selection // panel2 - upload // panel3 - source alphabet
	private JTextField field; // field - image selection // field2 - source alphabet selection
	ImageChosenListener imageChosenListener;

	File file = new File("");
	ImageIcon image = new ImageIcon();
	String lastPath;
	JFrame confirmFrame;

	public ImageChooser() {



		Color lightBlue = new Color(40, 151, 207);

		topPanel = new JPanel(); // upload button
		topPanel.setBackground(lightBlue);

		info = new JLabel("Choose an image that contains the text you want to transcribe");
		info.setFont(new Font(null, Font.PLAIN, 18));
		info.setForeground(Color.white);
		topPanel.add(info);



		panel1 = new JPanel(); // image button and text field
		panel1.setBackground(Color.white);

		imageButton = new JButton("Choose an image");
		imageButton.setFont(new Font(null, Font.PLAIN, 14));
		imageButton.addActionListener(this);

		field = new JTextField(45);
		field.setFont(new Font(null, Font.PLAIN, 14));
		field.setEditable(false); // Ensures that users will only attempt to upload viable images

		panel1.add(imageButton, BorderLayout.NORTH);
		panel1.add(field, BorderLayout.CENTER);



		panel2 = new JPanel(); // Finish button
		panel2.setLayout(new FlowLayout(FlowLayout.RIGHT));

		info2 = new JLabel("Just hit upload!");

		uploadButton = new JButton("Upload");
		uploadButton.setEnabled(false); // Disables upload button until a readable image is selected
		uploadButton.setFont(new Font(null, Font.PLAIN, 14));
		uploadButton.addActionListener(this);

		panel2.add(info2);
		info2.setVisible(false);
		panel2.add(uploadButton, BorderLayout.SOUTH);

		add(topPanel, BorderLayout.NORTH);
		add(panel1, BorderLayout.CENTER);
		add(panel2, BorderLayout.SOUTH);

		// Window specifications
		setTitle("Transcribe Text");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 350);
		setResizable(false);
		setLocation(250, 250);
		setVisible(true);

	}

	public void setImageChosenListener(ImageChosenListener listener)
	{
		imageChosenListener = listener;
	}

	// Action Listener for both image select and upload buttons
	public void actionPerformed(ActionEvent e) {
		confirmFrame = new JFrame();
		confirmFrame.setAlwaysOnTop(true);
		if (e.getSource() == imageButton) {

			JFileChooser newChooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Images (*.jpeg, *.jpg, *.png)", "jpg", "png", "jpeg");
			newChooser.setFileFilter(filter);
			int selectedFile = newChooser.showOpenDialog(null);
			newChooser.setDialogTitle("File Selector");

			if (selectedFile == JFileChooser.APPROVE_OPTION) {
				file = newChooser.getSelectedFile();

				// Ensure file is an image in the correct format
				if (file.getPath().toLowerCase().endsWith(".jpg") || file.getPath().toLowerCase().endsWith(".png") || file.getPath().toLowerCase().endsWith(".jpeg")) {
					image = new ImageIcon(file.getPath());
					field.setText(file.getPath());
					uploadButton.setEnabled(true);
					info2.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(new JFrame(), "Hmm, this doesn't look like an image. Try selecting"
							+ " another file.");
					uploadButton.setEnabled(false);
					info2.setVisible(false);
					return;
				}
				// Checks to make sure you don't accidentally upload the same image twice in a row
				/* if (file.getPath().equals(lastPath)) {
					switch(JOptionPane.showConfirmDialog(confirmFrame, "It looks like you've just uploaded this image. "
							+ "Would you like to upload it again?")) {
					case 0: // Yes
						uploadButton.doClick();
						break;
					case 1: // No
						field.setText("");
						break;
					case 2: // Cancel
						uploadButton.setEnabled(false);
						field.setText("");
						break;
					}
				}
				lastPath = file.getPath();*/
			}

		}
		// Upload image to the server
		if (e.getSource() == uploadButton) {

				JFrame newFrame = new JFrame();
				newFrame.setSize(200, 200);
				newFrame.setAlwaysOnTop(true);
				JPanel newPanel = new JPanel();

				image = new ImageIcon(file.getPath());

				JLabel newLabel = new JLabel(image);
				newPanel.add(newLabel);
				newFrame.add(newPanel);

				newFrame.pack();
				newFrame.setVisible(true);

				// Gives the user a display of the image and asks them to confirm that they wish to upload the right one
				switch (JOptionPane.showConfirmDialog(confirmFrame, "Is this the right image?")) {
					case 0: // Yes
						info2.setText("Uploading and processing your image...");
						if (imageChosenListener != null)
						{	
							imageChosenListener.imageChosen(file);
						}

						imageButton.setText("Choose an image");
						//field.setText("");
						uploadButton.setEnabled(false);
						info2.setText("Uploading your image now...");
						newFrame.setVisible(false);
						//finishButton.setEnabled(true);
						break;
					case 1: // No
						imageButton.setText("Choose an image");
						newFrame.setVisible(false);
						field.setText("");
						uploadButton.setEnabled(false);
						info2.setVisible(false);
						break;
					case 2: // Cancel
						newFrame.setVisible(false);
						field.setText("");
						uploadButton.setEnabled(false);
						info2.setVisible(false);
						break;
				}


			}

					/* if (e.getSource() == finishButton) {

						// upload to server

						switch(JOptionPane.showConfirmDialog(new JFrame(), "Are you sure?")) {
							case 0: // Yes
								JOptionPane.showMessageDialog(new JFrame(), "Finished");
								break;
							case 1: // No
								break;
							case 2: // Cancel
								break;
				}
		} */
	}
}

/** Potential Add-Ons
 *
 *  > User is told if they are about to upload the same image twice
 *  > User gets a preview of the image and are asked to confirm it
 *  > Feature to where a user can select multiple images at a time
 *  > User can resize the image previews
 *
 **/
