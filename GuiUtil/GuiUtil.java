package matrix.GuiUtil;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import org.lwjgl.opengl.GL11;

public class GuiUtil extends Gui {
   public static GuiUtil instance;
   private static final Minecraft mc = Minecraft.getMinecraft();

   public GuiUtil() {
      instance = this;
   }

   public static int drawString(String text, float x, float y, int color, boolean shadow) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      return mc.fontRendererObj.drawString(text, (double)x, (double)y, color, shadow);
   }

   public static int drawString(String text, int x, int y) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      return drawString(text, x, y, 16777215);
   }

   public static int drawString(String text, int x, int y, boolean shadow) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      return drawString(text, x, y, 16777215, shadow);
   }

   public static int drawString(String text, int x, int y, int color) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      return drawString(text, x, y, color, false);
   }

   public static int drawString(String text, int x, int y, int color, boolean shadow) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      String[] lines = text.split("\n");
      if (lines.length <= 1) {
         return mc.fontRendererObj.drawString(text, (double)x, (double)y, color, shadow);
      } else {
         int j = 0;

         for(int i = 0; i < lines.length; ++i) {
            j += mc.fontRendererObj.drawString(lines[i], (double)x, (double)(y + i * (mc.fontRendererObj.FONT_HEIGHT + 2)), color, shadow);
         }

         return j;
      }
   }

   public static int drawScaledString(String text, int x, int y, boolean shadow, float scale) {
      GlStateManager.pushMatrix();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.scale(scale, scale, 1.0F);
      int i = drawString(text, (int)((float)x / scale), (int)((float)y / scale), shadow);
      GlStateManager.scale(Math.pow((double)scale, -1.0D), Math.pow((double)scale, -1.0D), 1.0D);
      GlStateManager.popMatrix();
      return i;
   }

   public static void drawChromaString(String textIn, int xIn, int y, boolean shadow) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

      for(int j = 0; j < textIn.split("\n").length; ++j) {
         int x = xIn;
         char[] var9;
         int var8 = (var9 = textIn.split("\n")[j].toCharArray()).length;

         for(int var7 = 0; var7 < var8; ++var7) {
            char c = var9[var7];
            long l = System.currentTimeMillis() - (long)x * 10L - ((long)y - (long)j * 10L) * 10L;
            float speed = 2000.0F;
            int color = Color.HSBtoRGB((float)(l % (long)((int)speed)) / speed, 0.8F, 0.8F);
            drawString(String.valueOf(c), x, y + j * (mc.fontRendererObj.FONT_HEIGHT + 2), color, shadow);
            x += mc.fontRendererObj.getStringWidth(String.valueOf(c));
         }
      }

   }

   public static int drawCenteredString(String text, int x, int y) {
      return drawString(text, x - mc.fontRendererObj.getStringWidth(text) / 2, y);
   }

   public static int drawCenteredString(String text, int x, int y, int color) {
      return drawString(text, x - mc.fontRendererObj.getStringWidth(text) / 2, y, color);
   }

   public static int drawCenteredString(String text, int x, int y, int color, boolean shadow) {
      return drawString(text, x - mc.fontRendererObj.getStringWidth(text) / 2, y, color, shadow);
   }

   public static int drawScaledCenteredString(String text, int x, int y, boolean shadow, float scale) {
      return drawScaledString(text, x - mc.fontRendererObj.getStringWidth(text) / 2, y, shadow, scale);
   }

   public static void drawRectOutline(int left, int top, int right, int bottom, int color) {
      drawRect((double)(left - 1), (double)(top - 1), (double)(right + 1), (double)top, color);
      drawRect((double)right, (double)top, (double)(right + 1), (double)bottom, color);
      drawRect((double)(left - 1), (double)bottom, (double)(right + 1), (double)(bottom + 1), color);
      drawRect((double)(left - 1), (double)top, (double)left, (double)bottom, color);
   }

   public void drawGradientRect(int left, int top, int right, int bottom, int startColor, int endColor) {
      float f = (float)(startColor >> 24 & 255) / 255.0F;
      float f1 = (float)(startColor >> 16 & 255) / 255.0F;
      float f2 = (float)(startColor >> 8 & 255) / 255.0F;
      float f3 = (float)(startColor & 255) / 255.0F;
      float f4 = (float)(endColor >> 24 & 255) / 255.0F;
      float f5 = (float)(endColor >> 16 & 255) / 255.0F;
      float f6 = (float)(endColor >> 8 & 255) / 255.0F;
      float f7 = (float)(endColor & 255) / 255.0F;
      GlStateManager.disableTexture2D();
      GlStateManager.enableBlend();
      GlStateManager.disableAlpha();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.shadeModel(7425);
      Tessellator tessellator = Tessellator.getInstance();
      WorldRenderer worldrenderer = tessellator.getWorldRenderer();
      tessellator.draw();
      GlStateManager.shadeModel(7424);
      GlStateManager.disableBlend();
      GlStateManager.enableAlpha();
      GlStateManager.enableTexture2D();
   }

   public static void drawRoundedRect(int paramInt1, int paramInt2, int paramInt3, int paramInt4, float radius, int color) {
      float f1 = (float)(color >> 24 & 255) / 255.0F;
      float f2 = (float)(color >> 16 & 255) / 255.0F;
      float f3 = (float)(color >> 8 & 255) / 255.0F;
      float f4 = (float)(color & 255) / 255.0F;
      GlStateManager.color(f2, f3, f4, f1);
      drawRoundedRect((float)paramInt1, (float)paramInt2, (float)paramInt3, (float)paramInt4, radius);
   }

   public static void drawRoundedRect(float paramInt1, float paramInt2, float paramInt3, float paramInt4, float radius, int color) {
      float f1 = (float)(color >> 24 & 255) / 255.0F;
      float f2 = (float)(color >> 16 & 255) / 255.0F;
      float f3 = (float)(color >> 8 & 255) / 255.0F;
      float f4 = (float)(color & 255) / 255.0F;
      GlStateManager.color(f2, f3, f4, f1);
      drawRoundedRect(paramInt1, paramInt2, paramInt3, paramInt4, radius);
   }

   public static void drawRoundedRect(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5) {
      int i = 18;
      float f1 = 90.0F / (float)i;
      GlStateManager.disableTexture2D();
      GlStateManager.enableBlend();
      GlStateManager.disableCull();
      GlStateManager.enableColorMaterial();
      GlStateManager.blendFunc(770, 771);
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GL11.glBegin(5);
      GL11.glVertex2f(paramFloat1 + paramFloat5, paramFloat2);
      GL11.glVertex2f(paramFloat1 + paramFloat5, paramFloat4);
      GL11.glVertex2f(paramFloat3 - paramFloat5, paramFloat2);
      GL11.glVertex2f(paramFloat3 - paramFloat5, paramFloat4);
      GL11.glEnd();
      GL11.glBegin(5);
      GL11.glVertex2f(paramFloat1, paramFloat2 + paramFloat5);
      GL11.glVertex2f(paramFloat1 + paramFloat5, paramFloat2 + paramFloat5);
      GL11.glVertex2f(paramFloat1, paramFloat4 - paramFloat5);
      GL11.glVertex2f(paramFloat1 + paramFloat5, paramFloat4 - paramFloat5);
      GL11.glEnd();
      GL11.glBegin(5);
      GL11.glVertex2f(paramFloat3, paramFloat2 + paramFloat5);
      GL11.glVertex2f(paramFloat3 - paramFloat5, paramFloat2 + paramFloat5);
      GL11.glVertex2f(paramFloat3, paramFloat4 - paramFloat5);
      GL11.glVertex2f(paramFloat3 - paramFloat5, paramFloat4 - paramFloat5);
      GL11.glEnd();
      GL11.glBegin(6);
      float f2 = paramFloat3 - paramFloat5;
      float f3 = paramFloat2 + paramFloat5;
      GL11.glVertex2f(f2, f3);

      int j;
      float f4;
      for(j = 0; j <= i; ++j) {
         f4 = (float)j * f1;
         GL11.glVertex2f((float)((double)f2 + (double)paramFloat5 * Math.cos(Math.toRadians((double)f4))), (float)((double)f3 - (double)paramFloat5 * Math.sin(Math.toRadians((double)f4))));
      }

      GL11.glEnd();
      GL11.glBegin(6);
      f2 = paramFloat1 + paramFloat5;
      f3 = paramFloat2 + paramFloat5;
      GL11.glVertex2f(f2, f3);

      for(j = 0; j <= i; ++j) {
         f4 = (float)j * f1;
         GL11.glVertex2f((float)((double)f2 - (double)paramFloat5 * Math.cos(Math.toRadians((double)f4))), (float)((double)f3 - (double)paramFloat5 * Math.sin(Math.toRadians((double)f4))));
      }

      GL11.glEnd();
      GL11.glBegin(6);
      f2 = paramFloat1 + paramFloat5;
      f3 = paramFloat4 - paramFloat5;
      GL11.glVertex2f(f2, f3);

      for(j = 0; j <= i; ++j) {
         f4 = (float)j * f1;
         GL11.glVertex2f((float)((double)f2 - (double)paramFloat5 * Math.cos(Math.toRadians((double)f4))), (float)((double)f3 + (double)paramFloat5 * Math.sin(Math.toRadians((double)f4))));
      }

      GL11.glEnd();
      GL11.glBegin(6);
      f2 = paramFloat3 - paramFloat5;
      f3 = paramFloat4 - paramFloat5;
      GL11.glVertex2f(f2, f3);

      for(j = 0; j <= i; ++j) {
         f4 = (float)j * f1;
         GL11.glVertex2f((float)((double)f2 + (double)paramFloat5 * Math.cos(Math.toRadians((double)f4))), (float)((double)f3 + (double)paramFloat5 * Math.sin(Math.toRadians((double)f4))));
      }

      GL11.glEnd();
      GlStateManager.enableCull();
      GlStateManager.disableBlend();
      GlStateManager.disableColorMaterial();
      GlStateManager.enableTexture2D();
   }

   public static void drawRoundedOutline(int x, int y, int x2, int y2, float radius, float width, int color) {
      float f1 = (float)(color >> 24 & 255) / 255.0F;
      float f2 = (float)(color >> 16 & 255) / 255.0F;
      float f3 = (float)(color >> 8 & 255) / 255.0F;
      float f4 = (float)(color & 255) / 255.0F;
      GlStateManager.color(f2, f3, f4, f1);
      drawRoundedOutline((float)x, (float)y, (float)x2, (float)y2, radius, width);
   }

   public static void drawRoundedOutline(float x, float y, float x2, float y2, float radius, float width) {
      int i = 18;
      int j = 90 / i;
      GlStateManager.disableTexture2D();
      GlStateManager.enableBlend();
      GlStateManager.disableCull();
      GlStateManager.enableColorMaterial();
      GlStateManager.blendFunc(770, 771);
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      if (width != 1.0F) {
         GL11.glLineWidth(width);
      }

      GL11.glBegin(3);
      GL11.glVertex2f(x + radius, y);
      GL11.glVertex2f(x2 - radius, y);
      GL11.glEnd();
      GL11.glBegin(3);
      GL11.glVertex2f(x2, y + radius);
      GL11.glVertex2f(x2, y2 - radius);
      GL11.glEnd();
      GL11.glBegin(3);
      GL11.glVertex2f(x2 - radius, y2 - 0.1F);
      GL11.glVertex2f(x + radius, y2 - 0.1F);
      GL11.glEnd();
      GL11.glBegin(3);
      GL11.glVertex2f(x + 0.1F, y2 - radius);
      GL11.glVertex2f(x + 0.1F, y + radius);
      GL11.glEnd();
      float f1 = x2 - radius;
      float f2 = y + radius;
      GL11.glBegin(3);

      int k;
      int m;
      for(k = 0; k <= i; ++k) {
         m = 90 - k * j;
         GL11.glVertex2f((float)((double)f1 + (double)radius * MathUtils.getRightAngle(m)), (float)((double)f2 - (double)radius * MathUtils.getAngle(m)));
      }

      GL11.glEnd();
      f1 = x2 - radius;
      f2 = y2 - radius;
      GL11.glBegin(3);

      for(k = 0; k <= i; ++k) {
         m = k * j + 270;
         GL11.glVertex2f((float)((double)f1 + (double)radius * MathUtils.getRightAngle(m)), (float)((double)f2 - (double)radius * MathUtils.getAngle(m)));
      }

      GL11.glEnd();
      GL11.glBegin(3);
      f1 = x + radius;
      f2 = y2 - radius;

      for(k = 0; k <= i; ++k) {
         m = k * j + 90;
         GL11.glVertex2f((float)((double)f1 + (double)radius * MathUtils.getRightAngle(m)), (float)((double)f2 + (double)radius * MathUtils.getAngle(m)));
      }

      GL11.glEnd();
      GL11.glBegin(3);
      f1 = x + radius;
      f2 = y + radius;

      for(k = 0; k <= i; ++k) {
         m = 270 - k * j;
         GL11.glVertex2f((float)((double)f1 + (double)radius * MathUtils.getRightAngle(m)), (float)((double)f2 + (double)radius * MathUtils.getAngle(m)));
      }

      GL11.glEnd();
      if (width != 1.0F) {
         GL11.glLineWidth(1.0F);
      }

      GlStateManager.enableCull();
      GlStateManager.disableBlend();
      GlStateManager.disableColorMaterial();
      GlStateManager.enableTexture2D();
   }

   public static void drawCircle(float x, float y, float radius, float thickness, Color color, boolean smooth) {
      drawPartialCircle(x, y, radius, 0, 360, thickness, color, smooth);
   }

   public static void drawPartialCircle(int x, int y, float radius, int startAngle, int endAngle, float thickness, Color color, boolean smooth) {
      drawPartialCircle(x, y, radius, startAngle, endAngle, thickness, color, smooth);
   }

   public static void drawPartialCircle(float x, float y, float radius, int startAngle, int endAngle, float thickness, Color colour, boolean smooth) {
      GL11.glDisable(3553);
      GL11.glBlendFunc(770, 771);
      if (startAngle > endAngle) {
         int temp = startAngle;
         startAngle = endAngle;
         endAngle = temp;
      }

      if (startAngle < 0) {
         startAngle = 0;
      }

      if (endAngle > 360) {
         endAngle = 360;
      }

      if (smooth) {
         GL11.glEnable(2848);
      } else {
         GL11.glDisable(2848);
      }

      GL11.glLineWidth(thickness);
      GL11.glColor4f((float)colour.getRed() / 255.0F, (float)colour.getGreen() / 255.0F, (float)colour.getBlue() / 255.0F, (float)colour.getAlpha() / 255.0F);
      GL11.glBegin(3);
      float ratio = 0.017453292F;

      for(int i = startAngle; i <= endAngle; ++i) {
         float radians = (float)(i - 90) * ratio;
         GL11.glVertex2f(x + (float)Math.cos((double)radians) * radius, y + (float)Math.sin((double)radians) * radius);
      }

      GL11.glEnd();
      GL11.glEnable(3553);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
   }

   public static void drawFilledRect(float x1, float y1, float x2, float y2, int colour, boolean smooth) {
      drawFilledShape(new float[]{x1, y1, x1, y2, x2, y2, x2, y1}, new Color(colour, true), smooth);
   }

   public static void drawFilledShape(float[] points, Color colour, boolean smooth) {
      GL11.glPushMatrix();
      GL11.glDisable(3553);
      GL11.glBlendFunc(770, 771);
      if (smooth) {
         GL11.glEnable(2848);
      } else {
         GL11.glDisable(2848);
      }

      GL11.glLineWidth(1.0F);
      GL11.glColor4f((float)colour.getRed() / 255.0F, (float)colour.getGreen() / 255.0F, (float)colour.getBlue() / 255.0F, (float)colour.getAlpha() / 255.0F);
      GL11.glBegin(9);

      for(int i = 0; i < points.length; i += 2) {
         GL11.glVertex2f(points[i], points[i + 1]);
      }

      GL11.glEnd();
      GL11.glEnable(3553);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glPopMatrix();
   }

   public static void drawLine(float x, float x1, float y, float thickness, int colour, boolean smooth) {
      drawLines(new float[]{x, y, x1, y}, thickness, new Color(colour, true), smooth);
   }

   public static void drawVerticalLine(float x, float y, float y1, float thickness, int colour, boolean smooth) {
      drawLines(new float[]{x, y, x, y1}, thickness, new Color(colour, true), smooth);
   }

   public static void drawLines(float[] points, float thickness, Color colour, boolean smooth) {
      GL11.glPushMatrix();
      GL11.glDisable(3553);
      GL11.glBlendFunc(770, 771);
      if (smooth) {
         GL11.glEnable(2848);
      } else {
         GL11.glDisable(2848);
      }

      GL11.glLineWidth(thickness);
      GL11.glColor4f((float)colour.getRed() / 255.0F, (float)colour.getGreen() / 255.0F, (float)colour.getBlue() / 255.0F, (float)colour.getAlpha() / 255.0F);
      GL11.glBegin(1);

      for(int i = 0; i < points.length; i += 2) {
         GL11.glVertex2f(points[i], points[i + 1]);
      }

      GL11.glEnd();
      GL11.glEnable(2848);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glEnable(3553);
      GL11.glPopMatrix();
   }
}
