package matrix.Utils.font;

import java.awt.Font;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class FontUtil {
   public static volatile int completed;
   public static MinecraftFontRenderer normal;
   public static Font normal_;
   private static final Minecraft mc = Minecraft.getMinecraft();

   private static Font getFont(Map<String, Font> locationMap, String location, int size) {
      Font font = null;

      try {
         if (locationMap.containsKey(location)) {
            font = ((Font)locationMap.get(location)).deriveFont(0, (float)size);
         } else {
            InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("client/font/" + location)).getInputStream();
            font = Font.createFont(0, is);
            locationMap.put(location, font);
            font = font.deriveFont(0, (float)size);
         }
      } catch (Exception var5) {
         var5.printStackTrace();
         System.out.println("Error loading font");
         font = new Font("default", 0, 10);
      }

      return font;
   }

   public static boolean hasLoaded() {
      return completed >= 3;
   }

   public static void bootstrap() {
      (new Thread(() -> {
         Map<String, Font> locationMap = new HashMap();
         normal_ = getFont(locationMap, "ARIAL.TTF", 19);
         ++completed;
      })).start();
      (new Thread(() -> {
         new HashMap();
         ++completed;
      })).start();
      (new Thread(() -> {
         new HashMap();
         ++completed;
      })).start();

      while(!hasLoaded()) {
         try {
            Thread.sleep(5L);
         } catch (InterruptedException var1) {
            var1.printStackTrace();
         }
      }

      normal = new MinecraftFontRenderer(normal_, true, true);
   }

   public static int drawCenteredString(String text, int x, int y) {
      return drawString(text, x - mc.fontRendererObj.getStringWidth(text) / 2, y);
   }

   private static int drawString(String text, int i, int y) {
      return 0;
   }

   public static int drawCenteredString(String text, int x, int y, int color) {
      return drawString(text, x - mc.fontRendererObj.getStringWidth(text) / 2, y, color);
   }

   public static int drawString(String text, int i, int y, int color) {
      return 0;
   }

   public static int drawCenteredString(String text, int x, int y, int color, boolean shadow) {
      return drawString(text, x - mc.fontRendererObj.getStringWidth(text) / 2, y, color, shadow);
   }

   private static int drawString(String text, int i, int y, int color, boolean shadow) {
      return 0;
   }

   public static int drawScaledCenteredString(String text, int x, int y, boolean shadow, float scale) {
      return drawScaledString(text, x - mc.fontRendererObj.getStringWidth(text) / 2, y, shadow, scale);
   }

   private static int drawScaledString(String text, int i, int y, boolean shadow, float scale) {
      return 0;
   }
}
