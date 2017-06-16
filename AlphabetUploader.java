import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.swing.filechooser.*;
import java.util.*;

public class AlphabetUploader extends JFrame {
	
	private JButton imageButton;
	private JTextField field;
	private JButton uploadButton, finishButton;
	private JPanel panel, panel2;
	private DirectoryChooser d1;
	private File folder;
	private File[] listFiles;
	private ArrayList<HandwrittenLetter> alphabet;
	
	public AlphabetUploader() {
		
		alphabet = new ArrayList<HandwrittenLetter>();
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
		
		finishButton = new JButton("Finish");
		finishButton.setEnabled(false);
		finishButton.addActionListener(d1);
		
		
		panel2 = new JPanel();
		panel2.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panel2.add(finishButton);
		
		panel = new JPanel();
		panel.add(imageButton, BorderLayout.NORTH);
		panel.add(field, BorderLayout.NORTH);
		panel.add(uploadButton, BorderLayout.CENTER);
		
		add(panel);
		add(panel2, BorderLayout.SOUTH);
		
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
				
				for(int i = 0; i < listFiles.length; i++) {
					
					char newChar = listFiles[i].getName().charAt(0);
			
					
					GlyphInfo newGlyph = new GlyphInfo();
					newGlyph.createGlyph(); // populates newGlyph.img with random numbers
					
					// ArrayList of HandwrittenLetter objects, each one contains 1) character and 2) stamp
					alphabet.add(new HandwrittenLetter(newChar, newGlyph.getImg()));
				
				
				}
				
				// Print out character with stamp data line by line
				for(int i = 0; i < alphabet.size(); i++) {
					System.out.print(alphabet.get(i).getChar() + ": ");
					
					System.out.print(alphabet.get(i).getImg(100));
					
					System.out.println();
				}	
				
				finishButton.setEnabled(true);
			}
			
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
		}
	}
}

	


