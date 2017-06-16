import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.swing.filechooser.*;
import java.util.*;

public class WriteWindow extends JFrame {

	private JButton fileButton;
	private JPanel panel1, panel2;
	private JLabel label;
	private String fileToWrite;
	
	// Call new WriteWindow with an argument of the string to be written to the file
	public WriteWindow(String file) {
		
		fileToWrite = file;
		
		panel1 = new JPanel();
		panel2 = new JPanel();
	
		fileButton = new JButton("Save");
		label = new JLabel("Click to Save");
		
		fileButton.addActionListener(new ButtonListener());
		
		panel1.add(label, BorderLayout.NORTH);
		panel2.add(fileButton);
		
		
		add(panel1, BorderLayout.NORTH);
		add(panel2, BorderLayout.CENTER);
		
		setSize(800, 250);
		setLocation(300, 300);
		setResizable(false);
		setTitle("Save Window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public class ButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {

				JFileChooser newChooser = new JFileChooser();
				newChooser.setCurrentDirectory(null);
				
				int saveFile = newChooser.showSaveDialog(null);
				if(saveFile == JFileChooser.APPROVE_OPTION) {
					
					try {
						String location = newChooser.getSelectedFile().getPath().trim();
						if(!location.endsWith(".txt")){
							location += ".txt";
						}
						 
						FileWriter newWriter = new FileWriter(location);
						PrintWriter pWriter = new PrintWriter(newWriter);
						
						pWriter.println(fileToWrite);
						pWriter.close();
						
					} catch (IOException err) {
						}
				}
			
		}
	}
	
}
