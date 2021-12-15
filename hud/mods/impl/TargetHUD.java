package matrix.hud.mods.impl;

import matrix.hud.mods.HudMod;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;

public class TargetHUD extends HudMod {
   EntityLivingBase target;

   public TargetHUD() {
      super("TargetHUD", 150, 150);
   }

   public void draw() {
      this.renderTargetHud();
      super.draw();
   }

   public void renderDummy(int mouseX, int mouseY) {
      this.fr.drawStringWithShadow(this.mc.thePlayer.getName(), (double)(this.getX() + 2), (double)(this.getY() + 2), -1);
      this.fr.drawStringWithShadow((int)this.mc.thePlayer.getHealth() + " ❤", (double)(this.getX() + 2), (double)(this.getY() + 2 + this.fr.FONT_HEIGHT), -1);
      GuiInventory.drawEntityOnScreen(this.getX() + this.fr.getStringWidth(this.mc.thePlayer.getName()) + 30, this.getY() + 30, 20, 50.0F, 0.0F, this.mc.thePlayer);
      super.renderDummy(mouseX, mouseY);
   }

   public int getW() {
      return this.target != null ? this.fr.getStringWidth(this.target.getName()) + 70 : this.fr.getStringWidth("quickIsGay") + 70;
   }

   public int getH() {
      return this.fr.FONT_HEIGHT * 2 + 15;
   }

   private void renderTargetHud() {
      if (!(this.mc.pointedEntity instanceof EntityItemFrame)) {
         this.target = (EntityLivingBase)this.mc.pointedEntity;
         if (this.target != null) {
            this.fr.drawStringWithShadow(this.target.getName(), (double)(this.getX() + 2), (double)(this.getY() + 2), -1);
            this.fr.drawStringWithShadow((int)this.target.getHealth() + " ❤", (double)(this.getX() + 2), (double)(this.getY() + 2 + this.fr.FONT_HEIGHT), -1);
            GuiInventory.drawEntityOnScreen(this.getX() + this.fr.getStringWidth(this.target.getName()) + 25, this.getY() + 30, 20, 50.0F, 0.0F, this.target);
         }
      }

   }
}
