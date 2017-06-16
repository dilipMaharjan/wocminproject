import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.swing.filechooser.*;
import java.util.*;

public class WriteWindow extends JFrame {

	private JButton fileButton, selectButton;
	private JTextField field;
	private JPanel panel1, panel2;
	private JLabel label;
	private String fileToWrite, location;
	
	// Call new WriteWindow with an argument of the string to be written to the file
	public WriteWindow(String file) {
		
		fileToWrite = file;
		
		panel1 = new JPanel();
		panel2 = new JPanel();
	
		fileButton = new JButton("Save");
		label = new JLabel("          Choose a Location for your file");
		
		selectButton = new JButton("Choose Location");
		selectButton.addActionListener(new ButtonListener());
		
		field = new JTextField(30);
		field.setEditable(false);

		
		fileButton.addActionListener(new ButtonListener());
		
		panel1.setLayout(new BorderLayout());
		
		panel1.add(label, BorderLayout.WEST);
		panel1.add(selectButton, BorderLayout.EAST);
		panel1.add(field, BorderLayout.SOUTH);
		
		panel2.add(fileButton, BorderLayout.SOUTH);
		
		add(panel1, BorderLayout.NORTH);
		add(panel2, BorderLayout.SOUTH);
		
		setSize(400, 150);
		setLocation(400, 300);
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
						setVisible(false);
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
