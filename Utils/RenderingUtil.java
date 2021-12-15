package matrix.Utils;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import org.lwjgl.opengl.GL11;

public class RenderingUtil {
   static Minecraft mc = Minecraft.getMinecraft();
   static FontRenderer fr;

   static {
      fr = mc.fontRendererObj;
   }

   public static void drawHorizontalLine(int y, int x1, int x2, Color colour) {
      drawFilledRectangle(x1, y, x2, y + 1, colour);
   }

   public static void drawVerticalLine(int x, int y1, int y2, Color colour) {
      drawFilledRectangle(x, y1, x + 1, y2, colour);
   }

   public static void drawRectangle(int x1, int y1, int x2, int y2, Color colour) {
      drawHorizontalLine(y1, x1, x2, colour);
      drawHorizontalLine(y2, x1, x2 + 1, colour);
      drawVerticalLine(x1, y1, y2, colour);
      drawVerticalLine(x2, y1, y2, colour);
   }

   public static void drawFilledCircle(float xx, float yy, float radius, int sections, Color color) {
      double dAngle = 6.283185307179586D / (double)sections;
      GL11.glPushAttrib(8192);
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(2848);
      GL11.glBegin(6);

      for(int i = 0; i < sections; ++i) {
         float x = (float)((double)radius * Math.sin((double)i * dAngle));
         float y = (float)((double)radius * Math.cos((double)i * dAngle));
         GL11.glColor4f((float)color.getRed() / 255.0F, (float)color.getGreen() / 255.0F, (float)color.getBlue() / 255.0F, (float)color.getAlpha() / 255.0F);
         GL11.glVertex2f(xx + x, yy + y);
      }

      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glEnd();
      GL11.glPopAttrib();
   }

   public static void drawFilledRectangle(int x1, int y1, int x2, int y2, Color colour) {
      int tempY;
      if (x1 < x2) {
         tempY = x1;
         x1 = x2;
         x2 = tempY;
      }

      if (y1 < y2) {
         tempY = y1;
         y1 = y2;
         y2 = tempY;
      }

      Tessellator tessellator = Tessellator.getInstance();
      WorldRenderer vertexbuffer = tessellator.getWorldRenderer();
      GlStateManager.pushMatrix();
      GlStateManager.enableBlend();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.color((float)colour.getRed(), (float)colour.getGreen(), (float)colour.getBlue(), (float)colour.getAlpha());
      vertexbuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
      vertexbuffer.pos((double)x1, (double)y2, 0.0D).endVertex();
      vertexbuffer.pos((double)x2, (double)y2, 0.0D).endVertex();
      vertexbuffer.pos((double)x2, (double)y1, 0.0D).endVertex();
      vertexbuffer.pos((double)x1, (double)y1, 0.0D).endVertex();
      tessellator.draw();
      GlStateManager.disableBlend();
      GlStateManager.popMatrix();
   }

   public static void drawOutlinedString(String str, float x, float y, int color) {
      Minecraft mc = Minecraft.getMinecraft();
      mc.fontRendererObj.drawString(str, x - 0.3F, y, Colors.getColor(0));
      mc.fontRendererObj.drawString(str, x + 0.3F, y, Colors.getColor(0));
      mc.fontRendererObj.drawString(str, x, y + 0.3F, Colors.getColor(0));
      mc.fontRendererObj.drawString(str, x, y - 0.3F, Colors.getColor(0));
      mc.fontRendererObj.drawString(str, x, y, color);
   }

   public static void glColor(Color color) {
      GL11.glColor4f((float)color.getRed() / 255.0F, (float)color.getGreen() / 255.0F, (float)color.getBlue() / 255.0F, (float)color.getAlpha() / 255.0F);
   }

   public static void glColor1(int hex) {
      float alpha = (float)(hex >> 24 & 255) / 255.0F;
      float red = (float)(hex >> 16 & 255) / 255.0F;
      float green = (float)(hex >> 8 & 255) / 255.0F;
      float blue = (float)(hex & 255) / 255.0F;
      GL11.glColor4f(red, green, blue, alpha);
   }

   public static void draw2DCircle(EntityLivingBase entity, double posX, double posY, double posZ, int color, int sides) {
      GlStateManager.pushMatrix();
      GlStateManager.translate(posX, posY, posZ);
      GL11.glNormal3f(0.0F, 0.0F, 0.0F);
      GlStateManager.rotate(-mc.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
      GlStateManager.scale(-0.1D, -0.1D, 0.1D);
      GL11.glDisable(2929);
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(3553);
      GlStateManager.depthMask(true);
      GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);

      for(int i = 0; i < 10; ++i) {
         drawCircle(0.0F, 0.0F, 10.0F, sides, color);
      }

      GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.color(0.0F, 0.0F, 0.0F);
      GL11.glEnable(2929);
      GlStateManager.popMatrix();
   }

