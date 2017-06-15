import java.util.Random;

public class GlyphInfo {
    private int[] img;
    private int x_dim;
    private int x_start;
    private int y_dim;
    private int y_start;
    
    ///////////
    
    public GlyphInfo() {
    	x_dim = 8;
    	x_start = 8;
    	y_dim = 10;
    	y_start = 10;
    }
    
    /**
     * test GlyphInfo generator
     * generates 100 random numbers (1-100) to place into img
     */
    public void createGlyph() {
    	GlyphInfo newGlyph = new GlyphInfo();
    	
    	final int STAMP_SIZE = 100;
    	
    	int[] a = new int[STAMP_SIZE];
    	
    	Random rand = new Random();
    	for(int i = 0; i < STAMP_SIZE; i++) {
    		a[i] = rand.nextInt(100);
    	}
    	
    	img = a;
    	
    }

  
    /////////
    
    public int[] getImg()
    {
      return img;
    }
    public int getXDim()
    {
      return x_dim;
    }
    public int getXStart()
    {
      return x_start;
    }
    public int getYDim()
    {
      return y_dim;
    }
    public int getYStart()
    {
      return y_start;
    }
    
    
  }