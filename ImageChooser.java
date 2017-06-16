import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.swing.filechooser.*;
import java.util.*;

//	Main Frame, includes
// 	> Image Select Button
//  > Text Field to display file path
//	> Upload Button
public class ImageChooser extends JFrame {
	
	private JButton uploadButton, imageButton, finishButton;
	private JPanel panel1, panel2, panel3; // panel1 - image selection // panel2 - upload // panel3 - source alphabet
	private JTextField field; // field - image selection // field2 - source alphabet selection
	private FileChooseListener f1;
	
	public ImageChooser() {
		
		uploadButton = new JButton("Upload");
		uploadButton.setEnabled(false); // Disables upload button until a readable image is selected
		
		finishButton = new JButton("Finish");
		finishButton.setEnabled(false);
		
		
		imageButton = new JButton("Choose an image");
		
		field = new JTextField(45);
		field.setEditable(false); // Ensures that users will only attempt to upload viable images
		
		// Create and add an ActionListener to buttons
		f1 = new FileChooseListener();
		imageButton.addActionListener(f1);
		uploadButton.addActionListener(f1);
		finishButton.addActionListener(f1);
		
		panel1 = new JPanel(); // image button and text field
		panel2 = new JPanel(); // upload button
		panel3 = new JPanel(); // Finish button
				
		panel1.add(imageButton, BorderLayout.NORTH);
		panel1.add(field, BorderLayout.CENTER);
		
		panel2.add(uploadButton, BorderLayout.SOUTH);
		
		panel3.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panel3.add(finishButton);
		
		add(panel1, BorderLayout.NORTH);
		add(panel2, BorderLayout.CENTER);
		add(panel3, BorderLayout.SOUTH);
		
		// Window specifications
		setTitle("Source Text Image Uploader");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 200);
		setResizable(false);
		setLocation(300, 300);
		setVisible(true);
		
	}
	
	// Action Listener for both image select and upload buttons
	public class FileChooseListener implements ActionListener {
		File file = new File("");
		ImageIcon image = new ImageIcon();
		String lastPath;
		JFrame confirmFrame;
		
		public void actionPerformed(ActionEvent e) {
			confirmFrame = new JFrame();
			confirmFrame.setAlwaysOnTop(true);
			if (e.getSource() == imageButton) {
			
				JFileChooser newChooser = new JFileChooser();
				int selectedFile = newChooser.showOpenDialog(null);
				newChooser.setDialogTitle("File Selector");
			
				if (selectedFile == JFileChooser.APPROVE_OPTION) {
					file = newChooser.getSelectedFile();
					
					// Ensure file is an image in the correct format
					if (file.getPath().toLowerCase().endsWith(".jpg") || file.getPath().toLowerCase().endsWith(".png") || file.getPath().toLowerCase().endsWith(".jpeg")) {
						image = new ImageIcon(file.getPath());
						field.setText(file.getPath());
						uploadButton.setEnabled(true);
					}
					else {
						JOptionPane.showMessageDialog(new JFrame(), "Hmm, this doesn't look like an image. Try selecting"
								+ " another file.");
						uploadButton.setEnabled(false);
						return;
					}
					// Checks to make sure you don't accidentally upload the same image twice in a row
					if (file.getPath().equals(lastPath)) {
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
					lastPath = file.getPath();
				}	
				
			}
			
			// Temporarily, displays the image in a new window
			// will eventually upload image to the server
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
							JOptionPane.showMessageDialog(confirmFrame, "Uploading...");
							imageButton.setText("Choose an image");	
							field.setText("");
							uploadButton.setEnabled(false);
							newFrame.setVisible(false);
							finishButton.setEnabled(true);
							break;
						case 1: // No
							imageButton.setText("Choose an image");
							newFrame.setVisible(false);
							field.setText("");
							uploadButton.setEnabled(false);
							break;
						case 2: // Cancel
							newFrame.setVisible(false);
							field.setText("");
							uploadButton.setEnabled(false);
							break;
					}
			
			}
			
			if (e.getSource() == finishButton) {
				JOptionPane.showMessageDialog(new JFrame(), "Finished!");
				// close window and proceed
			}
			
		}
	}
	
	public static void main(String[] args) {
		
		//new AlphabetUploader();
		new ImageChooser();
		
		//new WriteWindow("Test 25");

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
