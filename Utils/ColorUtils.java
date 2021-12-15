package matrix.Utils;

import java.awt.Color;
import java.util.concurrent.atomic.AtomicInteger;

public class ColorUtils {
   public static int getColor(Color color) {
      return getColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
   }

   public static int getColor(int brightness) {
      return getColor(brightness, brightness, brightness, 255);
   }

   public static int getColor(int brightness, int alpha) {
      return getColor(brightness, brightness, brightness, alpha);
   }

   public static int getColor(int red, int green, int blue) {
      return getColor(red, green, blue, 255);
   }

   public static int getColor(int red, int green, int blue, int alpha) {
      int color = 0;
      int color = color | alpha << 24;
      color |= red << 16;
      color |= green << 8;
      color |= blue;
      return color;
   }

   public static int astolfoColors(int yOffset, int yTotal, long index) {
      float speed = 3000.0F;

      float hue;
      for(hue = (float)((System.currentTimeMillis() + index) % (long)((int)speed)) + (float)((yTotal - yOffset) * 9); hue > speed; hue -= speed) {
      }

      hue /= speed;
      if ((double)hue > 0.5D) {
         hue = 0.5F - (hue - 0.5F);
      }

      hue += 0.5F;
      return Color.HSBtoRGB(hue, 0.5F, 1.0F);
   }

   public static Color astolfoColorsColor(int yOffset, int yTotal, long index) {
      float speed = 3000.0F;

      float hue;
      for(hue = (float)((System.currentTimeMillis() + index) % (long)((int)speed)) + (float)((yTotal - yOffset) * 9); hue > speed; hue -= speed) {
      }

      hue /= speed;
      if ((double)hue > 0.5D) {
         hue = 0.5F - (hue - 0.5F);
      }

      hue += 0.5F;
      return Color.getHSBColor(hue, 0.5F, 1.0F);
   }

   public static int rainbowWawe(int delay) {
      AtomicInteger ColorIndex = new AtomicInteger();
      int clrd = ColorIndex.get() / delay;
      ColorIndex.getAndIncrement();
      return rainbowColor(clrd);
   }

   public static int rainbowStatic(int delay) {
      return rainbowColor(delay);
   }

   private static int rainbowColor(int delay) {
      double rainbowState = Math.ceil((double)((System.currentTimeMillis() + (long)delay) / 40L));
      rainbowState %= 360.0D;
      return Color.getHSBColor((float)(rainbowState / 360.0D), 1.0F, 1.0F).getRGB();
   }

   public static Color rainbowColorRealColor(int delay) {
      double rainbowState = Math.ceil((double)((System.currentTimeMillis() + (long)delay) / 40L));
      rainbowState %= 360.0D;
      return Color.getHSBColor((float)(rainbowState / 360.0D), 1.0F, 1.0F);
   }
}
