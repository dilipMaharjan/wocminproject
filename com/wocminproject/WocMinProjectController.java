package com.wocminproject;

import java.io.File;
import java.util.ArrayList;

public class WocMinProjectController implements MappingFolderChosenListener, ImageChosenListener {
  MappingChooser mappingChooser;
  ImageChooser imageChooser;
  RequestGlyphArray serviceRequester;

  ArrayList<MappedGlyph> mapping;

  public static void main(String[] args)
  {
    new WocMinProjectController().run();
  }

  public WocMinProjectController()
  {
    serviceRequester = new RequestGlyphArray();
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

  public void processMapping(File mappingFolder)
  {
    //use files in folder to find mapping
    mapping = new ArrayList<MappedGlyph>();
  }

  public void imageChosen(File image)
  {
    //TODO: show some kind of dialog while this process is going on?

    //send image to glyph analysis service and get JSON response back
    String imageInfo = serviceRequester.getGlyphInfo(image);
    if (imageInfo.isEmpty()) {
      //TODO: show warning that no response came back
      return;
    }

    System.out.println("Image info: " + imageInfo);
  }
}
