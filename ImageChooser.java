import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.swing.filechooser.*;

//	Main Frame, includes
// 	> Image Select Button
//  > Text Field to display file path
//	> Upload Button
public class ImageChooser extends JFrame {
	
	private JButton uploadButton, imageButton;
	private JPanel panel1, panel2;
	private JTextField field;
	
	public ImageChooser() {
		
		uploadButton = new JButton("Upload");
		uploadButton.setEnabled(false); // Disables upload button until a readable image is selected
		
		imageButton = new JButton("Choose an image");
		
		field = new JTextField(25);
		field.setEditable(false); // Ensures that users will only attempt to upload viable images
		
		// Create and add an ActionListener to buttons
		FileChooseListener f1 = new FileChooseListener();
		imageButton.addActionListener(f1);
		uploadButton.addActionListener(f1);
		
		panel1 = new JPanel(); // image button and text field
		panel2 = new JPanel(); // upload button
				
		panel1.add(imageButton, BorderLayout.NORTH);
		panel1.add(field, BorderLayout.CENTER);
		panel2.add(uploadButton, BorderLayout.SOUTH);
		
		add(panel1, BorderLayout.NORTH);
		add(panel2, BorderLayout.CENTER);
		
		// Window specifications
		setTitle("Image Chooser");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 250);
		setLocation(300, 300);
		setLocation(300, 300);
		setVisible(true);
		
	}
	
	// Error Window class that displays a pop up message to help correct the problem
	public class ErrorWindow extends JFrame {
		JPanel panel = new JPanel();
		
		public ErrorWindow(int a) {
			
			setSize(350, 100);
			setLocation(350, 350);
			if (a == 1) {
				
				JLabel label = new JLabel("Hmm, this doesn't look like an image");
				JLabel label2 = new JLabel("Try selecting a different file");
				panel.add(label);
				panel.add(label2);
				add(panel);
				setVisible(true);
				
				imageButton.setText("Choose another file");
				field.setEditable(false);
				uploadButton.setEnabled(false);
			}
		}
	}
	
	// Action Listener for both image select and upload buttons
	public class FileChooseListener implements ActionListener {
		File file = new File("");
		ImageIcon image = new ImageIcon();
		
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == imageButton) {
			
				JFileChooser newChooser = new JFileChooser();
				int selectedFile = newChooser.showOpenDialog(null);
				newChooser.setDialogTitle("File Selector");
			
				if (selectedFile == JFileChooser.APPROVE_OPTION) {
					file = newChooser.getSelectedFile();
					
					// Ensure file is an image in the correct format
					if (file.getPath().endsWith(".jpg") || file.getPath().endsWith(".png")) {
						image = new ImageIcon(file.getPath());
						field.setText(file.getPath());
						field.setEditable(true);
						uploadButton.setEnabled(true);
					}
					else {
						new ErrorWindow(1); 
						return;
					}
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
					
					imageButton.setText("Choose another image");
					field.setText("");
					field.setEditable(false);
					uploadButton.setEnabled(false);

			}
		}
	}
	
	public static void main(String[] args) {
		
		new ImageChooser();
	}

}