   public static void draw2D(EntityLivingBase entity, double posX, double posY, double posZ, int color, int backgroundColor) {
      GlStateManager.pushMatrix();
      GlStateManager.translate(posX, posY, posZ);
      GL11.glNormal3f(0.0F, 0.0F, 0.0F);
      GlStateManager.rotate(-mc.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
      GlStateManager.scale(-0.1D, -0.1D, 0.1D);
      GL11.glDisable(2929);
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(3553);
      GlStateManager.depthMask(true);
      drawRect(-7.0F, 2.0F, -4.0F, 3.0F, color);
      drawRect(4.0F, 2.0F, 7.0F, 3.0F, color);
      drawRect(-7.0F, 0.5F, -6.0F, 3.0F, color);
      drawRect(6.0F, 0.5F, 7.0F, 3.0F, color);
      drawRect(-7.0F, 3.0F, -4.0F, 3.3F, backgroundColor);
      drawRect(4.0F, 3.0F, 7.0F, 3.3F, backgroundColor);
      drawRect(-7.3F, 0.5F, -7.0F, 3.3F, backgroundColor);
      drawRect(7.0F, 0.5F, 7.3F, 3.3F, backgroundColor);
      double YY = entity.getEntityBoundingBox().maxY - entity.getEntityBoundingBox().minY;
      GlStateManager.translate(0.0D, 21.0D + -YY * 12.0D, 0.0D);
      drawRect(4.0F, -20.0F, 7.0F, -19.0F, color);
      drawRect(-7.0F, -20.0F, -4.0F, -19.0F, color);
      drawRect(6.0F, -20.0F, 7.0F, -17.5F, color);
      drawRect(-7.0F, -20.0F, -6.0F, -17.5F, color);
      drawRect(7.0F, -20.0F, 7.3F, -17.5F, backgroundColor);
      drawRect(-7.3F, -20.0F, -7.0F, -17.5F, backgroundColor);
      drawRect(4.0F, -20.3F, 7.3F, -20.0F, backgroundColor);
      drawRect(-7.3F, -20.3F, -4.0F, -20.0F, backgroundColor);
      GL11.glEnable(2929);
      GlStateManager.color(1.0F, 1.0F, 1.0F);
      GlStateManager.popMatrix();
      GlStateManager.resetColor();
   }

   public static void draw2D(Entity entity, double posX, double posY, double posZ, int color, int backgroundColor) {
      int clrd = true;
      int clrd = ColorUtils.rainbowWawe(200);
      GlStateManager.pushMatrix();
      GlStateManager.translate(posX, posY, posZ);
      GL11.glNormal3f(0.0F, 0.0F, 0.0F);
      GlStateManager.rotate(-mc.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
      GlStateManager.scale(-0.1D, -0.1D, 0.1D);
      GL11.glDisable(2929);
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(3553);
      GlStateManager.depthMask(true);
      drawRect(-7.0F, 2.0F, -4.0F, 3.0F, clrd);
      drawRect(4.0F, 2.0F, 7.0F, 3.0F, clrd);
      drawRect(-7.0F, 0.5F, -6.0F, 3.0F, clrd);
      drawRect(6.0F, 0.5F, 7.0F, 3.0F, clrd);
      drawRect(-7.0F, 3.0F, -4.0F, 3.3F, backgroundColor);
      drawRect(4.0F, 3.0F, 7.0F, 3.3F, backgroundColor);
      drawRect(-7.3F, 0.5F, -7.0F, 3.3F, backgroundColor);
      drawRect(7.0F, 0.5F, 7.3F, 3.3F, backgroundColor);
      double YY = entity.getEntityBoundingBox().maxY + 0.5D - entity.getEntityBoundingBox().minY;
      GlStateManager.translate(0.0D, 21.0D + -YY * 12.0D, 0.0D);
      drawRect(4.0F, -20.0F, 7.0F, -19.0F, clrd);
      drawRect(-7.0F, -20.0F, -4.0F, -19.0F, clrd);
      drawRect(6.0F, -20.0F, 7.0F, -17.5F, clrd);
      drawRect(-7.0F, -20.0F, -6.0F, -17.5F, clrd);
      drawRect(7.0F, -20.0F, 7.3F, -17.5F, backgroundColor);
      drawRect(-7.3F, -20.0F, -7.0F, -17.5F, backgroundColor);
      drawRect(4.0F, -20.3F, 7.3F, -20.0F, backgroundColor);
      drawRect(-7.3F, -20.3F, -4.0F, -20.0F, backgroundColor);
      GL11.glEnable(2929);
      GlStateManager.popMatrix();
      GlStateManager.color(1.0F, 1.0F, 1.0F);
   }

   public static void drawFancy(double d, double e, double f2, double f3, int paramColor) {
      float alpha = (float)(paramColor >> 24 & 255) / 255.0F;
      float red = (float)(paramColor >> 16 & 255) / 255.0F;
      float green = (float)(paramColor >> 8 & 255) / 255.0F;
      float blue = (float)(paramColor & 255) / 255.0F;
      GlStateManager.enableBlend();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GL11.glPushMatrix();
      GL11.glEnable(2848);
      GL11.glEnable(2881);
      GL11.glEnable(2832);
      GL11.glEnable(3042);
      GL11.glColor4f(red, green, blue, alpha);
      GL11.glBegin(7);
      GL11.glVertex2d(f2 + 1.300000011920929D, e);
      GL11.glVertex2d(d + 1.0D, e);
      GL11.glVertex2d(d - 1.300000011920929D, f3);
      GL11.glVertex2d(f2 - 1.0D, f3);
      GL11.glEnd();
      GL11.glDisable(2848);
      GL11.glDisable(2881);
      GL11.glDisable(2832);
      GL11.glDisable(3042);
      GlStateManager.disableBlend();
      GL11.glPopMatrix();
   }

   public static void glColor(int hex) {
      float alpha = (float)(hex >> 24 & 255) / 255.0F;
      float red = (float)(hex >> 16 & 255) / 255.0F;
      float green = (float)(hex >> 8 & 255) / 255.0F;
      float blue = (float)(hex & 255) / 255.0F;
      GL11.glColor4f(red, green, blue, alpha);
   }

   public static void glColor2(int hex) {
      float alpha = 20.0F;
      float red = (float)(hex >> 16 & 255) / 255.0F;
      float green = (float)(hex >> 8 & 255) / 255.0F;
      float blue = (float)(hex & 255) / 255.0F;
      GL11.glColor4f(red, green, blue, alpha);
   }

   public static float[] RGBA(int hex) {
      if ((hex & -67108864) == 0) {
         hex |= -16777216;
      }

      float alpha = (float)(hex >> 24 & 255) / 255.0F;
      float red = (float)(hex >> 16 & 255) / 255.0F;
      float green = (float)(hex >> 8 & 255) / 255.0F;
      float blue = (float)(hex & 255) / 255.0F;
      return new float[]{red, green, blue, alpha};
   }

   public static void drawGradient(double x, double y, double x2, double y2, int col1, int col2) {
      float f = (float)(col1 >> 24 & 255) / 255.0F;
      float f1 = (float)(col1 >> 16 & 255) / 255.0F;
      float f2 = (float)(col1 >> 8 & 255) / 255.0F;
      float f3 = (float)(col1 & 255) / 255.0F;
      float f4 = (float)(col2 >> 24 & 255) / 255.0F;
      float f5 = (float)(col2 >> 16 & 255) / 255.0F;
      float f6 = (float)(col2 >> 8 & 255) / 255.0F;
      float f7 = (float)(col2 & 255) / 255.0F;
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(2848);
      GL11.glShadeModel(7425);
      GL11.glPushMatrix();
      GL11.glBegin(7);
      GL11.glColor4f(f1, f2, f3, f);
      GL11.glVertex2d(x2, y);
      GL11.glVertex2d(x, y);
      GL11.glColor4f(f5, f6, f7, f4);
      GL11.glVertex2d(x, y2);
      GL11.glVertex2d(x2, y2);
      GL11.glEnd();
      GL11.glPopMatrix();
      GL11.glEnable(3553);
      GL11.glDisable(3042);
      GL11.glDisable(2848);
      GL11.glShadeModel(7424);
   }

   public static void drawGradientSideways(double left, double top, double right, double bottom, int col1, int col2) {
      float f = (float)(col1 >> 24 & 255) / 255.0F;
      float f1 = (float)(col1 >> 16 & 255) / 255.0F;
      float f2 = (float)(col1 >> 8 & 255) / 255.0F;
      float f3 = (float)(col1 & 255) / 255.0F;
      float f4 = (float)(col2 >> 24 & 255) / 255.0F;
      float f5 = (float)(col2 >> 16 & 255) / 255.0F;
      float f6 = (float)(col2 >> 8 & 255) / 255.0F;
      float f7 = (float)(col2 & 255) / 255.0F;
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(2848);
      GL11.glShadeModel(7425);
      GL11.glPushMatrix();
      GL11.glBegin(7);
      GL11.glColor4f(f1, f2, f3, f);
      GL11.glVertex2d(left, top);
      GL11.glVertex2d(left, bottom);
      GL11.glColor4f(f5, f6, f7, f4);
      GL11.glVertex2d(right, bottom);
      GL11.glVertex2d(right, top);
      GL11.glEnd();
      GL11.glPopMatrix();
      GL11.glEnable(3553);
      GL11.glDisable(3042);
      GL11.glDisable(2848);
      GL11.glShadeModel(7424);
   }

   public static void rectangle(double left, double top, double right, double bottom, int color) {
      double var5;
      if (left < right) {
         var5 = left;
         left = right;
         right = var5;
      }

      if (top < bottom) {
         var5 = top;
         top = bottom;
         bottom = var5;
      }

      float var11 = (float)(color >> 24 & 255) / 255.0F;
      float var6 = (float)(color >> 16 & 255) / 255.0F;
      float var7 = (float)(color >> 8 & 255) / 255.0F;
      float var8 = (float)(color & 255) / 255.0F;
      WorldRenderer worldRenderer = Tessellator.getInstance().getWorldRenderer();
      GlStateManager.enableBlend();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.color(var6, var7, var8, var11);
      worldRenderer.pos(left, bottom, 0.0D);
      worldRenderer.pos(right, bottom, 0.0D);
      worldRenderer.pos(right, top, 0.0D);
      worldRenderer.pos(left, top, 0.0D);
      Tessellator.getInstance().draw();
      GlStateManager.disableBlend();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
   }

   public static void rectangleBordered(double x, double y, double x1, double y1, double width, int internalColor, int borderColor) {
      rectangle(x + width, y + width, x1 - width, y1 - width, internalColor);
      rectangle(x + width, y, x1 - width, y + width, borderColor);
      rectangle(x, y, x + width, y1, borderColor);
      rectangle(x1 - width, y, x1, y1, borderColor);
      rectangle(x + width, y1 - width, x1 - width, y1, borderColor);
   }

   public static void filledBox(AxisAlignedBB boundingBox, int color, boolean shouldColor) {
      GlStateManager.pushMatrix();
      float var11 = (float)(color >> 24 & 255) / 255.0F;
      float var6 = (float)(color >> 16 & 255) / 255.0F;
      float var7 = (float)(color >> 8 & 255) / 255.0F;
      float var8 = (float)(color & 255) / 255.0F;
      WorldRenderer worldRenderer = Tessellator.getInstance().getWorldRenderer();
      if (shouldColor) {
         GlStateManager.color(var6, var7, var8, var11);
      }

      byte draw = true;
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
      worldRenderer.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
      worldRenderer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
      worldRenderer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
      worldRenderer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
      worldRenderer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
      worldRenderer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
      worldRenderer.pos(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
      worldRenderer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
      Tessellator.getInstance().draw();
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
      worldRenderer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
      worldRenderer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
      worldRenderer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
      worldRenderer.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
      worldRenderer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
      worldRenderer.pos(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
      worldRenderer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
      worldRenderer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
      Tessellator.getInstance().draw();
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
      worldRenderer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
      worldRenderer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
      worldRenderer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
      worldRenderer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
      worldRenderer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
      worldRenderer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
      worldRenderer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
      worldRenderer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
      Tessellator.getInstance().draw();
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
      worldRenderer.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
      worldRenderer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
      worldRenderer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
      worldRenderer.pos(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
      worldRenderer.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
      worldRenderer.pos(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
      worldRenderer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
      worldRenderer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
      Tessellator.getInstance().draw();
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
      worldRenderer.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
      worldRenderer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
      worldRenderer.pos(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
      worldRenderer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
      worldRenderer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
      worldRenderer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
      worldRenderer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
      worldRenderer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
      Tessellator.getInstance().draw();
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
      worldRenderer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
      worldRenderer.pos(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
      worldRenderer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
      worldRenderer.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
      worldRenderer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
      worldRenderer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
      worldRenderer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
      worldRenderer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
      Tessellator.getInstance().draw();
      GlStateManager.depthMask(true);
      GlStateManager.popMatrix();
   }

   public static void drawOutlinedBoundingBox1(AxisAlignedBB boundingBox) {
      Tessellator var1 = Tessellator.getInstance();
      WorldRenderer var2 = var1.getWorldRenderer();
      var2.begin(3, DefaultVertexFormats.POSITION_COLOR);
      var2.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
      var2.pos(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
      var2.pos(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
      var2.pos(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
      var2.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
      var1.draw();
      var2.begin(3, DefaultVertexFormats.POSITION_COLOR);
      var2.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
      var2.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
      var2.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
      var2.pos(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
      var2.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
      var1.draw();
      var2.begin(1, DefaultVertexFormats.POSITION_COLOR);
      var2.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
      var2.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
      var2.pos(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
      var2.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
      var2.pos(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
      var2.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
      var2.pos(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
      var2.pos(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
      var1.draw();
   }

   public static void drawLines(AxisAlignedBB boundingBox) {
      GL11.glPushMatrix();
      GL11.glBegin(2);
      GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
      GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
      GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
      GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
      GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
      GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
      GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
      GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
      GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
      GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
      GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
      GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
      GL11.glEnd();
      GL11.glPopMatrix();
   }

   public static void drawBoundingBox(AxisAlignedBB axisalignedbb) {
      Tessellator tessellator = Tessellator.getInstance();
      WorldRenderer worldrender = Tessellator.getInstance().getWorldRenderer();
      worldrender.begin(7, DefaultVertexFormats.POSITION_COLOR);
      worldrender.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).endVertex();
      worldrender.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).endVertex();
      worldrender.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).endVertex();
      worldrender.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).endVertex();
      worldrender.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).endVertex();
      worldrender.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).endVertex();
      worldrender.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).endVertex();
      worldrender.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).endVertex();
      worldrender.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).endVertex();
      worldrender.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).endVertex();
      worldrender.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).endVertex();
      worldrender.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).endVertex();
      worldrender.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).endVertex();
      worldrender.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).endVertex();
      worldrender.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).endVertex();
      worldrender.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).endVertex();
      worldrender.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).endVertex();
      worldrender.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).endVertex();
      worldrender.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).endVertex();
      worldrender.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).endVertex();
      worldrender.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).endVertex();
      worldrender.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).endVertex();
      worldrender.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).endVertex();
      worldrender.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).endVertex();
      worldrender.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).endVertex();
      worldrender.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).endVertex();
      worldrender.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).endVertex();
      worldrender.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).endVertex();
      worldrender.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).endVertex();
      worldrender.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).endVertex();
      worldrender.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).endVertex();
      worldrender.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).endVertex();
      worldrender.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).endVertex();
      worldrender.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).endVertex();
      worldrender.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).endVertex();
      worldrender.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).endVertex();
      worldrender.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).endVertex();
      worldrender.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).endVertex();
      worldrender.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).endVertex();
      worldrender.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).endVertex();
      worldrender.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).endVertex();
      worldrender.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).endVertex();
      worldrender.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).endVertex();
      worldrender.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).endVertex();
      worldrender.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).endVertex();
      worldrender.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).endVertex();
      worldrender.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).endVertex();
      worldrender.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).endVertex();
      tessellator.draw();
   }

   public static void drawLine3D(float x, float y, float z, float x1, float y1, float z1, int color) {
      pre3D();
      GL11.glLoadIdentity();
      Minecraft.getMinecraft().entityRenderer.orientCamera(Minecraft.getMinecraft().timer.renderPartialTicks);
      float var11 = (float)(color >> 24 & 255) / 255.0F;
      float var6 = (float)(color >> 16 & 255) / 255.0F;
      float var7 = (float)(color >> 8 & 255) / 255.0F;
      float var8 = (float)(color & 255) / 255.0F;
      GL11.glColor4f(var6, var7, var8, var11);
      GL11.glLineWidth(0.5F);
      GL11.glBegin(3);
      GL11.glVertex3d((double)x, (double)y, (double)z);
      GL11.glVertex3d((double)x1, (double)y1, (double)z1);
      GL11.glEnd();
      post3D();
   }

   public static void draw3DLine(float x, float y, float z, int color) {
      pre3D();
      GL11.glLoadIdentity();
      Minecraft.getMinecraft().entityRenderer.orientCamera(Minecraft.getMinecraft().timer.renderPartialTicks);
      float var11 = (float)(color >> 24 & 255) / 255.0F;
      float var6 = (float)(color >> 16 & 255) / 255.0F;
      float var7 = (float)(color >> 8 & 255) / 255.0F;
      float var8 = (float)(color & 255) / 255.0F;
      GL11.glColor4f(var6, var7, var8, var11);
      GL11.glLineWidth(1.5F);
      GL11.glBegin(3);
      GL11.glVertex3d(0.0D, (double)Minecraft.getMinecraft().thePlayer.getEyeHeight(), 0.0D);
      GL11.glVertex3d((double)x, (double)y, (double)z);
      GL11.glEnd();
      post3D();
   }

   public static void pre3D() {
      GL11.glPushMatrix();
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glShadeModel(7425);
      GL11.glDisable(3553);
      GL11.glEnable(2848);
      GL11.glDisable(2929);
      GL11.glDisable(2896);
      GL11.glDepthMask(false);
      GL11.glHint(3154, 4354);
   }

   public static void post3D() {
      GL11.glDepthMask(true);
      GL11.glEnable(2929);
      GL11.glDisable(2848);
      GL11.glEnable(3553);
      GL11.glDisable(3042);
      GL11.glPopMatrix();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
   }

   public static void glColor(float alpha, int redRGB, int greenRGB, int blueRGB) {
      float red = 0.003921569F * (float)redRGB;
      float green = 0.003921569F * (float)greenRGB;
      float blue = 0.003921569F * (float)blueRGB;
      GL11.glColor4f(red, green, blue, alpha);
   }

   public static void drawRect(float x, float y, float x1, float y1) {
      GL11.glBegin(7);
      GL11.glVertex2f(x, y1);
      GL11.glVertex2f(x1, y1);
      GL11.glVertex2f(x1, y);
      GL11.glVertex2f(x, y);
      GL11.glEnd();
   }

   public static void drawRect(float x, float y, float x1, float y1, int color) {
      enableGL2D();
      glColor1(color);
      drawRect(x, y, x1, y1);
      disableGL2D();
   }

   public static void drawRoundedRect(float x, float y, float x1, float y1, float f, int insideC) {
      drawRect(x + 0.5F, y, x1 - 0.5F, y + 0.5F, insideC);
      drawRect(x + 0.5F, y1 - 0.5F, x1 - 0.5F, y1, insideC);
      drawRect(x, y + 0.5F, x1, y1 - 0.5F, insideC);
   }

   public static void drawHLine(float x, float y, float x1, float y1) {
      GL11.glPushMatrix();
      enableGL2D();
      GL11.glColor3f(0.0F, 1.0F, 0.0F);
      GL11.glBegin(3);
      GL11.glVertex2d((double)x, (double)y);
      GL11.glVertex2d((double)x1, (double)y1);
      GL11.glEnd();
      disableGL2D();
      GL11.glPopMatrix();
   }

   public static void drawVLine(float x, float y, float x1, float y1, float width, int color) {
      if (!(width <= 0.0F)) {
         GL11.glPushMatrix();
         pre3D();
         float var11 = (float)(color >> 24 & 255) / 255.0F;
         float var6 = (float)(color >> 16 & 255) / 255.0F;
         float var7 = (float)(color >> 8 & 255) / 255.0F;
         float var8 = (float)(color & 255) / 255.0F;
         GlStateManager.enableAlpha();
         GlStateManager.enableBlend();
         int shade = GL11.glGetInteger(2900);
         GlStateManager.shadeModel(7425);
         GL11.glColor4f(var6, var7, var8, var11);
         float line = GL11.glGetFloat(2849);
         GL11.glLineWidth(width);
         GL11.glBegin(3);
         GL11.glVertex3d((double)x, (double)y, 0.0D);
         GL11.glVertex3d((double)x1, (double)y1, 0.0D);
         GL11.glEnd();
         GlStateManager.shadeModel(shade);
         GL11.glLineWidth(line);
         post3D();
         GL11.glPopMatrix();
      }
   }

   public static void enableGL2D() {
      GL11.glDisable(2929);
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      GL11.glBlendFunc(770, 771);
      GL11.glDepthMask(true);
      GL11.glEnable(2848);
      GL11.glHint(3154, 4354);
      GL11.glHint(3155, 4354);
   }

   public static void disableGL2D() {
      GL11.glEnable(3553);
      GL11.glDisable(3042);
      GL11.glEnable(2929);
      GL11.glDisable(2848);
      GL11.glHint(3154, 4352);
      GL11.glHint(3155, 4352);
   }

   public static void drawEllipse(float cx, float cy, float rx, float ry, int num_segments, int col) {
      GL11.glPushMatrix();
      cx *= 2.0F;
      cy *= 2.0F;
      float theta = (float)(6.283185307179586D / (double)num_segments);
      float c = (float)Math.cos((double)theta);
      float s = (float)Math.sin((double)theta);
      float f = (float)(col >> 24 & 255) / 255.0F;
      float f1 = (float)(col >> 16 & 255) / 255.0F;
      float f2 = (float)(col >> 8 & 255) / 255.0F;
      float f3 = (float)(col & 255) / 255.0F;
      float x = 1.0F;
      float y = 0.0F;
      enableGL2D();
      GL11.glScalef(0.5F, 0.5F, 0.5F);
      GL11.glColor4f(f1, f2, f3, f);
      GL11.glBegin(2);

      for(int ii = 0; ii < num_segments; ++ii) {
         GL11.glVertex2f(x * rx + cx, y * ry + cy);
         float t = x;
         x = c * x - s * y;
         y = s * t + c * y;
      }

      GL11.glEnd();
      GL11.glScalef(2.0F, 2.0F, 2.0F);
      disableGL2D();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glPopMatrix();
   }

   public static void drawCircle(float cx, float cy, float r, int num_segments, int c) {
      GL11.glPushMatrix();
      cx *= 2.0F;
      cy *= 2.0F;
      float f = (float)(c >> 24 & 255) / 255.0F;
      float f1 = (float)(c >> 16 & 255) / 255.0F;
      float f2 = (float)(c >> 8 & 255) / 255.0F;
      float f3 = (float)(c & 255) / 255.0F;
      float theta = (float)(6.2831852D / (double)num_segments);
      float p = (float)Math.cos((double)theta);
      float s = (float)Math.sin((double)theta);
      float x = r *= 2.0F;
      float y = 0.0F;
      enableGL2D();
      GL11.glScalef(0.5F, 0.5F, 0.5F);
      GL11.glColor4f(f1, f2, f3, f);
      GL11.glBegin(2);

      for(int ii = 0; ii < num_segments; ++ii) {
         GL11.glVertex2f(x + cx, y + cy);
         float t = x;
         x = p * x - s * y;
         y = s * t + p * y;
      }

      GL11.glEnd();
      GL11.glScalef(2.0F, 2.0F, 2.0F);
      disableGL2D();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glPopMatrix();
   }

   public static void drawBorderedCircle(float circleX, float circleY, double radius, double width, int borderColor, int innerColor) {
      enableGL2D();
      GlStateManager.enableBlend();
      GL11.glEnable(2881);
      drawCircle(circleX, circleY, (float)(radius - 0.5D + width), 72, borderColor);
      drawFullCircle(circleX, circleY, (float)radius, innerColor);
      GlStateManager.disableBlend();
      GL11.glDisable(2881);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      disableGL2D();
   }

   public static void drawCircleNew(float x, float y, float radius, int numberOfSides) {
      float z = 0.0F;
      int numberOfVertices = numberOfSides + 2;
      float doublePi = 6.2831855F;
   }

   public static void drawFullCircle(float cx, float cy, float r, int c) {
      r *= 2.0F;
      cx *= 2.0F;
      cy *= 2.0F;
      float theta = 0.19634953F;
      float p = (float)Math.cos((double)theta);
      float s = (float)Math.sin((double)theta);
      float x = r;
      float y = 0.0F;
      enableGL2D();
      GL11.glEnable(2848);
      GL11.glHint(3154, 4354);
      GL11.glEnable(3024);
      GL11.glScalef(0.5F, 0.5F, 0.5F);
      glColor1(c);
      GL11.glBegin(9);

      for(int ii = 0; ii < 32; ++ii) {
         GL11.glVertex2f(x + cx, y + cy);
         float t = x;
         x = p * x - s * y;
         y = s * t + p * y;
      }

      GL11.glEnd();
      GL11.glScalef(2.0F, 2.0F, 2.0F);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      disableGL2D();
   }

   public static void ohnoes() {
      Minecraft.getMinecraft().shutdown();
   }

   public static void drawOutlinedBoundingBox(AxisAlignedBB aa) {
      Tessellator tessellator = Tessellator.getInstance();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      worldRenderer.begin(3, DefaultVertexFormats.POSITION);
      worldRenderer.pos(aa.minX, aa.minY, aa.minZ).endVertex();
      worldRenderer.pos(aa.maxX, aa.minY, aa.minZ).endVertex();
      worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ).endVertex();
      worldRenderer.pos(aa.minX, aa.minY, aa.maxZ).endVertex();
      worldRenderer.pos(aa.minX, aa.minY, aa.minZ).endVertex();
      tessellator.draw();
      worldRenderer.begin(3, DefaultVertexFormats.POSITION);
      worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
      worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ).endVertex();
      worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ).endVertex();
      worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ).endVertex();
      worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
      tessellator.draw();
      worldRenderer.begin(1, DefaultVertexFormats.POSITION);
      worldRenderer.pos(aa.minX, aa.minY, aa.minZ).endVertex();
      worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
      worldRenderer.pos(aa.maxX, aa.minY, aa.minZ).endVertex();
      worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ).endVertex();
      worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ).endVertex();
      worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ).endVertex();
      worldRenderer.pos(aa.minX, aa.minY, aa.maxZ).endVertex();
      worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ).endVertex();
      tessellator.draw();
   }

   public static void drawOutlinedBlockESP(double x, double y, double z, float red, float green, float blue, float alpha, float lineWidth) {
      GL11.glPushMatrix();
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glDisable(3553);
      GL11.glEnable(2848);
      GL11.glDisable(2929);
      GL11.glDepthMask(false);
      GL11.glLineWidth(lineWidth);
      GL11.glColor4f(red, green, blue, alpha);
      drawOutlinedBoundingBox1(new AxisAlignedBB(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D));
      GL11.glDisable(2848);
      GL11.glEnable(3553);
      GL11.glEnable(2929);
      GL11.glDepthMask(true);
      GL11.glDisable(3042);
      GL11.glPopMatrix();
   }

   public static void drawBlockESP(double x, double y, double z, float red, float green, float blue, float alpha, float lineRed, float lineGreen, float lineBlue, float lineAlpha, float lineWidth) {
      GL11.glPushMatrix();
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glDisable(3553);
      GL11.glEnable(2848);
      GL11.glDisable(2929);
      GL11.glDepthMask(false);
      GL11.glColor4f(red, green, blue, alpha);
      drawBoundingBox(new AxisAlignedBB(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D));
      GL11.glLineWidth(lineWidth);
      GL11.glColor4f(lineRed, lineGreen, lineBlue, lineAlpha);
      drawOutlinedBoundingBox1(new AxisAlignedBB(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D));
      GL11.glDisable(2848);
      GL11.glEnable(3553);
      GL11.glEnable(2929);
      GL11.glDepthMask(true);
      GL11.glDisable(3042);
      GL11.glPopMatrix();
   }

   public static void drawSolidBlockESP(double x, double y, double z, float red, float green, float blue, float alpha) {
      GL11.glPushMatrix();
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glDisable(3553);
      GL11.glEnable(2848);
      GL11.glDisable(2929);
      GL11.glDepthMask(false);
      GL11.glColor4f(red, green, blue, alpha);
      drawBoundingBox(new AxisAlignedBB(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D));
      GL11.glDisable(2848);
      GL11.glEnable(3553);
      GL11.glEnable(2929);
      GL11.glDepthMask(true);
      GL11.glDisable(3042);
      GL11.glPopMatrix();
   }

   public static void drawOutlinedEntityESP(double x, double y, double z, double width, double height, float red, float green, float blue, float alpha) {
      GL11.glPushMatrix();
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glDisable(3553);
      GL11.glEnable(2848);
      GL11.glDisable(2929);
      GL11.glDepthMask(false);
      GL11.glColor4f(red, green, blue, alpha);
      drawOutlinedBoundingBox1(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
      GL11.glDisable(2848);
      GL11.glEnable(3553);
      GL11.glEnable(2929);
      GL11.glDepthMask(true);
      GL11.glDisable(3042);
      GL11.glPopMatrix();
   }

   public static void drawSolidEntityESP(double x, double y, double z, double width, double height, float red, float green, float blue, float alpha) {
      GL11.glPushMatrix();
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glDisable(2896);
      GL11.glDisable(3553);
      GL11.glEnable(2848);
      GL11.glDisable(2929);
      GL11.glDepthMask(false);
      GlStateManager.enableBlend();
      GL11.glColor4f(red, green, blue, alpha);
      drawBoundingBox(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
      GL11.glDisable(2848);
      GL11.glEnable(3553);
      GL11.glEnable(2896);
      GL11.glEnable(2929);
      GL11.glDepthMask(true);
      GlStateManager.disableBlend();
      GL11.glDisable(3042);
      GL11.glPopMatrix();
   }

   public static void drawEntityESP(double x, double y, double z, double width, double height, float red, float green, float blue, float alpha, float lineRed, float lineGreen, float lineBlue, float lineAlpha, float lineWdith) {
      GL11.glPushMatrix();
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glDisable(3553);
      GL11.glEnable(2848);
      GL11.glDisable(2929);
      GL11.glDepthMask(false);
      GL11.glColor4f(red, green, blue, alpha);
      drawBoundingBox(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
      GL11.glLineWidth(lineWdith);
      GL11.glColor4f(lineRed, lineGreen, lineBlue, lineAlpha);
      drawOutlinedBoundingBox1(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
      GL11.glDisable(2848);
      GL11.glEnable(3553);
      GL11.glEnable(2929);
      GL11.glDepthMask(true);
      GL11.glDisable(3042);
      GL11.glPopMatrix();
   }

   public static void drawTracerLine(double x, double y, double z, float red, float green, float blue, float alpha, float lineWdith) {
      GL11.glPushMatrix();
      GL11.glEnable(3042);
      GL11.glEnable(2848);
      GL11.glDisable(2929);
      GL11.glDisable(3553);
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(3042);
      GL11.glLineWidth(lineWdith);
      GL11.glColor4f(red, green, blue, alpha);
      GL11.glBegin(2);
      GL11.glVertex3d(0.0D, 0.0D + (double)Minecraft.getMinecraft().thePlayer.getEyeHeight(), 0.0D);
      GL11.glVertex3d(x, y, z);
      GL11.glEnd();
      GL11.glDisable(3042);
      GL11.glEnable(3553);
      GL11.glEnable(2929);
      GL11.glDisable(2848);
      GL11.glDisable(3042);
      GL11.glPopMatrix();
   }

   public static void drawFilledBox(AxisAlignedBB axisAlignedBB) {
      Tessellator tessellator = Tessellator.getInstance();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_NORMAL);
      worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
      worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
      worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
      worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
      worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
      worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
      worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
      worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
      tessellator.draw();
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_NORMAL);
      worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
      worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
      worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
      worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
      worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
      worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
      worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
      worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
      tessellator.draw();
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_NORMAL);
      worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
      worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
      worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
      worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
      worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
      worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
      worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
      worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
      tessellator.draw();
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_NORMAL);
      worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
      worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
      worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
      worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
      worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
      worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
      worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
      worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
      tessellator.draw();
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_NORMAL);
      worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
      worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
      worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
      worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
      worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
      worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
      worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
      worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
      tessellator.draw();
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_NORMAL);
      worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
      worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
      worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
      worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
      worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
      worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
      worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
      worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
      tessellator.draw();
   }
}
