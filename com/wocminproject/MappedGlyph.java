package com.wocminproject;

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
}
