
public class HandwrittenLetter {
	private char character;
	private int[] img;
	
	// HandwrittenLetter : character, stamp info
	// Glyph : location, stamp info
	// TextLetter : location, character
	
	// get a glyph, take stamp info and compare to HandwrittenLetter stamp info
	// matching character gets assigned to new TextLetter object
	// location from glyph is given to TextLetter object
	
	public HandwrittenLetter(char a, int[] b) {
		character = a;
		img = b;
	}
	
	public char getChar() {
		return character;
	}
	
	public void setChar(char a) {
		character = a;
	}
	 
	public String toString() {
		return String.valueOf(character);
	}

	public int[] getImg() {
		return img;
	}
	
	public String getImg(int i) {
		String a = "";
		for(int j = 0; j < i; j++) {
			a += String.valueOf(img[j] + "  ");
			if(img[j] < 10) {
				a += " ";
			}
		}
		return a;
	}
	
}
