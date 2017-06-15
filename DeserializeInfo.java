import java.util.*;
import java.lang.reflect.Type;
import com.google.gson.*;
import com.google.gson.reflect.*;

public class DeserializeInfo {
  public static void main(String[] args)
  {
    new DeserializeInfo().test();
  }

  public void test()
  {
    String json = "[{'img':[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,179,0,0,0,0,0,0,0,0,179,179,0,0,0,0,0,0,0,0,179,102,46,19,42,149,149,0,0,0,72,116,0,0,0,125,125,19,50,24,125,179,0,0,0,0,0,0,0,0,179,179,0,0,0,0,0,0,0,0,179,179,0,0,0,0,0,0,0,0,179,179,0,0,0,0,0,0,0,0,179,179,0,0,0,0,0,0,0,0,179,176,0,0,0,0,0,0,0,0,176,25,64,140,140,140,20,0,0,0,15,179,0,0,0,0,0,0,0,0,179,116,116,45,90,113,26,0,0,0,116,109,0,0,0,47,109,109,49,109,109,179,0,0,0,0,0,0,0,0,179,179,0,0,0,0,0,0,0,0,179,179,0,0,0,0,0,0,0,0,179,179,0,0,0,0,0,0,0,0,179,179,0,0,0,0,0,0,2,12,179,16,0,0,0,12,113,113,113,113,113,179,0,0,4,7,0,4,2,4,179,179,0,0,0,0,0,0,0,0,179,68,0,0,0,129,129,71,38,53,129,75,57,19,104,144,144,0,0,0,52,179,0,0,0,0,0,0,0,0,179,179,0,0,0,0,0,0,0,0,179,179,0,0,0,0,0,0,0,0,179,178,0,0,0,0,0,1,16,19,178,123,0,0,0,13,31,50,123,123,123,103,0,0,0,103,103,103,103,31,103,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,107,0,0,0,107,107,89,47,88,107,106,85,21,106,106,106,0,0,0,106,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,24,0,0,0,131,131,131,29,79,73,140,71,83,4,4,4,20,81,78,140,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],'x_dim':27,'x_start':16,'y_dim':76,'y_start':29},{'img':[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,179,0,0,0,0,0,0,0,0,179,179,0,0,0,0,0,0,0,0,179,102,46,19,42,149,149,0,0,0,72,116,0,0,0,125,125,19,50,24,125,179,0,0,0,0,0,0,0,0,179,179,0,0,0,0,0,0,0,0,179,179,0,0,0,0,0,0,0,0,179,179,0,0,0,0,0,0,0,0,179,179,0,0,0,0,0,0,0,0,179,176,0,0,0,0,0,0,0,0,176,25,64,140,140,140,20,0,0,0,15,179,0,0,0,0,0,0,0,0,179,116,116,45,90,113,26,0,0,0,116,109,0,0,0,47,109,109,49,109,109,179,0,0,0,0,0,0,0,0,179,179,0,0,0,0,0,0,0,0,179,179,0,0,0,0,0,0,0,0,179,179,0,0,0,0,0,0,0,0,179,179,0,0,0,0,0,0,2,12,179,16,0,0,0,12,113,113,113,113,113,179,0,0,4,7,0,4,2,4,179,179,0,0,0,0,0,0,0,0,179,68,0,0,0,129,129,71,38,53,129,75,57,19,104,144,144,0,0,0,52,179,0,0,0,0,0,0,0,0,179,179,0,0,0,0,0,0,0,0,179,179,0,0,0,0,0,0,0,0,179,178,0,0,0,0,0,1,16,19,178,123,0,0,0,13,31,50,123,123,123,103,0,0,0,103,103,103,103,31,103,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,107,0,0,0,107,107,89,47,88,107,106,85,21,106,106,106,0,0,0,106,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,24,0,0,0,131,131,131,29,79,73,140,71,83,4,4,4,20,81,78,140,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],'x_dim':27,'x_start':160,'y_dim':76,'y_start':29},{'img':[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,120,120,120,83,3,3,0,0,0,120,126,104,125,76,5,0,0,0,0,126,179,0,0,0,0,0,0,0,0,179,179,0,0,0,0,0,0,0,0,179,114,0,0,0,0,6,111,114,114,114,119,0,0,0,2,14,86,119,119,119,0,0,0,0,178,178,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,179,179,0,0,0,7,123,123,123,123,9,1,0,0,0,57,119,119,119,95,16,2,0,0,0,110,177,0,0,0,0,0,0,0,0,177,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,111,0,0,0,1,13,114,114,114,114,24,0,0,0,1,23,130,130,130,111,97,0,0,0,83,118,78,118,0,118,4,126,126,126,126,23,0,0,0,3,68,75,116,116,116,116,0,0,0,14,121,121,121,115,20,2,0,0,0,82,114,36,39,10,114,114,30,84,38,114,0,0,0,0,0,0,0,0,0,0,121,26,58,32,121,121,3,8,6,121,117,0,0,0,4,6,99,117,117,117,82,0,0,0,4,16,120,120,120,120,48,0,0,0,15,68,119,119,119,119,4,0,0,0,34,127,127,127,121,4,4,81,120,120,120,120,0,0,0,4,4,65,130,130,130,95,0,0,0,4,28,119,119,119,119,84,0,0,0,3,0,0,0,0,179,179,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,179,179,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,0,0,0,79,120,120,120,120,21,7,0,0,0,83,135,135,135,50,7,5,0,0,0,120,120,120,120,83,6,134,134,0,64,79,9,0,0,0,134,3,43,93,131,135,135,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,179,179,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,179,179,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,176,176,0,0,0,0,1,0,0,0,120,120,120,120,79,4,135,0,0,0,8,74,61,0,135,135,128,0,0,0,11,95,77,0,128,128,3,0,0,0,123,123,123,123,57,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,179,179,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,179,179,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,20,64,63,167,167,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,0,0,0,118,118,118,118,93,5,3,0,0,0,115,118,118,118,94,3,4,0,0,0,86,119,119,119,119,16,0,0,0,0,179,179,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,179,179,0,0,0,0,0,0,0,0,0,0,0,0,0,0,38,140,140,140,54,24,0,0,0,4,4,54,124,124,124,124,0,0,0,4,19,64,103,103,138,138,0,0,0,17,5,0,0,0,31,126,126,126,125,5,39,0,0,0,14,67,120,120,120,120,94,0,0,0,1,22,110,119,119,119,122,44,85,31,122,82,9,39,36,122,0,0,0,0,0,0,0,0,0,0,0,0,0,0,179,179,0,0,0,0,79,66,119,106,119,119,0,0,0,7,56,142,142,142,18,7,0,0,0,19,227,0,0,0,21,21,0,0,0,109,1,115,120,120,120,89,0,0,0,1,85,0,0,0,96,118,78,118,0,118,59,0,0,0,1,11,123,123,123,123,106,0,0,0,2,14,99,120,120,120,178,0,0,0,0,0,0,0,0,178,0,0,0,0,0,0,0,0,0,0,0,0,0,0,179,179,0,0,0,0,5,0,0,0,138,138,118,104,35,5,48,33,14,21,126,126,97,126,54,13,13,146,146,146,9,2,0,0,0,4,91,117,117,117,85,62,0,0,0,58,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,121,0,0,0,4,4,76,121,121,121,177,0,0,0,0,0,12,17,28,177,175,0,0,0,0,8,25,23,44,175,103,103,0,103,103,103,0,0,0,103,0,0,0,0,179,179,0,0,0,0,113,113,113,113,8,14,2,17,10,113,126,126,45,121,6,6,0,0,0,126,0,0,0,0,0,0,0,0,0,0],'x_dim':52,'x_start':48,'y_dim':58,'y_start':47},{'img':[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,108,108,0,108,108,76,0,0,0,108,162,0,0,0,0,0,66,83,4,162,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,105,105,105,105,105,89,0,0,0,31,114,114,114,114,7,3,0,0,0,112,175,20,43,18,4,0,0,0,0,175,113,0,0,0,0,1,113,113,113,113,37,0,0,0,7,22,125,125,125,125,0,0,0,0,0,0,0,0,0,0,1,52,124,124,124,124,0,0,0,1,113,0,0,1,15,22,111,113,113,113,30,0,0,0,4,8,126,126,126,126,103,0,0,0,98,98,88,103,74,103,3,114,129,129,129,37,0,0,0,2,81,102,0,0,152,152,0,0,0,28,102,102,102,44,4,4,32,93,102,102,110,0,0,0,60,60,92,110,110,110,1,0,0,0,58,124,124,124,121,2,6,0,0,0,119,126,126,126,48,6,16,0,0,0,127,127,87,74,80,110,97,0,0,0,9,10,96,123,123,123,145,0,0,0,17,17,0,16,145,145,3,0,0,0,43,129,129,129,111,3,13,54,131,89,134,134,0,0,0,11,1,44,125,125,125,125,0,0,0,1,102,102,102,102,52,38,22,23,85,102,1,0,0,0,121,121,121,121,74,9,172,0,0,0,0,0,0,0,0,172,7,0,0,0,122,122,122,122,68,8,0,0,0,0,0,0,0,0,0,0,37,0,0,0,58,114,114,114,114,86,5,0,0,0,117,117,117,117,98,5,15,0,0,0,136,136,88,128,49,18,179,0,0,0,0,0,0,0,0,179,0,5,18,18,178,178,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,126,126,126,126,24,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,129,129,118,129,22,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,123,123,123,123,57,2,179,0,0,0,0,0,0,0,0,179,108,0,0,0,33,108,65,108,108,108,0,0,0,0,142,142,115,97,32,5,0,0,0,0,0,0,0,0,0,0,5,0,0,0,126,126,126,126,26,5,0,0,0,0,137,137,70,118,86,17,4,0,0,0,124,124,124,121,58,8,12,0,0,0,139,139,139,55,53,12,0,0,0,0,0,0,0,0,0,0,0,8,30,31,176,176,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,120,120,120,120,81,2,7,0,0,0,84,120,120,120,118,7,60,0,0,0,5,16,128,128,128,104,76,0,0,0,118,118,0,13,157,76,0,0,0,0,124,124,124,124,49,4,4,0,0,0,179,179,0,0,0,4,2,0,0,0,125,125,125,125,40,2,44,142,142,142,41,7,0,0,0,6,0,29,129,117,129,129,0,0,0,0,40,42,77,78,154,154,0,0,0,35,3,0,0,0,28,126,126,126,126,4,29,0,0,0,33,53,134,134,134,72,71,14,20,9,148,148,30,1,30,113,0,0,0,0,179,179,0,0,0,0,5,0,0,0,116,116,116,116,99,6,0,0,0,0,0,0,0,0,0,0,114,0,0,0,8,23,110,114,114,114,123,123,123,61,1,1,0,0,0,123,144,144,0,0,94,94,0,0,0,70,1,117,127,127,127,48,0,0,0,1,53,0,0,0,0,0,210,5,0,131,84,0,0,0,2,2,119,119,119,119,105,42,71,34,105,105,37,86,59,105,0,0,0,0,179,179,0,0,0,0,15,0,0,0,72,121,121,121,121,15,65,0,0,0,3,21,122,122,122,122,136,0,0,0,19,63,132,32,66,136,112,112,92,112,72,19,0,0,0,112,124,124,124,124,29,3,0,0,0,41,85,53,115,115,115,115,0,0,0,31,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,141,0,0,0,0,0,141,67,0,141,179,0,0,0,0,0,2,4,10,179,170,59,41,35,0,0,0,0,0,170,179,0,0,0,0,0,0,0,0,179,0,0,0,0,0,0,0,0,0,0],'x_dim':47,'x_start':107,'y_dim':58,'y_start':47}]";

    ArrayList<GlyphInfo> foundGlyphs = deserializeServiceResponse(json);
    GlyphInfo firstGlyph = foundGlyphs.get(0);
    System.out.print("img: ");
    for (int i = 0; i < 1000; i++) {
      System.out.print(firstGlyph.getImg()[i] + " ");
    }
    System.out.println();
    System.out.println(firstGlyph.getXStart());
  }

  public ArrayList<GlyphInfo> deserializeServiceResponse(String jsonResponse)
  {
    Gson gson = new Gson();
    Type collectionType = new TypeToken<ArrayList<GlyphInfo>>(){}.getType();
    ArrayList<GlyphInfo> foundGlyphs = gson.fromJson(jsonResponse, collectionType);
    return foundGlyphs;
  }

  public class GlyphInfo {
    private int[] img;
    private int x_dim;
    private int x_start;
    private int y_dim;
    private int y_start;

    public int[] getImg()
    {
      return img;
    }
    public int getXDim()
    {
      return x_dim;
    }
    public int getXStart()
    {
      return x_start;
    }
    public int getYDim()
    {
      return y_dim;
    }
    public int getYStart()
    {
      return y_start;
    }
  }
}
