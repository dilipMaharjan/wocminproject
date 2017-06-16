package com.wocminproject.models;

public class MappedGlyph {
  private int[] featureDescriptor;
  private char character;

  public MappedGlyph() {}

  public MappedGlyph(char character)
  {
    this.character = character;
  }

  public int[] getFeatureDescriptor()
  {
    return featureDescriptor;
  }

  public char getCharacter()
  {
    return character;
  }

  public void setFeatureDescriptor(int[] featureDescriptor)
  {
    this.featureDescriptor = featureDescriptor;
  }

  public String toString() {
    StringBuilder value = new StringBuilder(character + "[");
    for (int number : featureDescriptor) {
      value.append(number + " ");
    }
    value.append("]");
		return value.toString();
	}
}
