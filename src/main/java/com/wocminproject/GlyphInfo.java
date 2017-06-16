package com.wocminproject;

import java.util.ArrayList;

public class GlyphInfo {
  private int[] img;
  private int x_dim;
  private int x_start;
  private int y_dim;
  private int y_start;
  private char matchedCharacter;

  public int[] getFeatureDescriptor()
  {
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
  public char getMatchedCharacter()
  {
    return matchedCharacter;
  }
  public void setMatchedCharacter(char character)
  {
    matchedCharacter = character;
  }
}
