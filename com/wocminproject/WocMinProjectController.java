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
    processMapping(folder);
    //then have the user select the source image
    mappingChooser.setVisible(false);
    imageChooser = new ImageChooser();
    imageChooser.setImageChosenListener(this);
  }

  private void processMapping(File mappingFolder)
  {
    //use files in folder to find mapping
    mapping = new ArrayList<MappedGlyph>();
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
