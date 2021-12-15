package matrix.hud.mods.impl;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.List;
import matrix.hud.mods.HudMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.util.ResourceLocation;

public class PackDisplay extends HudMod {
   private ResourceLocation thumbnail;
   private Minecraft mc = Minecraft.getMinecraft();
   public Rectangle settingHUDRect = new Rectangle(0, 0, 96, 32);
   IResourcePack pack = this.getCurrentPack();

   public PackDisplay() {
      super("Pack Display", 200, 200);
   }

   public void draw() {
      this.pack = this.getCurrentPack();
      this.loadTexture();
      Gui.drawRect((double)(this.getX() + 5), (double)(this.getY() + 5), (double)(this.getX() + this.fr.getStringWidth(this.pack.getPackName()) + 60), (double)(this.getY() + 38), (new Color(0, 0, 0, 135)).getRGB());
      if (this.thumbnail != null) {
         GlStateManager.pushMatrix();
         GlStateManager.enableBlend();
         GlStateManager.color(1.0F, 1.0F, 1.0F);
         this.mc.getTextureManager().bindTexture(this.thumbnail);
         Gui.drawModalRectWithCustomSizedTexture(this.getX() + 10, this.getY() + this.fr.FONT_HEIGHT, 0.0F, 0.0F, 25, 25, 25.0F, 25.0F);
         GlStateManager.popMatrix();
      }

      this.fr.drawStringWithShadow(this.pack.getPackName(), (double)(this.getX() + 45), (double)(this.getY() + 18), -1);
   }

   public void renderDummy(int mouseX, int mouseY) {
      this.pack = this.getCurrentPack();
      this.loadTexture();
      Gui.drawRect((double)(this.getX() + 5), (double)(this.getY() + 5), (double)(this.getX() + this.fr.getStringWidth(this.pack.getPackName()) + 60), (double)(this.getY() + 38), (new Color(0, 0, 0, 135)).getRGB());
      if (this.thumbnail != null) {
         GlStateManager.pushMatrix();
         GlStateManager.enableBlend();
         GlStateManager.color(1.0F, 1.0F, 1.0F);
         this.mc.getTextureManager().bindTexture(this.thumbnail);
         Gui.drawModalRectWithCustomSizedTexture(this.getX() + 10, this.getY() + this.fr.FONT_HEIGHT, 0.0F, 0.0F, 25, 25, 25.0F, 25.0F);
         GlStateManager.popMatrix();
      }

      this.fr.drawStringWithShadow(this.pack.getPackName(), (double)(this.getX() + 45), (double)(this.getY() + 18), -1);
      super.renderDummy(mouseX, mouseY);
   }

   public int getWidth() {
      return this.fr.getStringWidth("[Ping: 000]");
   }

   public int getHeight() {
      return this.fr.FONT_HEIGHT;
   }

   private IResourcePack getCurrentPack() {
      List list = this.mc.getResourcePackRepository().getRepositoryEntries();
      Object pack = null;
      return (IResourcePack)(list.size() > 0 ? ((ResourcePackRepository.Entry)list.get(0)).getResourcePack() : this.mc.mcDefaultResourcePack);
   }

   public void loadTexture() {
      DynamicTexture dt;
      try {
         dt = new DynamicTexture(this.getCurrentPack().getPackImage());
      } catch (Exception var3) {
         dt = TextureUtil.missingTexture;
      }

      this.thumbnail = this.mc.getTextureManager().getDynamicTextureLocation("texturepackicon", dt);
   }
}
