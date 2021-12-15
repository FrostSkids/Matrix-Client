package matrix.Utils.config;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class FileManager {
   private static Gson gson = new Gson();
   private static File ROOT_DIR = new File("MatrixClient");
   public static File MODS_DIR;

   static {
      MODS_DIR = new File(ROOT_DIR, "Cosmetics");
   }

   public static void init() {
      if (!ROOT_DIR.exists()) {
         ROOT_DIR.mkdirs();
      }

      if (!MODS_DIR.exists()) {
         MODS_DIR.mkdirs();
      }

   }

   public static Gson getGson() {
      return gson;
   }

   public static File getModsDirectory() {
      return MODS_DIR;
   }

   public static boolean writeJsonToFile(File file, Object obj) {
      try {
         if (!file.exists()) {
            file.createNewFile();
         }

         FileOutputStream outPutStream = new FileOutputStream(file);
         outPutStream.write(gson.toJson(obj).getBytes());
         outPutStream.flush();
         outPutStream.close();
         return true;
      } catch (Exception var3) {
         var3.printStackTrace();
         return false;
      }
   }

   public static <T> T readFromJson(File file, Class<T> c) {
      try {
         FileInputStream fileInputStream = new FileInputStream(file);
         InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
         BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
         StringBuilder builder = new StringBuilder();

         String line;
         while((line = bufferedReader.readLine()) != null) {
            builder.append(line);
         }

         bufferedReader.close();
         inputStreamReader.close();
         fileInputStream.close();
         return gson.fromJson(builder.toString(), c);
      } catch (Exception var7) {
         var7.printStackTrace();
         return null;
      }
   }
}
