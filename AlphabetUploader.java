import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.swing.filechooser.*;

public class AlphabetUploader extends JFrame {
	
	private JButton imageButton;
	private JTextField field;
	private JButton uploadButton;
	private JPanel panel;
	private DirectoryChooser d1;
	private File folder;
	private File[] listFiles;
	
	public AlphabetUploader() {
		setSize(800, 250);
		setLocation(300, 300);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setResizable(false);
		setTitle("Alphabet Selector");
		
		
		imageButton = new JButton("Select a Directory");
		field = new JTextField(45);
		uploadButton = new JButton("Upload");
		uploadButton.setEnabled(false);
		
		d1 = new DirectoryChooser();
		imageButton.addActionListener(d1);
		uploadButton.addActionListener(d1);
		
		panel = new JPanel();
		panel.add(imageButton, BorderLayout.NORTH);
		panel.add(field, BorderLayout.NORTH);
		panel.add(uploadButton, BorderLayout.CENTER);
		
		add(panel);
		
		setVisible(true);
		
	}

	public class DirectoryChooser implements ActionListener {
		
		
		
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == imageButton) {
					
				JFileChooser newChooser = new JFileChooser();
				
				newChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				
				//newChooser.setAcceptAllFileFilterUsed(false);
				
				if(newChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					uploadButton.setEnabled(true);
					
					folder = new File(newChooser.getSelectedFile().getPath());
					listFiles = folder.listFiles();
					field.setText(newChooser.getSelectedFile().getPath());
				}
				
				
			}
			
			if (e.getSource() == uploadButton) {
				JPanel picPanel = new JPanel();
				
				for(int i = 0; i < listFiles.length; i++) {
					System.out.println(listFiles[i].getName());
					
				}
			}
			
		}
	}
}
	
	

