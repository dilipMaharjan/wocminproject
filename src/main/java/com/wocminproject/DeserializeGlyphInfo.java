package com.wocminproject;

import com.wocminproject.models.GlyphInfo;
import java.util.*;
import java.lang.reflect.Type;
import com.google.gson.*;
import com.google.gson.reflect.*;

public class DeserializeGlyphInfo {
  public ArrayList<GlyphInfo> getGlyphInfoFromJson(String jsonResponse)
  {
    Gson gson = new Gson();
    Type collectionType = new TypeToken<ArrayList<GlyphInfo>>(){}.getType();
    ArrayList<GlyphInfo> foundGlyphs = gson.fromJson(jsonResponse, collectionType);
    return foundGlyphs;
  }
}
