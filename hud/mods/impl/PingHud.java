package matrix.hud.mods.impl;

import matrix.hud.mods.HudMod;

public class PingHud extends HudMod {
   public PingHud() {
      super("PingHud", 200, 200);
   }

   public void draw() {
      if (this.mc.getNetHandler() != null && this.mc.thePlayer != null && this.mc.getNetHandler().getPlayerInfo(this.mc.thePlayer.getUniqueID()) != null) {
         this.fr.drawStringWithShadow("[Ping: " + this.mc.getNetHandler().getPlayerInfo(this.mc.thePlayer.getUniqueID()).getResponseTime() + "]", (double)this.getX(), (double)this.getY(), -1);
      }

      super.draw();
   }

   public void renderDummy(int mouseX, int mouseY) {
      this.fr.drawStringWithShadow("[Ping: 000]", (double)this.getX(), (double)this.getY(), -1);
      super.renderDummy(mouseX, mouseY);
   }

   public int getW() {
      return this.fr.getStringWidth("[Ping: 000]");
   }

   public int getH() {
      return this.fr.FONT_HEIGHT;
   }
}
