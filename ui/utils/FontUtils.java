package matrix.ui.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class FontUtils extends Gui {
   protected static final Minecraft mc = Minecraft.getMinecraft();

   public static int drawString(Object text, int x, int y) {
      return drawString(text, x, y, 16777215, false);
   }

   public static int drawString(Object text, int x, int y, int color) {
      return drawString(text, x, y, color, false);
   }

   public static int drawString(Object text, int x, int y, boolean shadow) {
      return drawString(text, x, y, 16777215, shadow);
   }

   public static int drawString(Object text, int x, int y, int color, boolean shadow) {
      return mc.fontRendererObj.drawString(String.valueOf(text), (double)((float)x), (double)((float)y), color, shadow);
   }

   public static int drawString(Object text, float x, float y) {
      return drawString(text, x, y, 16777215, false);
   }

   public static int drawString(Object text, float x, float y, int color) {
      return drawString(text, x, y, color, false);
   }

   public static int drawString(Object text, float x, float y, boolean shadow) {
      return drawString(text, x, y, 16777215, shadow);
   }

   public static int drawString(Object text, float x, float y, int color, boolean shadow) {
      return mc.fontRendererObj.drawString(String.valueOf(text), (double)x, (double)y, color, shadow);
   }

   public static int drawCenteredString(Object text, int x, int y, int color) {
      return drawCenteredString(text, x, y, color, false);
   }

   public static int drawCenteredString(Object text, float x, float y, int color) {
      return mc.fontRendererObj.drawString(String.valueOf(text), (double)(x - (float)mc.fontRendererObj.getStringWidth(String.valueOf(text)) / 2.0F), (double)y, color, false);
   }

   public static int drawCenteredString(Object text, int x, int y, int color, boolean shadow) {
      return mc.fontRendererObj.drawString(String.valueOf(text), (double)((float)x - (float)mc.fontRendererObj.getStringWidth(String.valueOf(text)) / 2.0F), (double)((float)y), color, shadow);
   }
}
