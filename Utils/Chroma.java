package matrix.Utils;

import java.awt.Color;
import net.minecraft.client.Minecraft;

public class Chroma {
   public static void drawChromaString(String string, int x, int y, boolean shadow) {
      Minecraft mc = Minecraft.getMinecraft();
      int xTmp = x;
      char[] var9;
      int var8 = (var9 = string.toCharArray()).length;

      for(int var7 = 0; var7 < var8; ++var7) {
         char textChar = var9[var7];
         long l = System.currentTimeMillis() - (long)(xTmp * 10 - y * 10);
         int i = Color.HSBtoRGB((float)(l % 2000L) / 2000.0F, 0.8F, 0.8F);
         String tmp = String.valueOf(textChar);
         mc.fontRendererObj.drawString(tmp, (double)xTmp, (double)y, i, shadow);
         xTmp += mc.fontRendererObj.getCharWidth(textChar);
      }

   }
}
