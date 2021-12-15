package matrix.ui.buttons;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class CosmeticsButton extends GuiButton {
   private int j6;
   private int j10;
   int fade;
   int fade2;

   public CosmeticsButton(int i, int j, int k, String s) {
      this(i, j, k, 120, k, s);
   }

   public CosmeticsButton(int i, int j, int k, int l, int i1, String s) {
      super(i, j, k, l, i1, s);
      this.enabled = true;
      this.visible = true;
   }

   protected int getHoverState(boolean flag) {
      byte byte0 = 1;
      if (!this.enabled) {
         byte0 = 0;
      } else if (flag) {
         byte0 = 2;
      }

      return byte0;
   }

   public void drawButton(Minecraft mc, int mouseX, int mouseY) {
      this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
      int j = -1;
      if (this.hovered) {
         j = 35071;
      }

      if (!this.hovered) {
         this.fade = 230;
      } else {
         if (this.fade <= 50) {
            return;
         }

         if (this.fade != 160) {
            this.fade -= 10;
         }
      }

      if (!this.hovered) {
         this.fade2 = 200;
      } else {
         if (this.fade2 <= 30) {
            return;
         }

         if (this.fade2 != 200) {
            this.fade2 += 10;
         }
      }

      float b = (float)(this.hovered ? (new Color(30, 30, 30, 100)).getRGB() : (new Color(30, 30, 30, 30)).getRGB());
      Color a = new Color(15, 15, 15, this.fade);
      Color a2 = new Color(100, 100, 100, this.fade2);
      Color a3 = new Color(80, 80, 80, this.fade);
      if (this.xPosition >= this.xPosition && this.yPosition >= this.yPosition && this.xPosition < this.xPosition + this.width && this.yPosition < this.yPosition + this.height) {
         int n = true;
         FontRenderer fr = mc.fontRendererObj;
         this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
         this.drawRoundedRect(this.xPosition - 1, this.yPosition - 1, this.width + 2, this.height + 2, 3, a2);
         this.drawRoundedRect(this.xPosition, this.yPosition, this.width, this.height, 3, a3);
         this.drawRoundedRect(this.xPosition, this.yPosition, this.width, this.height, 3, a);
         GlStateManager.color(1.0F, 1.0F, 1.0F);
         Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("client/icon/cosmetics_icon.png"));
         Gui.drawModalRectWithCustomSizedTexture(this.xPosition + 3, this.yPosition + 3, 0.0F, 0.0F, 15, 15, 15.0F, 15.0F);
         drawCenteredString(fr, this.displayString, (double)(this.xPosition + this.width / 2), (double)(this.yPosition + (this.height - 8) / 2), j);
      }

   }

   private void drawCircle(int x, int y, int width, int height, Color color) {
      this.drawArc(x + width / 2, y + height / 2, width / 2, 0, 360, color);
   }

   private void drawArc(int x, int y, int radius, int startAngle, int endAngle, Color color) {
      GL11.glPushMatrix();
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      GL11.glBlendFunc(770, 771);
      GL11.glColor4f((float)color.getRed() / 255.0F, (float)color.getGreen() / 255.0F, (float)color.getBlue() / 255.0F, (float)color.getAlpha() / 255.0F);
      WorldRenderer worldRenderer = Tessellator.getInstance().getWorldRenderer();
      worldRenderer.begin(6, DefaultVertexFormats.POSITION);
      worldRenderer.pos((double)x, (double)y, 0.0D).endVertex();

      for(int i = (int)((double)startAngle / 360.0D * 100.0D); i <= (int)((double)endAngle / 360.0D * 100.0D); ++i) {
         double angle = 6.283185307179586D * (double)i / 100.0D + Math.toRadians(180.0D);
         worldRenderer.pos((double)x + Math.sin(angle) * (double)radius, (double)y + Math.cos(angle) * (double)radius, 0.0D).endVertex();
      }

      Tessellator.getInstance().draw();
      GL11.glEnable(3553);
      GL11.glDisable(3042);
      GL11.glPopMatrix();
   }

   private void drawRoundedRect(int x, int y, int width, int height, int cornerRadius, Color color) {
      drawRect((double)x, (double)(y + cornerRadius), (double)(x + cornerRadius), (double)(y + height - cornerRadius), color.getRGB());
      drawRect((double)(x + cornerRadius), (double)y, (double)(x + width - cornerRadius), (double)(y + height), color.getRGB());
      drawRect((double)(x + width - cornerRadius), (double)(y + cornerRadius), (double)(x + width), (double)(y + height - cornerRadius), color.getRGB());
      this.drawArc(x + cornerRadius, y + cornerRadius, cornerRadius, 0, 90, color);
      this.drawArc(x + width - cornerRadius, y + cornerRadius, cornerRadius, 270, 360, color);
      this.drawArc(x + width - cornerRadius, y + height - cornerRadius, cornerRadius, 180, 270, color);
      this.drawArc(x + cornerRadius, y + height - cornerRadius, cornerRadius, 90, 180, color);
   }
}
