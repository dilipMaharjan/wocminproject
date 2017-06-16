package com.wocminproject;

import java.util.ArrayList;

public class CalcGlyphSimilarity {

  public char findClosestGlyph(ArrayList<MappedGlyph> mapping, int[] featureDescriptor) {
    char mostSimilarGlyph = '\0';
    double mostSimilarRating = 0.0;

    //loop through glyphs in mapping and find the one with the largest similarity rating
    for (MappedGlyph glyph : mapping) {
      double similarity = calcSimilarity(glyph.getFeatureDescriptor(), featureDescriptor);
      if (similarity > mostSimilarRating) {
        mostSimilarRating = similarity;
        mostSimilarGlyph = glyph.getCharacter();
      }
    }

    return mostSimilarGlyph;
  }

  // Returns a percentage rating for similarity of the two given feature descriptors.
  // 100% means they are identical
  private double calcSimilarity(int[] image1, int[] image2)
  {
    //compute distance between two feature vectors
    int distanceSum = 0;
    for (int i = 0; i < 1000; i++) {
      distanceSum += Math.pow((image2[i] - image1[i]), 2);
    }
    double distance = Math.sqrt(distanceSum);

    //calculate dissimilarity as percentage
    final double MAX_DISTANCE = 8063.8;
    double distanceRating = distance / MAX_DISTANCE;

    //convert dissimilarity rating to similarity
    int similarityRating = 100 - (int) (distanceRating * 100);
    return similarityRating;
  }
}
