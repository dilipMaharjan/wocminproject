package com.wocminproject;

import java.io.File;
import java.util.*;

public class WocMinProjectController implements MappingFolderChosenListener,
                                                ImageChosenListener {
  MappingChooser mappingChooser;
  ImageChooser imageChooser;
  RequestGlyphArray serviceRequester;
  DeserializeGlyphInfo deserializer;
  CalcGlyphSimilarity glyphMatcher;

  ArrayList<MappedGlyph> mapping;
  //ArrayList<GlyphInfo> foundGlyphs;

  public static void main(String[] args)
  {
    new WocMinProjectController().run();
  }

  public WocMinProjectController()
  {
    serviceRequester = new RequestGlyphArray();
    deserializer = new DeserializeGlyphInfo();
    glyphMatcher = new CalcGlyphSimilarity();
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
      ArrayList<GlyphInfo> foundGlyphInfo = deserializer.getGlyphInfoFromJson(json);
      if (foundGlyphInfo.size() == 0) {
        //TODO: show warning that a file in the directory wasn't processed correctly
        continue;
      }

      //get the feature descriptor stamp from the analyzed glyph info
      GlyphInfo glyphInfo = foundGlyphInfo.get(0);
      mappedGlyph.setFeatureDescriptor(glyphInfo.getFeatureDescriptor());

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
    //for each found glyph, calculate which glyph in the mapping is the most similar
    for (GlyphInfo glyph : foundGlyphs) {
      char matchedCharacter = glyphMatcher.findClosestGlyph(mapping, glyph.getFeatureDescriptor());
      glyph.setMatchedCharacter(matchedCharacter);
    }

    //now put the glyphs in order to prepare for output
    orderGlyphs(foundGlyphs);
  }

  //Put found glyphs in order using bounding box info
  private void orderGlyphs(ArrayList<GlyphInfo> foundGlyphs)
  {
    //sort glyphs by Y coordinate
    sortGlyphsByY(foundGlyphs);

    //calculate difference between Y coordinates of each two letters
    ArrayList<Integer> differences = new ArrayList<Integer>();
    for (int i = 0; i < (foundGlyphs.size() - 1); i++) {
      int difference = foundGlyphs.get(i).getYStart() - foundGlyphs.get(i+1).getYStart();
      difference = Math.abs(difference);
      differences.add(difference);
    }

    //calculate average letter height
    int totalLetterHeight = 0;
    for (GlyphInfo glyph : foundGlyphs) {
      totalLetterHeight += glyph.getYDim();
    }
    int averageLetterHeight = totalLetterHeight / foundGlyphs.size();

    //loop through Y differences and find any that are greater than the average
    //letter height, because these probably indicate row boundaries. Then split the
    //glyphs by row
    ArrayList<ArrayList<GlyphInfo>> glyphRows = new ArrayList<ArrayList<GlyphInfo>>();
    int lastRowBoundary = 0;
    for (int i = 0; i < differences.size(); i++) {
      if (differences.get(i) > averageLetterHeight) {
        ArrayList<GlyphInfo> newRow = new ArrayList<GlyphInfo>(foundGlyphs.subList(lastRowBoundary, i+1));
        glyphRows.add(newRow);
        lastRowBoundary = i+1;
      }
    }
    //add the final row, which the loop above misses
    ArrayList<GlyphInfo> finalRow = new ArrayList<GlyphInfo>(foundGlyphs.subList(lastRowBoundary, foundGlyphs.size()));
    glyphRows.add(finalRow);

    //for each row, sort by X coordinate
    for (ArrayList<GlyphInfo> row : glyphRows) {
      sortGlyphsByX(row);
    }

    //put all the rows together into one final ordered list
    ArrayList<GlyphInfo> orderedGlyphs = new ArrayList<GlyphInfo>();
    for (ArrayList<GlyphInfo> row : glyphRows) {
      orderedGlyphs.addAll(row);
    }

    //now display options for outputting the transcribed text
    //TODO: pass off to spacing here instead
    outputTranscription(orderedGlyphs);
  }

  private void sortGlyphsByY(ArrayList<GlyphInfo> glyphs)
  {
    Collections.sort(glyphs, new Comparator<GlyphInfo>() {
        public int compare(GlyphInfo o1, GlyphInfo o2) {
            if (o1.getYStart() == o2.getYStart()) {
                return 0;
            }
            return o1.getYStart() < o2.getYStart() ? -1 : 1;
        }
    });
  }

  private void sortGlyphsByX(ArrayList<GlyphInfo> glyphs)
  {
    Collections.sort(glyphs, new Comparator<GlyphInfo>() {
        public int compare(GlyphInfo o1, GlyphInfo o2) {
            if (o1.getXStart() == o2.getXStart()) {
                return 0;
            }
            return o1.getXStart() < o2.getXStart() ? -1 : 1;
        }
    });
  }

  private void outputTranscription(ArrayList<GlyphInfo> orderedGlyphs)
  {
    //build output string
    String output = "natty test";

    //show output selection window
    WriteWindow outputWindow = new WriteWindow(output);

    for (GlyphInfo glyph : orderedGlyphs) {
      System.out.println(glyph.getMatchedCharacter() + " " + glyph.getYStart());
    }
  }
}
