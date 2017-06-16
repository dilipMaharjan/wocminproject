import okhttp3.*;
import java.net.URLConnection;
import java.io.File;

public class RequestImageInfo {

  public static void main(String[] args)
  {
    new RequestImageInfo().test();
  }

  public void test()
  {
    try {
      File file = new File("test2.png");
      String mimeType = URLConnection.guessContentTypeFromName(file.getName());
      System.out.println("mime type: " + mimeType);
      MediaType imageType = MediaType.parse(mimeType);

      OkHttpClient client = new OkHttpClient();

      RequestBody requestBody = new MultipartBody.Builder()
        .setType(MultipartBody.FORM)
        .addFormDataPart("image", "test2.png",
            RequestBody.create(imageType, file))
        .build();

      Request request = new Request.Builder()
        .url("http://172.19.144.219:12345/images")
        .post(requestBody)
        .build();

      Response response = client.newCall(request).execute();
      System.out.println(response.body().string());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
