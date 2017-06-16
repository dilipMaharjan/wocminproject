/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wocminproject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wocminproject.models.ImageInfo;
import java.io.File;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *
 * @author dmaharjan
 */
public class RequestGlyphArray {
    //Returns a JSON string of the response from the glyph analysis service
    public String getGlyphInfo(File file) {
        try {

            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            System.out.println("mime type: " + mimeType);
            MediaType imageType = MediaType.parse(mimeType);

            OkHttpClient client = new OkHttpClient();

            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("image", file.getName(),
                            RequestBody.create(imageType, file))
                    .build();

            Request request = new Request.Builder()
                    .url("http://172.19.144.219:12345/images")
                    .post(requestBody)
                    .build();

            Response response = client.newCall(request).execute();
            String json = response.body().string();
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private ArrayList<ImageInfo> getSortedArray(String jsonString) {
        ArrayList<ImageInfo> imageInfo = new Gson().fromJson(jsonString, new TypeToken<List<ImageInfo>>() {
        }.getType());
        Collections.sort(imageInfo, new Comparator<ImageInfo>() {
            public int compare(ImageInfo o1, ImageInfo o2) {
                if (o1.getYStart()== o2.getYStart()) {
                    return 0;
                }
                return o1.getXStart() < o2.getXStart() ? -1 : 1;
            }
        });

        return imageInfo;
    }
}
