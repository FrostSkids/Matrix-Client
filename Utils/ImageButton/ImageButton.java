package matrix.Utils.ImageButton;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ImageButton extends GuiButton {
   private int ani = 0;
   public ResourceLocation image;

   public ImageButton(int buttonId, int x, int y, int widthIn, int heightIn, ResourceLocation image) {
      super(buttonId, x, y, widthIn, heightIn, "");
      this.image = image;
   }

   public void drawButton(Minecraft mc, int mouseX, int mouseY) {
      this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
      if (this.hovered) {
         if (this.ani < 2) {
            ++this.ani;
         }
      } else if (this.ani > 0) {
         --this.ani;
      }

      drawTexture(this.image, (float)(this.xPosition - this.ani), (float)(this.yPosition - this.ani), (float)(this.width + this.ani * 2), (float)(this.height + this.ani * 2));
   }

   public void setImage(ResourceLocation image) {
      this.image = image;
   }

   public static void drawTexture(ResourceLocation resourceLocation, float x, float y, float width, float height) {
      GL11.glPushMatrix();
      float size = width / 2.0F;
      GL11.glEnable(3042);
      GL11.glEnable(3553);
      GL11.glEnable(2848);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      Minecraft.getMinecraft().getTextureManager().bindTexture(resourceLocation);
      GL11.glBegin(7);
      GL11.glTexCoord2d((double)(0.0F / size), (double)(0.0F / size));
      GL11.glVertex2d((double)x, (double)y);
      GL11.glTexCoord2d((double)(0.0F / size), (double)((0.0F + size) / size));
      GL11.glVertex2d((double)x, (double)(y + height));
      GL11.glTexCoord2d((double)((0.0F + size) / size), (double)((0.0F + size) / size));
      GL11.glVertex2d((double)(x + width), (double)(y + height));
      GL11.glTexCoord2d((double)((0.0F + size) / size), (double)(0.0F / size));
      GL11.glVertex2d((double)(x + width), (double)y);
      GL11.glEnd();
      GL11.glEnable(3553);
      GL11.glDisable(2848);
      GL11.glDisable(3042);
      GL11.glPopMatrix();
   }
}
