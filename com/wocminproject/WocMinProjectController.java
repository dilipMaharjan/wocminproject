package com.wocminproject;

import java.io.File;
import java.util.ArrayList;

public class WocMinProjectController implements MappingFolderChosenListener, ImageChosenListener {
  MappingChooser mappingChooser;
  ImageChooser imageChooser;
  RequestGlyphArray serviceRequester;
  DeserializeGlyphInfo deserializer;

  ArrayList<MappedGlyph> mapping;

  public static void main(String[] args)
  {
    new WocMinProjectController().run();
  }

  public WocMinProjectController()
  {
    serviceRequester = new RequestGlyphArray();
    deserializer = new DeserializeGlyphInfo();
  }

  public void run()
  {
    //begin by getting mapping information from user
    mappingChooser = new MappingChooser();
    mappingChooser.setMappingFolderChosenListener(this);
  }

  public void mappingFolderChosen(File folder)
  {
    //TODO: show some kind of dialog while mapping is going on?

    //read in the mapping and process it
    boolean success = processMapping(folder);
    //then have the user select the source image
    if (success) {
      mappingChooser.setVisible(false);
      imageChooser = new ImageChooser();
      imageChooser.setImageChosenListener(this);
    }
  }

  private boolean processMapping(File mappingFolder)
  {
    mapping = new ArrayList<MappedGlyph>();
    //get mapping information for each image in the folder
    for(File file : mappingFolder.listFiles()) {
      //if the folder item is actually another folder, ignore it
      //TODO: also ignore files that aren't images (maybe should go in MappingChooser instead?)
      if (!file.isFile()) {
        continue;
      }

      //the first char of the file name will be the character to map it to
      char newChar = file.getName().charAt(0);
      MappedGlyph mappedGlyph = new MappedGlyph(newChar);

      //send the image to the server and get back the feature descriptor stamp
      String json = serviceRequester.getGlyphInfo(file);
      if (json.isEmpty()) {
        //TODO: show warning that a file in the directory wasn't processed correctly
        continue;
      }
      ArrayList<GlyphInfo> foundGlyphs = deserializer.getGlyphInfoFromJson(json);
      if (foundGlyphs.size() == 0) {
        //TODO: show warning that a file in the directory wasn't processed correctly
        continue;
      }

      //get the feature descriptor stamp from the analyzed glyph info
      GlyphInfo foundGlyph = foundGlyphs.get(0);
      mappedGlyph.setFeatureDescriptor(foundGlyph.getImg());

      //add to mapping
      mapping.add(mappedGlyph);
    }

    System.out.print("mapping: ");
    for (MappedGlyph glyph : mapping) {
      System.out.println(glyph.toString());
    }

    //TODO: display warning if no characters were found to map to
    if (mapping.isEmpty()) {
      return false; //finding mapping info was unsuccessful
    } else {
      return true;
    }
    
  }

  public void imageChosen(File image)
  {
    //TODO: show some kind of dialog while this process is going on?

    //send image to glyph analysis service and get JSON response back
    String json = serviceRequester.getGlyphInfo(image);
    if (json.isEmpty()) {
      //TODO: show warning that no response came back
      return;
    }

    //get Java objects from JSON response
    ArrayList<GlyphInfo> foundGlyphs = deserializer.getGlyphInfoFromJson(json);
    if (foundGlyphs.size() == 0) {
      //TODO: show warning that no glyphs were found
      return;
    }
    mapGlyphs(foundGlyphs);
  }

  private void mapGlyphs(ArrayList<GlyphInfo> foundGlyphs)
  {
    System.out.println("Glyphs to match: " + foundGlyphs.size());
    System.out.println("Glyphs in mapping: " + mapping.size());
  }
}
