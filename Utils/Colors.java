package matrix.Utils;

import java.awt.Color;

public class Colors {
   public static int MIN_VALUE = Integer.MIN_VALUE;
   public static int MAX_VALUE = Integer.MAX_VALUE;
   public static int guiBack1 = (new Color(30, 30, 30, 255)).getRGB();
   public static int guiBack2 = (new Color(20, 20, 20, 255)).getRGB();
   public static int guiTRANS_1 = (new Color(0, 0, 0, 80)).getRGB();
   public static int guiTRANS_2 = (new Color(30, 30, 30, 255)).getRGB();
   public static int guiTRANS_3 = (new Color(0, 0, 0, 140)).getRGB();
   public static int CLIENT_COLOR = (new Color(100, 100, 255)).getRGB();
   public static int CLIENT_COLOR_DARKER = (new Color(60, 60, 255)).getRGB();

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
}
