package matrix.Utils;

import net.minecraft.client.gui.Gui;
import org.lwjgl.opengl.GL11;

public class NewRoundedRect {
   public static void drawHollowRect(int x, int y, int w, int h, int color) {
      drawHorizontalLine(x, x + w, y, color);
      drawHorizontalLine(x, x + w, y + h, color);
      drawVerticalLine(x, y + h, y, color);
      drawVerticalLine(x + w, y + h, y, color);
   }

   public static void drawHorizontalLine(int startX, int endX, int y, int color) {
      if (endX < startX) {
         int i = startX;
         startX = endX;
         endX = i;
      }

      Gui.drawRect((double)startX, (double)y, (double)(endX + 1), (double)(y + 1), color);
   }

   public static void drawVerticalLine(int x, int startY, int endY, int color) {
      if (endY < startY) {
         int i = startY;
         startY = endY;
         endY = i;
      }

      Gui.drawRect((double)x, (double)(startY + 1), (double)(x + 1), (double)endY, color);
   }

   public static void drawRoundedRect(double x, double y, double x1, double y1, double radius, int color) {
      GL11.glPushAttrib(0);
      GL11.glScaled(0.5D, 0.5D, 0.5D);
      x *= 2.0D;
      y *= 2.0D;
      x1 *= 2.0D;
      y1 *= 2.0D;
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      setColor(color);
      GL11.glEnable(2848);
      GL11.glBegin(9);

      int i;
      for(i = 0; i <= 90; i += 3) {
         GL11.glVertex2d(x + radius + Math.sin((double)i * 3.141592653589793D / 180.0D) * radius * -1.0D, y + radius + Math.cos((double)i * 3.141592653589793D / 180.0D) * radius * -1.0D);
      }

      for(i = 90; i <= 180; i += 3) {
         GL11.glVertex2d(x + radius + Math.sin((double)i * 3.141592653589793D / 180.0D) * radius * -1.0D, y1 - radius + Math.cos((double)i * 3.141592653589793D / 180.0D) * radius * -1.0D);
      }

      for(i = 0; i <= 90; i += 3) {
         GL11.glVertex2d(x1 - radius + Math.sin((double)i * 3.141592653589793D / 180.0D) * radius, y1 - radius + Math.cos((double)i * 3.141592653589793D / 180.0D) * radius);
      }

      for(i = 90; i <= 180; i += 3) {
         GL11.glVertex2d(x1 - radius + Math.sin((double)i * 3.141592653589793D / 180.0D) * radius, y + radius + Math.cos((double)i * 3.141592653589793D / 180.0D) * radius);
      }

      GL11.glEnd();
      GL11.glEnable(3553);
      GL11.glDisable(3042);
      GL11.glDisable(2848);
      GL11.glDisable(3042);
      GL11.glEnable(3553);
      GL11.glScaled(2.0D, 2.0D, 2.0D);
      GL11.glPopAttrib();
   }

   public static void drawBorderedRoundedRect(float x, float y, float x1, float y1, float borderSize, int borderC, int insideC) {
      drawRoundedRect((double)x, (double)y, (double)x1, (double)y1, (double)borderSize, borderC);
      drawRoundedRect((double)(x + 0.5F), (double)(y + 0.5F), (double)(x1 - 0.5F), (double)(y1 - 0.5F), (double)borderSize, insideC);
   }

   public static void drawBorderedRoundedRect2(float x, float y, float x1, float y1, float borderSize, int insideC) {
      drawRoundedRect((double)(x + 0.5F), (double)(y + 0.5F), (double)(x1 - 0.5F), (double)(y1 - 0.5F), (double)borderSize, insideC);
   }

   public static void drawBorderedRoundedRect(float x, float y, float x1, float y1, float radius, float borderSize, int borderC, int insideC) {
      drawRoundedRect((double)x, (double)y, (double)x1, (double)y1, (double)radius, borderC);
      drawRoundedRect((double)(x + borderSize), (double)(y + borderSize), (double)(x1 - borderSize), (double)(y1 - borderSize), (double)radius, insideC);
   }

   public static void setColor(int color) {
      float a = (float)(color >> 24 & 255) / 255.0F;
      float r = (float)(color >> 16 & 255) / 255.0F;
      float g = (float)(color >> 8 & 255) / 255.0F;
      float b = (float)(color & 255) / 255.0F;
      GL11.glColor4f(r, g, b, a);
   }
}
